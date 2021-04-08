package pl.pk99.symmetricencryptionapi.component.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pk99.symmetricencryptionapi.component.AsymmetricEncryptor;
import pl.pk99.symmetricencryptionapi.component.AsymmetricKey;
import pl.pk99.symmetricencryptionapi.config.AlgorithmsConfiguration;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
@RequiredArgsConstructor
public class AsymmetricEncryptorImpl implements AsymmetricEncryptor {

    private final AsymmetricKey asymmetricKey;

    @Override
    public byte[] encrypt(String text) {
        try {
            Cipher cipher = Cipher.getInstance(AlgorithmsConfiguration.ASYMMETRIC_ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, asymmetricKey.getKeyPair().getPrivate());
            return cipher.doFinal(text.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
                | InvalidKeyException e) {
            throw new RuntimeException("Error during encryption occurred");
        }
    }

    @Override
    public String decrypt(byte[] encryptedText) {
        try {
            Cipher cipher = Cipher.getInstance(AlgorithmsConfiguration.ASYMMETRIC_ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, asymmetricKey.getKeyPair().getPublic());
            return new String(cipher.doFinal(encryptedText));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
                | InvalidKeyException e) {
            throw new RuntimeException("Error during decryption occurred");
        }
    }
}
