package com.mercury.dao;

import com.mercury.exception.DataAccessException;
import com.mercury.model.EmployeeInfo;
import com.mercury.model.EmployeePosition;
import com.mercury.model.User;
import com.mercury.model.expand.UserExpansion;
import org.hibernate.criterion.Criterion;

import javax.xml.crypto.Data;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface UserDAO extends GenericDAO<User, Integer> {
    User authenticate(String login, String password) throws DataAccessException, InvalidKeySpecException, NoSuchAlgorithmException;

    User get(int userId) throws DataAccessException;

    User getEntityWithRestrictions(Criterion... criteria) throws DataAccessException;

    User getByLogin(String login) throws DataAccessException;

    User getAndExpand(int userId, UserExpansion... expand) throws DataAccessException;

    EmployeePosition getPosition(int positionId) throws DataAccessException;

    void create(User user, EmployeeInfo info) throws DataAccessException;
}
