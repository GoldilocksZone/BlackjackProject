package com.skilldistillery.blackjack.entities;

import java.util.List;
import java.util.ArrayList;

abstract class BlackjackPosition {
	public List<BlackjackHand> hands;
	
	protected BlackjackPosition () {
		this.hands = new ArrayList<BlackjackHand>();
		this.addHand(new BlackjackHand());
		this.addHand(new BlackjackHand());
	}
	
	protected BlackjackPosition(ArrayList<BlackjackHand> hands) {
		this.hands = new ArrayList<BlackjackHand>(hands);
	}
	
	public void addHand(BlackjackHand hand) {
		this.hands.add(hand);
	}
	
	public List<BlackjackHand> getHands() {
		return this.hands;
	}
	
	public void clearHands() {
		this.hands.clear();
	}
}
