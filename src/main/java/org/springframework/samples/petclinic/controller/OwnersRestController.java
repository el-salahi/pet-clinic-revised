package org.springframework.samples.petclinic.controller;


import org.springframework.samples.petclinic.model.owner.Owner;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1")
public class OwnersRestController {

    private final OwnerRepository owners;

    public OwnersRestController(OwnerRepository owners) {
        this.owners = owners;
    }

    @GetMapping("/owners")
    public List<Owner> listOwners() {
        return owners.findAll();
    }

    @GetMapping("/owners/{ownerId}")
    public Owner getOwner(@PathVariable int ownerId) throws Exception {
        var owner = owners.findById(ownerId);
        if (owner.isEmpty()) {
            throw new Exception("owner not found");
        }
        return owner.get();
    }

    @PostMapping(path = "/owners", consumes = "application/json")
    public Owner createOwner(@RequestBody Owner owner) {
        return owners.save(owner);
    }
}
