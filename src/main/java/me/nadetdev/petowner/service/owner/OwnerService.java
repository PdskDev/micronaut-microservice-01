package me.nadetdev.petowner.service.owner;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import me.nadetdev.petowner.domaine.Owner;

import java.util.Optional;

public interface OwnerService {
    Owner save(Owner owner);
    Page<Owner> findAll(Pageable pageable);
    Optional<Owner> findOne(Long id);
    void delete(Long id);

}
