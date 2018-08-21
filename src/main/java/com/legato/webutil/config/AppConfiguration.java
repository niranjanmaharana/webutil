package com.legato.webutil.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.legato.webutil.model.UserProfile;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.legato.webutil")
@PropertySource({"classpath:/application.properties", "classpath:/users.properties"})
public class AppConfiguration implements WebMvcConfigurer {
	@Value( "${user.afid}" )
	private String afid;
	
	@Value( "${user.fullname}" )
	private String fullname;
	
	@Value( "${user.password}" )
	private String password;
	
	@Value( "${user.role}" )
	private String role;
	
	private static final Logger LOGGER = Logger.getLogger(AppConfiguration.class);
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(31457280); // 30MB
		multipartResolver.setMaxUploadSizePerFile(31457280); // 3MB
		return multipartResolver;
	}
	
	@Bean
	public List<UserProfile> userProfiles(){
		LOGGER.info("User profiles to be configured with security configuration : ");
		List<UserProfile> profiles = new ArrayList<>();
		String[] afids = afid.split(",");
		String[] fullnames = fullname.split(",");
		String[] passwords = password.split(",");
		String[] roles = role.split(",");
		for(int i = 0; i < afids.length; i++){
			UserProfile profile = new UserProfile();
			profile.setAfId(afids[i]);
			profile.setUsername(afids[i]);
			profile.setPassword(passwords[i]);
			profile.setRole(roles[i]);
			profile.setFirstname(fullnames[i]);
			LOGGER.info(profile);
			profiles.add(profile);
		}
		return profiles;
	}
}