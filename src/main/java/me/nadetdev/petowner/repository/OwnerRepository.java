package me.nadetdev.petowner.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import me.nadetdev.petowner.domaine.Owner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public abstract class OwnerRepository implements JpaRepository<Owner, Long> {

    @PersistenceContext
    private final EntityManager entityManager;

    public OwnerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Owner mergeAndSave(Owner owner){
        owner = entityManager.merge(owner);
        return save(owner);
    }
}
