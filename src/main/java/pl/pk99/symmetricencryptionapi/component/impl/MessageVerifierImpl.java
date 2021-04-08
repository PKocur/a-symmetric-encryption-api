package pl.pk99.symmetricencryptionapi.component.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.pk99.symmetricencryptionapi.component.AsymmetricKey;
import pl.pk99.symmetricencryptionapi.component.MessageVerifier;
import pl.pk99.symmetricencryptionapi.config.AlgorithmsConfiguration;
import pl.pk99.symmetricencryptionapi.error.ErrorMessages;
import pl.pk99.symmetricencryptionapi.error.exception.ApiException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class MessageVerifierImpl implements MessageVerifier {

    private final AsymmetricKey asymmetricKey;

    @Override
    public boolean verify(String message, byte[] signedMessage) {
        try {
            Signature signature = Signature.getInstance(AlgorithmsConfiguration.ASYMMETRIC_SIGN_ALGORITHM);
            signature.initVerify(asymmetricKey.getKeyPair().getPublic());
            signature.update(message.getBytes());
            return signature.verify(Base64.getDecoder().decode(signedMessage));
        } catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException e) {
            if (e instanceof InvalidKeyException)
                throw new ApiException(HttpStatus.BAD_REQUEST, "key", ErrorMessages.INVALID_KEY_PROVIDED);
            else
                throw new RuntimeException("Error during message verifying occurred");
        }
    }
}
