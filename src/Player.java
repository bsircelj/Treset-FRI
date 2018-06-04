import java.util.ArrayList;

public class Player {

   ArrayList<Card> hand = new ArrayList<Card>();
   int points;
   int id;  // od 0 do 3 bo vsak igralec meu id, 0-2 so skupaj in 1-3.
            // Potek igre gre od 0 do 3, in potem lahko s tem idjem oznacujemo kdo je na vrsti.


   public Player(int id, ArrayList<Card> hand){
       points=0;
       this.id=id;
       this.hand=hand;
   }

    public Card nextCard(State state) {
       Card returned = state.getMoves().get(0);
       return returned;
    }
}
