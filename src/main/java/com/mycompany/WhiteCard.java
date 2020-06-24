package com.mycompany;

import java.util.TreeMap;

public class WhiteCard {
        Integer cardLevel;
        Integer maxValue;
        String[] cards;
        TreeMap<Integer,Integer> cardsMap;

        public WhiteCard(Integer cardLevel, Integer maxValue, String[] cards, TreeMap<Integer, Integer> cardsMap) {
            this.cardLevel = cardLevel;
            this.maxValue = maxValue;
            this.cards = cards;
            this.cardsMap = cardsMap;
        }
}
