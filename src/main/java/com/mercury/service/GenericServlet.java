package com.mercury.service;

import com.mercury.exception.BadRequestException;
import com.mercury.exception.DataAccessException;
import com.mercury.exception.ForbiddenException;
import com.mercury.exception.NotFoundException;
import com.mercury.util.RequestWrapper;
import com.mercury.util.ResponseWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GenericServlet extends HttpServlet {
    protected void handleGet(RequestWrapper request, ResponseWrapper response) throws ServletException, IOException,
            BadRequestException, DataAccessException, ForbiddenException, NotFoundException{
        super.doGet(request.getInnerRequest(), response.getInnerResponse());
    }

    protected void handlePost(RequestWrapper request, ResponseWrapper response) throws ServletException, IOException,
            BadRequestException, DataAccessException, ForbiddenException, NotFoundException{
        super.doPost(request.getInnerRequest(), response.getInnerResponse());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseWrapper response = new ResponseWrapper(resp);
        try {
            handleGet(new RequestWrapper(req), response);
        } catch (BadRequestException e) {
            response.setBadRequestError(e);
        } catch (DataAccessException e) {
            response.setInternalError(e);
        } catch (ForbiddenException e) {
            response.setForbiddenError(e);
        } catch (NotFoundException e) {
            response.setNotFoundError(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseWrapper response = new ResponseWrapper(resp);
        try {
            handlePost(new RequestWrapper(req), response);
        } catch (BadRequestException e) {
            response.setBadRequestError(e);
        } catch (DataAccessException e) {
            response.setInternalError(e);
        } catch (ForbiddenException e) {
            response.setForbiddenError(e);
        } catch (NotFoundException e) {
            response.setNotFoundError(e);
        }
    }
}
