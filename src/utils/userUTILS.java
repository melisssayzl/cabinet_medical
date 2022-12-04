package utils;
import java.util.List;

import modele.beans.User;
import models.dao.DAOUser;

public class  userUTILS {
	
	
	public static boolean checkuserpassword(String username,String password) 
	{
		boolean ok= false;
		
		List<List<Object>> list = DatabaseConfig.getSingleton()
				.executeQueryPS("SELECT * FROM user WHERE `username` = ? AND `password` = ? ", username,password);		
		if(list.size()==0) {
			
			System.out.println("no user with that name and password");
			ok=false;
		}else {
			System.out.println("azzul");
			ok=true;
		}
		
		
		return ok;
	}
	public static User checkuserpassword1(String username,String password) 
	{
		User user=new User();
		
		List<List<Object>> list = DatabaseConfig.getSingleton()
				.executeQueryPS("SELECT * FROM user WHERE `username` = ? AND `password` = ? ", username,password);		
		if(list.size()==0) {
			
			user=null;
			
		}else{
			
 		    user =  DAOUser.getByLine(list.get(0));
			
		}
		
		
		return user;
	}
	
	

}
