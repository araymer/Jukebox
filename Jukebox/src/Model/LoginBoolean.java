package Model;




public class LoginBoolean {
	private JukeboxAccountCollection jukeAccs;
    public boolean authenticate(String id, String password) {
        // hardcoded username and password
    	if(jukeAccs.containsKey(id)){
	        if (jukeAccs.get(id).getID.equals("Ali") && jukeAccs.get(id).getPassword.equals("1111")) {
	            return true;
	        }
	        
        }
	        
        
        return false;
    }
}