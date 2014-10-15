package Model;

import java.util.HashMap;

public class JukeboxAccountCollection {
	private static HashMap<String, JukeboxAccount> accountList;
	private static JukeboxAccount jukeAcc;
	public JukeboxAccountCollection(){
		accountList = new HashMap<String, JukeboxAccount>();
		accountList.put("Ali", new JukeboxAccount("Ali", "1111"));
		accountList.put("Chris", new JukeboxAccount("Chris", "2222"));
		accountList.put("River", new JukeboxAccount("River", "3333"));
		accountList.put("Ryan", new JukeboxAccount("Ryan", "4444"));
	}
    public static boolean authenticate(String id, String password) {
        // hardcoded username and password
    	if(accountList.containsKey(id)){
	        if (accountList.get(id).getID().equals("Ali") && accountList.get(id).getPassword().equals("1111")) {
	        	jukeAcc = accountList.get(id);
	            return true;
	        }
	        if (accountList.get(id).getID().equals("Chris") && accountList.get(id).getPassword().equals("2222")) {
	        	jukeAcc = accountList.get(id);
	            return true;
	        }
	        if (accountList.get(id).getID().equals("River") && accountList.get(id).getPassword().equals("3333")) {
	        	jukeAcc = accountList.get(id);
	            return true;
	        }
	        if (accountList.get(id).getID().equals("Ryan") && accountList.get(id).getPassword().equals("4444")) {
	        	jukeAcc = accountList.get(id);
	            return true;
	        }
	        
        }
	        
        
        return false;
    }
    public static JukeboxAccount getAcc(){
    	return jukeAcc;
    	
    }
}
