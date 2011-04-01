package table;

import storage.*;
import java.util.ArrayList;
import participant.*;

public class Table {
 Player tableOwner;
 ArrayList<Player> players = new ArrayList<Player>();
 ArrayList<Spectator> spectators= new ArrayList<Spectator>();

 public Table(Player owner) {
  this.tableOwner = owner;
 }
 
 public ArrayList<Player> getAllPlayers() {
  return players;
 }
 
 public ArrayList<Spectator> getAllSpectators(){
  return spectators;
 }
 
 public Player getPlayer(int index) {
  return players.get(index);
 }
 
 public Spectator getSpectator(int index) {
  return spectators.get(index);
 }


 public void requestJoin(Player player){
   boolean join=false ; 
   
   if(join==true){
    System.out.println("Request join accepted");
    //Add player to array list of players
    players.add(player);
   }
    else
       {
       System.out.println("Request join rejected");
       }
 } 

 public void requestLeave(Player player) {
  boolean leave=false;
   
   if(leave==true){
    System.out.println("Request leave accepted");
    //do not have to remove the player as they are not in the list
   }
    else
    {
     System.out.println("Request leave rejected");
        }
  }
 
 public void requestView(Spectator spectator) {
   boolean view=false;
  
  if(view==true){
  System.out.println("TableOwner accepts spectator");
  //Add spectator to arraylist of spectators
   spectators.add(spectator);
  }
  else
  {
   System.out.println("TableOwner rejects spectator");
  }
 }

 //Helper method to find the index of the spectator in the array list of spectators
 private int findIndex(Spectator spectator) {
  int index=0;
  for (int i=0; i<spectators.size(); i++) {
   if (spectators.get(i).equals(spectator)) {
       index=i;
   }
  }
  return index;
 }  
 
 public void requestLeave(Spectator spectator) {
     boolean leave=false;
  int i;
     i=this.findIndex(spectator);
     
   if(leave==true){
  System.out.println("TableOwner accepts spectator from leaving the table");
  //remove spectator from list of spectators
  spectators.remove(i);
  }
  else
  {
   System.out.println("TableOwner rejects spectator from leaving the table");
     }
 }
 
 //need save and close methods for both player and spectator 
 public boolean savePlayer(Statistics player) {
  return Storage.savePlayer(player);
 }
 
 public boolean saveGame(Statistics spectator){
  return Storage.savePlayer(spectator);
 }
 

}