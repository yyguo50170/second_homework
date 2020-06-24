package com.mycompany;

import java.util.TreeMap;

public class BlackCard {
        Integer cardLevel;
        Integer maxValue;
        String[] cards;
        TreeMap<Integer,Integer> cardsMap;

        public BlackCard(Integer cardLevel, Integer maxValue, String[] cards, TreeMap<Integer, Integer> cardsMap) {
            this.cardLevel = cardLevel;
            this.maxValue = maxValue;
            this.cards = cards;
            this.cardsMap = cardsMap;
        }
}
