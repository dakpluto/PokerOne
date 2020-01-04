import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> deck;

    public Deck() {
        this.deck = new Stack<>();
        this.deck = createDeck();
    }

    public Stack<Card> getDeck() { return deck; }

    public void setDeck(Stack<Card> deck) {
        this.deck = deck;
    }

    public void resetDeck() {
        setDeck(createDeck());
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deck=" + deck +
                '}';
    }

    @NotNull
    private Stack<Card> createDeck() {
        Stack<Card> buildDeck = new Stack<>();
        String word;
        for(Suit suit : Suit.values()) {
            for(int i = 1; i <= 13; i++) {
                if(i == 1) word = "Ace";
                else if (i == 11) word = "Jack";
                else if (i == 12) word = "Queen";
                else if (i == 13) word = "King";
                else word = Integer.toString(i);

                Card currentCard = new Card(word + " of " + suit.toString().toLowerCase() + "s", suit, i);
                buildDeck.push(currentCard);
            }
        }
        shuffleDeck(buildDeck);
        return buildDeck;
    }

    public void shuffleDeck(Stack<Card> deck) {
        Collections.shuffle(deck);
    }
}
