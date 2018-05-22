import java.util.ArrayList;

public class RandomPlayer extends Player {

    public RandomPlayer(int id, ArrayList<Card> hand, String controller) {
        super(id, hand, controller);
    }

    @Override
    public Card nextCard(ArrayList<Card> previous,ArrayList<Card> table){
        int index = (int)Math.random()*(hand.size()-1);
        Card one = hand.get(index);
        hand.remove(index);
        return one;
    }
}
