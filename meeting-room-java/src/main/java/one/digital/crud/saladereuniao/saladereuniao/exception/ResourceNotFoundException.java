package one.digital.crud.saladereuniao.saladereuniao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

    private static final Long SERIAL_VERSION_UID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
