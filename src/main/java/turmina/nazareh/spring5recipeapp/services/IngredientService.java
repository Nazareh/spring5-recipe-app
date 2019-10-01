package turmina.nazareh.spring5recipeapp.services;

import reactor.core.publisher.Mono;
import turmina.nazareh.spring5recipeapp.commands.IngredientCommand;

public interface IngredientService {
    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    Mono<IngredientCommand> saveIngredientCommand(IngredientCommand command);

    Mono<Void> deleteById (String recipeId, String ingredientId);
}
