package game1.service;

import game1.domain.models.Game;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class GameServiceImpl implements GameService{

    private Map<String, Game> storedGames = new HashMap<String, Game>();

    /**
     *         given id
     */
    public boolean containsGame(String id) {
        return storedGames.containsKey(id);
    }

    /**
     * with the returned unique id.
     * @param newGame the game to store
     */
    public String store(Game newGame) {
        String uniqueId = UUID.randomUUID().toString();
        storedGames.put(uniqueId, newGame);
        return uniqueId;
    }

    /**
     * @return the game matching the id
     */
    public Game getGameById(String id) {
        Game game = storedGames.get(id);
        if (game == null) {
            try {
                throw new NotFoundException("No game found with id " + id);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return game;
    }

    /**
     * @return the number of games in the repository. May contain duplicates, if
     * the same {@link Game} object is stored multiple times.
     */
    public int getSize() {
        return storedGames.size();
    }
}
