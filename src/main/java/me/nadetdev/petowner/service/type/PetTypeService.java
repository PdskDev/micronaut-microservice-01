package me.nadetdev.petowner.service.type;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import me.nadetdev.petowner.domaine.Pet;
import me.nadetdev.petowner.domaine.PetType;

import java.util.Optional;

public interface PetTypeService {
    PetType save(PetType petType);
    Page<PetType> findAll(Pageable pageable);
    Optional<PetType> findOne(Long id);
    void delete(Long id);
}
