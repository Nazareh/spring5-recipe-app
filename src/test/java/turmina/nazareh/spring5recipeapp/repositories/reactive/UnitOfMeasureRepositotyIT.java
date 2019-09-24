package turmina.nazareh.spring5recipeapp.repositories.reactive;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import turmina.nazareh.spring5recipeapp.domain.UnitOfMeasure;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureRepositotyIT {

    public static final String EACH = "Each";
    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception{
        unitOfMeasureReactiveRepository.deleteAll().block();

    }
    @Test
    public void testSaveUom (){
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(EACH);
        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

        assertEquals("1",unitOfMeasureReactiveRepository.count().block().toString());
    }

    @Test
    public void testFindByDescription() throws Exception{
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription(EACH);
        unitOfMeasureReactiveRepository.save(uom).block();

        UnitOfMeasure fetchedUOM = unitOfMeasureReactiveRepository.findByDescription(EACH).block();

        assertEquals(fetchedUOM.getDescription(), EACH);

    }
}
