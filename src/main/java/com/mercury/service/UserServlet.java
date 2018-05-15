package com.mercury.service;

import com.mercury.dao.impl.UserDaoImpl;
import com.mercury.dao.util.PasswordHash;
import com.mercury.dto.UserDTO;
import com.mercury.exception.BadRequestException;
import com.mercury.exception.DataAccessException;
import com.mercury.exception.ForbiddenException;
import com.mercury.exception.NotFoundException;
import com.mercury.model.EmployeeInfo;
import com.mercury.model.EmployeePosition;
import com.mercury.model.User;
import com.mercury.util.RequestWrapper;
import com.mercury.util.ResponseWrapper;

import javax.servlet.ServletException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

public class UserServlet extends GenericServlet {
    private static UserDaoImpl userDao = new UserDaoImpl();

    private UserDTO getUser(RequestWrapper request, Integer userId) throws DataAccessException,
            NotFoundException {
        User user = null;
        if (userId == 0) {
            user = userDao.getWithInfo(request.getCurrentUserId());
        } else {
            user = userDao.getWithInfo(userId);
        }
        if (user != null) {
            return new UserDTO(user);
        } else {
            throw new NotFoundException("User not found");
        }
    }

    private List<UserDTO> getAllUsers() throws DataAccessException {
        List<User> users = userDao.getAll();
        List<UserDTO> result = new ArrayList<>();
        for (User user : users) {
            result.add(new UserDTO(user));
        }
        return result;
    }

    private UserDTO createUser(RequestWrapper request) throws BadRequestException, DataAccessException {
        User user = new User();
        EmployeeInfo info = new EmployeeInfo();
        String password = request.requireNotBlankParameterString("password");
        user.setLogin(request.requireNotBlankParameterString("login"));
        try {
            user.setPassword(PasswordHash.generateStrongPasswordHash(password));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new DataAccessException(e.getMessage());
        }
        user.setFirstName(request.requireNotBlankParameterString("firstName"));
        user.setLastName(request.requireNotBlankParameterString("lastName"));
        user.setMiddleName(request.getParameterTrimmedString("middleName"));
        user.setAdmin(request.requireParameterBoolean("admin"));
        info.setDescription(request.getParameterString("description"));

        Integer positionId = request.getParameterInteger("position");
        if (positionId != null) {
            info.setPosition(userDao.getPosition(positionId));
        }
        userDao.create(user, info);
        return new UserDTO(user);
    }

    private UserDTO updateUser(RequestWrapper request) throws BadRequestException, DataAccessException, NotFoundException {
        Integer userId = request.requirePositiveParameterInteger("id");
        User user = userDao.getWithInfo(userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        String login = request.getParameterTrimmedString("login");
        String firstName = request.getParameterTrimmedString("firstName");
        String lastName = request.getParameterTrimmedString("lastName");
        String middleName = request.getParameterTrimmedString("middleName");
        String password = request.getParameterTrimmedString("password");
        Boolean admin = request.getParameterBoolean("admin");
        String description = request.getParameterString("description");
        Integer positionId = request.getParameterInteger("position");
        if (login != null) {
            user.setLogin(login);
        }
        if (firstName != null) {
            user.setFirstName(firstName);
        }
        if (lastName != null) {
            user.setLastName(lastName);
        }
        if (middleName != null) {
            user.setMiddleName(middleName);
        }
        if (password != null) {
            try {
                user.setPassword(PasswordHash.generateStrongPasswordHash(password));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new DataAccessException(e.getMessage());
            }
        }
        if (admin != null) {
            user.setAdmin(admin);
        }
        if (description != null) {
            user.getInfo().setDescription(description);
        }
        if (positionId != null) {
            if (positionId < 0) {
                user.getInfo().setPosition(null);
            } else {
                EmployeePosition position = userDao.getPosition(positionId);
                if (position == null) {
                    throw new NotFoundException("Position not found");
                }
                user.getInfo().setPosition(position);
            }
        }
        userDao.update(user);
        return new UserDTO(user);
    }

    private void deleteUser(RequestWrapper request) throws BadRequestException, DataAccessException, NotFoundException {
        Integer userId = request.requirePositiveParameterInteger("id");
        User user = userDao.getWithInfo(userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        userDao.delete(user);
    }

    @Override
    protected void handleGet(RequestWrapper request, ResponseWrapper response) throws ServletException, IOException,
            BadRequestException, DataAccessException, ForbiddenException, NotFoundException {
        Integer userId = request.getParameterNonNegativeInteger("id");
        if (userId != null) {
            response.writeJson(getUser(request, userId));
        } else {
            response.writeJson(getAllUsers());
        }
    }

    @Override
    protected void handlePost(RequestWrapper request, ResponseWrapper response) throws ServletException, IOException,
            BadRequestException, DataAccessException, ForbiddenException, NotFoundException {
        if (!request.isUserAdmin()) {
            throw new ForbiddenException("Not an admin");
        }
        if (request.isPutMethod()) {
            response.writeJson(updateUser(request));
        } else if (request.isDeleteMethod()) {
            deleteUser(request);
            response.setNoContentStatus();
        } else {
            response.writeJson(createUser(request));
        }
    }
}
