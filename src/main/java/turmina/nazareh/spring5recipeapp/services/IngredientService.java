package turmina.nazareh.spring5recipeapp.services;

import turmina.nazareh.spring5recipeapp.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
