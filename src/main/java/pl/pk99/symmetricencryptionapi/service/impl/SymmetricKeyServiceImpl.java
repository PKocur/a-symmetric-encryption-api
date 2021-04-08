package pl.pk99.symmetricencryptionapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pk99.symmetricencryptionapi.component.RandomSymmetricKeyGenerator;
import pl.pk99.symmetricencryptionapi.component.SymmetricKey;
import pl.pk99.symmetricencryptionapi.service.SymmetricKeyService;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

@Service
@RequiredArgsConstructor
public class SymmetricKeyServiceImpl implements SymmetricKeyService {

    private final RandomSymmetricKeyGenerator randomSymmetricKeyGenerator;
    private final SymmetricKey symmetricKey;

    @Override
    public String getNewKey() {
        SecretKey key = randomSymmetricKeyGenerator.generate();
        symmetricKey.setKey(key);
        return DatatypeConverter.printHexBinary(key.getEncoded());
    }

    @Override
    public void setKey(String key) {
        symmetricKey.setKey(DatatypeConverter.parseHexBinary(key));
    }
}
