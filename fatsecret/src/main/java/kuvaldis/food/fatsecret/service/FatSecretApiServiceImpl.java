package kuvaldis.food.fatsecret.service;

import kuvaldis.food.fatsecret.auth.FatSecretRequestFactoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FatSecretApiServiceImpl implements FatSecretApiService {

    private static final String BASE_URL = "http://platform.fatsecret.com/rest/server.api";

    @Autowired
    private ProtectedResourceDetails fatsecretResourceDetails;

    @Autowired
    private FatSecretRequestFactoryFactory fatSecretRequestFactoryFactory;

    @Override
    public String getFood(final Long foodId) {
        final Map<String, String> params = new HashMap<>();
        params.put("food_id", String.valueOf(foodId));
        return new OAuthRestTemplate(fatSecretRequestFactoryFactory.get("food.get", params), fatsecretResourceDetails)
                .postForObject(BASE_URL, null, String.class);
    }
}
