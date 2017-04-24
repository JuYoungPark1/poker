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
        int continuousRank = 1;
        int incrementRank = 1;
        int beforeRank = 0;

        for (Card card : cardList) {

            if (tempMap.containsKey(card.getSuit())) {
                Integer count = tempMap.get(card.getSuit());
                count = new Integer(count.intValue() + 1);
                tempMap.put(card.getSuit(), count);
                if (beforeRank == 0) {
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
            }

            System.out.println( );
            // TRIPLE 구현부분 - 최승환
            if (tempMap.containsValue(card.getRank())) {
                if (beforeRank == 0) {
                    beforeRank = card.getRank();
                } else {
                    // 덱의 시작부터 끝까지 전 숫자와 현재 숫자가 같을 시 continuousRank 1 증가시킴
                    if (beforeRank == card.getRank()) {
                        continuousRank++;
                        beforeRank = card.getRank();
                    }
                }
            }
        }

        // FLUSH 검사 부분에서 incrementRank를 추가로 검사하여 5에 해당할 시 스트레이트 플러시, 아니면 일반 플러시로 return함.
        for (Suit key : tempMap.keySet()) {
            if (tempMap.get(key) == 5) {
                if (incrementRank == 5) {
                    return "STRAIGHTFLUSH";
                } else {
                    return "FLUSH";
                }
            }
        }

        if (continuousRank == 3) {
            return "TRIPLE";
        }
        return "NOTHING";
    }
}
