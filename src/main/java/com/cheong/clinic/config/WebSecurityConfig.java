package com.cheong.clinic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cheong.clinic.auth.handler.LoginSuccessHandler;
import com.cheong.clinic.auth.service.AuthenticationProviderService;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages= {"com.cheong.clinic.*"})
public class WebSecurityConfig{
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/admin/**","/admin","/patient").hasAuthority("ROLE_ADMIN")
				.antMatchers("/user/**").hasAuthority("ROLE_USER")
				.and()
				.formLogin()
				.loginPage("/auth/login/index")
				.loginProcessingUrl("/login")
				.successHandler(new LoginSuccessHandler())
				.and()
				.logout()
				.logoutSuccessUrl("/");

		
		return http.build();
	}

	@Bean
	public AuthenticationProvider AuthenticationProviderService() {
		return new AuthenticationProviderService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	


}
