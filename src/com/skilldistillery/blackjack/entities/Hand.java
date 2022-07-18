package com.skilldistillery.blackjack.entities;

import java.util.List;
import java.util.ArrayList;

abstract class Hand {
	protected List<Card> cards;
	
	protected Hand () {
		this.cards = new ArrayList<Card>();
	}
	
	protected Hand(Card card) {
		this.cards.add(card);
	}
	
	protected Hand (List<Card> cards) {
		this.cards = cards;
	}
	
	protected Hand (Hand hand) {
		this.cards = hand.getCards();
	}
	
	public ArrayList<Card> getCards() {
		return new ArrayList<Card>(this.cards);
	}
	
	public int getValue() {
		int totalValue = 0;
		for (Card card : this.cards) {
			totalValue += card.getValue();
		}
		return totalValue;
	}
	
	public void addCard(Card card) {
		this.cards.add(card);
	}
	
	public void clear() {
		this.cards.clear();
	}
	
	public Card remove(Card card) {
		return this.cards.remove(this.cards.indexOf(card));
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("| ");
		for (Card card : this.cards) {
			string.append(card.toString() + " | ");
		}
		return string.toString();
	}
}
