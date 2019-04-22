package com.stackroute.musemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.musemanager.filter.JwtFilter;



@SpringBootApplication
public class MusemanagerApplication {


	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registerBean = new FilterRegistrationBean();
		registerBean.setFilter(new JwtFilter());
		registerBean.addUrlPatterns("/api/jobservice*");
		return registerBean;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MusemanagerApplication.class, args);
	}

}
