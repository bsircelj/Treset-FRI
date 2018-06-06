import java.util.ArrayList;
import java.util.Random;

public class MCPlayer extends Player {

    boolean verbose;
    boolean printOn = false;
    boolean coloring = true;
    int iter;

    public MCPlayer(int id, ArrayList<Card> hand,boolean coloring,int iter) {
        super(id, hand);
        this.coloring = coloring;
        this.iter = iter;
    }

    public Card nextCard(State state){
        printOn=state.printOn;
        printOn=false;
        if(printOn) {
            int i = 0;
            System.out.print("Missing colors: ");
            for (int j = 0; j < 4; j++) {
                System.out.print("\n[" + j + "] ");
                for (char c : state.missingColor.get(j))
                    System.out.print(c + " ");

            }
            System.out.print("\n\tMC player:" + id + "\n\t");
            for (Card c : hand) {
                String msg = "[" + i++ + "] " + c + ", ";
                ColorPrint.hand(c.color, msg);
            }
            System.out.println();
        }
        Card chosen = ISMCTS(state,this.iter, false);
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
           // if(i>1000)
                break;
                i++;
            Node node = rootnode;

            Random random = rootstate.random;

            //Determinize
            State state = rootstate.cloneAndRandomize(coloring);

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
        if(printOn)
        System.out.println("iterations: "+i);
        if(printOn)
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
