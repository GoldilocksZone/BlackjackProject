package com.skilldistillery.blackjack.entities;

public class BlackjackDealer extends BlackjackPosition {
	Deck deck;
	
	public BlackjackDealer() {
		this.deck = new Deck();
	}
	
	public Card dealCard() {
		return deck.getCard();
	}
}
