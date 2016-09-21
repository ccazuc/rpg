package com.mideas.rpg.v2.command;

import static com.mideas.rpg.v2.connection.PacketID.CREATE_CHARACTER;
import static com.mideas.rpg.v2.connection.PacketID.ERROR_NAME_ALPHABET;
import static com.mideas.rpg.v2.connection.PacketID.ERROR_NAME_ALREADY_TAKEN;

import com.mideas.rpg.v2.Mideas;

import static com.mideas.rpg.v2.connection.PacketID.CHARACTER_CREATED;

import com.mideas.rpg.v2.connection.ConnectionManager;
import com.mideas.rpg.v2.hud.SelectScreen;

public class CommandCreateCharacter extends Command {
	
	@Override
	public void read() {
		byte packetId = ConnectionManager.getConnection().readByte();
		if(packetId == CHARACTER_CREATED) {
			CommandSelectScreenLoadCharacters.write();
			SelectScreen.setCreatingCharacter(false);
			SelectScreen.setSelectedCharacter(SelectScreen.getSelectedCharacterIndex(), false);
			SelectScreen.setSelectedCharacter(SelectScreen.getTotalCharacter(), true);
			SelectScreen.setSelectedCharacterIndex(SelectScreen.getTotalCharacter());
			SelectScreen.getCharacterInput().resetText();
		}
		else if(packetId == ERROR_NAME_ALPHABET) {
			SelectScreen.getAlert().setActive();
			SelectScreen.getAlert().setText("Le nom doit contenir uniquement des caract�res alphab�tiques");
		}
		else if(packetId == ERROR_NAME_ALREADY_TAKEN) {
			SelectScreen.getAlert().setActive();
			SelectScreen.getAlert().setText("Ce nom est d�j� utilis�");
		}
	}
	
	public static void write(String name) {
		if(ConnectionManager.isConnected()) {
			ConnectionManager.getConnection().writeByte(CREATE_CHARACTER);
			ConnectionManager.getConnection().writeString(name);
			ConnectionManager.getConnection().writeInt(Mideas.getAccountId());
			ConnectionManager.getConnection().writeString(SelectScreen.getSelectedClasse());
			ConnectionManager.getConnection().writeString(SelectScreen.getSelectedRace());
			ConnectionManager.getConnection().send();
		}
	}
}