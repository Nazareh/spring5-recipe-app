package turmina.nazareh.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import turmina.nazareh.spring5recipeapp.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, String> {
}
