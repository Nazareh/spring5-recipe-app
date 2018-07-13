package turmina.nazareh.spring5recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import turmina.nazareh.spring5recipeapp.domain.Difficulty;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Set<CategoryCommand> categories = new HashSet<>();
    private Integer cookTime;
    private Difficulty difficulty;
    private String directions;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private NotesCommand notes;
    private Integer prepTime;
    private Integer servings;
    private String source;
    private String url;
    private Byte[] image;
}
