import java.util.*;

public class GoBoomGame {
    private static final String[] SUITS = { "c", "d", "h", "s" };
    private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "J", "Q", "K" };

    private List<Player> players;
    private List<Card> deck;
    private List<Card> center;
    private int currentPlayerIndex;
    private int trickCount;

    public GoBoomGame() {
        players = new ArrayList<>();
        deck = new ArrayList<>();
        center = new ArrayList<>();
        currentPlayerIndex = 0;
        trickCount = 1;
    }

    public void startGame() {

        createPlayers();
        createDeck();
        shuffleDeck();
        dealCards();

        Card leadCard = getLeadCard();
        center.add(leadCard);

        currentPlayerIndex = determineFirstPlayerIndex(leadCard);

        System.out.println("\nAt the beginning of the game, the first lead card " + leadCard + " is placed at the center.");
        System.out.println("Player " + (currentPlayerIndex+1) + " is the first player because of " + leadCard);

        System.out.println("""
            c = club 
            d = diamond
            h = heart 
            s = spade """);

        displayGameState();

        while (trickCount == 1) {
            Player currentPlayer = players.get(currentPlayerIndex);
        
            if (center.size() == players.size()+1) {
                int winnerIndex = determineTrickWinnerIndex(center)-1;
                Player winner = players.get(winnerIndex);
                
                System.out.println("\n*** " + winner.getPlayerName() + " wins Trick #" + trickCount + " ***");
                trickCount++;
                center.clear();
                
                displayGameState();

                
            }
        
            if (!deck.isEmpty()) {
                
                Card playedCard = currentPlayer.playCard(center.isEmpty() ? null : center.get(0));
                currentPlayer.removeCardFromHand(playedCard);
                center.add(playedCard);
        
                System.out.println(currentPlayer.getPlayerName() + " plays " + playedCard);
        
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }
        
            displayGameState();
        }
        while (trickCount>1 || !deck.isEmpty() || !center.isEmpty()) {
            Player currentPlayer = players.get(currentPlayerIndex);
        
            if (center.size() == players.size()) {
                int winnerIndex = determineTrickWinnerIndex(center);
                Player winner = players.get(winnerIndex);
                
                System.out.println("\n*** " + winner.getPlayerName() + " wins Trick #" + trickCount + " ***");
                trickCount++;
                center.clear();
                
                displayGameState();

                
            }
        
            if (!deck.isEmpty()) {
                
                Card playedCard = currentPlayer.playCard(center.isEmpty() ? null : center.get(0));
                currentPlayer.removeCardFromHand(playedCard);
                center.add(playedCard);
        
                System.out.println(currentPlayer.getPlayerName() + " plays " + playedCard);
        
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }
        
            displayGameState();
        }
       
        System.out.println("Game Over");
    }

    private int determineTrickWinnerIndex(List<Card> trickCards) {
        Card leadCard = trickCards.get(0);
        String leadSuit = leadCard.getSuit();
        int maxRankValue = 0;
        int winnerIndex = 0;

        for (int i = 1; i < trickCards.size(); i++) {
            Card card = trickCards.get(i);
            String suit = card.getSuit();
            int rankValue = card.getRankValue();

            if (suit.equals(leadSuit) && rankValue > maxRankValue) {
                maxRankValue = rankValue;
                winnerIndex = i;
            }
        }
        // System.out.println("\n*** " + currentPlayerIndex +" plusssss "+ winnerIndex + " wins Trick #" + trickCount + " ***");
        return (currentPlayerIndex + winnerIndex) % players.size();
    }

    private int determineFirstPlayerIndex(Card leadCard) {
        String rank = leadCard.getRank();
        if (rank.equals("A") || rank.equals("5") || rank.equals("9") || rank.equals("K")) {
            return 0;
        } else if (rank.equals("2") || rank.equals("6") || rank.equals("10")) {
            return 1;
        } else if (rank.equals("3") || rank.equals("7") || rank.equals("J")) {
            return 2;
        } else if (rank.equals("4") || rank.equals("8") || rank.equals("Q")) {
            return 3;
        } else {
            throw new IllegalArgumentException("Invalid lead card rank");
        }
    }

    private void createPlayers() {
        for (int i = 0; i < 4; i++) {
            players.add(new Player("Player" + (i + 1)));
        }
    }

    private void createDeck() {
        for (String suit : SUITS) {
            for (String rank : RANKS) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    private void shuffleDeck() {
        Collections.shuffle(deck);
    }

    private void dealCards() {
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                Card card = deck.remove(deck.size() - 1);
                player.addCardToHand(card);
            }
        }
    }

    private Card getLeadCard() {
        return deck.remove(deck.size() - 1);
    }

    private void displayGameState() {
        
        System.out.println("\nTrick #" + trickCount);
        for (Player player : players) {
            System.out.println(player.getPlayerName() + ": " + player.getHand());
        }
        System.out.println("Center : " + center);
        System.out.println("Deck : " + deck);
        System.out.println("Score: Player1 = 0 | Player2 = 0 | Player3 = 0 | Player4 = 0");
        // System.out.println("Turn : " + players.get(currentPlayerIndex).getPlayerName());
        
    }

    public static void main(String[] args) {
        GoBoomGame game = new GoBoomGame();
        game.startGame();
    }
}
