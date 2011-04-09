package participant;


import java.lang.String;

import storage.*;
import table.Table;

/**
 * Player class: Implements simple player options: modifyProfile, requestJoin, requestLeave, viewStatistics etc 
 * Player object has a contained storage object that assigns values for username, password, chip amounts etc
 * @author JackGivesBack
 *
 */

public class Player { 
	Statistics player;
  
  public Player(String username, String password){
	  this.player = Storage.loadPlayer(username, password);
  }
  
  /**
   * Allows the player to modify their player profile- password, name and age
   * @param  inputPassword the current password of the player
   * @param  newPassword the new modified password of the player
   * @param  age the age of the player
   * @return
   */	
  public void modifyProfile(String inputPassword, String newPassword, String realName, int age) {
	  if (this.player.validatePassword(inputPassword)) {
		  this.player.changePassword(newPassword);
	  }
	  
	  this.player.setRealName(realName);
	  this.player.setAge(age);
  }
  
  
  public void modifyProfile(String inputPassword, String newPassword) {
	  this.modifyProfile(inputPassword, newPassword, "noName", -1);
  }
  
  
  public void requestJoin(Table table, boolean canJoin) {
	  table.requestJoin(this, canJoin);
  }
  
  public void requestLeave(Table table, boolean canLeave) {
	  table.requestLeave(this, canLeave);
  }
  
  /**
   * Returns the user name of the current  player
   * @return the user name of the player
   */	
  public String getUsername() {
	  return this.player.getUsername();
  }
  
  /**
   * Returns the real name of the current player
   * @return the real name of the current player
   */	
  public String getRealName() {
	  return this.player.getRealName();
  }
  
  /**
   * Returns the age of the current player
   * @return the age of the current player
   */	
  public int getAge() {
	  return this.player.getAge();
  }
  
  /**
   * Returns the number of chips that the current player has
   * @return the number of chips of the current player's hand
   */	
  public int getChips() {
	  return this.player.getChips();
  }
  
  
  public void setChips(int numChips) {
	  this.player.setChips(numChips);
  }
  
  public boolean addChips(int numChips) {
	  return this.player.addChips(numChips);
  }
  
  public void addWin(int betValue) {
	  this.player.addWin(betValue);
  }
  
  public void addLoss(int betValue) {
	  this.player.addLoss(betValue);
  }
  
  public String toString() {
	  return this.player.toString();
  }
  
  public void displayStatistics() {
	  this.player.displayStatistics();
  }
  
  public Statistics toStatistics() {
	  return this.player;
  }
}

