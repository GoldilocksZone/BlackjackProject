package com.skilldistillery.blackjack.entities;

public class BlackjackDealer extends BlackjackPosition {
	Deck deck;
	
	public BlackjackDealer() {
		this.deck = new Deck();
	}
	
	public void dealTo(BlackjackHand hand) {
		hand.addCard(deck.getCard());
	}
}
