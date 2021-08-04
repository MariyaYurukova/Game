package game1.web.controllers;

import game1.domain.models.GameModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FrontPageController {

    @RequestMapping("/")
    public ModelAndView renderFrontPage() {
        return new GameModelAndView("index");
    }
}
