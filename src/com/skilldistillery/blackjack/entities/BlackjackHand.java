package com.skilldistillery.blackjack.entities;

public class BlackjackHand extends Hand {
	private boolean isSplit, isStayed, isSurrendered, isBlackjack, isBust;
	
	public BlackjackHand() {
		this.isSplit = false;
		this.isStayed = false;
		this.isSurrendered = false;
		this.isBlackjack = false;
	}
	
	public BlackjackHand(Card card) {
		this();
		this.cards.add(card);
	}
	
	@Override
	public int getValue() {
		int valueOfHand = 0;
		for (Card card : this.cards) {
			valueOfHand += card.getValue();
		}
		return valueOfHand;
	}
	
	public boolean isBlackjack() {
		return this.isBlackjack;
	}
	
	public Card remove(int index) {
		return this.cards.remove(index);
	}
	
	public boolean isBust() {
		return this.isBust;
	}
	
	public boolean isSplit() {
		return this.isSplit;
	}
	
	public void split() {
		this.isSplit = true;
	}
	
	public boolean isStayed() {
		return this.isStayed;
	}
	
	public void stay() {
		this.isStayed = true;
	}
	
	public boolean isSurrendered() {
		return this.isSurrendered;
	}
	
	public void surrender() {
		this.isSurrendered = true;
	}
	
	public void markBlackjack() {
		this.isBlackjack = true;
	}
	
	public void markBust() {
		this.isBust = true;
	}
	
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append(super.toString() + "\n");
		string.append("Total value of hand: " + this.getValue());
		if (this.isBust()) {
			string.append("This hand is a bust.");
		} else if (this.isBlackjack()) {
			string.append("This hand is a blackjack!");
		}
		return string.toString();
	}
	// TODO Write isHard and isSoft methods
}
