package com.edu.cnu.poker;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;

import static com.edu.cnu.poker.Suit.DIAMONDS;

/**
 * Created by cse on 2017-04-17.
 */
public class Evaluator {
    public String evaluate(List<Card> cardList) {
        Map<Suit, Integer> tempMap = new HashMap<Suit, Integer>();
        Map<Integer, Integer> tempMap2 = new HashMap<Integer, Integer>();
        boolean isFLUSH=false;
        Collections.sort(cardList);
        int before_Card_Rank=cardList.get(0).getRank()-1;
        int isMOUNTAIN = cardList.get(4).getRank()-cardList.get(0).getRank();

        if(isMOUNTAIN==12){
            cardList.get(0).setRank(14);
            Collections.sort(cardList);
            before_Card_Rank=cardList.get(0).getRank()-1;
        }

        for (Card card : cardList) {
            if (tempMap2.containsKey(card.getRank())){ //카드들의 숫자별로 갯수를 저장
                Integer count = tempMap2.get(card.getRank());
                count = new Integer(count.intValue() + 1);
                tempMap2.put(card.getRank(), count);
            }else {
                tempMap2.put(card.getRank(), new Integer(1));
            }
            if (tempMap.containsKey(card.getSuit())) {
                Integer count = tempMap.get(card.getSuit());
                count = new Integer(count.intValue() + 1);
                tempMap.put(card.getSuit(), count);
            } else {
                tempMap.put(card.getSuit(), new Integer(1));
            }
        }


        for (Suit key : tempMap.keySet()) {
            if (tempMap.get(key) == 5) {
                isFLUSH=true;
            }
        }

        for(int i=0; i<cardList.size(); i++){
            if((cardList.get(i).getRank()-before_Card_Rank==1) && tempMap2.size()==5){
                before_Card_Rank=cardList.get(i).getRank();
                if(i==4){
                    if(cardList.get(4).getRank()==5){
                        if(isFLUSH){
                            return "BACKSTRAIGHTFLUSH";
                        }
                        return "BACKSTRAIGHT";
                    }
                    else if(cardList.get(4).getRank()==14){
                        if(isFLUSH){
                            return "ROYALSTRAIGHTFLUSH";
                        }
                        return "MOUNTAIN";
                    }
                    else {
                        if(isFLUSH){
                            return "STRAIGHTFLUSH";
                        }
                        return "STRAIGHT";
                    }
                }
            }
        }




        for(Integer rank : tempMap2.keySet()){
            Integer o_count = 0; //원페어의 갯수
            boolean isTRIPLE = false; //트리플 여부
            for(Integer rank2 : tempMap2.keySet()) {//원페어들의 갯수를 체크
                if (tempMap2.get(rank2) == 2) {
                    o_count++;
                }
                else if (tempMap2.get(rank2) == 3) {//트리플 여부 체크
                    isTRIPLE = true;
                }
            }
            if (isTRIPLE) {
                if (o_count == 1){
                    return "FULL_HOUSE";
                }
                else
                    return "TRIPLE";
            }
            else if(tempMap2.get(rank) == 4){
                return "FOUR_CARD";
            }
            else if(o_count == 2){
                return "TWO_PAIR";
            }
            else if(o_count == 1){
                return "ONE_PAIR";
            }
        }


        if(isFLUSH){
            return "FLUSH";
        }
        return "TOP";
    }
}