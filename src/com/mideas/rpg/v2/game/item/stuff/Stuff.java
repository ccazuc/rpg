package com.mideas.rpg.v2.game.item.stuff;

import org.newdawn.slick.opengl.Texture;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.Sprites;
import com.mideas.rpg.v2.game.ClassType;
import com.mideas.rpg.v2.game.item.Item;
import com.mideas.rpg.v2.game.item.ItemType;
import com.mideas.rpg.v2.game.item.gem.GemBonusType;
import com.mideas.rpg.v2.game.item.gem.Gem;
import com.mideas.rpg.v2.game.item.gem.GemColor;

public class Stuff extends Item {

	protected boolean gemBonusActivated;
	protected GemBonusType gemBonusType;
	protected ClassType[] classType;
	protected WeaponType weaponType;
	protected WeaponSlot weaponSlot;
	protected int gemBonusValue;
	protected Gem equippedGem1;
	protected Gem equippedGem2;
	protected Gem equippedGem3;
	protected GemColor color1;
	protected GemColor color2;
	protected GemColor color3;
	protected StuffType type;
	protected int critical;
	protected int strength;
	protected int stamina;
	protected Wear wear;
	protected int price;
	protected int armor;
	protected int level;
	protected int mana;

	public Stuff(Stuff stuff) {
		super(stuff.id, stuff.sprite_id, stuff.itemType, stuff.name, stuff.quality, stuff.sellPrice, stuff.maxStack);
		this.gemBonusValue = stuff.gemBonusValue;
		this.gemBonusType = stuff.gemBonusType;
		this.classType = stuff.classType;
		this.critical = stuff.critical;
		this.strength = stuff.strength;
		this.stamina = stuff.stamina;
		this.color1 = stuff.color1;
		this.color2 = stuff.color2;
		this.color3 = stuff.color3;
		this.level = stuff.level;
		this.armor = stuff.armor;
		this.type = stuff.type;
		this.wear = stuff.wear;
		this.mana = stuff.mana;
	}
	
	public Stuff(StuffType type, ClassType[] classType, String sprite_id, int id, String name, int quality, GemColor color1, GemColor color2, GemColor color3, GemBonusType gemBonusType, int gemBonusValue, int level, Wear wear, int critical, int strength, int stamina, int armor, int mana, int sellPrice) {
		super(id, sprite_id, ItemType.STUFF, name, quality, sellPrice, 1);
		this.gemBonusValue = gemBonusValue;
		this.gemBonusType = gemBonusType;
		this.classType = classType;
		this.critical = critical;
		this.strength = strength;
		this.stamina = stamina;
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
		this.level = level;
		this.armor = armor;
		this.type = type;
		this.wear = wear;
		this.mana = mana;
	}

	public Stuff(Stuff weapon, int i) { //weapon constructor
		super(weapon.id, weapon.sprite_id, weapon.itemType, weapon.name, weapon.quality, weapon.sellPrice, weapon.maxStack);
		this.weaponType = weapon.weaponType;
		this.weaponSlot = weapon.weaponSlot;
		this.classType = weapon.classType;
		this.critical = weapon.critical;
		this.strength = weapon.strength;
		this.stamina = weapon.stamina;
		this.color1 = weapon.color1;
		this.color2 = weapon.color2;
		this.color3 = weapon.color3;
		this.level = weapon.level;
		this.armor = weapon.armor;
		this.mana = weapon.mana;
	}
	
	public Stuff(int id, String name, String sprite_id, ClassType[] classType, WeaponType weaponType, WeaponSlot weaponSlot, int quality, GemColor color1, GemColor color2, GemColor color3, int level, int armor, int stamina, int mana, int critical, int strength, int sellPrice) {
		super(id, sprite_id, ItemType.WEAPON, name, quality, sellPrice, 1);
		this.weaponType = weaponType;
		this.weaponSlot = weaponSlot;
		this.classType = classType;
		this.critical = critical;
		this.strength = strength;
		this.stamina = stamina;
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
		this.level = level;
		this.armor = armor;
		this.mana = mana;
	}

	public boolean canWearWeapon() {
		int i = 0;
		while(i < Mideas.joueur1().getWeaponType().length) {
			if(Mideas.joueur1().getweaponType(i) == this.weaponType) {
				return true;
			}
			i++;
		}
		return false;
	}
	
	public boolean checkBonusTypeActivated() {
		if(this.color1 != GemColor.NONE || this.color2 != GemColor.NONE || this.color3 != GemColor.NONE) {
			if(this.equippedGem1 != null && isBonusActivated(this.equippedGem1.getColor(), this.color1)) {
				if(this.color2 != GemColor.NONE) {
					if(this.equippedGem2 != null && isBonusActivated(this.equippedGem2.getColor(), this.color2)) {
						if(this.color3 != GemColor.NONE) {
							if(this.equippedGem3 != null && isBonusActivated(this.equippedGem3.getColor(), this.color3)) {
								this.gemBonusActivated = true;
								return true;
							}
						}
						else {
							this.gemBonusActivated = true;
							return true;
						}
					}
				}
				else {
					this.gemBonusActivated = true;
					return true;
				}
			}
		}
		this.gemBonusActivated = false;
		return false;
	}
	
	public boolean getGemBonusActivated() {
		return this.gemBonusActivated;
	}
	
	public WeaponSlot getWeaponSlot() {
		return this.weaponSlot;
	}

	public WeaponType getWeaponType() {
		return this.weaponType;
	}
	
	public GemColor getGemSlot1() {
		return this.color1;
	}
	
	public GemColor getGemSlot2() {
		return this.color2;
	}
	
	public GemColor getGemSlot3() {
		return this.color3;
	}
	
	public GemBonusType getGemBonusType() {
		return this.gemBonusType;
	}
	
	public int getGemBonusValue() {
		return this.gemBonusValue;
	}
	public void setEquippedGem1(Gem gem) {
		this.equippedGem1 = gem;
	}
	
	public void setEquippedGem2(Gem gem) {
		this.equippedGem2 = gem;
	}
	
	public void setEquippedGem3(Gem gem) {
		this.equippedGem3 = gem;
	}
	
	public Gem getEquippedGem1() {
		return this.equippedGem1;
	}
	
	public Gem getEquippedGem2() {
		return this.equippedGem2;
	}
	
	public Gem getEquippedGem3() {
		return this.equippedGem3;
	}
	
	public Texture getFreeSlotGemSprite1() {
		return convColor(this.color1);
	}
	
	public Texture getFreeSlotGemSprite2() {
		return convColor(this.color2);
	}
	
	public Texture getFreeSlotGemSprite3() {
		return convColor(this.color3);
	}
	
	public boolean isBonusActivated(GemColor gemColor, GemColor slotColor) {
		if(gemColor == GemColor.BLUE && slotColor == GemColor.BLUE) {
			return true;
		}
		if(gemColor == GemColor.YELLOW && slotColor == GemColor.YELLOW)  {
			return true;
		}
		if(gemColor == GemColor.RED && slotColor == GemColor.RED) {
			return true;
		}
		if(gemColor == GemColor.GREEN && (slotColor == GemColor.BLUE || slotColor == GemColor.YELLOW)) {
			return true;
		}
		if(gemColor == GemColor.ORANGE && (slotColor == GemColor.RED || slotColor == GemColor.YELLOW)) {
			return true;
		}
		if(gemColor == GemColor.PURPLE && (slotColor == GemColor.RED || slotColor == GemColor.BLUE)) {
			return true;
		}
		return false;
	}
	
 	public Texture convColor(GemColor color) {
		if(color == GemColor.BLUE) {
			return Sprites.gem_blue;
		}
		if(color == GemColor.RED) {
			return Sprites.gem_red;
		}
		if(color == GemColor.YELLOW) {
			return Sprites.gem_yellow;
		}
		if(color == GemColor.META) {
			return Sprites.gem_meta;
		}
		return null;
	}
	
	public String convTypeToString() {
		if(this.weaponType == WeaponType.BOW) {
			return "Bow";
		}
		if(this.weaponType == WeaponType.CROSSBOW) {
			return "Crossbow";
		}
		if(this.weaponType == WeaponType.DAGGER) {
			return "Dagger";
		}
		if(this.weaponType == WeaponType.FISTWEAPON) {
			return "Fist weapon";
		}
		if(this.weaponType == WeaponType.GUN) {
			return "Gun";
		}
		if(this.weaponType == WeaponType.ONEHANDEDAXE) {
			return "One handed axe";
		}
		if(this.weaponType == WeaponType.ONEHANDEDMACE) {
			return "One handed mace";
		}
		if(this.weaponType == WeaponType.ONEHANDEDSWORD) {
			return "One handed sword";
		}
		if(this.weaponType == WeaponType.POLEARM) {
			return "Polearm";
		}
		if(this.weaponType == WeaponType.STAFF) {
			return "Staff";
		}
		if(this.weaponType == WeaponType.THROWN) {
			return "Thrown";
		}
		if(this.weaponType == WeaponType.TWOHANDEDAXE) {
			return "Two handed axe";
		}
		if(this.weaponType == WeaponType.TWOHANDEDMACE) {
			return "Two handed mace";
		}
		if(this.weaponType == WeaponType.TWOHANDEDSWORD) {
			return "Two handed sword";
		}
		if(this.weaponType == WeaponType.WAND) {
			return "Wand";
		}
		return "";
	}
	
	public int getCritical() {
		return this.critical;
	}
	
	public int getStrength() {
		return this.strength;
	}
	
	public int getStamina() {
		return this.stamina;
	}
	
	public int getArmor() {
		return this.armor;
	}
	
	public int getMana() {
		return this.mana;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public Wear getWear() {
		return this.wear;
	}
	
	public StuffType getType() {
		return this.type;
	}
	
	public ClassType[] getClassType() {
		return this.classType;
	}
	
	public ClassType getClassType(int i) {
		return this.classType[i];
	}
	
	public String convStuffTypeToString() {
		if(this.type == StuffType.HEAD) {
			return "Head";
		}
		if(this.type == StuffType.NECKLACE) {
			return "Necklace";
		}
		if(this.type == StuffType.SHOULDERS) {
			return "Shoulders";
		}
		if(this.type == StuffType.BACK) {
			return "Back";
		}
		if(this.type == StuffType.CHEST) {
			return "Chest";
		}
		if(this.type == StuffType.WRISTS) {
			return "Wrists";
		}
		if(this.type == StuffType.GLOVES) {
			return "Gloves";
		}
		if(this.type == StuffType.BELT) {
			return "Belt";
		}
		if(this.type == StuffType.LEGGINGS) {
			return "Leggings";
		}
		if(this.type == StuffType.BOOTS) {
			return "Boots";
		}
		if(this.type == StuffType.RING) {
			return "Ring";
		}
		if(this.type == StuffType.TRINKET) {
			return "Trinket";
		}
		if(this.type == StuffType.MAINHAND) {
			return "MainHand";
		}
		if(this.type == StuffType.OFFHAND) {
			return "OffHand";
		}
		return "Ranged";
	}
	
	public String convClassTypeToString(int i) {
		if(i < this.classType.length) {
			if(this.classType[i] == ClassType.DEATHKNIGHT) {
				return "DeathKnight";
			}
			if(this.classType[i] == ClassType.GUERRIER) {
				return "Warrior";
			}
			if(this.classType[i] == ClassType.HUNTER) {
				return "Hunter";
			}
			if(this.classType[i] == ClassType.MAGE) {
				return "Mage";
			}
			if(this.classType[i] == ClassType.MONK) {
				return "MonK";
			}
			if(this.classType[i] == ClassType.PALADIN) {
				return "Paladin";
			}
			if(this.classType[i] == ClassType.PRIEST) {
				return "Priest";
			}
			if(this.classType[i] == ClassType.ROGUE) {
				return "Rogue";
			}
			if(this.classType[i] == ClassType.SHAMAN) {
				return "Shaman";
			}
			return "Warlock";
		}
		return null;
	}
	
	public String convWearToString() {
		if(this.wear == Wear.CLOTH) {
			return "Cloth";
		}
		if(this.wear == Wear.LEATHER) {
			return "Leather";
		}
		if(this.wear == Wear.MAIL) {
			return "Mail";
		}
		if(this.wear == Wear.PLATE) {
			return "Plate";
		}
		return "";
	}
	
	public boolean canEquipTo(ClassType type) {
		int i = 0;
		while(i < this.classType.length) {
			if(type == this.classType[i]) {
				return true;
			}
			i++;
		}
		return false;
	}
	
	public String convSlotToString() {
		if(this.weaponSlot == WeaponSlot.MAINHAND) {
			return "Main Hand";
		}
		if(this.weaponSlot == WeaponSlot.OFFHAND) {
			return "Off Hand";
		}
		if(this.weaponSlot == WeaponSlot.RANGED) {
			return "Ranged";
		}
 		return "";
 	}
	
	public String convWeaponTypeToString() {
		if(this.weaponType == WeaponType.BOW) {
			return "Bow";
		}
		if(this.weaponType == WeaponType.CROSSBOW) {
			return "Crossbow";
		}
		if(this.weaponType == WeaponType.DAGGER) {
			return "Dagger";
		}
		if(this.weaponType == WeaponType.FISTWEAPON) {
			return "Fist weapon";
		}
		if(this.weaponType == WeaponType.GUN) {
			return "Gun";
		}
		if(this.weaponType == WeaponType.ONEHANDEDAXE) {
			return "One handed axe";
		}
		if(this.weaponType == WeaponType.ONEHANDEDMACE) {
			return "One handed mace";
		}
		if(this.weaponType == WeaponType.ONEHANDEDSWORD) {
			return "One handed sword";
		}
		if(this.weaponType == WeaponType.POLEARM) {
			return "Polearm";
		}
		if(this.weaponType == WeaponType.STAFF) {
			return "Staff";
		}
		if(this.weaponType == WeaponType.THROWN) {
			return "Thrown";
		}
		if(this.weaponType == WeaponType.TWOHANDEDAXE) {
			return "Two handed axe";
		}
		if(this.weaponType == WeaponType.TWOHANDEDMACE) {
			return "Two handed mace";
		}
		if(this.weaponType == WeaponType.TWOHANDEDSWORD) {
			return "Two handed sword";
		}
		if(this.weaponType == WeaponType.WAND) {
			return "Wand";
		}
		return "";
	}
	
	public boolean isHead() {
		return this.type == StuffType.HEAD;
	}
	
	public boolean isNecklace() {
		return this.type == StuffType.NECKLACE;
	}
	
	public boolean isShoulders() {
		return this.type == StuffType.SHOULDERS;
	}
	
	public boolean isChest() {
		return this.type == StuffType.CHEST;
	}
	
	public boolean isBack() {
		return this.type == StuffType.BACK;
	}
	
	public boolean isWrists() {
		return this.type == StuffType.WRISTS;
	}
	
	public boolean isGloves() {
		return this.type == StuffType.GLOVES;
	}
	
	public boolean isBelt() {
		return this.type == StuffType.BELT;
	}
	
	public boolean isLeggings() {
		return this.type == StuffType.LEGGINGS;
	}
	
	public boolean isBoots() {
		return this.type == StuffType.BOOTS;
	}
	
	public boolean isRing() {
		return this.type == StuffType.RING;
	}
	
	public boolean isTrinket() {
		return this.type == StuffType.TRINKET;
	}
	
	public boolean isMainHand() {
		return this.type == StuffType.MAINHAND;
	}
	
	public boolean isOffHand() {
		return this.type == StuffType.OFFHAND;
	}
	
	public boolean isRanged() {
		return this.type == StuffType.RANGED;
	}
	
	public boolean equals(Stuff item) {
		return item != null && item.getId() == this.id;
	}
	
	public int getPrice() {
		return this.price;
	}
}
