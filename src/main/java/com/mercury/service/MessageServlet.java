package com.mercury.service;

import com.google.gson.Gson;
import com.mercury.dao.impl.MessageDaoImpl;
import com.mercury.dao.impl.UserDaoImpl;
import com.mercury.dto.MessageDTO;
import com.mercury.dto.UserDTO;
import com.mercury.exception.DataAccessException;
import com.mercury.model.Message;
import com.mercury.model.User;
import com.mercury.model.expand.UserExpansion;
import com.mercury.util.SessionWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MessageServlet extends HttpServlet {
    private static UserDaoImpl userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionWrapper session = new SessionWrapper(req.getSession());
        Integer id = session.getLoggedUserId();

        if (id != null) {
            try {
                User user = userDao.get(id);
                List<MessageDTO> messageDTOList = new ArrayList<>();
                for (Message message: user.getReceivedMessages()) {
                    MessageDTO messageDTO = new MessageDTO(message);
                    messageDTO.setAuthor(new UserDTO(message.getAuthor()));
                    messageDTOList.add(messageDTO);
                }

                resp.getWriter().write(new Gson().toJson(messageDTOList));
            } catch (DataAccessException e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
