package game1.service;

import game1.domain.models.Game;
import javassist.NotFoundException;

public interface GameService {


    boolean containsGame(String id);
    String store(Game newGame);
    Game getGameById(String id);
    int getSize();
}
