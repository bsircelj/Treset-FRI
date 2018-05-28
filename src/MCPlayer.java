import java.util.ArrayList;

public class MCPlayer extends Player {
    Search search;

    public MCPlayer(int id, ArrayList<Card> hand) {
        super(id, hand);
        search = new Search();
    }

    public Card nextCard(ArrayList<Card> previous,ArrayList<Card> table){
        return search.nextCard(previous,table);
    }
}
