package turmina.nazareh.spring5recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import turmina.nazareh.spring5recipeapp.commands.UnitOfMeasureCommand;
import turmina.nazareh.spring5recipeapp.domain.UnitOfMeasure;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private final String DESCRIPTION = "cups";
    private final String UOM_ID = "1L";
    private UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp(){
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullParameter(){

        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject (){
        assertNotNull(converter.convert(new UnitOfMeasure()));

    }
    @Test
    public void testConvert(){
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription(DESCRIPTION);
        uom.setId(UOM_ID);

        //when
        UnitOfMeasureCommand uomCommand = converter.convert(uom);

        //then
        assertEquals(DESCRIPTION,uomCommand.getDescription());
        assertEquals(UOM_ID,uomCommand.getId());

    }

}