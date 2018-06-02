public class Card {
    char color;// barve S B C D, brez barve null -> 'N'
    int type;
    int rank; //Moc karte 3 > 2 > R etc... visja stevilka, mocnejsa je

    public Card(int type,char color){
        if(type<1||type>10){
            throw new java.lang.Error("Type "+type+" is not valid card type");
        }
        this.color=color;
        this.type=type;
        this.rank = type-4;
        rank = rank<0 ? rank+10 : rank;
    }

    public boolean equals(Card that){
        if(this.color==that.color&&this.type==that.type)
            return true;
        return false;
    }

    public double value(){
        double val = 0;
        switch(type){
            case 1: val = 1;
                break;
            case 2: val = 0.33;
                break;
            case 3: val = 0.33;
                break;
            case 4: val = 0;
                break;
            case 5: val = 0;
                break;
            case 6: val = 0;
                break;
            case 7: val = 0;
                break;
            case 8: val = 0.33;
                break;
            case 9: val = 0.33;
                break;
            case 10: val = 0.33;
                break;
        }
        return val;
    }

    public Card clone(){
        return new Card(this.type,this.color);
    }

    public String toString(){
        String name = "";
        switch(type){
            case 1: name+="Asso";
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
            case 'S': name+="Spade";
                break;
            case 'B': name+="Bastoni";
                break;
            case 'C': name+="Coppe";
                break;
            case 'D': name+="Denari";
                break;
        }
        return name;
    }

}
