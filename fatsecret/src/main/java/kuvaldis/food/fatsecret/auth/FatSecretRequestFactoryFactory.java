package kuvaldis.food.fatsecret.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth.consumer.OAuthConsumerSupport;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthClientHttpRequestFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class FatSecretRequestFactoryFactory {

    @Autowired
    private ProtectedResourceDetails fatsecretResourceDetails;

    @Autowired
    private OAuthConsumerSupport fatsecretConsumerSupport;

    public ClientHttpRequestFactory get(final String method) {
        final OAuthClientHttpRequestFactory result = new OAuthClientHttpRequestFactory(
                new SimpleClientHttpRequestFactory(), fatsecretResourceDetails, fatsecretConsumerSupport);
        result.setAdditionalOAuthParameters(new HashMap<String, String>() {{
            put("method", method);
        }});
        return result;

    }
}
