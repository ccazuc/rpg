package com.mideas.rpg.v2.hud;

import java.util.Arrays;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.mideas.rpg.v2.Interface;
import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.Sprites;
import com.mideas.rpg.v2.TTF2;
import com.mideas.rpg.v2.game.IconsManager;
import com.mideas.rpg.v2.game.spell.SpellManager;
import com.mideas.rpg.v2.utils.Draw;
import com.mideas.rpg.v2.utils.Texture;

public class SpellBookFrame {
	
	private static boolean[] hover_book = new boolean[7];
	private static boolean hover_book_button;

	public static void draw() {
		int x = 110;
		int y = -290;
		int yShift = 85;
		Draw.drawQuad(Sprites.spellbook_page1, Display.getWidth()/2, Display.getHeight()/2-350);
		if(Mideas.joueur1().getClasse().equals("Guerrier")) {
			Draw.drawQuad(Sprites.spellbook_spell_bg, Display.getWidth()/2+x, Display.getHeight()/2+y);
			Draw.drawQuad(IconsManager.getSprite42(SpellManager.getBookSpell(102).getSpriteId()), Display.getWidth()/2+x+20, Display.getHeight()/2+y+yShift+15);
			Draw.drawQuad(Sprites.spellbook_spell_bg, Display.getWidth()/2+x, Display.getHeight()/2+y+yShift);
			TTF2.characterFrameStats.drawStringShadow(Display.getWidth()/2+x+75, Display.getHeight()/2+y+yShift+15, "Heroic Strike", Color.decode("#D9D623"), Color.black, 1, 1, 1);
			drawSpell(Sprites.spell_book_grey_charge, IconsManager.getSprite42(SpellManager.getBookSpell(101).getSpriteId()), 3, x, y+2*yShift, "Charge");
			drawSpell(Sprites.spell_book_grey_thunder_clap, IconsManager.getSprite42(SpellManager.getBookSpell(105).getSpriteId()), 7, x, y+3*yShift, "Thunder Clap");
			drawSpell(Sprites.spell_book_grey_rend, IconsManager.getSprite42(SpellManager.getBookSpell(104).getSpriteId()), 10, x, y+4*yShift, "Rend");
			drawSpell(Sprites.spell_book_grey_mortal_strike, IconsManager.getSprite42(SpellManager.getBookSpell(103).getSpriteId()), 15, x, y+5*yShift, "Mortal Strike");
			if(hover_book_button) {
				Draw.drawQuad(Sprites.close_spell_book_hover, Display.getWidth()/2+593, Display.getHeight()/2-332);
			}
		}
	}
	
	public static boolean mouseEvent() {
		setHoverFalse();
		Arrays.fill(hover_book, false);
		int x = 110;
		int y = -290;
		int yShift = 85;
		if(Mideas.mouseX() >= Display.getWidth()/2+x+480 && Mideas.mouseX() <= Display.getWidth()/2+x+502 && Mideas.mouseY() >= Display.getHeight()/2+y-45 && Mideas.mouseY() <= Display.getHeight()/2+y-25) {
			hover_book_button = true;
			if(Mouse.getEventButtonState()) {
				Interface.closeSpellBookFrame();
				return true;
			}
		}
		int i = 0;
		while(i <= 5) {
			isHoverBook(x, y, i*yShift, i);
			i++;
		}
		return false;
	}
	
	private static boolean isHoverBook(int x, int y, int yShift, int i) {
		if(Mideas.getHover() && Mideas.mouseX() >= Display.getWidth()/2+x+20 && Mideas.mouseX() <= Display.getWidth()/2+x+20+44 && Mideas.mouseY() >= Display.getHeight()/2+y+yShift+15 && Mideas.mouseY() <= Display.getHeight()/2+y+yShift+15+44) {
			hover_book[i] = true;
			Mideas.setHover(false);
		}
		return false;
	}
	
	private static void drawSpell(Texture grey_texture, Texture texture, int level, int x, int y, String spellName) {
		if(Mideas.getLevel() < level) {
			Draw.drawQuad(grey_texture, Display.getWidth()/2+x+20, Display.getHeight()/2+y+15);
			Draw.drawQuad(Sprites.spellbook_spell_bg_grey, Display.getWidth()/2+x, Display.getHeight()/2+y);
			TTF2.characterFrameStats.drawStringShadow(Display.getWidth()/2+x+75, Display.getHeight()/2+y+15, spellName, Color.decode("#633E21"), Color.black, 1, 1, 1);
			TTF2.characterFrameStats.drawStringShadow(Display.getWidth()/2+x+75, Display.getHeight()/2+y+30, "Available at level "+level, Color.decode("#633E21"), Color.black, 1, 1, 1);
		}
		else {
			Draw.drawQuad(texture, Display.getWidth()/2+x+20, Display.getHeight()/2+y+15);
			Draw.drawQuad(Sprites.spellbook_spell_bg, Display.getWidth()/2+x, Display.getHeight()/2+y);
			TTF2.characterFrameStats.drawStringShadow(Display.getWidth()/2+x+75, Display.getHeight()/2+y+15, spellName, Color.decode("#D9D623"), Color.black, 1, 1, 1);
		}
	}
	
	public static boolean getHoverBook(int i) {
		return hover_book[i];
	}
	
	public static boolean[] getHoverBook() {
		return hover_book;
	}
	
	private static void setHoverFalse() {
		hover_book_button = false;
	}
}