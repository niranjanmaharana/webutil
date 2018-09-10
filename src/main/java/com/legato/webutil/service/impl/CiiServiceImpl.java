package com.legato.webutil.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.legato.webutil.domain.documents.Document;
import com.legato.webutil.domain.documents.FileType;
import com.legato.webutil.service.CiiService;

@Service
public class CiiServiceImpl implements CiiService{
	private static final Logger LOGGER = Logger.getLogger(CiiServiceImpl.class);
	@Autowired private UrlCoderServiceImpl urlCoderService;
	@Value("${cii.rootPath}")
	private String rootPath;
	
	@Override
	public List<Document> getDocumentList(String rootPath) throws IOException{
		rootPath = urlCoderService.decode(rootPath);
		File folder = new File(rootPath);
		List<Document> documents = new ArrayList<>();
		if(folder != null && folder.exists()){
			File[] files = new File(rootPath).listFiles();
			for (File file : files){
				BasicFileAttributes fileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
				Document document = new Document();
				document.setDirectory(file.isDirectory());
				document.setFileName(file.getName());
				document.setFilePath(file.getAbsolutePath());
				document.setCreationTime(new Date(fileAttributes.creationTime().to(TimeUnit.MILLISECONDS)));
				document.setLastAccessTime(new Date(fileAttributes.lastAccessTime().to(TimeUnit.MILLISECONDS)));
				document.setLastModifiedTime(new Date(fileAttributes.lastModifiedTime().to(TimeUnit.MILLISECONDS)));
				documents.add(document);
			}
		}
		return documents;
	}
	
	@Override
	public boolean uploadDoc(MultipartFile file, String filePath) throws IOException{
		try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(filePath, file.getOriginalFilename())))) {
			outputStream.write(file.getBytes());
			outputStream.flush();
			LOGGER.info("File uploaded : " + filePath + File.separator + file.getOriginalFilename());
			return true;
		}catch(Exception exception){
			LOGGER.error("Error occurred while uploading the document ! " + exception.getMessage());
			return false;
		}
	}
	
	@Override
	public void downloadCommonFile(File file, HttpServletResponse response) throws IOException{
		if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            LOGGER.error(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            LOGGER.error("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }
        
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
        response.setContentLength((int)file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
        LOGGER.info(file.getAbsolutePath() + " downloaded.");
	}
	
	@Override
	public Map<String, Object> createFolder(String path, String folderName){
		Map<String, Object> result = new LinkedHashMap<>();
		path = urlCoderService.decode(path);
		folderName = urlCoderService.decode(folderName);
		File newFile = new File(path + File.separator + folderName);
		boolean created = false;
		if(!newFile.exists()){
			created = newFile.mkdirs();
			result.put("message", created ? "Folder created successfully." : "Error occurred while creating the directory !");
			result.put("responseCode", created ? 200 : 500);
		}
		else{
			result.put("message", "Folder already exists !");
			result.put("responseCode", 409);
		}
		LOGGER.info(path + File.separator + folderName + (created ? " created." : " not created !"));
		return result;
	}
	
	@Override
	public String removefile(int fileType, String fileName){
		File fileToRemove = new File((fileType == FileType.DOCUMENTS.getId() ? "" : "") + File.separator + fileName);
		if(fileToRemove.exists())
			fileToRemove.delete();
		LOGGER.info(fileName + " removed from " + (fileType == FileType.DOCUMENTS.getId() ? FileType.DOCUMENTS.getName() : FileType.WEBEX.getName()));
		return "File removed successfully.";
	}
}