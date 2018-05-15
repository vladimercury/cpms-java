package com.mercury.dao;

import com.mercury.exception.DataAccessException;
import com.mercury.model.EmployeePosition;

public interface PositionDAO extends GenericDAO<EmployeePosition,Integer> {
    EmployeePosition getPosition(int id) throws DataAccessException;
}
