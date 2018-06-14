package turmina.nazareh.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import turmina.nazareh.spring5recipeapp.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
}
