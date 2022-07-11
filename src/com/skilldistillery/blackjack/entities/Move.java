package com.skilldistillery.blackjack.entities;

import java.util.Scanner;
import java.util.List;

import java.util.ArrayList;

public enum Move implements BlackjackRuleChecking {
	PLACE_BET {
		private int betAmount;
		private Scanner scanner;

		@Override
		public boolean checkConditions(BlackjackPosition position) {
			if (position instanceof BlackjackPlayer) {
				int purseValue = ((BlackjackPlayer) position).getPurseValue();
				this.betAmount = getBetFromUser(purseValue, this.scanner);
				if (this.betAmount <= purseValue) {
					return true;
				}
			}
			return false;
		}

		public boolean execute(BlackjackPosition position, BlackjackDealer dealer, Scanner scanner) {
			this.scanner = scanner;
			if (this.checkConditions(position) == true) {
				((BlackjackPlayer) position).subtractFromPurseValue(this.betAmount);
				((BlackjackPlayer) position).setBet(this.betAmount);
				return true;
			}
			return false;
		}
	},
	HIT {
		private List<BlackjackHand> hittableHands = new ArrayList<BlackjackHand>();

		@Override
		public boolean checkConditions(BlackjackPosition position) {
			for (BlackjackHand hand : position.hands) {
				if (!hand.isBust() && !hand.isBlackjack() && !(hand.isSplit() && hand.cards.size() >= 2)) {
					this.hittableHands.add(hand);
				}
				if (this.hittableHands.size() > 0) {
					return true;
				}
			}
			return false;
		}

		public boolean execute(BlackjackPosition position, BlackjackDealer dealer, Scanner scanner) {
			if (this.checkConditions(position) == true) {
				for (BlackjackHand hand : this.hittableHands) {
					dealer.dealTo(hand);
				}
				return true;
			}
			return false;
		}
	},
	STAY {
		@Override
		public boolean checkConditions(BlackjackPosition position) {
			return true;
		}

		public boolean execute(BlackjackPosition position, BlackjackDealer dealer, Scanner scanner) {
			if (this.checkConditions(position) == true) {
				for (BlackjackHand hand : position.hands) {
					hand.stay();
				}
			}
			return true;
		}
	},
	DOUBLE_DOWN {
		private int betAmount;
		private Scanner scanner;

		@Override
		public boolean checkConditions(BlackjackPosition position) {
			if (position instanceof BlackjackPlayer) {
				int purseValue = ((BlackjackPlayer) position).getPurseValue();
				this.betAmount = getBetFromUser(purseValue, this.scanner);
				if (this.betAmount <= purseValue) {
					return true;
				}
			}
			return false;
		}

		public boolean execute(BlackjackPosition position, BlackjackDealer dealer, Scanner scanner) {
			this.scanner = scanner;
			if (this.checkConditions(position) == true) {
				((BlackjackPlayer) position).subtractFromPurseValue(this.betAmount);
				((BlackjackPlayer) position).setBet(this.betAmount);
				return true;
			}
			return false;
		}
	},
	SPLIT {
		private List<BlackjackHand> splittableHands = new ArrayList<BlackjackHand>();

		@Override
		public boolean checkConditions(BlackjackPosition position) {
			for (BlackjackHand hand : position.hands) {
				if (!hand.isBust() &&
						!hand.isBlackjack() &&
						hand.cards.size() == 2 &&
						(hand.cards.get(0).getRank() == hand.cards.get(1).getRank())) {
					this.splittableHands.add(hand);
				}
				if (this.splittableHands.size() > 0) {
					return true;
				}
			}
			return false;
		}

		public boolean execute(BlackjackPosition position, BlackjackDealer dealer, Scanner scanner) {
			if (this.checkConditions(position) == true) {
				List<BlackjackHand> handsToAdd = new ArrayList<BlackjackHand>(); 
				for (BlackjackHand hand : this.splittableHands) {
					hand.split();
					handsToAdd.add(new BlackjackHand(hand.cards.remove(hand.cards.size()-1)));
				}
				for (BlackjackHand hand : handsToAdd) {
					hand.split();
					position.hands.add(hand);
				}
				return true;
			}
			return false;
		}
	},
	SURRENDER {
		@Override
		public boolean checkConditions(BlackjackPosition position) {
			return true;
		}

		public boolean execute(BlackjackPosition position, BlackjackDealer dealer, Scanner scanner) {
			if (this.checkConditions(position) == true) {
				for (BlackjackHand hand : position.hands) {
					hand.surrender();
				}
			}
			return true;
		}
	};

	private static int getBetFromUser(int purseValue, Scanner scanner) {
		int bet;
		System.out.printf("Your purse contains: $%d %n", purseValue);
		System.out.print("How much would you like to bet? ");
		bet = scanner.nextInt();
		scanner.nextLine();
		return bet;
	}
	
	
}
