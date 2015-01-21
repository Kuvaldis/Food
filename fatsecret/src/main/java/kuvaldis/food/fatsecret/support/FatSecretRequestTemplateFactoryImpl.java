package kuvaldis.food.fatsecret.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class FatSecretRequestTemplateFactoryImpl implements FatSecretRequestTemplateFactory {

    @Autowired
    private ProtectedResourceDetails fatsecretResourceDetails;

    @Autowired
    private FatSecretRequestFactoryFactory fatSecretRequestFactoryFactory;

    @Override
    public RestOperations get(String method, Map<String, String> params) {
        return new OAuthRestTemplate(fatSecretRequestFactoryFactory.get(method, params), fatsecretResourceDetails);
    }

    public void setFatsecretResourceDetails(ProtectedResourceDetails fatsecretResourceDetails) {
        this.fatsecretResourceDetails = fatsecretResourceDetails;
    }

    public void setFatSecretRequestFactoryFactory(FatSecretRequestFactoryFactory fatSecretRequestFactoryFactory) {
        this.fatSecretRequestFactoryFactory = fatSecretRequestFactoryFactory;
    }
}
