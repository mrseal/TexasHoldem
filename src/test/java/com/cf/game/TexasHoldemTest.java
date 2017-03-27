package com.cf.game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TexasHoldemTest {

    @Test
    public void testCompareHighHands() {
        assertTrue(TexasHoldem.compare("B11, A2, A9, D10, A8", "D4, A8, C5, C6, B12") < 0);
    }

    @Test
    public void testCompareHighHandAndOnePair() {
        assertTrue(TexasHoldem.compare("B14, A3, A9, D10, A8", "D4, A2, C2, C6, B11") < 0);
    }

    @Test
    public void testCompareOnePairs() {
        assertTrue(TexasHoldem.compare("B11, A9, D9, D5, A8", "D4, B10, C10, C6, B12") < 0);
    }

    @Test
    public void testCompareStraights() {
        assertTrue(TexasHoldem.compare("D2, B2, C10, A10, B12", "D5, A6, C7, C8, B9") < 0);
    }

    @Test
    public void testCompareSameRanking() {
        assertTrue(TexasHoldem.compare("D2, B2, C10, A10, B12", "D12, A6, C10, C8, B9") > 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPokerNumber() {
        TexasHoldem.compare("B11", "A8, A4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPoker() {
        TexasHoldem.compare("M11, B2, A10, A14, D1", "D12, A6, C10, C8, B9");
    }

}
