import java.util.ArrayList;

public class Treset {

    public static void main(String [] args){
        ArrayList<Card> deck = Rules.createDeck();
        ArrayList<Player> players = new ArrayList<Player>();

        for(int i=0;i<3;i++){
            players.add(new RandomPlayer(i,(ArrayList<Card>)deck.subList(i*10,(i+1)*10-1))); //Inicializacija igralcev
        }
        players.add(new HumanPlayer(3,(ArrayList<Card>)deck.subList(30,39)));

        ArrayList<Card> previousRound = new ArrayList<Card>();

        for(int i=0;i<10;i++){
            ArrayList<Card> onTable = new ArrayList<Card>();
            for(int j=0;i<4;j++)
                onTable.add(players.get(j).nextCard(previousRound,onTable));
            previousRound = onTable;
            onTable.clear();
        }
    }


}
