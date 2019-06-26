package whist;

import cards.Card;
import cards.Card.Suit;
import java.util.*;

/**
 * Skeleton class for storing information about whist tricks
 *
 * @author ajb
 */
public class Trick {

    //Card variables to store the card played by each player
    //for every trick.
    Card playerOneCard;
    Card playerTwoCard;
    Card playerThreeCard;
    Card playerFourCard;

    public ArrayList<Card> trick;// = new ArrayList<>();
    public static Suit trumps;
    public Card leadCard; //= trick.get(0);
    public int leadPlayer;
    public Suit leadSuit;// = leadCard.getSuit();
    
    public Trick(int p) { //p is the lead player 
        trick = new ArrayList<>();
        //leadCard = trick.get(0);
        leadPlayer = p;
    }

    //Sets the trump of a game to a given, random suit.
    public static void setTrumps(Suit s) {
        trumps = s;
    }

    /**
     *
     * @return the Suit of the lead card.
     */
    public Suit getLeadSuit() {
        leadSuit = this.trick.get(0).getSuit();
        return leadSuit;
    }

    /**
     * Records the Card c played by Player p for this trick
     *
     * @param c
     * @param p
     */
    public void setCard(Card c, Player p) {
        //this.playedCard = c;
        
        switch (p.getID()) {
            case 0:
                this.playerOneCard = c;
                trick.add(playerOneCard);
                break;
            case 1:
                this.playerTwoCard = c;
                trick.add(playerTwoCard);
                break;
            case 2:
                this.playerThreeCard = c;
                trick.add(playerThreeCard);
                break;
            case 3:
                this.playerFourCard = c;
                trick.add(playerFourCard);
                break;
        }  
    }

    /**
     * Returns the card played by player with id p for this trick
     *
     * @param p
     * @return
     */
    public Card getCard(Player p) {
        Card card = null;
        switch (p.getID()) {
            case 0:
                card = this.playerOneCard;
                break;
            case 1:
                card = this.playerTwoCard;
                break;
            case 2:
                card = this.playerThreeCard;
                break;
            case 3:
                card = this.playerFourCard;
                break;
        }
        return card;
    }
    
    /**
     * Finds the ID of the winner of a completed trick
     */
    public int findWinner() {
        int winningID = 0;
        
        //Iterator<Card> iterator = trick.iterator();
        Card highestCard = trick.get(0);
        for (int i = 1; i < trick.size(); i++){
            Card currentCard = trick.get(i);
            if (currentCard.getSuit().equals(leadSuit)) {
                if (highestCard.getRank().
                        compareTo(currentCard.getRank()) < 0) {
                    highestCard = currentCard;
                }
            }
            else
            //if next card doesn't follow suit, and is not a trump...
            //it is not the highest card
            if (!(currentCard.getSuit().equals(leadSuit)) &&
                    (!(currentCard.getSuit().equals(trumps)))){
                highestCard = highestCard;
            }
            else
            //if the next card is a trump, and the highest card isn't...
            //the next card is the new highest card.
            if (currentCard.getSuit().equals(trumps) &&
                    (!(highestCard.getSuit().equals(trumps)))){
                highestCard = currentCard;
            }
            else
            //if highest card is a trump and the next card isn't...
            //the next card is not the highest card.
            if (highestCard.getSuit().equals(trumps) &&
                    (!(currentCard.getSuit().equals(trumps)))){
                highestCard = highestCard;
            }
            else 
            //if both cards are trumps, if the next card has higher rank
            //the next card is the new highest card.    
                if (highestCard.getSuit().equals(trumps) &&
                        currentCard.getSuit().equals(trumps)){
                    if (highestCard.getRank().
                            compareTo(currentCard.getRank()) < 0) {
                    highestCard = currentCard;
                }
            
            }
                
        }
        
        if (highestCard.equals(playerOneCard)) {
            winningID = 0;
        } else if (highestCard.equals(playerTwoCard)) {
            winningID = 1;
        } else if (highestCard.equals(playerThreeCard)) {
            winningID = 2;
        } else if (highestCard.equals(playerFourCard)) {
            winningID = 3;
        }
        return winningID;
    }
    
       
    //Method that finds the highest card in a trick.
    public Card highestCard (){
        Iterator<Card> iterator = trick.iterator();
        Card highestCard = trick.get(0);
        
        if (trick.size() == 1){
            return highestCard;
        }
        
        for (int i = 1; i < trick.size(); i++){
            Card currentCard = trick.get(i);
            if (currentCard.getSuit().equals(leadSuit)) {
                if (highestCard.getRank().
                        compareTo(currentCard.getRank()) < 0) {
                    highestCard = currentCard;
                }
            }
            else
            //if next card doesn't follow suit, and is not a trump...
            //it is not the highest card
            if (!currentCard.getSuit().equals(leadSuit) &&
                    (!currentCard.getSuit().equals(trumps))){
                highestCard = highestCard;
            }
            else
            //if the next card is a trump, and the highest card isn't...
            //the next card is the new highest card.
            if (currentCard.getSuit().equals(trumps) &&
                    (!highestCard.getSuit().equals(trumps))){
                highestCard = currentCard;
            }
            else
            //if highest card is a trump and the next card isn't...
            //the next card is not the highest card.
            if (highestCard.getSuit().equals(trumps) &&
                    !currentCard.getSuit().equals(trumps)){
                highestCard = highestCard;
            }
                
        }
        return highestCard;
    }
    
    //Method to find your partners ID.
    public int findPartnerID(int id){
        int partnerID = 0;
        switch (id){
            case 1:
                partnerID = 3;
                break;
            case 2:
                partnerID = 4;
                break; 
            case 3:
                partnerID = 1;
                break;
            case 4:
                partnerID = 2;
                break;    
        }
        return partnerID;
    }
        
    @Override
    public String toString() {
        StringBuilder trickBuilder = new StringBuilder();
        if (trick.size() == 0){
            return "Current Trick: Trick is empty! Pick a card";
        }
        for (int i = 0; i < this.trick.size() - 1; i++) {
            trickBuilder.append(this.trick.get(i)).append(", ");
        }
        trickBuilder.append(this.trick.get(this.trick.size() - 1)).append(".");
        return "Current trick: " + trickBuilder.toString();
    }

}
