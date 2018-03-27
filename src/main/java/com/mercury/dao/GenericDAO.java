package com.mercury.dao;

import com.mercury.exception.DataAccessException;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<Type, ID extends Serializable> {
    List<Type> getAll() throws DataAccessException;

    Type update(Type updateEntity) throws DataAccessException;

    void delete(Type entity) throws DataAccessException;

    void create(Type entity) throws DataAccessException;
}
