package com.mycompany;

import java.util.*;

public class Poker {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please write down Black's Cards:");
        String blackIn = in.nextLine();
        String[] blackCards = blackIn.split(" ");
        System.out.print("Please write down White's Cards:");
        String whiteIn = in.nextLine();
        String[] whiteCards = whiteIn.split(" ");
        in.close();

        //整理黑方的牌
        TreeMap<Integer,Integer> blackCardsMap = turnCardsToTreeMap(blackCards);
        Integer blackLevel = judgeCardLevel(blackCards,blackCardsMap);
        BlackCard blackCard = new BlackCard(blackLevel,blackCardsMap.lastKey(),blackCards,blackCardsMap);

        //整理白方的牌
        TreeMap<Integer,Integer> whiteCardsMap = turnCardsToTreeMap(whiteCards);
        Integer whiteLevel = judgeCardLevel(whiteCards,whiteCardsMap);
        WhiteCard whiteCard = new WhiteCard(whiteLevel,whiteCardsMap.lastKey(),whiteCards,whiteCardsMap);

        System.out.println(judgeWinner(blackCard,whiteCard));
    }
    public static String judgeWinner(BlackCard blackCard,WhiteCard whiteCard){
         if(blackCard.cardLevel > whiteCard.cardLevel)
             return "Black wins";
         else if(blackCard.cardLevel < whiteCard.cardLevel)
             return "White wins";
         else{
             if(blackCard.maxValue > whiteCard.maxValue)
                 return "Black wins";
             else if(blackCard.maxValue < whiteCard.maxValue)
                 return "White wins";
             else {
                 Set<Integer> blackKeys = blackCard.cardsMap.keySet();
                 Iterator<Integer> blackIt = blackKeys.iterator();
                 Integer[] blackValues = new Integer[blackKeys.size()];
                 Set<Integer> whiteKeys = whiteCard.cardsMap.keySet();
                 Iterator<Integer> whiteIt = whiteKeys.iterator();
                 Integer[] whiteValues = new Integer[whiteKeys.size()];
                 int i = 0;
                 while (blackIt.hasNext()) {
                     blackValues[i] = blackIt.next() ;
                     whiteValues[i]=whiteIt.next();
                     i++;
                 }
                 for(i = blackKeys.size()-1;i >= 0;i--){
                     if(blackValues[i] > whiteValues[i])
                         return "Black wins";
                     else if(blackValues[i]< whiteValues[i])
                         return "White wins";
                 }
                 return "Tie";
             }
         }
    }

    public static Integer judgeCardLevel(String[] cards,TreeMap<Integer,Integer> cardsMap){
        Boolean isSameColor =  judgeSameColor(cards);
        Boolean is5Xcontinue = judge5XContCard(cardsMap);
        if(is5Xcontinue && isSameColor)
            return 7;
        else if(isSameColor)
            return 6;
        else if(is5Xcontinue)
            return 5;
        else if(cardsMap.size()== 2)
            return 4;
        else if(cardsMap.size()== 5)
            return 1;
        else if(cardsMap.size()== 4)
            return 2;
        else if(cardsMap.size()== 3){
            Set<Integer> cardValues = cardsMap.keySet();
            Iterator<Integer> it = cardValues.iterator();
            while (it.hasNext()){
                if(cardsMap.get(it.next()) == 3)
                    return 4;
            }
            return 3;
        }
        return 1;
    }

    public static boolean judgeSameColor(String[] cards){
        Set<Character> cardColor = new HashSet<>();
        for(String card:cards){
            cardColor.add(card.charAt(1));
        }
        if(cardColor.size() != 1)
            return false;
        return true;
    }

    public static boolean judge5XContCard(TreeMap<Integer,Integer> cardsMap){
        Set<Integer> valueSet = cardsMap.keySet();
        if(valueSet.size() != 5)
            return false;
        Iterator<Integer> it = valueSet.iterator();
        Integer first = it.next();
        Integer cur = null;
        while (it.hasNext()){
            cur = it.next();
            if(cur != first+1)
                return false;
            first = cur;
        }
        return true;
    }

    public static TreeMap<Integer,Integer> turnCardsToTreeMap(String[] cards){
        TreeMap<Integer,Integer> cardsMap = new TreeMap<>();
        for(String card:cards){
            Character cardValue = card.charAt(0);
            if(Character.isDigit(cardValue)){
                Integer count = cardsMap.get(cardValue-'0');
                if(count != null)
                    cardsMap.put(cardValue-'0',cardsMap.get(cardValue-'0')+1);
                else
                    cardsMap.put(cardValue-'0',1);
            }else{
                switch (cardValue){
                    case 'T': {
                        Integer count = cardsMap.get(10);
                        if (count != null)
                            cardsMap.put(10,count + 1);
                        else
                            cardsMap.put(10, 1);
                        break;
                    }
                    case 'J': {
                        Integer count = cardsMap.get(11);
                        if (count != null)
                            cardsMap.put(11, count + 1);
                        else
                            cardsMap.put(11, 1);
                        break;
                    }
                    case 'Q':{
                        Integer count = cardsMap.get(12);
                        if (count != null)
                            cardsMap.put(12, count + 1);
                        else
                            cardsMap.put(12, 1);
                        break;
                    }
                    case 'K':{
                        Integer count = cardsMap.get(13);
                        if (count != null)
                            cardsMap.put(13, count + 1);
                        else
                            cardsMap.put(13, 1);
                        break;
                    }
                    case 'A':{
                        Integer count = cardsMap.get(14);
                        if (count != null)
                            cardsMap.put(14, count + 1);
                        else
                            cardsMap.put(14, 1);
                        break;
                    }
                }
            }
        }
        return cardsMap;
    }
}