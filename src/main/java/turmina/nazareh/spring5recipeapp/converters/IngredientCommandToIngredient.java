package turmina.nazareh.spring5recipeapp.converters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import turmina.nazareh.spring5recipeapp.commands.IngredientCommand;
import turmina.nazareh.spring5recipeapp.domain.Ingredient;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand,Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomCommandToUomConverter;

    @Autowired
    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomCommandToUomConverter = uomConverter;
    }

    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null)
            return null;
        Ingredient ingredient = new Ingredient();
        ingredient.setId(source. getId());
        ingredient.setDescription(source.getDescription());
        ingredient.setAmount(source.getAmount());
        ingredient.setUnitOfMeasure(uomCommandToUomConverter.convert(source.getUnitOfMeasureCommand()));

        return ingredient;
    }
}
