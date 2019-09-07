package turmina.nazareh.spring5recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import turmina.nazareh.spring5recipeapp.commands.RecipeCommand;
import turmina.nazareh.spring5recipeapp.converters.RecipeCommandToRecipe;
import turmina.nazareh.spring5recipeapp.converters.RecipeToRecipeCommand;
import turmina.nazareh.spring5recipeapp.domain.Recipe;
import turmina.nazareh.spring5recipeapp.exceptions.NotFoundException;
import turmina.nazareh.spring5recipeapp.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {


    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private  final RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the service");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return  recipes;

    }

    @Override
    public Recipe findById(String id) {
        return recipeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe not found. For ID value: "+ id));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand (RecipeCommand recipeCommand ){
        Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);

        Recipe savedRecipe = recipeRepository.save(recipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);

    }

    @Override
    public RecipeCommand findCommandById(String id) {

        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    public void deleteById(String id) {
        recipeRepository.deleteById(id);
    }
}
