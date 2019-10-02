package turmina.nazareh.spring5recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(NumberFormatException.class)
//    public ModelAndView handleNumberFormat (Exception e){
//        log.error("Handling bad request exception");
//        log.error(e.getMessage());
//
//        ModelAndView modelAndView = new ModelAndView();
//
//        modelAndView.setViewName("400error");
//        modelAndView.addObject("exception",e);
//
//        return modelAndView;
//
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NotFoundException.class)
//    public ModelAndView handleNotFound(Exception e) {
//
//        log.error("Handling not found exception");
//        log.error(e.getMessage());
//
//        ModelAndView modelAndView = new ModelAndView();
//
//        modelAndView.setViewName("404error");
//        modelAndView.addObject("exception", e);
//
//        return modelAndView;
//    }
}
