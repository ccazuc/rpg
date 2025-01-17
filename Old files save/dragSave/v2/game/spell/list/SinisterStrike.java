package com.mideas.rpg.v2.game.spell.list;

import com.mideas.rpg.v2.Sprites;
import com.mideas.rpg.v2.game.spell.Spell;

public class SinisterStrike extends Spell {

	private static int cd;
	
	public SinisterStrike() {
		super(3000, 3000, 1500, 0, 0, 0, 2, 0, 703);
		name = "Sinister Strike";
		sprite = Sprites.spell_sinister_strike;
	}

	public void setSpellCd(int number) {
		cd = number;
	}
	
	public int getSpellCd() {
		return cd;
	}
}