package com.pc.exceptions;

public class NoDataFoundException extends RuntimeException {

    private static final long serialVersionUID = 5093850273541747142L;

    public NoDataFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoDataFoundException(String msg) {
        super(msg);
    }

    public NoDataFoundException(Throwable cause) {
        super(cause);
    }

}
