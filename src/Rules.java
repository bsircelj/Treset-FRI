import java.util.ArrayList;
import java.util.Collections;

public class Rules {

    public static ArrayList<Card> createDeck(){
        ArrayList<Card> deck = new ArrayList<Card>();
        Card.Color[] all = {Card.Color.Spade,Card.Color.Bastoni,Card.Color.Coppe,Card.Color.Denari};
        for(int i=1;i<=10;i++){
            for(Card.Color color:all)
                deck.add(new Card(i,color));
        }
        Collections.shuffle(deck);
        return deck;
    }

}
