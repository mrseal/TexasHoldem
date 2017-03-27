package com.cf.game;

public abstract class TexasHoldem {

    /**
     * 比较两副手牌大小
     *
     * @param handsA
     *            手牌A
     * @param handsB
     *            手牌B
     * @return 负数：如果handsA小于handsB; 正数：如果handsA大于handsB; 零：相等
     */
    public static int compare(final String handsA, final String handsB) {
        return getHands(handsA).compareTo(getHands(handsB));
    }

    private static Hands getHands(final String handsStr) {
        final String[] pokerStrs = formatAndVerify(handsStr).split(",");
        final Poker[] pokers = new Poker[pokerStrs.length];
        for (int i = 0; i < pokerStrs.length; i++) {
            pokers[i] = getPoker(pokerStrs[i]);
        }
        return new Hands(pokers);
    }

    private static String formatAndVerify(final String handsStr) {
        final String formattedStr = handsStr.replaceAll("\\s+", "");
        if (!formattedStr.matches("^([a-dA-D]\\d\\d?,){4}[a-dA-D]\\d\\d?$")) {
            throw new IllegalArgumentException("Invalid hands String");
        }
        return formattedStr;
    }

    private static Poker getPoker(final String pokerStr) {
        return new Poker(Integer.valueOf(pokerStr.substring(1)), getSuit(pokerStr.charAt(0)));
    }

    private static Suit getSuit(final char suit) {
        switch (suit) {
        case 'a':
        case 'A':
            return Suit.HEARTS;
        case 'b':
        case 'B':
            return Suit.DIAMONDS;
        case 'c':
        case 'C':
            return Suit.SPADES;
        case 'd':
        case 'D':
            return Suit.CLUBS;
        default:
            return null;
        }
    }

}
