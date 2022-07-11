package com.skilldistillery.blackjack.entities;

import java.util.List;

public class BlackjackPlayer extends BlackjackPosition {
//	private Purse purse;
	private int purse, bet;
	private String name;
	
	public BlackjackPlayer(String name) {
		super();
		this.purse = 100;
		this.bet = 0;
		this.name = name;
//		this.purse = new Purse();
	}
	
//	public BlackjackPlayer(Purse purse) {
//		super();
//		this.purse = new Purse(purse);
//	}
	
	public String getName() {
		return new String(this.name);
	}
	
	public int getPurseValue() {
//		return purse.getTotalValue();
		return purse;
	}
	
	public int getBet() {
		return this.bet;
	}
	
	public void setBet(int amount) {
		this.bet = amount;
	}
	
	public void resetBet() {
		this.setBet(0);;
	}
	
	public void subtractFromPurseValue(int amount) {
		this.purse -= amount;
	}
	
	public void addToPurseValue(int amount) {
		this.purse += amount;
	}

	public List<BlackjackHand> getHands() {
		return this.getHands();
	}
}
