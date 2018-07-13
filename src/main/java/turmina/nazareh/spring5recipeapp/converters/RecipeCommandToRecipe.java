package turmina.nazareh.spring5recipeapp.converters;

import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import turmina.nazareh.spring5recipeapp.commands.RecipeCommand;
import turmina.nazareh.spring5recipeapp.domain.Recipe;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand,Recipe> {
    private CategoryCommandToCategory categoryConverter;
    private IngredientCommandToIngredient ingredientConverter;
    private NotesCommandToNotes notesConverter;

    @Autowired
    public RecipeCommandToRecipe(CategoryCommandToCategory categoryCommandToCategory,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 NotesCommandToNotes notesCommandToNotes) {
        this.categoryConverter = categoryCommandToCategory;
        this.ingredientConverter = ingredientCommandToIngredient;
        this.notesConverter = notesCommandToNotes;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {

        if (source == null)
            return null;

        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setDirections(source.getDirections());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setServings(source.getServings());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setDescription(source.getDescription());

        if (source.getNotes() != null ){
            recipe.setNotes(notesConverter.convert(source.getNotes()));
        }

        if(source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach( category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }

        if(source.getIngredients() != null && source.getCategories().size() >0){
            source.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }
}
