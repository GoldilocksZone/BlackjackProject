package com.skilldistillery.blackjack.entities;

public enum Suit {
	HEARTS("Hearts", "♥"), SPADES("Spades", "♠"), CLUBS("Clubs", "♣"), DIAMONDS("Diamonds", "♦");

	private String name, symbol;

	Suit(String name, String symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	public String getSymbol() {
		return this.symbol;
	}

	@Override
	public String toString() {
		return this.getSymbol();
	}
}
