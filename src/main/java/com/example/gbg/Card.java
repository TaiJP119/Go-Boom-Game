package com.example.gbg;

public class Card {
    private String suit;
    private String rank;
    private boolean facedUp; // New variable

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
        this.facedUp = true; // Set facedUp to true by default
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public boolean isFacedUp() {
        return facedUp;
    }

    public void setFacedUp(boolean facedUp) {
        this.facedUp = facedUp;
    }

    public int getRankValue() {
        if (rank.equals("a")) {
            return 14;
        } else if (rank.equals("k")) {
            return 13;
        } else if (rank.equals("q")) {
            return 12;
        } else if (rank.equals("j")) {
            return 11;
        } else if (rank.equals("10")) {
            return 10;
        } else if (rank.equals("9")) {
            return 9;
        } else if (rank.equals("8")) {
            return 8;
        } else if (rank.equals("7")) {
            return 7;
        } else if (rank.equals("6")) {
            return 6;
        } else if (rank.equals("5")) {
            return 5;
        } else if (rank.equals("4")) {
            return 4;
        } else if (rank.equals("3")) {
            return 3;
        } else if (rank.equals("2")) {
            return 2;
        } else {
            throw new IllegalArgumentException("Invalid card rank");
        }
    }

    public int getRankFinalValue() {
        if (rank.equals("a")) {
            return 1;
        } else if (rank.equals("k")) {
            return 10;
        } else if (rank.equals("q")) {
            return 10;
        } else if (rank.equals("j")) {
            return 10;
        } else if (rank.equals("10")) {
            return 10;
        } else if (rank.equals("9")) {
            return 9;
        } else if (rank.equals("8")) {
            return 8;
        } else if (rank.equals("7")) {
            return 7;
        } else if (rank.equals("6")) {
            return 6;
        } else if (rank.equals("5")) {
            return 5;
        } else if (rank.equals("4")) {
            return 4;
        } else if (rank.equals("3")) {
            return 3;
        } else if (rank.equals("2")) {
            return 2;
        } else {
            throw new IllegalArgumentException("Invalid card rank");
        }
    }
    @Override
    public String toString() {
        return suit + rank;
    }
}
