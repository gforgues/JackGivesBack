package participant;

import java.io.*;
import java.util.*;
import java.lang.String;

import storage.*;

/**
 * A simple database interface
 * 
 * @author JackGivesBack
 *
 */

public class Player { //cards.Hand implements Participant {
	Statistics player;
  
  public Player(String username, String password){
	  this.player = Storage.loadPlayer(username, password);
  }
  
  public void modifyProfile(String inputPassword, String newPassword, String realName, int age) {
	  //this.player.validatePassword(newPassword);
	  if (this.player.validatePassword(inputPassword)) {
		  this.player.changePassword(newPassword);
	  }
	  
	  this.player.setRealName(realName);
	  this.player.setAge(age);
  }
  
  public void modifyProfile(String inputPassword, String newPassword) {
	  this.modifyProfile(inputPassword, newPassword, "", -1);
  }
    
  public void requestJoin(Table table) {
	  table.requestJoin(this);
  }
  
  //Should we say Table.saveGame(this)- cuz it has to do with a particular
  //player..?
  public void saveGame() {
	  Table.saveGame(this);
  }
  
  //is the save game method in Table class a static method?
  public void requestLeave(Table table) {
	  table.requestLeave(this);
  }
  
  public String getUsername() {
	  return this.player.getUsername();
	  //return this.userName;
  }
  
  public String getRealName() {
	  return this.player.getRealName();
  }
  
  public int getAge() {
	  return this.player.getAge();
  }
  
  public int getChips() {
	  return this.player.getChips();
  }
  
  public void setChips(int numChips) {
	  this.player.setChips(numChips);
  }
  
  public void addChips(int numChips) {
	  this.player.addChips(numChips);
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
  
  public String getStats() {
	  return this.player.getStats();
  }
}

