package kuvaldis.food.fatsecret.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.*;
import org.springframework.security.oauth.consumer.client.CoreOAuthConsumerSupport;

@Configuration
@ComponentScan(basePackages = "kuvaldis.food.fatsecret")
public class FatSecretConfig {

    @Value("${fatsecret.api.oauth.consumer.key}")
    private String consumerKey;

    @Value("${fatsecret.api.oauth.shared.secret}")
    private String sharedSecret;

    @Bean
    public OAuthConsumerSupport fatsecretConsumerSupport() {
        return new CoreOAuthConsumerSupport();
    }

    @Bean
    public ProtectedResourceDetailsService protectedResourceDetailsService() {
        return new InMemoryProtectedResourceDetailsService();
    }

    @Bean
    public ProtectedResourceDetails fatsecretResourceDetails() {
        final BaseProtectedResourceDetails details = new BaseProtectedResourceDetails();
        details.setConsumerKey(consumerKey);
        details.setSharedSecret(new SharedConsumerSecretImpl(sharedSecret));
        details.setAcceptsAuthorizationHeader(false);
        return details;
    }
}
