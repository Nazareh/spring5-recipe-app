package turmina.nazareh.spring5recipeapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import turmina.nazareh.spring5recipeapp.converters.RecipeCommandToRecipe;
import turmina.nazareh.spring5recipeapp.converters.RecipeToRecipeCommand;
import turmina.nazareh.spring5recipeapp.domain.Recipe;
import turmina.nazareh.spring5recipeapp.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeService recipeService;
    Recipe recipe;
    Set<Recipe> recipesSet;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe ;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
        recipe = new Recipe();
        recipesSet = new HashSet<>();
    }

    @Test
    public void getRecipeByIdTest(){
        recipe.setId(1L);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        assertNotNull("Null recipe returned", recipeService.findById(1L));
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }

    @Test
    public void getRecipesTest() {
        recipesSet.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesSet);

        assertEquals(recipeService.getRecipes().size(),1);
        verify(recipeRepository,times(1)).findAll();
    }

    @Test
    public void testDeleteById() throws Exception {

        //given
        Long idToDelete = Long.valueOf(2);
        //when
        recipeService.deleteById(idToDelete);
        //then
        verify(recipeRepository,times(1)).deleteById(anyLong());
    }
}