package game1.domain.models;

import game1.domain.models.Game;
import game1.domain.models.Difficulty;
import game1.domain.models.view.GameView;
import org.springframework.web.servlet.ModelAndView;


public class GameModelAndView  extends ModelAndView {

    /**
     * Constructs a new model and view with the given template name and
     * pre-populates the model with generic parameters required by templates.
     */
    public GameModelAndView(String templateName) {
        super(templateName);
        addObject("difficulties", Difficulty.values());
    }

    /**
     * Constructs a new model and view with the given game available for the template.
     */
    public GameModelAndView(String templateName, Game game) {
        this(templateName);
        addObject("gameView", new GameView(game));
    }
}
