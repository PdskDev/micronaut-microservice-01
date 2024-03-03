package me.nadetdev.petowner.service.visit;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;
import me.nadetdev.petowner.domaine.Visit;
import me.nadetdev.petowner.repository.VisitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.Optional;

@Singleton
@Transactional
public class VisitServiceImpl implements VisitService {
    private final Logger log = LoggerFactory.getLogger(VisitServiceImpl.class);
    private final VisitRepository visitRepository;
    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Visit save(Visit visit) {
        log.debug("Request to save a new visit : {}", visit);
        return visitRepository.save(visit);
    }

    @Override
    public Page<Visit> findAll(Pageable pageable) {
        log.debug("Request to get all visits");
        return visitRepository.findAll(pageable);
    }

    @Override
    public Optional<Visit> findOne(Long id) {
        log.debug("Request to get one visit: {}", id);
        return visitRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete one visit: {}", id);
        visitRepository.deleteById(id);
    }
}
