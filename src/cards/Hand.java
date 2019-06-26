package cards;

import java.io.Serializable;
import java.util.*;

public class Hand implements Iterable<Card>, Serializable {

    //Class is serializable with an ID of 300.
    private static final long serialVersionUID = 300L;
    
    public int sizeOfHand;
    public ArrayList<Card> handOfCards; //ArrayList for new hand.
    
    //Variables to keep track of number of suits and value of hand.
    int numberOfSpades = 0;
    int numberOfHearts = 0;
    int numberOfDiamonds = 0;
    int numberOfClubs = 0;
    int valueOfHand = 0;
    
    //Default constructor that creates a new hand.
    public Hand() {
        this.handOfCards = new ArrayList<>();
        this.numberOfSpades = countSuit(Card.Suit.SPADES);;
        this.numberOfHearts = countSuit(Card.Suit.HEARTS);
        this.numberOfDiamonds = countSuit(Card.Suit.DIAMONDS);
        this.numberOfClubs = countSuit(Card.Suit.CLUBS); 
        
        for (Card card : this.handOfCards){
            this.valueOfHand += card.getRank().getValue();
        }    
    }

    //Constructor that takes an array of cards and adds it to an empty hand.
    public Hand(Card[] arrayOfCards) {
        this.handOfCards = new ArrayList<>();
        
        for (Card card : arrayOfCards) {
            this.handOfCards.add(card);
        }
        
        this.numberOfSpades = countSuit(Card.Suit.SPADES);;
        this.numberOfHearts = countSuit(Card.Suit.HEARTS);
        this.numberOfDiamonds = countSuit(Card.Suit.DIAMONDS);
        this.numberOfClubs = countSuit(Card.Suit.CLUBS);
        
        for (Card card : this.handOfCards){
            this.valueOfHand += card.getRank().getValue();
        }
    }

    //Construtor that takes another hand and adds all the cards to this hand.
    public Hand(Hand otherHand) {
        this.handOfCards = new ArrayList<>();
        
        for (Card card : otherHand.handOfCards) {
            this.handOfCards.add(card);
        }
        
        this.numberOfSpades = countSuit(Card.Suit.SPADES);
        this.numberOfHearts = countSuit(Card.Suit.HEARTS);
        this.numberOfDiamonds = countSuit(Card.Suit.DIAMONDS);
        this.numberOfClubs = countSuit(Card.Suit.CLUBS);
        
        for (Card card : this.handOfCards){
            this.valueOfHand += card.getRank().getValue();
        }
    }
    
    //Method to add a single card to a hand.
    public void addCard(Card addedCard) {
        this.handOfCards.add(addedCard);
        
        switch (addedCard.getSuit()){
            case SPADES: this.numberOfSpades++;
                break;
            case HEARTS: this.numberOfHearts++;
                break;
            case DIAMONDS: this.numberOfDiamonds++;
                break;
            case CLUBS: this.numberOfClubs++;
                break;
        }
        
        this.valueOfHand += addedCard.getRank().getValue();
    }

    //Method to add a collection of cards to a hand.
    public void addCards(Collection<Card> addedCards) {
        for (Card card : addedCards) {
            this.handOfCards.add(card);   
            
            switch (card.getSuit()){
            case SPADES: numberOfSpades++;
                break;
            case HEARTS: numberOfHearts++;
                break;
            case DIAMONDS: numberOfDiamonds++;
                break;
            case CLUBS: numberOfClubs++;
                break;
            }
            
            this.valueOfHand += card.getRank().getValue();
            
        }
    }

    //Method to add a another hand to this hand.
    public void addHand(Hand addedHand) {
        for (Card card : addedHand.handOfCards) {
            this.handOfCards.add(card);
            
            switch (card.getSuit()){
            case SPADES: this.numberOfSpades++;
                break;
            case HEARTS: numberOfHearts++;
                break;
            case DIAMONDS: numberOfDiamonds++;
                break;
            case CLUBS: numberOfClubs++;
                break;
            }
            this.valueOfHand += card.getRank().getValue();
        }
    }
    
    //Method to remove a card from a hand.
    public boolean removeCard(Card card) {
        if (handOfCards.contains(card)) { //If the hand contains the card...
                handOfCards.remove(card); //remove it...
            
            switch (card.getSuit()){
                case SPADES: this.numberOfSpades--;
                    break;
                case HEARTS: this.numberOfHearts--;
                    break;
                case DIAMONDS: this.numberOfDiamonds--;
                    break;
                case CLUBS: this.numberOfClubs--;
                    break;
            } 
            
            this.valueOfHand -= card.getRank().getValue();
            
            return true; //and return true.
        }
        return false;
    }

    //Method to remove all cards from another hand passed as an argument.
    public boolean removeHand(Hand other) {
        boolean cardsMatch = false;
        int cardsRemoved = 0;
        int matchedCards = 0;

        for (Card card : other.handOfCards) { //Check if every card is present.
            if (this.handOfCards.contains(card)) {
                matchedCards++;
            }
        }

        if (matchedCards == other.handOfCards.size()) { //If every card is present...
            for (Card card : other.handOfCards) {
                if (this.handOfCards.remove(card)){ //remove every card from the hand.
                    cardsRemoved++;               
                    switch (card.getSuit()){
                        case SPADES: this.numberOfSpades--;
                            break;
                        case HEARTS: this.numberOfHearts--;
                            break;
                        case DIAMONDS: this.numberOfDiamonds--;
                            break;
                        case CLUBS: this.numberOfClubs--;
                            break;
                    }
                    this.valueOfHand -= card.getRank().getValue();
                }
            }
        }
        
        if (cardsRemoved == other.handOfCards.size()) {
            cardsMatch = true;
        } else {
            cardsMatch = false;
        }
        return cardsMatch;
    }

    //Method that removes a card at a given index and returns it.
    public Card removeHere(int index) {
        Card removedCard = this.handOfCards.get(index);
        this.handOfCards.remove(removedCard);
        this.valueOfHand -= removedCard.getRank().getValue();
        switch (removedCard.getSuit()){
            case SPADES: this.numberOfSpades--;
                break;
            case HEARTS: this.numberOfHearts--;
                break;
            case DIAMONDS: this.numberOfDiamonds--;
                break;
            case CLUBS: this.numberOfClubs--;
                break;
        }

        return removedCard;
    }
    
    //Method that sorts a given hand into ascending order.
    public void sort() {
        Comparator compAsc = new Card.CompareAscending();
        Collections.sort(this.handOfCards, compAsc);
    }

    //Method that sorts a given hand into descending order.
    public void sortByRank() {
        Comparator compRank = new Card.CompareRank();
        Collections.sort(this.handOfCards, compRank);
    }


    //Method that returns the number of cards of a given suit in a hand.
    public int countSuit(Card.Suit suit) {
        int numberOfCards = 0;
        for (Card card : handOfCards) {
            if (card.getSuit().equals(suit)) {
                numberOfCards++;
            }
        }
        return numberOfCards;
    }

    //Method that returns the number of cards of a given rank in a hand.
    public int countRank(Card.Rank rank) {
        int numberOfCards = 0;
        for (Card card : handOfCards) {
            if (card.getRank().equals(rank)) {
                numberOfCards++;
            }
        }
        return numberOfCards;
    }

    //Method that takes a given suit and returns true if a hand contains any
    //cards with the given suit.
    public boolean hasSuit(Card.Suit suit) {
        for (Card card : handOfCards) {
            if (card.getSuit().equals(suit)) {
                return true;
            }
        }
        return false;
    }
    
    //toString method to print a hand.
    public String toString(){
        StringBuilder handBuilder = new StringBuilder();
        for (int i = 0; i < handOfCards.size()-1; i++){
            handBuilder.append(handOfCards.get(i)).append(", ");
        }
        handBuilder.append(handOfCards.get(handOfCards.size()-1)).append(".");
        
        return "Your hand is: " + handBuilder.toString();
    }
     

    //Create new iterator.
    @Override
    public Iterator<Card> iterator() {
        return new NestedIterator();
    }

    //Nested iterator class.
    private class NestedIterator implements Iterator<Card> {

        int position = 0;
        @Override
        public boolean hasNext() {
            return (position < handOfCards.size());
        }

        @Override
        public Card next() {
            return handOfCards.get(position++);
        }      
    }
    
    ///***********************HAND TESTING********************************
 
    public static void main (String[] args){
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();
        Card card1 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.SIX, Card.Suit.HEARTS);
        Card card3 = new Card(Card.Rank.SEVEN, Card.Suit.SPADES);
        Card card4 = new Card(Card.Rank.TWO, Card.Suit.CLUBS);
        Card card5 = new Card(Card.Rank.KING, Card.Suit.HEARTS);
        Card card6 = new Card(Card.Rank.SEVEN, Card.Suit.CLUBS);
        Card card7 = new Card(Card.Rank.SIX, Card.Suit.CLUBS);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        
        System.out.print("\tHand hand1 = new Hand()\n\n");
        
        System.out.print("Card #1 is: " + card1 + ", ");
        System.out.print("Card #2 is: " + card2 +", ");
        System.out.print("Card #3 is: " + card3 +".\n\n");
        System.out.print("Method\t\t\tOutput\n");
        System.out.println("_________________________________________\n");
        System.out.print("hand1.addCard(card1)");
        hand1.addCard(card1);
        System.out.print("\t" + hand1 + "\n");
        System.out.print("hand1.addCard(card2)");
        hand1.addCard(card2);
        System.out.print("\t" + hand1 + "\n");
        System.out.print("hand1.addCard(card3)");
        hand1.addCard(card3);
        System.out.print("\t" + hand1 + "\n");
        System.out.print("hand1.removeCard(card1)");
        System.out.print("\t" + hand1.removeCard(card1)+ "\n\n");
        hand1.removeCard(card1);
        System.out.print(hand1 + "\n\n");
        
        System.out.print("\tHand hand2 = new Hand()\n");           
        System.out.print("Card #4 is: " + card4 +", ");
        System.out.print("Card #5 is: " + card5 +", ");
        System.out.print("Card #6 is: " + card6 +", ");
        System.out.print("Card #7 is: " + card7 +".\n\n");
        
        System.out.print("Method\t\t\tOutput\n");
        System.out.println("_________________________________________\n");
        
        System.out.print("hand2.addCards(cards)");
        hand2.addCards(cards);
        System.out.print("\t" + hand2+"\n");
        
        System.out.print("hand2.addHand(hand1)");
        hand2.addHand(hand1);
        System.out.print("\t"+hand2+"\n");
        
        System.out.print("hand2.removeHere(2)");
        System.out.print("\t" + hand2.removeHere(2) +"\n");
        
        System.out.print("hand2.removeHand(hand1)");
        System.out.print("\t" + hand2.removeHand(hand1));
        System.out.print("\t" + hand2 +"\n\n");
        
        System.out.print("hand2.addHand(hand1)");
        hand2.addHand(hand1);
        System.out.print("\t" + hand2+"\n");
        
        System.out.print("hand2.sort()");
        hand2.sort();
        System.out.print("\t\t" + hand2 + "\n");
        
        System.out.print("hand2.sortByRank()");
        hand2.sortByRank();
        System.out.print("\t" + hand2 + "\n");
        
        System.out.print("hand2.countSuit(CLUBS)");
        System.out.print("\t" + hand2.countSuit(Card.Suit.CLUBS) + "\n");
        
        System.out.print("hand2.countRank(SIX)");
        System.out.print("\t" + hand2.countRank(Card.Rank.SIX) + "\n");
        
        System.out.print("hand2.hasSuit(HEARTS)");
        System.out.print("\t" + hand2.hasSuit(Card.Suit.HEARTS) + "\n");
        
        System.out.print("hand2.valueOfHand");
        System.out.print("\t" + hand2.valueOfHand + "\n");
         }
     //******************************************************************/

}