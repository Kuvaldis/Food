package kuvaldis.food.fatsecret.auth;

import org.springframework.security.oauth.consumer.nonce.UUIDNonceFactory;
import org.springframework.stereotype.Component;

@Component
public class FatSecretNonceFactory extends UUIDNonceFactory {
    @Override
    public String generateNonce() {
        return super.generateNonce().replace("-", "");
    }
}
