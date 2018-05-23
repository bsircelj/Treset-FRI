import java.util.ArrayList;
import java.util.TreeSet;

public class Node {

    int win,total;

    TreeSet<Node> children;
    Node parent;
    int player; //Kdo je na vrsti
    int myId;//Id igralca, ki izvaja search. da vemo če izbiramo mi ali kdo drug
    ArrayList<Card> onTable; //Karte igrane od ostalih to potezo
    Card card; //Karto, ki jo igra kdor je na vrsti v tej potezi
    ArrayList<Card> alreadyPlayed = new ArrayList<Card>();//Vse karte, ki so bile igrane od kogarkoli, bomo rabli za konstruiranje igre do konca
    ArrayList<ArrayList<Card.Color>> missingColor = new ArrayList<>();


    public Node(Node parent, int player,int myId, Card card, ArrayList<Card> onTable, ArrayList<Card> alreadyPlayed){
        this.parent=parent;
        children  = new TreeSet<Node>();
        win=total=0;
        this.player = player;
        this.card = card;
        this.onTable = onTable;
        this.alreadyPlayed=alreadyPlayed;
        for(int i=0;i<4;i++){
            missingColor.set(i,new ArrayList<Card.Color>());
        }
    }

    public ArrayList<Card.Color> getMissing(int playerId){//metoda ki vrne seznam barv, za katere vemo, da jih igralec nima
        if(playerId==myId){
            throw new Error("Sami zase vemo kaj imamo v roki. Klican je bil napacen id.");
        }
        return missingColor.get(playerId);
    }


}
