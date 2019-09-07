package turmina.nazareh.spring5recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import turmina.nazareh.spring5recipeapp.commands.CategoryCommand;
import turmina.nazareh.spring5recipeapp.domain.Category;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {
    public static final String ID_VALUE = "String";
    public static final String DESCRIPTION = "descript";
    CategoryToCategoryCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convertTest() throws Exception {
        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand categoryCommand = converter.convert(category);

        //then
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());

    }

}