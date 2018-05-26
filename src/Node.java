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
        ArrayList<Node> legalChildren = new ArrayList<Node>();
        for(Node child:this.childNodes){
            if(legalMoves.contains(child.move)){
                legalChildren.add(child);
            }
        }
        double score = 0;
        Node maxChild = null;
        for(Node child:legalChildren){
            double curScore = child.wins/child.visits + exploration*Math.sqrt(Math.log(child.avails)/child.visits);
            if(curScore>score){
                score=curScore;
                maxChild=child;
            }
            child.avails++;
        }

        return maxChild;
    }

    public Node addChild(Card move, int p){
        Node n = new Node(move,this,p);
        this.childNodes.add(n);
        return n;
    }

    public void Update(State terminalState){
        //Update this node - increment the visit count by one, and increase the win count by the result of terminalState for self.playerJustMoved.
        this.visits +=1;
        this.wins += terminalState.getResult(this.playerJustMoved);
    }

    public String treeToString(int indent){
        String s = this.indentString(indent) + this.toString();
        for (int i =0;i < this.childNodes.size(); i++){
            s += childNodes.get(i).treeToString(indent + 1);
        }
    return s;

    }

    public String indentString(int indent){
        String s = "\n";
        for(int i =0; i < indent + 1; i++){
            s += "| ";
        }
        return s;
    }

    public String chilrenToString(){
        String s = "";
        for (int i =0;i < this.childNodes.size(); i++){
            s += childNodes.get(i).toString() + "\n";
        }
        return s;
    }


}
