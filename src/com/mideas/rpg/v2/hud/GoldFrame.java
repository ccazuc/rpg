package com.mideas.rpg.v2.hud;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.Sprites;
import com.mideas.rpg.v2.TTF2;
import com.mideas.rpg.v2.utils.Draw;

public class GoldFrame {
	
	public static void draw() {
		if(ContainerFrame.getBagOpen(0)) {
			int x = -159;
			int y = -174;
			int gold = Mideas.calcGoldCoin();
			int silver = Mideas.calcSilverCoin();
			int copper = Mideas.getGold()-Mideas.calcGoldCoin()*10000-Mideas.calcSilverCoin()*100;
			Draw.drawQuad(Sprites.copper_coin, Display.getWidth()+x, Display.getHeight()+y);
			x-= TTF2.coin.getWidth(String.valueOf(copper))+2;
			TTF2.coin.drawStringShadow(Display.getWidth()+x, Display.getHeight()+y+1, String.valueOf(copper), Color.white, Color.black, 1, 1, 1);
			x-= Sprites.silver_coin.getImageWidth()+7;
			Draw.drawQuad(Sprites.silver_coin, Display.getWidth()+x, Display.getHeight()+y);
			x-= TTF2.coin.getWidth(String.valueOf(silver))+2;
			TTF2.coin.drawStringShadow(Display.getWidth()+x, Display.getHeight()+y+1, String.valueOf(silver), Color.white, Color.black, 1, 1, 1);
			x-= Sprites.gold_coin.getImageWidth()+7;
			Draw.drawQuad(Sprites.gold_coin, Display.getWidth()+x, Display.getHeight()+y);
			x-= TTF2.coin.getWidth(String.valueOf(gold))+2;
			TTF2.coin.drawStringShadow(Display.getWidth()+x, Display.getHeight()+y+1, String.valueOf(gold), Color.white, Color.black, 1, 1, 1);
		}
	}
}
