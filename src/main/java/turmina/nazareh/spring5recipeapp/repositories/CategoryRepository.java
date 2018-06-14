package turmina.nazareh.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import turmina.nazareh.spring5recipeapp.domain.Category;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
