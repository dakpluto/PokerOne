import java.util.Arrays;

public class Player {
    private String name;
    private int bank;
    private int bet;
    private Card[] cards;
    private int highCard;
    private int pairOne;
    private int pairTwo;
    private int trips;
    private int fourOfAKind;


    public Player(String name, int bank) {
        this.name = name;
        this.bank = bank;
        this.bet = 0;
        this.cards = new Card[5];
        this.highCard = 0;
        this.pairOne = 0;
        this.pairTwo = 0;
        this.trips = 0;
        this.fourOfAKind = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public int getHighCard() {
        return highCard;
    }

    public void setHighCard(int highCard) {
        this.highCard = highCard;
    }

    public int getPairOne() {
        return pairOne;
    }

    public void setPairOne(int pairOne) {
        this.pairOne = pairOne;
    }

    public int getPairTwo() {
        return pairTwo;
    }

    public void setPairTwo(int pairTwo) {
        this.pairTwo = pairTwo;
    }

    public int getTrips() {
        return trips;
    }

    public void setTrips(int trips) {
        this.trips = trips;
    }

    public int getFourOfAKind() {
        return fourOfAKind;
    }

    public void setFourOfAKind(int fourOfAKind) {
        this.fourOfAKind = fourOfAKind;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", bank=" + bank +
                ", cards=" + Arrays.toString(cards) +
                '}';
    }

    public void displayCards() {
        System.out.println("\nYour current cards: ");
        for (int i = 0; i < 5; i++) {
            System.out.println("[" + (i + 1) + "]  " + this.cards[i].getWordName());
        }
    }

    public void addBank() {
        if (bank < 100) {
            this.bank = 1000;
        } else {
            System.out.println("Bank already over 100.  Not allowed to add funds.");
        }
    }
}
