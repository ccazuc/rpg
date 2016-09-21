package com.mideas.rpg.v2.game.item.shop;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.mideas.rpg.v2.Interface;
import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.Sprites;
import com.mideas.rpg.v2.TTF2;
import com.mideas.rpg.v2.game.CharacterStuff;
import com.mideas.rpg.v2.game.ClassType;
import com.mideas.rpg.v2.game.IconsManager;
import com.mideas.rpg.v2.game.Shop;
import com.mideas.rpg.v2.game.bag.Bag;
import com.mideas.rpg.v2.game.bag.BagManager;
import com.mideas.rpg.v2.game.item.Item;
import com.mideas.rpg.v2.game.item.ItemType;
import com.mideas.rpg.v2.game.item.gem.GemManager;
import com.mideas.rpg.v2.game.item.potion.Potion;
import com.mideas.rpg.v2.game.item.potion.PotionManager;
import com.mideas.rpg.v2.game.item.stuff.Stuff;
import com.mideas.rpg.v2.game.item.stuff.StuffManager;
import com.mideas.rpg.v2.game.item.weapon.WeaponManager;
import com.mideas.rpg.v2.hud.ContainerFrame;
import com.mideas.rpg.v2.hud.DragManager;
import com.mideas.rpg.v2.hud.LogChat;
import com.mideas.rpg.v2.jdo.JDOStatement;
import com.mideas.rpg.v2.utils.Draw;

public class ShopManager {
	
	private static boolean[] slot_hover = new boolean[12];
	private static boolean left_arrow;
	private static boolean right_arrow;
	private static boolean hover_button;
	private static int page;
	private static int numberPage;
	private static Color bgColor = new Color(0, 0, 0, .6f); 
	private static Color borderColor = Color.decode("#494D4B");

	private static ArrayList<Shop> shopList = new ArrayList<Shop>();
	
	public static void loadStuffs() throws SQLException {
		JDOStatement statement = Mideas.getJDO().prepare("SELECT id, class, price FROM shop");
		statement.execute();
		double i = 0;
		while(statement.fetch()) {
			int id = statement.getInt();
			short classeTemp = statement.getShort();
			ClassType[] classeType = StuffManager.getClasses(classeTemp);
			int sellPrice = statement.getInt();
			shopList.add(new Shop(id, classeType, sellPrice));
			i++;
		}
		numberPage = (int)Math.ceil(i/10);
	}
	
	public static Item getItem(int id) {
		int i = 0;
		while(i < StuffManager.getStuffList().size()) {
			if(StuffManager.getStuffList().get(i).getId() == id) {
				return StuffManager.getStuffList().get(i);
			}
			i++;
		}
		i = 0;
		while(i < PotionManager.getPotionList().size()) {
			if(PotionManager.getPotionList().get(i).getId() == id) {
				return PotionManager.getPotionList().get(i);
			}
			i++;
		}
		i = 0;
		while(i < BagManager.getContainerList().size()) {
			if(BagManager.getContainerList().get(i).getId() == id) {
				return BagManager.getContainerList().get(i);
			}
			i++;
		}
		i = 0;
		while(i < WeaponManager.getWeaponList().size()) {
			if(WeaponManager.getWeaponList().get(i).getId() == id) {
				return WeaponManager.getWeaponList().get(i);
			}
			i++;
		}
		i = 0;
		while(i < GemManager.getGemList().size()) {
			if(GemManager.getGemList().get(i).getId() == id) {
				return GemManager.getGemList().get(i);
			}
			i++;
		}
		return null;
	}
	
	public static void draw() {
		int xLeft = -279;
		int xRight = -114;
		int y = -275;
		int yShift = 52;
		Draw.drawQuad(Sprites.shop_frame, Display.getWidth()/2-300, Display.getHeight()/2-350);
		int i = 0;
		int j = 0;
		int x = xLeft;
		int z = 0;
		while(i < 10) {
			drawShopItem(i, x, y+j*yShift);
			shopHover(i, x+z, y+j*yShift, x, y+j*yShift);
			drawGoldCoin(i, x, y+j*yShift);
			i++;
			j++;
			if(i%5 == 0) {
				j = 0;
			}
			if(i == 5) {
				x = xRight;
				z = 165;
			}
		}
		calcCoin(Mideas.joueur1().getGold(), xRight, y+250);
		if(hover_button) {
			Draw.drawQuad(Sprites.close_shop_hover, Display.getWidth()/2+27, Display.getHeight()/2-337);
		}
		if(page != 0) {
			Draw.drawQuad(Sprites.left_colored_arrow, Display.getWidth()/2+xLeft+3, Display.getHeight()/2+y+268);
		}
		if(page != 2) {
			Draw.drawQuad(Sprites.right_colored_arrow, Display.getWidth()/2+xRight+125, Display.getHeight()/2+y+268);
		}
		if(right_arrow && page != 2) {
			Draw.drawQuad(Sprites.right_arrow_hover, Display.getWidth()/2+xRight+125, Display.getHeight()/2+y+268);
		}
		if(left_arrow && page != 0) {
			Draw.drawQuad(Sprites.left_arrow_hover, Display.getWidth()/2+xLeft+3, Display.getHeight()/2+y+268);
		}
		if(page == 2) {
			Draw.drawQuad(Sprites.right_uncolored_arrow, Display.getWidth()/2+xRight+125, Display.getHeight()/2+y+268);
		}
	}
	
	public static boolean mouseEvent() throws SQLException, NoSuchAlgorithmException {
		if(isHoverShopFrame()) {
			Arrays.fill(slot_hover, false);
		}
		right_arrow = false;
		left_arrow = false;
		hover_button = false;
		int xLeft = -279;
		int xRight = -114;
		int y = -275;
		if(Mideas.getHover() && Mideas.mouseX() >= Display.getWidth()/2+xRight+126 && Mideas.mouseX() <= Display.getWidth()/2+xRight+151 && Mideas.mouseY() >= Display.getHeight()/2+y+266 && Mideas.mouseY() <= Display.getHeight()/2+y+292) {
			right_arrow = true;
			Mideas.setHover(false);
		}
		else if(Mideas.getHover() && Mideas.mouseX() >= Display.getWidth()/2+xRight-161 && Mideas.mouseX() <= Display.getWidth()/2+xRight-136 && Mideas.mouseY() >= Display.getHeight()/2+y+266 && Mideas.mouseY() <= Display.getHeight()/2+y+292) {
			left_arrow = true;
			Mideas.setHover(false);
		}
		if(Mideas.getHover() && Mideas.mouseX() >= Display.getWidth()/2+27 && Mideas.mouseX() <= Display.getWidth()/2+46 && Mideas.mouseY() >= Display.getHeight()/2-337 && Mideas.mouseY() <= Display.getHeight()/2-319) {
			hover_button = true;
			Mideas.setHover(false);
		}
		if(Mouse.getEventButtonState()) {
			if(Mideas.getHover() && Mideas.mouseX() >= Display.getWidth()/2+27 && Mideas.mouseX() <= Display.getWidth()/2+46 && Mideas.mouseY() >= Display.getHeight()/2-337 && Mideas.mouseY() <= Display.getHeight()/2-319) {
				Interface.closeShopFrame();
				Mideas.setHover(false);
				return true;
			}
			else if(right_arrow && page < numberPage-1) {
				page++;
				return true;
			}
			else if(left_arrow && page > 0) {
				page--;
				return true;
			}	
		}
		isSlotHover(xLeft, y, 0, 41, 0);
		isSlotHover(xLeft, y, 52, 93, 1);
		isSlotHover(xLeft, y, 104, 145, 2);
		isSlotHover(xLeft, y, 156, 197, 3);
		isSlotHover(xLeft, y, 208, 249, 4);
		isSlotHover(xRight, y, 0, 41, 5);
		isSlotHover(xRight, y, 52, 93, 6);
		isSlotHover(xRight, y, 104, 145, 7);
		isSlotHover(xRight, y, 156, 197, 8);
		isSlotHover(xRight, y, 208, 249, 9);
		if(Mouse.getEventButton() == 1) {
			if(!Mouse.getEventButtonState()) {
				if(DragManager.isHoverBagFrame()) {
					int i = 0;
					while(i < Mideas.bag().getBag().length) {
						if(clickSellItem(i)) {
							CharacterStuff.setBagItems();
							return true;
						}
						i++;
					}
				}
			}
		}
		int i = 0;
		while(i < 10 && i+10*page < shopList.size()) {
			if(buyItems(slot_hover[i], shopList.get(i+10*page))) {
				return true;
			}
			i++;
		}
		return false;
	}
	
	public static boolean buyItems(boolean slot_hover, Shop item) throws SQLException {
		if(slot_hover && item != null) {
			if(Mideas.getCurrentGold() >= item.getSellPrice()) {
				checkItem(item);
			}
			else {
				LogChat.setStatusText3("Vous n'avez pas assez d'argent");
			}
			return true;
		}
		return false;
	}
	
	private static boolean clickSellItem(int i) throws SQLTimeoutException, SQLException, NoSuchAlgorithmException {
		if(sellItem(Mideas.bag().getBag(i), ContainerFrame.getContainerFrameSlotHover(i), DragManager.getClickBag(i))) {
			Mideas.bag().setBag(i, null);
			return true;
		}
		return false;
	}
	
	private static boolean sellItem(Item item, boolean hover, boolean click_hover) throws SQLTimeoutException, SQLException, NoSuchAlgorithmException {
		if(item != null && hover && click_hover && Interface.getShopFrameStatus()) {
			if(item.getItemType() == ItemType.ITEM || item.getItemType() == ItemType.POTION) {
				LogChat.setStatusText3("Vous avez vendu "+Mideas.joueur1().getNumberItem(item)+" "+item.getStuffName()+" pour "+item.getSellPrice()*Mideas.joueur1().getNumberItem(item));
				Mideas.setGold(Mideas.getGold()+item.getSellPrice()*Mideas.joueur1().getNumberItem(item));
				Mideas.joueur1().setNumberItem(item, 0);
			}
			else {
				Mideas.setGold(Mideas.getGold()+item.getSellPrice());
				LogChat.setStatusText3("Vous avez vendu "+item.getStuffName()+" pour "+item.getSellPrice());
			}
			DragManager.mouseEvent();
			return true;
		}
		return false;
	}
	private static boolean checkItem(Shop item) throws SQLException {
		int i = 0;
		if(StuffManager.exists(item.getId()) || WeaponManager.exists(item.getId())) {
			while(i < Mideas.bag().getBag().length) {
				if(Mideas.bag().getBag(i) == null) {
					Mideas.bag().setBag(i, StuffManager.getClone(item.getId()));
					LogChat.setStatusText3("Vous avez bien achet� "+StuffManager.getStuff(item.getId()).getStuffName());
					Mideas.setGold(Mideas.getGold()-item.getSellPrice());
					CharacterStuff.setBagItems();
					return true;
				}
				i++;
			}
		}
		else if(PotionManager.exists(item.getId())) {
			if(checkBagItem(item)) {
				while(i < Mideas.bag().getBag().length) {
					if(Mideas.bag().getBag(i) != null && Mideas.bag().getBag(i).getId() == item.getId()) {
						Mideas.joueur1().setNumberItem(Mideas.bag().getBag(i), Mideas.joueur1().getNumberItem(Mideas.bag().getBag(i))+1);
						LogChat.setStatusText3("Vous avez bien achet� "+PotionManager.getPotion(item.getId()).getStuffName());
						Mideas.setGold(Mideas.getGold()-item.getSellPrice());
						CharacterStuff.setBagItems();
						return true;
					}
					i++;
				}
			}
			else {
				while(i < Mideas.bag().getBag().length) {
					if(Mideas.bag().getBag(i) == null) {
						Potion temp = PotionManager.getClone(item.getId());
						Mideas.bag().setBag(i, temp);
						Mideas.joueur1().setNumberItem(temp, 1);
						LogChat.setStatusText3("Vous avez bien achet� "+PotionManager.getPotion(item.getId()).getStuffName());
						Mideas.setGold(Mideas.getGold()-item.getSellPrice());
						CharacterStuff.setBagItems();
						return true;
					}
					i++;
				}
			}
		}
		else if(GemManager.exists(item.getId())) {
			while(i < Mideas.bag().getBag().length) {
				if(Mideas.bag().getBag(i) == null) {
					Mideas.bag().setBag(i, GemManager.getClone(item.getId()));
					LogChat.setStatusText3("Vous avez bien achet� "+GemManager.getGem(item.getId()).getStuffName());
					Mideas.setGold(Mideas.getGold()-item.getSellPrice());
					CharacterStuff.setBagItems();
					return true;
				}
				i++;
			}
		}
		LogChat.setStatusText3("Votre inventaire est pleins");
		return false;
	}
	
	private static boolean checkBagItem(Shop item) {
		int i = 0;
		while(i < Mideas.bag().getBag().length) {
			if(Mideas.bag().getBag(i) != null && Mideas.bag().getBag(i).getId() == item.getId()) {
				return true;
			}
			i++;
		}
		return false;
	}

	private static void drawShopItem(int i, int x, int y) {
		if(i+10*page < shopList.size()) {
			Draw.drawQuad(IconsManager.getSprite35(getItem(shopList.get(i+10*page).getId()).getSpriteId()), Display.getWidth()/2+x+3, Display.getHeight()/2+y+3);
			Draw.drawQuad(Sprites.shop_border, Display.getWidth()/2+x-1, Display.getHeight()/2+y);
		}
	}
	
	private static void shopHover(int i, int x_item, int y_item, int x_hover, int y_hover) {
		if(slot_hover[i]) {
			if(i+10*page < shopList.size()) {
				int shift = 40;
				Item item = getItem(shopList.get(i+10*page).getId());
				if(item.getItemType() == ItemType.STUFF) {
					if(i < 5) {
						drawLeftStuff(i, x_item, y_item);
					}
					else {
						drawRightStuff(i, x_item, y_item);
					}
				}
				else if(item.getItemType() == ItemType.POTION) {
					shift = 25;
					Draw.drawColorQuad(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item, 285, 40+TTF2.statsName.getLineHeight()*2, bgColor);
					Draw.drawColorQuadBorder(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item, 287, 40+TTF2.statsName.getLineHeight()*2, borderColor);
					x_item+= 10;
					TTF2.itemName.drawStringShadow(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item, item.getStuffName(), ContainerFrame.getItemNameColor(item), Color.black, 1, 1, 1);
					if(((Potion)item).getPotionHeal() > 0) {
						TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item+shift, "Restores "+((Potion)item).getPotionHeal()+" Hp", Color.green, Color.black, 1, 1, 1);
						shift+= 20;
					}
					if(((Potion)item).getPotionMana() > 0) {
						TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item+shift, "Restores "+((Potion)item).getPotionMana()+" Mana", Color.green, Color.black, 1, 1, 1);
						shift+= 20;
					}
					if(Mideas.getLevel() >= ((Potion)item).getLevel()) {
						TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item+shift, "Level "+((Potion)item).getLevel()+" required", Color.white, Color.black, 1, 1, 1);
					}
					else {
						TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item+shift, "Level "+((Potion)item).getLevel()+" required", Color.red, Color.black, 1, 1, 1);
					}
				}
				else if(item.getItemType() == ItemType.BAG) {
					Draw.drawColorQuad(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item, 285, 40+TTF2.statsName.getLineHeight(), bgColor);
					Draw.drawColorQuadBorder(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item, 287, 40+TTF2.statsName.getLineHeight(), borderColor);
					TTF2.itemName.drawStringShadow(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item, item.getStuffName(), ContainerFrame.getItemNameColor(item), Color.black, 1, 1, 1);
					TTF2.itemName.drawStringShadow(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item+20, "Container "+((Bag)item).getSize()+" slots", Color.white, Color.black, 1, 1, 1);
				}
			}
			Draw.drawQuad(Sprites.shop_hover, Display.getWidth()/2+x_hover, Display.getHeight()/2+y_hover);
		}
	}
	
	private static void drawLeftStuff(int i, int x_item, int y_item) {
		Color temp = null;
		Item item = getItem(shopList.get(i+10*page).getId());
		int xShift = 280;
		int shift = 75;
		String classe = "";
		if(((Stuff)item).getClassType().length < 10) {
			classe = "Classes: ";
			int k = 0;
			while(k < ((Stuff)item).getClassType().length) {
				if(k == ((Stuff)item).getClassType().length-1) {
					classe+= ((Stuff)item).convClassTypeToString(k);
				}
				else {
					classe+= ((Stuff)item).convClassTypeToString(k)+", ";
				}
				k++;
			}
		}
		if(TTF2.itemName.getWidth(item.getStuffName()) > 285 || TTF2.statsName.getWidth(classe) > 285) {
			xShift = Math.max(TTF2.itemName.getWidth(item.getStuffName()), TTF2.statsName.getWidth(classe))+15;
		}
		Draw.drawColorQuad(Display.getWidth()/2+x_item-15, Display.getHeight()/2+30+y_item, -5-xShift, 75+TTF2.statsName.getLineHeight()*ContainerFrame.getNumberStats((Stuff)item), bgColor);
		Draw.drawColorQuadBorder(Display.getWidth()/2+x_item-14, Display.getHeight()/2+30+y_item, -7-xShift, 75+TTF2.statsName.getLineHeight()*ContainerFrame.getNumberStats((Stuff)item), borderColor);
		TTF2.itemName.drawStringShadow(Display.getWidth()/2+x_item-10-xShift, Display.getHeight()/2+y_item+33, item.getStuffName(), ContainerFrame.getItemNameColor(item), Color.black, 1, 1, 1);
		TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item-10-xShift, Display.getHeight()/2+y_item+55, ((Stuff)item).convStuffTypeToString(), Color.white, Color.black, 1, 1, 1);
		if(DragManager.canWear((Stuff)item)) {
			temp = Color.white;
		}
		else {
			temp = Color.red;
		}
		TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item-20-TTF2.font4.getWidth(((Stuff)item).convWearToString()), Display.getHeight()/2+y_item+55, ((Stuff)item).convWearToString(), temp, Color.black, 1, 1, 1);
		if(((Stuff)item).getArmor() > 0) {
			TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item-10-xShift, Display.getHeight()/2+y_item+shift, "Armor : "+((Stuff)item).getArmor(), Color.white, Color.black, 1, 1, 1);
			shift+= 20;
		}
		if(((Stuff)item).getStrength() > 0) {
			TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item-10-xShift, Display.getHeight()/2+y_item+shift, "+ "+((Stuff)item).getStrength()+" Strengh", Color.white, Color.black, 1, 1, 1);
			shift+= 20;
		}
		if(((Stuff)item).getMana() > 0) {
			TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item-10-xShift, Display.getHeight()/2+y_item+shift, "+ "+((Stuff)item).getMana()+" Mana", Color.white, Color.black, 1, 1, 1);
			shift+= 20;
		}
		if(((Stuff)item).getStamina() > 0) {
			TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item-10-xShift, Display.getHeight()/2+y_item+shift, "+ "+((Stuff)item).getStamina()+" Stamina", Color.white, Color.black, 1, 1, 1);
			shift+= 20;
		}
		if(((Stuff)item).getCritical() > 0) {
			TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item-10-xShift, Display.getHeight()/2+y_item+shift, "+ "+((Stuff)item).getCritical()+" Critical", Color.white, Color.black, 1, 1, 1);
			shift+= 20;
		}
		if(((Stuff)item).canEquipTo(DragManager.convClassType())) {
			temp = Color.white;
		}
		else {
			temp = Color.red;
		}
		if(!classe.equals("")) {
			TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item-10-xShift, Display.getHeight()/2+y_item+shift, classe, temp, Color.black, 1, 1, 1);
			shift+= 20;
		}
		if(Mideas.getLevel() >= ((Stuff)item).getLevel()) {
			TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item-10-xShift, Display.getHeight()/2+y_item+shift, "Level "+((Stuff)item).getLevel()+" required", Color.white, Color.black, 1, 1, 1);
		}
		else {
			TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item-10-xShift, Display.getHeight()/2+y_item+shift, "Level "+((Stuff)item).getLevel()+" required", Color.red, Color.black, 1, 1, 1);
		}
	}
	
	private static void drawRightStuff(int i, int x_item, int y_item) {
		Color temp = null;
		Item item = getItem(shopList.get(i+10*page).getId());
		int xShift = 280;
		int shift = 75;
		String classe = "";
		if(((Stuff)item).getClassType().length < 10) {
			classe = "Classes: ";
			int k = 0;
			while(k < ((Stuff)item).getClassType().length) {
				if(k == ((Stuff)item).getClassType().length-1) {
					classe+= ((Stuff)item).convClassTypeToString(k);
				}
				else {
					classe+= ((Stuff)item).convClassTypeToString(k)+", ";
				}
				k++;
			}
		}
		if(TTF2.itemName.getWidth(item.getStuffName()) > 285 || TTF2.statsName.getWidth(classe) > 285) {
			xShift = Math.max(TTF2.itemName.getWidth(item.getStuffName()), TTF2.statsName.getWidth(classe))+15;
		}
		Draw.drawColorQuad(Display.getWidth()/2+x_item, Display.getHeight()/2+30+y_item, xShift, 75+TTF2.statsName.getLineHeight()*ContainerFrame.getNumberStats((Stuff)item), bgColor);
		Draw.drawColorQuadBorder(Display.getWidth()/2+x_item, Display.getHeight()/2+30+y_item, xShift, 75+TTF2.statsName.getLineHeight()*ContainerFrame.getNumberStats((Stuff)item), borderColor);
		x_item+= 10;
		TTF2.itemName.drawStringShadow(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item+33, item.getStuffName(), ContainerFrame.getItemNameColor(item), Color.black, 1, 1, 1);
		TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item+55, ((Stuff)item).convStuffTypeToString(), Color.white, Color.black, 1, 1, 1);
		if(DragManager.canWear((Stuff)item)) {
			temp = Color.white;
		}
		else {
			temp = Color.red;
		}
		TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item+xShift-TTF2.statsName.getWidth(((Stuff)item).convWearToString())-15, Display.getHeight()/2+y_item+55, ((Stuff)item).convWearToString(), temp, Color.black, 1, 1, 1);
		if(((Stuff)item).getArmor() > 0) {
			TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item+shift, "Armor : "+((Stuff)item).getArmor(), Color.white, Color.black, 1, 1, 1);
			shift+= 20;
		}
		if(((Stuff)item).getStrength() > 0) {
			TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item+shift, "+ "+((Stuff)item).getStrength()+" Strengh", Color.white, Color.black, 1, 1, 1);
			shift+= 20;
		}
		if(((Stuff)item).getMana() > 0) {
			TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item+shift, "+ "+((Stuff)item).getMana()+" Mana", Color.white, Color.black, 1, 1, 1);
			shift+= 20;
		}
		if(((Stuff)item).getStamina() > 0) {
			TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item+shift, "+ "+((Stuff)item).getStamina()+" Stamina", Color.white, Color.black, 1, 1, 1);
			shift+= 20;
		}
		if(((Stuff)item).getCritical() > 0) {
			TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item+shift, "+ "+((Stuff)item).getCritical()+" Critical", Color.white, Color.black, 1, 1, 1);
			shift+= 20;
		}
		if(((Stuff)item).canEquipTo(DragManager.convClassType())) {
			temp = Color.white;
		}
		else {
			temp = Color.red;
		}
		if(!classe.equals("")) {
			TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item+shift, classe, temp, Color.black, 1, 1, 1);
			shift+= 20;
		}
		if(Mideas.getLevel() >= ((Stuff)item).getLevel()) {
			TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item+shift, "Level "+((Stuff)item).getLevel()+" required", Color.white, Color.black, 1, 1, 1);
		}
		else {
			TTF2.statsName.drawStringShadow(Display.getWidth()/2+x_item, Display.getHeight()/2+y_item+shift, "Level "+((Stuff)item).getLevel()+" required", Color.red, Color.black, 1, 1, 1);
		}
	}
	
	public static void isSlotHover(int x, int y, int i, int j, int k) {
		if(Mideas.getHover() && Mideas.mouseX() >= Display.getWidth()/2+x && Mideas.mouseX() <= Display.getWidth()/2+x+42 && Mideas.mouseY() >= Display.getHeight()/2+y+i && Mideas.mouseY() <= Display.getHeight()/2+y+j) {
			slot_hover[k] = true;
			Mideas.setHover(false);
		}
	}

	private static void drawGoldCoin(int i, int x, int y) {
		if(i+10*page < shopList.size()) {
			calcCoin(shopList.get(i+10*page).getSellPrice(), x+49, y+35);
			Draw.drawQuad(Sprites.cursor, -100, -100);
		}
	}
	
	public static boolean calcCoin(int cost, int x, int y) {
		if(Mideas.calcGoldCoinCost(cost) > 0 && Mideas.calcSilverCoinCost(cost) > 0 && cost-Mideas.calcGoldCoinCost(cost)*10000-Mideas.calcSilverCoinCost(cost)*100 > 0) {
			TTF2.coin.drawStringShadow(Display.getWidth()/2+x, Display.getHeight()/2+y, String.valueOf(Mideas.calcGoldCoinCost(cost)), Color.white, Color.black, 1, 1, 1);
			Draw.drawQuad(Sprites.gold_coin, Display.getWidth()/2+x+1+TTF2.coin.getWidth(String.valueOf(Mideas.calcGoldCoinCost(cost))), Display.getHeight()/2+y);
			TTF2.coin.drawStringShadow(Display.getWidth()/2+x+20+TTF2.coin.getWidth(String.valueOf(Mideas.calcGoldCoinCost(cost))), Display.getHeight()/2+y, String.valueOf(Mideas.calcSilverCoinCost(cost)), Color.white, Color.black, 1, 1, 1);
			Draw.drawQuad(Sprites.silver_coin, Display.getWidth()/2+x+21+TTF2.coin.getWidth(String.valueOf(Mideas.calcGoldCoinCost(cost))+String.valueOf(Mideas.calcSilverCoinCost(cost))), Display.getHeight()/2+y);
			TTF2.coin.drawStringShadow(Display.getWidth()/2+x+48+TTF2.coin.getWidth(String.valueOf(Mideas.calcGoldCoinCost(cost)+Mideas.calcSilverCoinCost(cost))), Display.getHeight()/2+y, String.valueOf(cost-Mideas.calcGoldCoinCost(cost)*10000-Mideas.calcSilverCoinCost(cost)*100), Color.white, Color.black, 1, 1, 1);
			Draw.drawQuad(Sprites.copper_coin, Display.getWidth()/2+x+38+TTF2.coin.getWidth(String.valueOf(Mideas.calcGoldCoinCost(cost))+String.valueOf(Mideas.calcSilverCoinCost(cost))+String.valueOf(cost-Mideas.calcGoldCoinCost(cost)*10000-Mideas.calcSilverCoinCost(cost)*100)), Display.getHeight()/2+y);
			return true;
		}
		if(Mideas.calcGoldCoinCost(cost) > 0 && Mideas.calcSilverCoinCost(cost) > 0 && cost-Mideas.calcGoldCoinCost(cost)*10000-Mideas.calcSilverCoinCost(cost)*100 <= 0) {
			TTF2.coin.drawStringShadow(Display.getWidth()/2+x, Display.getHeight()/2+y, String.valueOf(Mideas.calcGoldCoinCost(cost)), Color.white, Color.black, 1, 1, 1);
			Draw.drawQuad(Sprites.gold_coin, Display.getWidth()/2+x+1+TTF2.coin.getWidth(String.valueOf(Mideas.calcGoldCoinCost(cost))), Display.getHeight()/2+y);
			TTF2.coin.drawStringShadow(Display.getWidth()/2+x+20+TTF2.coin.getWidth(String.valueOf(Mideas.calcGoldCoinCost(cost))), Display.getHeight()/2+y, String.valueOf(Mideas.calcSilverCoinCost(cost)), Color.white, Color.black, 1, 1, 1);
			Draw.drawQuad(Sprites.silver_coin, Display.getWidth()/2+x+21+TTF2.coin.getWidth(String.valueOf(Mideas.calcGoldCoinCost(cost))+String.valueOf(Mideas.calcSilverCoinCost(cost))), Display.getHeight()/2+y);
			return true;
		}
		if(Mideas.calcGoldCoinCost(cost) > 0 && Mideas.calcSilverCoinCost(cost) <= 0 && cost-Mideas.calcGoldCoinCost(cost)*10000-Mideas.calcSilverCoinCost(cost)*100 > 0) {
			TTF2.coin.drawStringShadow(Display.getWidth()/2+x, Display.getHeight()/2+y, String.valueOf(Mideas.calcGoldCoinCost(cost)), Color.white, Color.black, 1, 1, 1);
			Draw.drawQuad(Sprites.gold_coin, Display.getWidth()/2+x+1+TTF2.coin.getWidth(String.valueOf(Mideas.calcGoldCoinCost(cost))), Display.getHeight()/2+y);
			TTF2.coin.drawStringShadow(Display.getWidth()/2+x+20+TTF2.coin.getWidth(String.valueOf(Mideas.calcGoldCoinCost(cost))), Display.getHeight()/2+y, String.valueOf(cost-Mideas.calcGoldCoinCost(cost)*10000-Mideas.calcSilverCoinCost(cost)*100), Color.white, Color.black, 1, 1, 1);
			Draw.drawQuad(Sprites.copper_coin, Display.getWidth()/2+x+21+TTF2.coin.getWidth(String.valueOf(Mideas.calcGoldCoinCost(cost))+String.valueOf(cost-Mideas.calcGoldCoinCost(cost)*10000-Mideas.calcSilverCoinCost(cost)*100)), Display.getHeight()/2+y);
			return true;
		}
		if(Mideas.calcGoldCoinCost(cost) <= 0 && Mideas.calcSilverCoinCost(cost) > 0 && cost-Mideas.calcGoldCoinCost(cost)*10000-Mideas.calcSilverCoinCost(cost)*100 > 0) {
			TTF2.coin.drawStringShadow(Display.getWidth()/2+x, Display.getHeight()/2+y, String.valueOf(Mideas.calcSilverCoinCost(cost)), Color.white, Color.black, 1, 1, 1);
			Draw.drawQuad(Sprites.silver_coin, Display.getWidth()/2+x+1+TTF2.coin.getWidth(String.valueOf(Mideas.calcSilverCoinCost(cost))), Display.getHeight()/2+y);
			TTF2.coin.drawStringShadow(Display.getWidth()/2+x+20+TTF2.coin.getWidth(String.valueOf(Mideas.calcSilverCoinCost(cost))), Display.getHeight()/2+y, String.valueOf(cost-Mideas.calcGoldCoinCost(cost)*10000-Mideas.calcSilverCoinCost(cost)*100), Color.white, Color.black, 1, 1, 1);
			Draw.drawQuad(Sprites.copper_coin, Display.getWidth()/2+x+21+TTF2.coin.getWidth(String.valueOf(Mideas.calcSilverCoinCost(cost))+String.valueOf(cost-Mideas.calcGoldCoinCost(cost)*10000-Mideas.calcSilverCoinCost(cost)*100)), Display.getHeight()/2+y);
			return true;
		}
		if(Mideas.calcGoldCoinCost(cost) > 0 && Mideas.calcSilverCoinCost(cost) <= 0 && cost-Mideas.calcGoldCoinCost(cost)*10000-Mideas.calcSilverCoinCost(cost)*100 <= 0) {
			TTF2.coin.drawStringShadow(Display.getWidth()/2+x, Display.getHeight()/2+y, String.valueOf(Mideas.calcGoldCoinCost(cost)), Color.white, Color.black, 1, 1, 1);
			Draw.drawQuad(Sprites.gold_coin, Display.getWidth()/2+x+1+TTF2.coin.getWidth(String.valueOf(Mideas.calcGoldCoinCost(cost))), Display.getHeight()/2+y);
			return true;
		}
		if(Mideas.calcGoldCoinCost(cost) <= 0 && Mideas.calcSilverCoinCost(cost) > 0 && cost-Mideas.calcGoldCoinCost(cost)*10000-Mideas.calcSilverCoinCost(cost)*100 <= 0) {
			TTF2.coin.drawStringShadow(Display.getWidth()/2+x, Display.getHeight()/2+y, String.valueOf(Mideas.calcSilverCoinCost(cost)), Color.white, Color.black, 1, 1, 1);
			Draw.drawQuad(Sprites.silver_coin, Display.getWidth()/2+x+1+TTF2.coin.getWidth(String.valueOf(Mideas.calcSilverCoinCost(cost))), Display.getHeight()/2+y);
			return true;
		}
		if(Mideas.calcGoldCoinCost(cost) <= 0 && Mideas.calcSilverCoinCost(cost) <= 0 && cost-Mideas.calcGoldCoinCost(cost)*10000-Mideas.calcSilverCoinCost(cost)*100 > 0) {
			TTF2.coin.drawStringShadow(Display.getWidth()/2+x, Display.getHeight()/2+y, String.valueOf(cost-Mideas.calcGoldCoinCost(cost)*10000-Mideas.calcSilverCoinCost(cost)*100), Color.white, Color.black, 1, 1, 1);
			Draw.drawQuad(Sprites.copper_coin, Display.getWidth()/2+x+1+TTF2.coin.getWidth(String.valueOf(cost-Mideas.calcGoldCoinCost(cost)*10000-Mideas.calcSilverCoinCost(cost)*100)), Display.getHeight()/2+y);
			return true;
		}
		return true;
	}
	
	public static boolean isHoverShopFrame() {
		if(Mideas.mouseX() >= Display.getWidth()/2-300 && Mideas.mouseX() <= Display.getWidth()/2-300+Sprites.shop_frame.getImageWidth() && Mideas.mouseY() >= Display.getHeight()/2-350 && Mideas.mouseY() <= Display.getHeight()/2-350+Sprites.shop_frame.getImageHeight()) {
			return true;
		}
		return false;
	}
	
	public static ArrayList<Shop> getShopList() {
		return shopList;
	}
}