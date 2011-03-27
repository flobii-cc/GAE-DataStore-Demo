package co.cc.gae.client;


import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("gaedscom")
public interface DataStoreCom extends RemoteService {
	Boolean greetServer() throws IllegalArgumentException;
	String registerUser(String username, String pwd) throws IllegalArgumentException;
	String loginUser(String username, String password) throws IllegalArgumentException;
	void storeSentence(String sent, String uid) throws IllegalArgumentException;
	List<String> readStorage(String uid) throws IllegalArgumentException;
}