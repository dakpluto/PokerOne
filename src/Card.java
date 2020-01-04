public class Card {
    private String wordName;
    private Suit suit;
    private int value;

    public Card(String wordName, Suit suit, int value) {
        this.wordName = wordName;
        this.suit = suit;
        this.value = value;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return wordName + " " + suit + " " + value;
    }
}
