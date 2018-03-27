package com.mercury.service;

import com.google.gson.Gson;
import com.mercury.dao.impl.UserDaoImpl;
import com.mercury.dao.util.PasswordHash;
import com.mercury.dto.UserDTO;
import com.mercury.exception.DataAccessException;
import com.mercury.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

public class HashServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String password = request.getParameter("password");
        if (password != null) {
            try {
                response.getWriter().write(PasswordHash.generateStrongPasswordHash(password));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
