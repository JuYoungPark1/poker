package com.edu.cnu.poker;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.edu.cnu.poker.Suit.DIAMONDS;

/**
 * Created by cse on 2017-04-17.
 */
public class Evaluator {
    public String evaluate(List<Card> cardList) {
        Map<Suit, Integer> tempMap = new HashMap<Suit, Integer>();
        Map<Integer, Integer> tempMap2 = new HashMap<Integer, Integer>();
        int continuousRank = 1;
        int incrementRank = 1;
        int beforeRank = 0;
        boolean firstWasA = false;
        boolean sameRank = false;

        for (Card card : cardList) {

            if (card.getRank() == 1 ) {
                firstWasA = true;
            }

            if (beforeRank == card.getRank()) {
                sameRank = true;
            } else {
                sameRank = false;
            }
            if (tempMap2.containsKey(card.getRank())){
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
                if (beforeRank == 0) {
                    // 이전 카드를 현재 카드로 갱신
                    beforeRank = card.getRank();
                } else {
                    if (beforeRank + 1 == card.getRank()) {
                        // 덱의 전 숫자와 현재숫자가 1 차이로 증가할 시 incrementRank 1 증가시킴
                        incrementRank++;
                    }
                    beforeRank = card.getRank();
                }

            } else {
                tempMap.put(card.getSuit(), new Integer(1));
                beforeRank = card.getRank();
            }

        }
        for(Integer rank : tempMap2.keySet()){
            Integer count = 0; //원페어의 갯수
            for(Integer rank2 : tempMap2.keySet()) {//원페어들의 갯수를 체크
                if (tempMap2.get(rank2) == 2) {
                    count++;
                }
            }
            if (tempMap2.get(rank) == 3) {
                return "TRIPLE";
            }
            else if(tempMap2.get(rank) == 4){
                return "FOUR_CARD";
            }
            else if(count == 1){
                return "ONE_PAIR";
            }
            else if(count == 2){
                return "TWO_PAIR";
            }
        }

        for (Suit key : tempMap.keySet()) {
            // 세개의 카드를 확인하고 이들이 동일한 Rank를 가졌을 때 TRIPLE return.


            // FLUSH 검사 부분에서 incrementRank가 5, 첫 Rank가 A 일시 백 스트레이트 플러시
            // 아닌 경우 스트레이트 플러시, 그것도 아니면 일반 플러시로 return함.
            if (tempMap.get(key) == 5) {
                if (incrementRank == 5) {
                    if (firstWasA) {
                        return "BACKSTRAIGHTFLUSH";
                    } else {
                        return "STRAIGHTFLUSH";
                    }
                } else {
                    return "FLUSH";
                }
            }
        }
        
        return "NOTHING";
    }
}
