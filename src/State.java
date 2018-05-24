import java.util.ArrayList;

public class State {
    int playerToMove;
    ArrayList<ArrayList<Card>> playerHands;
    ArrayList<Card> discards;
    Card.Color currentColor;

    public State(){
        playerToMove = 0;
        discards = new ArrayList<Card>();
        currentColor = null;
        playerHands = new ArrayList<ArrayList<Card>>();
        for(int i=0;i<4;i++){
           playerHands.add(new ArrayList<Card>());
        }

    }

    public State clone(){
        State clone = new State();
        clone.playerToMove = this.playerToMove;
        for(int i=0;i<4;i++){
            clone.playerHands.set(i,new ArrayList(this.playerHands.get(i)));
        }
        clone.discards = new ArrayList(discards);
        //clone.currentColor = new Card.Color(currentColor);

        return clone;
    }

}
