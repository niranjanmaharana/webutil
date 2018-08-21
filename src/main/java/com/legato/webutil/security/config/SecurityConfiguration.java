package com.legato.webutil.security.config;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.legato.webutil.model.UserProfile;
import com.legato.webutil.util.NoPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired private List<UserProfile> userProfiles;
	private static final Logger LOGGER = Logger.getLogger(SecurityConfiguration.class);

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		userProfiles.forEach(profile -> {
			try {
				auth.inMemoryAuthentication().passwordEncoder(NoPasswordEncoder.getInstance()).withUser(profile.getAfId()).password(profile.getPassword()).roles(profile.getRole());
			} catch (Exception exception) {
				LOGGER.error("Error occurred while adding user("+profile.getAfId()+") to security configuration ! " + exception.getMessage());
			}
		});
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**", "/cii/**").hasAnyRole("SUPERADMIN", "ADMIN", "USER").and().authorizeRequests()
			.antMatchers("/login**", "/access/**").permitAll().and().formLogin().loginPage("/login")
			.loginProcessingUrl("/signin")
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler((request, response, auth) -> {
				String contextPath = request.getContextPath();
				response.sendRedirect(contextPath + "/auth/success");
			})
			.failureHandler((request, response, exp) -> {
				String errMsg = "";
				if (exp.getClass().isAssignableFrom(BadCredentialsException.class))
					errMsg = "Invalid username or password.";
				else
					errMsg = "Unknown error - " + exp.getMessage();
				request.getSession().setAttribute("message", errMsg);
				String contextPath = request.getContextPath();
				response.sendRedirect(contextPath);
			})
			.permitAll()
			.and().logout().logoutUrl("/signout")
			.logoutSuccessHandler((request, response, auth) -> {
				LOGGER.info(auth.getName() + " has logged out.");
				request.getSession().setAttribute("message", "You are logged out successfully.");
				String contextPath = request.getContextPath();
				response.sendRedirect(contextPath + "/admin/success");
			})
			.permitAll()
			.and().csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/images/**", "/css/**", "/js/**");
	}
}