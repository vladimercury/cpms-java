package com.mercury.exception;

public class NotAuthorizedException extends ForbiddenException {
    public NotAuthorizedException() {
        super("Not authorized");
    }
}
