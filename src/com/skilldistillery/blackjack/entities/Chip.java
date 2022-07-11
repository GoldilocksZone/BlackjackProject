package com.skilldistillery.blackjack.entities;

public enum Chip {
	ONE(1),
	FIVE(5),
	TEN(10),
	TWENTY(20),
	FIFTY(50),
	ONE_HUNDRED(100),
	FIVE_HUNDRED(500),
	ONE_THOUSAND(1_000);
	
	private int value;
	
	Chip(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
