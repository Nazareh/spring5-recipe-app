package turmina.nazareh.spring5recipeapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import turmina.nazareh.spring5recipeapp.commands.RecipeCommand;
import turmina.nazareh.spring5recipeapp.domain.Category;
import turmina.nazareh.spring5recipeapp.domain.Recipe;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe,RecipeCommand> {

    private CategoryToCategoryCommand categoryConverter;
    private IngredientToIngredientCommand ingredientConverter;
    private NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryToCategoryCommand,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 NotesToNotesCommand notesToNotesCommand) {
        this.categoryConverter = categoryToCategoryCommand;
        this.ingredientConverter = ingredientToIngredientCommand;
        this.notesConverter = notesToNotesCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null)
            return null;
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach((Category category) -> recipeCommand.getCategories().add(categoryConverter.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients()
                    .forEach(ingredient -> recipeCommand.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipeCommand;
    }
}
