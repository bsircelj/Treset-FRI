import java.util.ArrayList;

public class State {
    int playerToMove;
    ArrayList<ArrayList<Card>> playerHands;
    ArrayList<Card> discards;
    char currentColor;
    int roundNo;
    ArrayList<Card> onTable;
    int[] roundWinner;
    int [] order;

    public State(){
        playerToMove = 0;
        discards = new ArrayList<Card>();//karte ki so bile igrane prej in niso vec v igri
        currentColor = 'N';//Brez barvanja -> miza je prazna
        playerHands = new ArrayList<ArrayList<Card>>();
        for(int i=0;i<4;i++){
           playerHands.add(new ArrayList<Card>());
        }
        roundNo = 0;
        onTable = new ArrayList<Card>();
        roundWinner = new int[10];
        int [] order2 = {0, 1, 2, 3};
        order = order2;

    }

    public State clone(){
        State clone = new State();
        clone.playerToMove = this.playerToMove;
        for(int i=0;i<4;i++){
            clone.playerHands.set(i,new ArrayList(this.playerHands.get(i)));
            clone.order[i] = this.order[i];
        }
        clone.discards = new ArrayList(discards);
        clone.currentColor = this.currentColor;
        clone.roundNo = this.roundNo;
        clone.onTable = new ArrayList(this.onTable);
        clone.roundWinner = this.roundWinner.clone();

        return clone;
    }

    public State cloneAndRandomize(){
        State st = this.clone();
        ArrayList<Card> deck = Rules.createDeck();
        deck.removeAll(discards);
        deck.removeAll(onTable);
        deck.removeAll(playerHands.get(playerToMove));

        int receiver=playerToMove;
        for(int i=0;i<4;i++){
            st.playerHands.get(i).clear();
        }
        for(int i=0;i<deck.size();i++){
            receiver=++receiver%4;
            if(receiver==playerToMove)
                receiver++;
            playerHands.get(receiver).add(deck.get(i));
        }

        return st;
    }

    public void doMove(Card playedCard){
        playerHands.get(playerToMove).remove(playedCard);
        onTable.add(playedCard);
        if(onTable.size()>3){
            int won = Rules.pickUp(this.onTable);
            order = Rules.changeOrder(order,onTable);
            currentColor = 'N';
            playerToMove = order[0];
            roundWinner[roundNo] = won;
            roundNo++;
        }else{
            playerToMove=(playerToMove+1)%4;
        }
    }

    public ArrayList<Card> getMoves(){
        ArrayList<Card> allMoves = new ArrayList<Card>();
        if(currentColor=='N'){
            return playerHands.get(playerToMove);
        }else{
            for(Card c:playerHands.get(playerToMove))
                if(c.color==currentColor)
                    allMoves.add(c);
            if(allMoves.size()==0)
                return playerHands.get(playerToMove);
        }
        return allMoves;
    }

}
