package com.mideas.rpg.v2.game.spell;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.game.item.Item;
import com.mideas.rpg.v2.game.item.ItemType;
import com.mideas.rpg.v2.game.item.potion.Potion;
import com.mideas.rpg.v2.game.item.potion.PotionManager;
import com.mideas.rpg.v2.game.item.stuff.Stuff;
import com.mideas.rpg.v2.game.item.stuff.StuffManager;
import com.mideas.rpg.v2.game.item.stuff.WeaponManager;
import com.mideas.rpg.v2.game.shortcut.PotionShortcut;
import com.mideas.rpg.v2.game.shortcut.Shortcut;
import com.mideas.rpg.v2.game.shortcut.SpellShortcut;
import com.mideas.rpg.v2.game.shortcut.StuffShortcut;
import com.mideas.rpg.v2.jdo.JDOStatement;

public class SpellBarManager {

	public static void loadSpellBar() throws FileNotFoundException, SQLException {
		int i = 0;
		int id;
		JDOStatement statement = Mideas.getJDO().prepare("SELECT slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8, slot9, slot10, slot11, slot12, slot13, slot14, slot15, slot16, slot17, slot18, slot19, slot20, slot21, slot22, slot23, slot24, slot25, slot26, slot27, slot28, slot29, slot30, slot31, slot32, slot33, slot34, slot35 FROM spellbar WHERE class = ?");
		statement.putString(Mideas.joueur1().getClasse());
		statement.execute();
		if(statement.fetch()) {
			while(i < 35) {
				id = statement.getInt();
				if(StuffManager.exists(id)) {
					Mideas.joueur1().setSpells(i, new StuffShortcut((Stuff)StuffManager.getStuff(id)));
				}
				else if(PotionManager.exists(id)) {
					Mideas.joueur1().setSpells(i, new PotionShortcut((Potion)PotionManager.getPotion(id)));
				}
				else if(SpellManager.exists(id)) {
					Mideas.joueur1().setSpells(i, new SpellShortcut((Spell)SpellManager.getBookSpell(id)));
				}
				else if(WeaponManager.exists(id)) {
					Mideas.joueur1().setSpells(i, new StuffShortcut((Stuff)WeaponManager.getWeapon(id)));
				}
				i++;
			}
		}
	}
	
	public static void setSpellBar() throws FileNotFoundException, SQLException {
		JDOStatement statement = Mideas.getJDO().prepare("UPDATE spellbar SET slot1 = ?, slot2 = ?, slot3 = ?, slot4 = ?, slot5 = ?, slot6 = ?, slot7 = ?, slot8 = ?, slot9 = ?, slot10 = ?, slot11 = ?, slot12 = ?, slot13 = ?, slot14 = ?, slot15 = ?, slot16 = ?, slot17 = ?, slot18 = ?, slot19 = ?, slot20 = ?, slot21 = ?, slot22 = ?, slot23 = ?, slot24 = ?, slot25 = ?, slot26 = ?, slot27 = ?, slot28 = ?, slot29 = ?, slot30 = ?, slot31 = ?, slot32 = ?, slot33 = ?, slot34 = ?, slot35 = ? WHERE class = ?");
		int i = 0;
		while(i < 35) {
			Shortcut tempSpell = Mideas.joueur1().getSpells(i);
			int id = 0;
			if(tempSpell != null) {
				id = tempSpell.getId();
			}
			statement.putInt(id);
			i++;
		}
		statement.putString(Mideas.joueur1().getClasse());
		statement.execute();
	}
}
