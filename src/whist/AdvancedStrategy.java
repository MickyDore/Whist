/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whist;

import cards.Card;
import cards.Hand;

/**
 *
 * @author Micky
 */
public class AdvancedStrategy implements Strategy{

    Trick previousTrick;
    
    @Override
    public Card chooseCard(Hand h, Trick t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateData(Trick c) {
        previousTrick = c;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
