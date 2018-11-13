//package com.example.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.view.JstlView;
//import org.springframework.web.servlet.view.UrlBasedViewResolver;
//
//@Configuration
//@ComponentScan("com.example.demo") // @Controller
//@EnableWebMvc // @RequestMapping
//public class Config {
//	@Bean
//	public UrlBasedViewResolver viewResolver() {
//		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
//		viewResolver.setPrefix("/WEB-INF/views/");
//		viewResolver.setSuffix(".jsp");
//		viewResolver.setViewClass(JstlView.class);
//		return viewResolver;
//	}
//
//
//}
