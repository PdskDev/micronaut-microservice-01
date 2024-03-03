package me.nadetdev.petowner.service.owner;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;
import me.nadetdev.petowner.domaine.Owner;
import me.nadetdev.petowner.repository.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.Optional;

@Singleton
@Transactional
public class OwnerServiceImpl implements OwnerService {
    private final Logger log = LoggerFactory.getLogger(OwnerServiceImpl.class);
    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner save(Owner owner) {
        log.debug("Request to save Owner: {}", owner);
        return ownerRepository.save(owner);
    }

    @Override
    public Page<Owner> findAll(Pageable pageable) {
        log.debug("Request to get all owners");
        return ownerRepository.findAll(pageable);
    }

    @Override
    public Optional<Owner> findOne(Long id) {
        log.debug("Request to get Owner : {}", id);
        return ownerRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Owner : {}", id);
        ownerRepository.deleteById(id);
    }
}
