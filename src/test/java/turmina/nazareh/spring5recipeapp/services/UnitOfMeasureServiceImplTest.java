package turmina.nazareh.spring5recipeapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import turmina.nazareh.spring5recipeapp.commands.UnitOfMeasureCommand;
import turmina.nazareh.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import turmina.nazareh.spring5recipeapp.domain.UnitOfMeasure;
import turmina.nazareh.spring5recipeapp.repositories.reactive.UnitOfMeasureReactiveRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand =  new UnitOfMeasureToUnitOfMeasureCommand();
    UnitOfMeasureService unitOfMeasureService;

    @Mock
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureReactiveRepository,unitOfMeasureToUnitOfMeasureCommand);

    }

    @Test
    public void listAllUoms() {

        //given
        Set<UnitOfMeasure> unitOfMeasureSet = new HashSet<>();

        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId("1L");
        unitOfMeasureSet.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId("2L");
        unitOfMeasureSet.add(uom2);

        when(unitOfMeasureReactiveRepository.findAll()).thenReturn(Flux.just(uom1,uom2));

        //when
        List<UnitOfMeasureCommand> unitOfMeasureCommands = unitOfMeasureService.listAllUoms().collectList().block();

        //then
        assertEquals(2,unitOfMeasureCommands.size());
        verify(unitOfMeasureReactiveRepository,times(1)).findAll();


    }
}