import java.util.ArrayList;
import java.util.Random;

public class MCPlayer extends Player {

    boolean verbose;

    public MCPlayer(int id, ArrayList<Card> hand) {
        super(id, hand);
    }


    public Card nextCard(State state){
        int i=0;
        for (int j = 0; j < 4; j++){
            for (int k = 0; k < 4; k++) {
                if ()
                state.missingColor.get(j).get(k)
            }
        }
        System.out.print("\tMC player:"+id+"\n\t");
        for(Card c:hand){
            String msg = "[" + i++ + "] " + c + ", ";
            ColorPrint.hand(msg);
        }
        System.out.println();

        Card chosen = ISMCTS(state,2000, true);
        Rules.remove(this.hand,chosen);
        return chosen;
    }

    public Card ISMCTS(State rootstate, int itermax){
        return ISMCTS(rootstate,itermax,false);
    }

    public Card ISMCTS(State rootstate, int itermax, boolean verbose){
        Node rootnode = new Node();

        //for(int iter=0;iter<itermax;iter++){
        long startTime = System.currentTimeMillis();
        int i=0;
        while(true){
            if(System.currentTimeMillis()-startTime>itermax)
                break;
                i++;
            Node node = rootnode;

            Random random = rootstate.random;

            //Determinize
            State state = rootstate.cloneAndRandomize();

            //Select
            while(true){
                ArrayList<Card> possibleMoves = state.getMoves();
                ArrayList<Card> untriedMoves = node.getUntriedMoves(possibleMoves);
                if(possibleMoves.isEmpty()||!untriedMoves.isEmpty())
                    break;

                node = node.UCBSelectChild(possibleMoves);
                state.doMove(node.move);
            }

            //Expand
            ArrayList<Card> untriedMoves = node.getUntriedMoves(state.getMoves());
            if(!untriedMoves.isEmpty()){
                Card m = untriedMoves.get((random.nextInt(untriedMoves.size())));
                int player = state.playerToMove;
                state.doMove(m);
                Rules.remove(untriedMoves,m);
                node = node.addChild(m,player); /////////////////
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
        System.out.println("iterations: "+i);
        if(verbose){
            ColorPrint.tree(rootnode.treeToString(0));

        }else{
            ColorPrint.tree(rootnode.chilrenToString());
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
