package turmina.nazareh.spring5recipeapp.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import turmina.nazareh.spring5recipeapp.commands.RecipeCommand;
import turmina.nazareh.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
   Flux<Recipe> getRecipes();

   Mono<Recipe> findById(String id);

   Mono<RecipeCommand> saveRecipeCommand(RecipeCommand recipeCommand);

   Mono<RecipeCommand> findCommandById(String id);

   Mono<Void> deleteById (String id);

}
