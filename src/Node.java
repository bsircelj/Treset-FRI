import java.util.ArrayList;

public class Node {

    int win,lose,total;

    ArrayList<Node> children;
    Node parent;
    int player; //Kdo je na vrsti
    ArrayList<Card> onTable; //Karte igrane od ostalih to potezo
    Card card; //Karto, ki jo igram v tej potezi

    public void Node(Node parent, int player, Card card, ArrayList<Card> onTable){
        this.parent=parent;
        children  = new ArrayList<Node>();
        win=lose=total=0;
        this.player = player;
        this.card = card;
        this.onTable = onTable;
    }


}
