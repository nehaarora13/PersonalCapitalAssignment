package com.pc.exceptions;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception {


    private static final long serialVersionUID = 2944414802863822391L;
    private HttpStatus httpStatus;

    public ServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
    
    public ServiceException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
    
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

}
