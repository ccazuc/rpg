package com.mideas.rpg.v2.hud;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.Arrays;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.mideas.rpg.v2.Interface;
import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.Sprites;
import com.mideas.rpg.v2.TTF2;
import com.mideas.rpg.v2.game.CharacterStuff;
import com.mideas.rpg.v2.game.item.Item;
import com.mideas.rpg.v2.game.item.ItemType;
import com.mideas.rpg.v2.game.item.potion.PotionManager;
import com.mideas.rpg.v2.game.item.stuff.BagManager;
import com.mideas.rpg.v2.game.item.stuff.StuffManager;
import com.mideas.rpg.v2.utils.Draw;

public class EndFightFrame {

	private static boolean endFightEvent;

	public static void draw() throws SQLException {
		if(Interface.getAdminPanelFrameStatus()) {
			Arrays.fill(AdminPanelFrame.getHover(), false);
		}
		if(Interface.getCharacterFrameStatus()) {
			Arrays.fill(CharacterFrame.getHoverCharacterFrame(), false);
		}
		if(Interface.getContainerFrameStatus()) {
			Arrays.fill(ContainerFrame.getContainerFrameSlotHover(), false);
		}
		if(Interface.getShopFrameStatus()) {
			Arrays.fill(ShopFrame.getShopHover(), false);
		}
		if(Interface.isSpellBookFrameActive()) {
			Arrays.fill(SpellBookFrame.getHoverBook(), false);
		}
		Draw.drawQuad(Sprites.alert, Display.getWidth()/2-Sprites.button_hover.getWidth()/2-105, Display.getHeight()/2-80);
		if(Mideas.mouseX() >= Display.getWidth()/2-130 && Mideas.mouseX() <= Display.getWidth()/2-3 && Mideas.mouseY() <= Display.getHeight()/2-18 && Mideas.mouseY() >= Display.getHeight()/2-37) {
			Draw.drawQuad(Sprites.button_hover, Display.getWidth()/2-Sprites.button_hover.getWidth()/2-70, Display.getHeight()/2-43);
		}
		else {
			Draw.drawQuad(Sprites.button, Display.getWidth()/2-Sprites.button_hover.getWidth()/2-70, Display.getHeight()/2-43);
		}
		if(Mideas.mouseX() >= Display.getWidth()/2+7 && Mideas.mouseX() <= Display.getWidth()/2+134 && Mideas.mouseY() <= Display.getHeight()/2-15 && Mideas.mouseY() >= Display.getHeight()/2-38) {
			Draw.drawQuad(Sprites.button_hover2, Display.getWidth()/2-Sprites.button_hover.getWidth()/2+70, Display.getHeight()/2-43);
		}
		else {
			Draw.drawQuad(Sprites.button2, Display.getWidth()/2-Sprites.button_hover.getWidth()/2+70, Display.getHeight()/2-43);
		}
		TTF2.buttonFont.drawStringShadow(Display.getWidth()/2-TTF2.buttonFont.getWidth("Retry")/2-69, Display.getHeight()/2-41, "Retry", Color.white, Color.black, 1, 1, 1);
		TTF2.buttonFont.drawStringShadow(Display.getWidth()/2-TTF2.buttonFont.getWidth("Quit")/2+69, Display.getHeight()/2-41, "Quit", Color.white, Color.black, 1, 1, 1);
		if(Mideas.joueur1().getStamina() <= 0) {
			TTF2.font4.drawStringShadow(Display.getWidth()/2-50, Display.getHeight()/2-66, "Player 2 won", Color.white, Color.black, 1, 1, 1);
		}
		else if(Mideas.joueur2().getStamina() <= 0) {
			TTF2.font4.drawStringShadow(Display.getWidth()/2-50, Display.getHeight()/2-66, "Player 1 won", Color.white, Color.black, 1, 1, 1);
			if(!endFightEvent) {
				doEndFightEvent();
				endFightEvent = true;
			}
		}
		
	}
	
	public static void mouseEvent() throws SQLException {
		if(Mouse.getEventButton() == 0) {
			if(!Mouse.getEventButtonState()) {
				if(Mideas.mouseX() >= Display.getWidth()/2+7 && Mideas.mouseX() <= Display.getWidth()/2+134 && Mideas.mouseY() <= Display.getHeight()/2-15 && Mideas.mouseY() >= Display.getHeight()/2-38) {
					System.exit(1);
					CharacterStuff.setBagItems();
					CharacterStuff.setEquippedBags();
					CharacterStuff.setEquippedItems();
					Mideas.setConfig();
					Mideas.setExp(Mideas.joueur2().getExpGained());
					Mideas.setGold(0);
				}
				else if(Mideas.mouseX() >= Display.getWidth()/2-130 && Mideas.mouseX() <= Display.getWidth()/2-3 && Mideas.mouseY() <= Display.getHeight()/2-18 && Mideas.mouseY() >= Display.getHeight()/2-37) {
					Mideas.setJoueur2(Mideas.getRandomClass(2));
					Mideas.joueur1().setStamina(Mideas.joueur1().getMaxStamina());
					Mideas.joueur1().setMana(Mideas.joueur1().getMaxMana());
					LogChat.setStatusText("");
					LogChat.setStatusText2("");
					endFightEvent = false;
				}
			}
		}
	}
	
	private static boolean dropRate(Item item) throws SQLException {
		int i = 0;
		while(i < Mideas.bag().getBag().length) {
			if(Mideas.bag().getBag(i) == null) {
				Mideas.bag().setBag(i, item);
				CharacterStuff.setBagItems();
				return true;
			}
			i++;
		}
		LogChat.setStatusText3("Votre inventaire est pleins");
		return false;
	}
	
	public static boolean dropItem(Item potion, int number) throws SQLException {
		if(potion.getItemType() == ItemType.ITEM || potion.getItemType() == ItemType.POTION) {
			if(checkBagItems(potion)) {
				int i = 0;
				while(i < Mideas.bag().getBag().length) {
					if(Mideas.bag().getBag(i) == null) {
						Mideas.bag().setBag(i, potion);
						Mideas.joueur1().setNumberItem(potion, 1);
						CharacterStuff.setBagItems();
						return true;
					}
					i++;
				}
			}
			else {
				int i = 0;
				while(i < Mideas.bag().getBag().length) {
					if(Mideas.bag().getBag(i) != null && Mideas.bag().getBag(i).equals(potion)) {
						Mideas.joueur1().setNumberItem(Mideas.bag().getBag(i), Mideas.joueur1().getNumberItem(Mideas.bag().getBag(i))+1);
						CharacterStuff.setBagItems();
						return true;
					}
					i++;
				}
			}
		}
		return false;
	}
	
	public static boolean checkBagItems(Item potion) {
		int i = 0;
		while(i < Mideas.bag().getBag().length) {
			if(Mideas.bag().getBag(i) != null && potion.getId() ==  Mideas.bag().getBag(i).getId()) {
				return false;
			}
			i++;
		}
		return true;
	}
	
	public static boolean bagItems(Item item) {
		int i = 0;
		while(i < Mideas.bag().getBag().length) {
			if(Mideas.bag().getBag(i) != null && item.equals(Mideas.bag().getBag(i))) {
				return false;
			}
			i++;
		}
		i = 0;
		while(i < Mideas.joueur1().getStuff().length) {
			if(Mideas.joueur1().getStuff(i) != null && item.equals(Mideas.joueur1().getStuff(i))) {
				return false;
			}
			i++;
		}
		return true;
	}
	
	private static void lootGuerrier() throws SQLException {
		float x = 0.05f;
		drop(x, StuffManager.getClone(1001));
		drop(x, StuffManager.getClone(2001));
		drop(x, StuffManager.getClone(3001));
		drop(x, StuffManager.getClone(4001));
		drop(x, StuffManager.getClone(5001));
		drop(x, StuffManager.getClone(6001));
		drop(x, StuffManager.getClone(7001));
		drop(x, StuffManager.getClone(8001));
		drop(x, StuffManager.getClone(9001));
		drop(x, StuffManager.getClone(10001));
		drop(x, StuffManager.getClone(13001));
		drop(x, StuffManager.getClone(13002));
		drop(x, StuffManager.getClone(14001));
		drop(x, StuffManager.getClone(14002));
		drop(.01f*x, StuffManager.getClone(15001));
		drop(.01f*x, StuffManager.getClone(16001));
		drop(.01f*x,StuffManager.getClone(17001));
		drop(.95f, PotionManager.getClone(1));
		drop(.95f, BagManager.getClone(4));
	}

	private static void lootHunter() throws SQLException {
		float x = 0.05f;
		drop(x, StuffManager.getClone(1201));
		drop(x, StuffManager.getClone(2001));
		drop(x, StuffManager.getClone(3201));
		drop(x, StuffManager.getClone(4001));
		drop(x, StuffManager.getClone(5201));
		drop(x, StuffManager.getClone(6201));
		drop(x, StuffManager.getClone(7201));
		drop(x, StuffManager.getClone(8201));
		drop(x, StuffManager.getClone(9201));
		drop(x, StuffManager.getClone(10201));
		drop(.01f*x,StuffManager.getClone(17001));
		drop(.95f, PotionManager.getClone(1));
	}
	
	private static void lootPaladin() throws SQLException {
		float x = 0.05f;
		drop(x, StuffManager.getClone(1501));
		drop(x, StuffManager.getClone(2001));
		drop(x, StuffManager.getClone(3501));
		drop(x, StuffManager.getClone(4001));
		drop(x, StuffManager.getClone(5501));
		drop(x, StuffManager.getClone(6501));
		drop(x, StuffManager.getClone(7501));
		drop(x, StuffManager.getClone(8501));
		drop(x, StuffManager.getClone(9501));
		drop(x, StuffManager.getClone(10501));
		drop(.95f, PotionManager.getClone(1));
	}

	private static void lootMage() throws SQLException {
		float x = 0.05f;
		drop(x, StuffManager.getClone(1301));
		drop(x, StuffManager.getClone(2001));
		drop(x, StuffManager.getClone(3301));
		drop(x, StuffManager.getClone(4001));
		drop(x, StuffManager.getClone(5301));
		drop(x, StuffManager.getClone(6301));
		drop(x, StuffManager.getClone(7301));
		drop(x, StuffManager.getClone(8301));
		drop(x, StuffManager.getClone(9301));
		drop(x, StuffManager.getClone(10301));
		drop(.95f, PotionManager.getClone(1));
	}
	
	private static void lootIllidan() {
		//drop(.03f, new WarglaiveOfAzzinoth());
	}
	
	public static boolean lootManager() throws SQLException {
		if(Mideas.joueur2().getClasse().equals("DeathKnight")) {
			lootGuerrier();
			return true;
		}
		else if(Mideas.joueur2().getClasse().equals("Guerrier")) {
			lootGuerrier();
			return true;
		}
		else if(Mideas.joueur2().getClasse().equals("Hunter")) {
			lootHunter();
			return true;
		}
		else if(Mideas.joueur2().getClasse().equals("Mage")) {
			lootMage();
			return true;
		}
		else if(Mideas.joueur2().getClasse().equals("Monk")) {
			lootGuerrier();
			return true;
		}
		else if(Mideas.joueur2().getClasse().equals("Paladin")) {
			lootPaladin();
			return true;
		}
		else if(Mideas.joueur2().getClasse().equals("Priest")) {
			lootGuerrier();
			return true;
		}
		else if(Mideas.joueur2().getClasse().equals("Rogue")) {
			lootGuerrier();
			return true;
		}
		else if(Mideas.joueur2().getClasse().equals("Shaman")) {
			lootGuerrier();
			return true;
		}
		else if(Mideas.joueur2().getClasse().equals("Warlock")) {
			lootGuerrier();
			return true;
		}
		else if(Mideas.joueur2().getClasse().equals("Illidan")) {
			lootIllidan();
			return true;
		}		
		return false;	
	}
	
	private static void drop(float x, Item item) throws SQLException {
		if(Math.random() <= x && item != null) {
			if(item.getItemType() == ItemType.POTION || item.getItemType() == ItemType.ITEM) {
				dropItem(item, 1);
			}
			else {
				dropRate(item);
			}
			SpellBarFrame.setBagChange(true);
			LogChat.setStatusText3("Vous avez obtenus "+item.getStuffName());
		}
	}
	
	public static boolean getEndFightEventState() {
		return endFightEvent;
	}
	
	public static void setEndFightEvent(boolean we) {
		endFightEvent = we;
	}
	
	public static void doEndFightEvent() throws SQLTimeoutException, SQLException {
		Mideas.setExp(Mideas.joueur2().getExpGained());
		Mideas.getLevel();
		Mideas.setGold(Mideas.joueur2().getGoldGained());
		lootManager();
	}
}

