package com.cf.game;

import static com.cf.game.Suit.CLUBS;
import static com.cf.game.Suit.DIAMONDS;
import static com.cf.game.Suit.HEARTS;
import static com.cf.game.Suit.SPADES;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HandsTest {

    private final Poker a14 = new Poker(14, HEARTS);
    private final Poker b14 = new Poker(14, DIAMONDS);
    private final Poker c14 = new Poker(14, SPADES);
    private final Poker d14 = new Poker(14, CLUBS);
    private final Poker a13 = new Poker(13, HEARTS);
    private final Poker a12 = new Poker(12, HEARTS);
    private final Poker c12 = new Poker(12, SPADES);
    private final Poker a11 = new Poker(11, HEARTS);
    private final Poker a10 = new Poker(10, HEARTS);
    private final Poker a9 = new Poker(9, HEARTS);
    private final Poker a3 = new Poker(3, HEARTS);
    private final Poker d10 = new Poker(10, CLUBS);

    @Test
    public void testRoyalFlush() {
        final Hands royalFlush = new Hands(a13, a10, a12, a14, a11);
        assertEquals(Ranking.ROYAL_FLUSH, royalFlush.getRanking());
        assertEquals(14, royalFlush.getValue());
    }

    @Test
    public void testStraightFlush() {
        final Hands straightFlush = new Hands(a13, a10, a12, a9, a11);
        assertEquals(Ranking.STRAIGHT_FLUSH, straightFlush.getRanking());
        assertEquals(13, straightFlush.getValue());
    }

    @Test
    public void testFourOfAKind() {
        final Hands fourOfAKind = new Hands(a14, b14, a9, d14, c14);
        assertEquals(Ranking.FOUR_OF_A_KIND, fourOfAKind.getRanking());
        assertEquals(1409, fourOfAKind.getValue());
    }

    @Test
    public void testFullHouse() {
        final Hands fullHouse = new Hands(a14, b14, a12, d14, c12);
        assertEquals(Ranking.FULL_HOUSE, fullHouse.getRanking());
        assertEquals(1412, fullHouse.getValue());
    }

    @Test
    public void testFlush() {
        final Hands flush = new Hands(a13, a10, a3, a9, a11);
        assertEquals(Ranking.FLUSH, flush.getRanking());
        assertEquals(1311100903, flush.getValue());
    }

    @Test
    public void testStraight() {
        final Hands straight = new Hands(a13, d10, a12, a14, a11);
        assertEquals(Ranking.STRAIGHT, straight.getRanking());
        assertEquals(14, straight.getValue());
    }

    @Test
    public void testThreeOfAKind() {
        final Hands threeOfAKind = new Hands(a14, b14, a3, d14, c12);
        assertEquals(Ranking.THREE_OF_A_KIND, threeOfAKind.getRanking());
        assertEquals(141203, threeOfAKind.getValue());
    }

    @Test
    public void testTwoPairs() {
        final Hands twoPairs = new Hands(a10, b14, d10, d14, c12);
        assertEquals(Ranking.TWO_PAIRS, twoPairs.getRanking());
        assertEquals(141012, twoPairs.getValue());
    }

    @Test
    public void testOnePair() {
        final Hands onePair = new Hands(a10, a3, d10, d14, c12);
        assertEquals(Ranking.ONE_PAIR, onePair.getRanking());
        assertEquals(10141203, onePair.getValue());
    }

    @Test
    public void testHighHand() {
        final Hands highHand = new Hands(a10, b14, a9, a3, c12);
        assertEquals(Ranking.HIGH_HAND, highHand.getRanking());
        assertEquals(1412100903, highHand.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPokerNumber() {
        new Hands(a10, b14);
    }

}
