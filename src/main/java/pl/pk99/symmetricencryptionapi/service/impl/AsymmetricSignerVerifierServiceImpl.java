package pl.pk99.symmetricencryptionapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pk99.symmetricencryptionapi.component.MessageSigner;
import pl.pk99.symmetricencryptionapi.component.MessageVerifier;
import pl.pk99.symmetricencryptionapi.form.MessageForm;
import pl.pk99.symmetricencryptionapi.form.MessageVerifierForm;
import pl.pk99.symmetricencryptionapi.service.AsymmetricSignerVerifierService;

import javax.xml.bind.DatatypeConverter;

@Service
@RequiredArgsConstructor
public class AsymmetricSignerVerifierServiceImpl implements AsymmetricSignerVerifierService {

    private final MessageSigner messageSigner;
    private final MessageVerifier messageVerifier;

    @Override
    public String signMessage(MessageForm messageForm) {
        return DatatypeConverter.printHexBinary(messageSigner.sign(messageForm.getMessage()));
    }

    @Override
    public boolean verifyMessage(MessageVerifierForm messageForm) {
        return messageVerifier.verify(messageForm.getMessage(), DatatypeConverter.parseHexBinary(messageForm.getSignedMessage()));
    }
}
