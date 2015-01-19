package kuvaldis.food.fatsecret.auth;

import org.springframework.http.client.ClientHttpRequestFactory;

import java.util.Map;

public interface FatSecretRequestFactoryFactory {
    ClientHttpRequestFactory get(String method);
    ClientHttpRequestFactory get(String method, Map<String, String> params);
}
