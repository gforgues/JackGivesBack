package table;

import junit.framework.*;
import java.util.ArrayList;

import participant.*;

public class TestTable extends TestCase {
	private Player p1; 
    private Player p2; 
    private Player tableOwner; 
    private Table table;
    
    public void setUp() { 
       p1 = new Player("jessica", "password");
       p2 = new Player("jack", "Black");
       tableOwner = new Player("emily", "abc");
       table = new Table(tableOwner);
    	//f12CHF= new Money(12, "CHF"); 
        //f14CHF= new Money(14, "CHF"); 
        //f28USD= new Money(28, "USD"); 
    }
	
	public void testRequestJoinOnePlayerTableAccepts() {
		//Table table = new Table(player);
		//table.requestJoin(new Player("jack", "Black"));
		table.requestJoin(p1);
		
		assertEquals(table.getAllPlayers().size(), 1);
	}
	
	public void testRequestJoin2PlayersTableRejectsOne() {		
		p1.requestJoin(table);
		p2.requestJoin(table);
		
		assertEquals(table.getAllPlayers().size(), 1);
	}
	
	public void testRequestJoinAcceptAll() {
		
	}
}