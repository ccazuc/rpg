package com.mideas.rpg.v2.hud;

import java.io.FileNotFoundException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.Sprites;
import com.mideas.rpg.v2.FontManager;
import com.mideas.rpg.v2.chat.ChatFrame;
import com.mideas.rpg.v2.command.CommandSpellCast;
import com.mideas.rpg.v2.game.CharacterStuff;
import com.mideas.rpg.v2.game.IconsManager;
import com.mideas.rpg.v2.game.item.ItemType;
import com.mideas.rpg.v2.game.item.potion.Potion;
import com.mideas.rpg.v2.game.shortcut.PotionShortcut;
import com.mideas.rpg.v2.game.shortcut.Shortcut;
import com.mideas.rpg.v2.game.shortcut.ShortcutType;
import com.mideas.rpg.v2.game.shortcut.SpellShortcut;
import com.mideas.rpg.v2.game.shortcut.StuffShortcut;
import com.mideas.rpg.v2.game.spell.SpellManager;
import com.mideas.rpg.v2.game.spell.SpellType;
import com.mideas.rpg.v2.hud.Cast;
import com.mideas.rpg.v2.utils.Draw;
import com.mideas.rpg.v2.utils.Texture;
import com.mideas.rpg.v2.utils.Tooltip;

public class SpellBarFrame {
	
	static Shortcut hoveredSpell;
	private static boolean isCastingSpell;
	private static float xHoveredSpell;
	private static int yHoveredSpell;
	private static int hoveredSlot = -1;
	private static Tooltip tooltip = new Tooltip(0, 0, 0, 0, 0.6f);
	private final static String[] bindDisplay = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
	
	private static String numberFreeSlotBag = "";
	
	public static boolean draw() {
		if(DragManager.isSpellBarHover()) {
			hoveredSlot = -1;
		}
		hoveredSpell = null;
		if(Mideas.joueur1().getLevel() >= 70) {
			Draw.drawQuad(Sprites.exp_bar, Display.getWidth()/2-Sprites.final_spellbar.getImageWidth()*Mideas.getDisplayXFactor()/2+110*Mideas.getDisplayXFactor(), Display.getHeight()-Sprites.final_spellbar.getImageHeight()*Mideas.getDisplayYFactor()+24*Mideas.getDisplayXFactor(), 1165*Mideas.getDisplayXFactor(), 8);
		}
		else if(Mideas.joueur1().getLevel() > 1) {
			float e = (Mideas.joueur1().getExp()-(float)Mideas.getExpNeeded(Mideas.joueur1().getLevel()-1))/((float)Mideas.getExpNeeded(Mideas.joueur1().getLevel())-Mideas.getExpNeeded(Mideas.joueur1().getLevel()-1));
			Draw.drawQuad(Sprites.exp_bar, Display.getWidth()/2-Sprites.final_spellbar.getImageWidth()*Mideas.getDisplayXFactor()/2+110*Mideas.getDisplayXFactor(), Display.getHeight()-Sprites.final_spellbar.getImageHeight()*Mideas.getDisplayYFactor()+24*Mideas.getDisplayXFactor(), 1165*e*Mideas.getDisplayXFactor(), 9);
		}
		else {
			float e = ((float)Mideas.joueur1().getExp()/Mideas.getExpNeeded(Mideas.joueur1().getLevel()));
			Draw.drawQuad(Sprites.exp_bar, Display.getWidth()/2-Sprites.final_spellbar.getImageWidth()*Mideas.getDisplayXFactor()/2+110*Mideas.getDisplayXFactor(), Display.getHeight()-Sprites.final_spellbar.getImageHeight()*Mideas.getDisplayYFactor()+24*Mideas.getDisplayXFactor(), 1165*e*Mideas.getDisplayXFactor(), 8);
		}
		//TTF2.get("FRIZQT", 15).drawStringShadow(Display.getWidth()/2+5-TTF2.get("FRIZQT", 15).getWidth(Mideas.getFps()), Display.getHeight()-180, Mideas.getFps(), Color.yellow, Color.black, 1);
        Draw.drawQuad(Sprites.final_spellbar, Display.getWidth()/2-Sprites.final_spellbar.getImageWidth()/2*Mideas.getDisplayXFactor(), Display.getHeight()-Sprites.final_spellbar.getImageHeight()*Mideas.getDisplayYFactor(), Sprites.final_spellbar.getImageWidth()*Mideas.getDisplayXFactor(), Sprites.final_spellbar.getImageHeight()*Mideas.getDisplayYFactor());
		FontManager.get("FRIZQT", 15).drawStringShadow(Display.getWidth()/2+557*Mideas.getDisplayXFactor()-FontManager.get("FRIZQT", 15).getWidth(numberFreeSlotBag)/2, Display.getHeight()-22, numberFreeSlotBag, Color.white, Color.black, 1, 1, 1);
		float x = -Sprites.final_spellbar.getImageWidth()/2+120f;
		int spellCount = 0;
		int yShift = 0;
		float y = -39.7f;
		while(spellCount < Mideas.joueur1().getSpells().length) {
			if(spellCount >= 12) {
				if(DragSpellManager.getDraggedSpell() != null || DragManager.getDraggedItem() != null || DragSpellManager.getDraggedSpellBook() != null || Mideas.joueur1().getSpells(spellCount) != null) {
		        	if(spellCount%2 == 0) {
		        		Draw.drawQuad(Sprites.spellbar_case, Display.getWidth()/2+(x-4)*Mideas.getDisplayXFactor(), Display.getHeight()+(y-4.5f+yShift)*Mideas.getDisplayYFactor());
		        	}
		        	else {
		        		Draw.drawQuad(Sprites.spellbar_case2, Display.getWidth()/2+(x-4)*Mideas.getDisplayXFactor(), Display.getHeight()+(y-4.5f+yShift)*Mideas.getDisplayYFactor());
		        	}
				}
			}
 			if(Mideas.joueur1().getSpells(spellCount) != null) {
				if(Mideas.joueur1().getSpells(spellCount).getShortcutType() == ShortcutType.SPELL) {
					SpellShortcut spell = (SpellShortcut)Mideas.joueur1().getSpells(spellCount);
					if(!(DragSpellManager.getDraggedSpell() == spell)) {
						Texture sprite = spell.getSprite();
						Draw.drawQuad(sprite, Display.getWidth()/2+x*Mideas.getDisplayXFactor(), Display.getHeight()+(y+yShift)*Mideas.getDisplayYFactor(), sprite.getImageWidth()*Mideas.getDisplayXFactor(), sprite.getImageHeight()*Mideas.getDisplayXFactor());
						Draw.drawQuad(Sprites.spell_border, Display.getWidth()/2+(-2+x)*Mideas.getDisplayXFactor(), Display.getHeight()+(y-2+yShift)*Mideas.getDisplayYFactor());
					}
					if(Mideas.mouseX() >= Display.getWidth()/2+x*Mideas.getDisplayXFactor() && Mideas.mouseX() <= Display.getWidth()/2+37+x*Mideas.getDisplayXFactor() && Mideas.mouseY() >= Display.getHeight()+(y-2+yShift)*Mideas.getDisplayYFactor()  && Mideas.mouseY() <= Display.getHeight()+(-2+yShift)*Mideas.getDisplayXFactor()) {
						hoveredSpell = spell;
						xHoveredSpell = x;
						yHoveredSpell = yShift;
					}
					if(SpellManager.getCd(spell.getSpell().getSpellId()) > 0) {
						FontManager.font5.drawStringShadow(Display.getWidth()/2+(25+x)*Mideas.getDisplayXFactor(), Display.getHeight()+y+8+yShift, String.valueOf(SpellManager.getCd(spell.getSpell().getSpellId())), Color.white, Color.black, 1, 1, 1);
					}
				}
				else if(Mideas.joueur1().getSpells(spellCount).getShortcutType() == ShortcutType.STUFF) {
					Draw.drawQuad(Mideas.joueur1().getSpells(spellCount).getSprite(), Display.getWidth()/2+x*Mideas.getDisplayXFactor(), Display.getHeight()+(y+yShift)*Mideas.getDisplayYFactor());
					Draw.drawQuad(Sprites.spell_border, Display.getWidth()/2+(-2+x)*Mideas.getDisplayXFactor(), Display.getHeight()+(y-2+yShift)*Mideas.getDisplayYFactor());
					StuffShortcut spell = (StuffShortcut)Mideas.joueur1().getSpells(spellCount);
					if(isEquippedItem(Mideas.joueur1().getSpells(spellCount).getId())) {
						Draw.drawQuad(Sprites.equipped_item_frame, Display.getWidth()/2+x*Mideas.getDisplayXFactor(), Display.getHeight()+(y-1+yShift)*Mideas.getDisplayYFactor());
					}
					if(Mideas.mouseX() >= Display.getWidth()/2+x*Mideas.getDisplayXFactor() && Mideas.mouseX() <= Display.getWidth()/2+37+x*Mideas.getDisplayXFactor() && Mideas.mouseY() >= Display.getHeight()+(y-2+yShift)*Mideas.getDisplayYFactor()  && Mideas.mouseY() <= Display.getHeight()+(-2+yShift)*Mideas.getDisplayXFactor()) {
						hoveredSpell = spell;
					}
				}
				else if(Mideas.joueur1().getSpells(spellCount).getShortcutType() == ShortcutType.POTION) {
					Draw.drawQuad(Mideas.joueur1().getSpells(spellCount).getSprite(), Display.getWidth()/2+x*Mideas.getDisplayXFactor(), Display.getHeight()+(y+yShift)*Mideas.getDisplayYFactor());
					//TTF2.get("FRIZQT", 15).drawStringShadow(Display.getWidth()/2+(x+37)*Mideas.getDisplayXFactor()-TTF2.get("FRIZQT", 15).getWidth(String.valueOf(Mideas.bag().getNumberItemInBags(((PotionShortcut)Mideas.joueur1().getSpells(spellCount)).getId()))), Display.getHeight()+(y+16+yShift)*Mideas.getDisplayYFactor(), String.valueOf(Mideas.bag().getNumberItemInBags(((PotionShortcut)Mideas.joueur1().getSpells(spellCount)).getId())), Color.white, Color.black, 1, 1, 1);
					Draw.drawQuad(Sprites.spell_border, Display.getWidth()/2-2+x*Mideas.getDisplayXFactor(), Display.getHeight()+(y-2+yShift)*Mideas.getDisplayYFactor());
					PotionShortcut spell = (PotionShortcut)Mideas.joueur1().getSpells(spellCount);
					if(Mideas.mouseX() >= Display.getWidth()/2+x*Mideas.getDisplayXFactor() && Mideas.mouseX() <= Display.getWidth()/2+37+x*Mideas.getDisplayXFactor() && Mideas.mouseY() >= Display.getHeight()+(y-2+yShift)*Mideas.getDisplayYFactor()  && Mideas.mouseY() <= Display.getHeight()+(-2+yShift)*Mideas.getDisplayXFactor()) {
						hoveredSpell = spell;
					}
				}
				if(DragSpellManager.getDraggedSpell() != Mideas.joueur1().getSpells(spellCount)) {
					if(spellCount+1 < 10) {
						FontManager.get("FRIZQT", 15).drawStringShadow(Display.getWidth()/2+(30+x)*Mideas.getDisplayXFactor()-FontManager.get("FRIZQT", 15).getWidth(bindDisplay[spellCount]), Display.getHeight()+y, bindDisplay[spellCount], Color.white, Color.black, 1, 1);
					}
					else if(spellCount+1 == 11) {
						FontManager.get("FRIZQT", 15).drawStringShadow(Display.getWidth()/2+(20+x)*Mideas.getDisplayXFactor(), Display.getHeight()+y, ")", Color.white, Color.black, 1, 1);
					}
					else if(spellCount+1 == 12) {
						FontManager.get("FRIZQT", 15).drawStringShadow(Display.getWidth()/2+(20+x)*Mideas.getDisplayXFactor(), Display.getHeight()+y, "=", Color.white, Color.black, 1, 1);
					}
				}
			}
			if(Mideas.mouseX() >= Display.getWidth()/2+x*Mideas.getDisplayXFactor() && Mideas.mouseX() <= Display.getWidth()/2+37+x*Mideas.getDisplayXFactor() && Mideas.mouseY() >= Display.getHeight()+(y-2+yShift)*Mideas.getDisplayYFactor()  && Mideas.mouseY() <= Display.getHeight()+(-2+yShift)*Mideas.getDisplayXFactor()) {
				hoveredSlot = spellCount;
				if(DragSpellManager.getDraggedSpell() != null || DragManager.getDraggedItem() != null || DragSpellManager.getDraggedSpellBook() != null || Mideas.joueur1().getSpells(spellCount) != null) {
					Draw.drawQuad(Sprites.spell_hover, Display.getWidth()/2+(x-2)*Mideas.getDisplayXFactor(), Display.getHeight()+(y-2+yShift)*Mideas.getDisplayYFactor());
				}
			}
			x+= 47.9;
			spellCount++;
			if(spellCount == 12) {
				x = -Sprites.final_spellbar.getImageWidth()/2+120f;
				yShift = -61;
			}
		}
		if(hoveredSpell != null) {
			if(hoveredSpell.getShortcutType() == ShortcutType.SPELL) {
				Draw.drawQuad(Sprites.tooltip, Display.getWidth()/2-74+xHoveredSpell, Display.getHeight()-220+yHoveredSpell);
				FontManager.get("FRIZQT", 16).drawString(Display.getWidth()/2-66+xHoveredSpell, Display.getHeight()-210+yHoveredSpell, ((SpellShortcut)hoveredSpell).getSpell().getName(), Color.white);
				//TTF2.font4.drawString(Display.getWidth()/2-66+xHoveredSpell, Display.getHeight()-188+yHoveredSpell, ((SpellShortcut)hoveredSpell).getSpell().getManaCost()+" Mana", Color.white);
				if(((SpellShortcut)hoveredSpell).getSpell().getType() == SpellType.HEAL) {
					//TTF2.font4.drawStringShadow(Display.getWidth()/2-66+xHoveredSpell, Display.getHeight()-158+yHoveredSpell, "Heals you for "+((SpellShortcut)hoveredSpell).getSpell().getHeal(), Color.yellow, Color.black, 1, 1, 1);
				}
				else {
					//TTF2.font4.drawStringShadow(Display.getWidth()/2-66+xHoveredSpell, Display.getHeight()-158+yHoveredSpell, "Deals "+(((SpellShortcut)hoveredSpell).getSpell().getDefaultDamage()+Mideas.joueur1().getStrength())+" damage to the enemy", Color.yellow, Color.black, 1, 1, 1);
				}
			}
		}
		int i = 0;
		float xBag = 489*Mideas.getDisplayXFactor();
		float xBagShift = -48.2f*Mideas.getDisplayXFactor();
		float yBag = -40*Mideas.getDisplayYFactor();
		while(i < 4) {
			if(Mideas.joueur1().bag().getEquippedBag(i) != null) {
				Draw.drawQuad(IconsManager.getSprite37(Mideas.joueur1().bag().getSpriteId(i)), Display.getWidth()/2+xBag+xBagShift*i, Display.getHeight()+yBag);
				if(DragBagManager.getHoverBag(i)) {
					Draw.drawQuad(Sprites.bag_hover, Display.getWidth()/2+xBag+xBagShift*i, Display.getHeight()+yBag);
					if(tooltip.getHashcode() != Mideas.joueur1().bag().getEquippedBag(i).hashCode()) {
						tooltip.update(Display.getWidth()/2+xBag+i*xBagShift-Mideas.joueur1().bag().getEquippedBag(i).getMaximumTooltipSize()-25*Mideas.getDisplayXFactor(),  Display.getHeight()-100*Mideas.getDisplayYFactor(), Mideas.joueur1().bag().getEquippedBag(i).getMaximumTooltipSize()+25*Mideas.getDisplayXFactor(), 60*Mideas.getDisplayYFactor(), Mideas.joueur1().bag().getEquippedBag(i).hashCode());
					}
					tooltip.draw();
					FontManager.get("FRIZQT", 14).drawStringShadow(Display.getWidth()/2+xBag+xBagShift*i-Mideas.joueur1().bag().getEquippedBag(i).getMaximumTooltipSize()-15*Mideas.getDisplayXFactor(), Display.getHeight()-90*Mideas.getDisplayYFactor(), Mideas.joueur1().bag().getEquippedBag(i).getStuffName(), Color.white, Color.black, 1, 1, 1);
					FontManager.get("FRIZQT", 14).drawStringShadow(Display.getWidth()/2+xBag+xBagShift*i-Mideas.joueur1().bag().getEquippedBag(i).getMaximumTooltipSize()-15*Mideas.getDisplayXFactor(), Display.getHeight()-70*Mideas.getDisplayYFactor(), Mideas.joueur1().bag().getEquippedBag(i).getSlotTooltipString(), Color.white, Color.black, 1, 1, 1);
				}
				if(DragBagManager.getClickBag(i)) {
					Draw.drawQuad(Sprites.bag_click_hover, Display.getWidth()/2+xBag+xBagShift*i, Display.getHeight()+yBag);
				}
				if(ContainerFrame.getBagOpen(i+1)) {
					Draw.drawQuadBlend(Sprites.bag_open_border, Display.getWidth()/2+xBag+xBagShift*i, Display.getHeight()+yBag, 40*Mideas.getDisplayXFactor(), 37*Mideas.getDisplayYFactor());
					Draw.drawQuad(Sprites.cursor, -5000, -5000);
					i++;
					continue;
				}
				Draw.drawQuad(Sprites.cursor, -100, -100);
			}
			i++;
		}
		if(Mideas.mouseX() >= Display.getWidth()/2+539.2f*Mideas.getDisplayXFactor() && Mideas.mouseX() <= Display.getWidth()/2+(539.2+48.2f)*Mideas.getDisplayXFactor() && Mideas.mouseY() >= Display.getHeight()+yBag && Mideas.mouseY() <= Display.getHeight()-3*Mideas.getDisplayYFactor()) {
			Draw.drawQuad(Sprites.bag_hover, Display.getWidth()/2+537*Mideas.getDisplayXFactor(), Display.getHeight()+yBag);
		}
		/*if(DragBagManager.getClickBag(0)) {
			Draw.drawQuad(Sprites.bag_click_hover, Display.getWidth()/2+539.2f, Display.getHeight()-41);
		}*/
		if(ContainerFrame.getBagOpen(0)) {
			Draw.drawQuadBlend(Sprites.bag_open_border, Display.getWidth()/2+536.2f*Mideas.getDisplayXFactor(), Display.getHeight()+yBag, 40*Mideas.getDisplayXFactor(), 37*Mideas.getDisplayYFactor());
		}
		return false;
	}
	
	public static boolean mouseEvent() throws FileNotFoundException {
		if(Mouse.getEventButton() == 0 || Mouse.getEventButton() == 1) {
			if(!Mouse.getEventButtonState() && DragSpellManager.getDraggedSpell() == null && DragSpellManager.getDraggedSpellBook() == null && DragManager.getDraggedItem() == null) {
				if(hoveredSpell != null && DragManager.getDraggedItem() == null && DragSpellManager.getDraggedSpell() == null && DragSpellManager.getDraggedSpellBook() == null) {
					if(hoveredSpell.getShortcutType() == ShortcutType.SPELL) {
						if(SpellManager.getCd(((SpellShortcut)hoveredSpell).getSpell().getSpellId()) <= 0 && !CastBar.isCasting()) {
							if(((SpellShortcut)hoveredSpell).getSpell().getCastTime() > 0) {
								isCastingSpell = true;
								CastBar.addCast(new Cast(((SpellShortcut)hoveredSpell).getSpell().getCastTime(), ((SpellShortcut)hoveredSpell).getSpell().getName()) {
									@Override
									public void endCastEvent() {
										//((SpellShortcut)hoveredSpell).getSpell().action(Mideas.joueur1(), Mideas.target());
										CommandSpellCast.write(((SpellShortcut)hoveredSpell).getSpell());
									}
								});
							}
							else {
								hoveredSpell.use(hoveredSpell);
								checkKeyboardCd(hoveredSpell);
								Mideas.setCurrentPlayer(false);
							}
						}
						else {
							//Mideas.joueur1().tick();
							Mideas.setCurrentPlayer(false);
						}
					}
					else if(hoveredSpell.getShortcutType() == ShortcutType.STUFF) {
						((StuffShortcut)hoveredSpell).use(hoveredSpell);
					}
					else if(hoveredSpell.getShortcutType() == ShortcutType.POTION) {
						if(Mideas.joueur1().bag().getNumberItemInBags(hoveredSpell.getId()) > 0) {
							((PotionShortcut)hoveredSpell).use(hoveredSpell);
						}
					}
				}
			}
		}
		return false;
	}
	
	public static boolean keyboardEvent() throws FileNotFoundException {
		if(!ChatFrame.getChatActive()) {
			if(Keyboard.getEventKey() == Keyboard.KEY_1) {
				keyboardAttack(Mideas.joueur1().getSpells(0));
				return true;
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_2) {
				keyboardAttack(Mideas.joueur1().getSpells(1));
				return true;
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_3) {
				keyboardAttack(Mideas.joueur1().getSpells(2));
				return true;
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_4) {
				keyboardAttack(Mideas.joueur1().getSpells(3));
				return true;
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_5) {
				keyboardAttack(Mideas.joueur1().getSpells(4));
				return true;
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_6) {
				keyboardAttack(Mideas.joueur1().getSpells(5));
				return true;
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_7) {
				keyboardAttack(Mideas.joueur1().getSpells(6));
				return true;
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_8) {
				keyboardAttack(Mideas.joueur1().getSpells(7));
				return true;
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_9) {
				keyboardAttack(Mideas.joueur1().getSpells(8));
				return true;
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_0) {
				keyboardAttack(Mideas.joueur1().getSpells(9));
				return true;
			}
			else if(Keyboard.getEventKey() == 26) {
				keyboardAttack(Mideas.joueur1().getSpells(10));
				return true;
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_EQUALS) {
				keyboardAttack(Mideas.joueur1().getSpells(11));
				return true;
			}
		}
		return false;
	}
	
	public static void keyboardAttack(Shortcut spell) throws FileNotFoundException {
		if(spell != null && spell.getShortcutType() == ShortcutType.SPELL) {
			if(!CastBar.isCasting() && SpellManager.getCd(((SpellShortcut)spell).getSpell().getSpellId()) <= 0 && Mideas.joueur1().getMana() >= ((SpellShortcut)spell).getSpell().getManaCost()) {
				if(((SpellShortcut)spell).getSpell().getCastTime() > 0) {
					isCastingSpell = true;
					CastBar.addCast(new Cast(((SpellShortcut)spell).getSpell().getCastTime(), ((SpellShortcut)spell).getSpell().getName()) {
						@Override
						public void endCastEvent() {
							//((SpellShortcut)spell).getSpell().action(Mideas.joueur1(), Mideas.target());
							CommandSpellCast.write(((SpellShortcut)spell).getSpell());
							checkKeyboardCd(spell);
						}
					});
				}
				else {
					//((SpellShortcut)spell).getSpell().action(Mideas.joueur1(), Mideas.target());
					//CommandSpellCast.write(((SpellShortcut)spell).getSpell());
					checkKeyboardCd(spell);
					Mideas.setCurrentPlayer(false);
				}
			}
			else if(!CastBar.isCasting()) {
				//Mideas.joueur1().tick();
				Mideas.setCurrentPlayer(false);
			}
		}
		else if(spell != null && spell.getShortcutType() == ShortcutType.POTION) {
			spell.use(spell);
		}
		else if(spell != null && spell.getShortcutType() == ShortcutType.STUFF) {
			spell.use(spell);
		}
	}
	
	public static void setIsCastingSpell(boolean we) {
		isCastingSpell = we;
	}
	
	public static boolean getIsCastingSpell() {
		return isCastingSpell;
	}
	

	public static boolean doHealingPotion(Potion item) {
		if(item != null && item.getItemType() == ItemType.POTION) {
			if(Mideas.joueur1().getStamina()+item.getPotionHeal() >= Mideas.joueur1().getMaxStamina() && Mideas.joueur1().getStamina() != Mideas.joueur1().getMaxStamina()) {
				LogChat.setStatusText3("Vous vous �tes rendu "+(Mideas.joueur1().getMaxStamina()-Mideas.joueur1().getStamina())+" hp");
				Mideas.joueur1().setStamina(Mideas.joueur1().getMaxStamina());
				item.setAmount(item.getAmount()-1);
			}
			else if(Mideas.joueur1().getStamina() != Mideas.joueur1().getMaxStamina()) {
				Mideas.joueur1().setStamina(Mideas.joueur1().getStamina()+item.getPotionHeal());
				LogChat.setStatusText3("Vous vous �tes rendu "+item.getPotionHeal()+" hp");
				item.setAmount(item.getAmount()-1);
			}
			else {
				LogChat.setStatusText3("Vos HP �taient d�j� au maximum");
			}
			CharacterStuff.setBagItems();
			if(item.getAmount() <= 0) {
				CharacterStuff.setBagItems();
				return true;
			}
		}
		return false;
	}
	
	static void checkKeyboardCd(Shortcut spell) {
		int i = 0;
		while(i < Mideas.joueur1().getSpells().length) {
			if(spell.getShortcutType() == ShortcutType.SPELL) {
				if(Mideas.joueur1().getSpells(i) != null && Mideas.joueur1().getSpells(i).getShortcutType() == ShortcutType.SPELL && ((SpellShortcut)Mideas.joueur1().getSpells(i)).getSpell().getSpellId() == ((SpellShortcut)spell).getSpell().getSpellId()) {
					spell.setCd(((SpellShortcut)spell).getSpell().getSpellId(), ((SpellShortcut)spell).getSpell().getSpellBaseCd());
					break;
				}
			}
			i++;
		}
	}
	
	public static boolean getHoverSpellBar(int i) {
		return hoveredSlot == i;
	}
	
	private static boolean isEquippedItem(int id) {
		int i = 0;
		while(i < Mideas.joueur1().getStuff().length) {
			if(Mideas.joueur1().getStuff(i) != null && Mideas.joueur1().getStuff(i).getId() == id) {
				return true;
			}
			i++;
		}
		return false;
	}
	
	public static void setNumberFreeSlotBag(int number) {
		numberFreeSlotBag = "("+Integer.toString(number)+")";
	}
}
