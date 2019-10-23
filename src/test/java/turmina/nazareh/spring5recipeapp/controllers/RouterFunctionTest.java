package turmina.nazareh.spring5recipeapp.controllers;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;
import turmina.nazareh.spring5recipeapp.config.WebConfig;
import turmina.nazareh.spring5recipeapp.domain.Recipe;
import turmina.nazareh.spring5recipeapp.services.RecipeService;

import static org.mockito.Mockito.when;

public class RouterFunctionTest {
    
    WebTestClient webTestClient;
    
    @Mock
    RecipeService recipeService;
    
    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        WebConfig webConfig = new WebConfig();

        RouterFunction<?> routerFunction = webConfig.routes(recipeService);
        
        webTestClient = WebTestClient.bindToRouterFunction(routerFunction).build();
    }

    @Test
    public void testGetRecipes() {
        
        when(recipeService.getRecipes()).thenReturn(Flux.just());

        webTestClient.get().uri("/api/recipes")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testGetRecipesWithData() {

        when(recipeService.getRecipes()).thenReturn(Flux.just(new Recipe()));

        webTestClient.get().uri("/api/recipes")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Recipe.class);
    }
}
