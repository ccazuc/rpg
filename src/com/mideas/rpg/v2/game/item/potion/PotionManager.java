package com.mideas.rpg.v2.game.item.potion;

import java.sql.SQLException;
import java.util.HashMap;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.jdo.JDOStatement;

public class PotionManager {
	
	private static HashMap<Integer, Potion> potionList = new HashMap<Integer, Potion>();
	private static int numberPotionLoaded;
	
	/*public static void loadPotions() {
		try {
			JDOStatement statement = Mideas.getJDO().prepare("SELECT id, sprite_id, name, level, heal, mana, sellprice FROM item_potion");
			statement.execute();
			while(statement.fetch()) {
				int id = statement.getInt();
				String sprite_id = statement.getString();
				String name = statement.getString();
				byte level = statement.getByte();
				int heal = statement.getInt();
				int mana = statement.getInt();
				int sellPrice = statement.getInt();
				Potion newPotion = new Potion(id, sprite_id, name, level, heal, mana, sellPrice, 1);
				potionList.put(id, newPotion);
				numberPotionLoaded++;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	public static void storeNewPiece(Potion potion) {
		if(potion != null) {
			Potion tmp = potionList.get(potion.getId());
			if(tmp != null && !tmp.getIsLoaded()) {
				tmp.updatePotion(potion);
			}
			else {
				potionList.put(potion.getId(), potion);
			}
		}
	}
	
	public static Potion getPotion(int id) {
		if(potionList.containsKey(id)) {
			return potionList.get(id);
		}
		return null;
	}
	
	public static Potion getClone(int id) {
		Potion temp = getPotion(id);
		if(temp != null) {
			return new Potion(temp);
		}
		return null;
	}
	
	public static boolean exists(int id) {
		return potionList.containsKey(id);
	}
	
	public static int getNumberPotionLoaded() {
		return numberPotionLoaded;
	}
	
	public static HashMap<Integer, Potion> getPotionMap() {
		return potionList;
	}
}
