package whist;

import cards.Card;
import java.util.*;
import cards.Deck;
import cards.Hand;
import cards.Card;
import cards.Card.Rank;
import cards.Card.Suit;

public class BasicStrategy implements Strategy {

    //Variable to store the previous trick.
    public Trick previousTrick;

    //Method to choose a given card based on the
    //requirements of a Basic Strategy.
    @Override
    public Card chooseCard(Hand h, Trick t) {

        Card cardToPlay = null;
        Comparator<Card> rank = new Card.CompareRank();
        Collections.sort(h.handOfCards, rank);
        int sizeOfHand = h.handOfCards.size();
        int nosMaxRank = h.countRank
        (h.handOfCards.get(sizeOfHand - 1).getRank());
        Iterator<Card> handIt = h.iterator();
        Iterator<Card> trickIt = t.trick.iterator();

        //Case 1 - The player is first to act.
        if (t.trick.isEmpty()) {
            if (nosMaxRank > 1) {
                int randomCard = (int) 
                        ((Math.random() * ((nosMaxRank - 1) + 1) + 1));
                return h.handOfCards.get(sizeOfHand - randomCard);
            } else {
                return h.handOfCards.get((sizeOfHand) - 1);
            }
        } else 
        //Case 2 - The players partner is winning.  
        //if player can follow suit
        if (partnerIsWinning(t)) {
            Suit previousSuit = t.getLeadSuit();
            if (h.hasSuit(previousSuit)) {
                while (handIt.hasNext()) {
                    Card card = handIt.next();
                    if (card.getSuit().equals(previousSuit)) {
                        return card;
                    }
                }
            } //if player can't follow suit
            else if ((!(h.hasSuit(previousSuit)))) {
                while (handIt.hasNext()) {
                    Card card = handIt.next();
                    if (!(card.getSuit().equals(Trick.trumps))) {
                        return card;
                    }
                }
            }
        } 
        //Case 3 - Partner hasn't played or isn't winning the current trick
        else if (!(partnerIsWinning(t))) {
            Card currentMaxCard = t.highestCard();
            Suit currentMaxSuit = currentMaxCard.getSuit();
            
            //if player has the same suit.
            if (h.hasSuit(currentMaxSuit)) {
                if (canBeatCard(currentMaxCard, h.handOfCards)) {
                    //play highest card
                    return highestRank(currentMaxSuit, h);
                } else 
                    if (!canBeatCard(currentMaxCard, h.handOfCards)) {
                    //play lowest card
                    return lowestRank(currentMaxSuit, h);
                }
            } else //if player can't follow suit
            if (!(h.hasSuit(currentMaxSuit))) {
                //if it can trump...
                if (h.hasSuit(Trick.trumps)) {
                    //play a trump
                    return lowestRank(Trick.trumps, h);
                } else {
                    //play the lowest card
                    Card card = handIt.next();
                    return card;
                }
            }
        }


        return Card.max(h.handOfCards);
    }

    //Method to return the previous trick.
    @Override
    public void updateData(Trick c) {
        previousTrick = c;
    }

    //Method to test whether a player can beat a given card
    //with the cards in their hand
    public boolean canBeatCard(Card c, ArrayList<Card> hand) {
        for (Card card : hand) {
            if (c.getSuit().equals(card.getSuit())) {
                if (c.getRank().compareTo(card.getRank()) < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    //Method that returns the highest value card of a given suit.
    public Card highestRank(Suit s, Hand hand) {
        ArrayList<Card> list = new ArrayList<>();
        for (Card card : hand) {
            if (card.getSuit().equals(s)) {
                list.add(card);
            }
        }
        Comparator<Card> rank = new Card.CompareRank();
        Collections.sort(list, rank);

        return list.get(list.size() - 1);
    }

    //Method that returns the lowest value card of a given suit.
    public Card lowestRank(Suit s, Hand hand) {
        ArrayList<Card> list = new ArrayList<>();
        for (Card card : hand) {
            if (card.getSuit().equals(s)) {
                list.add(card);
            }
        }
        Comparator<Card> rank = new Card.CompareRank();
        Collections.sort(list, rank);

        return list.get(0);
    }

    //Method to find whether your partner is winning a trick.
    public boolean partnerIsWinning(Trick t) {

        int sizeOfTrick = t.trick.size();
        int firstPlayerID = t.leadPlayer;
        int myID = ((firstPlayerID + sizeOfTrick) % 4);
        int partnerID = ((myID + 2) % 4);
        int winningID = t.findWinner();

        if (winningID == partnerID) {
            return true;
        } else {
            return false;
        }

    }

}
