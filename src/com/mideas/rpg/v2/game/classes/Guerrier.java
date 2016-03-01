package com.mideas.rpg.v2.game.classes;

import com.mideas.rpg.v2.game.Joueur;
import com.mideas.rpg.v2.game.item.stuff.Stuff;
import com.mideas.rpg.v2.game.spell.HeroicStrike;
import com.mideas.rpg.v2.game.spell.Spell;

public class Guerrier extends Joueur {
	
	public static final int MAX_HEALTH = 10000;
	public static final int MAX_MANA = 1500;
	
	public Guerrier() {
		super(10000, 10, 200, 200, 200, 5, 1500, new Spell[49], new Spell[49], new Stuff[21], "Guerrier", 1, 10000, 1500, 0, 800, 1100, 0);
		setSpells(0, new HeroicStrike());
		setSpellUnlocked(0, new HeroicStrike());
	}	
}