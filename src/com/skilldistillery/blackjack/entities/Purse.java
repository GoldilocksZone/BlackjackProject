package com.skilldistillery.blackjack.entities;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

public class Purse {
	private Map<Chip, Integer> chips;

	public Purse () {
		this.chips = new HashMap<Chip, Integer>();
	}
	
	public Purse(Purse purse) {
		this.chips = new HashMap<Chip, Integer>(purse.chips);
	}
	
	public void addChip(Chip chip) {
		if (this.chips.get(chip) != null) {
			this.chips.put(chip, this.chips.get(chip)+1);
		} else {
			this.chips.put(chip, 1);
		}
	}
	
	public void removeChip(Chip chip) {
		if(this.chips.get(chip) > 0) {
			this.chips.put(chip, this.chips.get(chip)-1);
		}
	}
	
	public int getTotalValue() {
		int totalValue = 0;
		Set<Chip> chips = this.chips.keySet();
		Iterator<Chip> it = chips.iterator();
		Chip chip;
		while (it.hasNext()) {
			chip = it.next();
			totalValue += this.chips.get(chip) * chip.getValue();
		}
		return totalValue;
	}
	
	@Override
	public String toString() {
		return this.chips.toString();
	}
	
	public void run() {
		this.addChip(Chip.FIVE);
		System.out.println(this.toString());
		this.removeChip(Chip.FIVE);
		System.out.println(this.toString());
	}
	
	public static void main(String[] args) {
		Purse purse = new Purse();
		purse.run();
	}
}
