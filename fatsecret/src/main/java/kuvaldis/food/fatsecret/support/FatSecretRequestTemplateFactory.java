package kuvaldis.food.fatsecret.support;

import org.springframework.web.client.RestOperations;

import java.util.Map;

public interface FatSecretRequestTemplateFactory {

    RestOperations get(String method, Map<String, String> params);
}
