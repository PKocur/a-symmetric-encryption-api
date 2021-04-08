package pl.pk99.symmetricencryptionapi.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.pk99.symmetricencryptionapi.error.exception.ApiException;
import pl.pk99.symmetricencryptionapi.error.exception.ApiValidationException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String DEFAULT_ERROR_NAME = "error";
    private static final String DEFAULT_ERROR_MESSAGE = "Other error occurred";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> validationErrors = getValidationErrors(exception.getBindingResult());
        return handleExceptionInternal(exception, wrapIntoErrors(validationErrors), headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ApiValidationException.class)
    protected ResponseEntity<Object> handleApiValidationException(ApiValidationException exception, WebRequest request) {
        Map<String, String> validationErrors = getValidationErrors(exception.getBindingResult());
        return handleExceptionInternal(exception, wrapIntoErrors(validationErrors), new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException exception, WebRequest request) {
        Map<String, String> applicationError = new HashMap<>();
        applicationError.put(exception.getFieldName(), exception.getMessage());
        return new ResponseEntity<>(wrapIntoErrors(applicationError), exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherException(Exception exception, WebRequest request) {
        Map<String, String> otherError = new HashMap<>();
        if (exception.getMessage() != null && !exception.getMessage().isEmpty()) {
            otherError.put(DEFAULT_ERROR_NAME, exception.getMessage());
        } else {
            otherError.put(DEFAULT_ERROR_NAME, DEFAULT_ERROR_MESSAGE);
        }
        return new ResponseEntity<>(wrapIntoErrors(otherError), HttpStatus.BAD_REQUEST);
    }

    private Map<String, String> getValidationErrors(BindingResult bindingResult) {
        Map<String, String> validationErrors = new HashMap<>();
        for (ObjectError error : bindingResult.getAllErrors()) {
            String fieldName = error.getObjectName();
            if (error instanceof FieldError) {
                fieldName = ((FieldError) error).getField();
            }
            String message = error.getDefaultMessage();
            validationErrors.put(fieldName, message);
        }
        return validationErrors;
    }

    private Map<String, Map<String, String>> wrapIntoErrors(Map<String, String> errorMap) {
        Map<String, Map<String, String>> errors = new HashMap<>();
        errors.put("errors", errorMap);
        return errors;
    }
}
