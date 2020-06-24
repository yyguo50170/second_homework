package com.mycompany;

import org.junit.Assert;
import org.junit.Test;

import java.util.TreeMap;

public class TestPoker {


    @Test
    public void shouldGiveTrueWhenGiveSameColor(){
        String[] cards = {"3D","4D","5D","6D","7D"};
        boolean res = Poker.judgeSameColor(cards);
        Assert.assertEquals(true,res);
    }

    @Test
    public void shouldGiveTreeMapWhenGiveStringArray(){
        String[] cards = {"3D","4D","5D","6D","7D"};
        TreeMap<Integer,Integer> cardsMap = Poker.turnCardsToTreeMap(cards);
        System.out.println(cardsMap);
    }

    @Test
    public void shouldGiveTrueWhenGive5XContCards(){
        TreeMap<Integer,Integer> cardsMap = new TreeMap<>();
        cardsMap.put(3,1);
        cardsMap.put(4,1);
        cardsMap.put(5,1);
        cardsMap.put(6,1);
        cardsMap.put(7,1);
        Boolean ret = Poker.judge5XContCard(cardsMap);
        Assert.assertEquals(true,ret);
    }

    @Test
    public void shouldReturnTheCardsLevel(){
        String[] cards = {"3D","4D","5D","6D","7D"};
        TreeMap<Integer,Integer> cardsMap = new TreeMap<>();
        cardsMap.put(3,1);
        cardsMap.put(4,1);
        cardsMap.put(5,1);
        cardsMap.put(6,1);
        cardsMap.put(7,1);
        int ret = Poker.judgeCardLevel(cards,cardsMap);
        Assert.assertEquals(7,ret);
    }

    @Test
    public void shouldGiveWinner(){
        //Black: `2H 3D 5S 9C KD` White: `2C 3H 4S 8C AH`
        //输出:
        //White wins
        String[] blackCards = {"2H","3D","5S","9C","KD"};
        TreeMap<Integer,Integer> blackCardMap = Poker.turnCardsToTreeMap(blackCards);
        Integer blackLevel = Poker.judgeCardLevel(blackCards,blackCardMap);
        BlackCard blackCard = new BlackCard(blackLevel,13,blackCards,blackCardMap);

        String[] whiteCards = {"2C","3H","4S","8C","AH"};
        TreeMap<Integer,Integer> whiteCardMap = Poker.turnCardsToTreeMap(whiteCards);
        Integer whiteLevel = Poker.judgeCardLevel(whiteCards,whiteCardMap);
        WhiteCard whiteCard = new WhiteCard(whiteLevel,14,whiteCards,whiteCardMap);

        String ret = Poker.judgeWinner(blackCard,whiteCard);
        Assert.assertEquals("White wins",ret);
    }
}
