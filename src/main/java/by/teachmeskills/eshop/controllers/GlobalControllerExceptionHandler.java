package by.teachmeskills.eshop.controllers;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import static by.teachmeskills.eshop.utils.PagesPathEnum.ERROR_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.ERROR_PARAM;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler
    public ModelAndView handleAuthException(Exception e) {
        ModelMap modelMap = new ModelMap();
        ModelAndView modelAndView = new ModelAndView();
        modelMap.addAttribute(ERROR_PARAM.getValue(), e.getMessage());
        modelAndView.setViewName(ERROR_PAGE.getPath());
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
    }
}