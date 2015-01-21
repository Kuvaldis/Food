package kuvaldis.food.fatsecret.support;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.security.oauth.consumer.OAuthConsumerSupport;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthClientHttpRequestFactory;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FatSecretRequestFactoryFactoryImplTest {

    private FatSecretRequestFactoryFactoryImpl fatSecretRequestFactoryFactory;

    @Mock
    private ProtectedResourceDetails protectedResourceDetails;

    @Mock
    private OAuthConsumerSupport oAuthConsumerSupport;

    @Before
    public void setUp() {
        final FatSecretRequestFactoryFactoryImpl fatSecretRequestFactoryFactory = new FatSecretRequestFactoryFactoryImpl();
        fatSecretRequestFactoryFactory.setFatsecretConsumerSupport(oAuthConsumerSupport);
        fatSecretRequestFactoryFactory.setFatsecretResourceDetails(protectedResourceDetails);
        this.fatSecretRequestFactoryFactory = fatSecretRequestFactoryFactory;
    }

    @Test
    public void getShouldReturnNotNull() {
        // when
        final ClientHttpRequestFactory result = fatSecretRequestFactoryFactory.get("some method");
        // then
        assertThat(result).isNotNull();
    }

    @Test
    public void getShouldReturnOAuthClientHttpRequestFactory() {
        // when
        final ClientHttpRequestFactory result = fatSecretRequestFactoryFactory.get("some method");
        // then
        assertThat(result).isInstanceOf(OAuthClientHttpRequestFactory.class);
    }

    @Test
    public void getShouldReturnResultWithCorrectFormatSetup() {
        // given
        final String format = "some format";
        fatSecretRequestFactoryFactory.setFormat(format);
        // when
        final OAuthClientHttpRequestFactory result = (OAuthClientHttpRequestFactory) fatSecretRequestFactoryFactory.get("some method");
        // then
        assertThat(result.getAdditionalOAuthParameters()).containsEntry("format", format);
    }

    @Test
    public void getShouldReturnResultWithMethodPassed() {
        // given
        final String method = "some method";
        // when
        final OAuthClientHttpRequestFactory result = (OAuthClientHttpRequestFactory) fatSecretRequestFactoryFactory.get(method);
        // then
        assertThat(result.getAdditionalOAuthParameters()).containsEntry("method", method);
    }

    @Test
    public void getShouldReturnResultWithSize2() {
        // when
        final OAuthClientHttpRequestFactory result = (OAuthClientHttpRequestFactory) fatSecretRequestFactoryFactory.get("some method");
        // then
        assertThat(result.getAdditionalOAuthParameters()).hasSize(2);
    }

    @Test
    public void getWithParamsNullShouldReturnResultWithSize2() {
        // when
        final OAuthClientHttpRequestFactory result = (OAuthClientHttpRequestFactory) fatSecretRequestFactoryFactory.get("some method");
        // then
        assertThat(result.getAdditionalOAuthParameters()).hasSize(2);
    }

    @Test
    public void getWithParamsShouldReturnResultWithParamsPassed() {
        // given
        final Map<String, String> params = new HashMap<>();
        params.put("param1", "param1 value");
        params.put("param2", "param2 value");
        // when
        final OAuthClientHttpRequestFactory result = (OAuthClientHttpRequestFactory) fatSecretRequestFactoryFactory.get("some method", params);
        // then
        assertThat(result.getAdditionalOAuthParameters()).containsEntry("param1", "param1 value");
        assertThat(result.getAdditionalOAuthParameters()).containsEntry("param2", "param2 value");
    }

    @Test
    public void getWithParamsShouldReturnResultWithPassedParamsSizePlus2() {
        // given
        final Map<String, String> params = new HashMap<>();
        params.put("param1", "param1 value");
        params.put("param2", "param2 value");
        // when
        final OAuthClientHttpRequestFactory result = (OAuthClientHttpRequestFactory) fatSecretRequestFactoryFactory.get("some method", params);
        // then
        assertThat(result.getAdditionalOAuthParameters()).hasSize(params.size() + 2);
    }
}