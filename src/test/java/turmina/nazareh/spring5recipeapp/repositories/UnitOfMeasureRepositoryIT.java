package turmina.nazareh.spring5recipeapp.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import turmina.nazareh.spring5recipeapp.bootstrap.RecipeBootstrap;
import turmina.nazareh.spring5recipeapp.domain.UnitOfMeasure;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RecipeRepository recipeRepository;



    @Before
    public void setUp() throws Exception{
        recipeRepository.deleteAll();
        unitOfMeasureRepository.deleteAll();
        categoryRepository.deleteAll();

        RecipeBootstrap recipeBootstrap = new RecipeBootstrap(categoryRepository,recipeRepository,unitOfMeasureRepository);
        recipeBootstrap.onApplicationEvent(null);
    }

    @Test
    public void findByDescription() {
        String description = "Teaspoon";
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription(description);
        assertEquals(description, uomOptional.get().getDescription());
    }

    @Test
    public void findByDescriptionCup() {
        String description = "Cup";
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription(description);
        assertEquals(description, uomOptional.get().getDescription());
    }
}