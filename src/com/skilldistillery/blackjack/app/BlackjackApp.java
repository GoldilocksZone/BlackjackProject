package com.skilldistillery.blackjack.app;

import com.skilldistillery.blackjack.entities.*;

public class BlackjackApp {

	public static void main(String[] args) {
		BlackjackApp app = new BlackjackApp();
		
		app.play();
	}
	
	public void play() {
		Table table = new Table();
		boolean keepPlaying = true;
		while (keepPlaying == true) {
			table.dealIn();
			if(!table.gameEnd()) {
				table.playerMoves();
			}
			if (!table.gameEnd()) {
				table.dealerMoves();
			}
			table.determineWinner();
			keepPlaying = table.playAgain();
			if (keepPlaying) {
				table.refresh();
			}
		}
		table.close();

	}
	
	public void printHeader() {
		System.out.println("*************");
		System.out.println("* Blackjack *");
		System.out.println("*************");
	}
	
	public boolean checkGameEnd(Table table) {
		return (table.loser != null);
	}
}
