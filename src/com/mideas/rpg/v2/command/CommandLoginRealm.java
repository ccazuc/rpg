package com.mideas.rpg.v2.command;

import com.mideas.rpg.v2.connection.ConnectionManager;
import com.mideas.rpg.v2.connection.PacketID;
import com.mideas.rpg.v2.hud.RealmListFrame;
import com.mideas.rpg.v2.hud.SelectScreen;

public class CommandLoginRealm extends Command {

	@Override
	public void read() {
		short packetId = ConnectionManager.getWorldServerConnection().readShort();
		if(packetId == PacketID.LOGIN_REALM_SUCCESS) {
			System.out.println("LOGIN:LOGIN_REALM_SUCCESS");
			ConnectionManager.setIsLoggedOnWorldServer(true);
			ConnectionManager.setWorldServer(RealmListFrame.getSelectedRealm());
			SelectScreen.getAlert().setText("Loading characters...");
			//SelectScreen.setRealmScreenActive(false);
		}
	}
}
