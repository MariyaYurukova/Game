package game1.web.controllers;


import game1.*;
import game1.domain.models.Game;
import game1.domain.models.Difficulty;
import game1.domain.models.GameModelAndView;
import game1.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class GameController {

    private static final String GAME_TEMPLATE = "game";

    private final GameServiceImpl repository;

    @Autowired
    public GameController(GameServiceImpl repository) {
        this.repository = repository;
    }

    /**
     * and redirects the user to that game.
     * @param difficulty
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public RedirectView startNewGame(
            @RequestParam("difficulty") Difficulty difficulty) {

        Game newGame = new Game(difficulty);
        String gameId = repository.store(newGame);
        return new RedirectView("/game/" + gameId);
    }

    /**
     * be able to play the game on this page.
     *id The unique ID of the user's game
     */
    @RequestMapping(value = "/game/{gameId}", method = RequestMethod.GET)
    public ModelAndView showGame(@PathVariable("gameId") String id) throws NotFoundException {
        Game game = null;
        game = repository.getGameById(id);

        return new GameModelAndView(GAME_TEMPLATE, game);
    }

    /**
     * user back to the game view
     */
    @RequestMapping(value = "/game/{gameId}", method = RequestMethod.POST)
    public RedirectView openSquare(
            @PathVariable("gameId") String id,
            @RequestParam("row") int row,
            @RequestParam("col") int col) throws NotFoundException {

        Game game = null;
        game = repository.getGameById(id);
        if (!game.isOver()) {
            game.openSquare(row, col);
        }
        return new RedirectView("/game/" + id);
    }
}
