import java.util.ArrayList;

public class RandomPlayer extends Player {

    public RandomPlayer(int id, ArrayList<Card> hand) {
        super(id, hand);
    }

    public Card nextCard(ArrayList<Card> previous,ArrayList<Card> table){
        int index = (int)Math.random()*(hand.size()-1);//@Alex popravi da barva
        Card one = hand.get(index);
        hand.remove(index);
        return one;
    }
}
