package turmina.nazareh.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import turmina.nazareh.spring5recipeapp.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,String> {

    Optional<UnitOfMeasure> findByDescription (String description);

}
