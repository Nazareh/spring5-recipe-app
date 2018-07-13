package turmina.nazareh.spring5recipeapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import turmina.nazareh.spring5recipeapp.commands.UnitOfMeasureCommand;
import turmina.nazareh.spring5recipeapp.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure,UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {

        if(source == null)
            return null;

        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setDescription(source.getDescription());
        uomCommand.setId(source.getId());


        return uomCommand;
    }
}
