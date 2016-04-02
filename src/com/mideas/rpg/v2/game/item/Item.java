package com.mideas.rpg.v2.game.item;

public class Item implements Cloneable {

	protected ItemType itemType;
	protected int sellPrice;
	protected String name;
	protected int id;
	protected String sprite_id;
	protected int maxStack;
	protected int quality;
	
	public Item(int id, String sprite_id, ItemType itemType, String name, int quality, int sellPrice, int maxStack) {
		this.sellPrice = sellPrice;
		this.itemType = itemType;
		this.sprite_id = sprite_id;
		this.name = name;
		this.id = id;
		this.maxStack = maxStack;
		this.quality = quality;
	}
	
	public Item() {}

	public int getId() {
		return id;
	}
	
	public String getSpriteId() {
		return sprite_id;
	}
	
	public boolean equals(Item item) {
		return item != null && item.getId() == id;
	}
	
	public String getStuffName() {
		return name;
	}
	
	public int getQuality() {
		return quality;
	}
	
	public int getSellPrice() {
		return sellPrice;
	}
	
	public ItemType getItemType() {
		return itemType;
	}
}