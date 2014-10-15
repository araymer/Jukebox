/*CLASS NOT YET IMPLEMENTED
 * Authors: Aaron Raymer, Kyle Willson
 * Class: CardReader. Keeps track of a Hashmap of JukeboxAccount objects and returns current object.
 */

package Model;

import java.io.Serializable;
import java.util.HashMap;

public class CardReader implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6440473827352185173L;
	private HashMap<String, JukeboxAccount> accountList;
	
	public CardReader() {
		
		accountList = new HashMap<String, JukeboxAccount>();
		accountList.put("Ali", new JukeboxAccount("Ali", "1111"));
		accountList.put("Chris", new JukeboxAccount("Chris", "2222"));
		accountList.put("River", new JukeboxAccount("River", "3333"));
		accountList.put("Ryan", new JukeboxAccount("Ryan", "4444"));
		
	
	}
	//returns JukeboxAccount
	public JukeboxAccount getAccount(String ID) {
		return accountList.get(ID);
	}
	
	public HashMap<String, JukeboxAccount> returnMap() {
		return accountList;
	}
	
	
}
