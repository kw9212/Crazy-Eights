/** Player.java
*   Author: Keun Woo Song (ks3651)
*   
*   Player class as part of Crazy Eights
*   To be used with Game, Card, Deck classes
*
*/

import java.util.ArrayList;
import java.util.Scanner;

class Player{
    
    private ArrayList<Card> hand; // the player's hand
    private Scanner input;

    public Player(){
        this.hand = new ArrayList<>();
        this.input = new Scanner(System.in);
    }

    // Adds a card to the player's hand
    public void addCard(Card c){
        this.hand.add(c);
    }
   
    // Covers all the logic regarding a human player's turn
    // public so it may be called by the Game class
    public Card playsTurn(Deck deck){
        System.out.println("Players Turn!");

    	ArrayList<Card> hand = this.getHand(); // card in hand check

        for(int i = 0; i < getHand().size(); i ++){
            System.out.println("Card Num : " + i + "\t" + "Suit/Rank : " 
            + getHand().get(i).getStringSuit() + "/" 
            + getHand().get(i).getStringRank());
        }
        while(true){
            int selectedCardIndex = Integer.parseInt(input.nextLine());

            //check if the card number is correct
            if(selectedCardIndex > getHand().size() -1){
                System.out.println
                ("There is no card Number : " + selectedCardIndex);
                continue;
            }
            //play the card
            return getHand().remove(selectedCardIndex);
        }
    }

    public boolean checkIfPlayAvailable(Card topCard){
        for(int i = 0; i < getHand().size(); i ++){
            if(topCard.getRank() ==  getHand().get(i).getRank() 
            || topCard.getSuit() ==  getHand().get(i).getSuit()){
                return true;
            }
        }
        return false;
    }
    // if play rank 8
    public char changeSuit(){
        while(true){
            System.out.println
            ("You handed out Joker! pick the suit you want to change(S, D, H, C)");
            String selectedSuit = input.nextLine();
            selectedSuit = selectedSuit.toUpperCase();
            //select one of suits
            if("S".equals(selectedSuit) || "D".equals(selectedSuit) 
            || "H".equals(selectedSuit) || "C".equals(selectedSuit)){
                return selectedSuit.charAt(0);
            }
            else{
                System.out.println("You can only choose on of S, D, H, C");
            }
        }
    }

    // Accessor for the players hand
    public ArrayList<Card> getHand(){
     
    	return this.hand;
    }

    // Returns a printable string representing the player's hand
    public String handToString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Card in hands\n");
        for(Card card : getHand()){
            retString.append("Suit : " + card.getStringSuit() 
            + "\tRank" + card.getStringRank() + "\n");
        }
        return retString.toString();
    }
}