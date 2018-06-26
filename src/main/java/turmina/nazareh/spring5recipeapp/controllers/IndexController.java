package turmina.nazareh.spring5recipeapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import turmina.nazareh.spring5recipeapp.domain.Category;
import turmina.nazareh.spring5recipeapp.domain.UnitOfMeasure;
import turmina.nazareh.spring5recipeapp.repositories.CategoryRepository;
import turmina.nazareh.spring5recipeapp.repositories.UnitOfMeasureRepository;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"/","", "index"} )
    public String getIndexPage(){

        Optional<Category> category = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Pinch");

        System.out.println("category = " + category.get().getId());
        System.out.println("unitOfMeasure = " + unitOfMeasure.get().getId());

        return "index";
    }
}
