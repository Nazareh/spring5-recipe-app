package turmina.nazareh.spring5recipeapp.services;

import turmina.nazareh.spring5recipeapp.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById (String recipeId, String ingredientId);
}
