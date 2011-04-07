LetsPlayCards

Authors:
Gabriel Forgues
Surbhi Gupta
Justin Kat
Jessica Makucka
Chandani Patel

Platforms:
Windows 7

Compilers:
JDK1.6.0_16

Third Party Libraries for Source Code: None

Third Party Libraries for Testing:
JUnit 
http://www.junit.org/

Source File Organisation:
cards: Contains all files dealing with cards
	AllCards.java
	BlackjackHand.java
	Card.java
	Deck.java
	Hand.java

game: Contains functionality used in games such as Blackjack (ex: hit(), stand(), etc.)
	Blackjack.java

gameEngine: Contains logic to run a game
	GameEngine.java
	GameEngineInterface.java

gui: Contains user interface menu functionality
	MainInterface.java

participant: Contains functions dealing with players and spectator information
	Chips.java
	Player.java
	Spectator.java

storage:  Contains all reading and writing to files
	Storage.java
	BlackjackStorage.java
	Statistics.java
	HallOfFame.java

table: Contains logic to set up a game (adding players etc.)
	Table.java
	



All rights reserved.