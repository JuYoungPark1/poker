package com.edu.cnu.poker;

import java.util.*;

/**
 * Created by cse on 2017-04-17.
 */


public class Evaluator {
    Map<Suit, Integer> suit_Count_Map;
    Map<Integer, Integer> rank_Count_Map;
    int before_Card_Rank;
    int isMOUNTAIN;

    public String evaluate(List<Card> cardList) {

        startInitialValue(cardList);
        for (Card card : cardList) {
            create_Rank_Map(card);
            create_Suit_Map(card);
        }



        for(int i=0; i<cardList.size(); i++){
            if((cardList.get(i).getRank()-before_Card_Rank==1) && rank_Count_Map.size()==5){
                before_Card_Rank=cardList.get(i).getRank();
                if(i==4){
                    if(cardList.get(4).getRank()==5){
                        if(isFLUSH()){
                            return "BACKSTRAIGHTFLUSH";
                        }
                        return "BACKSTRAIGHT";
                    }
                    else if(cardList.get(4).getRank()==14){
                        if(isFLUSH()){
                            return "ROYALSTRAIGHTFLUSH";
                        }
                        return "MOUNTAIN";
                    }
                    else {
                        if(isFLUSH()){
                            return "STRAIGHTFLUSH";
                        }
                        return "STRAIGHT";
                    }
                }
            }
        }




        for(Integer rank : rank_Count_Map.keySet()){
            Integer one_pair_count = 0; //원페어의 갯수
            boolean isTRIPLE = false; //트리플 여부
            for(Integer rank2 : rank_Count_Map.keySet()) {//원페어들의 갯수를 체크
                if (rank_Count_Map.get(rank2) == 2) {
                    one_pair_count++;
                }
                else if (rank_Count_Map.get(rank2) == 3) {//트리플 여부 체크
                    isTRIPLE = true;
                }
            }
            if (isTRIPLE) {
                if (one_pair_count == 1){
                    return "FULL_HOUSE";
                }
                else
                    return "TRIPLE";
            }
            else if(rank_Count_Map.get(rank) == 4){
                return "FOUR_CARD";
            }
            else if(one_pair_count == 2){
                return "TWO_PAIR";
            }
            else if(one_pair_count == 1){
                return "ONE_PAIR";
            }
        }


        if(isFLUSH()){
            return "FLUSH";
        }
        return "TOP";
    }

    private boolean isFLUSH() {
        for (Suit key : suit_Count_Map.keySet()) {
            if (suit_Count_Map.get(key) == 5) {
                return true;
            }
        }
        return false;
    }

    private void create_Suit_Map(Card card) {
        if (suit_Count_Map.containsKey(card.getSuit())) {
            Integer count = suit_Count_Map.get(card.getSuit());
            count = new Integer(count.intValue() + 1);
            suit_Count_Map.put(card.getSuit(), count);
        } else {
            suit_Count_Map.put(card.getSuit(), new Integer(1));
        }
    }

    private void create_Rank_Map(Card card) {
        if (rank_Count_Map.containsKey(card.getRank())){ //카드들의 숫자별로 갯수를 저장
            Integer count = rank_Count_Map.get(card.getRank());
            count = new Integer(count.intValue() + 1);
            rank_Count_Map.put(card.getRank(), count);
        }else {
            rank_Count_Map.put(card.getRank(), new Integer(1));
        }
    }

    private void startInitialValue(List<Card> cardList) {
        suit_Count_Map = new HashMap<Suit, Integer>();
        rank_Count_Map = new HashMap<Integer, Integer>();
        Collections.sort(cardList);
        before_Card_Rank=cardList.get(0).getRank()-1;
        isMOUNTAIN = cardList.get(4).getRank()-cardList.get(0).getRank();
        if(isMOUNTAIN==12){
            cardList.get(0).setRank(14);
            Collections.sort(cardList);
            before_Card_Rank=cardList.get(0).getRank()-1;
        }
    }
}