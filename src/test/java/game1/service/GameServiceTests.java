package game1.service;

import game1.domain.models.Difficulty;
import game1.domain.models.Game;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;



import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class GameServiceTests {

    private GameService repository ;
    private Game game1 = new Game(Difficulty.BEGINNER);
    private Game game2 = new Game(Difficulty.INTERMEDIATE);


//    @Test
//    public void GameService_GetCorrectId_ShouldReturnCorrectId() {
//
//      assertEquals("Beginner",expected.getId,actual.getId());
//      assertEquals("Intermediate",expected.getId,actual.getId());
//      assertEquals("Advanced",expected.getId,actual.getId());
//    }





    @Test
    public void repositoryAssignsUniqueIdsForEachNewGame() {
        String id1 = repository.store(game1);
        String id2 = repository.store(game2);

        assertNotEquals(id1, id2);
    }

}
