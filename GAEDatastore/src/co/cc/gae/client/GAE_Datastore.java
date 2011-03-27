package co.cc.gae.client;

/*
 * Author: Kevin Haller
 * Google Appengine Datastore Demo
 * for free use
 * Blog: 	http://flobii-cc.blogspot.com
 * Website:	http://flobii.co.cc
 * @2011
 */

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class GAE_Datastore implements EntryPoint {

	private final DataStoreComAsync greetingService = GWT
			.create(DataStoreCom.class);

	PopupPanel p = new PopupPanel();
	static String uid ="";
	VerticalPanel rpanel = new VerticalPanel();
	VerticalPanel logpanel = new VerticalPanel();
	VerticalPanel regpanel = new VerticalPanel();
	HorizontalPanel hzpanel = new HorizontalPanel();

	
	public void onModuleLoad() {
	final TextBox tregun = new TextBox();
	final PasswordTextBox tregpwd = new PasswordTextBox();
	Anchor registerbtn = new Anchor("Register");
	registerbtn.setStyleName("anchor");
	rpanel.setSize(Window.getClientWidth()+"px",Window.getClientHeight()+"px");
	registerbtn.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			greetingService.registerUser(tregun.getText(),tregpwd.getText(), new AsyncCallback<String>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
				}

				@Override
				public void onSuccess(String result) {
					if((result.split(",")[0]).equals("true")){
						tregun.setText("");
						tregpwd.setText("");
					}
						Window.alert(result.split(",")[1]);
				}
			});
		}
	});
	
	final TextBox tloginun = new TextBox();
	final PasswordTextBox tloginpwd = new PasswordTextBox();
	Anchor loginbtn = new Anchor("Login");
	loginbtn.setStyleName("anchor");
	loginbtn.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			greetingService.loginUser(tloginun.getText(), tloginpwd.getText(),new AsyncCallback<String>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
				}

				@Override
				public void onSuccess(String result) {
					if(result.split(",")[0].equals("true")){
						uid = result.split(",")[2];
						loadPP(uid);
						tloginun.setText("");
						tloginpwd.setText("");
					}else{
						Window.alert(result.split(",")[1]);
					}
				}
			});
		}
	});
	rpanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
	rpanel.add(new HTML("<p1><font size=\"32\">G</font>oogle <font size=\"32\">A</font>ppengine <font size=\"32\">D</font>atastore <font size=\"32\">D</font>emo</p1>"));
	regpanel.setSpacing(5);
	regpanel.add(new HTML("<p2>Register</p2>"));
	regpanel.add(tregun);
	regpanel.add(tregpwd);
	regpanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
	regpanel.add(registerbtn);
	logpanel.setSpacing(5);
	logpanel.add(new HTML("<p2>Login</p2>"));
	logpanel.add(tloginun);
	logpanel.add(tloginpwd);
	logpanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
	logpanel.add(loginbtn);
	hzpanel.setSpacing(100);
	hzpanel.add(regpanel);
	hzpanel.add(logpanel);
	rpanel.add(hzpanel);
	RootPanel.get("rpanel").add(rpanel);
	}
	
	private void loadPP(String suid){
		p.clear();
		final PopupPanel pop = new PopupPanel();
		pop.add(new HTML("<p4>Hello,"+suid+"</p4>"));
		pop.setPopupPosition(200, 200);
		pop.show();
		VerticalPanel vp = new VerticalPanel();
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(20);
		VerticalPanel vpst = new VerticalPanel();
		final TextBox wrs = new TextBox();
		Anchor wrsbtn = new Anchor("Store");
		wrsbtn.setStyleName("anchor");
		vpst.setSpacing(5);
		vpst.add(new HTML("<p3>Store in the Datastore</p3>"));
		vpst.add(wrs);
		vpst.add(wrsbtn);
		wrsbtn.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				greetingService.storeSentence(wrs.getText(), uid, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess(Void result) {
						wrs.setText("");
					}
				});
			}
		});
		VerticalPanel vpre = new VerticalPanel();
		final TextArea rrs = new TextArea();
		rrs.setSize("300px", "150px");
		Anchor rrsbtn = new Anchor("Read");
		rrsbtn.setStyleName("anchor");
		vpre.setSpacing(5);
		vpre.add(new HTML("<p3>Read the Datastore</p3>"));
		vpre.add(rrs);
		vpre.add(rrsbtn);
		hp.add(vpst);
		hp.add(vpre);
		vp.add(hp);
		rrsbtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				greetingService.readStorage(uid, new AsyncCallback<List<String>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess(List<String> result) {
						String rs ="";
						for(int n =0;n<result.size();n++){
							rs = rs+result.get(n)+"\n";
						}
						rrs.setText(rs);
					}
				});
			}
		});
		Anchor logout = new Anchor("Logout");
		logout.setStyleName("logout");
		logout.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				p.hide();
				pop.hide();
				uid="";
			}
		});
		vp.add(logout);
		p.setWidget(vp);
		p.center();
	}
}
