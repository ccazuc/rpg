package com.mideas.rpg.v2.hud;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.FontManager;
import com.mideas.rpg.v2.connection.AuthServerConnectionRunnable;
import com.mideas.rpg.v2.files.logs.LogsMgr;
import com.mideas.rpg.v2.files.logs.LogsType;
import com.mideas.rpg.v2.render.Draw;
import com.mideas.rpg.v2.render.Sprites;
import com.mideas.rpg.v2.utils.Alert;
import com.mideas.rpg.v2.utils.Button;
import com.mideas.rpg.v2.utils.CheckBox;
import com.mideas.rpg.v2.utils.Color;
import com.mideas.rpg.v2.utils.Input;

public class LoginScreen {

	private static boolean shouldUpdateSize;
	private final static Input account = new Input(FontManager.get("ARIALN", 21), 50, false, Display.getWidth()/2-91*Mideas.getDisplayXFactor(), Display.getHeight()/2+12*Mideas.getDisplayYFactor(), 187*Mideas.getDisplayXFactor(), true, 6, 24);
	private final static Input password = new Input(FontManager.get("ARIALN", 16), 19, false, false);
	private static String passwordText = "";
	final static Alert alert = new Alert("", -355*Mideas.getDisplayXFactor(), -60*Mideas.getDisplayYFactor(), 700*Mideas.getDisplayXFactor(), 21, "OK");
	private final static StringBuilder passwordBuilder = new StringBuilder();
	static boolean rememberAccountName;
	private static String realmName = "";
	private static int realmNameWidth;
	private final static Button leaveButton = new Button(Display.getWidth()/2+753*Mideas.getDisplayXFactor(), Display.getHeight()/2+426*Mideas.getDisplayYFactor(), (short)185, (short)34, "Quit", 16, 2) {
		
		@Override
		public void onLeftClickUp() {
			Mideas.closeGame();
		}
	};
	private final static Button connectionButton = new Button(Display.getWidth()/2-95*Mideas.getDisplayXFactor(), Display.getHeight()/2+185*Mideas.getDisplayYFactor(), (short)197, (short)33, "Login", 20, 2) {
		
		@Override
		public void onLeftClickUp() {
			connectionEvent();
		}
	};
	private final static Button officialWebsiteButton = new Button(26*Mideas.getDisplayXFactor(), Display.getHeight()-222*Mideas.getDisplayYFactor(), (short)184, (short)33, "Official Site", 16, 2) {
		
		@Override
		public void onLeftClickUp() {
			Sys.openURL("https://github.com/ccazuc/RPG");
		}
	};
	private final static CheckBox rememberAccountNameCheckBox = new CheckBox(27*Mideas.getDisplayXFactor(), Display.getHeight()-167*Mideas.getDisplayYFactor(), 22*Mideas.getDisplayXFactor(), 18*Mideas.getDisplayYFactor()) {
		
		@Override
		public boolean get() {
			return rememberAccountName;
		}
		
		@Override
		public void set() {
			rememberAccountName = !rememberAccountName;
		}
	};
	private final static Button cancelConnectionAlertButton = new Button(0, 0, (short)0, (short)0, "Cancel", 20, 2) {
		
		@Override
		public void onLeftClickUp() {
			AuthServerConnectionRunnable.cancelAuthConnection();
		}
	};
	private final static Button okAlertButton = new Button(0, 0, (short)0, (short)0, "OK", 20, 2) {
		
		@Override
		public void onLeftClickUp() {
			alert.setInactive();
		}
	};

	private final static String noAccountName = "Veuillez saisir votre nom de compte.";
	private final static String noPassword = "Veuillez saisir votre mot de passe.";
	
	public static void draw() {
		updateSize();
		Draw.drawQuadBG(Sprites.login_screen);
		//System.out.println("Login_screen: width: " + Sprites.login_screen.getImageWidth());
		drawPassword();
		FontManager.get("FRIZQT", 17).drawStringShadow(Display.getWidth()-23*Mideas.getDisplayXFactor()-realmNameWidth, Display.getHeight()/2+325*Mideas.getDisplayYFactor(), realmName, Color.GREY, Color.BLACK, 2, 0, 0);
		rememberAccountNameCheckBox.draw();
		officialWebsiteButton.draw();
		account.draw();
		leaveButton.draw();
		connectionButton.draw();
		alert.draw();
	}
	
	public static boolean mouseEvent() {
		if(alert.event()) return true;
		if(!alert.isActive()) {
			if(leaveButton.event()) return true;
			if(connectionButton.event()) return true;
			if(rememberAccountNameCheckBox.event()) return true;
			if(officialWebsiteButton.event()) return true;
			if((Mouse.getEventButton() == 0 || Mouse.getEventButton() == 1) && Mouse.getEventButtonState()) {
				if(Mideas.getHover() && Mideas.mouseX() >= Display.getWidth()/2-105*Mideas.getDisplayXFactor() && Mideas.mouseX() <= Display.getWidth()/2+105*Mideas.getDisplayXFactor() && Mideas.mouseY() >= Display.getHeight()/2+8*Mideas.getDisplayYFactor() && Mideas.mouseY() <= Display.getHeight()/2+43*Mideas.getDisplayYFactor()) {
					account.setIsActive(true);
					Mideas.setHover(false);
				}
				else if(Mideas.getHover() && Mideas.mouseX() >= Display.getWidth()/2-105*Mideas.getDisplayXFactor() && Mideas.mouseX() <= Display.getWidth()/2+105*Mideas.getDisplayXFactor() && Mideas.mouseY() >= Display.getHeight()/2+108*Mideas.getDisplayYFactor() && Mideas.mouseY() <= Display.getHeight()/2+143*Mideas.getDisplayYFactor()) {
					password.setIsActive(true);
					Mideas.setHover(false);
				}
			}
		}
		return false;
	}
	
	public static void event() {
		if(Keyboard.getEventKey() == Keyboard.KEY_RETURN || Keyboard.getEventKey() == 156) {
			if(alert.isActive()) {
				alert.keyPressed();
			}
			else {
				connectionEvent();
			}
		}
		else if(!alert.isActive()) {
			if(Keyboard.getEventKey() == Keyboard.KEY_TAB) {
				if(password.isActive()) {
					account.setIsActive(true);
				}
				else {
					password.setIsActive(true);
				}
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
				Mideas.closeGame();
			}
		}
		if(account.isActive()) {
			if(account.event()) {
				rememberAccountName = false;
			}
		}
		else if(password.isActive()) {
			if(password.event()) {
				updatePasswordText(password.getText().length());
			}
		}
	}
	
	private static void drawPassword() {
		int i = 0;
		float x = Display.getWidth()/2-91*Mideas.getDisplayXFactor();
		while(i < passwordText.length()) {
			FontManager.get("ARIALN", 22).drawChar(x, Display.getHeight()/2+112*Mideas.getDisplayYFactor(), '*', Color.WHITE);
			x+= 8*Mideas.getDisplayXFactor();
			i++;
		}
		if(password.isActive()) {
			if(Mideas.getLoopTickTimer()%1000 < 500) {
				Draw.drawColorQuad(Display.getWidth()/2-92*Mideas.getDisplayXFactor()+8*Mideas.getDisplayXFactor()*password.getCursorPosition(), Display.getHeight()/2+112*Mideas.getDisplayYFactor(), 6*Mideas.getDisplayXFactor(), 24*Mideas.getDisplayYFactor(), Color.WHITE);
			}
		}
	}
	
	private static void updatePasswordText(int length) {
		int i = 0;
		passwordBuilder.setLength(0);
		while(i < length) {
			passwordBuilder.append('*');
			i++;
		}
		passwordText = passwordBuilder.toString();
	}
	
	public static void closeInput() {
		password.setIsActive(false);
		account.setIsActive(false);
	}
	
	static void connectionEvent() {
		if(account.getText().length() == 0) {
			alert.setText(noAccountName);
			alert.setActive();
			LoginScreen.setAlertButtonOk();
		}
		else if(password.getText().length() == 0) {
			alert.setText(noPassword);
			alert.setActive();
			LoginScreen.setAlertButtonOk();
		}
		else {
			passwordText = "";
			alert.setText("Connection...");
			alert.setActive();
			alert.setButton(cancelConnectionAlertButton);
			AuthServerConnectionRunnable.connectToAuthServer(account.getText(), password.getText());
			password.resetText();
		}
	}
	
	public static void loginSuccess() {
		if(!rememberAccountName) {
			account.resetText();
		}
		alert.setInactive();
	}
	
	public static void resetMenuState() {
		if(!rememberAccountName) {
			account.setIsActive(true);
		}
		else {
			password.setIsActive(true);
		}
	}
	
	public static void updateSize() {
		if(!shouldUpdateSize) {
			return;
		}
		leaveButton.update(Display.getWidth()/2+753*Mideas.getDisplayXFactor(), Display.getHeight()/2+426*Mideas.getDisplayYFactor());
		connectionButton.update(Display.getWidth()/2-95*Mideas.getDisplayXFactor(), Display.getHeight()/2+185*Mideas.getDisplayYFactor());
		alert.update(Display.getWidth()/2-720*Mideas.getDisplayXFactor()/2, Display.getHeight()/2-60*Mideas.getDisplayYFactor(), 720*Mideas.getDisplayXFactor());
		account.update(Display.getWidth()/2-91*Mideas.getDisplayXFactor(), Display.getHeight()/2+12*Mideas.getDisplayYFactor(), 200*Mideas.getDisplayXFactor());
		officialWebsiteButton.update(26*Mideas.getDisplayXFactor(), Display.getHeight()-222*Mideas.getDisplayYFactor());
		rememberAccountNameCheckBox.update(27*Mideas.getDisplayXFactor(), Display.getHeight()-167*Mideas.getDisplayYFactor(), 22*Mideas.getDisplayXFactor(), 18*Mideas.getDisplayYFactor());
		shouldUpdateSize = false;
	}
	
	public static void shouldUpdate() {
		shouldUpdateSize = true;
	}
	
	public static Alert getAlert() {
		return alert;
	}
	
	public static boolean getRememberAccountName() {
		return rememberAccountName;
	}
	
	public static void setRememberAccountName(boolean we) {
		rememberAccountName = we;
	}
	
	public static String getAccountName() {
		return account.getText();
	}
	
	public static void setAccountName(String name) {
		account.setText(name);
		if(name.length() != 0) {
			password.setIsActive(true);
		}
	}
	
	public static void setRealmName(String name) {
		realmName = name;
		realmNameWidth = FontManager.get("FRIZQT", 17).getWidth(realmName);
	}
	
	public static void setAlertButtonOk() {
		alert.setButton(okAlertButton);
	}
	
	public static String getRealmName() {
		return realmName;
	}
	
	public static void resetPassword() {
		password.resetText();
		passwordText = "";
	}
}
