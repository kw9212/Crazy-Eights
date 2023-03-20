# Crazy-Eights


## Card Class

I created set method, get method for suits and ranks here. With switch-case, 
I assigned each initial of suits(Spade, Clover, Diamond, Heart) to each symbol as a string. 

In getStringRank method, we have some cards with names, instead of ranks: 1(Ace), 11(jack), 12(queen), 13(king) 
so I assigned them to each number and return their names and rest of cards return their ranks(2~10)

In toString method, simply it returns card information as a form of “rank of suit”

## Deck Class

I made number of ranks and suits as final to prevent change them. 
I created objects for the deck, I multiple ranks with suits.length, instead hard coding 52 
because although the card ranks and suits are static in this game, 
I tried to make the number of card reusable in terms of the variation of suits and ranks..

In the constructor, I create the deck object and with nested for loop, 
I assign each card with one suit and one rank in order without overlapping.

In getLeftCardsInDeck method, I created a return value as deck’s length – top 
because top card would be faceup.

In deal method, with if statement, it determine we can deal and if we can, it deals the topcard. 

Candeal method returns a boolean variable based on the result 
if top is equal or larger than the total length of the deck. 
If top is bigger than that, it means there is no card in the deck, which means that it cannot deal so return false. 
Otherwise, return true. This helps deal method to determine which one is the current top card.

isMinusExists checks if there is an element in array that is -1. If there is, it returns false.

In the shuffle method, I used random class to generate random numbers 
so I can shuffle cards. With arraylist and size method, 
I created a while condition that adds a card until it has 52 cards (0~51) in it and with if statement, 
I checked if there is a card that has already existed in the list before adding it. 
Then with for loop, made card object shuffle randomly with assigning address. 

In string toString method, I used stringbuilder class. 
And for each loop, returns a string of the deck with suit and rank.

## Game Class

In the constructor we are setting the game. Make a deck and shuffle it, players participated in with objects.
Then distributeInitalSevenCards method distribute 7 cards to each player. I created this with for loop for each player.

checkIfGameOver method shows if the player can continue the game. 

There are two cases. First, there is no card in the deck. 
I made this with if statement and compare each cards in hand and declare winner who has less cards. 
Then second case is that one of the players discard all the cards in hand. 
I made this also with if statement to compare their cards in hand equal to 0.

selectContinueGameOrNot method takes scanner so the player can continue the game after the game ends by entering y or n. 
I added also the third case that the player enters others than y or n.

playersMoveWhenAvailable and playersMoveWhenNotAvailable methods indicates 
if they can play with the current cards. 
I made this with if statements to check 
if they have at least one card in hand which has the same suit or the same rank with those of topcard. 
If the player selected the card that is against the rule, 
it prints “that card is not allowed!” if the player has no card that can play. 
They draw a card from the deck until they pick a playable card. 
I made this by creating drewCard object and with the deal method. 

In play method, it shows the statement that game has begun immediately and after we distribute 7 cards to each player, 
with deal method, it shows topcard from the deck. 
Then while both players can play, with aforementioned methods, it determines the game can continue and keep going.

In the computerTurn method, as the humanplayer logic, with if statement, the computer player decides the cards in its hand can play. 
If it can, play it and remove one card (remove method) if the computer plays 8, it randomly choose one of suits. 
I made this string array and by creating randomnumber, the computer choose one of indexes in the array. 
As the human player, the computer player too draw the card if it has no card that can be played in hand.

In the checkIfComputerPlayAvailable shows cards in the computer player can be played.
printStatus method shows that how many cards left in each player’s hand and shows current faceup(topcard) in the deck.


## Player Class

In the constructor, I initialize new arraylist 
which will be cards in human hand and input variable from the scanner class.

Addcard method add a card in hand

Playsturn method shows human player’s logic. 
First check what are the cards in hand. I did this with for loop. 
Then let the player enter one of the cards it happens with index number. 
Then, with if statement, we check if the card number is correct. 
If not, tells the player it is not in hand. If it is playable, 
it will be played and remove from the hand.

checkIfPlayAvailable method gives that if the card is playable. 

changeSuit method is for when the player plays 8, 
then the player can choose the suit they want to play with. 
I made this with nextLine method so the player can enter. 
In case the player enters wrong initial, I prevent it with else statement.

genHand method is the accessor for the player’s hand.

handToString method shows what is in the player’s hand. 
I used StringBuilder class to try in various ways for this part. 
With for loop, it enumerates cards in player’s hand.
