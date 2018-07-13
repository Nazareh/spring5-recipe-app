package turmina.nazareh.spring5recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import turmina.nazareh.spring5recipeapp.commands.UnitOfMeasureCommand;
import turmina.nazareh.spring5recipeapp.domain.UnitOfMeasure;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private final String DESCRIPTION = "Pint";
    private final Long UOM_ID = 1L ;
    private UnitOfMeasureCommandToUnitOfMeasure converter;


    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullObject(){
        assertNull(converter.convert(null));

    }
    @Test
    public void testEmptyObject (){
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));

    }
    @Test
    public void convertTest(){

        //given
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setDescription(DESCRIPTION);
        uomCommand.setId(UOM_ID);

        //when
        UnitOfMeasure uom = converter.convert(uomCommand);

        //then
        assertEquals(DESCRIPTION,uom.getDescription());

        assertEquals(UOM_ID,uom.getId());

    }
}