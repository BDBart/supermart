package org.example.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

public abstract class Dao<E> {

    @PersistenceContext
    protected EntityManager entityManager;

    public Collection<E> getAll() {
        return entityManager.createNamedQuery(E().getSimpleName() + "findAll", E()).getResultList();
    }

    public E add(E entity){
        entityManager.persist(entity);
        return entity;
    }

    @SuppressWarnings("unchecked")
    private Class<E> E(){
        ParameterizedType thisDaoClass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<E>) thisDaoClass.getActualTypeArguments()[0];
    }
}
