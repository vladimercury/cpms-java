package com.mercury.service;

import com.google.gson.Gson;
import com.mercury.LoggerWrapper;
import com.mercury.dao.impl.EmployeeInfoDaoImpl;
import com.mercury.dao.impl.EmployeePositionDaoImpl;
import com.mercury.dao.impl.UserDaoImpl;
import com.mercury.dto.EmployeeInfoDTO;
import com.mercury.dto.UserDTO;
import com.mercury.exception.DataAccessException;
import com.mercury.model.EmployeeInfo;
import com.mercury.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloServlet extends HttpServlet {
    private static UserDaoImpl userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        request.setCharacterEncoding("UTF-8");
        try {
            List<User> userList = userDao.getAll();
            List<UserDTO> userDTOS = new ArrayList<>();
            for (User user : userList) {
                userDTOS.add(new UserDTO(user));
        }
            response.getWriter().write(new Gson().toJson(userDTOS));
        } catch (DataAccessException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
