package com.mideas.rpg.v2.hud;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.mideas.rpg.v2.Interface;
import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.Sprites;
import com.mideas.rpg.v2.TTF2;
import com.mideas.rpg.v2.utils.Alert;
import com.mideas.rpg.v2.utils.Button;
import com.mideas.rpg.v2.utils.Draw;
import com.mideas.rpg.v2.utils.Input;

public class LoginScreen {

	private static boolean accountActive = true;
	private static boolean passwordActive;
	static Input account = new Input(TTF2.loginScreenAccount, 10);
	static Input password = new Input(TTF2.loginScreenPassword, 19);
	static Alert alert = new Alert("", Display.getWidth()/2-360*Mideas.getDisplayXFactor(), -20, 700, 130, 230, 38, Display.getHeight()+30, 20, "Ok");
	//private static boolean alertActive;
	private static boolean init;
	private static Button leaveButton = new Button(Display.getWidth()/2+773*Mideas.getDisplayXFactor(), Display.getHeight()/2+428*Mideas.getDisplayYFactor(), 185, 34, "Leave", 16) {
		@Override
		public void eventButtonClick() throws SQLException {
			Mideas.saveAllStats();
			System.exit(0);
		}
	};
	
	private static Button connectionButton = new Button(Display.getWidth()/2-113*Mideas.getDisplayXFactor(), Display.getHeight()/2+185*Mideas.getDisplayYFactor(), 210, 35, "Connection", 16) {
		@Override
		public void eventButtonClick() throws SQLException, NoSuchAlgorithmException {
			if(!alert.isActive()) {
				connectionEvent();
			}
		}
	};
	
	public static void draw() {
		if(!init) {
			updateSize();
			init = true;
		}
		Draw.drawQuadBG(Sprites.login_screen);
		TTF2.loginScreenAccount.drawString(Display.getWidth()/2-93*Mideas.getDisplayXFactor(), Display.getHeight()/2+12*Mideas.getDisplayYFactor(), account.getText(), Color.white);
		TTF2.loginScreenPassword.drawString(Display.getWidth()/2-93*Mideas.getDisplayXFactor(), Display.getHeight()/2+112*Mideas.getDisplayYFactor(), drawPassword(password.getText().length()), Color.white);
		if(System.currentTimeMillis()%1000 < 500) {
			if(accountActive) {
				TTF2.loginScreenTick.drawString(Display.getWidth()/2-99*Mideas.getDisplayXFactor()+account.getCursorShift(), Display.getHeight()/2+3*Mideas.getDisplayYFactor(), "|", Color.white);
			}
			else {
				TTF2.loginScreenTick.drawString(Display.getWidth()/2-99*Mideas.getDisplayXFactor()+TTF2.loginScreenPassword.getWidth(drawPassword(password.getText().length())), Display.getHeight()/2+103*Mideas.getDisplayYFactor(), "|", Color.white);
			}
		}
		leaveButton.draw();
		connectionButton.draw();
		alert.draw();
		//Draw.drawColorQuad(0, 0, 1920, 1080, Color.blue);
	}
	
	public static boolean mouseEvent() throws NoSuchAlgorithmException, SQLException {
		if(!Interface.getHasLoggedIn()) {
			alert.event();
			if(!alert.isActive()) {
				leaveButton.event();
				connectionButton.event();
				if((Mouse.getEventButton() == 0 || Mouse.getEventButton() == 1) && Mouse.getEventButtonState()) {
					if(Mideas.getHover() && Mideas.mouseX() >= Display.getWidth()/2-105*Mideas.getDisplayXFactor() && Mideas.mouseX() <= Display.getWidth()/2+105*Mideas.getDisplayXFactor() && Mideas.mouseY() >= Display.getHeight()/2+8*Mideas.getDisplayYFactor() && Mideas.mouseY() <= Display.getHeight()/2+43*Mideas.getDisplayYFactor()) {
						accountActive = true;
						passwordActive = false;
						Mideas.setHover(false);
					}
					else if(Mideas.getHover() && Mideas.mouseX() >= Display.getWidth()/2-105*Mideas.getDisplayXFactor() && Mideas.mouseX() <= Display.getWidth()/2+105*Mideas.getDisplayXFactor() && Mideas.mouseY() >= Display.getHeight()/2+108*Mideas.getDisplayYFactor() && Mideas.mouseY() <= Display.getHeight()/2+143*Mideas.getDisplayYFactor()) {
						accountActive = false;
						passwordActive = true;
						Mideas.setHover(false);
					}
				}
			}
		}
		return false;
	}
	
	public static void event() throws SQLException, NoSuchAlgorithmException {
		if(Keyboard.getEventKey() == Keyboard.KEY_RETURN || Keyboard.getEventKey() == 156) {
			if(alert.isActive()) {
				alert.setInactive();
			}
			else {
				connectionEvent();
			}
		}
		if(!alert.isActive()) {
			if(Keyboard.getEventKey() == Keyboard.KEY_TAB) {
				accountActive = !accountActive;
				passwordActive = !passwordActive;
			}
		}
		if(accountActive) {
			account.event();
		}
		else if(passwordActive) {
			password.event();
		}
	}
	
	private static String drawPassword(int length) {
		int i = 0;
		String pw = "";
		while(i < length) {
			pw+= "*";
			i++;
		}
		return pw;
	}
	
	public static Alert getAlert() {
		return alert;
	}
	
	public static Input getPassword() {
		return password;
	}
	
	static void connectionEvent() throws NoSuchAlgorithmException, SQLException {
		if(account.getText().equals("")) {
			alert.setText("Veuillez saisir votre nom de compte.");
			alert.setActive();
		}
		else if(password.getText().equals("")) {
			alert.setText("Veuillez saisir votre mot de passe.");
			alert.setActive();
		}
		else {
			if(LoginManager.checkLogin(account.getText(), password.getText())) {
				account.resetText();
				password.resetText();
				accountActive = true;
				passwordActive = false;
			}
		}
	}
	
	public static void updateSize() {
		leaveButton.setX(Display.getWidth()/2+753*Mideas.getDisplayXFactor());
		leaveButton.setY(Display.getHeight()/2+428*Mideas.getDisplayYFactor());
		leaveButton.setButtonWidth(185);
		leaveButton.setButtonHeight(34);
		connectionButton.setX(Display.getWidth()/2-105*Mideas.getDisplayXFactor());
		connectionButton.setY(Display.getHeight()/2+185*Mideas.getDisplayYFactor());
		connectionButton.setButtonWidth(210);
		connectionButton.setButtonHeight(38);
		alert.setX(-355*Mideas.getDisplayXFactor());
		alert.setY(-60);
	}
}
