package me.nadetdev.petowner.service.visit;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import me.nadetdev.petowner.domaine.Visit;

import java.util.Optional;

public interface VisitService {
    Visit save(Visit vist);
    Page<Visit> findAll(Pageable pageable);
    Optional<Visit> findOne(Long id);
    void delete(Long id);

}
