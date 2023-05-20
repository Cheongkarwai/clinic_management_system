package com.cheong.clinic.config;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

import com.cheong.clinic.auth.handler.LoginSuccessHandler;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "com.cheong.clinic.*" })
@EnableConfigurationProperties(JwtTokenProperties.class)
public class WebSecurityConfig {

//	@Value("${jwt.secret_key.access_token}")
//	private String accessTokenSecretKey;
//	
//	@Value("${jwt.secret_key.refresh_token}")
//	private String refreshTokenSecretKey;
	
	private JwtTokenProperties jwtTokenProperties;

	@PostConstruct
	public void init() {
		System.out.println(jwtTokenProperties.getIssuer());
	}

	@Autowired
	private UserDetailsService userService;
	
private String secretKey1 = "234234sdasdas34wddsdadasdadasdasda3423423423";
	
	private String secretKey2 = "234234sdasdas34wddsdadasdadasdaq4323423424234";

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//		http.csrf().disable().authorizeRequests().antMatchers("/admin/**", "/admin", "/patient")
//		.hasAuthority("ROLE_ADMIN").antMatchers("/user/**").hasAuthority("ROLE_USER").and().formLogin(login -> {
//			try {
//				login.loginPage("/auth/login/index").loginProcessingUrl("/login")
//						.successHandler(new LoginSuccessHandler()).and()
//						.logout(logout -> logout.logoutSuccessUrl("/"));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});

		http.csrf().disable().cors().disable().authorizeHttpRequests(req -> req.anyRequest().permitAll())
				.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(
						exception -> exception.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
								.accessDeniedHandler(new BearerTokenAccessDeniedHandler()));

		return http.build();
	}

//	@Bean
//	AuthenticationProvider AuthenticationProviderService() {
//		return new AuthenticationProviderService();
//	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// encoder for refresh token
	@Bean
	JwtEncoder refreshTokenEncoder() {
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey1.getBytes(),
				MacAlgorithm.HS256.getName());
		JWKSource<SecurityContext> jwks = new ImmutableSecret<>(secretKeySpec);
		return new NimbusJwtEncoder(jwks);
	}

	@Bean
	JwtDecoder refreshTokenDecoder() {
		return NimbusJwtDecoder
				.withSecretKey(new SecretKeySpec(secretKey1.getBytes(),
						MacAlgorithm.HS256.getName()))
				.build();
	}

	// encoder for access token
	@Bean
	@Primary
	JwtEncoder accessTokenEncoder() {
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey2.getBytes(),
				MacAlgorithm.HS256.getName());
		JWKSource<SecurityContext> jwks = new ImmutableSecret<>(secretKeySpec);
		return new NimbusJwtEncoder(jwks);
	}

	@Bean
	@Primary
	JwtDecoder accessTokenDecoder() {
		return NimbusJwtDecoder.withSecretKey(new SecretKeySpec(secretKey2.getBytes(),
				MacAlgorithm.HS256.getName())).build();
	}

	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userService);
		return provider;
	}

}
