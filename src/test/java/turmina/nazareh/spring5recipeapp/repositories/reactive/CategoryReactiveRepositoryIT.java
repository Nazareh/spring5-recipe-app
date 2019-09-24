package turmina.nazareh.spring5recipeapp.repositories.reactive;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import turmina.nazareh.spring5recipeapp.domain.Category;
import turmina.nazareh.spring5recipeapp.domain.Recipe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryIT {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception{
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testRecipeSave(){
        Category category = new Category();
        category.setDescription("Yummy");

        categoryReactiveRepository.save(category).block();

        assertEquals("1",categoryReactiveRepository.count().block().toString());
    }

    @Test
    public void testFindByDescription() throws Exception{
        Category category = new Category();
        category.setDescription("Foo");

        categoryReactiveRepository.save(category).then().block();

        Category fetchedCat = categoryReactiveRepository.findByDescription("Foo").block();

        assertNotNull(fetchedCat);
        assertEquals("Foo",fetchedCat.getDescription());
    }
}
