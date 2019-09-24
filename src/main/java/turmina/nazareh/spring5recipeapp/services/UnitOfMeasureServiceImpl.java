package turmina.nazareh.spring5recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import turmina.nazareh.spring5recipeapp.commands.UnitOfMeasureCommand;
import turmina.nazareh.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import turmina.nazareh.spring5recipeapp.repositories.UnitOfMeasureRepository;
import turmina.nazareh.spring5recipeapp.repositories.reactive.UnitOfMeasureReactiveRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureReactiveRepository uomReactiveRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureReactiveRepository uomReactiveRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.uomReactiveRepository = uomReactiveRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Flux<UnitOfMeasureCommand> listAllUoms() {

        return uomReactiveRepository
                .findAll()
                .map(unitOfMeasureToUnitOfMeasureCommand::convert);
    }
}
