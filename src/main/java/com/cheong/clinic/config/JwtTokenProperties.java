package com.cheong.clinic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@ConfigurationProperties(prefix = "jwt")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtTokenProperties {

	private String accessTokenSecretKey;
	
	private String refreshTokenSecretKey;
	
	private String issuer;
	
	private String issueAt;
}
