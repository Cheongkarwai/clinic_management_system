package cheong.clinic_management_system.service;

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

import cheong.clinic_management_system.repository.IUserRepository;


@Service
@Transactional
public class UserDetailsManagerImpl implements UserDetailsManager{

	@Autowired
	private IUserRepository userRepository;
	
	private Logger logger = Logger.getLogger(UserDetailsManagerImpl.class.getName());
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<cheong.clinic_management_system.entity.User> userOptional = userRepository.findById(username);
		
		if(userOptional.isPresent()) {
			
			cheong.clinic_management_system.entity.User user = userOptional.get();
			
			logger.info("User details : "+user.getRole());

			return  User.withUsername(user.getUsername())
					.password(user.getPassword())
					.authorities(user.getRole())
					.accountLocked(user.getEnabled())
					.build();
		}
		throw new UsernameNotFoundException("User not found");
	}

	@Override
	public void createUser(UserDetails user) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(user.getAuthorities());
		userRepository.save(new cheong.clinic_management_system.entity.User(user.getUsername(),
				user.getPassword(),
				grantedAuthorities.get(0).toString(),
				user.isEnabled()));
	}

	@Override
	public void updateUser(UserDetails user) {
		List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) user.getAuthorities();
		userRepository.save(new cheong.clinic_management_system.entity.User(user.getUsername(),
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
