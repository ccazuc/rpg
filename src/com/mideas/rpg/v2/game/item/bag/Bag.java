package com.mideas.rpg.v2.game.item.bag;

import java.util.HashMap;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.game.item.Item;
import com.mideas.rpg.v2.game.item.ItemType;
import com.mideas.rpg.v2.hud.SpellBarFrame;

public class Bag extends Item implements Cloneable {
	
	private Item[] bag = new Item[16];
	private Container[] equippedBag = new Container[4];
	private int id;
	private String sprite_id;
	private String name;
	private int size;
	private static boolean bagChange = true;
	private static int numberFreeSlotBag;
	
	private static HashMap<Item, Integer> numberStack = new HashMap<Item, Integer>();
	private static HashMap<Integer, Integer> itemList = new HashMap<Integer, Integer>();
	private static HashMap<Integer, String> itemListString = new HashMap<Integer, String>();
	
	public Bag() {}
	
	public Bag(Bag bag) {
		super(bag.id, bag.sprite_id, bag.itemType, bag.name, bag.quality, bag.sellPrice, bag.maxStack);
		this.sprite_id = bag.sprite_id;
		this.name = bag.name;
		this.size = bag.size;
		this.id = bag.id;
	}
	
	public Bag(int id, String sprite_id, String name, int quality, int size, int sellPrice) {
		super(id, sprite_id, ItemType.CONTAINER, name, quality, sellPrice, 1);
		this.sprite_id = sprite_id;
		this.name = name;
		this.size = size;
		this.id = id;
	}
	
	public void event() {
		if(bagChange) {
			updateBagItem();
			if(Mideas.joueur1() != null) {
				if(Mideas.joueur1().getFirstProfession() != null) {
					Mideas.joueur1().getFirstProfession().updateNumberPossibleCraft();
				}
				else if(Mideas.joueur1().getSecondProfession() != null) {
					Mideas.joueur1().getSecondProfession().updateNumberPossibleCraft();
				}
			}
			bagChange = false;
		}
		SpellBarFrame.setNumberFreeSlotBag(numberFreeSlotBag);
	}
	
	public Item[] getBag() {
		return this.bag;
	}
	
	public Container[] getEquippedBag() {
		return this.equippedBag;
	}
	
	public Container getEquippedBag(int i) {
		return this.equippedBag[i];
	}
	
	@Override
	public String getSpriteId()  {
		return this.sprite_id;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getNumberFreeSlotBag() {
		return numberFreeSlotBag;
	}
	
	public String getSpriteId(int i) {
		if(i < this.equippedBag.length) {
			return this.equippedBag[i].getSpriteId();
		}
		return null;
	}
	
	public void setEquippedBag(int i, Container bag) {
		if(i < this.equippedBag.length) {
			if(bag != null) {
				int length = Mideas.bag().getBag().length;
				Item[] tempBag = this.bag.clone();
				if(this.equippedBag[i] != null) {
					int tempBagSize = this.equippedBag[i].getSize();
					this.equippedBag[i] = bag;
					if(tempBagSize >= this.equippedBag[i].getSize()) {
						this.bag = new Item[length+(tempBagSize-this.equippedBag[i].getSize())];
					}
					else {
						this.bag = new Item[length+(this.equippedBag[i].getSize()-tempBagSize)];
					}
				}
				else {
					this.equippedBag[i] = bag;
					this.bag = new Item[length+this.equippedBag[i].getSize()];
				}
				int j = 0;
				while(j < tempBag.length && j < this.bag.length) {
					this.bag[j] = tempBag[j];
					j++;
				}
			}
			else {
				this.equippedBag[i] = bag;
			}
		}
	}
	
	public int getEquippedBagSize(int i) {
		if(i < this.equippedBag.length && this.equippedBag[i] != null) {
			return this.equippedBag[i].getSize();
		}
		return 0;
	}
	
	public void updateBagItem() {
		numberFreeSlotBag = 0;
		itemList.clear();
		int i = 0;
		while(i < Mideas.bag().getBag().length) {
			if(Mideas.bag().getBag(i) != null) {
				if(itemList.containsKey(Mideas.bag().getBag(i).getId())) {
					if(Mideas.bag().getBag(i).isStackable()) {
						itemList.put(Mideas.bag().getBag(i).getId(), Mideas.bag().getNumberBagItem(Mideas.bag().getBag(i))+itemList.get(Mideas.bag().getBag(i).getId()));
					}
					else {
						itemList.put(Mideas.bag().getBag(i).getId(), itemList.get(Mideas.bag().getBag(i).getId())+1);
					}
				}
				else {
					if(Mideas.bag().getBag(i).isStackable()) {
						itemList.put(Mideas.bag().getBag(i).getId(), Mideas.bag().getNumberBagItem(Mideas.bag().getBag(i)));
					}
					else {
						itemList.put(Mideas.bag().getBag(i).getId(), 1);
					}
				}
			}
			else {
				numberFreeSlotBag++;
			}
			i++;
		}
	}
	
	public int getNumberItemInBags(int id) {
		if(itemList.containsKey(id)) {
			return itemList.get(id);
		}
		return 0;
	}
	
	public String getNumberItemInBagsString(int id) {
		if(itemListString.containsKey(id)) {
			return itemListString.get(id);
		}
		return "0";
	}
	
	public HashMap<Item, Integer> getNumberStack() {
		return numberStack;
	}
	
	public HashMap<Integer, Integer> getItemList() {
		return itemList;
	}
	
	public int getItemNumber(Item item) {
		if(itemList.containsKey(item.getId())) {
			return itemList.get(item.getId());
		}
		return 0;
	}
	
	public Item getEquals(Item item) {
		int i = 0;
		while(i < numberStack.size()) {
			if(numberStack.get(i) != null && numberStack.get(i).equals(item)) {
				return getKey(item);
			}
			i++;
		}
		return null;
	}
	
	public static Item getKey(Item item){
	    for(Item key : numberStack.keySet()) {
	       if(key.getId() == item.getId()) {
	        	return key;
	        }
	    }
	    return null;
	}
	
	public int getNumberBagItem(Item item) {
		return numberStack.get(item);
	}
	
	public Item getBag(int i) {
		if(i >= 0 && i < this.bag.length && this.bag[i] != null) {
			return this.bag[i];
		}
		return null;
	}
	
	public void setBag(int i, Item stuff) {
		if(i >= 0 && i < this.bag.length) {
			this.bag[i] = stuff;
		}
	}
	
	public void setBag(int i, Item stuff, int number) {
		if(i >= 0 && i < this.bag.length) {
			if(number <= 0) {
				this.bag[i] = null;
				numberStack.remove(stuff);
			}
			else {
				this.bag[i] = stuff;
				numberStack.put(stuff, number);
			}
		}
	}
	
	@Override
	public int getId() {
		return this.id;
	}
	
	public void setBagChange(boolean we) {
		bagChange = we;
	}
}
