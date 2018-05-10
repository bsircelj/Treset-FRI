public class Card {
    public enum Color{Spade,Bastoni,Coppe,Denari};

    private Color color;
    private int type;
    private int rank; //Moc karte 3 > 2 > R etc... visja stevilka, mocnejsa je

    public Card(int type,Color color){
        if(type<1||type>10){
            throw new java.lang.Error("Type "+type+" is not valid card type");
        }
        this.color=color;
        this.type=type;
        this.rank = type-4;
        rank = rank<0 ? rank+10 : rank;
    }

    public String toString(){
        String name = "";
        switch(type){
            case 1: name+="Ass";
                break;
            case 2: name+="2";
                break;
            case 3: name+="3";
                break;
            case 4: name+="4";
                break;
            case 5: name+="5";
                break;
            case 6: name+="6";
                break;
            case 7: name+="7";
                break;
            case 8: name+="Fante";
                break;
            case 9: name+="Cavallo";
                break;
            case 10: name+="Re";
                break;
        }
        name+=" di ";
        switch(this.color){
            case Spade: name+="Spade";
                break;
            case Bastoni: name+="Bastoni";
                break;
            case Coppe: name+="Coppe";
                break;
            case Denari: name+="Denari";
                break;
        }
        return name;
    }

}
