package com.example.gbg;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;



import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private TextView welcomeTextView;
    private TextView trickTextView;
    private TextView deckTextView;
    public static TextView playersTurnTextView;
    private TextView player1TextView;
    private TextView player2TextView;
    private TextView player3TextView;
    private TextView player4TextView;
    private ImageView leadCardImageView;
    private ImageView centerCard2ImageView;
    private ImageView centerCard3ImageView;
    private ImageView centerCard4ImageView;
    private ImageView centerCard5ImageView;
    int y = 0;
    public ImageButton Card1;
    public  ImageButton Card2;
    public ImageButton Card3;
    public ImageButton Card4;
    public  ImageButton Card5;
    public  ImageButton Card6;
    public   ImageButton Card7;
    private   TextView PlayedCard;

    //gbg codes here
    private static final String[] SUITS = {"c", "d", "h", "s"};
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "a", "j", "q", "k"};

    private List<Player> players;
    private List<Card> deck;
    private List<Card> center;
    private int currentPlayerIndex;
    private int trickCount;
    private int[] scores;
    private int currentTrickPlayerIndex;
    private int getPlayer1Score;
    private int getPlayer2Score;
    private int getPlayer3Score;
    private int getPlayer4Score;

    private String centerLeadCard;
    private String center2Card;
    private String center3Card;
    private String center4Card;
    private String center5Card;

    private String currentPlayerName;
    private String playedCardG;
    private String winnerGetName;
    private String FinalwinnerGetName;
    private List<List<Card>> HandCards;
    private List<Integer> playerScores;
    int lowestScore;
    List<Player> lowestScoringPlayers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcomeTextView = findViewById(R.id.Welcome);
        welcomeTextView.setText("Welcome To Go Boom Game!");
//        EditText userInputEditText = findViewById(R.id.userInputEditText);
//        CharSequence userInput = userInputEditText.getText();
//        String userInputString = userInput.toString();
        leadCardImageView = findViewById(R.id.LeadCard);
        centerCard2ImageView = findViewById(R.id.Center_card2);
        centerCard3ImageView = findViewById(R.id.Center_card3);
        centerCard4ImageView = findViewById(R.id.Center_card4);
        centerCard5ImageView = findViewById(R.id.Center_card5);

        trickTextView = findViewById(R.id.Trick);
        deckTextView = findViewById(R.id.Deck);

        playersTurnTextView = findViewById(R.id.PlayersTurn);
//        player1TextView = findViewById(R.id.player1TextView);
//        player2TextView = findViewById(R.id.player2TextView);
//        player3TextView = findViewById(R.id.player3TextView);
//        player4TextView = findViewById(R.id.player4TextView);

        start();


        Card1 = findViewById(R.id.Card1);
        Card2 = findViewById(R.id.Card2);
        Card3 = findViewById(R.id.Card3);
        Card4 = findViewById(R.id.Card4);
        Card5 = findViewById(R.id.Card5);
        Card6 = findViewById(R.id.Card6);
        Card7 = findViewById(R.id.Card7);
//        Player player = new Player(y);
//        player.waitForUserInput(this); // Pass the MainActivity instance as an argument



        Card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int buttonValue = Integer.parseInt(Card1.getTag().toString());
                y = buttonValue;
                displayResult();
            }
        });
        Card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int buttonValue = Integer.parseInt(Card2.getTag().toString());
                y = buttonValue;
                displayResult();
            }
        });
        Card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int buttonValue = Integer.parseInt(Card3.getTag().toString());
                y = buttonValue;
                displayResult();
            }
        });
        Card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int buttonValue = Integer.parseInt(Card4.getTag().toString());
                y =buttonValue;
                displayResult();
            }
        });
        Card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int buttonValue = Integer.parseInt(Card5.getTag().toString());
                y = buttonValue;
                displayResult();
            }
        });
        Card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int buttonValue = Integer.parseInt(Card6.getTag().toString());
                y = buttonValue;
                displayResult();
            }
        });
        Card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int buttonValue = Integer.parseInt(Card7.getTag().toString());
                y = buttonValue;
                displayResult();
            }
        });

    }
    private void displayResult() {
        PlayedCard = findViewById(R.id.PlayedCard);
        PlayedCard.setText("Played card: " + y);


    }

    public interface ButtonClickListener {
        void onButtonClicked(int buttonValue);
    }

    private ButtonClickListener buttonClickListener;

    // Method to set the button click listener
    public void setButtonClickListener(ButtonClickListener listener) {
        buttonClickListener = listener;
    }

    //gbg codes here
    public MainActivity() {
        players = new ArrayList<>();
        deck = new ArrayList<>();
        center = new ArrayList<>();
        playerScores = new ArrayList<>();
        currentPlayerIndex = 0;
        trickCount = 1;
        scores = new int[4];
    }

    public int getTrickCount() {
        return trickCount;
    }
    public List<Card> getDeck() {
        return deck;
    }
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    //center cards
    public String getCenterLeadCard() {
        try {
            centerLeadCard = center.get(0).toString();
            Log.v("Testing", "Center1Cards: " + centerLeadCard);
        } catch (java.lang.IndexOutOfBoundsException e) {
            centerLeadCard = "back"; // Replace "default_card" with the desired default card resource name
            Log.v("Testing", "C1cards: " + centerLeadCard);
        }
        leadCardImageView.setImageResource(getResources().getIdentifier(centerLeadCard, "drawable", getPackageName()));
        return centerLeadCard;
    }
    public String getCenter2Cards() {
            try {
                center2Card = center.get(1).toString();
                Log.v("Testing", "Center2Cards: " + center2Card);
            } catch (java.lang.IndexOutOfBoundsException e) {
                center2Card = "back"; // Replace "default_card" with the desired default card resource name
                Log.v("Testing", "C2cards: " + center2Card);
            }
        centerCard2ImageView.setImageResource(getResources().getIdentifier(center2Card, "drawable", getPackageName()));
            return center2Card;
    }

    public String getCenter3Cards() {
        try {
            center3Card = center.get(2).toString();
            Log.v("Testing", "Center2Cards: " + center3Card);
        } catch (java.lang.IndexOutOfBoundsException e) {
            center3Card = "back"; // Replace "default_card" with the desired default card resource name
            Log.v("Testing", "C3cards: " + center3Card);
        }
        centerCard3ImageView.setImageResource(getResources().getIdentifier(center3Card, "drawable", getPackageName()));
        return center3Card;
    }

    public String getCenter4Cards() {
        try {
            center4Card = center.get(3).toString();
            Log.v("Testing", "Center2Cards: " + center4Card);
        } catch (java.lang.IndexOutOfBoundsException e) {
            center4Card = "back"; // Replace "default_card" with the desired default card resource name
            Log.v("Testing", "C4cards: " + center4Card);
        }

        centerCard4ImageView.setImageResource(getResources().getIdentifier(center4Card, "drawable", getPackageName()));
        return center4Card;
    }

    public String getCenter5Cards() {
        try {
            center5Card = center.get(4).toString();
            Log.v("Testing", "Center2Cards: " + center5Card);
        } catch (java.lang.IndexOutOfBoundsException e) {
            center5Card = "back"; // Replace "default_card" with the desired default card resource name
            Log.v("Testing", "C5cards: " + center5Card);
        }
        centerCard5ImageView.setImageResource(getResources().getIdentifier(center5Card, "drawable", getPackageName()));
        return center5Card;
    }


    private void displayScores() {
        Log.v("Testing", "Score: ");
        List<Integer> playerScoresIn = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            Log.v("Testing", players.get(i).getName() + " = " + scores[i] + " | ");
            playerScoresIn.add(scores[i]);
        }
    }
    public List<Integer> getPlayerScores(List<Integer> playerScoresIn) {
        playerScores.clear();
        playerScores.addAll(playerScoresIn);
        getPlayer1Score = playerScores.get(0);
        getPlayer2Score = playerScores.get(1);
        getPlayer3Score = playerScores.get(2);
        getPlayer4Score = playerScores.get(3);

        return playerScores;
    }

    public int getPlayer1Score() {
        return getPlayer1Score;
    }
    public int getPlayer2Score() {
        return getPlayer2Score;
    }
    public int getPlayer3Score() {
        return getPlayer3Score;
    }
    public int getPlayer4Score() {
        return getPlayer4Score;
    }

    public String getCurrentPlayerName() {
        return currentPlayerName;
    }

    public String getplayedCardG() {
        return playedCardG;
    }
    public String getWinnerName() {
        return winnerGetName;
    }
    public String getFinalwinnerGetName() {
        return FinalwinnerGetName;
    }
    public List<List<Card>> getPlayerHands() {
        List<List<Card>> HandCards = new ArrayList<>();
        for (Player player : players) {
            HandCards.add(player.getHand());
        }
        return HandCards;
    }
    public List<Card> getCenter() {
        return center;
    }

    private void playGame() {
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
        createDeck();
        shuffleDeck();
        dealCards();

        Card leadCard = getLeadCard();
        center.add(leadCard);

        // Determine the first player based on the lead card
        currentPlayerIndex = determineFirstPlayerIndex(leadCard);
        centerLeadCard = leadCard.toString();

        Log.v("Testing", "At the beginning of the game, the first lead card " + leadCard + " is placed at the center.");
        Log.v("Testing", "Player " + (getCurrentPlayerIndex() + 1) + " is the first player because of " + centerLeadCard);

        displayGameState();

//h
//        while (!deck.isEmpty()) {
//            currentTrickPlayerIndex = currentPlayerIndex;
//            do {
//                Player currentPlayer = players.get(currentTrickPlayerIndex);
//                Log.v("Testing", "Do run Player " + (getCurrentPlayerIndex() + 1) + " is the first player because of " + centerLeadCard);
//
//                Card playedCard = currentPlayer.playCard(leadCard, deck, this);
//                currentPlayer.removeCardFromHand(playedCard);
//                center.add(playedCard);
//
//                currentPlayerName = currentPlayer.getName();
//                playedCardG = playedCard.toString();
//                Log.v("Testing", currentPlayer.getName() + " plays " + playedCard);
//                Log.v("Testing", getCurrentPlayerName() + " plays " + getplayedCardG());
//                if (leadCard == null) {
//                    leadCard = playedCard;
//                }
//                centerLeadCard = leadCard.toString();
//                Log.v("Testing", currentPlayer.getName() + " plays " + playedCard);
//                Log.v("Testing", "leadsCard:  " + getCenterLeadCard());
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
//            Log.v("Testing", "*** " + winner.getName() + " wins Trick #" + trickCount + " ***");
//            Log.v("Testing", "*** " + getWinnerName() + " wins Trick #" + getTrickCount() + " ***");
//
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
//        Log.v("Testing", "Renewing Cards and Deck...");
//        deck.clear();
//        for (Player player : players) {
//            player.clearHand();
//        }
//        trickCount = 1;
        //h
    }



    public void end() {
        FinalwinnerGetName = lowestScoringPlayers.get(0).toString();
        Log.v("Testing", "CONGRATULATIONS!! Player " + lowestScoringPlayers.get(0) + " WIN the GAME!! ");
        Log.v("Testing", "CONGRATULATIONS!!!! Player " + getFinalwinnerGetName() + " WIN the GAME!! ");
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
            }
        }
        winnerIndex = (currentPlayerIndex + winnerIndex) % players.size();
        return winnerIndex;
    }

    private int determineFirstPlayerIndex(Card leadCard) {
        String rank = leadCard.getRank();
        if (rank.equals("a") || rank.equals("5") || rank.equals("9") || rank.equals("k")) {
            return 0; // Player1
        } else if (rank.equals("2") || rank.equals("6") || rank.equals("10")) {
            return 1; // Player2
        } else if (rank.equals("3") || rank.equals("7") || rank.equals("j")) {
            return 2; // Player3
        } else if (rank.equals("4") || rank.equals("8") || rank.equals("q")) {
            return 3; // Player4
        }
        return -1;
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

    public void displayGameState() {
        Log.v("Testing", "Trick #" + trickCount);
        Log.v("Testing", "Trick #" + getTrickCount());
        Log.v("Testing", "Deck : " + deck);
        Log.v("Testing", "Deck : " + getDeck());
        trickTextView.setText("Trick: " + trickCount);
        deckTextView.setText("Deck: " + deck.toString());

        for (Player player : players) {
            Log.v("Testing", player.getName() + ": " + player.getHand());
        }
        Log.v("Testing", "Center : " + center);
        Log.v("Testing", "Center : " + getCenter());
        getCenterLeadCard();
        getCenter2Cards();
        getCenter3Cards();
        getCenter4Cards();
        getCenter5Cards();



        //displayScores();

    }

    private Card getLeadCard() {

        return deck.remove(deck.size() - 1);
    }

//    public static void main(String[] args) {
//        MainActivity game = new MainActivity();
//        game.playGame();
//    }

    //player

}
