package kuvaldis.food.fatsecret.service;

import com.google.common.truth.Truth;
import kuvaldis.food.fatsecret.support.FatSecretRequestTemplateFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestOperations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FatSecretApiServiceImplTest {

    public static final String CORRECT_URL = "http://platform.fatsecret.com/rest/server.api";
    private FatSecretApiServiceImpl fatSecretApiService;

    @Mock
    private FatSecretRequestTemplateFactory fatSecretRequestTemplateFactory;

    @Before
    public void setUp() throws Exception {
        FatSecretApiServiceImpl fatSecretApiService = new FatSecretApiServiceImpl();
        fatSecretApiService.setFatSecretRequestTemplateFactory(fatSecretRequestTemplateFactory);
        this.fatSecretApiService = fatSecretApiService;
    }

    @Test
    public void getFoodGetRequestShouldBeCalledWithCorrectUrl() throws Exception {
        // given
        final RestOperations incorrectTemplate = mock(RestOperations.class);
        when(fatSecretRequestTemplateFactory.get(anyString(), anyMapOf(String.class, String.class))).thenReturn(incorrectTemplate);
        // and
        final Long foodId = 1l;
        final Map<String, String> params = new HashMap<>();
        params.put("food_id", String.valueOf(foodId));
        final RestOperations correctTemplate = mock(RestOperations.class);
        when(fatSecretRequestTemplateFactory.get("food.get", params)).thenReturn(correctTemplate);
        // when
        fatSecretApiService.getFood(foodId);
        // then
        verify(correctTemplate).getForObject(CORRECT_URL, String.class);
        // and
        verify(incorrectTemplate, never()).getForObject(anyString(), any(Class.class));
    }

    @Test
    public void getFoodShouldReturnCorrectValueDependingOnFoodId() throws Exception {
        // given
        final Long foodId1 = 1l;
        final String templateResult1 = "1";
        final RestOperations template1 = setupTemplatesReturn(foodId1, templateResult1);
        // and
        final Long foodId2 = 2l;
        final String templateResult2 = "2";
        final RestOperations template2 = setupTemplatesReturn(foodId2, templateResult2);
        // when
        final String getFoodResult1 = fatSecretApiService.getFood(foodId1);
        // and
        final String getFoodResult2 = fatSecretApiService.getFood(foodId2);
        // then
        verify(template1).getForObject(anyString(), eq(String.class));
        verify(template2).getForObject(anyString(), eq(String.class));
        Truth.assertThat(getFoodResult1).isEqualTo(templateResult1);
        Truth.assertThat(getFoodResult2).isEqualTo(templateResult2);
    }

    private RestOperations setupTemplatesReturn(Long foodId, String templateResult) {
        final Map<String, String> params = new HashMap<>();
        params.put("food_id", String.valueOf(foodId));
        final RestOperations template = mock(RestOperations.class);
        when(template.getForObject(anyString(), eq(String.class))).thenReturn(templateResult);
        when(fatSecretRequestTemplateFactory.get("food.get", params)).thenReturn(template);
        return template;
    }
}