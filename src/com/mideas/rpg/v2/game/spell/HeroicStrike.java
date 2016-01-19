package com.mideas.rpg.v2.game.spell;

import com.mideas.rpg.v2.Sprites;

public class HeroicStrike extends Spell {

	private static int cd;
	
	public HeroicStrike() {
		super(1500, 1500, 400, 40, 2, 0, 2, 0, 102);
		name = "HeroicStrike";
		sprite = Sprites.spell_heroic_strike;
	}

	public void setSpellCd(int number) {
		cd = number;
	}
	
	public int getSpellCd() {
		return cd;
	}
}
