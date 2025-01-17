package com.mideas.rpg.v2.game.unit;

import java.util.ArrayList;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.chat.ChatFrame;
import com.mideas.rpg.v2.chat.Message;
import com.mideas.rpg.v2.chat.MessageType;
import com.mideas.rpg.v2.game.CharacterStuff;
import com.mideas.rpg.v2.game.Party;
import com.mideas.rpg.v2.game.SocialFrameMenu;
import com.mideas.rpg.v2.game.auction.AuctionHouse;
import com.mideas.rpg.v2.game.classes.Wear;
import com.mideas.rpg.v2.game.guild.Guild;
import com.mideas.rpg.v2.game.guild.GuildRank;
import com.mideas.rpg.v2.game.item.Item;
import com.mideas.rpg.v2.game.item.ItemType;
import com.mideas.rpg.v2.game.item.bag.Bag;
import com.mideas.rpg.v2.game.item.container.ContainerManager;
import com.mideas.rpg.v2.game.item.gem.Gem;
import com.mideas.rpg.v2.game.item.gem.GemManager;
import com.mideas.rpg.v2.game.item.stuff.Stuff;
import com.mideas.rpg.v2.game.item.stuff.StuffManager;
import com.mideas.rpg.v2.game.item.weapon.WeaponManager;
import com.mideas.rpg.v2.game.item.weapon.WeaponType;
import com.mideas.rpg.v2.game.preamade_group.PremadeGroup;
import com.mideas.rpg.v2.game.profession.Profession;
import com.mideas.rpg.v2.game.profession.ProfessionManager;
import com.mideas.rpg.v2.game.race.Race;
import com.mideas.rpg.v2.game.shortcut.Shortcut;
import com.mideas.rpg.v2.game.social.Friend;
import com.mideas.rpg.v2.game.social.Ignore;
import com.mideas.rpg.v2.hud.LogChat;
import com.mideas.rpg.v2.hud.PartyFrame;
import com.mideas.rpg.v2.hud.SpellBarFrame;
import com.mideas.rpg.v2.hud.social.SocialFrame;
import com.mideas.rpg.v2.hud.social.friends.FriendsFrame;

public class Joueur extends Unit {

	public final static int MAXIMUM_AMOUNT_IGNORES = 50; 
	public final static int MAXIMUM_AMOUNT_FRIENDS = 50; 
	private ArrayList<Friend> friendList;
	private ArrayList<Ignore> ignoreList;
	private Profession secondProfession;
	private Profession firstProfession;
	private final AuctionHouse auctionHouse;
	private WeaponType[] weaponType;
	private ArrayList<Integer> spellUnlockedList;
	private PremadeGroup premadeGroup;
	private int numberYellowGem;
	private GuildRank guildRank;
	private Bag bag = new Bag();
	private long GCDStartTimer;
	private int numberBlueGem;
	private String guildTitle;
	private int defaultArmor;
	private long GCDEndTimer;
	private int numberRedGem;
	private Stuff[] stuff;
	private int critical;
	private int strength;
	private Unit target;
	private Party party;
	private int baseExp;
	private float armor;
	private Guild guild;
	private Wear wear;
	private long gold;
	private int copperPiece;
	private String copperPieceString;
	private int silverPiece;
	private String silverPieceString;
	private int goldPiece;
	private String goldPieceString;
	private long exp;

	private final static String warrior = "Guerrier";
	private final static String hunter = "Hunter";
	private final static String mage = "Mage";
	private final static String paladin = "Paladin";
	private final static String priest = "Priest";
	private final static String rogue = "Rogue";
	private final static String shaman = "Shaman";
	private final static String warlock = "Warlock";
	private final static String druid = "Druid";
	
	public Joueur(ClassType classType, int id, String name, Wear wear, WeaponType[] weaponType, int stamina, int mana, int strength, int armor, int defaultArmor, int critical, int maxStamina, int maxMana) {
		super(id, stamina, maxStamina, mana, maxMana, 1, "", classType);
		this.strength = strength;
		this.armor = armor;
		this.defaultArmor = defaultArmor;
		this.name = name;
		this.critical = critical;
		this.spellUnlockedList = new ArrayList<Integer>();
		this.weaponType = weaponType;
		this.stuff = new Stuff[19];
		this.wear = wear;
		this.firstProfession = ProfessionManager.getProfession(0);
		this.auctionHouse = new AuctionHouse();
		//this.classString = convClassTypeToString(this.classType);
		this.friendList = new ArrayList<Friend>();
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.friendList.add(new Friend(5, "Test"));
		this.ignoreList = new ArrayList<Ignore>();
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "Test"));
		this.ignoreList.add(new Ignore(1, "THEEND"));
	}
	
	public Joueur(Joueur joueur) {
		super(joueur.id, joueur.stamina, joueur.maxStamina, joueur.mana, joueur.maxMana, joueur.level, joueur.name, joueur.classType);
		this.spellUnlockedList = joueur.spellUnlockedList;
		this.auctionHouse = joueur.auctionHouse;
		this.defaultArmor = joueur.defaultArmor;
		this.classString = joueur.classString;
		this.weaponType = joueur.weaponType;
		this.friendList = joueur.friendList;
		this.ignoreList = joueur.ignoreList;
		this.strength = joueur.strength;
		this.critical = joueur.critical;
		this.stuff = joueur.stuff;
		this.armor = joueur.armor;
		this.mana = joueur.mana;
		this.wear = joueur.wear;
	}
	
	/*public void tick() throws SQLException {
		if(Mideas.getCurrentPlayer()) {
			attack(this.target);
			SpellBarFrame.setIsCastingSpell(false);
		}
	}
	
	public boolean cast(Spell spell) throws SQLException {
		if(spell.getType() == SpellType.DAMAGE) {
			SpellBarFrame.setIsCastingSpell(false);
			if(!spell.hasMana()) {
				attack(Mideas.target());
			}
			else {
				spell.cast(Mideas.target(), Mideas.joueur1(), spell);
				//LogChat.setStatusText("Le joueur 1 a enlevée "+spell.getDamage()+" hp au "+Mideas.target().getClasse()+", "+Mideas.target().getStamina()+" hp restant");
				return true;
			}
		}
		else if(spell.getType() == SpellType.HEAL) {
			SpellBarFrame.setIsCastingSpell(false);
			if(!spell.hasMana()) {
				attack(Mideas.target());
			}
			else {
				if(Mideas.joueur1().getStamina()+spell.getHeal() >= Mideas.joueur1().getMaxStamina()) {
					int diff = Mideas.joueur1().getMaxStamina()-Mideas.joueur1().getStamina();
					spell.healMax(Mideas.joueur1(), spell);
					LogChat.setStatusText("Vous vous êtes rendu "+diff+" hp, vous avez maintenant "+Mideas.joueur1().getStamina()+" hp");	
					return true;
				}
				else {
					spell.heal(Mideas.joueur1(), spell);
					LogChat.setStatusText("Vous vous êtes rendu "+spell.getHeal()+" hp, vous avez maintenant "+Mideas.joueur1().getStamina()+" hp");
					return true;
				}
			}
		}
		return false;
	}*/
	
	/*public void attack(Joueur joueur) throws SQLException {
		double damage = Mideas.joueur1().getStrength()*ThreadLocalRandom.current().nextDouble(.9, 1.1);
		SpellBarFrame.setIsCastingSpell(false);
		if(Math.random() < this.critical/100.) {
			damage*= 2;
		}
		joueur.setStamina(joueur.getStamina()-damage);
		//LogChat.setStatusText("Le joueur 1 a enlevé "+Math.round(damage)+" hp au "+joueur.getClasse()+", "+joueur.getStamina()+" hp restant"); //and "+Mideas.joueur2.getMana()+" mana left");
		if(Mideas.joueur1().getStamina() <= 0) {
			LogChat.setStatusText("Le joueur 2 a gagné !");
			LogChat.setStatusText2(""); 
			y = 1;
			return;
		}
		else if(Mideas.target().getStamina() <= 0) {
			LogChat.setStatusText("Le joueur 1 a gagné !");
			LogChat.setStatusText2("");
			z = 1;
			return;
		}
	}*/
	
	/*public void attackUI(Spell spell) {
		double damage = Mideas.joueur2().getStrength()*ThreadLocalRandom.current().nextDouble(.9, 1.1);
		float rand = (float)Math.random();
		if(rand < Mideas.joueur2().getCritical()/100.) {
			damage*= 2;
		}
		if(spell.getType() == SpellType.HEAL && spell.hasMana()) {
			if(Mideas.joueur2().getStamina() < Mideas.joueur2().getMaxStamina()) {
				if(Mideas.joueur2().getStamina()+spell.getHeal() >= Mideas.joueur2().getMaxStamina()) {
					int diff = Mideas.joueur2().getMaxStamina()-Mideas.joueur2().getStamina();
					spell.healMax(Mideas.joueur2(), spell);
					LogChat.setStatusText2("Le joueur2 s'est rendu "+diff+" hp, il a maintenant "+Mideas.joueur2().getStamina()+" hp");
				}
				else {
					spell.heal(Mideas.joueur2(), spell);
					LogChat.setStatusText2("Le joueur2 s'est rendu "+spell.getHeal()+" hp, il a maintenant "+Mideas.joueur2().getStamina()+" hp");	
				}
			}
		}
		else if(spell.getType() == SpellType.DAMAGE) {
			Spell cast = Spell.getRandomSpell();
			if(rand > .2 && rand <= .4 && cast.hasMana()) {	
				cast.cast(Mideas.joueur1(), Mideas.joueur2(), spell);
				LogChat.setStatusText2("Le joueur 2 a enlevée "+cast.getDamage()+" hp au "+Mideas.joueur1().getClasse()+", "+Mideas.joueur1().getStamina()+" hp restant");
			}
			else {
				Mideas.joueur1().setStamina(Mideas.joueur1().getStamina()-damage);
				LogChat.setStatusText2("Le joueur 2 a enlevée "+Math.round(damage)+" hp au "+Mideas.joueur1().getClasse()+", "+Mideas.joueur1().getStamina()+" hp restant");	
			}
		}
	}*/
	
	/*public void loadStuff() {
		int i = 0;
		Interface.setStuffFullyLoaded(true);
		while(i < Mideas.joueur1().getStuff().length) {
			if(Mideas.joueur1().getStuff(i) != null && !Mideas.joueur1().getStuff(i).getIsLoaded()) {
				if(StuffManager.exists(Mideas.joueur1().getStuff(i).getId())) {
					Gem gem1 = Mideas.joueur1().getStuff(i).getEquippedGem(0);
					Gem gem2 = Mideas.joueur1().getStuff(i).getEquippedGem(1);
					Gem gem3 = Mideas.joueur1().getStuff(i).getEquippedGem(2);
					Mideas.joueur1().setStuff(i, StuffManager.getClone(Mideas.joueur1().getStuff(i).getId()));
					Mideas.joueur1().getStuff(i).setEquippedGem(0, gem1);
					Mideas.joueur1().getStuff(i).setEquippedGem(1, gem2);
					Mideas.joueur1().getStuff(i).setEquippedGem(2, gem3);
					ConnectionManager.getItemRequested().remove(Mideas.joueur1().getStuff(i).getId());
					Mideas.joueur1().getStuff(i).setIsLoaded(true);
					i++;
					continue;
				}
				else if(WeaponManager.exists(Mideas.joueur1().getStuff(i).getId())) {
					Gem gem1 = Mideas.joueur1().getStuff(i).getEquippedGem(0);
					Gem gem2 = Mideas.joueur1().getStuff(i).getEquippedGem(1);
					Gem gem3 = Mideas.joueur1().getStuff(i).getEquippedGem(2);
					Mideas.joueur1().setStuff(i, WeaponManager.getClone(Mideas.joueur1().getStuff(i).getId()));
					Mideas.joueur1().getStuff(i).setEquippedGem(0, gem1);
					Mideas.joueur1().getStuff(i).setEquippedGem(1, gem2);
					Mideas.joueur1().getStuff(i).setEquippedGem(2, gem3);
					ConnectionManager.getItemRequested().remove(Mideas.joueur1().getStuff(i).getId());
					Mideas.joueur1().getStuff(i).setIsLoaded(true);
					i++;
					continue;
				}
				Interface.setStuffFullyLoaded(false);
			}
			else if(Mideas.joueur1().getStuff(i) != null && !checkGemLoaded(Mideas.joueur1().getStuff(i))) {
				int j = 0;
				while(j < Mideas.joueur1().getStuff(i).getEquippedGems().length) {
					if(Mideas.joueur1().getStuff(i).getEquippedGem(j) != null  && !Mideas.joueur1().getStuff(i).getEquippedGem(j).getIsLoaded()) {
						if(GemManager.exists(Mideas.joueur1().getStuff(i).getEquippedGem(j).getId())) {
							Mideas.joueur1().getStuff(i).setEquippedGem(j, GemManager.getClone(Mideas.joueur1().getStuff(i).getEquippedGem(j).getId()));
						}
					}
					else {
						Interface.setStuffFullyLoaded(false);
					}
					j++;
				}
 			}
			i++;
		}
	}*/
	
	/*private static boolean checkGemLoaded(Stuff stuff) {
		int i = 0;
		while(i < stuff.getEquippedGems().length) {
			if(stuff.getEquippedGem(i) != null && !stuff.getEquippedGem(i).getIsLoaded()) {
				return false;
			}
			i++;
		}
		return true;
	}*/
	
	/*public void loadBag() {
		int i = 0;
		Interface.setBagFullyLoaded(true);
		while(i < this.bag.getBag().length) {
			if(this.bag.getBag(i) != null && !this.bag.getBag(i).getIsLoaded()) {
				if(StuffManager.exists(this.bag.getBag(i).getId())) {
					Gem gem1 = ((Stuff)this.bag.getBag(i)).getEquippedGem(0);
					Gem gem2 = ((Stuff)this.bag.getBag(i)).getEquippedGem(1);
					Gem gem3 = ((Stuff)this.bag.getBag(i)).getEquippedGem(2);
					this.bag.setBag(i, StuffManager.getClone(this.bag.getBag(i).getId()));
					((Stuff)this.bag.getBag(i)).setEquippedGem(0, gem1);
					((Stuff)this.bag.getBag(i)).setEquippedGem(1, gem2);
					((Stuff)this.bag.getBag(i)).setEquippedGem(2, gem3);
					ConnectionManager.getItemRequested().remove(this.bag.getBag(i).getId());
					this.bag.getBag(i).setIsLoaded(true);
				}
				else if(WeaponManager.exists(this.bag.getBag(i).getId())) {
					Gem gem1 = ((Stuff)this.bag.getBag(i)).getEquippedGem(0);
					Gem gem2 = ((Stuff)this.bag.getBag(i)).getEquippedGem(1);
					Gem gem3 = ((Stuff)this.bag.getBag(i)).getEquippedGem(2);
					this.bag.setBag(i, WeaponManager.getClone(this.bag.getBag(i).getId()));
					((Stuff)this.bag.getBag(i)).setEquippedGem(0, gem1);
					((Stuff)this.bag.getBag(i)).setEquippedGem(1, gem2);
					((Stuff)this.bag.getBag(i)).setEquippedGem(2, gem3);
					ConnectionManager.getItemRequested().remove(this.bag.getBag(i).getId());
					this.bag.getBag(i).setIsLoaded(true);
				}
				else if(GemManager.exists(this.bag.getBag(i).getId())) {
					this.bag.setBag(i, GemManager.getClone(this.bag.getBag(i).getId()));
					ConnectionManager.getItemRequested().remove(this.bag.getBag(i).getId());
					this.bag.getBag(i).setIsLoaded(true);
				}
				else if(ContainerManager.exists(this.bag.getBag(i).getId())) {
					this.bag.setBag(i, ContainerManager.getClone(this.bag.getBag(i).getId()));
					ConnectionManager.getItemRequested().remove(this.bag.getBag(i).getId());
					this.bag.getBag(i).setIsLoaded(true);
				}
				else if(PotionManager.exists(this.bag.getBag(i).getId())) {
					int number = this.bag.getBag(i).getAmount();
					this.bag.setBag(i, PotionManager.getClone(this.bag.getBag(i).getId()), number);
					ConnectionManager.getItemRequested().remove(this.bag.getBag(i).getId());
					this.bag.getBag(i).setIsLoaded(true);
				}
				else {
					Interface.setBagFullyLoaded(false);
				}
			}
			if(this.bag.getBag(i) != null && (this.bag.getBag(i).isStuff() || this.bag.getBag(i).isWeapon())) {
				int j = 0;
				while(j < ((Stuff)this.bag.getBag(i)).getEquippedGems().length) {
					if(((Stuff)this.bag.getBag(i)).getEquippedGem(j) != null  && !((Stuff)this.bag.getBag(i)).getEquippedGem(j).getIsLoaded()) {
						if(GemManager.exists(((Stuff)this.bag.getBag(i)).getEquippedGem(j).getId())) {
							((Stuff)this.bag.getBag(i)).setEquippedGem(j, GemManager.getClone(((Stuff)this.bag.getBag(i)).getEquippedGem(j).getId()));
						}
						else {
							Interface.setBagFullyLoaded(false);
						}
					}
					j++;
				}
 			}
			i++;
		}
		if(Interface.getBagFullyLoaded()) {
			if(Mideas.joueur1().getFirstProfession() != null) {
				Mideas.joueur1().getFirstProfession().updateNumberPossibleCraft();
			}
			else if(Mideas.joueur1().getSecondProfession() != null) {
				Mideas.joueur1().getSecondProfession().updateNumberPossibleCraft();
			}
		}
	}*/
	
	/*public void loadSpellbar() {
		int i = 0;
		Interface.setSpellbarFullyLoaded(true);
		while(i < Mideas.joueur1().getSpells().length) {
			if(Mideas.joueur1().getSpells(i) != null && !Mideas.joueur1().getSpells(i).getIsLoaded()) {
				if(StuffManager.exists(Mideas.joueur1().getSpells(i).getId())) {
					Mideas.joueur1().setSpells(i, new StuffShortcut(StuffManager.getClone(Mideas.joueur1().getSpells(i).getId())));
					ConnectionManager.getItemRequested().remove(Mideas.joueur1().getSpells(i).getId());
					Mideas.joueur1().getSpells(i).setIsLoaded(true);
					i++;
					continue;
				}
				else if(WeaponManager.exists(Mideas.joueur1().getSpells(i).getId())) {
					Mideas.joueur1().setSpells(i, new StuffShortcut(WeaponManager.getClone(Mideas.joueur1().getSpells(i).getId())));
					ConnectionManager.getItemRequested().remove(Mideas.joueur1().getSpells(i).getId());
					Mideas.joueur1().getSpells(i).setIsLoaded(true);
					i++;
					continue;
				}
				else if(PotionManager.exists(Mideas.joueur1().getSpells(i).getId())) {
					Mideas.joueur1().setSpells(i, new PotionShortcut(PotionManager.getClone(Mideas.joueur1().getSpells(i).getId())));
					ConnectionManager.getItemRequested().remove(Mideas.joueur1().getSpells(i).getId());
					Mideas.joueur1().getSpells(i).setIsLoaded(true);
					i++;
					continue;
				}
				Interface.setSpellbarFullyLoaded(false);
			}
			i++;
		}
	}*/
	
	public boolean addItem(Item item, int amount) {
		if(amount == 1) {
			return addSingleItem(item, amount);
		}
		else if(amount > 1) {
			if(item.isStackable()) {
				return addSingleItem(item, amount);
			}
			return addMultipleUnstackableItem(item.getId(), amount);
		}
		return false;
	}
	
	private boolean addSingleItem(Item item, int amount) {
		int i = 0;
		if(!item.isStackable()) {
			while(i < this.bag.getBag().length && amount > 0) {
				if(this.bag.getBag(i) == null) {
					this.bag.setBag(i, item);
					amount --;
				}
				i++;
			}
			//CharacterStuff.setBagItems();
		}
		else {
			while(i < this.bag.getBag().length) {
				if(this.bag.getBag(i) != null && this.bag.getBag(i).equals(item)) {
					this.bag.setBag(i, item, this.bag.getBag(i).getAmount()+amount);
					//CharacterStuff.setBagItems();
					return true;
				}
				i++;
			}
			i = 0;
			while(i < this.bag.getBag().length) {
				if(this.bag.getBag(i) == null) {
					this.bag.setBag(i, item, amount);
					//CharacterStuff.setBagItems();
					return true;
				}
				i++;
			}
		}
		LogChat.setStatusText3("Votre inventaire est pleins");
		return false;
	}
	
	private boolean addMultipleUnstackableItem(int id, int number) {
		int i = 0;
		boolean returns = false;
		ItemType type = null;
		if(WeaponManager.exists(id) || StuffManager.exists(id) || GemManager.exists(id) || ContainerManager.exists(id)) {
			if(WeaponManager.exists(id)) {
				type = ItemType.WEAPON;
			}
			else if(StuffManager.exists(id)) {
				type = ItemType.STUFF;
			}
			else if(GemManager.exists(id)) {
				type = ItemType.GEM;
			}
			else if(ContainerManager.exists(id)) {
				type = ItemType.CONTAINER;
			}
			while(i < this.bag.getBag().length && number > 0) {
				if(this.bag.getBag(i) == null) {
					if(type == ItemType.WEAPON) {
						this.bag.setBag(i, WeaponManager.getClone(id));
					}
					else if(type == ItemType.STUFF) {
						this.bag.setBag(i, StuffManager.getClone(id));
					}
					else if(type == ItemType.GEM) {
						this.bag.setBag(i, GemManager.getClone(id));
					}
					else {
						this.bag.setBag(i, ContainerManager.getClone(id));
					}
					number--;
					returns = true;
				}
				i++;
			}
			//CharacterStuff.setBagItems();
		}
		return returns;
	}
	
	public void deleteItem(Item item, int amount) {
		int i = 0;
		if(!item.isStackable()) {
			while(i < this.bag.getBag().length && amount > 0) {
				if(this.bag.getBag(i) != null && this.bag.getBag(i).equals(item)) {
					this.bag.setBag(i, null);
					amount--;
				}
				i++;
			}
			//CharacterStuff.setBagItems();
		}
		else {
			while(i < this.bag.getBag().length && amount > 0) {
				if(this.bag.getBag(i) != null && this.bag.getBag(i).equals(item)) {
					int temp = amount;
					amount-= this.bag.getBag(i).getAmount();
					this.bag.setBag(i, this.bag.getBag(i), Math.max(0, this.bag.getBag(i).getAmount()-temp));
				}
				i++;
			}
			//CharacterStuff.setBagItems();
		}
	}
	
	public boolean canWear(Stuff stuff) {
		if(stuff != null) {
			if(this.wear == Wear.PLATE) {
				return true;
			}
			if(this.wear == Wear.MAIL) {
				if(stuff.getWear() == Wear.PLATE) {
					return false;
				}
				return true;
			}
			if(this.wear == Wear.LEATHER) {
				if(stuff.getWear() == Wear.PLATE || stuff.getWear() == Wear.MAIL) {
					return false;
				}
				return true;
			}
			if(this.wear == Wear.CLOTH) {
				if(stuff.getWear() == Wear.CLOTH || stuff.getWear() == Wear.NONE) {
					return true;
				}
				return false;
			}
		}
		return false;
	}

	public boolean canEquipStuff(Stuff stuff) {
		if(Mideas.joueur1().getLevel() >= stuff.getLevel() && canWear(stuff) && stuff.canEquipTo(Joueur.convStringToClassType(Mideas.joueur1().getClasseString()))) {
			return true;
		}
		return false;
	}
	
	public void addFriend(Friend friend) {
		if(this.friendList.size() < MAXIMUM_AMOUNT_FRIENDS) {
			this.friendList.add(friend);
			sortFriendList();
			ChatFrame.addMessage(new Message(friend.getName()+" is now in your friend list.", false, MessageType.SELF, true));
		}
	}
	
	public void addFriendNoSort(Friend friend) {
		if(this.friendList.size() < MAXIMUM_AMOUNT_FRIENDS) {
			this.friendList.add(friend);
		}
	}
	
	public void removeFriend(int id) {
		int i = 0;
		while(i < this.friendList.size()) {
			Friend friend = this.friendList.get(i);
			if(friend.getCharacterId() == id) {
				ChatFrame.addMessage(new Message(friend.getName()+" is no longer in your friend list.", false, MessageType.SELF, true));
				this.friendList.remove(i);
				if(this.friendList.size() == 0 || FriendsFrame.getSelectedFriend() == i) {
					FriendsFrame.resetSelectedFriend();
				}
				return;
			}
			i++;
		}
	}
	
	public Friend getFriend(int id) {
		int i = 0;
		while(i < this.friendList.size()) {
			Friend friend = this.friendList.get(i);
			if(friend.getCharacterId() == id) {
				return friend;
			}
			i++;
		}
		return null;
	}
	
	public void sortFriendList() {
		int i = 0;
		int j = 0;
		Friend temp;
		while(i < this.friendList.size()) {
			j = i;
			while(j < this.friendList.size()) {
				if((this.friendList.get(i).isOnline() && this.friendList.get(j).isOnline() && this.friendList.get(i).getName().compareTo(this.friendList.get(j).getName()) > 0) || (!this.friendList.get(i).isOnline() && !this.friendList.get(j).isOnline() && this.friendList.get(i).getName().compareTo(this.friendList.get(j).getName()) > 0) || (this.friendList.get(j).isOnline() && !this.friendList.get(i).isOnline())) {
					temp = this.friendList.get(j);
					this.friendList.set(j, this.friendList.get(i));
					this.friendList.set(i, temp);
				}
				j++;
			}
			i++;
		}
	}
	
	public ArrayList<Friend> getFriendList() {
		return this.friendList;
	}
	
	public void addIgnore(Ignore ignore) {
		if(this.ignoreList.size() < MAXIMUM_AMOUNT_IGNORES) {
			this.ignoreList.add(ignore);
			sortIgnoreList();
		}
	}
	
	public void addIgnoreNoSort(Ignore ignore) {
		if(this.ignoreList.size() < MAXIMUM_AMOUNT_IGNORES) {
			this.ignoreList.add(ignore);
		}
	}
	
	public void removeIgnore(int id) {
		int i = 0;
		while(i < this.ignoreList.size()) {
			if(this.ignoreList.get(i).getId() == id) {
				this.ignoreList.remove(i);
				return;
			}
			i++;
		}
	}
	
	public void sortIgnoreList() {
		int i = 0;
		int j = 0;
		Ignore temp;
		while(i < this.ignoreList.size()) {
			j = i;
			while(j < this.ignoreList.size()) {
				if(this.ignoreList.get(i).getName().compareTo(this.ignoreList.get(j).getName()) > 0) {
					temp = this.ignoreList.get(j);
					this.ignoreList.set(j, this.ignoreList.get(i));
					this.ignoreList.set(i, temp);
				}
				j++;
			}
			i++;
		}
	}
	
	public PremadeGroup getPremadeGroup()
	{
		return (this.premadeGroup);
	}
	
	public void setPremadeGroup(PremadeGroup group)
	{
		this.premadeGroup = group;
	}
	
	public ArrayList<Ignore> getIgnoreList() {
		return this.ignoreList;
	}
	
	public String getGuildTitle() {
		return this.guildTitle;
	}
	
	public GuildRank getGuildRank() {
		return this.guildRank;
	}
	
	public void setGuildRank(GuildRank guildRank) {
		this.guildRank = guildRank;
		this.guildTitle = guildRank.getName()+" of "+this.guild.getName();
	}
	
	public Guild getGuild() {
		return this.guild;
	}
	
	public void setGuild(Guild guild) {
		this.guild = guild;
		if(this.guild == null) {
			SocialFrame.setSelectedMenu(SocialFrameMenu.FRIEND_FRAME);
		}
	}
	
	public AuctionHouse getAuctionHouse() {
		return this.auctionHouse;
	}
	
	public void setParty(Party party) {
		if(party == null) {
			PartyFrame.setDisplayMember(-1);
		}
		this.party = party;
	}
	
	public boolean canInvitePlayerInParty(int id) {
		return id != this.id && ((this.party == null) || (this.party.isPartyLeader(this)));
	}
	
	public Party getParty() {
		return this.party;
	}
	
	public Unit getTarget() {
		return this.target;
	}
	
	public void setTarget(Unit target) {
		this.target = target;
	}
	
	public void setGCDStartTimer(long timer) {
		this.GCDStartTimer = timer;
	}
	
	public long getGCDStartTimer() {
		return this.GCDStartTimer;
	}
	
	public void setGCDEndTimer(long timer) {
		this.GCDEndTimer = timer;
	}
	
	public long getGCDEndTimer() {
		return this.GCDEndTimer;
	}
	
	public void setFirstProfession(Profession profession) {
		this.firstProfession = profession;
	}
	
	public Profession getFirstProfession() {
		return this.firstProfession;
	}
	
	public Profession getSecondProfession() {
		return this.secondProfession;
	}
	
	public void setSecondProfession(Profession profession) {
		this.secondProfession = profession;
	}
	
	public void setStuffArmor(int armor) {
		this.armor+= armor;
	}

	public void setStuffStrength(int strengh) {
		this.strength+= strengh;
	}
	
	public void setStuffStamina(int stamina) {
		this.maxStamina+= stamina;
		this.stamina+= stamina;
		this.hasHpChanged = true;
	}
	
	public void setStuffCritical(int critical) {
		this.critical+= critical;
	}
	
	public void setStuffMana(int mana) {
		this.maxMana+= mana;
		this.mana+= mana;
		this.hasManaChanged = true;
	}
	
	public void setNumberRedGem(int nb) {
		this.numberRedGem = nb;
	}
	
	public int getNumberRedGem() {
		return this.numberRedGem;
	}
	
	public void setNumberBlueGem(int nb) {
		this.numberBlueGem = nb;
	}
	
	public int getNumberBlueGem() {
		return this.numberBlueGem;
	}
	
	public void setNumberYellowGem(int nb) {
		this.numberYellowGem = nb;
	}
	
	public int getNumberYellowGem() {
		return this.numberYellowGem;
	}
	
	public float getArmor() {
		return this.armor;
	}
	
	public Wear getWear() {
		return this.wear;
	}
	
	public int getCritical() {
		return this.critical;
	}
	
	public int getStrength() {
		return this.strength;
	}

	public int getSpellsListSize() {
		return SpellBarFrame.getButtonList().length;
	}

	public Shortcut getSpells(int i) {
		return SpellBarFrame.getButton(i).getShortcut();
	}
	
	public void setSpells(int i, Shortcut spell) {
		SpellBarFrame.setShortcut(i, spell);
	}
	
	public ArrayList<Integer> getSpellUnlocked() {
		return this.spellUnlockedList;
	}
	
	public void addSpellUnlocked(int id) {
		this.spellUnlockedList.add(id);
	}
	
	public void removeSpellUnlocked(int id) {
		int i = this.spellUnlockedList.size();
		while(--i >= 0) {
			if(this.spellUnlockedList.get(i) == id) {
				this.spellUnlockedList.remove(i);
				return;
			}
		}
	}
	
	public Stuff[] getStuff() {
		return this.stuff;
	}
	
	public Stuff getStuff(int i) {
		return this.stuff[i];
	}
	
	public void setStuff(int i, Item tempItem) { //TODO : add stats calculation
		if(this.stuff[i] != null) {
			
		}
		if(tempItem == null) {
			this.stuff[i] = null;
		}
		else if(tempItem.isStuff() || tempItem.isWeapon()) {
			if(this.stuff[i] != null) {
				SpellBarFrame.updateEquippedItem(this.stuff[i].getId(), false);
			}
			this.stuff[i] = (Stuff)tempItem;
			SpellBarFrame.updateEquippedItem(this.stuff[i].getId(), true);
		}
		else {
			System.out.println("Error on setStuff, tried to wear an item that isn't a Stuff");
		}
	}
	
	public void updateStuff(int slot, Item item) {
		if(item == null) {
			System.out.println("Error updateStuff slot "+slot);
			return;
		}
		if(item.isStuff() || item.isWeapon()) {
			item.setIsLoaded(true);
			if(this.stuff[slot] == null) {
				this.stuff[slot] = (Stuff)item;
			}
			else {
				int i = 0; 
				while(i < this.stuff[slot].getEquippedGems().length) {
					((Stuff)item).setEquippedGem(i, this.stuff[slot].getEquippedGem(i));
					i++;
				}
				this.stuff[slot] = (Stuff)item;
			}
		}
	}
	
	public void updateStuffGem(int slot, int gemSlot, Item item) {
		if(item == null) {
			System.out.println("Error updateStuffGem slot "+slot);
			return;
		}
		if(item.isGem()) {
			item.setIsLoaded(true);
			if(this.stuff[slot] == null) {
				return;
			}
			this.stuff[slot].setEquippedGem(gemSlot, (Gem)item);
		}
	}
	
	public Bag bag() {
		return this.bag;
	}
	
	public long getExp() {
		return this.exp;
	}
	
	public int getBaseExp() {
		return this.baseExp;
	}
	
	public void setArmor(float number) {
		this.armor = (Math.round(100*(this.armor+number))/100.f);
	}
	
	public int getDefaultArmor() {
		return this.defaultArmor;
	}
	
	public long getGold() {
		return this.gold;
	}
	
	public void setGold(long gold) {
		this.gold = gold;
		calcGoldPiece();
		calcSilverPiece();
		calcCopperPiece();
	}
	
	private void calcGoldPiece() {
		this.goldPiece = (int)Math.floorDiv(this.gold, 10000);
		this.goldPieceString = String.valueOf(this.goldPiece);
	}
	
	private void calcSilverPiece() {
		this.silverPiece = (int)Math.floorDiv(this.gold % 10000, 100);
		this.silverPieceString = String.valueOf(this.silverPiece);
	}
	
	private void calcCopperPiece() {
		this.copperPiece = (int)this.gold % 100;
		this.copperPieceString = String.valueOf(this.copperPiece);
	}
	
	public static int getGoldPiece(int gold) {
		return Math.floorDiv(gold, 10000);
	}
	
	public static int getSilverPiece(int gold) {
		return Math.floorDiv(gold%10000, 100);
	}
	
	public static int getCopperPiece(int gold) {
		return gold%100;
	}
	
	public int getGoldPiece() {
		return this.goldPiece;
	}
	
	public String getGoldPieceString() {
		return this.goldPieceString;
	}
	
	public int getSilverPiece() {
		return this.silverPiece;
	}
	
	public String getSilverPieceString() {
		return this.silverPieceString;
	}
	
	public int getCopperPiece() {
		return this.copperPiece;
	}
	
	public String getCoppierPieceString() {
		return this.copperPieceString;
	}
	
	public void setExp(int baseExp, int expGained ) {
		this.exp = baseExp+expGained;
	}
	
	public void setExp(long exp) {
		this.exp = exp;
		this.level = Mideas.getLevel(this.exp);
	}
	
	public WeaponType[] getWeaponType() {
		return this.weaponType;
	}
	
	public WeaponType getweaponType(int i) {
		if(i < this.weaponType.length) {
			return this.weaponType[i];
		}
		return null;
	}
	
	public static String convClassTypeToString(ClassType type) {
		if(type == ClassType.DRUID) {
			return druid;
		}
		if(type == ClassType.GUERRIER) {
			return warrior;
		}
		if(type == ClassType.HUNTER) {
			return hunter;
		}
		if(type == ClassType.MAGE) {
			return mage;
		}
		if(type == ClassType.PALADIN) {
			return paladin;
		}
		if(type == ClassType.PRIEST) {
			return priest;
		}
		if(type == ClassType.ROGUE) {
			return rogue;
		}
		if(type == ClassType.SHAMAN) {
			return shaman;
		}
		if(type == ClassType.WARLOCK) {
			return warlock;
		}
		return null;
	}
	
	public static ClassType convStringToClassType(String classType) {
		if(classType.equals(warrior)) {
			return ClassType.GUERRIER;
		}
		if(classType.equals(druid)) {
			return ClassType.DRUID;
		}
		if(classType.equals(hunter)) {
			return ClassType.HUNTER;
		}
		if(classType.equals(mage)) {
			return ClassType.MAGE;
		}
		if(classType.equals(paladin)) {
			return ClassType.PALADIN;
		}
		if(classType.equals(priest)) {
			return ClassType.PRIEST;
		}
		if(classType.equals(rogue)) {
			return ClassType.ROGUE;
		}
		if(classType.equals(shaman)) {
			return ClassType.SHAMAN;
		}
		if(classType.equals(warlock)) {
			return ClassType.WARLOCK;
		}
		return null;
	}
	
	public static String convRaceToString(Race race) {
		if(race == Race.BLOODELF) {
			return "Bloodelf";
		}
		if(race == Race.DRAENEI) {
			return "Draenei";
		}
		if(race == Race.DWARF) {
			return "Dwarf";
		}
		if(race == Race.GNOME) {
			return "Gnome";
		}
		if(race == Race.HUMAN) {
			return "Human";
		}
		if(race == Race.NIGHTELF) {
			return "Nightelf";
		}
		if(race == Race.ORC) {
			return "Orc";
		}
		if(race == Race.TAUREN) {
			return "Tauren";
		}
		if(race == Race.TROLL) {
			return "Troll";
		}
		if(race == Race.UNDEAD) {
			return "Undead";
		}
		return null;
	}
} 

