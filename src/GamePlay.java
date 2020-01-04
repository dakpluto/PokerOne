import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class GamePlay {
    public static boolean checkBetAmount(int bet, int bank) {
        return bet <= bank;
    }

    public static void dealCardsToPlayer(Deck deck, Player player) {
        Card[] cards = new Card[5];
        for (int i = 0; i < 5; i++) {
            cards[i] = deck.getDeck().pop();
        }
        player.setCards(cards);
    }

    public static int bestHand(@NotNull Player player) {
        int bet = player.getBet();
        int payout = 0;
        boolean flushCheck;
        boolean straightCheck;

        flushCheck = flushCheckMethod(player.getCards());
        straightCheck = straightCheckMethod(player.getCards());
        player.setFourOfAKind(fourOfAKindCheck(player.getCards()));
        player.setTrips(tripsCheck(player.getCards(), player.getFourOfAKind()));
        player.setPairOne(pairCheck(player.getCards(), 0, player.getTrips(), player.getFourOfAKind()));
        player.setPairTwo(pairCheck(player.getCards(), player.getPairOne(), player.getTrips(), player.getFourOfAKind()));
        player.setHighCard(highCardCheck(player.getCards()));

        System.out.println("Best hand: ");
        if (flushCheck && straightCheck && player.getHighCard() == 14) {
            System.out.println("Royal Flush!");
            payout = 800;
        } else if (flushCheck && straightCheck) {
            System.out.println("Straight Flush!");
            payout = 50;
        } else if (player.getFourOfAKind() > 0) {
            System.out.println("Four of a Kind!");
            payout = 25;
        } else if (player.getTrips() > 0 && player.getPairOne() > 0) {
            System.out.println("Full House!");
            payout = 9;
        } else if (flushCheck) {
            System.out.println("Flush");
            payout = 6;
        } else if (straightCheck) {
            System.out.println("Straight");
            payout = 4;
        } else if (player.getTrips() > 0) {
            System.out.println("Three of a Kind");
            payout = 3;
        } else if (player.getPairOne() > 0 && player.getPairTwo() > 0) {
            System.out.println("Two Pair");
            payout = 2;
        } else if (player.getPairOne() > 10 || player.getPairOne() == 1) {
            System.out.println("Jacks or Better");
            payout = 1;
        } else if (player.getPairOne() > 1) {
            System.out.println("Pair, but not Jacks or Better");
        } else {
            System.out.println("High Card");
        }
        return bet * payout;
    }

    private static boolean flushCheckMethod(@NotNull Card[] cards) {
        boolean flushCheck = true;
        Suit firstSuit = cards[0].getSuit();

        for (Card card : cards) {
            if (card.getSuit() != firstSuit) {
                flushCheck = false;
                break;
            }
        }

        return flushCheck;
    }

    private static boolean straightCheckMethod(@NotNull Card[] cards) {
        Card previousCard;
        Card[] sortedCards;
        sortedCards = cards;
        Arrays.sort(sortedCards, Comparator.comparing(Card::getValue));
        previousCard = sortedCards[0];
        if (previousCard.getValue() == 1 && sortedCards[1].getValue() == 10) {
            previousCard = sortedCards[1];
            for (int i = 2; i < 5; i++) {
                if (sortedCards[i].getValue() != (previousCard.getValue() + 1)) {
                    return false;
                }
                previousCard = sortedCards[i];
            }
            return true;
        } else {
            for (int i = 1; i < 5; i++) {
                if (sortedCards[i].getValue() != (previousCard.getValue() + 1)) {
                    return false;
                }
                previousCard = sortedCards[i];
            }
        }
        return true;
    }

    private static int fourOfAKindCheck(@NotNull Card[] cards) {
        int quadValue = 0;
        Card[] sortedCards;
        sortedCards = cards;
        Arrays.sort(sortedCards, Comparator.comparing(Card::getValue));

        for (int i = 0; i < 2; i++) {
            if (sortedCards[i].getValue() == sortedCards[i + 1].getValue() && sortedCards[i].getValue() == sortedCards[i + 2].getValue() && sortedCards[i].getValue() == sortedCards[i + 3].getValue()) {
                quadValue = sortedCards[i].getValue();
            }
        }

        return quadValue;
    }

    private static int tripsCheck(@NotNull Card[] cards, int fourOfAKind) {
        int tripValue = 0;
        Card[] sortedCards;
        sortedCards = cards;
        Arrays.sort(sortedCards, Comparator.comparing(Card::getValue));

        for (int i = 0; i < 3; i++) {
            if (sortedCards[i].getValue() == sortedCards[i + 1].getValue() && sortedCards[i].getValue() == sortedCards[i + 2].getValue() && sortedCards[i].getValue() != fourOfAKind) {
                tripValue = sortedCards[i].getValue();
            }
        }

        return tripValue;
    }

    private static int pairCheck(@NotNull Card[] cards, int existingPair, int trips, int fourOfAKind) {
        int pairValue = 0;
        Card[] sortedCards;
        sortedCards = cards;
        Arrays.sort(sortedCards, Comparator.comparing(Card::getValue));

        for (int i = 0; i < 4; i++) {
            if (sortedCards[i].getValue() == sortedCards[i + 1].getValue() && sortedCards[i].getValue() != existingPair && sortedCards[i].getValue() != trips && sortedCards[i].getValue() != fourOfAKind) {
                pairValue = sortedCards[i].getValue();
                break;
            }
        }

        return pairValue;
    }

    private static int highCardCheck(@NotNull Card[] cards) {
        int highCard = 0;
        for (Card card : cards) {
            if (card.getValue() == 1) {
                highCard = 14;
                break;
            }
            if (card.getValue() > highCard) {
                highCard = card.getValue();
            }
        }

        return highCard;
    }

    // Discard

    public static void discardProcess(Deck deck, Player player, Card discard) {
        int discardCounter = 0;
        Card[] cards = player.getCards();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (discardCounter < 3) {
            System.out.println("Would you like to discard a card?  Enter the card number.  Select 9 when finished.");
            player.displayCards();
            System.out.println("[9] Finished");
            choice = scanner.nextInt();
            if (choice >= 1 && choice <= 5) {
                cards[choice - 1] = discard;
                player.setCards(cards);
                discardCounter++;
            } else if (choice == 9){
                discardCounter = 9;
            } else {
                System.out.println("Invalid selection.  Select again.");
            }
        }

        System.out.println("Dealing Cards!");

        for(int i = 0; i < cards.length; i++) {
            if (cards[i].getValue() == 99) {
                cards[i] = deck.getDeck().pop();
            }
        }

        player.setCards(cards);
    }

    public static void payout (Player player, Deck deck) {
        System.out.println("You win $" + bestHand(player));
        player.setBank(player.getBank() + bestHand(player));
        System.out.println("You now have $" + player.getBank());
        resetTurn(deck);
        player.setFourOfAKind(0);
        player.setTrips(0);
        player.setPairTwo(0);
        player.setPairOne(0);
        player.setHighCard(0);
    }

    private static void resetTurn(Deck deck) {
        deck.resetDeck();
    }

}
