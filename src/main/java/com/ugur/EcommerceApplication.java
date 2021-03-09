package com.ugur;

import com.ugur.filter.AuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthenticationFilter> filterRegistrationBean(){
		FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
		AuthenticationFilter authenticationFilter = new AuthenticationFilter();
		registrationBean.setFilter(authenticationFilter);
		registrationBean.addUrlPatterns("/roles/*");
		registrationBean.addUrlPatterns("/categories/*");
		registrationBean.addUrlPatterns("/products/*");
		registrationBean.addUrlPatterns("/users/delete/*");
		registrationBean.addUrlPatterns("/users/getAllUsers");
		return registrationBean;
	}
}
