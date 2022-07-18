package com.skilldistillery.blackjack.entities;

import java.util.List;
import java.util.ArrayList;

abstract class BlackjackPosition {
	protected List<BlackjackHand> hands;

	protected BlackjackPosition() {
		this.hands = new ArrayList<BlackjackHand>();
		this.addHand(new BlackjackHand());
	}

	protected BlackjackPosition(ArrayList<BlackjackHand> hands) {
		this.hands = new ArrayList<BlackjackHand>(hands);
	}

	protected void addHand(BlackjackHand hand) {
		this.hands.add(hand);
	}

	protected List<BlackjackHand> getHands() {
		return this.hands;
	}

	protected void clearHands() {
		this.hands.clear();
	}

	protected void addCardToHand(Card card, int handIndex) {
		this.hands.get(handIndex).addCard(card);
	}

	protected void printAllHands() {
		if (this.hands.size() == 1) {
			System.out.println(this.hands.get(0).toString());
		} else {
			for (int handIndex = 0; handIndex < this.hands.size(); handIndex++) {
				System.out.println("Hand " + (handIndex + 1) + ": " + this.hands.get(handIndex).toString());
			}
		}
	}
}
