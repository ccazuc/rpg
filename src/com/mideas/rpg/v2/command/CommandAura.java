package com.mideas.rpg.v2.command;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.connection.ConnectionManager;
import com.mideas.rpg.v2.connection.PacketID;
import com.mideas.rpg.v2.game.unit.Unit;
import com.mideas.rpg.v2.game.aura.Aura;
import com.mideas.rpg.v2.game.aura.AuraMgr;
import com.mideas.rpg.v2.game.aura.AppliedAura;

public class CommandAura extends Command {

	@Override
	public void read() {
		short packetId = ConnectionManager.getWorldServerConnection().readShort();
		if(packetId == PacketID.AURA_SEND) {
			int unitID = ConnectionManager.getWorldServerConnection().readInt();
			int auraID = ConnectionManager.getWorldServerConnection().readInt();
			long endTimer = ConnectionManager.getWorldServerConnection().readLong();
			byte numberStack = ConnectionManager.getWorldServerConnection().readByte();
			Aura aura = AuraMgr.getAura(auraID);
			if(aura == null) {
				return;
			}
			Unit unit = unitID == Mideas.joueur1().getId() ? Mideas.joueur1() : Mideas.joueur1().getTarget();
			if(unit == null) {
				return;
			}
			unit.applyAura(new AppliedAura(aura, endTimer, numberStack));
		}
		else if(packetId == PacketID.AURA_UPDATE) {
			int unitID = ConnectionManager.getWorldServerConnection().readInt();
			int auraID = ConnectionManager.getWorldServerConnection().readInt();
			long endTimer = ConnectionManager.getWorldServerConnection().readLong();
			byte numberStack = ConnectionManager.getWorldServerConnection().readByte();
			Aura aura = AuraMgr.getAura(auraID);
			if(aura == null) {
				return;
			}
			Unit unit = unitID == Mideas.joueur1().getId() ? Mideas.joueur1() : Mideas.joueur1().getTarget();
			if(unit == null) {
				return;
			}
			AppliedAura applied = null;
			if(aura.isBuff()) {
				applied = unit.getBuff(auraID);
			}
			else {
				applied = unit.getDebuff(auraID);
			}
			if(applied == null) {
				return;
			}
			applied.update(endTimer, numberStack);
		}
		else if(packetId == PacketID.AURA_CANCEL) {
			int unitID = ConnectionManager.getWorldServerConnection().readInt();
			int auraID = ConnectionManager.getWorldServerConnection().readInt();
			Aura aura = AuraMgr.getAura(auraID);
			if(aura == null) {
				return;
			}
			Unit unit = unitID == Mideas.joueur1().getId() ? Mideas.joueur1() : Mideas.joueur1().getTarget();
			if(unit == null) {
				return;
			}
			if(aura.isBuff()) {
				unit.removeBuff(auraID);
			}
			else {
				unit.removeDebuff(auraID);
			}
		}
		else if(packetId == PacketID.AURA_INIT) {
			int unitID = ConnectionManager.getWorldServerConnection().readInt();
			short length = ConnectionManager.getWorldServerConnection().readShort();
			Unit unit = unitID == Mideas.joueur1().getId() ? Mideas.joueur1() : Mideas.joueur1().getTarget();
			int i = 0;
			while(i < length) {
				int auraID = ConnectionManager.getWorldServerConnection().readInt();
				long endTimer = ConnectionManager.getWorldServerConnection().readLong();
				byte numberStack = ConnectionManager.getWorldServerConnection().readByte();
				unit.applyAura(new AppliedAura(AuraMgr.getAura(auraID), endTimer, numberStack));
				i++;
			}
		}
	}
	
	public static void cancelAura(int auraID) {
		ConnectionManager.getWorldServerConnection().startPacket();
		ConnectionManager.getWorldServerConnection().writeShort(PacketID.AURA);
		ConnectionManager.getWorldServerConnection().writeShort(PacketID.AURA_CANCEL);
		ConnectionManager.getWorldServerConnection().writeInt(auraID);
		ConnectionManager.getWorldServerConnection().endPacket();
		ConnectionManager.getWorldServerConnection().send();
	}
}
