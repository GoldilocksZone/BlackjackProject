package com.skilldistillery.blackjack.app;

import com.skilldistillery.blackjack.entities.*;
import java.util.Scanner;

public class BlackjackApp {

	public static void main(String[] args) {
		BlackjackApp app = new BlackjackApp();
		
		app.play();
	}
	
	public void play() {
		Table table = new Table();
		System.out.println(table.getCurrentRound());
		table.getCurrentRound().execute(table);
		while (table.getCurrentRound() != Round.END_GAME) {
			table.goToNextRound();
			System.out.println(table.getCurrentRound());
			table.getCurrentRound().execute(table);
		}
		table.close();
	}
	
}
