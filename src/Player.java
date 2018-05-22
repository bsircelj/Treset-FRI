import java.util.ArrayList;

public class Player {

   ArrayList<Card> hand = new ArrayList<Card>();
   int points;
   int id;  // od 0 do 3 bo vsak igralec meu id, 0-2 so skupaj in 1-3.
            // Potek igre gre od 0 do 3, in potem lahko s tem idjem oznacujemo kdo je na vrsti.
   Search search = new Search(id);
   //String controller; //Moznosti, monte karlo, rocni nadzor, random, hevristicne itd...
                        //{computer,human,random}
    //ne bomo naredili tega -> bomo z dedovanjem
    //Osnoven class bo monte carlo player, izpeljani boj drugi

   public Player(int id, ArrayList<Card> hand, String controller){
       points=0;
       this.id=id;
       this.hand=hand;
   }

   public Card nextCard(ArrayList<Card> previous,ArrayList<Card> table){

    return null;//TODO
   }


}
