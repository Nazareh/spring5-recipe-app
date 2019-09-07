package turmina.nazareh.spring5recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import turmina.nazareh.spring5recipeapp.domain.Difficulty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private String id;
    @NotBlank
    @Size(min = 3, max = 255)private String description;

    @Min(1)
    @Max(999)
    private Integer cookTime;

    @NotBlank
    private String directions;

    @Min(1)
    @Max(999)
    private Integer prepTime;

    @Min(1)
    @Max(999)
    private Integer servings;

    @URL
    private String url;
    private Byte[] image;
    private String source;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private NotesCommand notes;
    private Difficulty difficulty;
    private Set<CategoryCommand> categories = new HashSet<>();
}
