package com.l2jwalker.dao.support;

import com.l2jwalker.entity.Identifiable;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<Entity extends Identifiable<PK>, PK extends Serializable> {

    Entity getById(PK pk);

    Entity get(Entity entity);

    void refresh(Entity entity);

    void save(Entity entity);

    Entity merge(Entity entity);

    void save(Iterable<Entity> entities);

    void delete(Entity entity);

    void delete(Iterable<Entity> entities);

    void delete(PK pk);

    List<Entity> find(Entity entity, SearchTemplate searchTemplate);

    int findCount(Entity entity, SearchTemplate searchTemplate);

    void quickInsert(Iterable<Entity> entities);

}