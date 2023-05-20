package cheong.clinic_management_system.service;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

public class AuthenticationProviderService implements AuthenticationProvider{
	
	@Autowired
	private UserDetailsManager userDetailsManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Logger logger = Logger.getLogger(AuthenticationProviderService.class.getName());

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		UserDetails userDetails = userDetailsManager.loadUserByUsername(authentication.getName());
		
		boolean hasRoleAdmin = userDetails.getAuthorities()
				.stream()
				.anyMatch(e->e.getAuthority().equals("ROLE_ADMIN"));
		
		logger.info("Role admin is exists "+hasRoleAdmin);
		
		userDetails.getAuthorities().forEach(e->logger.info(e.getAuthority()));
		
		if(passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
			return new UsernamePasswordAuthenticationToken(authentication.getName(), 
					passwordEncoder.encode(authentication.getCredentials().toString()),
					userDetails.getAuthorities());
		}
		throw new BadCredentialsException("Invalid username and password");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}

