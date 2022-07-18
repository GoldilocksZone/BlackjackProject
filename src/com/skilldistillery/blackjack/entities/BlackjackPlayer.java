package com.skilldistillery.blackjack.entities;

import java.util.List;

public class BlackjackPlayer extends BlackjackPosition {
	private String name;
	
	public BlackjackPlayer(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return new String(this.name);
	}

	public List<BlackjackHand> getHands() {
		return super.getHands();
	}
}
