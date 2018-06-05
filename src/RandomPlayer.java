import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer extends Player {

    public RandomPlayer(int id, ArrayList<Card> hand) {
        super(id, hand);
    }

    boolean printOn = false;
    public Card nextCard(State state){
        printOn=state.printOn;
        if(printOn) {
            int i = 0;
            System.out.print("\n\tRandom player:" + id + "\n\t");
            for (Card c : hand) {
                String msg = "[" + i++ + "] " + c + ", ";
                ColorPrint.hand(c.color, msg);
            }
            System.out.println();
        }
        Random random = state.random;
        ArrayList<Card> moves = state.getMoves();
        return moves.get(random.nextInt(moves.size()));
    }
}
