/** Game.java
*   Author: Keun Woo Song (ks3651)
*   
*   
*   Game class for playing crazy eights in commandline
*   To be used with Player, Card, Deck classes
*
*/

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

class Game{

    private char currentSuit; // need in case an 8 is played
    private Card faceup; 
    private Scanner input;
    private Player p1;
    private ArrayList<Card> compHand;
    private Deck cards;
    
    // sets up the Game object for play
    public Game(){
   
        input = new Scanner(System.in);
        this.cards = new Deck();
        this.cards.shuffle();
        p1 = new Player();
        compHand = new ArrayList<>();
    }

    // when the game begins, distribute 7 cards to each player
    public void distributeInitalSevenCards(){
        int cardNumberToDistribute = 7;

        for(int i = 0; i < cardNumberToDistribute; i++){
            p1.addCard(cards.deal());
        }
        for(int i = 0; i < cardNumberToDistribute; i++){
            compHand.add(cards.deal());
        }
    }
    //check if the game can cotinue
    public boolean checkIfGameOver(){
       // case 1: no card in the deck
        if(!cards.canDeal()){
            System.out.println("Deck is EMPTY!!!! : PLAYER LEFT CARD - " 
            + p1.getHand().size() + "\t" + 
            "COMPUTER LEFT CARD - " + compHand.size());
          
            if(compHand.size() < p1.getHand().size()){ 
                System.out.println("Computer wins!");
            }
            else if(compHand.size() == p1.getHand().size()){
                System.out.println("Draw!");
            }
            else{   
                System.out.println("Player wins!");
            }
            return true;
        }//case 2: a player has no card in hand
        if(compHand.size() == 0){ //the computer wins
            System.out.println
            ("Computer has no cards in hand! Computer wins!");
            return true;
        }
        else if(p1.getHand().size() == 0){//the player wins
            System.out.println
            ("player has no cards in hand! player wins!");
            return true;
        }
        return false;
    }
    // ask the player if they want to play again
    public boolean selectContinueGameOrNot(Scanner scanner){
        while(true){
            System.out.println("Play new Game? : Yes - Y / No - N");
            String yn = scanner.nextLine();
            yn = yn.toUpperCase();
            if("Y".equals(yn)){
                return true;
            }
            else if("N".equals(yn)){
                return false;
            }
            else{
                System.out.println("Input Only Y or N");
            }
        }
    }
    // 
    public void playersMoveWhenAvailable(Card topCard){
        while(true){
            Card selectedCard = p1.playsTurn(cards);
            //same suit same number
            if(topCard.getRank() == selectedCard.getRank() 
            || topCard.getSuit() == selectedCard.getSuit()){
                if(selectedCard.getRank() == 8){
                    currentSuit = p1.changeSuit();
                    System.out.println
                    ("Player changed suit [" + selectedCard.getStringSuit() 
                    + " -> " + selectedCard.getStringSuit(currentSuit) + "]");
                    selectedCard.setSuit(currentSuit);
                }
                System.out.println("Player handed card : " 
                + selectedCard.getStringSuit() + "/"
                +  selectedCard.getStringRank());
                faceup = selectedCard;
                break;
            }
            p1.addCard(selectedCard);
            System.out.println("that card is not allowed!");
        }
    }

    public Boolean playersMoveWhenNotAvailable(Card topCard){
        while(!p1.checkIfPlayAvailable(faceup)){
            Card drewCard = cards.deal();
            if(checkIfGameOver()){
                return selectContinueGameOrNot(input);
            }
            p1.getHand().add(drewCard);
            System.out.println
            ("Player cannot play so drew one card... T.T : " 
            +  drewCard.getStringSuit() + "/"
            +  drewCard.getStringRank());
        }
        return null;
    }

    // Plays a game of crazy eights. 
    // Returns true to continue playing and false to stop playing
    public boolean play(){

        System.out.println("Crazy Eights Game Start..!");
       
        distributeInitalSevenCards();
    
        faceup = cards.deal();
        System.out.println("The first Top Card is : " 
        + faceup.getStringSuit() + "/"+  faceup.getStringRank());

        while(true){ // if both players can continue the game
          
            System.out.println("Left CardNum In Deck : " 
            +  cards.getLeftCardsInDeck());
        
            if(p1.checkIfPlayAvailable(faceup)){
                playersMoveWhenAvailable(faceup);
            }
            else{Boolean gameOver = playersMoveWhenNotAvailable(faceup);
                if(gameOver != null)
                    return gameOver;
            }
            printStatus("P"); //player

            if(checkIfGameOver())
                return selectContinueGameOrNot(input);

            faceup = computerTurn();

            if(checkIfGameOver())
                return selectContinueGameOrNot(input);

            printStatus("C"); //computer
        }
    }
    //same logic with that of player 
        private Card computerTurn(){
     
         System.out.println("Computers Turn!");
         for(int i = 0; i < compHand.size(); i ++){
             if(faceup.getRank() ==  compHand.get(i).getRank() 
             || faceup.getSuit() ==  compHand.get(i).getSuit()){
                 Card compCard = compHand.remove(i);

              
                 if(compCard.getRank() == 8){
                     System.out.println
                     ("computer handed out Joker! computer can now pick the suit(S, D, H, C)");
                     String[] suits = new String[]{"S","D","H","C"};
                     Random random = new Random();
                     compCard.setSuit(suits[random.nextInt(4)].charAt(0));
                     System.out.println("Computer choose suit : " + compCard.getStringSuit());
                 }

                 System.out.println("Computer handed card : " 
                 + compCard.getStringSuit() + "/"+  compCard.getStringRank());
                 return compCard;
             }
         }
         while(!checkIfComputerPlayAvailable(faceup)){
             System.out.println("Computer cannot play so drew one card...T.T");
             Card drewCard = cards.deal();
             if(drewCard == null){
                 return null;
             }
             compHand.add(drewCard);
         }

         return faceup;
    }
    //same condition with that of player: same rank or any rank with same suit
    public boolean checkIfComputerPlayAvailable(Card topCard){
        for (Card card : compHand) {
            if (topCard.getRank() == card.getRank() 
            || topCard.getSuit() == card.getSuit()) {
                return true;
            }
        }
        return false;
    }

    public void printStatus(String user){
        if(user.equals("C"))
            System.out.println
            ("Computers card left : [" + compHand.size() + "]");
        else
            System.out.println
            ("Players card left : [" + p1.getHand().size() + "]");
        System.out.println
        ("CURRENT FACEUP : [" + faceup.getStringSuit() 
        + "/"+  faceup.getStringRank() + "]");
    }
}