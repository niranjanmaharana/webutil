package com.legato.webutil.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.legato.webutil.service.CiiService;
import com.legato.webutil.service.impl.UrlCoderServiceImpl;

@Controller
@RequestMapping("/cii")
public class CiiController {
	private static final Logger LOGGER = Logger.getLogger(CiiController.class);
	@Autowired private UrlCoderServiceImpl urlCoder;
	@Autowired private CiiService ciiService;
	@Value("${cii.rootPath}")
	private String rootPath;
	
	@GetMapping("/documents/list")
	public @ResponseBody ResponseEntity<Object> getDocumentList(HttpServletRequest request, 
			HttpServletResponse response, 
			@RequestParam("rootPath") String rootPath){
		try {
			return new ResponseEntity<>(ciiService.getDocumentList(rootPath), HttpStatus.OK);
		} catch (IOException exception) {
			LOGGER.error("Error occurred while fetching the file list ! " + exception.getMessage());
			return new ResponseEntity<>("Error occurred while fetching the file list !", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/documents")
	public ModelAndView listDocuments(HttpServletRequest request, 
			HttpServletResponse response) throws UnsupportedEncodingException{
		return new ModelAndView("documentlist").addObject("rootPath", urlCoder.encode(rootPath));
	}
	
	@GetMapping("/downloadFile")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("filePath") String filePath) throws IOException{
		LOGGER.info("File Path : " + filePath);
		File file = new File(filePath);
		ciiService.downloadCommonFile(file, response);
	}

	@GetMapping("/uploadform")
	public ModelAndView docUploadForm(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("uploadciidoc");
	}
	
	@PostMapping("/upload")
	public ResponseEntity<Object> uploadDoc(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("file") MultipartFile file, @RequestParam("filePath") String filePath) throws IOException{
		if (!file.getOriginalFilename().isEmpty()) {
			ciiService.uploadDoc(file, urlCoder.decode(filePath));
		} else {
			LOGGER.error("File uploading has been cancelled since file name is not valid !");
			return new ResponseEntity<>("Invalid file.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("File Uploaded Successfully.", HttpStatus.OK);
	}
	
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
        if(mimeType==null)
            mimeType = "application/octet-stream";        
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
        response.setContentLength((int)file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
        LOGGER.info(file.getAbsolutePath() + " downloaded.");
	}
	
	@PostMapping("/createfolder")
	public @ResponseBody Map<String, Object> createFolder(HttpServletRequest request, HttpServletResponse response,  
			@RequestParam("path") String path, 
			@RequestParam("folderName") String folderName){
		return ciiService.createFolder(path, folderName);
	}
	
	@PostMapping("/removefile")
	public @ResponseBody ResponseEntity<Object> removefile(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("fileType") int fileType, @RequestParam("fileName") String fileName){
		try{
			return new ResponseEntity<>(ciiService.removefile(fileType, fileName), HttpStatus.OK);
		} catch(Exception exception){
			LOGGER.info("Error occurred while removing the file : " + fileName + " ! " + exception.getMessage());
		}
		return new ResponseEntity<>("Error occurred while removing the file !", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}