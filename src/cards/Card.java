package cards;

import java.io.Serializable;
import java.util.*;

public class Card implements Comparable<Card>, Serializable {

    //Class is serializable with an ID of 100.
    private static final long serialVersionUID = 100L;
    
    //Class variables of type Rank and Suit.
    private Rank rank;
    private Suit suit;

    //Enum type to store values to each card ranking.
    public enum Rank {

        //Values for a Rank of a card.
        TWO(2), THREE(3), FOUR(4), FIVE(5),
        SIX(6), SEVEN(7), EIGHT(8), NINE(9), 
        TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);

        private int value;

        //Constructor for a Card Rank.
        Rank(int value) {
            this.value = value;
        }

        //Accessor method to return the value of a Rank.
        public int getValue() {
            return value;
        }

        //Accessor method that returns the previous
        //enum value in the list.
        public Rank getPrevious() {
            int previous = ordinal() - 1;
            if (previous < 0) {
                previous += values().length;
            }
            return Rank.values()[previous];
        }
    }

    //Enum type for the Suit variables in a deck of cards.
    public enum Suit {

        //Values for the suit of a card.
        SPADES, HEARTS, DIAMONDS, CLUBS;

        //Accessor method to get a random suit.
        public static Suit getRandomSuit() {
            int randomSuitNumber = (int)
                    (Math.random()*(Suit.values().length));
            return (Suit.values()[randomSuitNumber]);
        }
    }

    //toString method.
    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    //Constructor with Rank and Suit passed as parameters.
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    //Accessor method to return the Rank of a card.
    public Rank getRank() {
        return rank;
    }

    //Accessor method to return the Suit of a card.
    public Suit getSuit() {
        return suit;
    }

    //compareTo() method that sorts the cards into descending order.
    @Override
    public int compareTo(Card o) {
        int suitComparison = this.getSuit().
                compareTo(o.getSuit());
        int rankComparison = this.getRank().
                compareTo(o.getRank());

        if (suitComparison == 0) {
            if (rankComparison < 0) {
                return -1;
            } else {
                return 1;
            }
        }
        return suitComparison;
    }

    //Method that iterates through a List of cards 
    //and returns the card with the highest value.
    public static Card max(ArrayList<Card> list) {
        Iterator<Card> iterate = list.iterator();
        Card highestCard = iterate.next();
        while (iterate.hasNext()) {
            Card currentCard = iterate.next();
            int suitCompare = highestCard.getSuit().
                    compareTo(currentCard.getSuit());
            int rankCompare = highestCard.getRank().
                    compareTo(currentCard.getRank());

            if (suitCompare < 0) {
                highestCard = currentCard;
            } else if (suitCompare == 0) {
                if (rankCompare < 0) {
                    highestCard = currentCard;
                }
            }
        }
        return highestCard;
    }

    //Nested Comparator class that sorts cards into ascending order.
    public static class CompareAscending implements Comparator<Card> {

        @Override
        public int compare(Card a, Card b) {
            int suitComp = a.getSuit().compareTo(b.getSuit());
            int rankComp = a.getRank().compareTo(b.getRank());

            if (suitComp == 0) {
                return rankComp;
            }
            return suitComp;
        }
    }

    //Nested comparator class that sorts cards into order of rank.
    public static class CompareRank implements Comparator<Card> {

        @Override
        public int compare(Card a, Card b) {
            int suitComp = a.getSuit().compareTo(b.getSuit());
            int rankComp = a.getRank().compareTo(b.getRank());

            if (rankComp == 0) {
                return suitComp;
            }
            return rankComp;
        }
    }
    ///***********************CARD TESTING********************

    public static void main(String[] args) {
        Card card1 = new Card(Rank.TWO, Suit.CLUBS);
        Card card2 = new Card(Rank.SIX, Suit.HEARTS);
        Card card3 = new Card(Rank.SEVEN, Suit.SPADES);
        Card card4 = new Card(Rank.KING, Suit.CLUBS);

        System.out.print("Card #1 is:\t\t" + card1 + "\n\n");

        System.out.print("Method\t\t\tOutput\n");
        System.out.println("_____________________"
                + "____________________\n");
        System.out.print("1.getRank()");
        System.out.print("\t\t" + card1.getRank() + "\n");
        System.out.print("1.getPrevious()");
        System.out.print("\t\t" + card1.getRank().
                getPrevious() + "\n");
        System.out.print("1.getSuit()");
        System.out.print("\t\t" + card1.getSuit() + "\n");
        System.out.print("1.getValue()");
        System.out.print("\t\t" + card1.getRank().
                getValue() + "\n");
        System.out.println("_____________________"
                + "____________________\n");
        System.out.print("Card #2 is:\t\t" + card2 + "\n");
        System.out.print("Card #3 is:\t\t" + card3 + "\n");
        System.out.print("Card #4 is:\t\t" + card4 + "\n\n\n");

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        Comparator<Card> rank = new CompareRank();
        Comparator<Card> asc = new CompareAscending();

        System.out.print("Method\t\t\tOutput\n");
        System.out.println("___________________"
                + "______________________\n");
        System.out.print("max()");
        System.out.print("\t\t\t" + max(cards) + "\n");
        System.out.print("1.compareTo(4)");
        System.out.print("\t\t" + card1.
                compareTo(card4) + "\n\n");

        Collections.sort(cards, asc);
        System.out.print("CompareAscending\t");
        for (int i = 0; i < cards.size() - 1; i++) {
            System.out.print(cards.get(i) + "\n");
            System.out.print("\t\t\t");
        }
        System.out.print(cards.get(cards.size() - 1) + "\n\n");

        Collections.sort(cards, rank);
        System.out.print("CompareRank\t\t");
        for (int i = 0; i < cards.size() - 1; i++) {
            System.out.print(cards.get(i) + "\n");
            System.out.print("\t\t\t");
        }
        System.out.print(cards.get(cards.size() - 1) + "\n");
        System.out.print("_________________"
                + "________________________\n");

    }
    //************************************************/
}
