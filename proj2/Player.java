package proj2;

/**
* File: Player.java
* Project2
* @author Javier Santiago
* Date 5/16/21
* Course: CCOM4029

*/


import java.util.*;



public class Player {
	private Hand hand;
	private boolean turn;
	private boolean stackAvail;
	private boolean deckAvail;
	private boolean layAvail;
	private boolean layOnStackAvail;
	private Card addedCard;
	private List discardedCards;
	private int playerId;



	public Player(int playerId) {
		hand = new Hand();
		turn = false;
		stackAvail = false;
		deckAvail = false;
		layAvail = false;
		layOnStackAvail = false;
		discardedCards = new ArrayList();
		this.playerId = playerId;
	}

	public void addCardToHand(Card card){


		hand.addCard(card);
		hand.sort();
	}

	public void removeCardFromHand(Card card){
	
		hand.removeCard(card);
		hand.sort();
	}

	public int getHandEval(){
		return hand.evaluateHand();
	}

	public void setTurn(boolean turn){
		this.turn = turn;

		if (turn){
			setStackAvail(true);
			setDeckAvail(true);
			clearDiscardedCars();
		}
		else{
			setLayAvail(false);
			setLayOnStackAvail(false);
		}
	}

	public boolean getTurn(){
		return turn;
	}

	public void setStackAvail(boolean avail){
		stackAvail = avail;
	}

	public boolean isStackAvail(){
		return stackAvail;
	}

	public void setDeckAvail(boolean avail){
		deckAvail = avail;
	}

	public boolean isDeckAvail(){
		return deckAvail;
	}

	public void setLayAvail(boolean avail){
		layAvail = avail;
	}

	public boolean isLayAvail(){
		return layAvail;
	}

	public void setLayOnStackAvail(boolean avail){
		layOnStackAvail = avail;
	}

	public boolean isLayOnStackAvail(){
		return layOnStackAvail;
	}

	public void setAddedCard(Card card){
		addCardToHand(card);
		addedCard = card;
	}

	public void displayAddedCard(){
		System.out.println("\tAdded: " + addedCard.toString());
	}

	public void addToDiscardedCards(Card card){
		removeCardFromHand(card);
		discardedCards.add(card);

	}

	public void clearDiscardedCars(){
		discardedCards.clear();
	}

	public void displayDiscardedCards(){

		StringBuffer discarded = new StringBuffer("\tDiscarded: ");

		for (int i = 0; i < discardedCards.size(); i++){
			if (i != discardedCards.size() -1){
				discarded.append(discardedCards.get(i));
				discarded.append(", ");
			}
			else{
				discarded.append(discardedCards.get(i));
			}
		}

		System.out.println(discarded);
	}

	public void displayHand(boolean intial){
		if (intial){
			System.out.println("Initial Player " + playerId + ": " + hand.toString());

		}
		else
			System.out.println("\tHand now: " + hand.toString());
	}

	public boolean isHandEmpty(){
		return hand.getNumberOfCards() == 0;
	}

	public void displayStatus(){
		System.out.println("Player " + playerId);
		displayAddedCard();
		displayDiscardedCards();
		displayHand(false);
	}


}
