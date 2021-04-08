package pl.pk99.symmetricencryptionapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.pk99.symmetricencryptionapi.component.SymmetricEncryptor;
import pl.pk99.symmetricencryptionapi.error.ErrorMessages;
import pl.pk99.symmetricencryptionapi.error.exception.ApiException;
import pl.pk99.symmetricencryptionapi.service.SymmetricEncryptorService;

import javax.xml.bind.DatatypeConverter;

@Service
@RequiredArgsConstructor
public class SymmetricEncryptorServiceImpl implements SymmetricEncryptorService {

    private final SymmetricEncryptor symmetricEncryptor;

    @Override
    public String encodeMessage(String message) {
        return DatatypeConverter.printHexBinary(symmetricEncryptor.encrypt(message));
    }

    @Override
    public String decodeMessage(String encryptedMessage) {
        try {
            return symmetricEncryptor.decrypt(DatatypeConverter.parseHexBinary(encryptedMessage));
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "message", ErrorMessages.HEX_FORMAT_REQUIRED);
        }
    }
}
