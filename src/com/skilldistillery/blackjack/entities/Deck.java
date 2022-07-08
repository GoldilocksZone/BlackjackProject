package com.skilldistillery.blackjack.entities;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	List<Card> deck = new ArrayList<Card>();
	
	Deck () {
		this.initializeNewDeck();
		this.shuffle();
	}
	
	public void initializeNewDeck() {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				this.deck.add(new Card(rank, suit));
			}
		}
	}
	
	public int checkDeckSize() {
		return this.deck.size();
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	public Card dealCard() {
		return this.deck.remove(0);
	}
}
