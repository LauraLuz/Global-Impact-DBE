package br.com.fiap.sigmaeats.controller.api;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.sigmaeats.model.Donation;
import br.com.fiap.sigmaeats.repository.DonationRepository;

@RestController
@RequestMapping("/api/donation")
public class ApiDonationController {
	
	@Autowired
	private DonationRepository repository;
	
	@GetMapping
	public Page<Donation> index(
			@RequestParam(required = false) String userName,
			@PageableDefault Pageable pageable
			) {
		
		if (userName == null) 
			return repository.findAll(pageable);
		
		return repository
				.findByUserNameLike("%" + userName + "%" , pageable);
	}
	
	@PostMapping
	public ResponseEntity<Donation> create(
			@RequestBody Donation donation,
			UriComponentsBuilder uriBuilder
			) {
		repository.save(donation);
		
		URI uri = uriBuilder
				.path("api/donation/{id}")
				.buildAndExpand(donation.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(donation);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Donation>  get(@PathVariable Long id) {
		Optional<Donation> donation = repository.findById(id);
		if (donation.isPresent()) 
			return ResponseEntity.ok( donation.get() ) ;
		
		return ResponseEntity.notFound().build();
		
		
	}
	
	
	
	
	

}
