package turmina.nazareh.spring5recipeapp.services;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import turmina.nazareh.spring5recipeapp.bootstrap.RecipeBootstrap;
import turmina.nazareh.spring5recipeapp.commands.RecipeCommand;
import turmina.nazareh.spring5recipeapp.converters.RecipeCommandToRecipe;
import turmina.nazareh.spring5recipeapp.converters.RecipeToRecipeCommand;
import turmina.nazareh.spring5recipeapp.domain.Recipe;
import turmina.nazareh.spring5recipeapp.repositories.CategoryRepository;
import turmina.nazareh.spring5recipeapp.repositories.RecipeRepository;
import turmina.nazareh.spring5recipeapp.repositories.UnitOfMeasureRepository;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeServiceIT{

    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    RecipeCommandToRecipe recipeCommandToRecipe;

    RecipeToRecipeCommand recipeToRecipeCommand;

    @Test
    @Ignore
    public void testSaveOfDescription() throws Exception{
        //given
        Iterable<Recipe> recipes = recipeService.getRecipes();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(),savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(),savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(),savedRecipeCommand.getIngredients().size());
    }

}
