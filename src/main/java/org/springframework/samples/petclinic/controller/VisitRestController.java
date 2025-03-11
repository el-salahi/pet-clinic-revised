package org.springframework.samples.petclinic.controller;

import org.springframework.samples.petclinic.model.owner.Visit;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class VisitRestController {

    private final OwnerRepository visits;

    public VisitRestController(OwnerRepository visits) {
        this.visits = visits;
    }

    @GetMapping("/owner/{ownerId}/pets/{petId}/visits")
    public Collection<Visit> getPetVisits(@PathVariable int ownerId, @PathVariable int petId) {
        return visits.findById(ownerId).get().getPet(petId).getVisits();
    }
}
