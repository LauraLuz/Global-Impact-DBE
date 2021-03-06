package br.com.fiap.sigmaeats.controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.sigmaeats.model.Login;
import br.com.fiap.sigmaeats.service.TokenService;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/api/auth")
	public ResponseEntity<String> auth(@RequestBody @Valid Login login ) {
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
		
		try {
			Authentication authenticate = authManager.authenticate(authenticationToken);
			String token = tokenService.getToken(authenticate);
			return ResponseEntity.ok(token);
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}

}
