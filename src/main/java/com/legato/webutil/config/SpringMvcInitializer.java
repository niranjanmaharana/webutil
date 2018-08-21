package com.legato.webutil.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.legato.webutil.security.config.SecurityConfiguration;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfiguration.class };
	}
 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return  new Class[] { SecurityConfiguration.class };
	}
 
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}