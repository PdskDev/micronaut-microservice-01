package me.nadetdev.petowner.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import me.nadetdev.petowner.domaine.PetType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public abstract class PetTypeRepository implements JpaRepository<PetType, Long> {

    @PersistenceContext
    private final EntityManager entityManager;

    public PetTypeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public PetType mergeAndSave(PetType type){
        type = entityManager.merge(type);
        return save(type);
    }
}
