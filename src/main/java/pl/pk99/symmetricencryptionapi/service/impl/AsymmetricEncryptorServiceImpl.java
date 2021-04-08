package pl.pk99.symmetricencryptionapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.pk99.symmetricencryptionapi.component.AsymmetricEncryptor;
import pl.pk99.symmetricencryptionapi.error.ErrorMessages;
import pl.pk99.symmetricencryptionapi.error.exception.ApiException;
import pl.pk99.symmetricencryptionapi.service.AsymmetricEncryptorService;

import javax.xml.bind.DatatypeConverter;

@Service
@RequiredArgsConstructor
public class AsymmetricEncryptorServiceImpl implements AsymmetricEncryptorService {

    private final AsymmetricEncryptor asymmetricEncryptor;

    @Override
    public String encodeMessage(String message) {
        return DatatypeConverter.printHexBinary(asymmetricEncryptor.encrypt(message));
    }

    @Override
    public String decodeMessage(String encryptedMessage) {
        try {
            return asymmetricEncryptor.decrypt(DatatypeConverter.parseHexBinary(encryptedMessage));
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "message", ErrorMessages.HEX_FORMAT_REQUIRED);
        }
    }
}
