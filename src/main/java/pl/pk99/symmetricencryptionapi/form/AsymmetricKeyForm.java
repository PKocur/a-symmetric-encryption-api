package pl.pk99.symmetricencryptionapi.form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static pl.pk99.symmetricencryptionapi.validation.ValidationErrorMessages.FIELD_LENGTH_BETWEEN;
import static pl.pk99.symmetricencryptionapi.validation.ValidationErrorMessages.FIELD_REQUIRED;

@Getter
@RequiredArgsConstructor
public class AsymmetricKeyForm {
    @NotBlank(message = FIELD_REQUIRED)
    @Size(min = 4, max = 5000, message = FIELD_LENGTH_BETWEEN)
    private final String publicKey;

    @NotBlank(message = FIELD_REQUIRED)
    @Size(min = 4, max = 5000, message = FIELD_LENGTH_BETWEEN)
    private final String privateKey;
}
