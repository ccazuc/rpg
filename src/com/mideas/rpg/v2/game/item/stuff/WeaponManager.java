package com.mideas.rpg.v2.game.item.stuff;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.game.ClassType;
import com.mideas.rpg.v2.hud.DragManager;
import com.mideas.rpg.v2.jdo.JDOStatement;

public class WeaponManager {
	
	private static ArrayList<Stuff> weaponList = new ArrayList<Stuff>();
	
	public static void loadWeapons() throws SQLException {
		JDOStatement statement = Mideas.getJDO().prepare("SELECT id, name, sprite_id, class, type, slot, quality, level, armor, stamina, mana, critical, strength, sellprice FROM weapon");
		statement.execute();
		while(statement.fetch()) {
			int id = statement.getInt();
			String name = statement.getString();
			String sprite_id = statement.getString();
			short classeTemp = statement.getShort();
			ClassType[] classeType = StuffManager.getClasses(classeTemp);
			String tempType = statement.getString();
			WeaponType type = getType(tempType);
			String tempSlot = statement.getString();
			WeaponSlot slot = getSlot(tempSlot);
			int quality = statement.getInt();
			int level = statement.getInt();
			int armor = statement.getInt();
			int stamina = statement.getInt();
			int mana = statement.getInt();
			int critical = statement.getInt();
			int strength = statement.getInt();
			int sellPrice = statement.getInt();
			Stuff newPiece = new Stuff(id, name, sprite_id, classeType, type, slot, quality, level, armor, stamina, mana, critical, strength, sellPrice);
			weaponList.add(newPiece);
		}
	}
	public static Stuff getWeapon(int id) {
		int i = 0;
		while(i < weaponList.size()) {
			if(weaponList.get(i).getId() == id) {
				return weaponList.get(i);
			}
			i++;
		}
		return null;
	}
	
	public static boolean exists(int id) {
		return getWeapon(id) != null;
	}
	
	public static Stuff getClone(int id) {
		Stuff temp = getWeapon(id);
		if(temp != null) {
			return new Stuff(temp, 0);
		}
		return null;
	}
	
	public static boolean canEquipWeapon(Stuff weapon) throws FileNotFoundException, SQLException {
		if(Mideas.getLevel() >= weapon.getLevel() && weapon.canWearWeapon() && weapon.canEquipTo(DragManager.convClassType())) {
			return true;
		}
		return false;
	}
	
	public static ArrayList<Stuff> getWeaponList() {
		return weaponList;
	}
	
	private static WeaponType getType(String type) {
		if(type.equals("DAGGER")) {
			return WeaponType.DAGGER;
		}
		if(type.equals("FISTWEAPON")) {
			return WeaponType.FISTWEAPON;
		}
		if(type.equals("ONEHANDEDAXE")) {
			return WeaponType.ONEHANDEDAXE;
		}
		if(type.equals("TWOHANDEDAXE")) {
			return WeaponType.TWOHANDEDAXE;
		}
		if(type.equals("ONEHANDEDSWORD")) {
			return WeaponType.ONEHANDEDSWORD;
		}
		if(type.equals("TWOHANDEDSWORD")) {
			return WeaponType.TWOHANDEDSWORD;
		}
		if(type.equals("ONEHANDEDMACE")) {
			return WeaponType.ONEHANDEDMACE;
		}
		if(type.equals("TWOHANDEDMACE")) {
			return WeaponType.TWOHANDEDMACE;
		}
		if(type.equals("POLEARM")) {
			return WeaponType.POLEARM;
		}
		if(type.equals("STAFF")) {
			return WeaponType.STAFF;
		}
		if(type.equals("BOW")) {
			return WeaponType.BOW;
		}
		if(type.equals("CROSSBOW")) {
			return WeaponType.CROSSBOW;
		}
		if(type.equals("GUN")) {
			return WeaponType.GUN;
		}
		if(type.equals("THROWN")) {
			return WeaponType.THROWN;
		}
		if(type.equals("WAND")) {
			return WeaponType.WAND;
		}
		return null;
	}
	
	private static WeaponSlot getSlot(String slot) {
		if(slot.equals("OFFHAND")) {
			return WeaponSlot.OFFHAND;
		}
		if(slot.equals("MAINHAND")) {
			return WeaponSlot.MAINHAND;
		}
		if(slot.equals("RANGED")) {
			return WeaponSlot.RANGED;
		}
		if(slot.equals("BOTH")) {
			return WeaponSlot.BOTH;
		}
		return null;
	}
}
