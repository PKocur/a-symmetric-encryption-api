package pl.pk99.symmetricencryptionapi.form;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static pl.pk99.symmetricencryptionapi.validation.ValidationErrorMessages.FIELD_LENGTH_BETWEEN;
import static pl.pk99.symmetricencryptionapi.validation.ValidationErrorMessages.FIELD_REQUIRED;

@Getter
public class SymmetricKeyForm {
    @NotBlank(message = FIELD_REQUIRED)
    @Size(min = 4, max = 100, message = FIELD_LENGTH_BETWEEN)
    private String key;
}
