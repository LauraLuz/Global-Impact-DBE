package br.com.fiap.sigmaeats.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Donation {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 10, message = "A descrição deve ter pelo penos 10 caracteres")
	private String description;
	
//	@NotBlank(message = "a doacao deve ter um tipo, perecivel ou nao perecivel")
	@Column(name = "donation_type")
	@Enumerated(EnumType.STRING)
	private DonationType type;
	
	@Min(value= 1, message = "A quantidade mínima é 1")
	private int quantidade;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Calendar validade;
	
//	descricao local de retirada e horario
	@Column(name = "descricao_retirada")
	private String descricaoRetirada;
	
	@ManyToOne
	private User user;
	
}
