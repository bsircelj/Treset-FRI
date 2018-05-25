import java.util.ArrayList;
import java.util.Collections;

public class Rules {

    public static ArrayList<Card> createDeck(){//Naredi nov deck in ga zmesa
        ArrayList<Card> deck = new ArrayList<Card>();
        char[] all = {'S','B','C','D'};
        for(int i=1;i<=10;i++){
            for(char color:all)
                deck.add(new Card(i,color));
        }
        Collections.shuffle(deck);
        return deck;
    }

    public static int pickUp(ArrayList<Card> table){//Kdo pobira trenuno rundo
        int which=0;                         //Vrne indeks zmagovalne karte, glede na vrstni red vhoda, prva karta je vedno prva igrana
        char firstColor = table.get(0).color;
        for(int i=1;i<4;i++){
            if(table.get(i).color==firstColor)
                if(table.get(i).rank>table.get(0).rank)
                    which=i;
        }
        return which;
    }

    public static int[] changeOrder(int [] oldOrder, ArrayList<Card> onTable){
        int lastPicked = Rules.pickUp(onTable);
        int[] newOrder = new int[4];
        for (int i = lastPicked; i < lastPicked + 4; i++ ) {
            newOrder[i - lastPicked] = i % 4;
        }
        return newOrder;
    }

    public static int simulate(Node node, int playerId){
        int points=0; //0=lost


        return points;//Simulacija do konca, ko bomo meli narejene node. bomo na random in tudi na "pametno" izbiranje kart
    }

}
