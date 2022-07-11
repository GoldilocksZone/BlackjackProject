package com.skilldistillery.blackjack.entities;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public enum Round implements BlackjackStages {
	SETUP {
		public void execute(Table table) {
			int numPlayers = getNumPlayers(table.getScanner());
			for (int i = 0; i < numPlayers; i++) {
				table.addPlayer(new BlackjackPlayer(getPlayerName(table.getScanner())));
			}
		}
	},
	PLACE_BETS {
		public void execute(Table table) {
			for (BlackjackPlayer player : table.getPlayers()) {
				table.tryMove(Move.PLACE_BET, player);
			}
		}

	},
	DEAL_IN {
		private final int NUM_DEALS = 2;

		public void execute(Table table) {
			for (int i = 1; i <= NUM_DEALS; i++) {
				for (BlackjackPlayer player : table.getPlayers()) {
					System.out.println("Dealing to" + player.getName());
					table.getDealer().dealTo(player.hands.get(0));
				}
				table.getDealer().dealTo(table.getDealer().hands.get(0));
				System.out.println("Dealing to dealer");
			}
			checkScores();
			System.out.println("*** Dealer ***");
			System.out.println(table.getDealer().hands.get(0).toString());
			for (BlackjackPlayer player : table.getPlayers()) {
				System.out.println("*** " + player.getName() + " ***");
				System.out.println(player.hands.get(0).toString());
			}
		}
	},
	PLAYER_MOVES {
		public void execute(Table table) {
			boolean isSuccessful = false;
			for (BlackjackPlayer player : table.getPlayers()) {
				System.out.println("***" + player.getName() + "***");
				while (!isSuccessful) {
					isSuccessful = table.tryMove(getPlayerMove(table.getScanner()), player);
					if (!isSuccessful) {
						System.out.println("Unable to perform move. Please try again.");
					}
				}
			}
		}
	},
	DEALER_MOVES {
		public void execute(Table table) {
			while (table.getDealer().hands.get(0).getValue() < 17) {
				table.tryMove(Move.HIT, table.getDealer());
			}
		}

	},
	SETTLE_UP {
		public void execute(Table table) {
			for (BlackjackPlayer player : table.getPlayers()) {
				player.resetBet();
			}
		}

	},
	END_GAME {
		public void execute(Table table) {
		}
	};

	public static int getNumPlayers(Scanner scanner) {
		int numPlayers;
		System.out.print("How many players will join the game? ");
		numPlayers = scanner.nextInt();
		scanner.nextLine();
		return numPlayers;
	}

	public static String getPlayerName(Scanner scanner) {
		String name;
		System.out.print("What is your name? ");
		name = scanner.next();
		return name;
	}

	public Move getPlayerMove(Scanner scanner) {
		int selection;
		System.out.println("What would you like to do?");
		for (Move move : Move.values()) {
			System.out.println((move.ordinal() + 1) + ". " + move);
		}
		System.out.print("> ");
		selection = scanner.nextInt();
		scanner.nextLine();
		return Move.values()[selection - 1];
	}
	
	public void checkScores(Table table) {
		
	}
}
