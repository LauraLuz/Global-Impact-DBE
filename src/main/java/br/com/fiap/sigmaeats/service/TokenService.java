package br.com.fiap.sigmaeats.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.fiap.sigmaeats.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class TokenService {

	@Value("${sigmaeats.jwt.duration}")
	private long duration;
	
	@Value("${sigmaeats.jwt.secret}")
	private String secret;

	public String getToken(Authentication authenticate) {
		User user = (User) authenticate.getPrincipal();
		
		Date today = new Date();
		

		Date expirationDate = new Date(today.getTime() + duration);
		
		System.out.println(secret);
		
		return Jwts.builder()
					.setIssuer("SigmaEatsAPI")
					.setSubject(user.getId().toString())
					.setIssuedAt(today)
					.setExpiration(expirationDate)
					.signWith(SignatureAlgorithm.HS256, secret)
					.compact();
					
	}

	public boolean isValid(String token) {

			try {
				Jwts
					.parser()
					.setSigningKey(this.secret)
					.parseClaimsJws(token);
				return true;
			} catch (ExpiredJwtException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedJwtException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedJwtException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;

	}

	public Long getUserId(String token) {
		Claims body = Jwts
			.parser()
			.setSigningKey(this.secret)
			.parseClaimsJws(token)
			.getBody();
		String id = body.getSubject();
		
		return Long.valueOf(id);
		
	}

}
