package com.mercury.service;

import com.google.gson.Gson;
import com.mercury.dao.impl.UserDaoImpl;
import com.mercury.dto.UserDTO;
import com.mercury.exception.DataAccessException;
import com.mercury.model.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    private static UserDaoImpl userDao = new UserDaoImpl();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        request.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (StringUtils.isNoneEmpty(login, password)) {
            try {
                User user = userDao.authenticate(login, password);
                Map<String, Object> responseMap = new HashMap<>();
                if (user != null) {
                    responseMap.put("success", true);
                    responseMap.put("user", new UserDTO(user));
                } else {
                    responseMap.put("success", false);
                }
                response.getWriter().write(new Gson().toJson(responseMap));
            } catch (DataAccessException | InvalidKeySpecException | NoSuchAlgorithmException e) {
                response.getWriter().write(e.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
