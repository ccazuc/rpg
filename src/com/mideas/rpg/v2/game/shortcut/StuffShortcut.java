package com.mideas.rpg.v2.game.shortcut;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.mideas.rpg.v2.utils.Texture;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.game.CharacterStuff;
import com.mideas.rpg.v2.game.IconsManager;
import com.mideas.rpg.v2.game.item.Item;
import com.mideas.rpg.v2.game.item.ItemType;
import com.mideas.rpg.v2.game.item.stuff.Stuff;
import com.mideas.rpg.v2.hud.DragManager;
import com.mideas.rpg.v2.hud.DragSpellManager;

public class StuffShortcut implements Shortcut {

	private Stuff stuff;
	private ShortcutType type;

	public StuffShortcut(Stuff stuff) {
		this.stuff = stuff;
		this.type = ShortcutType.STUFF;
	}
	
	@Override
	public boolean use(Shortcut shortcut) throws FileNotFoundException, SQLException {
		int i = 0;
		if(DragManager.getDraggedItem() == null && DragSpellManager.getDraggedSpell() == null && DragSpellManager.getDraggedSpellBook() == null) {
			while(i < Mideas.joueur1().getStuff().length) {
				if(this.stuff.getItemType() == ItemType.STUFF) {
					if(this.stuff != null && this.stuff.getType() == DragManager.getStuffType(i) && this.stuff.canEquipTo(DragManager.convClassType()) && Mideas.joueur1().canWear(this.stuff)) {
						if(Mideas.getLevel() >= this.stuff.getLevel()) {
							if(Mideas.joueur1().getStuff(i) == null) {
								Mideas.joueur1().setStuff(i, this.stuff);
								DragManager.calcStats(Mideas.joueur1().getStuff(i));
								DragManager.setNullContainer(Mideas.bag().getBag(i));
								CharacterStuff.setBagItems();
								CharacterStuff.setEquippedItems();
								break;
							}
							else {
								Item tempItem = Mideas.joueur1().getStuff(i);
								DragManager.calcStatsLess(tempItem);
								Mideas.joueur1().setStuff(i, this.stuff);
								DragManager.calcStats(Mideas.joueur1().getStuff(i));
								Mideas.bag().setBag(DragManager.checkItemSlot(this.stuff), tempItem);
								CharacterStuff.setBagItems();
								CharacterStuff.setEquippedItems();
								break;
							}
						}
					}
				}
				else if(this.stuff.getItemType() == ItemType.WEAPON) {
					if(this.stuff != null && this.stuff.getWeaponSlot() == DragManager.getWeaponSlot(i) && this.stuff.canEquipTo(DragManager.convClassType())) {
						if(Mideas.getLevel() >= this.stuff.getLevel()) {
							if(Mideas.joueur1().getStuff(i) == null) {
								Mideas.joueur1().setStuff(i, this.stuff);
								DragManager.calcStats(Mideas.joueur1().getStuff(i));
								DragManager.setNullContainer(Mideas.bag().getBag(i));
								CharacterStuff.setBagItems();
								CharacterStuff.setEquippedItems();
								break;
							}
							else {
								Item tempItem = Mideas.joueur1().getStuff(i);
								DragManager.calcStatsLess(tempItem);
								Mideas.joueur1().setStuff(i, this.stuff);
								DragManager.calcStats(Mideas.joueur1().getStuff(i));
								Mideas.bag().setBag(DragManager.checkItemSlot(this.stuff), tempItem);
								CharacterStuff.setBagItems();
								CharacterStuff.setEquippedItems();
								break;
							}
						}
					}
				}
				i++;
			}
		}
		return false;
	}
	
	@Override
	public int getId() {
		return this.stuff.getId();
	}
	
	@Override
	public Texture getSprite() {
		return IconsManager.getSprite37(this.stuff.getSpriteId());
	}

	@Override
	public void setCd(int id, int cd) {
	}
	
	public Stuff getStuff() {
		return this.stuff;
	}
	
	@Override
	public ShortcutType getShortcutType() {
		return this.type;
	}
}
