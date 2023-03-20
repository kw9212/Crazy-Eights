/** Card.java
*   Author: Keun Woo Song (ks3651)
*   
*   
*   Models a typical playing card
*
*/

class Card{
    
    private char suit;
    private int rank;

    // Initializes a card instance
    public Card(char suit, int rank){   
        this.suit=suit;
        this.rank=rank;
    }

    // Accessor for suit
    public char getSuit(){
        // your code here;
    	//char suit = 'Y';
        return this.suit;
    }

	public void setSuit(char suit){
		this.suit = suit;
	}
    
    // Accessor for rank
    // A = 1, 2 = 2, ... J = 11 Q = 12 K = 13
    public int getRank(){
  
        return this.rank;
    }
    //match initial of suits with each symbol
	public String getStringSuit() {
		switch (getSuit()) {
		case 'S': 
			return "♠";
		case 'C': 
			return "♣";
		case 'D': 
			return "⬥";
		case 'H': 
			return "♥";
		default:
			return null;
		
		}
	}
    //change suit type to string
	public String getStringSuit(char suit) {
		switch (suit) {
			case 'S':
				return "♠";
			case 'C':
				return "♣";
			case 'D':
				return "⬥";
			case 'H':
				return "♥";
			default:
				return null;
		}
	}
	//match rank that has a name with integers
	public String getStringRank() {
		switch (getRank()) {
		case 1: 
			return "Ace";
		case 11: 
			return "Jack";
		case 12: 
			return "Queen";
		case 13:
			return "King";
		default:
			return Integer.toString(getRank());
			// 2~10 --> integer number itself becomes a rank
		}
			
  	}
    // Returns a human readable form of the card (eg. King of Diamonds)
    public String toString(){
    
		String cardDescription; 
		cardDescription = getStringRank() + " of " + getStringSuit();
    	return cardDescription;
    }
}