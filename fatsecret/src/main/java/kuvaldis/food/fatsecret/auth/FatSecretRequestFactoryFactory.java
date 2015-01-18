package kuvaldis.food.fatsecret.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth.consumer.OAuthConsumerSupport;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthClientHttpRequestFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class FatSecretRequestFactoryFactory {

    @Value("${fatsecret.response.format}")
    private String format;

    @Autowired
    private ProtectedResourceDetails fatsecretResourceDetails;

    @Autowired
    private OAuthConsumerSupport fatsecretConsumerSupport;

    public ClientHttpRequestFactory get(final String method) {
        return get(method, null);
    }

    public ClientHttpRequestFactory get(final String method, final Map<String, String> params) {
        final OAuthClientHttpRequestFactory result = new OAuthClientHttpRequestFactory(
                new SimpleClientHttpRequestFactory(), fatsecretResourceDetails, fatsecretConsumerSupport);
        result.setAdditionalOAuthParameters(new HashMap<String, String>() {{
            put("method", method);
            put("format", format);
            Optional.ofNullable(params).ifPresent(this::putAll);
        }});
        return result;

    }
}
