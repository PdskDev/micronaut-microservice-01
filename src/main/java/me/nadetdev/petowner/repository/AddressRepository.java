package me.nadetdev.petowner.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import me.nadetdev.petowner.domaine.Address;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public abstract class AddressRepository implements JpaRepository<Address, Long> {

    @PersistenceContext
    private final EntityManager entityManager;

    public AddressRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Address mergeAndSave(Address address){
        address = entityManager.merge(address);
        return save(address);
    }
}
