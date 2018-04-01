package com.mercury.service;

import com.google.gson.Gson;
import com.mercury.dao.impl.UserDaoImpl;
import com.mercury.util.SessionWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LogoutServlet extends HttpServlet {
    private static UserDaoImpl userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionWrapper sessionWrapper = new SessionWrapper(request.getSession());
        sessionWrapper.setLoggedUserId(null);
        response.getWriter().write(new Gson().toJson(new HashMap<>()));
    }
}
