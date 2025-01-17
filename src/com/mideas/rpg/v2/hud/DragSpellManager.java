package com.mideas.rpg.v2.hud;

import org.lwjgl.opengl.Display;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.game.shortcut.Shortcut;
import com.mideas.rpg.v2.render.Draw;
import com.mideas.rpg.v2.render.Sprites;

public class DragSpellManager {

	private static Shortcut draggedShortcut;
	//private static boolean[] hover = new boolean[49];
	
	public static void draw() {
		if(draggedShortcut != null) {
			Draw.drawQuad(draggedShortcut.getSprite(), Mideas.mouseX(), Mideas.mouseY());
			Draw.drawQuad(Sprites.draggedspell_border, Mideas.mouseX()-5, Mideas.mouseY()-5);
		}
	}
	
	public static boolean mouseEvent() {
		/*if(isHoverSpellBarFrame()) {
			Arrays.fill(hover, false);
		}
		float xShift = 47.9f;
		int i = 0;
		int j = 0;	
		float x = -Sprites.final_spellbar.getImageWidth()/2+120f;
		float y = -39.7f;
		if(isHoverSpellBarFrame()) {
			while(i < Mideas.joueur1().getSpells().length) {
				isHoverSpellBar(x+j*xShift, y, i);
				j++;
				i++;
				if(i == 12) {
					y+= -61;
					j = 0;
				}
			}
		}
		if(!Mouse.getEventButtonState()) {
			if(Mouse.getEventButton() == 0 && Keyboard.isKeyDown(42)) { //caps key
				if(isHoverSpellBarFrame()) {
					i = 0;
					while(i < Mideas.joueur1().getSpells().length) {
						if(clickSpell(i)) {
							return true;
						}
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
			else if(DragManager.getDraggedItem() != null && Mouse.getEventButton() == 0) {
				if(isHoverSpellBarFrame()) {
					i = 0;
					while(i < Mideas.joueur1().getSpells().length) {
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
			else if(Mouse.getEventButton() == 0 || Mouse.getEventButton() == 1) {
				if(draggedShortcut != null && !isHoverSpellBarFrame()) {
					deleteSpell(draggedShortcut);
					draggedShortcut = null;
				}
				if(draggedBookSpell != null) {
					draggedBookSpell = null;
				}
			}
		}*/
		return false;
	}
	
	/*private static void isHoverSpellBar(float x, float y, int i) {
		if(Mideas.mouseX() >= Display.getWidth()/2+x && Mideas.mouseX() <= Display.getWidth()/2+x+37 && Mideas.mouseY() >= Display.getHeight()+y && Mideas.mouseY() <= Display.getHeight()+y+35) {
			hover[i] = true;
		}
	}*/
	
	/*private static void deleteSpell(Shortcut draggedSpell) {
		int i = 0;
		while(i < Mideas.joueur1().getSpells().size()) {
			if(draggedSpell == Mideas.joueur1().getSpells(i)) {
				Mideas.joueur1().setSpells(i, null);
				SpellBarManager.setSpellBar();
				return;
			}
			i++;
		}
	}*/
	
	/*private static boolean clickSpell(int i) {
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
					return true;
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
					return true;
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
					return true;
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
					return true;
				}
			}
		}

		return false;
	}*/
	
	/*private static boolean setNullSpell(Shortcut spell) {
		int i = 0;
		while(i < Mideas.joueur1().getSpells().size()) {
			if(spell == Mideas.joueur1().getSpells(i)) {
				Mideas.joueur1().setSpells(i, null);
				SpellBarManager.setSpellBar();
				return true;
			}
			i++;
		}
		return false;
	}*/
	
	/*private static boolean dropSpell(int i) {
		if(draggedShortcut != null) {
			if(hover[i]) {
				if(Mideas.joueur1().getSpells(i) == null) {
					setNullSpell(draggedShortcut);
					Mideas.joueur1().setSpells(i, draggedShortcut);
					draggedShortcut = null;
					SpellBarManager.setSpellBar();
					return true;
				}
				Shortcut tempSpellShortcut = Mideas.joueur1().getSpells(i);
				Mideas.joueur1().setSpells(i, draggedShortcut);
				draggedShortcut = tempSpellShortcut;
				SpellBarManager.setSpellBar();
				return true;
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
				Shortcut tempSpell = Mideas.joueur1().getSpells(i);
				Mideas.joueur1().setSpells(i, new SpellShortcut(draggedBookSpell));
				draggedShortcut = tempSpell;
				draggedBookSpell = null;
				SpellBarManager.setSpellBar();
				return true;
			}
		}
		else if(DragManager.getDraggedItem() != null) {
			if(hover[i]) {
				if(DragManager.getDraggedItem().isStuff() || DragManager.getDraggedItem().isWeapon()) {
					if(Mideas.joueur1().getSpells(i) == null) {
						Mideas.joueur1().setSpells(i, new StuffShortcut((Stuff)DragManager.getDraggedItem()));
						DragManager.setDraggedItem(null);
						SpellBarManager.setSpellBar();
						return true;
					}
					Shortcut temp = Mideas.joueur1().getSpells(i);
					Mideas.joueur1().setSpells(i, new StuffShortcut((Stuff)DragManager.getDraggedItem()));
					DragManager.setDraggedItem(null);
					draggedShortcut = temp;
					SpellBarManager.setSpellBar();
					return true;
				}
				else if(DragManager.getDraggedItem().isPotion()) {
					if(Mideas.joueur1().getSpells(i) == null) {
						Mideas.joueur1().setSpells(i, new PotionShortcut((Potion)DragManager.getDraggedItem()));
						DragManager.setDraggedItem(null);
						SpellBarManager.setSpellBar();
						return true;
					}
				}
			}
		}
		return false;
	}*/
	
	public static void setDraggedSpell(Shortcut shortcut) {
		draggedShortcut = shortcut;
	}
	
	public static Shortcut getDraggedSpell() {
		return draggedShortcut;
	}
	
	public static boolean isHoverSpellBarFrame() {
		if(Mideas.mouseX() >= Display.getWidth()/2-Sprites.final_spellbar.getImageWidth()/2 && Mideas.mouseX() <= Display.getWidth()/2+Sprites.final_spellbar.getImageWidth()/2 && Mideas.mouseY() >= Display.getHeight()-Sprites.final_spellbar.getImageHeight()-30 && Mideas.mouseY() <= Display.getHeight()) {
			return true;
		}
		return false;
	}
}
