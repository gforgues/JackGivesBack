package participant;

import java.io.*;
import java.util.*;
import java.lang.String;

/**
 * A simple database interface
 * 
 * @author Surbhi + Jessica
 *
 */

//how are we connecting the player class with the hand and bet classes???
//Inheritance?!?
public class Player extends cards.Hand implements Participant {
  private String userName;
  private String password;
  private String realName;
  private int age;
  private int wins = 0;
  private int losses = 0;
  
  public Player(String username, String password, String realName, int age){
	  this.userName = username;
	  this.password = password;
	  this.realName = realName;
	  this.age = age;
  }
  
  public Player(String username, String password) {
	  new Player(username, password, "", -1);
  }
  
  public void modifyProfile(String newPassword, String realName, int age){	  
	  checkPassword();
	  
	  if (checkPassword()) {
		  this.password = newPassword;
		  
		  if (realName != null) {
			  this.realName = realName;
		  }
		  //what if user doesn't input age?
		  if (age != -1) {
			  this.age = age;
		  }
	  }
  }
  
  public void modifyProfile(String newPassword) {
	  this.modifyProfile(newPassword, "", -1);
  }
  
  /*
   * 
   * Helper method for when a player modifies their password, that verify
   * their current one
   */
  private boolean checkPassword() {
	  Scanner keyboard = new Scanner(System.in);
	  String checkPassword;
	  boolean checker = false;
	  
	  System.out.println("Enter current password");
	  checkPassword = keyboard.nextLine();
	  
	  if (checkPassword.equals(this.password)) {
		  checker = true;
	  } else {
		  checker = false;
	  }
	  
	  return checker;
  }
  
  public void requestJoin() {
	  Table.requestJoin(this);
  }
  
  //Should we say Table.saveGame(this)- cuz it has to do with a particular
  //player..?
  public void saveGame() {
	  Table.saveGame(this);
  }
  
  //is the save game method in Table class a static method?
  public void requestLeave() {
	  Table.requestLeave(this);
  }
  
  //is the save game method in Table class a static method?
  public void closeGame() {
	  Table.closeGame();
  }
  
  public String getName() {
	  return this.userName;
  }
  
  public void addWins() {
	  wins++;
  }
  
  public void addLosses() {
	  losses++;
  }
  
  public int getWins() {
	  return wins;
  }
  
  public int getLosses() {
	  return losses;
  }
  
  public int getGamesPlayed() {
	  return wins + losses;
  }
}

