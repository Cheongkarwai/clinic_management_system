package cheong.clinic_management_system.config;

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

import cheong.clinic_management_system.handler.LoginSuccessHandler;
import cheong.clinic_management_system.service.AuthenticationProviderService;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages= {"cheong.clinic_management_system.service"})
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
