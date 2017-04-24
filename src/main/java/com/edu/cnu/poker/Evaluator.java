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
        int currentRank, beforeRank = 0;

        for (Card card : cardList) {
            if (tempMap.containsKey(card.getSuit())) {
                Integer count = tempMap.get(card.getSuit());
                count = new Integer(count.intValue() + 1);
                tempMap.put(card.getSuit(), count);

            } else {
                tempMap.put(card.getSuit(), new Integer(1));
            }

            // TRIPLE 구현부분 - 최승환
            if (tempMap.containsValue(card.getRank())) {
                if (beforeRank == 0) {
                    beforeRank = card.getRank();
                } else {
                    if (beforeRank == card.getRank()) {
                        continuousRank++;
                    }
                }
            }

            //System.out.println("Output : " + tempMap.get("TEST : " + tempMap.get("DIAMONDS")));
        }

        for (Suit key : tempMap.keySet()) {
            if (tempMap.get(key) == 5) {
                return "FLUSH";
            }
        }

        for (Suit key : tempMap.keySet()) {
            if (tempMap.get(key) == 5) {
                return "STRAIGHTFLUSH";
            }
        }

        if (continuousRank == 3) {
            return "TRIPLE";
        }
        return "NOTHING";
    }
}
