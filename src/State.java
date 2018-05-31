import java.util.ArrayList;
import java.util.Random;

public class State {
    int playerToMove;
    ArrayList<ArrayList<Card>> playerHands;
    ArrayList<Card> discards;
    char currentColor;
    int roundNo;
    ArrayList<Card> onTable;
    int[] roundWinner;
    int [] order;
    Random random;

    public State(){
        playerToMove = 0;
        discards = new ArrayList<Card>();//karte ki so bile igrane prej in niso vec v igri
        currentColor = 'N';//Brez barvanja -> miza je prazna

        //random = new Random();
        random = new Random((long)1212993.500322);//Za testirat boljse, da vedno izbere isto vrednost, ko bomo vedli da dela bomo dali na 'truly random' v zgornji vrstici
        ArrayList<Card> deck = Rules.createDeck(random);

        playerHands = new ArrayList<ArrayList<Card>>();
        for(int i=0;i<4;i++){
           playerHands.add(Rules.deckPart(deck,i));
        }
        roundNo = 0;
        onTable = new ArrayList<Card>();
        roundWinner = new int[10];
        int [] order2 = {0, 1, 2, 3};
        order = order2;
    }

    public State clone(){//lahko malo optimiziramo, da ne dela vedno vseh vrednosti, ki jih pol samo povozi
        State clone = new State();
        clone.playerToMove = this.playerToMove;
        clone.playerHands.clear();
        for(int i=0;i<4;i++){
            clone.playerHands.add(new ArrayList<Card>());
            for(Card c:this.playerHands.get(i))
                clone.playerHands.get(i).add(c.clone());
            clone.order[i] = this.order[i];
        }
        clone.discards.clear();

        for(Card c:this.discards)
            clone.discards.add(c.clone());

        clone.currentColor = this.currentColor;
        clone.roundNo = this.roundNo;

        clone.onTable.clear();
        for(Card c:this.onTable)
            clone.onTable.add(c.clone());

        clone.roundWinner = this.roundWinner.clone();
        clone.random = this.random;

        return clone;
    }

    public State cloneAndRandomize(){
        State st = this.clone();
        ArrayList<Card> deck = Rules.createDeck(random);
        deck.removeAll(discards);
        deck.removeAll(onTable);
        deck.removeAll(playerHands.get(playerToMove));

        int receiver=playerToMove;
        for(int i=0;i<4;i++){
            if(i==playerToMove)
                continue;
            st.playerHands.get(i).clear();
        }
        for(int i=0;i<deck.size();i++){
            receiver=++receiver%4;
            if(receiver==playerToMove)
                receiver=(receiver+1)%4;
            st.playerHands.get(receiver).add(deck.get(i));
        }

        return st;
    }

    public void doMove(Card playedCard){
        playerHands.get(playerToMove).remove(playedCard);
        onTable.add(playedCard);
        if(currentColor=='N')
            currentColor=playedCard.color;
        if(onTable.size()>3){
            int won = Rules.pickUp(this.onTable,order);
            order = Rules.changeOrder(order,onTable);
            currentColor = 'N';
            playerToMove = order[0];
            roundWinner[roundNo] = won;
            roundNo++;
            discards.addAll(onTable);
            onTable.clear();
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

    public int getResult(int player){

        return 0;//@Alex dodaj tockovanje, to se klice samo na koncen state. 0 ce smo zgubili cene tocke, player je za koga stejemo tocke
    }

    public double getScore(ArrayList<Card> table, int [] order){
        int which = State.pickUp(table, order);
        double score = 0;
        for (int i = 0; i < order.length; i++){
            score += table.get(i).Value();
        }
        return score;
    }

}
