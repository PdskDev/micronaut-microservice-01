package me.nadetdev.petowner.service.pet;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;
import me.nadetdev.petowner.domaine.Pet;
import me.nadetdev.petowner.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.Optional;

@Singleton
@Transactional
public class PetServiceImpl implements PetService {
    private final Logger log = LoggerFactory.getLogger(PetServiceImpl.class);
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet save(Pet pet) {
        log.debug("Request to save Pet: {}", pet);
        return petRepository.save(pet);
    }

    @Override
    public Page<Pet> findAll(Pageable pageable) {
        log.debug("Request to get all pets");
        return petRepository.findAll(pageable);
    }

    @Override
    public Optional<Pet> findOne(Long id) {
        log.debug("Request to get Pet : {}", id);
        return petRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Owner : {}", id);
        petRepository.deleteById(id);
    }
}
