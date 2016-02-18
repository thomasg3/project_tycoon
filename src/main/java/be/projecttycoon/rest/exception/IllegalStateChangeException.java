package be.projecttycoon.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by thomas on 18/02/16.
 */
@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
public class IllegalStateChangeException extends RuntimeException {
    public IllegalStateChangeException() {
        super();
    }

    public IllegalStateChangeException(Throwable cause) {
        super(cause);
    }

    public IllegalStateChangeException(String message) {
        super(message);
    }

    public IllegalStateChangeException(String message, Throwable cause) {
        super(message, cause);
    }

    protected IllegalStateChangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
