class Card {
    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getRankValue() {
        if (rank.equals("A")) {
            return 14;
        } else if (rank.equals("K")) {
            return 13;
        } else if (rank.equals("Q")) {
            return 12;
        } else if (rank.equals("J")) {
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

    @Override
    public String toString() {
        return suit + rank;
    }
}
