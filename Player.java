import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String playerName;
    public List<Card> hand;
    public int getLoadPlayerIndex;

    public Player(String playerName) {
        this.playerName = playerName;
        hand = new ArrayList<>();
    }

    public String getName() {
        return playerName;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }
    public void clearHand() {
        hand.clear();
    }

    public Card playCard(Card leadCard, List<Card> deck, GoBoomGame GoBoomGame) {
        // List<Card> validCards = getValidCards(leadCard);
        Scanner scanner = new Scanner(System.in);
        Card chosenCard = null;
        int chosenCardIndex;
        // GoBoomGame.displayGameState();
        // System.out.println("Turn : " + playerName);
        if (GoBoomGame.getTrickCount() > 1 && leadCard == null) {
            // The current player is the winner and must play the lead card
            while (chosenCard == null) {
                GoBoomGame.displayGameState();
                System.out.println("Turn : " + playerName);
                System.out.println("Now it's " + playerName + "'s turn. Please choose the lead card:");
                displayHand();
                System.out.print("> ");
                    //System.out.println("*********leader: "+ leadCard);

                String userInput = scanner.nextLine();
                if (checkInteger(userInput)) {
                    chosenCardIndex =  convertToInt(userInput) -1;  
                    chosenCard = hand.get(chosenCardIndex);
                    removeCardFromHand(chosenCard);

                    leadCard = chosenCard;
                }else {
                    userOption(userInput, GoBoomGame);
                }
            }
        } else if (getValidCards(leadCard).isEmpty() && leadCard != null) {
            System.out.println("Turn : " + playerName);
            System.out.println("No valid cards to play. Drawing a card...");

            Card drawnCard = drawCard(deck, leadCard);
            System.out.println(getName() + " draws " + drawnCard);
            GoBoomGame.displayGameState();
            return drawnCard;

        } else {
            // Normal gameplay, the player can choose any valid card
            while (chosenCard == null) {
                GoBoomGame.displayGameState();
                System.out.println("Turn : " + playerName);
                System.out.println("Now it's " + playerName + "'s turn. Please choose a card to play:");
                displayHand();
                System.out.print("> ");

                String userInput = scanner.nextLine();
                if (checkInteger(userInput)) {
                    chosenCardIndex =  convertToInt(userInput) -1;  

                    if (chosenCardIndex >= 0 && chosenCardIndex < hand.size()) {
                        chosenCard = hand.get(chosenCardIndex);
                        if (!isValidCard(chosenCard, leadCard)) {
                            System.out.println("Invalid card. Please choose a valid card.");
                            chosenCard = null;
                        } else {
                            removeCardFromHand(chosenCard);
                        }
                    } else {
                        System.out.println("Invalid card. Please choose a valid card.");
                    }
                }else {
                    userOption(userInput, GoBoomGame);
                   
                }

            }
        }


        return chosenCard;
    }

    private boolean isValidCard(Card card, Card leadCard) {

        String leadSuit = leadCard.getSuit();
        String leadRank = leadCard.getRank();
        String cardSuit = card.getSuit();
        String cardRank = card.getRank();
        return cardSuit.equals(leadSuit) || cardRank.equals(leadRank);
    }

    private List<Card> getValidCards(Card leadCard) {
        List<Card> validCards = new ArrayList<>();
        String leadSuit = leadCard.getSuit();
        String leadRank = leadCard.getRank();

        for (Card card : hand) {
            if (card.getSuit().equals(leadSuit) || card.getRank().equals(leadRank)) {
                validCards.add(card);
            }
        }

        return validCards;
    }

    public void removeCardFromHand(Card card) {
        hand.remove(card);
    }

    public Card drawCard(List<Card> deck, Card leadCard) {
        if (deck.isEmpty()) {
            System.out.println("Deck is empty. Skipping turn.");
            return null;
        }

        Card drawnCard = null;
        for (int i = deck.size() - 1; i >= 0; i--) {
            Card card = deck.get(i);
            if (card.getSuit().equals(leadCard.getSuit()) || card.getSuit().equals(leadCard.getRank())) {
                drawnCard = deck.remove(i);
                break;
            } else {
                drawnCard = deck.remove(0);
                break;
            }

        }

        if (drawnCard != null) {
            hand.add(drawnCard);
        }

        return drawnCard;
    }
    
    public void displayHand() {
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ": " + hand.get(i));
        }
    }

    public static boolean checkInteger(String input) {
        try {
            Integer.parseInt(input);
            return true; // Input is an integer
        } catch (NumberFormatException e) {
            return false; // Input is not an integer
        }
    }

    public static int convertToInt(String input) {
        return Integer.parseInt(input);
    }

private void saveGame(GoBoomGame goBoomGame) {
        try {
            String SAVE_FILE_PATH = "saved_game.txt";
            FileWriter writer = new FileWriter(SAVE_FILE_PATH);

            writer.write(goBoomGame.getTrickCount() + "\n");

            // Save each player's hand
            for (Player player : goBoomGame.players) {
                writer.write(player.getHand().toString() + "\n");
            }
    
            // Save remaining deck and center cards
            writer.write(goBoomGame.center.toString() + "\n");
            writer.write(goBoomGame.deck.toString() + "\n");

            // Save score
            for (Player player : goBoomGame.players) {
                writer.write(goBoomGame.scores[goBoomGame.players.indexOf(player)]+"\n");
            }

            // Write player turn
            writer.write(getLoadPlayerIndex()+"\n");
    
            writer.close();
            System.out.println("Game saved successfully!\n");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the game.\n");
            e.printStackTrace();
        }
    }
public int getLoadPlayerIndex(){
  switch(playerName) {
  case "Player1":
     getLoadPlayerIndex=0;
    break;
  case "Player2":
    getLoadPlayerIndex=1;
    break;
    case "Player3":
    getLoadPlayerIndex=2;
    break;
     case "Player4":
    getLoadPlayerIndex=3;
    break;
}
    return getLoadPlayerIndex;
}

  
    public void userOption(String option, GoBoomGame goBoomGame) {
        GoBoomGame game = new GoBoomGame();
        switch(option) {
            case "s": //save game
            saveGame(goBoomGame);
            break;
            
            case "x": //exit
            System.out.println("Bye!\n");
            System.exit(0);
            break;

            case "l": //load game
            goBoomGame.loadGame();
            //GoBoomGame game = new GoBoomGame();
            //game.start();
            break;

            case "r": //start a new game
            // GoBoomGame game = new GoBoomGame();
            System.out.println("----------------Restart a game----------------");
            game.playGame();
            break;

            default:
            System.out.println("Invalid option. Please try again.\n");
            break;

        }

    }



    @Override
    public String toString() {
        return playerName;
    }
}