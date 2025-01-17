package com.mideas.rpg.v2.game.unit;

public enum ClassType {

	DRUID((byte)0),
	GUERRIER((byte)1),
	HUNTER((byte)2),
	MAGE((byte)3),
	PALADIN((byte)4),
	PRIEST((byte)5),
	ROGUE((byte)6),
	SHAMAN((byte)7),
	WARLOCK((byte)8),
	NPC((byte)9);
	
	private byte value;
	
	private ClassType(byte value) {
		this.value = value;
	}
	
	public byte getValue() {
		return this.value;
	}
	
	public static ClassType getValue(byte value)
	{
		if (value < 0 || value >= ClassType.values().length)
			return (null);
		return (ClassType.values()[value]);
	}
}
