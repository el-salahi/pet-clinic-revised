package org.springframework.samples.petclinic.model.request;

import org.springframework.samples.petclinic.model.owner.PetType;

import java.time.LocalDate;

public class PetRequestBody {


	private String name;
	private LocalDate birthDate;
	private PetType type;
}
