package org.springframework.samples.petclinic.controller;

import org.springframework.samples.petclinic.model.owner.Owner;
import org.springframework.samples.petclinic.model.owner.Pet;
import org.springframework.samples.petclinic.model.owner.PetType;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class PetRestController {

	private final OwnerRepository owners;

	public PetRestController(OwnerRepository owners) {
		this.owners = owners;
	}

	@GetMapping("/owners/{ownerId}/pets")
	public List<Pet> listOwnerPets(@PathVariable int ownerId) throws Exception {
		var owner = owners.findById(ownerId);
		if (owner.isEmpty()) {
			throw new Exception("owner not found");
		}
		return owner.get().getPets();
	}

	@GetMapping("/owners/pet-types")
	public List<PetType> listPetTypes() {
		return owners.findPetTypes();
	}

//	@GetMapping("/owners/{ownerId}/pets/{petId}")
//	public Pet getPetById(@PathVariable int ownerId, @PathVariable int petId) throws Exception {
//		var owner = this.owners.findById(ownerId);
//		if (owner.isEmpty()) {
//			throw new Exception("owner not found");
//		}
//		return owner.get().getPet(petId);
//	}

	@ModelAttribute("pet")
	@GetMapping("/owners/{ownerId}/pets/{petId}")
	public Pet getPetById(@PathVariable("ownerId") int ownerId,
						  @PathVariable(name = "petId", required = false) Integer petId) {

		Optional<Owner> optionalOwner = this.owners.findById(ownerId);
		Owner owner = optionalOwner.orElseThrow(() -> new IllegalArgumentException(
			"Owner not found with id: " + ownerId + ". Please ensure the ID is correct "));

		Optional<Pet> optionalPet = Optional.ofNullable(owner.getPet(petId));
		Pet pet = optionalPet.orElseThrow(() -> new IllegalArgumentException(
			"Pet not found with id: " + petId + ". Please ensure the ID is correct "));

		return pet;
	}


	@GetMapping("/owners/{ownerId}/pets/{name}")
	public Pet getPetByName(@PathVariable int ownerId, @PathVariable String name) {
		var pet = owners.findById(ownerId).get().getPet(name);
		return pet;
	}

}
