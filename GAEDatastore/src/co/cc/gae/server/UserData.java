package co.cc.gae.server;

import java.util.List;
import javax.persistence.Id;

public class UserData {
	@Id String username;
	String pwd;
	List<String> text; 
}
