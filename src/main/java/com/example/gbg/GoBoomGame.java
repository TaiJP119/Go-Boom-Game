//package com.example.gbg;
//import java.util.*;
//
//public class GoBoomGame {
//    private static final String[] SUITS = {"c", "d", "h", "s"};
//    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "J", "Q", "K"};
//
//    private List<Player> players;
//    private List<Card> deck;
//    private List<Card> center;
//    private int currentPlayerIndex;
//    private int trickCount;
//    private int[] scores;
//    private int currentTrickPlayerIndex;
//    private int getPlayer1Score;
//    private int getPlayer2Score;
//    private int getPlayer3Score;
//    private int getPlayer4Score;
//
//    private String centerLeadCard;
//    private String center2Card;
//    private String center3Card;
//    private String center4Card;
//    private String center5Card;
//
//    private String currentPlayerName;
//    private String playedCardG;
//    private String winnerGetName;
//    private String FinalwinnerGetName;
//    private List<List<Card>> HandCards;
//    private List<Integer> playerScores;
//    int lowestScore;
//    List<Player> lowestScoringPlayers = new ArrayList<>();
//
//
//    public GoBoomGame() {
//        players = new ArrayList<>();
//        deck = new ArrayList<>();
//        center = new ArrayList<>();
//        playerScores = new ArrayList<>();
//        currentPlayerIndex = 0;
//        trickCount = 1;
//        scores = new int[4];
//    }
//
//    public int getTrickCount() {
//        return trickCount;
//    }
//    public List<Card> getDeck() {
//        return deck;
//    }
//    public int getCurrentPlayerIndex() {
//        return currentPlayerIndex;
//    }
//
//    //center cards
//    public String getCenter2Cards() {
//        try {
//            center2Card = center.get(1).toString();
//
//            System.out.println("Center2Cards: "+center2Card);
//        } catch (java.lang.IndexOutOfBoundsException e) {
//            center2Card = "back"; // Replace "default_card" with the desired default card resource name
//            System.out.println("C2cards: "+center2Card);
//        }
//        return center2Card;
//    }
//
//    public List<Card> getCenter3Cards() {
//        try
//
//        {
//            center3Card = center.get(2).toString();
//            System.out.print("Center3Cards: "+center3Card);
//        }catch(java.lang.IndexOutOfBoundsException e)
//
//        {
//            System.out.print("bak");
//        }
//        return center;
//    }
//    public List<Card> getCenter4Cards() {
//        try{
//            center4Card = center.get(3).toString();
//            System.out.print("Center4Cards: "+center4Card);
//        }catch(java.lang.IndexOutOfBoundsException e){
//            //String center = "c5";
//            System.out.print("bak");
//        }
//        return center;
//    }
//    public List<Card> getCenter5Cards() {
//        try{
//            center5Card = center.get(4).toString();
//            System.out.print("Center5Cards: "+center5Card);
//        }catch(java.lang.IndexOutOfBoundsException e) {
//            //String center = "c6";
//            System.out.println("C2cards: "+center5Card);}
//        return center;
//    }
//
//    private void displayScores() {
//        //  System.out.print("Score: ");
//        List<Integer> playerScoresIn = new ArrayList<>();
//        for (int i = 0; i < players.size(); i++) {
//            //System.out.print(players.get(i).getName() + " = " + scores[i] + " | ");
//            playerScoresIn.add(scores[i]);
//        }
//    }
//    public List<Integer> getPlayerScores(List<Integer> playerScoresIn) {
//        playerScores.clear();
//        playerScores.addAll(playerScoresIn);
//        getPlayer1Score = playerScores.get(0);
//        getPlayer2Score = playerScores.get(1);
//        getPlayer3Score = playerScores.get(2);
//        getPlayer4Score = playerScores.get(3);
//
//        return playerScores;
//    }
//
//    public int getPlayer1Score() {
//        return getPlayer1Score;
//    }
//    public int getPlayer2Score() {
//        return getPlayer2Score;
//    }
//    public int getPlayer3Score() {
//        return getPlayer3Score;
//    }
//    public int getPlayer4Score() {
//        return getPlayer4Score;
//    }
//
//
//    public String getCenterLeadCard() {
//        centerLeadCard = String.valueOf(center.get(0));
//        //centerLeadCard = "c4";
//        return centerLeadCard;
//    }
//
//    public String getCurrentPlayerName() {
//        return currentPlayerName;
//    }
//
//    public String getplayedCardG() {
//        return playedCardG;
//    }
//    public String getWinnerName() {
//        return winnerGetName;
//    }
//    public String getFinalwinnerGetName() {
//        return FinalwinnerGetName;
//    }
//    public List<List<Card>> getPlayerHands() {
//        List<List<Card>> HandCards = new ArrayList<>();
//        for (Player player : players) {
//            HandCards.add(player.getHand());
//        }
//        return HandCards;
//    }
//    public List<Card> getCenter() {
//        return center;
//    }
//
//    public void playGame() {
//        createPlayers();
//        while (true) {
//            start();
//            if (isGameEnded()) {
//                end();
//                lowestScore = 0;
//                break;
//            }
//        }
//    }
//
//    private boolean isGameEnded() {
//        for (int score : scores) {
//            if (score >= 10) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void start() {
//        createDeck();
//        shuffleDeck();
//        dealCards();
//
//        Card leadCard = getLeadCard();
//        center.add(leadCard);
//
//        // Determine the first player based on the lead card
//        currentPlayerIndex = determineFirstPlayerIndex(leadCard);
//        centerLeadCard = leadCard.toString();
//
//        System.out.println("At the beginning of the game, the first lead card " + leadCard + " is placed at the center.");
//        System.out.println( "Player " + (getCurrentPlayerIndex() + 1) + " is the first player because of " + centerLeadCard);
//        // displayGameState();
//
//        while (!deck.isEmpty()) {
//            currentTrickPlayerIndex = currentPlayerIndex;
//
//            do {
//
//                Player currentPlayer = players.get(currentTrickPlayerIndex);
//
//                Card playedCard = currentPlayer.playCard(leadCard, deck, this);
//                currentPlayer.removeCardFromHand(playedCard);
//                center.add(playedCard);
//
//                currentPlayerName = currentPlayer.getName();
//                playedCardG = playedCard.toString();
//                //System.out.println(currentPlayer.getName() + " plays " + playedCard);
//                System.out.println(getCurrentPlayerName() + " plays " + getplayedCardG());
//                if (leadCard == null) {
//                    leadCard = playedCard;
//                }
//                centerLeadCard = leadCard.toString();
//                // System.out.println("leadCard:  " + leadCard);
//                System.out.println("leadsCard:  " + getCenterLeadCard());
//
//                currentTrickPlayerIndex = (currentTrickPlayerIndex + 1) % players.size();
//
//            } while (currentTrickPlayerIndex != currentPlayerIndex);
//
//            // End of a trick, determine the winner
//            int winnerIndex = determineTrickWinnerIndex(center);
//            Player winner = players.get(winnerIndex);
//
//            winnerGetName = winner.getName();
//            //System.out.println("*** " + winner.getName() + " wins Trick #" + trickCount + " ***");
//            System.out.println("*** " + getWinnerName() + " wins Trick #" + getTrickCount() + " ***");
//            trickCount++;
//            center.clear();
//
//            // Set the winner as the first player of the next trick
//            currentPlayerIndex = winnerIndex;
//
//            leadCard = null;
//            // displayGameState();
//            if (isGameNeedLoop()) {
//                calculateFinalScores();
//                displayGameState();
//                break;
//            }
//        }
//        System.out.println("Renewing Cards and Deck...");
//        deck.clear();
//        for (Player player : players) {
//            player.clearHand();
//        }
//        trickCount = 1;
//    }
//
//    public void end() {
//        FinalwinnerGetName = lowestScoringPlayers.get(0).toString();
//        System.out.print("CONGRATULATIONS!! Player " + lowestScoringPlayers.get(0) + " WIN the GAME!! ");
//        System.out.print("CONGRATULATIONS!!!! Player " + getFinalwinnerGetName() + " WIN the GAME!! ");
//    }
//
//    private boolean isGameNeedLoop() {
//        for (Player player : players) {
//            if (player.getHand().isEmpty()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private void calculateFinalScores() {
//
//        for (int i = 0; i < players.size(); i++) {
//            List<Card> hand = players.get(i).getHand();
//            int score = 0;
//            for (Card card : hand) {
//                score += card.getRankFinalValue();
//            }
//
//            scores[i] += score;
//
//            if (scores[i] < lowestScore) {
//                lowestScore = scores[i];
//                lowestScoringPlayers.clear();
//                lowestScoringPlayers.add(players.get(i));
//            } else if (scores[i] == lowestScore) {
//                lowestScoringPlayers.add(players.get(i));
//            }
//
//        }
//
//    }
//
//    private int determineTrickWinnerIndex(List<Card> trickCards) {
//        Card leadCard = trickCards.get(0);
//        String leadSuit = leadCard.getSuit();
//        int maxRankValue = 0;
//        int winnerIndex = 0;
//        if (trickCount == 1) {
//            for (int i = 1; i < trickCards.size(); i++) {
//                Card card = trickCards.get(i);
//                String suit = card.getSuit();
//                int rankValue = card.getRankValue();
//
//                if (suit.equals(leadSuit) && rankValue > maxRankValue) {
//                    maxRankValue = rankValue;
//                    winnerIndex = i - 1;
//                }
//            }
//        } else {
//            for (int i = 0; i < trickCards.size(); i++) {
//                Card card = trickCards.get(i);
//                String suit = card.getSuit();
//                int rankValue = card.getRankValue();
//
//                if (suit.equals(leadSuit) && rankValue > maxRankValue) {
//                    maxRankValue = rankValue;
//                    winnerIndex = i;
//                }
//            }
//        }
//        winnerIndex = (currentPlayerIndex + winnerIndex) % players.size();
//        return winnerIndex;
//    }
//
//    private int determineFirstPlayerIndex(Card leadCard) {
//        String rank = leadCard.getRank();
//        if (rank.equals("A") || rank.equals("5") || rank.equals("9") || rank.equals("K")) {
//            return 0; // Player1
//        } else if (rank.equals("2") || rank.equals("6") || rank.equals("10")) {
//            return 1; // Player2
//        } else if (rank.equals("3") || rank.equals("7") || rank.equals("J")) {
//            return 2; // Player3
//        } else if (rank.equals("4") || rank.equals("8") || rank.equals("Q")) {
//            return 3; // Player4
//        } else {
//            throw new IllegalArgumentException("Invalid lead card rank");
//        }
//    }
//
//    private void createPlayers() {
//        for (int i = 0; i < 4; i++) {
//            players.add(new Player("Player" + (i + 1)));
//        }
//    }
//
//    private void createDeck() {
//        for (String suit : SUITS) {
//            for (String rank : RANKS) {
//                deck.add(new Card(suit, rank));
//            }
//        }
//    }
//
//    private void shuffleDeck() {
//        Collections.shuffle(deck);
//    }
//
//    private void dealCards() {
//        for (Player player : players) {
//            for (int i = 0; i < 7; i++) {
//                Card card = deck.remove(deck.size() - 1);
//                player.addCardToHand(card);
//            }
//        }
//    }
//
//    public void displayGameState() {
//        System.out.println("Trick #" + trickCount);
//        System.out.println("Trick #" + getTrickCount());
//
//        for (Player player : players) {
//            System.out.println(player.getName() + ": " + player.getHand());
//        }
//
//        System.out.println("Center : " + center);
//        System.out.println("Center : " + getCenter());
//        getCenter2Cards();
//        getCenter3Cards();
//        getCenter4Cards();
//        getCenter5Cards();
//        System.out.println();
//
//        System.out.println("Deck : " + deck);
//        System.out.println("Deck : " + getDeck());
//
//        displayScores();
//
//    }
//
//    private Card getLeadCard() {
//        return deck.remove(deck.size() - 1);
//    }
//
//    public static void main(String[] args) {
//        GoBoomGame game = new GoBoomGame();
//        game.playGame();
//    }
//}
