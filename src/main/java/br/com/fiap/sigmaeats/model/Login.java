package br.com.fiap.sigmaeats.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Login {
	
	@NotBlank
	@Email
	private String username;
	
	@NotBlank
	private String password;

}
