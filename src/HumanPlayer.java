import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(int id, ArrayList<Card> hand) {
        super(id, hand);
    }


    public Card nextCard(State state){

        System.out.println("On table:");
        for(int i=0;i<state.onTable.size();i++){
            ColorPrint.hand(state.onTable.get(i).color,"id: ["+state.order[i]+"]  "+state.onTable.get(i)+"\n");
        }
        System.out.print("["+state.playerToMove+"]\nIn your hands are: ");
        int i=0;
        for(Card c:hand){
            String msg = "[" + i++ + "] " + c + ", ";
            ColorPrint.hand(c.color, msg);
        }
        System.out.print("\nWrite card index to play it:");
        Scanner sc = new Scanner(System.in);
        int index=0;
        boolean ok = true;
        ArrayList<Card> possibleMoves = state.getMoves();
        Card one = null;
        while(ok){
            try{
                int choosenCard = sc.nextInt();
                if(choosenCard<hand.size()&&choosenCard>=0){
                    one = state.playerHands.get(state.playerToMove).get(choosenCard);
                    if(!Node.containsCard(possibleMoves,one)){
                        System.out.println("Card's color doesn't match.\nWrite card index to play it:");
                        continue;
                    }
                    ok=false;
                }else{
                    System.out.println("No card with this id, try again:");
                }
            }catch(Exception e){
                System.out.println("Man you dont even know how to write numbers, try again:");
               }

        }
        return one;
    }
}
