package turmina.nazareh.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
public class Category {

    private String id;
    private String description;
    private Set<Recipe> recipes;
}
