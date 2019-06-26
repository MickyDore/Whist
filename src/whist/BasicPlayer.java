package whist;

import cards.Card;
import java.util.*;
import cards.Deck;
import cards.Card.Suit;
import cards.Hand;

public class BasicPlayer implements Player {

    public Strategy strategy;
    public Hand playerHand;
    public int playerID;
    public Suit trumps;
    
    public BasicPlayer(int id, Strategy s){
        playerHand = new Hand();
        this.playerID = id;
        this.strategy = s;
        
    }
   
    @Override
    public void dealCard(Card c) {
        this.playerHand.handOfCards.add(c);
    }

    @Override
    public void setStrategy(Strategy s) {
        this.strategy = s;
    }

    @Override
    public Card playCard(Trick t) {
        Card cardToPlay = strategy.chooseCard(playerHand, t);
        this.playerHand.handOfCards.remove(cardToPlay);
        return cardToPlay;
    }

    @Override
    public void viewTrick(Trick t) {
        System.out.println(t);
    }

    @Override
    public void setTrumps(Card.Suit s) {
        this.trumps = s;
    }

    @Override
    public int getID() {
        return this.playerID;
    }
    
    
}
