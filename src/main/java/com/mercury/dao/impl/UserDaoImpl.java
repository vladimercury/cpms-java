package com.mercury.dao.impl;

import com.mercury.LoggerWrapper;
import com.mercury.dao.UserDAO;
import com.mercury.dao.util.HibernateUtil;
import com.mercury.dao.util.PasswordHash;
import com.mercury.exception.DataAccessException;
import com.mercury.model.User;
import com.mercury.model.expand.UserExpansion;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@SuppressWarnings("unchecked")
public class UserDaoImpl implements UserDAO {
    private static final LoggerWrapper LOG = LoggerWrapper.getLogger(UserDaoImpl.class);

    private void doExpand(User user, UserExpansion expansion) throws DataAccessException {
        switch (expansion) {
            case RECEIVED_MESSAGES:
                Hibernate.initialize(user.getReceivedMessages());
                break;
            case EMPLOYEE_INFO:
                Hibernate.initialize(user.getInfo());
                break;
        }
    }

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
    public User getAndExpand(int userId, UserExpansion... expand) throws DataAccessException {
        User user;
        try {
            HibernateUtil.beginTransaction();
            user = (User) HibernateUtil.getSession()
                    .createCriteria(User.class)
                    .add(Restrictions.eq("id", userId))
                    .uniqueResult();
            for (UserExpansion expansion : expand) {
                doExpand(user, expansion);
            }
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
