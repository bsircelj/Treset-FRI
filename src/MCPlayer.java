import java.util.ArrayList;
import java.util.Random;

public class MCPlayer extends Player {

    public MCPlayer(int id, ArrayList<Card> hand) {
        super(id, hand);
    }

    public Card nextCard(State state){
        Card chosen = ISMCTS(state,10);
        hand.remove(chosen);
        return chosen;
    }

    public Card ISMCTS(State rootstate, int itermax){
        return ISMCTS(rootstate,itermax,false);
    }

    public Card ISMCTS(State rootstate, int itermax, boolean verbose){
        Node rootnode = new Node();

        for(int iter=0;iter<itermax;iter++){
            Node node = rootnode;

            Random random = rootstate.random;

            //Determinize
            State state = rootstate.cloneAndRandomize();

            //Select
            while(!state.getMoves().isEmpty()&&node.getUntriedMoves(state.getMoves()).isEmpty()){//optimizacija: samo 1x se klice getMoves
                node = node.UCBSelectChild(state.getMoves());
                state.doMove(node.move);
            }

            //Expand
            ArrayList<Card> untriedMoves = node.getUntriedMoves(state.getMoves());
            if(!untriedMoves.isEmpty()){
                Card m = untriedMoves.get((random.nextInt(untriedMoves.size())));
                int player = state.playerToMove;
                state.doMove(m);
                node = node.addChild(m,player);
            }

            //Simulate
            while(true){
                ArrayList<Card> moves = state.getMoves();
                if(moves.isEmpty())
                    break;
                state.doMove(moves.get(random.nextInt(moves.size())));//lahko se dodamo neko hevristiko
            }

            //Backpropagate
            while(node!=null){
                node.Update(state);
                node = node.parent;
            }

        }
        if(verbose){
            System.out.print(rootnode.treeToString(0));
        }else{
            System.out.print(rootnode.chilrenToString());
        }
        Node result = rootnode.childNodes.get(0);
        for(Node all:rootnode.childNodes){
            if(all.visits>result.visits){
                result=all;
            }
        }

        return result.move;
    }
}
