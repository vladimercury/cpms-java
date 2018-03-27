package com.mercury.dao.impl;

import com.mercury.LoggerWrapper;
import com.mercury.dao.UserDAO;
import com.mercury.dao.util.HibernateUtil;
import com.mercury.dao.util.PasswordHash;
import com.mercury.exception.DataAccessException;
import com.mercury.model.EmployeeInfo;
import com.mercury.model.User;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@SuppressWarnings("unchecked")
public class UserDaoImpl implements UserDAO {
    private static final LoggerWrapper LOG = LoggerWrapper.getLogger(UserDaoImpl.class);

    @Override
    public User authenticate(String login, String password) throws DataAccessException,
            InvalidKeySpecException, NoSuchAlgorithmException {
        User user = getByLogin(login);
        if (user != null && PasswordHash.validatePassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User getByLogin(String login) throws DataAccessException {
        User user = null;
        try {
            HibernateUtil.beginTransaction();
            user = (User) HibernateUtil.getSession()
                    .createCriteria(User.class)
                    .add(Restrictions.eq("login", login))
                    .uniqueResult();
            HibernateUtil.commit();
        } catch (HibernateException e) {
            LOG.error(e);
            HibernateUtil.rollback();
            throw new DataAccessException(e.getMessage());
        } finally {
            HibernateUtil.closeSession();
        }
        return user;
    }

    @Override
    public List<User> getAll() throws DataAccessException {
        return (List<User>) HibernateUtil.doGetAll(User.class);
    }

    @Override
    public User update(User updateEntity) throws DataAccessException {
        return (User) HibernateUtil.doUpdate(updateEntity);
    }

    @Override
    public void delete(User entity) throws DataAccessException {
        HibernateUtil.doDelete(entity);
    }

    @Override
    public void create(User entity) throws DataAccessException {
        HibernateUtil.doCreate(entity);
    }
}
