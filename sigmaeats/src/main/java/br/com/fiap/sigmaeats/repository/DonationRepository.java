package br.com.fiap.sigmaeats.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.sigmaeats.model.Donation;
import br.com.fiap.sigmaeats.model.User;

public interface DonationRepository extends JpaRepository<Donation, Long> {
	
	Page<Donation> findByUserNameLike(String userName, Pageable pageable);

}
