import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(int id, ArrayList<Card> hand) {
        super(id, hand);
    }


    public Card nextCard(ArrayList<Card> previous,ArrayList<Card> table){
        System.out.println("In your hands are: ");
        int i=0;
        for(Card c:hand){
            System.out.print("[" + i++ + "] " + c + ", ");
        }
        System.out.println("Write card index to play it:");
        Scanner sc = new Scanner(System.in);
        int index=0;
        boolean ok = true;
        while(ok){
            try{
                int choosenCard = sc.nextInt();
                if(choosenCard<hand.size()&&choosenCard>=0){//@Alex dodaj obvezno barvanje
                    index=choosenCard;
                    ok=false;
                }else{
                    System.out.println("No card with this id, try again:");
                }
            }catch(Exception e){
                System.out.println("Man you dont even know how to write numbers, try again:");
               }

        }

        Card one = hand.get(index);
        hand.remove(index);
        return one;
    }
}
