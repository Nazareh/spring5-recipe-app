package turmina.nazareh.spring5recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import turmina.nazareh.spring5recipeapp.commands.IngredientCommand;
import turmina.nazareh.spring5recipeapp.domain.Ingredient;
import turmina.nazareh.spring5recipeapp.domain.Recipe;
import turmina.nazareh.spring5recipeapp.domain.UnitOfMeasure;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    public final Long INGREDIENT_ID_VALUE = 1L;
    public final String INGREDIENT_DESCRIPTION = "some ingredient";
    public final BigDecimal INGREDIENT_AMOUNT = new BigDecimal(3);
    public final Long UOM_ID_VALUE = 2L;
    public final String UOM_DESCRIPTION = "some UOM";
    public final Recipe RECIPE =  new Recipe();

    public UnitOfMeasure uom;
    public IngredientToIngredientCommand converter;

    @Before
    public void setUp(){
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());

    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject (){
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void testConvertWithUom(){
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID_VALUE);
        uom.setDescription(UOM_DESCRIPTION);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID_VALUE);
        ingredient.setDescription(INGREDIENT_DESCRIPTION);
        ingredient.setAmount(INGREDIENT_AMOUNT);
        ingredient.setUnitOfMeasure(uom);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(INGREDIENT_ID_VALUE,ingredientCommand.getId());
        assertEquals(INGREDIENT_DESCRIPTION,ingredientCommand.getDescription());
        assertEquals(INGREDIENT_AMOUNT,ingredientCommand.getAmount());
        assertEquals(UOM_ID_VALUE,ingredientCommand.getUnitOfMeasureCommand().getId());

    }

    @Test
    public void testConvertWithNullUom() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(INGREDIENT_AMOUNT);
        ingredient.setDescription(INGREDIENT_DESCRIPTION);
        ingredient.setUnitOfMeasure(null);
        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);
        //then
        assertNull(ingredientCommand.getUnitOfMeasureCommand());
        assertEquals(INGREDIENT_ID_VALUE, ingredientCommand.getId());
        // assertEquals(RECIPE, ingredientCommand.get);
        assertEquals(INGREDIENT_AMOUNT, ingredientCommand.getAmount());
        assertEquals(INGREDIENT_DESCRIPTION, ingredientCommand.getDescription());
    }
}