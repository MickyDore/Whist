package whist;

import cards.Card;
import cards.Hand;
import java.util.*;


public class HumanStrategy implements Strategy{

    Trick previousTrick;
    
    /*
    *Method to display a user:
    * ~ The current trump 
    * ~ The overall number of points for both teams in the match.
    * ~ The current trick being played
    * ~ The number of points for both teams in the current game.
    * ~ A clear display of the users hand with corresponding indexes.
    * ~ Clear instructions and prompt for the user to play a card.
    */  
    @Override
    public Card chooseCard(Hand h, Trick t) {
        Scanner scan = new Scanner(System.in);
        int cardChoice = 0;
        Comparator<Card> rank = new Card.CompareRank();
        Comparator<Card> ascending = new Card.CompareAscending();
        Collections.sort(h.handOfCards, ascending);

        System.out.println("--------------------------------------"
                + "--------------------------------------------------");
        System.out.print("Trumps:\t " + Trick.trumps + "  |  ");
        System.out.print("-> Team 1 Points: " + 
                BasicWhist.teamOnePoints  + " <-  |  ");
        System.out.print("Team 2 Points: " + 
                BasicWhist.teamTwoPoints + "  |  ");
        System.out.print("You are player: " + 1 + "  |\n");
        System.out.println("---------------------------------------"
                + "-------------------------------------------------");
        System.out.println("Lead Player: Player " + 
                (BasicWhist.firstPlayer +1));
        System.out.println(t);
        System.out.println("---------------------------------------"
                + "-------------------------------------------------");
        System.out.print("-> Team 1 Round Points: " + 
                BasicWhist.team1RoundPoints+" <-");
        System.out.print("  |  Team 2 Round Points: " + 
                BasicWhist.team2RoundPoints);
        System.out.println("  |  Your partner is player: " + 3);
        System.out.println("-------------------------------------------"
                + "---------------------------------------------\n\n");
            
        System.out.print("Your hand is shown below\n");
        System.out.println("---------------------------");
        int count = 0;
        for (Card card : h){           
            System.out.printf(card + "    \t" + count + "\n");
            count++;
        }
        System.out.println("");
        System.out.print("Please pick a card to play "
                + "by typing the number next to the Card.\n");
        System.out.print("Play Card: \n");
        cardChoice = scan.nextInt();
        System.out.println("\n\n");
        
        return h.handOfCards.get(cardChoice);

    }

    @Override
    public void updateData(Trick c) {
        previousTrick = c;
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
