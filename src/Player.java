import java.util.ArrayList;

public class Player {

   ArrayList<Card> hand = new ArrayList<Card>();
   int points;
   int id;  // od 1 do 4 bo vsak igralec meu id, 1-3 so skupaj in 2-4.
            // Potek igre gre od 1 do 4, in potem lahko s tem idjem oznacujemo kdo je na vrsti.
   Search search = new Search();
   String controller; //Moznosti, monte karlo, rocni nadzor, random, hevristicne itd...
                        //{computer,human,random}

   public Player(int id, ArrayList<Card> hand, String controller){
       points=0;
       this.id=id;
       this.hand=hand;
       this.controller=controller;
   }


}
