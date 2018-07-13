package turmina.nazareh.spring5recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import turmina.nazareh.spring5recipeapp.commands.IngredientCommand;
import turmina.nazareh.spring5recipeapp.commands.UnitOfMeasureCommand;
import turmina.nazareh.spring5recipeapp.domain.Ingredient;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    public final BigDecimal AMOUNT = new BigDecimal("1");
    public final String DESCRIPTION = "Cheeseburger";
    public final Long ID_VALUE = new Long(1L);
    public final Long UOM_ID = new Long(2L);

    IngredientCommandToIngredient converter;

    @Before
    public void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject()  {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void testConvertWithUom() {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setDescription(DESCRIPTION);
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(UOM_ID);
        ingredientCommand.setUnitOfMeasureCommand(uomCommand);

        //when
        Ingredient ingredient = converter.convert(ingredientCommand);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
    }
    @Test
    public void convertWithNullUom(){
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());

    }

}