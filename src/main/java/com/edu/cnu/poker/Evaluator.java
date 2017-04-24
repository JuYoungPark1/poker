package com.edu.cnu.poker;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cse on 2017-04-17.
 */
public class Evaluator {
    public String evaluate(List<Card> cardList) {
        Map<Suit, Integer> tempMap = new HashMap<Suit, Integer>();

        for (Card card : cardList) {
            if (tempMap.containsKey(card.getSuit())) {
                Integer count = tempMap.get(card.getSuit());
                count = new Integer(count.intValue() + 1);
                tempMap.put(card.getSuit(), count);

            } else {
                tempMap.put(card.getSuit(), new Integer(1));
            }

            System.out.println("tempMap.keySet() : " + tempMap.keySet());
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

        for (Integer key : tempMap.values()) {
            if (tempMap.get(key) == 3) {
                return "TRIPLE";
            }
        }
        return "NOTHING";
    }
}
