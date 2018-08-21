package com.legato.webutil.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/common")
public class CommonController {
	private static final Logger LOGGER = Logger.getLogger(CommonController.class);
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(@RequestParam("filePath") String filePath,  HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		File file = null;
		filePath = URLDecoder.decode(filePath, "UTF-8");
        file = new File(filePath);
        LOGGER.error(URLDecoder.decode(filePath, "UTF-8"));
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
        
        LOGGER.info(filePath + " downloaded.");
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
        response.setContentLength((int)file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
}