package br.com.fiap.sigmaeats.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.sigmaeats.exception.NotAllowedException;
import br.com.fiap.sigmaeats.exception.DonationNotFoundException;
import br.com.fiap.sigmaeats.model.Donation;
import br.com.fiap.sigmaeats.model.User;
import br.com.fiap.sigmaeats.repository.DonationRepository;

@Controller
@RequestMapping("/donation")
public class DonationController {
	
	@Autowired
	private DonationRepository repository;
	
	@Autowired
	private MessageSource messages;
	
	@GetMapping
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("donations");
		List<Donation> donations = repository.findAll();
		modelAndView.addObject("donations", donations);
		return modelAndView;
	}
	
	@RequestMapping("new")
	public String create(Donation donation) {
		return "donation-form";
	}
	
	@PostMapping
	public String save(@Valid Donation donation, BindingResult result, RedirectAttributes redirect) {
		if(result.hasErrors()) return "donation-form";
		repository.save(donation);
		redirect.addFlashAttribute("message", messages.getMessage("message.success.newdonation", null, LocaleContextHolder.getLocale()));
		return "redirect:donation";
	}
	
	@GetMapping("/hold/{id}")
	public String hold(@PathVariable Long id, Authentication auth) {
		Optional<Donation> optional = repository.findById(id);
		
		if (optional.isEmpty()) 
			throw new DonationNotFoundException("tarefa não encontrada");
		
		Donation donation = optional.get();
		
		if(donation.getUser() != null) 
			throw new NotAllowedException("tarefa já atribuída");
		
		
		User user = (User) auth.getPrincipal();
		
		donation.setUser(user);
		repository.save(donation);
		
		return "redirect:/donation";
	}
	
	@GetMapping("/release/{id}")
	public String release(@PathVariable Long id, Authentication auth) {
		Optional<Donation> optional = repository.findById(id);
		
		if (optional.isEmpty()) 
			throw new DonationNotFoundException("tarefa não encontrada");
		
		Donation donation = optional.get();
		if (!auth.getPrincipal().equals(donation.getUser()))
			throw new NotAllowedException("tarefa está atribuídaa outra pessoa");
		
		donation.setUser(null);
		
		repository.save(donation);
		
		return "redirect:/donation";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
