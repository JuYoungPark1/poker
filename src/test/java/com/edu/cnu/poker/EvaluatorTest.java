package com.edu.cnu.poker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by cse on 2017-04-17.
 */
public class EvaluatorTest {

    @Test
    public void 숫자가_같은카드가3장이면_트리플이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1, Suit.CLUBS),
                new Card(1, Suit.DIAMONDS),
                new Card(1, Suit.HEARTS),
                new Card(2, Suit.HEARTS),
                new Card(3, Suit.HEARTS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("TRIPLE"));
    }

    @Test
    public void 숫자가_연속되고_SUIT가_5개가동일하면_스트레이트플러쉬다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(3, Suit.CLUBS),
                new Card(4, Suit.CLUBS),
                new Card(5, Suit.CLUBS),
                new Card(6, Suit.CLUBS),
                new Card(7, Suit.CLUBS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("STRAIGHTFLUSH"));
    }

    @Test
    public void 숫자가_A부터시작돼_연속되고_SUIT가_5개가동일하면_백스트레이트플러쉬다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1, Suit.CLUBS),
                new Card(2, Suit.CLUBS),
                new Card(3, Suit.CLUBS),
                new Card(4, Suit.CLUBS),
                new Card(5, Suit.CLUBS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("BACKSTRAIGHTFLUSH"));
    }


    @Test
    public void SUIT가_5개가동일하면_플러쉬다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1,Suit.CLUBS),
                new Card(4,Suit.CLUBS),
                new Card(8,Suit.CLUBS),
                new Card(13,Suit.CLUBS),
                new Card(2,Suit.CLUBS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("FLUSH"));
    }

    @Test
    public void 숫자4개가_같으면_포카드다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(4,Suit.CLUBS),
                new Card(4,Suit.SPADES),
                new Card(5,Suit.DIAMONDS),
                new Card(4,Suit.HEARTS),
                new Card(4,Suit.DIAMONDS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("FOUR_CARD"));
    }

    @Test
    public void 숫자2개가_같으면_원페어다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(4,Suit.CLUBS),
                new Card(4,Suit.SPADES),
                new Card(5,Suit.DIAMONDS),
                new Card(6,Suit.HEARTS),
                new Card(7,Suit.DIAMONDS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("ONE_PAIR"));
    }
    @Test
    public void 숫자2개가_2번_같으면_투페어다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(4,Suit.CLUBS),
                new Card(4,Suit.SPADES),
                new Card(5,Suit.DIAMONDS),
                new Card(5,Suit.HEARTS),
                new Card(7,Suit.DIAMONDS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("TWO_PAIR"));
    }

    @Test
    public void 숫자2개가_1번_같고_숫자3개가_같으면_풀하우스다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(4,Suit.CLUBS),
                new Card(5,Suit.SPADES),
                new Card(5,Suit.DIAMONDS),
                new Card(5,Suit.HEARTS),
                new Card(4,Suit.DIAMONDS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("FULL_HOUSE"));
    }

    @Test
    public void SUIT가_5개동일하고_숫자가_10_J_Q_K_A가_연달아있으면_로티플이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(10, Suit.SPADES),
                new Card(11, Suit.SPADES),
                new Card(12, Suit.SPADES),
                new Card(13, Suit.SPADES),
                new Card(1, Suit.SPADES)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("ROYALSTRAIGHTFLUSH"));
    }

    @Test
    public void 아무런_패도_없으면_탑이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(4, Suit.HEARTS),
                new Card(11, Suit.SPADES),
                new Card(7, Suit.CLUBS),
                new Card(13, Suit.SPADES),
                new Card(1, Suit.SPADES)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("TOP"));
    }
    @Test
    public void 숫자가_연속되게_실행되면_스트레이트이다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(3, Suit.DIAMONDS),
                new Card(4, Suit.SPADES),
                new Card(5, Suit.HEARTS),
                new Card(6, Suit.SPADES),
                new Card(7, Suit.CLUBS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("STRAIGHT"));
    }
    @Test
    public void 숫자가_A부터시작돼_연속되면_백스트레이트이다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(3, Suit.DIAMONDS),
                new Card(2, Suit.SPADES),
                new Card(1, Suit.HEARTS),
                new Card(4, Suit.SPADES),
                new Card(5, Suit.CLUBS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("BACKSTRAIGHT"));
    }
    @Test
    public void 숫자가_10부터시작돼_연속이면_마운틴이다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(10, Suit.DIAMONDS),
                new Card(11, Suit.SPADES),
                new Card(12, Suit.HEARTS),
                new Card(13, Suit.SPADES),
                new Card(1, Suit.CLUBS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("MOUNTAIN"));
    }

}