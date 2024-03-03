package me.nadetdev.petowner.service.pet;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import me.nadetdev.petowner.domaine.Owner;
import me.nadetdev.petowner.domaine.Pet;

import java.util.Optional;

public interface PetService {
    Pet save(Pet pet);
    Page<Pet> findAll(Pageable pageable);
    Optional<Pet> findOne(Long id);
    void delete(Long id);
}
