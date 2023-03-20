/** Deck.java
*   Author: Keun Woo Song (ks3651)
*   
*   Models a typical deck of playing cards
*   To be used with Card class
*
*/
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

class Deck{

    private Card[] deck; // contains the cards to play with
    private int top; // controls the "top" of the deck to deal from

    public static final int ranks = 13;
    public static final char[] suits = {'S','C','D','H'};

    //create 52 cards and assign rank and suit without overlapping
    public Deck(){
    	deck = new Card[ranks * suits.length];
    	int cardNum = 0;
    	
    	for(int i = 0; i < this.suits.length; i++) {
    		char suit = suits[i];
    		for(int j = 1; j <= ranks; j++) {
    			int rank = j;
    			deck[cardNum] = new Card(suit, rank);
    			cardNum++;
    		}
    	}
    }
    //shows left "facedown" cards
	public int getLeftCardsInDeck(){
		return deck.length - this.top;
	}

    // Deals the top card off the deck
    public Card deal(){

		if(canDeal()) {
			Card retCard = deck[top];
			top++;
			return retCard;
		}
		return null;
    }


    // returns true provided there is a card left in the deck to deal
    public boolean canDeal(){

		if(top >= ranks * suits.length)
			return false;
		return true;
    }
    
    public boolean isMinusExists(int[] randomSeq) {
    	for(int i=0; i<randomSeq.length; i++) {
    		if(randomSeq[i] == -1)
    			return false;
    	}
    	return true;
    }

    // Shuffles the deck
    public void shuffle(){
		Random random = new Random();
		List<Integer> randList =  new ArrayList<>();
		while(randList.size() < ranks * suits.length) {
			Integer randNum = random.nextInt(ranks * suits.length);
			if (!randList.contains(randNum)) {
				randList.add(randNum);
			}
		}
		for(int i = 0; i < deck.length; i++){
			Card card = deck[i];
			deck[i] = deck[randList.get(i)];
			deck[randList.get(i)] = card;
		}
    }

    // Returns a string representation of the whole deck
    public String toString(){
		int cardOrder = 0;
		StringBuilder retString = new StringBuilder();
		for(Card card : deck){
			retString.append("cardNum : " + cardOrder + " " 
            +  card.getStringRank() + " / " + card.getStringSuit() + "\n");
			cardOrder++;
		}
		return retString.toString();
    }
}