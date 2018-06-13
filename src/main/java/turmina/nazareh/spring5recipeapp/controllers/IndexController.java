package turmina.nazareh.spring5recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/","", "index"} )
    public String getIndexPage(){

        System.out.println("not much to say....khkhj");
        return "index";
    }
}
