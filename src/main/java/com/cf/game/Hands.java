package com.cf.game;

import java.util.Arrays;
import java.util.Collections;

/**
 * 手牌，一副手牌必须有5张扑克牌
 */
public class Hands implements Comparable<Hands> {

    private final static int POKER_NUM = 5;

    private final Poker[] pokers;
    private Ranking ranking;
    private int value; // int类型可以满足5张手牌。如果要增加手牌数量，需考虑修改value的类型为long

    public Hands(final Poker... pokers) {
        if (pokers.length != POKER_NUM) {
            throw new IllegalArgumentException("One hands must have " + POKER_NUM + " pokers");
        }
        this.pokers = pokers;
        init();
    }

    public Poker[] getPokers() {
        return pokers;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public int getValue() {
        return value;
    }

    public int compareTo(final Hands other) {
        final int rankingCompare = ranking.getValue() - other.getRanking().getValue();
        if (rankingCompare != 0) {
            return rankingCompare;
        }
        return value - other.getValue();
    }

    private void init() {
        final Poker[] sortedPokers = Arrays.copyOf(pokers, pokers.length);
        Arrays.sort(sortedPokers, Collections.reverseOrder());

        // 先判断是否为同花或顺子
        int flushOrStraight = 0;
        if (isFlush(sortedPokers)) {
            flushOrStraight |= 1;
        }
        if (isStraight(sortedPokers)) {
            flushOrStraight |= 2;
        }

        switch (flushOrStraight) {
        case 3: {
            value = sortedPokers[0].getNum();
            if (value == 14) {
                ranking = Ranking.ROYAL_FLUSH;
            } else {
                ranking = Ranking.STRAIGHT_FLUSH;
            }
            return;
        }
        case 2:
            ranking = Ranking.STRAIGHT;
            value = sortedPokers[0].getNum();
            return;
        case 1:
            ranking = Ranking.FLUSH;
            for (final Poker sortedPoker : sortedPokers) {
                value = value * 100 + sortedPoker.getNum();
            }
            return;
        }

        // 判断其它牌型
        final int[] counts = new int[15];
        for (final Poker poker : sortedPokers) {
            counts[poker.getNum()]++;
        }

        int handsPattern = 0;
        final int[][] handsValue = new int[4][POKER_NUM];
        int position = 0;
        for (int i = counts.length - 1; i >= 0; i--) {
            if (counts[i] != 0) {
                handsPattern += counts[i] * (counts[i] + 1) / 2;
                handsValue[counts[i] - 1][position++] = i;
            }
        }

        switch (handsPattern) {
        case 11: {
            ranking = Ranking.FOUR_OF_A_KIND;
            break;
        }
        case 9: {
            ranking = Ranking.FULL_HOUSE;
            break;
        }
        case 8: {
            ranking = Ranking.THREE_OF_A_KIND;
            break;
        }
        case 7: {
            ranking = Ranking.TWO_PAIRS;
            break;
        }
        case 6: {
            ranking = Ranking.ONE_PAIR;
            break;
        }
        default: {
            ranking = Ranking.HIGH_HAND;
            break;
        }
        }

        for (int i = 3; i >= 0; i--) {
            for (int j = 0; j < POKER_NUM; j++) {
                if (handsValue[i][j] != 0) {
                    value = value * 100 + handsValue[i][j];
                }
            }
        }
    }

    // 是否为同花
    private boolean isFlush(final Poker[] sortedPokers) {
        final Suit suit0 = sortedPokers[0].getSuit();
        for (int i = 1; i < sortedPokers.length; i++) {
            if (sortedPokers[i].getSuit() != suit0) {
                return false;
            }
        }
        return true;
    }

    // 是否为顺子
    private boolean isStraight(final Poker[] sortedPokers) {
        for (int i = 1; i < sortedPokers.length; i++) {
            if (sortedPokers[i - 1].getNum() - sortedPokers[i].getNum() != 1) {
                return false;
            }
        }
        return true;
    }
}
