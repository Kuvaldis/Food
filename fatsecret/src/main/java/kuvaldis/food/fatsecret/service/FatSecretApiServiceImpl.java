package kuvaldis.food.fatsecret.service;

import kuvaldis.food.fatsecret.auth.FatSecretRequestFactoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Service;

@Service
public class FatSecretApiServiceImpl implements FatSecretApiService {

    @Autowired
    private ProtectedResourceDetails fatsecretResourceDetails;

    @Autowired
    private FatSecretRequestFactoryFactory fatSecretRequestFactoryFactory;

    @Override
    public String getFood() {
        return new OAuthRestTemplate(fatSecretRequestFactoryFactory.get("food.get"), fatsecretResourceDetails)
                .postForObject("http://platform.fatsecret.com/rest/server.api", null, String.class);
    }
}
