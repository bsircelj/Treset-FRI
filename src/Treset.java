import java.util.ArrayList;
import java.util.List;

public class Treset {

    public static void main(String [] args){
        ArrayList<Card> deck = Rules.createDeck();
        ArrayList<Player> players = new ArrayList<Player>();

        for(int i=0;i<3;i++){
            players.add(new RandomPlayer(i, deckPart(deck,i))); //Inicializacija igralcev
        }
        players.add(new HumanPlayer(3, deckPart(deck,3)));

        ArrayList<Card> previousRound = new ArrayList<Card>();

        for(int i=0;i<10;i++){
            ArrayList<Card> onTable = new ArrayList<Card>();
            int [] order ={0,1,2,3};
            for(int j=0;j<4;j++)
                onTable.add(players.get(order[j]).nextCard(previousRound,onTable));//vsak vrze dol karto

            printRound(order,onTable);
            order = changeOrder(order,onTable);//mogoce se dodat malo vec belezenja vsega nekam
            previousRound = onTable;
            onTable.clear();
        }
    }

    public static ArrayList<Card> deckPart(ArrayList<Card> deck, int i){
        return new ArrayList<Card>(deck.subList(i*10,(i+1)*10));
    }


    public static int[] changeOrder(int [] oldOrder, ArrayList<Card> onTable){

        return null;//@Alex correct order of next round
    }

    public static void printRound(int[] order, ArrayList<Card> onTable){

        //print played cards and winner
    }

}
