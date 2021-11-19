package br.com.fiap.sigmaeats.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.sigmaeats.model.User;
import br.com.fiap.sigmaeats.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findByEmail(username);
		if(user.isEmpty()) throw new UsernameNotFoundException("use not found");
		return user.get();
	}

	public static PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
