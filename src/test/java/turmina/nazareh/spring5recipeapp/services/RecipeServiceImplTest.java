package turmina.nazareh.spring5recipeapp.services;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import turmina.nazareh.spring5recipeapp.commands.RecipeCommand;
import turmina.nazareh.spring5recipeapp.converters.RecipeCommandToRecipe;
import turmina.nazareh.spring5recipeapp.converters.RecipeToRecipeCommand;
import turmina.nazareh.spring5recipeapp.domain.Recipe;
import turmina.nazareh.spring5recipeapp.exceptions.NotFoundException;
import turmina.nazareh.spring5recipeapp.repositories.reactive.RecipeReactiveRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeService recipeService;
    Recipe recipe;

    @Mock
    RecipeReactiveRepository recipeReactiveRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe ;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeReactiveRepository, recipeCommandToRecipe, recipeToRecipeCommand);
        recipe = new Recipe();
    }

    @Test
    public void getRecipeByIdTest(){
        recipe.setId("1L");

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        assertNotNull("Null recipe returned", recipeService.findById("1L").block());
        verify(recipeReactiveRepository,times(1)).findById(anyString());
        verify(recipeReactiveRepository,never()).findAll();
    }

    @Test
    public void getRecipeCommandByIdTest() throws Exception{
        recipe.setId("1L");

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId("1L");

        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

        RecipeCommand commandById = recipeService.findCommandById("1L").block();

        assertNotNull("Null recipe returned", commandById);
        verify(recipeReactiveRepository,times(1)).findById(anyString());
        verify(recipeReactiveRepository,never()).findAll();

    }

    @Test
    public void getRecipesTest() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.add(recipe);

        when(recipeReactiveRepository.findAll()).thenReturn(Flux.fromIterable(recipeSet));

        assertEquals(recipeService.getRecipes().collectList().block().size(),1);
        verify(recipeReactiveRepository,times(1)).findAll();
    }

    @Test
    public void testDeleteById() throws Exception {

        //given
        String idToDelete = "2L";
        when(recipeReactiveRepository.deleteById(anyString())).thenReturn(Mono.empty());
        //when
        recipeService.deleteById(idToDelete);
        //then
        verify(recipeReactiveRepository,times(1)).deleteById(anyString());
    }
}