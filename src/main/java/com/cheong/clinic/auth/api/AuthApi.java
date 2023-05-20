package com.cheong.clinic.auth.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheong.clinic.auth.model.LoginRequest;
import com.cheong.clinic.auth.model.Token;
import com.cheong.clinic.auth.service.TokenService;
import com.cheong.clinic.config.JwtTokenProperties;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthApi {

	private JwtTokenProperties jwtTokenProperties;
	
	private final TokenService tokenService;

	private final DaoAuthenticationProvider authenticationProvider;

	private final JwtDecoder jwtDecoder;

	public AuthApi(TokenService tokenService, DaoAuthenticationProvider authenticationProvider, JwtDecoder jwtDecoder) {
		this.tokenService = tokenService;
		this.authenticationProvider = authenticationProvider;
		this.jwtDecoder = jwtDecoder;
	}

	@PostMapping
	public HttpEntity<Token> login(@RequestBody LoginRequest loginRequest) {
		
		System.out.println(jwtTokenProperties.getIssuer());

		Authentication authentication = authenticationProvider.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String accessToken = tokenService.generateAccessToken(
				(org.springframework.security.core.userdetails.User) authentication.getPrincipal());
		String refreshToken = tokenService.generateRefreshToken(
				(org.springframework.security.core.userdetails.User) authentication.getPrincipal());

		return ResponseEntity.ok(Token.builder().accessToken(accessToken).refreshToken(refreshToken).build());
	}

	@GetMapping
	public Object hi(@AuthenticationPrincipal Jwt jwt) {

		Jwt jwtToken = jwtDecoder.decode(jwt.getTokenValue());
		return jwtToken.getSubject();
	}
}
