package turmina.nazareh.spring5recipeapp.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import turmina.nazareh.spring5recipeapp.commands.IngredientCommand;
import turmina.nazareh.spring5recipeapp.converters.IngredientCommandToIngredient;
import turmina.nazareh.spring5recipeapp.converters.IngredientToIngredientCommand;
import turmina.nazareh.spring5recipeapp.domain.Ingredient;
import turmina.nazareh.spring5recipeapp.domain.Recipe;
import turmina.nazareh.spring5recipeapp.repositories.RecipeRepository;
import turmina.nazareh.spring5recipeapp.repositories.UnitOfMeasureRepository;

import java.util.Optional;
import java.util.Set;

import static java.rmi.server.LogStream.log;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()){
            //todo impl error handling
            IngredientServiceImpl.log.error("recipe id not found, id: "+ recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if (!ingredientCommandOptional.isPresent()){
            //todo impl error handling
            IngredientServiceImpl.log.error("ingredient id not found, id: "+ ingredientId);

        }

        return ingredientCommandOptional.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional =  recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){
            //todo toss error if not found!
            IngredientServiceImpl.log.error("Recipe not found for id: "+ command.getRecipeId());
            return new IngredientCommand();
        }
        else{
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe.getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setDescription(command.getDescription());

                ingredientFound.setUom(unitOfMeasureRepository
                        .findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOOM NOT FOUND")));//todo address this

            }else {
                Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredientCommandToIngredient.convert(command));
            }

            Recipe savedRecipe =  recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();

                        //check by description
            if(!savedIngredientOptional.isPresent()){
                //not totally safe..but best guess
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }

            //todo check for fail
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        }
    }

    @Override
    @Transactional
    public void deleteById(String recipeId, String ingredientId) {

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("RECIPE "+recipeId+"  NOT FOUND"));


        log.debug("list initial size = " + recipe.getIngredients().size());
        Set<Ingredient> recipeIngredients = recipe.getIngredients();

        Ingredient ingredientToBeDelete = recipeIngredients
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ingredient "+ ingredientId + "  NOT FOUND"));
        ingredientToBeDelete.setRecipe(null);
        recipeIngredients.removeIf( ingredient -> ingredient.getId().equals(ingredientToBeDelete.getId()));
        recipe.setIngredients(recipeIngredients);
        log.debug("list final size = " + recipe.getIngredients().size());
        recipeRepository.save(recipe);

    }
}
