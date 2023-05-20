package com.cheong.clinic.auth.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheong.clinic.auth.repository.UserRepository;
import com.cheong.clinic.config.JwtTokenProperties;

@Service
@Transactional
@EnableConfigurationProperties(JwtTokenProperties.class)
public class TokenService {

	private final JwtEncoder accessTokenEncoder;
	
	private final JwtEncoder refreshTokenEncoder;

	private final UserRepository userRepository;
	
	
	public TokenService(JwtEncoder accessTokenEncoder,@Qualifier("refreshTokenEncoder") JwtEncoder refreshTokenEncoder,UserRepository userRepository) {
		this.accessTokenEncoder = accessTokenEncoder;
		this.refreshTokenEncoder = refreshTokenEncoder;
		this.userRepository = userRepository;
	}

	public String generateAccessToken(org.springframework.security.core.userdetails.User user) {

		com.cheong.clinic.auth.entity.User userDb = userRepository.findById(user.getUsername())
				.orElseThrow(() -> new NoSuchElementException("No User found"));

		String scope = userDb.getRole();

		JwsHeader jwsHeader = JwsHeader.with(()->MacAlgorithm.HS256.getName()).build();
		JwtClaimsSet claims = JwtClaimsSet.builder().subject(userDb.getUsername())
				.expiresAt(Instant.now().plus(20, ChronoUnit.MINUTES)).claim("scope", scope).build();

		return accessTokenEncoder.encode(JwtEncoderParameters.from(jwsHeader,claims)).getTokenValue();
	}
	
	public String generateRefreshToken(org.springframework.security.core.userdetails.User user) {
		com.cheong.clinic.auth.entity.User userDb = userRepository.findById(user.getUsername())
				.orElseThrow(() -> new NoSuchElementException("No User found"));

		String scope = userDb.getRole();

		JwsHeader jwsHeader = JwsHeader.with(()->MacAlgorithm.HS256.getName()).build();
		JwtClaimsSet claims = JwtClaimsSet.builder().subject(userDb.getUsername())
				
				.expiresAt(Instant.now().plus(6, ChronoUnit.HOURS)).claim("scope", scope).build();

		return refreshTokenEncoder.encode(JwtEncoderParameters.from(jwsHeader,claims)).getTokenValue();
	}
}