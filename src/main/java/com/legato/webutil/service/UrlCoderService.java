package com.legato.webutil.service;

public interface UrlCoderService {
	public String encode(String urlToEncode);
	public String decode(String urlToDecode);
}