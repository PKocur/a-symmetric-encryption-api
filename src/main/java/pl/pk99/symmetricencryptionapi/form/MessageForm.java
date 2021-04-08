package pl.pk99.symmetricencryptionapi.form;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static pl.pk99.symmetricencryptionapi.validation.ValidationErrorMessages.FIELD_LENGTH_BETWEEN;
import static pl.pk99.symmetricencryptionapi.validation.ValidationErrorMessages.FIELD_REQUIRED;

@Getter
public class MessageForm {
    @NotBlank(message = FIELD_REQUIRED)
    @Size(min = 1, max = 5000, message = FIELD_LENGTH_BETWEEN)
    private String message;
}
