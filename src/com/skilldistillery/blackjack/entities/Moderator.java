package com.skilldistillery.blackjack.entities;

import java.util.Scanner;

public class Moderator {

	private Round round;
	
	public Moderator() {
		this.round = Round.SETUP;
	}
	
	public boolean request(	Move move,
							BlackjackPosition position, BlackjackDealer dealer, Scanner scanner) {
		return move.execute(position, dealer, scanner);
	}
	
	public void conduct(Round round,
							Table table) {
		round.execute(table);
	}
	
	public Round getCurrentRound() {
		return this.round;
	}
	
	public Round goToNextRound() {
		switch (this.round) {
		case SETUP:
			this.round = Round.PLACE_BETS;
			break;
		case PLACE_BETS:
			this.round = Round.DEAL_IN;
			break;
		case DEAL_IN:
			this.round = Round.PLAYER_MOVES;
			break;
		case PLAYER_MOVES:
			this.round = Round.DEALER_MOVES;
			break;
		case DEALER_MOVES:
			this.round = Round.SETTLE_UP;
			break;
		case SETTLE_UP:
			this.round = Round.END_GAME;
			break;
		case END_GAME:
		}
		return this.round;
	}
}
