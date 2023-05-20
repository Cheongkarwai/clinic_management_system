package com.cheong.clinic.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.cheong.clinic.auth.repository.UserRepository;


@Service
@Transactional
public class UserService implements UserDetailsManager{

	@Autowired
	private UserRepository userRepository;
	
	private Logger logger = Logger.getLogger(UserService.class.getName());
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<com.cheong.clinic.auth.entity.User> userOptional = userRepository.findById(username);
		
		if(userOptional.isPresent()) {
			
			com.cheong.clinic.auth.entity.User user = userOptional.get();
			
			logger.info("User details : "+user.getRole());

			return  User.withUsername(user.getUsername())
					.password(user.getPassword())
					.authorities(user.getRole())
					.accountLocked(false)
					.disabled(false)
					.credentialsExpired(false)
					.accountExpired(false)
					.build();
		}
		throw new UsernameNotFoundException("User not found");
	}

	@Override
	public void createUser(UserDetails user) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(user.getAuthorities());
		userRepository.save(new com.cheong.clinic.auth.entity.User(user.getUsername(),
				user.getPassword(),
				grantedAuthorities.get(0).toString(),
				user.isEnabled()));
	}

	@Override
	public void updateUser(UserDetails user) {
		List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) user.getAuthorities();
		userRepository.save(new com.cheong.clinic.auth.entity.User(user.getUsername(),
				user.getPassword(),
				grantedAuthorities.get(0).toString(),
				user.isEnabled()));
	}

	@Override
	public void deleteUser(String username) {
		userRepository.deleteById(username);
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
	
	}

	@Override
	public boolean userExists(String username) {
		
		return userRepository.findById(username).isPresent();
	}



}
