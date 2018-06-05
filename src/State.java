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
    double [] playerPoints;
    Random random;
    ArrayList<ArrayList<Character>> missingColor;

    public State(){
        playerToMove = 0;
        discards = new ArrayList<Card>();//karte ki so bile igrane prej in niso vec v igri
        currentColor = 'N';//Brez barvanja -> miza je prazna

        //random = new Random();
        random = new Random((long)1212993.500322);//Za testirat boljse, da vedno izbere isto vrednost, ko bomo vedli da dela bomo dali na 'truly random' v zgornji vrstici
        ArrayList<Card> deck = Rules.createDeck(random);

        playerHands = new ArrayList<ArrayList<Card>>();
        double [] playerPoints2 = {0, 0, 0, 0};
        playerPoints = playerPoints2;
        missingColor = new ArrayList<>();
        for(int i=0;i<4;i++){
           playerHands.add(Rules.deckPart(deck,i));
           missingColor.add(new ArrayList<Character>());
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
        clone.missingColor.clear();
        for(int i=0;i<4;i++){
            clone.playerHands.add(new ArrayList<Card>());
            for(Card c:this.playerHands.get(i))
                clone.playerHands.get(i).add(c.clone());
            clone.order[i] = this.order[i];
            clone.playerPoints[i] = this.playerPoints[i];


            clone.missingColor.add(new ArrayList<Character>());
            for(Character c:this.missingColor.get(i))
                clone.missingColor.get(i).add(c.charValue());
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
        Rules.removeAll(deck,discards);
        Rules.removeAll(deck,onTable);
        Rules.removeAll(deck,playerHands.get(playerToMove));

        int receiver=playerToMove;
        for(int i=0;i<4;i++){
            if(i==playerToMove)
                continue;
            st.playerHands.get(i).clear();
        }
        int [] howMany ={0,0,0,0};
        for(int i=0;i<deck.size();i++){
            receiver=++receiver%4;
            if(receiver==playerToMove)
                receiver=(receiver+1)%4;
            howMany[receiver]++;
        }
        for(int i=0;i<4;i++){
            int index=0;
            while(deck.size()>0&&howMany[i]>0){
                if(!containsChar(missingColor.get(i),deck.get(index).color)){
                    st.playerHands.get(i).add(deck.get(index));
                    howMany[i]--;
                }else{
                    index++;
                }
            }
        }

        return st;
    }




    public void doMove(Card playedCard){
        if(currentColor!='N'&&playedCard.color!=currentColor&&!missingColor.get(playerToMove).contains(currentColor))
            missingColor.get(playerToMove).add(currentColor);
        Rules.remove(playerHands.get(playerToMove),playedCard);
        onTable.add(playedCard);
        if(currentColor=='N')
            currentColor=playedCard.color;
        if(onTable.size()>3){
            int won = Rules.pickUp(this.onTable,order);
            currentColor = 'N';
            assignScore(onTable, order, roundNo);
            order = Rules.changeOrder(order,onTable);
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

        if(this.playerPoints[player] > 5) {
            return 1;
        } else
            return 0;
    }

    public void assignScore(ArrayList<Card> table, int [] order, int turn){
        int which = Rules.pickUp(table, order);
        double score = 0;
        for (Card i: table){
            score += i.value();
        }
        int coPlayer = (which + 2) % 4;
        this.playerPoints[which] += score;
        this.playerPoints[coPlayer] += score;

        if(turn == 9){ //Če je zadnji turn, zaokroži dobljen rezultat navzdol in zadnjemu paru ki je pobral turn dodaj +1
            int opponent1 = (which + 1) % 4;
            int opponent2 = (which + 3) % 4;

            this.playerPoints[which] += 1;
            this.playerPoints[coPlayer] += 1;
            this.playerPoints[which] = Math.floor(this.playerPoints[which]);
            this.playerPoints[coPlayer] = Math.floor(this.playerPoints[coPlayer]);
            this.playerPoints[opponent1] = Math.floor(this.playerPoints[opponent1]);
            this.playerPoints[opponent2] = Math.floor(this.playerPoints[opponent2]);
        }


    }

    public boolean containsChar(ArrayList<Character> colors, char c){
        for(char each:colors){
            if(each==c)
                return true;
        }
        return false;
    }

}
