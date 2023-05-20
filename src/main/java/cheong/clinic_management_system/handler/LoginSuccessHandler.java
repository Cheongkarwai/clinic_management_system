package cheong.clinic_management_system.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private String contextRoot = "/clinic_management_system/";

	private Logger logger = Logger.getLogger(LoginSuccessHandler.class.getName());
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		logger.info("Authenticated !" + authentication.getAuthorities());
		
		logger.info(contextRoot);
		
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			response.sendRedirect(contextRoot+"admin");
		}
		
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			response.sendRedirect(contextRoot);
		}
	}
}
