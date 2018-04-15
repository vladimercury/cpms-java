package com.mercury.dao.impl;

import com.mercury.dao.MessageDAO;
import com.mercury.dao.util.HibernateUtil;
import com.mercury.exception.DataAccessException;
import com.mercury.model.Message;
import com.mercury.model.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import java.util.List;

@SuppressWarnings("unchecked")
public class MessageDaoImpl implements MessageDAO {
    @Override
    public List<Message> getAll() throws DataAccessException {
        return (List<Message>) HibernateUtil.doGetAll(Message.class);
    }

    @Override
    public Message update(Message updateEntity) throws DataAccessException {
        return (Message) HibernateUtil.doUpdate(updateEntity);
    }

    @Override
    public void delete(Message entity) throws DataAccessException {
        HibernateUtil.doDelete(entity);
    }

    @Override
    public void create(Message entity) throws DataAccessException {
        HibernateUtil.doCreate(entity);
    }

    @Override
    public List<Message> getReceivedMessagesForUser(User user) throws DataAccessException {
        List<Message> resultList = null;
        try {
            HibernateUtil.beginTransaction();
            resultList = HibernateUtil.getSession()
                    .createCriteria(Message.class)
                    .add(Restrictions.eq("TargetUserId", user))
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .list();
            HibernateUtil.commit();
        } catch (HibernateException e) {
            HibernateUtil.rollback();
            throw new DataAccessException(e.getMessage());
        } finally {
            HibernateUtil.closeSession();
        }
        return resultList;
    }
}
