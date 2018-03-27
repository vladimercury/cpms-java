package com.mercury.dao;

import com.mercury.exception.DataAccessException;
import com.mercury.model.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface UserDAO extends GenericDAO<User, Integer> {
    User authenticate(String login, String password) throws DataAccessException, InvalidKeySpecException, NoSuchAlgorithmException;

    User getByLogin(String login) throws DataAccessException;
}
