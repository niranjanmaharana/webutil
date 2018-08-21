package com.legato.webutil.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.legato.webutil.service.UrlCoderService;

@Service
public class UrlCoderServiceImpl implements UrlCoderService{
	public static final String ENC = "UTF-8";
	private static final Logger LOGGER = Logger.getLogger(UrlCoderServiceImpl.class);
	
	public String encode(String urlToEncode){
		try{
			return URLEncoder.encode(urlToEncode, ENC);
		} catch(UnsupportedEncodingException exception){
			LOGGER.error("Unable to encode " + urlToEncode + ", " + exception.getMessage());
		}
		return urlToEncode;
	}
	
	public String decode(String urlToDecode){
		try{
			return URLDecoder.decode(urlToDecode, ENC);
		} catch(UnsupportedEncodingException exception){
			LOGGER.error("Unable to decode " + urlToDecode + ", " + exception.getMessage());
		}
		return urlToDecode;
	}
}