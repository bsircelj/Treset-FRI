import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer extends Player {

    public RandomPlayer(int id, ArrayList<Card> hand) {
        super(id, hand);
    }

    public Card nextCard(State state){
        Random random = state.random;
        ArrayList<Card> moves = state.getMoves();
        return moves.get(random.nextInt(moves.size()));
    }
}
