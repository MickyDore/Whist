package whist;

import cards.Deck;
import cards.Card.Suit;
import java.util.*;
import cards.Hand;
import static whist.Trick.trumps;

/*
* ~ Run file to either:
* ~ Simulate a game of 4 Basic Players.
* ~ Play a game as a human player against 3 Basic Players.
* ~ Simulate a game of 2 Basic and 2 Advanced Players.
*/
public class BasicWhist {

    //Class varaibles
    static final int NOS_PLAYERS = 4;
    static final int NOS_TRICKS = 13;
    static final int WINNING_POINTS = 7;
    static final int SIZE_OF_DECK = 52;
    static int teamOnePoints = 0;
    static int teamTwoPoints = 0;
    static int team1RoundPoints;
    static int team2RoundPoints;
    static int firstPlayer;
    Player[] players;

    //Constructor that takes an array of players as a parameter.
    public BasicWhist(Player[] players) {
        this.players = players;
    }

    //Method that deals 52 cards between 4 players.
    public void dealHands(Deck deck) {
        for (int i = 0; i < SIZE_OF_DECK; i++) {
            players[i % NOS_PLAYERS].dealCard(deck.deal());
        }
    }

    //Method that plays a single trick.
    public Trick playTrick(Player firstPlayer) {
        Trick t = new Trick(firstPlayer.getID());
        int playerID = firstPlayer.getID();
        for (int i = 0; i < NOS_PLAYERS; i++) {
            int next = (playerID + i) % NOS_PLAYERS;
            t.setCard(players[next].playCard(t), players[next]);
        }
        return t;
    }

    /*
     *Method that plays an entire game.
     *A game consists of 13 tricks.
     */
    public void playGame() {
        Deck d = new Deck();
        dealHands(d);
        firstPlayer = (int) (NOS_PLAYERS * Math.random());
        Suit trumps = Suit.getRandomSuit();
        Trick.setTrumps(trumps);
        for (int i = 0; i < NOS_PLAYERS; i++) {
            players[i].setTrumps(trumps);
        }

        team1RoundPoints = 0;
        team2RoundPoints = 0;
        for (int i = 0; i < NOS_TRICKS; i++) {
            Trick t = playTrick(players[firstPlayer]);
            System.out.println("------------------------------------"
                    + "----------------------------------------------------");
            System.out.print("Trumps:\t " + trumps + "   |   ");
            System.out.print("Team 1 Points: " + teamOnePoints + "   |   ");
            System.out.print("Team 2 Points: " + teamTwoPoints + "   |   \n");
            //System.out.print("You are player: " + 1 + "\n");
            System.out.println("---------------------------------------"
                    + "-------------------------------------------------");

            System.out.println("Lead Player: Player " + (firstPlayer +1));
            System.out.println(t);
            firstPlayer = t.findWinner();
            System.out.print("Winner = Player " + (firstPlayer + 1) + "\n");
            System.out.println("-----------------------------------------"
                    + "-----------------------------------------------");

            if (firstPlayer == 0 || firstPlayer == 2) {
                team1RoundPoints++;
            } else {
                team2RoundPoints++;
            }
            System.out.print("Team 1 Round Points: " + team1RoundPoints);
            System.out.println("  |  Team 2 Round Points: " + 
                    team2RoundPoints);
            System.out.println("-----------------------------------------"
                    + "-----------------------------------------------\n\n");
        }

        if (team1RoundPoints > 6) {
            teamOnePoints += team1RoundPoints - 6;
        }
        if (team2RoundPoints > 6) {
            teamTwoPoints += team2RoundPoints - 6;
        }

    }

    //Method plays an entire match.
    //A match consists of recurring games until a winnin team is found.
    public void playMatch() {
        teamOnePoints = 0;
        teamTwoPoints = 0;
        while (teamOnePoints < WINNING_POINTS && 
                teamTwoPoints < WINNING_POINTS) {
            System.out.print("\t\t\t\t ~~~ NEW ROUND ~~~"+ "\n");
            playGame();
        }
        if (teamOnePoints >= WINNING_POINTS) {
            System.out.println("Winning team is Team One with " + 
                    teamOnePoints + " points, thanks for playing!");
        } else {
            System.out.println("Winning team is Team Two with " + 
                    teamTwoPoints + " points, thanks for playing!");
        }

    }

    //Method to simulate a game of Whist with four Basic Players.
    public static void basicGame() {
        Player[] p = new Player[NOS_PLAYERS];
        for (int i = 0; i < p.length; i++) {
            p[i] = new BasicPlayer(i, new BasicStrategy());
        }
        
        BasicWhist basic = new BasicWhist(p);
        basic.playMatch();

        //Ask user if they would like to play again.
        Scanner scan = new Scanner(System.in);
        String answer = null;
        System.out.println("\nWould you like to play another game?");
        System.out.println("Please type Yes(y)/No(n): ");

        answer = scan.nextLine().toLowerCase();

        switch (answer) {
            case "y":
                basic.playMatch();
                break;
            case "n":
                System.out.println("Thanks for playing!");
                break;
        }
    }
    
    //Method to simulate a game of Whist.
    public static void humanGame() {
        Player[] p = new Player[NOS_PLAYERS];
        p[NOS_PLAYERS-4] = new BasicPlayer(0, new HumanStrategy());
        for (int i = 1; i < (p.length); i++) {
            p[i] = new BasicPlayer(i, new BasicStrategy());
        }
        BasicWhist basic = new BasicWhist(p);
        basic.playMatch();

        //Ask user if they would like to play again.
        Scanner scan = new Scanner(System.in);
        String answer = null;
        System.out.println("\nWould you like to play another game?");
        System.out.println("Please type Yes(y)/No(n): ");

        answer = scan.nextLine().toLowerCase();

        switch (answer) {
            case "y":
                basic.playMatch();
                break;
            case "n":
                System.out.println("Thanks for playing!");
                break;
        }
    }

    public static void main(String[] args) {
        //basicGame();
        humanGame();

    }

}
