package com.example.gbg;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Player {
    public void waitForUserInput(MainActivity activity) {
        // Create a CountDownLatch with a count of 1
        CountDownLatch latch = new CountDownLatch(1);

        // Create a button click listener
        MainActivity.ButtonClickListener buttonClickListener = new MainActivity.ButtonClickListener() {
            @Override
            public void onButtonClicked(int buttonValue) {
                // Set the chosen button value as the 'yValue'
                yValue = buttonValue;

                // Release the latch to resume program execution
                latch.countDown();
            }
        };

        // Set the button click listener in the MainActivity
        activity.setButtonClickListener(buttonClickListener);

        try {
            // Wait for the latch to be released
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int yValue;

    public Player(int y) {
        yValue = y;
    }

    public int getValueY() {
        return yValue;
    }
    private String playerName;
    private List<Card> hand;
    private  MainActivity activity;


    public Player(String playerName) {
        this.playerName = playerName;
        this.activity = activity;
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
    private static int chosenCardIndex;

    public static void setChosenCardIndex(int chosenCardIndex) {
        Player.chosenCardIndex = chosenCardIndex;
    }


    public Card playCard(Card leadCard, List<Card> deck, MainActivity activity) {
        Card chosenCard = null;

       // Scanner scanner = new Scanner(System.in);
        Log.v("Testing", "Turn : " + playerName);
        if (activity.getTrickCount() > 1 && leadCard == null) {
            while (chosenCard == null) {
                activity.displayGameState();
                Log.v("Testing", "Turn : " + playerName);
                Log.v("Testing", "Now it's " + playerName + "'s turn. Please choose the lead card:");
                displayHand();
                try{
                    activity.getPlayerHands().get(3).get(6);
                    Log.v("Testing", "Showing last card for P4" + activity.getPlayerHands().get(3).get(6));
                }catch(java.lang.IndexOutOfBoundsException e){
                    Log.v("Testing", "BackCard");
                }

                //int chosenCardIndex = scanner.nextInt() - 1;
                waitForUserInput(activity);
                int chosenCardIndex = getValueY() - 1;
                Log.v("Testing", "chosenCard1 (Must correct):" + chosenCardIndex);

                chosenCard = hand.get(chosenCardIndex);
                removeCardFromHand(chosenCard);

                leadCard = chosenCard;
                Log.v("Testing", "trick:" + activity.getTrickCount() + "leadCard: " +
                        leadCard);
            }
        } else if (getValidCards(leadCard).isEmpty() && leadCard != null) {
            Log.v("Testing", "Turn : " + playerName);
            Log.v("Testing", "No valid cards to play. Drawing a card...");

            Card drawnCard = drawCard(deck, leadCard);
            Log.v("Testing", getName() + " draws " + drawnCard);
            activity.displayGameState();
            return drawnCard;
        } else {
            while (chosenCard == null) {
                activity.displayGameState();
                Log.v("Testing", "Turn : " + playerName);
                Log.v("Testing", "Now it's " + playerName + "'s turn. Please choose a card to play:");
                displayHand();

                try{
                    activity.getPlayerHands().get(3).get(6);
                    Log.v("Testing", "Showing last card for P4 " + activity.getPlayerHands().get(3).get(6));
                }catch(java.lang.IndexOutOfBoundsException e){
                    Log.v("Testing", "BackCard");
                }
                //int chosenCardIndex = scanner.nextInt() - 1;
               waitForUserInput(activity);
                int chosenCardIndex = getValueY() - 1;
                Log.v("Testing", "chosenCard2 (Must correct):" + chosenCardIndex);

                if (chosenCardIndex >= 0 && chosenCardIndex < hand.size()) {
                    chosenCard = hand.get(chosenCardIndex);
                    if (!isValidCard(chosenCard, leadCard)) {
                        Log.v("Testing", "Invalid card. Please choose a valid card.");
                        chosenCard = null;
                    } else {
                        removeCardFromHand(chosenCard);
                    }
                } else {
                    Log.v("Testing", "Invalid card. Please choose a valid card.");
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

    private void displayHand() {
        for (int i = 0; i < hand.size(); i++) {
            //System.out.println((i + 1) + ": " + hand.get(i));
        }
    }

    @Override
    public String toString() {
        return playerName;
    }
}