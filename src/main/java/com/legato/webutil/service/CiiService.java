package com.legato.webutil.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.legato.webutil.domain.documents.Document;

public interface CiiService {
	public List<Document> getDocumentList(String rootPath) throws IOException;
	public boolean uploadDoc(MultipartFile file, int fileType) throws IOException;
	public void downloadCommonFile(File file, HttpServletResponse response) throws IOException;
	public Map<String, Object> createFolder(String path, String folderName);
	public String removefile(int fileType, String fileName);
}