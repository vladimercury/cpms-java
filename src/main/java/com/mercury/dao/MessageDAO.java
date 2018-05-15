package com.mercury.dao;

import com.mercury.exception.DataAccessException;
import com.mercury.model.Message;
import com.mercury.model.User;

import java.util.List;

public interface MessageDAO extends GenericDAO<Message, Integer> {
    List<Message> getReceivedMessages(int userId) throws DataAccessException;
}
