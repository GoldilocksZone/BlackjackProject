package com.skilldistillery.blackjack.entities;

public enum Move{
	HIT(1),
	STAY(2),
	SPLIT(3),
	SURRENDER(4);
	
	private int value;
	
	Move(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
