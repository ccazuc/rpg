package com.mideas.rpg.v2.game;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.game.item.Item;
import com.mideas.rpg.v2.jdo.JDOStatement;

public class DropManager {

	private static HashMap<Integer, ArrayList<Drop>> dropList = new HashMap<Integer, ArrayList<Drop>>();
	
	public static void loadDropTable(int id) throws SQLException {
		/*JDOStatement statement = Mideas.getJDO().prepare("SELECT COUNT(mob_id) FROM drop WHERE mob_id = ?");
		statement.putInt(id);
		statement.execute();
		int number = statement.getInt();
		Item[] temp = new Item[number];*/
		ArrayList<Drop> temp = new ArrayList<Drop>();
		JDOStatement statement = Mideas.getJDO().prepare("SELECT item_id, amount, droprate FROM drop WHERE mob_id = ?");
		statement.putInt(id);
		statement.execute();
		while(statement.fetch()) {
			int itemId = statement.getInt();
			int amount = statement.getInt();
			int dropRate = statement.getInt();
			temp.add(new Drop(Item.getItem(itemId), dropRate, amount));
		}
		dropList.put(id, temp);
	}
	
	public static HashMap<Integer, ArrayList<Drop>> getDropList() {
		return dropList;
	}
	
	public static ArrayList<Drop> getList(int id) {
		return dropList.get(id);
	}
	
	public static boolean exists(int id) {
		return dropList.containsKey(id);
	}
}
