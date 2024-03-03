package me.nadetdev.petowner.service.type;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;
import me.nadetdev.petowner.domaine.PetType;
import me.nadetdev.petowner.repository.PetTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.Optional;

@Singleton
@Transactional
public class PetTypeServiceImpl implements PetTypeService {
    private final Logger log = LoggerFactory.getLogger(PetTypeServiceImpl.class);
    private final PetTypeRepository petTypeRepository;

    public PetTypeServiceImpl(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public PetType save(PetType petType) {
        log.debug("Request to save a type of Pet: {}", petType);
        return petTypeRepository.save(petType);
    }

    @Override
    public Page<PetType> findAll(Pageable pageable) {
        log.debug("Request to get all types of pets");
        return petTypeRepository.findAll(pageable);
    }

    @Override
    public Optional<PetType> findOne(Long id) {
        log.debug("Request to get Pet type : {}", id);
        return petTypeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Pet Type : {}", id);
        petTypeRepository.deleteById(id);
    }
}
