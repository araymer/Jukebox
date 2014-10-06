package Model;

import java.util.HashMap;

public class CardReader {
	private HashMap<String, JukeboxAccount> accountList;
	
	public CardReader() {
		accountList.put("Ali", new JukeboxAccount("Ali", "1111"));
		accountList.put("Chris", new JukeboxAccount("Chris", "2222"));
		accountList.put("River", new JukeboxAccount("River", "3333"));
		accountList.put("Ryan", new JukeboxAccount("Ryan", "4444"));
		
	
	}
	
	public JukeboxAccount getAccount(String ID) {
		return accountList.get(ID);
	}
	
}
