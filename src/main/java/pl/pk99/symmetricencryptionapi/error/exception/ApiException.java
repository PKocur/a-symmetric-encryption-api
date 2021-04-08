package pl.pk99.symmetricencryptionapi.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String fieldName;

    public ApiException(HttpStatus httpStatus, String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
        this.httpStatus = httpStatus;
    }
}
