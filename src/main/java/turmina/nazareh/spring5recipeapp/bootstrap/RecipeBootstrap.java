package turmina.nazareh.spring5recipeapp.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import turmina.nazareh.spring5recipeapp.domain.*;
import turmina.nazareh.spring5recipeapp.repositories.CategoryRepository;
import turmina.nazareh.spring5recipeapp.repositories.RecipeRepository;
import turmina.nazareh.spring5recipeapp.repositories.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    private UnitOfMeasure teaspoonUom;
    private UnitOfMeasure tablespoonUom;
    private UnitOfMeasure cupUom;
    private UnitOfMeasure pinchUom;
    private UnitOfMeasure ounceUom;
    private UnitOfMeasure dashUom;
    private UnitOfMeasure unitUom;
    private UnitOfMeasure poundUom;
    private UnitOfMeasure pintUom;
    private String UomNotFoundMessage = "Unit of measure not found: ";

    @Autowired
    public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData(){
        loadUnitsOfMeasure();
        recipeRepository.save(getSpicyGrilledChickenTacos());
        recipeRepository.save(getPerfectGuacamoleRecipe());
    }
    private void loadUnitsOfMeasure (){
        teaspoonUom = unitOfMeasureRepository
                .findByDescription("Teaspoon")
                .orElseThrow(() ->new RuntimeException(UomNotFoundMessage + "Teaspoon"));
        tablespoonUom = unitOfMeasureRepository
                .findByDescription("Tablespoon")
                .orElseThrow(() ->new RuntimeException(UomNotFoundMessage + "Tablespoon"));
        cupUom = unitOfMeasureRepository
                .findByDescription("Cup")
                .orElseThrow(() ->new RuntimeException(UomNotFoundMessage + "Cup"));
        pinchUom = unitOfMeasureRepository
                .findByDescription("Pinch")
                .orElseThrow(() ->new RuntimeException(UomNotFoundMessage + "Pinch"));
        ounceUom = unitOfMeasureRepository
                .findByDescription("Ounce")
                .orElseThrow(() ->new RuntimeException(UomNotFoundMessage + "Ounce"));
        dashUom = unitOfMeasureRepository
                .findByDescription("Dash")
                .orElseThrow(() ->new RuntimeException(UomNotFoundMessage + "Dash"));
        unitUom = unitOfMeasureRepository
                .findByDescription("Unit")
                .orElseThrow(() ->new RuntimeException(UomNotFoundMessage + "Unit"));
        poundUom = unitOfMeasureRepository
                .findByDescription("Pound")
                .orElseThrow(() ->new RuntimeException(UomNotFoundMessage + "Pound"));
        pintUom = unitOfMeasureRepository
                .findByDescription("Pint")
                .orElseThrow(() ->new RuntimeException(UomNotFoundMessage + "Pint"));

    }

    private Recipe getSpicyGrilledChickenTacos(){
        Recipe recipe = new Recipe();
        recipe.setDescription("Spicy Grilled Chicken Tacos");
        recipe.setCookTime(15);
        recipe.setPrepTime(20);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setServings(4);
        setChickenRecipeIngredients(recipe);
        recipe.setCategories(getChickenRecipeCategories());
        recipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        recipe.setSource("Chicken");
        recipe.setNotes(getChickenRecipeNotes(recipe));
        //todo
       // recipe.setImage(getResourceImage("GrilledChickenTacos.jpg"));
        recipe.setDirections(
            "1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
            "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, " +
            "oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil " +
            "to make a loose paste. Add the chicken to the bowl and toss to coat all over. Set aside to " +
            "marinate while the grill heats and you prepare the rest of the toppings.\n"+
            "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer " +
            "inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n"+
            "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over " +
            "medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, " +
            "turn it with tongs and heat for a few seconds on the other side. Wrap warmed tortillas in a " +
            "tea towel to keep them warm until serving.\n" +
            "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful " +
            "of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. " +
            "Drizzle with the thinned sour cream. Serve with lime wedges.");

    return recipe;
    }

    private  Notes getChickenRecipeNotes (Recipe recipe){
        Notes notes = new Notes();
        notes.setRecipe(recipe);
        notes.setRecipeNote(
                "Look for ancho chile powder with the Mexican ingredients at your grocery store, " +
                "on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, " +
                "the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the " +
                "flavor won't be quite the same.)");
        return notes;
    }

    private Set<Category> getChickenRecipeCategories(){
        Set<Category> categories = new HashSet();

        categories.add(categoryRepository
                .findByDescription("Mexican")
                .orElseThrow(() -> new RuntimeException("Category not found!")));
        return categories;
    }

    private void setChickenRecipeIngredients(Recipe recipe){

        recipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1) , teaspoonUom));
        recipe.addIngredient(new Ingredient("Dried cumin",  new BigDecimal(1) , teaspoonUom));
        recipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1) , teaspoonUom));
        recipe.addIngredient(new Ingredient("Salt", new BigDecimal(1/2) , teaspoonUom));
        recipe.addIngredient(new Ingredient("Garlic clove finely chopped", new BigDecimal(2) , unitUom));
        recipe.addIngredient(new Ingredient("Grated Orange Zest",  new BigDecimal(1) ,tablespoonUom));
        recipe.addIngredient(new Ingredient("Fresh Orange Juice",  new BigDecimal(3) ,tablespoonUom));
        recipe.addIngredient(new Ingredient("Olive Oil",  new BigDecimal(2) ,tablespoonUom));
        recipe.addIngredient(new Ingredient("Skinless, boneless chicken thighs",  new BigDecimal(1+ 1/4),poundUom));
        recipe.addIngredient(new Ingredient("Small corn tortillas", new BigDecimal(8) , unitUom));
        recipe.addIngredient(new Ingredient("Packed baby arugula", new BigDecimal(3) ,ounceUom));
        recipe.addIngredient(new Ingredient("Sliced medium ripe avocados", new BigDecimal(2) ,unitUom));
        recipe.addIngredient(new Ingredient("Thinly sliced radishes", new BigDecimal(4) ,unitUom));
        recipe.addIngredient(new Ingredient("Halved cherry tomatoes", new BigDecimal(1/2) ,pintUom));
        recipe.addIngredient(new Ingredient("Thinly sliced red onion", new BigDecimal(1/4) ,pintUom));
        recipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(1) ,unitUom));
        recipe.addIngredient(new Ingredient("Sour cream", new BigDecimal(1/2) ,cupUom));
        recipe.addIngredient(new Ingredient("Milk", new BigDecimal(1/4) ,cupUom));
        recipe.addIngredient(new Ingredient("Lime, cut into wedges", new BigDecimal(1) ,unitUom));

    }

    /*
    private Byte[] getResourceImage (String imagePath) {
        Byte[] image;
        try {
            File file = new File(imagePath);
            image =  ArrayUtils.toObject(Files.readAllBytes(file.toPath()));
         //   ArrayUtils. toObject(bytes);
        }
        catch (IOException e){
            System.out.println("WARNING: unable to read image :"+ imagePath);
            image = null;
        }
        return image;
    }*/

    //todo
    private Recipe getPerfectGuacamoleRecipe (){
        Recipe recipe = new Recipe();
        recipe.setDescription("Perfect Guacamole");
        recipe.setCookTime(0);
        recipe.setPrepTime(10);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setServings(2);
        setPerfectGuacamoleIngredients(recipe);
        recipe.setCategories(getPerfectGuacamoleCategories());
        recipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        recipe.setSource("Vegan");
        recipe.setNotes(getPerfectGuacamoleNotes());
        recipe.setDirections(
                "1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of " +
                "the avocado with a blunt knife and scoop out the flesh with a spoon. Place in a bowl.\n"+
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! " +
                "The guacamole should be a little chunky.)\n"+
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. " +
                "The acid in the lime juice will provide some balance to the richness of the avocado " +
                "and will help delay the avocados from turning brown. Add the chopped onion, cilantro, " +
                "black pepper, and chiles. Chili peppers vary individually in their hotness. So, start " +
                "with a half of one chili pepper and add to the guacamole to your desired degree of " +
                "hotness. Remember that much of this is done to taste because of the variability in " +
                "the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the " +
                "guacamole cover it and to prevent air reaching it. (The oxygen in the air causes " +
                "oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your " +
                "guacamole, add it just before serving.");
        return recipe;
    }

    private void setPerfectGuacamoleIngredients(Recipe recipe) {

        recipe.addIngredient(new Ingredient("Ripe avocados",new BigDecimal(2),unitUom));
        recipe.addIngredient(new Ingredient("Kosher Salt",new BigDecimal(1/2),teaspoonUom));
        recipe.addIngredient(new Ingredient("Fresh lime juice or lemon juice",new BigDecimal(1),tablespoonUom));
        recipe.addIngredient(new Ingredient("Minced red onion or thinly sliced green onion",new BigDecimal(1/4),cupUom));
        recipe.addIngredient(new Ingredient("Serrano chilies, stems and seeds removed, minced",new BigDecimal(2),unitUom));
        recipe.addIngredient(new Ingredient("Cilantro (leaves and tender stems), finely chopped",new BigDecimal(2),tablespoonUom));
        recipe.addIngredient(new Ingredient("Freshly grated black pepper",new BigDecimal(2),dashUom));
        recipe.addIngredient(new Ingredient("Ripe tomato, seeds and pulp removed, chopped",new BigDecimal(1/2),unitUom));

    }

    private  Notes getPerfectGuacamoleNotes (){
        Notes notes = new Notes();
        notes.setRecipeNote(
                "Be careful handling chiles if using. Wash your hands thoroughly after handling " +
                "and do not touch your eyes or the area near your eyes with your hands for several hours.");
        return notes;
    }

    private Set<Category> getPerfectGuacamoleCategories(){
        Set<Category> categories = new HashSet();

        categories.add(categoryRepository
                .findByDescription("Mexican")
                .orElseThrow(() -> new RuntimeException("Category not found!")));
        return categories;
    }

}
