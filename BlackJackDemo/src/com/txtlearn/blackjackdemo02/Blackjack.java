package com.txtlearn.blackjackdemo02;

import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		
		// Welcome Message
		System.out.println("Welcome to Blackjack!");
		
		// Create out playing deck
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();
		
		Deck playerDeck = new Deck(); // Create a deck for the player
		
		Deck dealerDeck = new Deck(); //Create a deck for the dealer
		
		double playerMoney = 100.00;
		
		Scanner userInput = new Scanner(System.in);
		
		// Game loop
		while(playerMoney > 0) {
			// Take the players bet
			System.out.println("You have " + playerMoney + "€, how much you would like to bet?");
			double playerBet = userInput.nextDouble();
			if(playerBet > playerMoney ) {
				System.out.println("You cannot bet more than you have, please leave.");
				break;
			}
			
			boolean endRound = false;
			
			// Start dealing, player get two cards
			playerDeck.draw(playingDeck);
			playerDeck.draw(playingDeck);
			
			//Dealer gets two cards
			dealerDeck.draw(playingDeck);
			dealerDeck.draw(playingDeck);
			
			while(true) {
				System.out.println("Your hand:");
				System.out.print(playerDeck.toString());
				System.out.println("Your hand is valued at: " + playerDeck.cardsValue());
				
				// Display dealer hand
				System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString() + " and [Hidden]");
				
				// What does the player want to do?
				System.out.println("Would you like to (1) hit or (2) stand?");
				int response = userInput.nextInt();
				
				// They hit
				if(response == 1) {
					playerDeck.draw(playingDeck);
					System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
					// Bust if > 21
					if(playerDeck.cardsValue() > 21) {
						System.out.println("Bust. Currently valued at: " + playerDeck.cardsValue());
						playerMoney -= playerBet;
						endRound = true;
						break;
					}
				}
				if(response == 2) {
					break;
				}
			}
			
			// Reveal Dealer Cards
			System.out.println("Dealer Cards: " + dealerDeck.toString());
			// See if dealer has more points than player
			if((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false) {
				System.out.println("Dealer beats you!");
				playerMoney -= playerBet;
				endRound = true;
			}
			// Dealer draws at 16, stand at 17
			while((dealerDeck.cardsValue() < 17) && endRound == false) {
			dealerDeck.draw(playingDeck);
			System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
			}
			// Display total value for dealer
			System.out.println("Dealer's hand is valued at: " + dealerDeck.cardsValue());
			
			// Determine if dealer busted
			if((dealerDeck.cardsValue() > 21) && endRound == false) {
				System.out.println("Dealer busts! You win.");
				playerMoney += playerBet;
				endRound = true;
			}
			
			// Determine if push == tasapeli
			if ((playerDeck.cardsValue() == dealerDeck.cardsValue() && endRound == false)) {
				System.out.println("Push");
				endRound = true;
			}
			
			if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false) {
				System.out.println("You win the hand! :)");
				playerMoney += playerBet;
				endRound = true;
			} else if (endRound == false) {
				System.out.println("You lose the hand!");
				playerMoney -= playerBet;
				endRound = true;
			}
			
			playerDeck.moveAllToDeck(playingDeck);
			dealerDeck.moveAllToDeck(playingDeck);
			System.out.println("End of hand.");
			
		

	}
	
		System.out.println("Game over! You are out of money. :(");

	}
} 
