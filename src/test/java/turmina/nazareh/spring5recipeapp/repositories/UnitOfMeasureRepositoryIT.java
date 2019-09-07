package turmina.nazareh.spring5recipeapp.repositories;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import turmina.nazareh.spring5recipeapp.domain.UnitOfMeasure;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class UnitOfMeasureRepositoryIT {

    private UnitOfMeasureRepository unitOfMeasureRepository;
    private String description;

    @Test
    @Ignore
    public void findByDescription() {
//        description = "Teaspoon";
//        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription(description);
//        assertEquals(description, uomOptional.get().getDescription());
    }

    @Test
    @Ignore
    public void findByDescriptionCup() {
//        description = "Cup";
//        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription(description);
//        assertEquals(description, uomOptional.get().getDescription());
    }
}