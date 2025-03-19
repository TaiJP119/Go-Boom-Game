import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Player {
    private String playerName;
    private List<Card> hand;
    
    public Player(String playerName) {
        this.playerName = playerName;
        hand = new ArrayList<>();
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }
    


    public Card playCard(Card leadCard) {
        Scanner scanner = new Scanner(System.in);
        Card chosenCard = null;
    
        if (leadCard == null) {
            // The current player is the winner and must play the lead card
            while (chosenCard == null) {
                System.out.println("Turn: " + playerName);
                System.out.println("Now it's " + playerName + "'s turn. Please choose the lead card:");
                displayHand();
                System.out.print("> ");
    
                int chosenCardIndex = scanner.nextInt() - 1;
    
                if (chosenCardIndex >= 0 && chosenCardIndex < hand.size()) {
                    chosenCard = hand.get(chosenCardIndex);
                    removeCardFromHand(chosenCard);
                } else {
                    System.out.println("Invalid card. Please choose a valid card.");
                }
            }
        } else {
            // Normal gameplay, the player can choose any valid card
            while (chosenCard == null) {
                System.out.println("Turn: " + playerName);
                System.out.println("Now it's " + playerName + "'s turn. Please choose a card to play:");
                displayHand();
                System.out.print("> ");
    
                int chosenCardIndex = scanner.nextInt() - 1;
    
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



    public void removeCardFromHand(Card card) {
        hand.remove(card);
    }

    private void displayHand() {
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ": " + hand.get(i));
        }
    }

    @Override
    public String toString() {
        return playerName;
    }
}
