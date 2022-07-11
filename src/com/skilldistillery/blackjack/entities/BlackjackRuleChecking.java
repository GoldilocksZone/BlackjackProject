package com.skilldistillery.blackjack.entities;

import java.util.Scanner;

public interface BlackjackRuleChecking {
	public boolean checkConditions(BlackjackPosition position);
	
	public boolean execute(BlackjackPosition position, BlackjackDealer dealer, Scanner scanner);
}
