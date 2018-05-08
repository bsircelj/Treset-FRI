public class Card {
    public enum Color{spada,bastone,kopa,dinar};

    private Color color;
    private int type;

    public Card(int type,Color color){
        this.color=color;
        this.type=type;
    }

}
