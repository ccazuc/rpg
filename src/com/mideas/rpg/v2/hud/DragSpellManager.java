package com.mideas.rpg.v2.hud;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Arrays;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.Sprites;
import com.mideas.rpg.v2.game.item.ItemType;
import com.mideas.rpg.v2.game.item.potion.Potion;
import com.mideas.rpg.v2.game.item.stuff.Stuff;
import com.mideas.rpg.v2.game.shortcut.PotionShortcut;
import com.mideas.rpg.v2.game.shortcut.Shortcut;
import com.mideas.rpg.v2.game.shortcut.ShortcutType;
import com.mideas.rpg.v2.game.shortcut.SpellShortcut;
import com.mideas.rpg.v2.game.shortcut.StuffShortcut;
import com.mideas.rpg.v2.game.spell.Spell;
import com.mideas.rpg.v2.game.spell.SpellBarManager;
import com.mideas.rpg.v2.game.spell.SpellManager;
import com.mideas.rpg.v2.utils.Draw;

public class DragSpellManager {

	private static Spell draggedBookSpell;
	private static Shortcut draggedShortcut;
	private static boolean[] hover = new boolean[35];
	
	public static void draw() {
		if(draggedBookSpell != null) {
			Draw.drawQuad(draggedBookSpell.getSprite(), Mideas.mouseX(), Mideas.mouseY());
			Draw.drawQuad(Sprites.draggedspell_border, Mideas.mouseX()-5, Mideas.mouseY()-5);
		}
		if(draggedShortcut != null) {
			Draw.drawQuad(draggedShortcut.getSprite(), Mideas.mouseX(), Mideas.mouseY());
			Draw.drawQuad(Sprites.draggedspell_border, Mideas.mouseX()-5, Mideas.mouseY()-5);
		}
	}
	
	public static boolean mouseEvent() throws FileNotFoundException, SQLException {
		if(isHoverSpellBarFrame()) {
			Arrays.fill(hover, false);
		}
		float x = -678+56.3f;
		float xShift = 56.3f;
		int y = -49;
		int i = 0;
		int j = 0;
		if(isHoverSpellBarFrame()) {
			while(i < SpellBarFrame.getHoverSpellBar().length) {
				isHoverSpellBar(x+j*xShift, y, i);
				j++;
				i++;
				if(i == 11) {
					y = -130;
					j = -1;
				}
			}
		}
		if(Mouse.getEventButtonState()) {
			if(Mouse.getEventButton() == 0 && Keyboard.isKeyDown(42)) { //caps key
				if(isHoverSpellBarFrame()) {
					i = 0;
					while(i < SpellBarFrame.getHoverSpellBar().length) {
						clickSpell(i);
						i++;
					}
				}
				//hover spellbook
				if(SpellBookFrame.getHoverBook(1) && Mideas.joueur1().getSpellUnlocked(0) != null) {
					draggedBookSpell = SpellManager.getBookSpell(102);
				}
				else if(SpellBookFrame.getHoverBook(2) && Mideas.joueur1().getSpellUnlocked(1) != null) {
					draggedBookSpell = SpellManager.getBookSpell(101);
				}
				else if(SpellBookFrame.getHoverBook(3) && Mideas.joueur1().getSpellUnlocked(2) != null) {
					draggedBookSpell = SpellManager.getBookSpell(105);
				}
				else if(SpellBookFrame.getHoverBook(4) && Mideas.joueur1().getSpellUnlocked(3) != null) {
					draggedBookSpell = SpellManager.getBookSpell(104);
				}
				else if(SpellBookFrame.getHoverBook(5) && Mideas.joueur1().getSpellUnlocked(4) != null) {
					draggedBookSpell = SpellManager.getBookSpell(103);
				}
			}
		}
		if(!Mouse.getEventButtonState()) {
			if(DragManager.getDraggedItem() != null && Mouse.getEventButton() == 0) {
				if(isHoverSpellBarFrame()) {
					i = 0;
					while(i < SpellBarFrame.getHoverSpellBar().length) {
						if(dropSpell(i)) {
							break;
						}
						i++;
					}
				}
			}
			else if(Mouse.getEventButton() == 0) {
				if(isHoverSpellBarFrame()) {
					i = 0;
					while(i <= hover.length-1) {
						if(dropSpell(i)) {
							break;
						}
						i++;
					}
				}
			}
			if(Mouse.getEventButton() == 1) {
				if(draggedShortcut != null && !isHoverSpellBarFrame()) {
					deleteSpell(draggedShortcut);
					draggedShortcut = null;
				}
				if(draggedBookSpell != null) {
					draggedBookSpell = null;
				}
			}
		}
		return false;
	}
	
	private static void isHoverSpellBar(float x, float y, int i) {
		if(Mideas.mouseX() >= Display.getWidth()/2+x && Mideas.mouseX() <= Display.getWidth()/2+x+47 && Mideas.mouseY() >= Display.getHeight()+y && Mideas.mouseY() <= Display.getHeight()+y+47) {
			hover[i] = true;
		}
	}
	
	private static void deleteSpell(Shortcut draggedSpell) throws FileNotFoundException, SQLException {
		int i = 0;
		while(i < Mideas.joueur1().getSpells().length) {
			if(draggedSpell == Mideas.joueur1().getSpells(i)) {
				Mideas.joueur1().setSpells(i, null);
				SpellBarManager.setSpellBar();
			}
			i++;
		}
	}
	
	private static void clickSpell(int i) throws FileNotFoundException, SQLException {
		if(hover[i]) {
			if(Mideas.joueur1().getSpells(i) != null) {
				if(Mideas.joueur1().getSpells(i).getShortcutType() == ShortcutType.SPELL) {
					if(draggedShortcut != null) {
						Shortcut tempShortcutSpell = Mideas.joueur1().getSpells(i);
						Mideas.joueur1().setSpells(i, draggedShortcut);
						draggedShortcut = tempShortcutSpell;
					}
					else {
						draggedShortcut = Mideas.joueur1().getSpells(i);
						Mideas.joueur1().setSpells(i, null);
					}
					SpellBarManager.setSpellBar();
				}
				else if(Mideas.joueur1().getSpells(i).getShortcutType() == ShortcutType.STUFF) {
					if(draggedShortcut != null) {
						Shortcut tempShortcutSpell = Mideas.joueur1().getSpells(i);
						Mideas.joueur1().setSpells(i, draggedShortcut);
						draggedShortcut = tempShortcutSpell;
					}
					else {
						draggedShortcut = Mideas.joueur1().getSpells(i);
						Mideas.joueur1().setSpells(i, null);
					}
					SpellBarManager.setSpellBar();
				}
				else if(Mideas.joueur1().getSpells(i).getShortcutType() == ShortcutType.POTION) {
					if(draggedShortcut != null) {
						Shortcut tempShortcutSpell = Mideas.joueur1().getSpells(i);
						Mideas.joueur1().setSpells(i, draggedShortcut);
						draggedShortcut = tempShortcutSpell;
					}
					else {
						draggedShortcut = Mideas.joueur1().getSpells(i);
						Mideas.joueur1().setSpells(i, null);
					}
					SpellBarManager.setSpellBar();
				}
			}
			else if(Mideas.joueur1().getSpells(i) == null) {
				if(draggedShortcut != null) {
					Mideas.joueur1().setSpells(i, draggedShortcut);
					draggedShortcut = null;
					SpellBarManager.setSpellBar();
				}
				else if(draggedShortcut != null) {
					Mideas.joueur1().setSpells(i, draggedShortcut);
					draggedShortcut = null;
					SpellBarManager.setSpellBar();
				}
				else if(draggedShortcut != null) {
					Mideas.joueur1().setSpells(i, draggedShortcut);
					draggedShortcut = null;
					SpellBarManager.setSpellBar();
				}
			}
		}
	}
	
	private static boolean setNullSpell(Shortcut spell) throws FileNotFoundException, SQLException {
		int i = 0;
		while(i < Mideas.joueur1().getSpells().length) {
			if(spell == Mideas.joueur1().getSpells(i)) {
				Mideas.joueur1().setSpells(i, null);
				SpellBarManager.setSpellBar();
				return true;
			}
			i++;
		}
		return false;
	}
	
	private static boolean dropSpell(int i) throws FileNotFoundException, SQLException {
		if(draggedShortcut != null) {
			if(hover[i]) {
				if(Mideas.joueur1().getSpells(i) == null) {
					setNullSpell(draggedShortcut);
					Mideas.joueur1().setSpells(i, draggedShortcut);
					draggedShortcut = null;
					SpellBarManager.setSpellBar();
					return true;
				}
				else {
					Shortcut tempSpellShortcut = Mideas.joueur1().getSpells(i);
					Mideas.joueur1().setSpells(i, draggedShortcut);
					draggedShortcut = tempSpellShortcut;
					SpellBarManager.setSpellBar();
					return true;
				}
			}
		}
		else if(draggedBookSpell != null) {
			if(hover[i]) {
				if(Mideas.joueur1().getSpells(i) == null) {
					Mideas.joueur1().setSpells(i, new SpellShortcut(draggedBookSpell));
					draggedBookSpell = null;
					draggedShortcut = null;
					SpellBarManager.setSpellBar();
					return true;
				}
				else {
					Shortcut tempSpell = Mideas.joueur1().getSpells(i);
					Mideas.joueur1().setSpells(i, new SpellShortcut(draggedBookSpell));
					draggedShortcut = tempSpell;
					draggedBookSpell = null;
					SpellBarManager.setSpellBar();
					return true;
				}
			}
		}
		else if(DragManager.getDraggedItem() != null) {
			if(hover[i]) {
				if(DragManager.getDraggedItem().getItemType() == ItemType.STUFF) {
					if(Mideas.joueur1().getSpells(i) == null) {
						Mideas.joueur1().setSpells(i, new StuffShortcut((Stuff)DragManager.getDraggedItem()));
						DragManager.setDraggedItem(null);
						SpellBarManager.setSpellBar();
						return true;
					}
				}
				else if(DragManager.getDraggedItem().getItemType() == ItemType.POTION) {
					if(Mideas.joueur1().getSpells(i) == null) {
						Mideas.joueur1().setSpells(i, new PotionShortcut((Potion)DragManager.getDraggedItem()));
						DragManager.setDraggedItem(null);
						SpellBarManager.setSpellBar();
					}
				}
			}
		}
		return false;
	}
	
	public static Shortcut getDraggedSpell() {
		return draggedShortcut;
	}
	
	public static Spell getDraggedSpellBook() {
		return draggedBookSpell;
	}
	
	private static boolean isHoverSpellBarFrame() {
		if(Mideas.mouseX() >= Display.getWidth()/2-809 && Mideas.mouseX() <= Display.getWidth()/2-809+Sprites.final_spellbar.getImageWidth() && Mideas.mouseY() >= Display.getHeight()-150 && Mideas.mouseY() <= Display.getHeight()) {
			return true;
		}
		return false;
	}
}
