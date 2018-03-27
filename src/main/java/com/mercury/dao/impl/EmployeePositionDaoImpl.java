package com.mercury.dao.impl;

import com.mercury.LoggerWrapper;
import com.mercury.dao.EmployeePositionDAO;
import com.mercury.dao.util.HibernateUtil;
import com.mercury.exception.DataAccessException;
import com.mercury.model.EmployeeInfo;
import com.mercury.model.EmployeePosition;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import java.util.List;

@SuppressWarnings("unchecked")
public class EmployeePositionDaoImpl implements EmployeePositionDAO {
    private static final LoggerWrapper LOG = LoggerWrapper.getLogger(EmployeePositionDaoImpl.class);

    @Override
    public List<EmployeePosition> getAll() throws DataAccessException {
        return (List<EmployeePosition>) HibernateUtil.doGetAll(EmployeePosition.class);
    }

    @Override
    public EmployeePosition update(EmployeePosition updateEntity) throws DataAccessException {
        return (EmployeePosition) HibernateUtil.doUpdate(updateEntity);
    }

    @Override
    public void delete(EmployeePosition entity) throws DataAccessException {
        HibernateUtil.doDelete(entity);
    }

    @Override
    public void create(EmployeePosition entity) throws DataAccessException {
        HibernateUtil.doCreate(entity);
    }
}
