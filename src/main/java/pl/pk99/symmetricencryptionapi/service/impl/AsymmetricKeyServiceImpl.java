package pl.pk99.symmetricencryptionapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pk99.symmetricencryptionapi.component.AsymmetricKey;
import pl.pk99.symmetricencryptionapi.component.RandomAsymmetricKeyGenerator;
import pl.pk99.symmetricencryptionapi.form.AsymmetricKeyForm;
import pl.pk99.symmetricencryptionapi.pojo.AsymmetricKeyPair;
import pl.pk99.symmetricencryptionapi.service.AsymmetricKeyService;

import javax.xml.bind.DatatypeConverter;
import java.security.KeyPair;

@Service
@RequiredArgsConstructor
public class AsymmetricKeyServiceImpl implements AsymmetricKeyService {

    private final RandomAsymmetricKeyGenerator asymmetricKeyGenerator;
    private final AsymmetricKey asymmetricKey;

    @Override
    public AsymmetricKeyPair getNewKeyPair() {
        KeyPair keyPair = asymmetricKeyGenerator.generate();
        asymmetricKey.setKey(keyPair);
        return AsymmetricKeyPair.of(
                DatatypeConverter.printHexBinary(asymmetricKey.getKeyPair().getPublic().getEncoded()),
                DatatypeConverter.printHexBinary(asymmetricKey.getKeyPair().getPrivate().getEncoded()));
    }

    @Override
    public void setKey(AsymmetricKeyForm asymmetricKeyForm) {
        asymmetricKey.setKey(AsymmetricKeyPair.of(asymmetricKeyForm.getPublicKey(), asymmetricKeyForm.getPrivateKey()));
    }
}
