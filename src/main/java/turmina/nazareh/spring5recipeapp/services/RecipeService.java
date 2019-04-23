package turmina.nazareh.spring5recipeapp.services;

import turmina.nazareh.spring5recipeapp.commands.RecipeCommand;
import turmina.nazareh.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
   Set<Recipe> getRecipes();

   Recipe findById(Long id);

   RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

   RecipeCommand findCommandById(Long id);

}
