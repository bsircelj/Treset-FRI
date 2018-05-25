import java.util.ArrayList;
import java.util.TreeSet;

public class Node {

    int wins,visits,avails;

    ArrayList<Node> childNodes;
    Node parent;
    int playerJustMoved; //Kdo je na vrsti
    Card move; //Karto, ki jo igra kdor je na vrsti v tej potezi

    public Node(Card move, Node parent, int justMoved){
        this.wins = 0;
        this.visits = 0;
        this.avails = 1;
        this.playerJustMoved=justMoved;
        childNodes = new ArrayList<Node>();
        this.parent=parent;
        this.move=move;
    }

    public ArrayList<Card> getUntriedMoves(ArrayList<Card> legalMoves){
        ArrayList<Card> untried = new ArrayList<Card>();
        for(Node child:this.childNodes){
            if(!legalMoves.contains(child.move)){
                untried.add(child.move);
            }
        }

        return untried;
    }
    public Node UCBSelectChild(ArrayList<Card> legalMoves){
        return UCBSelectChild(legalMoves,0.7);
    }

    public Node UCBSelectChild(ArrayList<Card> legalMoves, double exploration){


        return null;
    }


}
