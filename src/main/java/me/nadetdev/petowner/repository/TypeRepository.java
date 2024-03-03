package me.nadetdev.petowner.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import me.nadetdev.petowner.domaine.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public abstract class TypeRepository implements JpaRepository<Type, Long> {

    @PersistenceContext
    private final EntityManager entityManager;

    public TypeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Type mergeAndSave(Type type){
        type = entityManager.merge(type);
        return save(type);
    }
}
