package cards;

import java.io.Serializable;
import java.util.*;

public class Deck implements Iterable<Card>, Serializable {

    //Class is serializable with an ID of 200.
    private static final long serialVersionUID = 200L;
    
    //ArrayList for a deck of cards that is created
    //when the Deck constructor is invoked.
    public ArrayList<Card> deckOfCards;

    //Constructor to create an Arraylist to represent 
    //a deck of cards, initialise all the cards in the deck so
    //that a new Deck starts with all 52 cards, and shuffle them.
    //It does so by calling the method newDeck().
    public Deck() {
        newDeck();
    }

    //Method to return the number of cards left in a deck.
    public int size() {
        return this.deckOfCards.size();
    }

    //Method to create a new deck of 52 shuffled Card objects.
    public final void newDeck() {
        this.deckOfCards = new ArrayList<>();

        //for every suit...
        for (int i = 0; i < Card.Suit.values().length; i++) { 
            //make 13 different cards.
            for (int j = 0; j < Card.Rank.values().length; j++) { 
                Card card = new Card(Card.Rank.values()[j],
                        Card.Suit.values()[i]);
                this.deckOfCards.add(card);
            }
        }
        Collections.shuffle(deckOfCards); //shuffles the deck of cards.
    }

    //iterator() method that will make the class traverse 
    //the deck in the order they will be deailt by default.
    @Override
    public Iterator<Card> iterator() {
        return new DealOrder();
    }

    //Nested class Iterator that traverses the deck in deal order.
    private class DealOrder implements Iterator<Card> {
        int position = 0;

        @Override
        public boolean hasNext() {
            return (position < deckOfCards.size());
        }

        @Override
        public Card next() {
            return deckOfCards.get(position++);
        }

        @Override
        public void remove() {
            Card topCard = deckOfCards.get(position);
            deckOfCards.remove(topCard);
        }
    }

    //Nested class Iterator that traverses the deck in sorted order.
    private class SecondCardIterator implements Iterator<Card>  {
      
        Comparator<Card> sorted = new Card.CompareAscending();
        
        int position = 0;

        @Override
        public boolean hasNext() {
            return (position < deckOfCards.size());
        }

        @Override
        public Card next() {
            Collections.sort(deckOfCards, sorted);
            return deckOfCards.get(position++);
        }
    }


    //Method that uses an iterator to locate the top card in 
    //a deck, remove it from the deck of cards, and return it.
    public Card deal() {
        int position = 0;
        Card topCard = null;
        if (iterator().hasNext()) {
            topCard = iterator().next();
            deckOfCards.remove(topCard);
        }
        return topCard;
    }

    ///***********************DECK TESTING**********
     public static void main(String[] args){
 
        Deck deck = new Deck();
        
        System.out.print("\tDeck deck = new Deck()\n\n");
        System.out.print("Method\t\t\tOutput\n");
        System.out.println("_________________"
                + "________________________\n");
        System.out.print("deck.size()");
        System.out.print("\t\t" + deck.size() + "\n");
        System.out.print("__________________"
                + "_______________________\n\n");

        System.out.print("Dealing with DealOrder Iterator\n");
        System.out.print("____________________"
                + "_____________________\n\n");
        System.out.print("deck.deal()");
        System.out.print("\t\t" + deck.deal() + "\n");
        System.out.print("deck.deal()");
        System.out.print("\t\t" + deck.deal() + "\n");
        System.out.print("deck.deal()");
        System.out.print("\t\t" + deck.deal() + "\n\n");
        System.out.print("deck.size()");
        System.out.print("\t\t" + deck.size() + "\n\n");
        
        
        System.out.print("deck.newDeck()");
        deck.newDeck();
        System.out.print("\t\t" + "Deck reinitialised." + "\n");  
        
        System.out.print("deck.size()");
        System.out.print("\t\t" + deck.size() +"\n\n");
        
        System.out.print("deck.deal()");
        System.out.print("\t\t" + deck.deal() + "\n");
        
     }
     //**********************************************/
}