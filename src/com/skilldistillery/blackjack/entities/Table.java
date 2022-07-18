package com.skilldistillery.blackjack.entities;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Table {
	private Scanner scanner = new Scanner(System.in);
	private BlackjackPlayer player;
	private BlackjackDealer dealer;
	public BlackjackPosition loser;

	public Table() {
		this.player = new BlackjackPlayer(getPlayerName());
		this.dealer = new BlackjackDealer();
	}

	public void refresh() {
		this.player = new BlackjackPlayer(this.player.getName());
		this.dealer = new BlackjackDealer();
		this.loser = null;
	}
	
	public void close() {
		System.out.println("Thank you for playing!");
		scanner.close();
		System.exit(0);;
	}

	public String getPlayerName() {
		System.out.print("What is your name? ");
		return scanner.nextLine();
	}

	public void dealIn() {
		for (int i = 1; i <= 2; i++) {
			this.dealTo(this.player, 0);
			this.dealTo(this.dealer, 0);
		}
		// Print player hand & check score
		System.out.println(this.player.getName() + ":");
		this.player.printAllHands();
		System.out.println();
		// Print dealer hand & check score
		System.out.println("Dealer:");
		this.dealer.printAllHands();
		this.checkBlackjackOrBust(this.player);
		this.checkBlackjackOrBust(this.dealer);
		System.out.println();
	}

	public void dealTo(BlackjackPosition position, int handIndex) {
		position.addCardToHand(this.dealer.dealCard(), handIndex);
	}

	public void playerMoves() {
		boolean isComplete = false;
		System.out.println("***" + player.getName() + "***");
		while (!isComplete) {
			switch (getPlayerMove()) {
			case HIT:
				hit(this.player);
				break;
			case STAY:
				isComplete = true;
				break;
			case SPLIT:
				split(this.player);
				break;
			case SURRENDER:
				this.loser = this.player;
				isComplete = true;
				break;
			default:
				System.out.println("Error: Player move not recognized.");
			}
			checkBlackjackOrBust(this.player);
			if (this.loser != null) {
				isComplete = true;
			}
		}
	}

	public void dealerMoves() {
		boolean isComplete = false;
		while (!isComplete) {
			if (dealer.hands.get(0).getValue() < 17) {
				hit(this.dealer);
				System.out.println("Dealer:");
				this.dealer.printAllHands();
				checkBlackjackOrBust(this.dealer);
				if (this.loser != null) {
					isComplete = true;
				}
			} else {
				isComplete = true;
			}
		}
	}

	public Move getPlayerMove() {
		System.out.println("What would you like to do?");
		for (Move move : Move.values()) {
			System.out.println(move.getValue() + ". " + move);
		}
		while (true) {
			int selection = 0;
			System.out.print("> ");
			try {
				selection = this.scanner.nextInt();
			} catch (InputMismatchException ime) {
				System.out.println("Invalid selection. Please try again.");
			} finally {
				scanner.nextLine();
			}

			for (Move move : Move.values()) {
				if (move.getValue() == selection) {
					return move;
				}
			}
			System.out.println("Invalid selection. Please try again.");
		}
	}

	public void hit(BlackjackPosition position) {
		if (position.hands.size() == 1) {
			this.dealTo(position, 0);
		} else {
			this.dealTo(position, getIndexOfHandToHit(position));
		}
		// Print player hands
		System.out.println(this.player.getName() + ":");
		this.player.printAllHands();
		System.out.println();
	}

	public int getIndexOfHandToHit(BlackjackPosition position) {
		System.out.println("Type the number of the hand would you like to hit.");
		position.printAllHands();
		boolean validSelection = false;
		int selection = 0;
		while (!validSelection) {
			System.out.print("> ");
			try {
				selection = scanner.nextInt();
			} catch (InputMismatchException ioe) {
				System.out.println("Invalid selection. Please try again.");
			} finally {
				scanner.nextLine();
			}
			if (selection > 0 && selection <= position.hands.size()) {
				validSelection = true;
			} else {
				System.out.println("Invalid selection. Please try again.");
			}
		}
		return selection - 1;
	}

	public void split(BlackjackPosition position) {
		List<Integer> splittableHandIndices = getSplittableHandIndices(position);
		if (splittableHandIndices.size() == 0) {
			System.out.println("No splittable hands.");
		} else {
		for (Integer handIndex : splittableHandIndices) {
			Hand hand = position.hands.get(handIndex);
			position.addHand(new BlackjackHand(hand.remove(hand.cards.get(0))));
			this.dealTo(position, handIndex);
			this.dealTo(position, position.hands.size()-1);
		}
		// Print player hands
		System.out.println(this.player.getName() + ":");
		this.player.printAllHands();
		}
	}

	public List<Integer> getSplittableHandIndices(BlackjackPosition position) {
		List<Integer> handIndices = new ArrayList<Integer>();
		for (int handIndex = 0; handIndex < position.hands.size(); handIndex++) {
			Hand hand = position.hands.get(handIndex);
			if (hand.cards.size() == 2 && hand.cards.get(0).getRank() == hand.cards.get(1).getRank()) {
				handIndices.add(handIndex);
			}
		}
		return handIndices;
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

	public void checkBlackjackOrBust(BlackjackPosition position) {
		for (BlackjackHand hand : position.hands) {
			if (hand.getValue() == 21) {
				hand.markBlackjack();
				System.out.println("Blackjack! Game over!");
				if (position instanceof BlackjackPlayer) {
					this.loser = this.dealer;
				} else {
					this.loser = this.player;
				}
			} else if (hand.getValue() > 21) {
				hand.markBust();
				System.out.println("Bust! Game over!");
				this.loser = position;
			}
		}
	}

	public boolean gameEnd() {
		if (this.loser != null) {
			return true;
		}
		return false;
	}

	public void determineWinner() {
		if (this.dealer == this.loser) {
			System.out.println(this.player.getName() + " wins!");
		} else if (this.player == this.loser) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println(this.player.getName() + ":");
			this.player.printAllHands();
			System.out.println();
			// Print dealer hand & check score
			System.out.println("Dealer:");
			this.dealer.printAllHands();
			System.out.println();
			int maxHandValue = 0;
			for (BlackjackHand hand : this.player.hands) {
				if (hand.getValue() > maxHandValue && hand.getValue() < 22) {
					maxHandValue = hand.getValue();
				}
			}
			if (maxHandValue > this.dealer.getHands().get(0).getValue()) {
				this.loser = this.dealer;
			} else if (maxHandValue < this.dealer.getHands().get(0).getValue()) {
				this.loser = this.dealer;
			}
			if (this.dealer == this.loser) {
				System.out.println(this.player.getName() + " wins!");
			} else if (this.player == this.loser) {
				System.out.println("Dealer wins!");
			} else {
				System.out.println("Draw!");
			}
		}
	}
}
