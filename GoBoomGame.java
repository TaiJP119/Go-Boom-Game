import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Array;
import java.util.*;

public class GoBoomGame {

    private static final Map<String, String> SUITS = initializeSuits();
    private static final Map<String, String> RANKS = initializeRanks();

    private static final Map<String, String> initializeSuits() {
        Map<String, String> suits = new HashMap<>();
        suits.put("c", "c");
        suits.put("d", "d");
        suits.put("h", "h");
        suits.put("s", "s");
        return Collections.unmodifiableMap(suits);
    }

    private static Map<String, String> initializeRanks() {
        Map<String, String> ranks = new HashMap<>();
        ranks.put("2", "2");
        ranks.put("3", "3");
        ranks.put("4", "4");
        ranks.put("5", "5");
        ranks.put("6", "6");
        ranks.put("7", "7");
        ranks.put("8", "8");
        ranks.put("9", "9");
        ranks.put("10", "10");
        ranks.put("A", "A");
        ranks.put("J", "J");
        ranks.put("Q", "Q");
        ranks.put("K", "K");
        return Collections.unmodifiableMap(ranks);
    }

    // private static final String[] SUITS = { "c", "d", "h", "s" };
    // private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8",
    // "9", "10", "A", "J", "Q", "K" };
    public List<String> datahand;
    public List<Player> players;
    public List<Card> deck;
    public List<Card> center;
    public int currentPlayerIndex;
    public int trickCount;
    public int[] scores;
    private int currentTrickPlayerIndex;
    int lowestScore;
    Set<Player> lowestScoringPlayers = new HashSet<>();

    public GoBoomGame() {
        datahand = new ArrayList<>();
        players = new ArrayList<>();
        deck = new ArrayList<>();
        center = new ArrayList<>();
        currentPlayerIndex = 0;
        trickCount = 1;
        scores = new int[4];
    }

    public int getTrickCount() {
        return trickCount;
    }

    public void playGame() {
        createPlayers();
        while (true) {

            start();
            if (isGameEnded()) {
                end();
                lowestScore = 0;
                break;
            }
        }
    }

    private boolean isGameEnded() {
        for (int score : scores) {
            if (score >= 10) {
                return true;
            }
        }
        return false;
    }

    public void start() {
            System.out.println("""

                    c = club
                    d = diamond
                    h = heart
                    s = spade""");

            createDeck();
            shuffleDeck();
            dealCards();
            Card leadCard = getLeadCard();
center.add(leadCard);

        // Determine the first player based on the lead card
        currentPlayerIndex = determineFirstPlayerIndex(leadCard);

        System.out
                .println("At the beginning of the game, the first lead card " + leadCard + " is placed at the center.");
        System.out.println("Player " + (currentPlayerIndex + 1) + " is the first player because of " + leadCard);

        // displayGameState();

        while (!deck.isEmpty()) {
            currentTrickPlayerIndex = currentPlayerIndex;
            do {
                Player currentPlayer = players.get(currentTrickPlayerIndex);
                Card playedCard = currentPlayer.playCard(leadCard, deck, this);
                currentPlayer.removeCardFromHand(playedCard);
                center.add(playedCard);
                System.out.println(currentPlayer.getName() + " plays " + playedCard);
                if (leadCard == null) {
                    leadCard = playedCard;
                }
                currentTrickPlayerIndex = (currentTrickPlayerIndex + 1) % players.size();

            } while (currentTrickPlayerIndex != currentPlayerIndex);

            // End of a trick, determine the winner
            int winnerIndex = determineTrickWinnerIndex(center);
            Player winner = players.get(winnerIndex);
            System.out.println("*** " + winner.getName() + " wins Trick #" + trickCount + " ***");
            trickCount++;
            center.clear();

            // Set the winner as the first player of the next trick
            currentPlayerIndex = winnerIndex;

            leadCard = null;
            if (isGameNeedLoop()) {
                calculateFinalScores();
                displayGameState();
                break;
            }
        }
        System.out.println("Renewing Cards and Deck...");
        deck.clear();

        for (Player player : players) {
            player.clearHand();
        }
        trickCount = 1;
    }

    public void end() {
        // Calculate and display final scores based on remaining cards
        // calculateFinalScores();
        // displayGameState();
        // Display the player(s) with the lowest score
        // System.out.print("CONGRATULATIONS!! Player " + lowestScoringPlayers.get(0) +
        // " WIN the GAME!! ");
        System.out.print("CONGRATULATIONS!! " + lowestScoringPlayers);
        System.out.print(" WIN THE GAME!! ");
    }

    private boolean isGameNeedLoop() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void calculateFinalScores() {

        for (int i = 0; i < players.size(); i++) {
            List<Card> hand = players.get(i).getHand();
            int score = 0;
            for (Card card : hand) {
                score += card.getRankFinalValue();
            }

            scores[i] += score;

            if (scores[i] < lowestScore) {
                lowestScore = scores[i];
                lowestScoringPlayers.clear();
                lowestScoringPlayers.add(players.get(i));
            } else if (scores[i] == lowestScore) {
                lowestScoringPlayers.add(players.get(i));
            }

        }

    }

    private int determineTrickWinnerIndex(List<Card> trickCards) {
        Card leadCard = trickCards.get(0);
        String leadSuit = leadCard.getSuit();
        int maxRankValue = 0;
        int winnerIndex = 0;
        if (trickCount == 1) {
            for (int i = 1; i < trickCards.size(); i++) {
                Card card = trickCards.get(i);
                String suit = card.getSuit();
                int rankValue = card.getRankValue();

                if (suit.equals(leadSuit) && rankValue > maxRankValue) {
                    maxRankValue = rankValue;
                    winnerIndex = i - 1;
                    System.out.println("winnerIndex in detemineTW, i=1: " + winnerIndex);
                }
            }
        } else {
            for (int i = 0; i < trickCards.size(); i++) {
                Card card = trickCards.get(i);
                String suit = card.getSuit();
                int rankValue = card.getRankValue();

                if (suit.equals(leadSuit) && rankValue > maxRankValue) {
                    maxRankValue = rankValue;
                    winnerIndex = i;
                }
                // System.out.println("winner index for trick>1, i=0: "+winnerIndex);
            }
        }
        // Adjust the winner index based on the current player index
        // System.out.println("Calculated winner index:" + winnerIndex + " and " +
        // currentPlayerIndex + "****");
        winnerIndex = (currentPlayerIndex + winnerIndex) % players.size();
        // System.out.println("Calculated winner index:" + winnerIndex + "****");
        return winnerIndex;
    }

    private int determineFirstPlayerIndex(Card leadCard) {
        String rank = leadCard.getRank();
        if (rank.equals("A") || rank.equals("5") || rank.equals("9") || rank.equals("K")) {
            return 0; // Player1
        } else if (rank.equals("2") || rank.equals("6") || rank.equals("10")) {
            return 1; // Player2
        } else if (rank.equals("3") || rank.equals("7") || rank.equals("J")) {
            return 2; // Player3
        } else if (rank.equals("4") || rank.equals("8") || rank.equals("Q")) {
            return 3; // Player4
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
        for (String suit : SUITS.keySet()) {
            for (String rank : RANKS.keySet()) {
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

    public void dealdatacard() {
        for (Player player : players) {
            player.clearHand();
            for (String cardString : datahand) {
                Card card = Card.fromString(cardString);
                player.addCardToHand(card);
            }
        }
    }

    private Card getLeadCard() {
        return deck.remove(deck.size() - 1);
    }

    public void displayGameState() {
        System.out.println("\nTrick #" + trickCount);
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getHand());
        }
        System.out.println("Center : " + center);
        System.out.println("Deck : " + deck);
        displayScores();

    }

    private void displayScores() {
        System.out.print("Score: ");
        for (int i = 0; i < players.size(); i++) {
            System.out.print(players.get(i).getName() + " = " + scores[i] + " | ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        GoBoomGame game = new GoBoomGame();
        game.playGame();

    }
   private String centerLeadCard;
       public String getCenterLeadCard() {
        centerLeadCard = String.valueOf(center.get(0));
        //centerLeadCard = "c4";
        return centerLeadCard;
    }
    public void loadGame() {
        try {
            String SAVE_FILE_PATH = "saved_game.txt";
            BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE_PATH));
            System.out.println("Game Loaded Succeeded!! ");
            // Load Trick
            trickCount = Integer.parseInt(reader.readLine());
            //System.out.println("Game loaded trick:" + trickCount);

            // Load Player cards
            for (Player player : players) {
                String data = reader.readLine(); // Read the data as a string "[c7, h5, sK, d4, h3, d9]"
                data = data.substring(1, data.length() - 1); // Remove the square brackets
                player.clearHand();
                if (!data.isEmpty()) {
                    String[] handTokens = data.split(", ");
                    for (int i = 0; i < handTokens.length; i++) {
                        String cardString = handTokens[i].trim(); // Fix: Use handTokens[i] instead of handToken
                       // System.out.println("Game loaded card player " + player.getName() + ": " + cardString); // Fix:
                                                                                                               // Use
                                                                                                               // player.getName()
                        datahand.add(cardString);
                        Card card = Card.fromdataString(cardString);
                        player.addCardToHand(card);
                    }
                    //System.out.println(player.getName() + ": " + player.getHand());
                } else {
                    System.out.println("No cards found in the data.");
                }
            }

            //Load Center
             String centerdata = reader.readLine(); // Read the data as a string "[c7, h5, sK, d4, h3, d9]"
                centerdata = centerdata.substring(1, centerdata.length() - 1); // Remove the square brackets
                center.clear();
if (!centerdata.isEmpty()) {
                    String[] centerTokens = centerdata.split(", ");
                    for (int i = 0; i < centerTokens.length; i++) {
                        String centerString = centerTokens[i].trim(); // Fix: Use handTokens[i] instead of handToken

                         Card leadCard= Card.fromdataString(centerString);
                       center.add(leadCard);
                       
                       
                    }
                    
                } else {
                    System.out.println("No cards found in the data.");
                }

        } catch (IOException e) {
            System.out.println("Failed to load game: " + e.getMessage());
        }
Card leadCard = Card.fromdataString(getCenterLeadCard());
//System.out.println("Center loaded: " + leadCard);


    }

}
