package com.cf.game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PokerTest {

    @Test
    public void testCompare() {
        final Poker a8 = new Poker(8, Suit.HEARTS);
        final Poker d11 = new Poker(11, Suit.CLUBS);
        assertTrue(a8.compareTo(d11) < 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNumTooSmall() {
        new Poker(1, Suit.CLUBS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNumTooBig() {
        new Poker(15, Suit.CLUBS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSuit() {
        new Poker(8, null);
    }

}
