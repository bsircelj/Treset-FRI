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

        int [] order ={0,1,2,3};
        for(int i=0;i<10;i++){
            ArrayList<Card> onTable = new ArrayList<Card>();

            for(int j=0;j<4;j++) {
                onTable.add(players.get(order[j]).nextCard(previousRound, onTable));//vsak vrze dol karto
                printRound(order, onTable);
            }
            //printRound(order,onTable);
            order = changeOrder(order,onTable);//mogoce se dodat malo vec belezenja vsega nekam
            previousRound = onTable;
            onTable.clear();
        }
    }

    public static ArrayList<Card> deckPart(ArrayList<Card> deck, int i){
        return new ArrayList<Card>(deck.subList(i*10,(i+1)*10));
    }


    public static int[] changeOrder(int [] oldOrder, ArrayList<Card> onTable){
        int lastPicked = Rules.pickUp(onTable);
        int[] newOrder = new int[4];
        for (int i = lastPicked; i < lastPicked + 4; i++ ) {
            newOrder[i - lastPicked] = i % 4;
        }
        return newOrder;//@Alex correct order of next round
    }

    public static void printRound(int[] order, ArrayList<Card> onTable){
        for (int i = 0; i<order.length; i++) {
            System.out.print(order[i]);
            System.out.print("\t");
            try {
                System.out.println(onTable.get(i));
            } catch (IndexOutOfBoundsException e) {
                System.out.println();
            }
        }
        //print played cards and winner
    }

}
