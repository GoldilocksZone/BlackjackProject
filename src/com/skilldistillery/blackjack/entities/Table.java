package com.skilldistillery.blackjack.entities;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Table {
	private Scanner scanner = new Scanner(System.in);
	private List<BlackjackPlayer> players;
	private BlackjackDealer dealer;
	private Moderator moderator;

	public Table() {
		this.players = new ArrayList<BlackjackPlayer>();
		this.dealer = new BlackjackDealer();
		this.moderator = new Moderator();
	}

	public void close() {
		System.out.println("Thank you for playing!");
		scanner.close();
		System.exit(0);
	}

	public void addPlayer(BlackjackPlayer player) {
		this.players.add(player);
	}

	public List<BlackjackPlayer> getPlayers() {
		return this.players;
	}

	public boolean tryMove(Move move, BlackjackPosition player) {
		return this.moderator.request(move, player, this.dealer, scanner);
	}

	public BlackjackDealer getDealer() {
		return this.dealer;
	}

	public Round getCurrentRound() {
		return this.moderator.getCurrentRound();
	}

	public void goToNextRound() {
		this.moderator.goToNextRound();
	}

	public Scanner getScanner() {
		return this.scanner;
	}

	public void getNewDealer() {
		this.dealer = new BlackjackDealer();
	}

	public void setRound(Round round) {
		this.moderator.setRound(round);
	}

	public boolean playAgain() {
		String selection;
		while (true) {
			System.out.print("Would you like to play again? (y/n) ");
			selection = scanner.nextLine();
			switch (selection) {
			case "y":
			case "Y":
			case "YES":
			case "yes":
			case "Yes":
				return true;
			case "n":
			case "N":
			case "NO":
			case "no":
			case "No":
				return false;
			default:
				System.out.println("Invalid response. Please try again.");
				break;
			}
		}
	}
}
