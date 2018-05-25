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
                printRound(j, onTable);
            }
            System.out.println("\nNew turn:");
            //printRound(order,onTable);
            order = Rules.changeOrder(order,onTable);//mogoce se dodat malo vec belezenja vsega nekam
            previousRound = onTable;
            onTable.clear();
        }
    }

    public static ArrayList<Card> deckPart(ArrayList<Card> deck, int i){
        return new ArrayList<Card>(deck.subList(i*10,(i+1)*10));
    }




    public static void printRound(int playerOnMove, ArrayList<Card> onTable) {

        System.out.print(playerOnMove);
        System.out.print("\t");
        System.out.println(onTable.get(playerOnMove));
    }


}
