import java.util.*;

public class Hand {
	public ArrayList<Card> Hand;
	public ArrayList<Card> Discard;
	public int cardIndex;
	public String cardValueString = " ";
	public boolean testSelf = false;
	public boolean cardInHand = false;
	public boolean handEmpty = false;

	public Hand()
	// Default constructor method for the Hand class
	{
		Hand = new ArrayList<Card>();
		// An arraylist representing a player's hand
		Discard = new ArrayList<Card>();
		// An arraylist representing a player's discard pile
	}

	public void drawCard(Deck deckObject)
	// Draws a card from a specified deck
	{
		// Check to see if the deck is empty first
		if (deckObject.deckIsEmpty()) {
			System.out.println("The deck is currently empty");
		} else {
			Card tempCard = deckObject.getTopCard();
			this.Hand.add(tempCard);
			deckObject.removeTopCard();
			testForDrawnPairs();
		}

	}

	public void dealHand(Deck deckObject)
	// Draws seven cards from a deck object
	{
		for (int i = 0; i < 7; i++) {
			Card tempCard = deckObject.getTopCard();
			this.Hand.add(tempCard);
			deckObject.removeTopCard();
		}
		printHand();
		testForDrawnPairs();
	}

	public int getSize()
	// Returns the size of the player's hand
	{
		return this.Hand.size();
	}

	public int getDiscardSize() {
		return this.Discard.size();
	}

	public void removeCardToTargetDiscard(int cardIndex, Player target)
	// Removes card from the hand arraylist
	{
		if (!handIsEmpty()) {
			target.hand.Discard.add(this.Hand.get(cardIndex));
			this.Hand.remove(cardIndex);
		} else {
			System.out.println("The hand is empty!");
		}

	}

	public void testForDrawnPairs() 
	//Iterates through the hand to see if there are pairs. Unfortunately, there are special cases that need to be tested
	{
		for (int i = 0; i < (this.Hand.size()-1); i++) {
			for (int j = 1; j < this.Hand.size(); j++) {
				//If the values of j and i are equal, but j and i are not the same index
				if (this.Hand.get(j).valueToString().equals(this.Hand.get(i).valueToString()) && j != i) {
					System.out.println("A pair of a value " + this.Hand.get(j).valueToString() + " has been drawn and discarded.");
					//Remove from hand and discard
					this.Discard.add(this.Hand.get(i));
					this.Discard.add(this.Hand.get(j));
					this.Hand.remove(j);
					this.Hand.remove(i);
					System.out.println("\n\n");
				}
			}
		}
	}

	protected void printHand()
	// Prints the hand
	{
		for (int i = 0; i < this.Hand.size(); i++) {
			System.out.println(this.Hand.get(i).valueToString() + " of " + this.Hand.get(i).suitToString());
		}
	}

	public boolean testSelfCard(String testedCard)
	// Iterates through player's own hand to see if the card they are requesting
	// is in their hand
	{
		testedCard = testedCard.substring(0, 1).toUpperCase() + testedCard.substring(1).toLowerCase();
		for (int i = 0; i < this.Hand.size(); i++) {
			this.testSelf = testedCard.equals(this.Hand.get(i).valueToString());
			if (this.testSelf) {
				break;
			}
		}
		return this.testSelf;
	}

	public int indexOfOwnCardRequested(String testedCard)
	// Iterates through the player's own hand to find the index of the card to
	// be removed
	{
		testedCard = testedCard.substring(0, 1).toUpperCase() + testedCard.substring(1).toLowerCase();
		for (int i = 0; i < this.Hand.size(); i++) {
			this.testSelf = testedCard.equals(this.Hand.get(i).valueToString());
			if (this.testSelf) {
				this.cardIndex = this.Hand.indexOf(Hand.get(i));
				break;
			}
		}
		return this.cardIndex;
	}

	public int randomCardIndex() 
	// Randomly pick a card based on it's index
	{
		this.cardIndex = (int) (Math.random() * this.Hand.size());
		return this.cardIndex;
	}

	public String stringOfIndexOfCardToRequest(int cardIndex) 
	// Returns the value toString of a card based on it's index
	{
		cardValueString = this.Hand.get(cardIndex).valueToString();
		return cardValueString;

	}

	public boolean testCardToRequest(String testedCard)
	// Called by computer or other player object. Iterates through that player's
	// hand to see if a specific value of a card is in their hand.
	{
		testedCard = testedCard.substring(0, 1).toUpperCase() + testedCard.substring(1).toLowerCase();
		for (int i = 0; i < this.Hand.size(); i++) {
			this.cardInHand = testedCard.equals(this.Hand.get(i).valueToString());
			if (this.cardInHand) {
				break;
			}
		}
		return this.cardInHand;
	}

	public int requestCard(String testedCard)
	// Called by computer or other player object. Iterates through that player's
	// hand to return the index of a specific value of a card in their hand.
	{
		testedCard = testedCard.substring(0, 1).toUpperCase() + testedCard.substring(1).toLowerCase();
		for (int i = 0; i < this.Hand.size(); i++) {
			this.cardInHand = testedCard.equals(this.Hand.get(i).valueToString());
			if (cardInHand) {
				this.cardIndex = this.Hand.indexOf(Hand.get(i));
				break;
			}
		}
		return this.cardIndex;
	}

	public boolean handIsEmpty() 
	// Determines if the hand arraylist is empty
	{
		if (this.Hand.size() == 0) {
			handEmpty = true;
		} else {
			handEmpty = false;
		}
		return handEmpty;
	}
	public void determineWinner(Player playerA, Player playerB)
	// Determines the winner of the game!
	{
		if (playerA.hand.Discard.size() > playerB.hand.Discard.size()){
			System.out.println("Player, " + playerA.getName() + " has won the game!");
		}
		else if (playerA.hand.Discard.size() < playerB.hand.Discard.size()){
			System.out.println("Player, " + playerB.getName() + " has won the game!");
		}
		else{
			System.out.println("The result is a tie!");
		}
	}
}
