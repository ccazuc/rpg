package com.mideas.rpg.v2.command;

import com.mideas.rpg.v2.Interface;
import com.mideas.rpg.v2.chat.ChatFrame;
import com.mideas.rpg.v2.chat.Message;
import com.mideas.rpg.v2.chat.MessageType;
import com.mideas.rpg.v2.connection.ConnectionManager;
import com.mideas.rpg.v2.connection.PacketID;
import com.mideas.rpg.v2.game.SocialFrameMenu;
import com.mideas.rpg.v2.game.race.Race;
import com.mideas.rpg.v2.game.social.WhoUnit;
import com.mideas.rpg.v2.game.unit.ClassType;
import com.mideas.rpg.v2.hud.social.SocialFrame;
import com.mideas.rpg.v2.hud.social.who.WhoFrame;

public class CommandWho extends Command {
	
	@Override
	public void read() {
		WhoFrame.clearList();
		while(ConnectionManager.getWorldServerConnection().hasRemaining()) {
			int id = ConnectionManager.getWorldServerConnection().readInt();
			if(id == -1) {
				break;
			}
			String name = ConnectionManager.getWorldServerConnection().readString();
			String guildName = ConnectionManager.getWorldServerConnection().readString();
			Race race = Race.values()[ConnectionManager.getWorldServerConnection().readByte()];
			int level = ConnectionManager.getWorldServerConnection().readInt();
			ClassType classe = ClassType.values()[ConnectionManager.getWorldServerConnection().readByte()];
			WhoFrame.addToList(new WhoUnit(id, name, guildName, level, classe, race));
		}
		if (WhoFrame.getList().size() <= 4 && WhoFrame.getList().size() > 0)
		{
			int i = -1;
			while (++i < WhoFrame.getList().size())
				ChatFrame.addMessage(new Message(": Level " + WhoFrame.getList().get(i).getLevelString() + ' ' + WhoFrame.getList().get(i).getRace() + ' ' + WhoFrame.getList().get(i).getClasse() + " - Area", WhoFrame.getList().get(i).getName(), false, MessageType.SELF, false));
			if (WhoFrame.getList().size() == 1)
				ChatFrame.addMessage(new Message("1 player total", false, MessageType.SELF));
			else
				ChatFrame.addMessage(new Message(WhoFrame.getList().size() + " players total", false, MessageType.SELF));
		}
		else
		{
			SocialFrame.setSelectedMenu(SocialFrameMenu.WHO_FRAME);
			Interface.setSocialFrameStatus(true);
		}
	}
	
	public static void write(String word) {
		ConnectionManager.getWorldServerConnection().startPacket();
		ConnectionManager.getWorldServerConnection().writeShort(PacketID.WHO);
		ConnectionManager.getWorldServerConnection().writeString(word);
		ConnectionManager.getWorldServerConnection().endPacket();
		ConnectionManager.getWorldServerConnection().send();
	}
}
