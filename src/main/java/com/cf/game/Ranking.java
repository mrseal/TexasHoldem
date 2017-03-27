package com.cf.game;

public enum Ranking {

    // 皇家同花顺
    ROYAL_FLUSH(10),

    // 同花顺
    STRAIGHT_FLUSH(9),

    // 四条
    FOUR_OF_A_KIND(8),

    // 葫芦（三带二）
    FULL_HOUSE(7),

    // 同花
    FLUSH(6),

    // 顺子
    STRAIGHT(5),

    // 三条
    THREE_OF_A_KIND(4),

    // 两对
    TWO_PAIRS(3),

    // 一对
    ONE_PAIR(2),

    // 高牌（散牌）
    HIGH_HAND(1);

    private int value;

    private Ranking(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}