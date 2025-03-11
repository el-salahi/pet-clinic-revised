package org.springframework.samples.petclinic.controller;

import org.springframework.samples.petclinic.model.vet.Vet;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/v1")
public class VetRestController {

	private final VetRepository vets;

	public VetRestController(VetRepository vets) {
		this.vets = vets;
	}

	@GetMapping("/vets")
	public Collection<Vet> listVets() {
		return vets.findAll();
	}
}
