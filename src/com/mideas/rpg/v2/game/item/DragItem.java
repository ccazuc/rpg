package com.mideas.rpg.v2.game.item;

public enum DragItem {

	BAG((byte)0),
	INVENTORY((byte)1),
	BANK((byte)2),
	GUILDBANK((byte)3),
	;
	
	private byte value;
	
	private DragItem(byte value) {
		this.value = value;
	}
	
	public byte getValue() {
		return this.value;
	}
}
