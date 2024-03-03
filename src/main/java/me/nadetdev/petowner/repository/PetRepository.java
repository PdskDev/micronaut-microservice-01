package me.nadetdev.petowner.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import me.nadetdev.petowner.domaine.Address;
import me.nadetdev.petowner.domaine.Pet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public abstract class PetRepository implements JpaRepository<Pet, Long> {

    @PersistenceContext
    private final EntityManager entityManager;

    public PetRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Pet mergeAndSave(Pet pet){
        pet = entityManager.merge(pet);
        return save(pet);
    }
}
