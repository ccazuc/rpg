package com.mideas.rpg.v2.command;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.chat.ChatFrame;
import com.mideas.rpg.v2.chat.Message;
import com.mideas.rpg.v2.chat.MessageType;
import com.mideas.rpg.v2.connection.ConnectionManager;
import com.mideas.rpg.v2.connection.PacketID;
import com.mideas.rpg.v2.game.race.Race;
import com.mideas.rpg.v2.game.social.Friend;
import com.mideas.rpg.v2.game.unit.ClassType;
import com.mideas.rpg.v2.game.unit.Joueur;

public class CommandFriend extends Command {

	@Override
	public void read() {
		short packetId = ConnectionManager.getWorldServerConnection().readShort();
		if(packetId == PacketID.FRIEND_SEND_INFO) {
			
		}
		else if(packetId == PacketID.FRIEND_ADD) {
			boolean online = ConnectionManager.getWorldServerConnection().readBoolean();
			if(online) {
				Mideas.joueur1().addFriend(new Friend(ConnectionManager.getWorldServerConnection().readInt(), ConnectionManager.getWorldServerConnection().readString(), ConnectionManager.getWorldServerConnection().readInt(), Race.values()[ConnectionManager.getWorldServerConnection().readByte()], ClassType.values()[ConnectionManager.getWorldServerConnection().readByte()]));
			}
			else {
				Mideas.joueur1().addFriend(new Friend(ConnectionManager.getWorldServerConnection().readInt(), ConnectionManager.getWorldServerConnection().readString()));
			}
		}
		else if(packetId == PacketID.FRIEND_OFFLINE) {
			int id = ConnectionManager.getWorldServerConnection().readInt();
			int i = 0;
			while(i < Mideas.joueur1().getFriendList().size()) {
				if(Mideas.joueur1().getFriendList().get(i).getCharacterId() == id) {
					ChatFrame.addMessage(new Message(Mideas.joueur1().getFriendList().get(i).getName().concat(" is now offline."), false, MessageType.SELF, true));
					Mideas.joueur1().getFriendList().get(i).setOnlineStatus(false);
					return;
				}
				i++;
			}
		}
		else if(packetId == PacketID.FRIEND_ONLINE) {
			int id = ConnectionManager.getWorldServerConnection().readInt();
			String name = ConnectionManager.getWorldServerConnection().readString();
			int level = ConnectionManager.getWorldServerConnection().readInt();
			Race race = Race.values()[ConnectionManager.getWorldServerConnection().readByte()];
			ClassType classe = ClassType.values()[ConnectionManager.getWorldServerConnection().readByte()];
			int i = 0;
			while(i < Mideas.joueur1().getFriendList().size()) {
				if(Mideas.joueur1().getFriendList().get(i).getCharacterId() == id) {
					ChatFrame.addMessage(new Message(" is now online.", name, false, MessageType.SELF, false, true));
					Mideas.joueur1().getFriendList().get(i).updateInformations(name, level, race, classe);
					return;
				}
				i++;
			}
		}
		else if(packetId == PacketID.FRIEND_LOAD_ALL) {
			int i = 0;
			int length = ConnectionManager.getWorldServerConnection().readInt();
			while(i < length) {
				int id = ConnectionManager.getWorldServerConnection().readInt();
				boolean isOnline = ConnectionManager.getWorldServerConnection().readBoolean();
				String name = ConnectionManager.getWorldServerConnection().readString();
				if(isOnline) {
					int level = ConnectionManager.getWorldServerConnection().readInt();
					Race race = Race.values()[ConnectionManager.getWorldServerConnection().readByte()];
					ClassType classe = ClassType.values()[ConnectionManager.getWorldServerConnection().readByte()];
					Mideas.joueur1().addFriendNoSort(new Friend(id, name, level, race, classe));
				}
				else {
					Mideas.joueur1().addFriendNoSort(new Friend(id, name));
				}
				i++;
			}
			Mideas.joueur1().sortFriendList();
		}
		else if(packetId == PacketID.FRIEND_REMOVE) {
			int id = ConnectionManager.getWorldServerConnection().readInt();
			Mideas.joueur1().removeFriend(id);
		}
	}
	
	public static void addFriend(String name) {
		if(Mideas.joueur1().getFriendList().size() >= Joueur.MAXIMUM_AMOUNT_FRIENDS) {
			ChatFrame.addMessage(new Message("Your friendlist is full.", false, MessageType.SELF, true));
			return;
		}
		if(name.equals(Mideas.joueur1().getName()))
		{
			ChatFrame.addMessage(new Message("You can't put yourself in your friends list.", false, MessageType.SELF, true));
			return;
		}
		ConnectionManager.getWorldServerConnection().startPacket();
		ConnectionManager.getWorldServerConnection().writeShort(PacketID.FRIEND);
		ConnectionManager.getWorldServerConnection().writeShort(PacketID.FRIEND_ADD);
		ConnectionManager.getWorldServerConnection().writeString(name);
		ConnectionManager.getWorldServerConnection().endPacket();
		ConnectionManager.getWorldServerConnection().send();
	}
	
	public static void removeFriend(int id) {
		ConnectionManager.getWorldServerConnection().startPacket();
		ConnectionManager.getWorldServerConnection().writeShort(PacketID.FRIEND);
		ConnectionManager.getWorldServerConnection().writeShort(PacketID.FRIEND_REMOVE);
		ConnectionManager.getWorldServerConnection().writeInt(id);
		ConnectionManager.getWorldServerConnection().endPacket();
		ConnectionManager.getWorldServerConnection().send();
	}
}
