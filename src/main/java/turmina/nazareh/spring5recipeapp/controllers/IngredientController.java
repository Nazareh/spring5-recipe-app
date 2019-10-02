package turmina.nazareh.spring5recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import turmina.nazareh.spring5recipeapp.commands.IngredientCommand;
import turmina.nazareh.spring5recipeapp.commands.RecipeCommand;
import turmina.nazareh.spring5recipeapp.commands.UnitOfMeasureCommand;
import turmina.nazareh.spring5recipeapp.services.IngredientService;
import turmina.nazareh.spring5recipeapp.services.RecipeService;
import turmina.nazareh.spring5recipeapp.services.UnitOfMeasureService;

@Slf4j
@Controller
public class IngredientController {
    private RecipeService recipeService;
    private IngredientService ingredientService;
    private UnitOfMeasureService unitOfMeasureService;

    @Autowired
    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String showIngredients(@PathVariable String id, Model model){

        log.debug("Getting ingredient list for recipe id:" + id);

        model.addAttribute("recipe",recipeService.findCommandById(id));

        return "/recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model){

        log.debug("Getting ingredient list for recipe id:" + id);
        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(recipeId,id));
        return "/recipe/ingredient/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newRecipe(@PathVariable String recipeId, Model model){

        //make sure we have a good id value
        RecipeCommand recipeCommand = recipeService.findCommandById(recipeId).block();
        //todo raise exception is null

        //need to return back parent id for hidden form property
        IngredientCommand  ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeId);
        model.addAttribute("ingredient",ingredientCommand);

        //init uom
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList",unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }
    @GetMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model){
        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(recipeId,id).block());

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";

    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate (@ModelAttribute IngredientCommand command){

        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command).block();

            log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteAction (@PathVariable String recipeId, @PathVariable String ingredientId){
        log.debug("Deleting ingredient "+ ingredientId + " from recipe "+ recipeId);

        ingredientService.deleteById(recipeId,ingredientId).block() ;
        return "redirect:/recipe/"+ recipeId+"/ingredients";

    }

}
