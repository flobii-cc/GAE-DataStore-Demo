package co.cc.gae.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DataStoreComAsync {

	void greetServer(AsyncCallback<Boolean> callback);

	void loginUser(String username, String password,
			AsyncCallback<String> callback);

	void readStorage(String uid, AsyncCallback<List<String>> callback);

	void registerUser(String username, String pwd,
			AsyncCallback<String> callback);

	void storeSentence(String sent, String uid, AsyncCallback<Void> callback);

}
