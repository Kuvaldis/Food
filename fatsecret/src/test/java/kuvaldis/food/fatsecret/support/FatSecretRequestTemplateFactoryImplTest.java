package kuvaldis.food.fatsecret.support;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthClientHttpRequestFactory;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.web.client.RestOperations;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class FatSecretRequestTemplateFactoryImplTest {

    private FatSecretRequestTemplateFactoryImpl fatSecretRequestTemplateFactory;

    @Mock
    private ProtectedResourceDetails protectedResourceDetails;

    @Mock
    private FatSecretRequestFactoryFactory fatSecretRequestFactoryFactory;

    @Mock
    private OAuthClientHttpRequestFactory oAuthClientHttpRequestFactory;

    @Before
    public void setUp() {
        final FatSecretRequestTemplateFactoryImpl fatSecretRequestTemplateFactory = new FatSecretRequestTemplateFactoryImpl();
        fatSecretRequestTemplateFactory.setFatsecretResourceDetails(protectedResourceDetails);
        fatSecretRequestTemplateFactory.setFatSecretRequestFactoryFactory(fatSecretRequestFactoryFactory);
        Mockito.when(fatSecretRequestFactoryFactory.get(anyString(), any()))
                .thenReturn(oAuthClientHttpRequestFactory);
        this.fatSecretRequestTemplateFactory = fatSecretRequestTemplateFactory;
    }

    @Test
    public void getShouldReturnNotNull() throws Exception {
        // when
        final RestOperations template = fatSecretRequestTemplateFactory.get("some method", null);
        // then
        assertThat(template).isNotNull();
    }

    @Test
    public void getShouldReturnOAuthRestTemplate() throws Exception {
        // when
        final RestOperations template = fatSecretRequestTemplateFactory.get("some method", null);
        // then
        assertThat(template).isInstanceOf(OAuthRestTemplate.class);
    }

    @Test
    public void getShouldReturnTemplateContainingCorrectResource() throws Exception {
        // when
        final OAuthRestTemplate template = (OAuthRestTemplate) fatSecretRequestTemplateFactory.get("some method", null);
        // then
        assertThat(template.getResource()).isSameAs(protectedResourceDetails);
    }

    @Test
    public void getShouldReturnTemplateContainingCorrectRequestFactory() throws Exception {
        // when
        final OAuthRestTemplate template = (OAuthRestTemplate) fatSecretRequestTemplateFactory.get("some method", null);
        // then
        assertThat(template.getRequestFactory()).isSameAs(oAuthClientHttpRequestFactory);
    }
}