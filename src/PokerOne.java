import java.util.Scanner;

public class PokerOne {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();
        Card discardCard = new Card("Discard", Suit.HEART, 99);
        String playerName;
        int startingBank = 1000;
        int choice, exitCode = 0, betAmount;

        System.out.println("Please enter your name:");
        playerName = scanner.nextLine();

        Player currentPlayer = new Player(playerName, startingBank);

        do {
            System.out.println("\nPlayer: " + currentPlayer.getName() +
                    "\nCurrent Bank: " + currentPlayer.getBank() +
                    "\n\nSelect Option:" +
                    "\n1) Bet" +
                    "\n2) Add Funds" +
                    "\n3) Quit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\n\nEnter Bet Amount: ");
                    betAmount = scanner.nextInt();
                    if (GamePlay.checkBetAmount(betAmount, currentPlayer.getBank()) && betAmount > 0) {
                        System.out.println(System.lineSeparator().repeat(50));
                        currentPlayer.setBet(betAmount);
                        currentPlayer.setBank(currentPlayer.getBank() - betAmount);
                        GamePlay.dealCardsToPlayer(deck, currentPlayer);
                        GamePlay.bestHand(currentPlayer);
                        GamePlay.discardProcess(deck, currentPlayer, discardCard);
                        currentPlayer.displayCards();
                        GamePlay.bestHand(currentPlayer);
                        GamePlay.payout(currentPlayer, deck);
                    } else {
                        System.out.println("Invalid Bet Amount. Must be positive amount in your bank.");
                    }
                    break;
                case 2:
                    currentPlayer.addBank();
                    break;
                case 3:
                    exitCode = 1;
                    break;
                default:
                    System.out.println("Invalid option, please select again.");
                    break;
            }
        } while (exitCode == 0);


    }
}
