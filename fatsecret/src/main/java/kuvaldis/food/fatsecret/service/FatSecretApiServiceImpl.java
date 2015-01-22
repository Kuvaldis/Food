package kuvaldis.food.fatsecret.service;

import kuvaldis.food.fatsecret.support.FatSecretRequestTemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.HashMap;
import java.util.Map;

@Service
public class FatSecretApiServiceImpl implements FatSecretApiService {

    private static final String BASE_URL = "http://platform.fatsecret.com/rest/server.api";

    @Autowired
    private FatSecretRequestTemplateFactory fatSecretRequestTemplateFactory;

    @Override
    public String getFood(final Long foodId) {
        final Map<String, String> params = new HashMap<>();
        params.put("food_id", String.valueOf(foodId));
        final RestOperations template = fatSecretRequestTemplateFactory.get("food.get", params);
        return doGet(template, String.class);
    }

    private <T> T doGet(final RestOperations template, Class<T> cls) {
        return template.getForObject(BASE_URL, cls);
    }

    public void setFatSecretRequestTemplateFactory(FatSecretRequestTemplateFactory fatSecretRequestTemplateFactory) {
        this.fatSecretRequestTemplateFactory = fatSecretRequestTemplateFactory;
    }
}
