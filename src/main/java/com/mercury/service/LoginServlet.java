package com.mercury.service;

import com.google.gson.Gson;
import com.mercury.dao.impl.UserDaoImpl;
import com.mercury.dto.UserDTO;
import com.mercury.exception.DataAccessException;
import com.mercury.model.User;
import com.mercury.util.SessionWrapper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    private static UserDaoImpl userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionWrapper sessionWrapper = new SessionWrapper(request.getSession());

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (StringUtils.isNoneEmpty(login, password)) {
            try {
                User user = userDao.authenticate(login, password);
                Map<String, Object> responseMap = new HashMap<>();
                if (user != null) {
                    UserDTO userDTO = new UserDTO(user);
                    userDTO.setOwnUser(true);
                    sessionWrapper.setLoggedUserId(user.getId());
                    responseMap.put("success", true);
                    responseMap.put("user", userDTO);
                } else {
                    responseMap.put("success", false);
                }
                response.getWriter().write(new Gson().toJson(responseMap));
            } catch (DataAccessException | InvalidKeySpecException | NoSuchAlgorithmException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
