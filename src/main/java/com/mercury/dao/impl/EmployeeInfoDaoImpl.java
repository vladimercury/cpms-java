package com.mercury.dao.impl;


import com.mercury.LoggerWrapper;
import com.mercury.dao.EmployeeInfoDAO;
import com.mercury.dao.EmployeePositionDAO;
import com.mercury.dao.util.HibernateUtil;
import com.mercury.exception.DataAccessException;
import com.mercury.model.EmployeeInfo;
import com.mercury.model.EmployeePosition;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;

import java.util.List;

@SuppressWarnings("unchecked")
public class EmployeeInfoDaoImpl implements EmployeeInfoDAO {
    private static final LoggerWrapper LOG = LoggerWrapper.getLogger(EmployeeInfoDaoImpl.class);

    @Override
    public List<EmployeeInfo> getAll() throws DataAccessException {
        return (List<EmployeeInfo>) HibernateUtil.doGetAll(EmployeeInfo.class);
    }

    @Override
    public EmployeeInfo update(EmployeeInfo updateEntity) throws DataAccessException {
        return (EmployeeInfo) HibernateUtil.doUpdate(updateEntity);
    }

    @Override
    public void delete(EmployeeInfo entity) throws DataAccessException {
        HibernateUtil.doDelete(entity);
    }

    @Override
    public void create(EmployeeInfo entity) throws DataAccessException {
        HibernateUtil.doCreate(entity);
    }

    @Override
    public void createOrUpdate(EmployeeInfo entity) throws DataAccessException {
        HibernateUtil.doCreateOrUpdate(entity);
    }
}