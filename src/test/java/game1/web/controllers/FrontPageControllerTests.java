package game1.web.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrontPageControllerTests {

    @Test
    public void frontPageRendersTheIndexTemplate(){
        ModelAndView response = new FrontPageController().renderFrontPage();

        assertEquals("index", response.getViewName());
    }
}
