package com.mideas.rpg.v2.command;

import java.sql.SQLException;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.connection.ConnectionManager;
import com.mideas.rpg.v2.connection.PacketID;
import com.mideas.rpg.v2.game.item.Item;
import com.mideas.rpg.v2.game.item.ItemType;

public class CommandAddItem extends Command {

	@Override
	public void read() {
		byte packetID = ConnectionManager.getConnection().readByte();
		if(packetID == PacketID.KNOWN_ITEM) {
			int id = ConnectionManager.getConnection().readInt();
			int number = ConnectionManager.getConnection().readInt();
			Item item = Item.getItem(id);
			try {
				Mideas.joueur1().addItem(item, number);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else if(packetID == PacketID.UNKNOWN_ITEM) {
			try {
				int number = ConnectionManager.getConnection().readInt();
				ItemType type = ItemType.values()[ConnectionManager.getConnection().readChar()];
				if(type == ItemType.CONTAINER) {
					Mideas.joueur1().addItem(ConnectionManager.getConnection().readContainer(), number);
				}
				else if(type == ItemType.GEM) {
					Mideas.joueur1().addItem(ConnectionManager.getConnection().readGem(), number);
				}
				else if(type == ItemType.STUFF) {
					Mideas.joueur1().addItem(ConnectionManager.getConnection().readStuff(), number);
				}
				else if(type == ItemType.WEAPON) {
					Mideas.joueur1().addItem(ConnectionManager.getConnection().readWeapon(), number);
				}
				else if(type == ItemType.POTION) {
					Mideas.joueur1().addItem(ConnectionManager.getConnection().readPotion(), number);
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void write(int id, int number) {
		ConnectionManager.getConnection().writeByte(PacketID.ADD_ITEM);
		if(Item.exists(id)) {
			ConnectionManager.getConnection().writeByte(PacketID.KNOWN_ITEM);
			ConnectionManager.getConnection().writeInt(id);
			ConnectionManager.getConnection().writeInt(number);
		}
		else {
			ConnectionManager.getConnection().writeByte(PacketID.UNKNOWN_ITEM);
			ConnectionManager.getConnection().writeInt(id);
			ConnectionManager.getConnection().writeInt(number);
		}
		ConnectionManager.getConnection().send();
	}
}