package pl.pk99.symmetricencryptionapi.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;

@Getter
@RequiredArgsConstructor
public class ApiValidationException extends RuntimeException {
    private final BindingResult bindingResult;
}
