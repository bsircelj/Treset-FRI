import java.util.ArrayList;

public class Treset {

    public static void main(String [] args){
    ArrayList<Card> deck = Rules.createDeck();
    ArrayList<Player> players = new ArrayList<Player>();

    for(int i=0;i<4;i++){
        players.add(new Player(i,(ArrayList<Card>)deck.subList(i*10,(i+1)*10-1),"computer")); //Inicializacija igralcev
    }


    }


}
