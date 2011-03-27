package co.cc.gae.server;

import java.util.ArrayList;
import java.util.List;
import co.cc.gae.client.DataStoreCom;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;


public class DataStoreComImpl extends RemoteServiceServlet implements
DataStoreCom {

@Override
public Boolean greetServer() throws IllegalArgumentException {
return true;
}

@Override
public String registerUser(String username, String pwd) throws IllegalArgumentException {
Objectify ofy = ObjectifyService.begin();
ObjectifyService.register(UserData.class);
if(ofy.find(UserData.class,username) == null){
	UserData ud = new UserData();
	ud.username = username ;
	ud.pwd = pwd;
	ud.text = new ArrayList<String>();
	ud.text.add(0,"....");
	ofy.put(ud);
	return "true, Registration completed!";
}else{
	return "false, User already exist!";
}
}

@Override
public String loginUser(String username, String password) throws IllegalArgumentException {
Objectify ofy = ObjectifyService.begin();
ObjectifyService.register(UserData.class);
if(ofy.find(UserData.class,username) != null){
if(ofy.find(UserData.class,username).pwd.equals(password)){
	return"true, User logged in!,"+username;
}else{
	return "false, False password!";
}
}else{
return "false,User doesn't exist !";
}
}

@Override
public void storeSentence(String sent, String uid) throws IllegalArgumentException {
Objectify ofy = ObjectifyService.begin();
ObjectifyService.register(UserData.class);
UserData ud = ofy.find(UserData.class,uid);
ud.text.add(sent);
ofy.put(ud);
}

@Override
public List<String> readStorage(String uid) throws IllegalArgumentException {
Objectify ofy = ObjectifyService.begin();
ObjectifyService.register(UserData.class);
if(ofy.find(UserData.class,uid) != null){
return ofy.find(UserData.class,uid).text;
}else{
	return null;
}
}




}
