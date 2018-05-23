import java.util.ArrayList;

public class Search {

    Node root;
    int myId;
    public Search(int myId){
        this.myId = myId;//Id od igralca, ki izvaja search

    }

    public Card nextCard(ArrayList<Card> previous, ArrayList<Card> table){

        //if first time, initialise root
        //build prediction tree
        //simulate
        //after n seconds return best card
        if(root==null){
            initialise();
        }

        return null;
    }

    public void initialise(){
        root = new Node();
    }


}
