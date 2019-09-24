package turmina.nazareh.spring5recipeapp.services;

import reactor.core.publisher.Flux;
import turmina.nazareh.spring5recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;


public interface UnitOfMeasureService {
    Flux<UnitOfMeasureCommand> listAllUoms();
}
