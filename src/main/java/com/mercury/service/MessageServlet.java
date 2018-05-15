package com.mercury.service;

import com.google.gson.Gson;
import com.mercury.dao.impl.MessageDaoImpl;
import com.mercury.dao.impl.UserDaoImpl;
import com.mercury.dto.MessageDTO;
import com.mercury.exception.*;
import com.mercury.model.Message;
import com.mercury.model.User;
import com.mercury.util.RequestWrapper;
import com.mercury.util.ResponseWrapper;
import com.mercury.util.SessionWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MessageServlet extends GenericServlet {
    private static UserDaoImpl userDao = new UserDaoImpl();
    private static MessageDaoImpl messageDao = new MessageDaoImpl();

    private Message processPost(RequestWrapper request) throws IOException, DataAccessException,
            BadRequestException, NotFoundException, ForbiddenException {
        if (!request.isUserAuthorized()) {
            throw new NotAuthorizedException();
        }

        String content = request.requireNotEmptyParameterString("content");
        Integer targetId = request.requireParameterInteger("target");

        User author = userDao.get(request.getCurrentUserId());
        User target = userDao.get(targetId);
        if (target == null) {
            throw new NotFoundException("Target user not found");
        }

        Message message = new Message();
        message.setContent(content);
        message.setCreationDate(new Timestamp(new Date().getTime()));
        message.setAuthor(author);
        message.setTarget(target);

        messageDao.create(message);
        return message;
    }

    @Override
    protected void handleGet(RequestWrapper request, ResponseWrapper response) throws ServletException, IOException, BadRequestException, DataAccessException, ForbiddenException, NotFoundException {
        List<Message> messages = messageDao.getReceivedMessages(request.getCurrentUserId());
        response.writeJson(messages.stream().map(MessageDTO::new).collect(Collectors.toList()));
    }

    @Override
    protected void handlePost(RequestWrapper request, ResponseWrapper response) throws ServletException, IOException, BadRequestException, DataAccessException, ForbiddenException, NotFoundException {
        response.writeJson(processPost(request));
    }
}
