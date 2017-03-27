package com.cf.game;

public class Poker implements Comparable<Poker> {

    int num;
    Suit suit;

    public Poker(final int num, final Suit suit) {
        if (num > 14 || num < 2) {
            throw new IllegalArgumentException("num must be between 2-14");
        }
        if (suit == null) {
            throw new IllegalArgumentException("suit must not be null");
        }
        this.num = num;
        this.suit = suit;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getNum() {
        return num;
    }

    public int compareTo(final Poker o) {
        return num - o.num;
    }

}
