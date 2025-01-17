package com.mideas.rpg.v2.game.spell.list;

import com.mideas.rpg.v2.Sprites;
import com.mideas.rpg.v2.game.spell.Spell;

public class FlameShock extends Spell {

	private static int cd;
	
	public FlameShock() {
		super(1500, 1500, 750, 0, 0, 0, 2, 0, 302);
		name = "FlameShock";
		sprite = Sprites.spell_flame_shock;
	}

	public void setSpellCd(int number) {
		cd = number;
	}
	
	public int getSpellCd() {
		return cd;
	}
}