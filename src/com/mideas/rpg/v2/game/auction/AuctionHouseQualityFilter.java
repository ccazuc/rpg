package com.mideas.rpg.v2.game.auction;

public enum AuctionHouseQualityFilter {

	ERROR((byte)0),
	ALL((byte)1),
	POOR((byte)2),
	COMMON((byte)3),
	UNCOMMON((byte)4),
	RARE((byte)5),
	EPIC((byte)6),
	LEGENDARY((byte)7),
	
	;
	
	private final byte value;
	
	private AuctionHouseQualityFilter(byte value) {
		this.value = value;
	}
	
	public byte getValue() {
		return this.value;
	}
	
	public static AuctionHouseQualityFilter getQualityFilter(byte value) {
		if(value >= 0 && value < values().length) {
			return values()[value];
		}
		return ERROR;
	}
}
