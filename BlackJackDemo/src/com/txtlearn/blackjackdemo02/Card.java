package com.txtlearn.blackjackdemo02;

public class Card {
	
	private Suit suit;
	private Value value;
	
	public Card(Suit suit, Value value) {
		this.value = value;
		this.suit = suit;
	}
	
	
	public String toString() {
		return this.suit.toString() + "-" + this.value.toString(); // Antaa yhden kortin maan ja arvon
	}
	
	public Value getValue() {
		return this.value;
	}

}
