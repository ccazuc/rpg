package com.mideas.rpg.v2.hud;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.mideas.rpg.v2.Interface;
import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.Sprites;
import com.mideas.rpg.v2.TTF2;
import com.mideas.rpg.v2.command.CommandCreateCharacter;
import com.mideas.rpg.v2.command.CommandDeleteCharacter;
import com.mideas.rpg.v2.command.CommandLoadCharacter;
import com.mideas.rpg.v2.command.CommandLogout;
import com.mideas.rpg.v2.command.CommandSelectScreenLoadCharacters;
import com.mideas.rpg.v2.game.classes.ClassManager;
import com.mideas.rpg.v2.game.classes.SelectScreenPlayer;
import com.mideas.rpg.v2.game.race.Classe;
import com.mideas.rpg.v2.game.race.Race;
import com.mideas.rpg.v2.utils.Alert;
import com.mideas.rpg.v2.utils.Button;
import com.mideas.rpg.v2.utils.Draw;
import com.mideas.rpg.v2.utils.Input;

public class SelectScreen {

	private static final Color YELLOW = Color.decode("#FFC700");
	static boolean creatingCharacter;
	static boolean deletingCharacter;
	static boolean characterLoaded;
	private static SelectScreenPlayer[] characterList = new SelectScreenPlayer[10];
	private static boolean[] selectedCharacter = new boolean[10];
	private static int selectedCharacterIndex = 0;
	private static int totalCharacter;
	private static boolean init;
	private static float x_hover_race;
	private static float y_hover_race;
	private static float x_hover_classe;
	private static float y_hover_classe;
	private static Race[] race = new Race[]{Race.HUMAN, Race.DWARF, Race.NIGHTELF, Race.GNOME, Race.DRAENEI, Race.ORC, Race.UNDEAD, Race.TAUREN, Race.TROLL, Race.BLOODELF};
	private static Race hoveredRace;
	private static Classe hoveredClasse;
	private static Race selectedRace = Race.UNDEAD;
	private static Classe selectedClasse = Race.HUMAN.getClasse()[0];
	private static float x_selected_race = Display.getWidth()/2-742*Mideas.getDisplayXFactor();
	private static float y_selected_race = Display.getHeight()/2-340*Mideas.getDisplayYFactor()+66*Mideas.getDisplayYFactor();
	private static float x_selected_classe = Display.getWidth()/2-868*Mideas.getDisplayXFactor();
	private static float y_selected_classe = Display.getHeight()/2+99*Mideas.getDisplayYFactor();
	private static Color bgColor = new Color(0, 0, 0, .35f);
	private static Input character = new Input(TTF2.loginScreenAccount, 12);
	static Input deleteCharacter = new Input(TTF2.loginScreenAccount, 8);
	private static Alert alert = new Alert("", Display.getWidth()/2-360*Mideas.getDisplayXFactor(), -20, 700, 130, 230, 38, Display.getHeight()+30, 20, "Ok");
	private static Button newCharacterButton = new Button(Display.getWidth()/2+630*Mideas.getDisplayXFactor(), Display.getHeight()/2+293, 278*Mideas.getDisplayXFactor(), 36, "Create new character", 16) {
		@Override
		public void eventButtonClick() {
			creatingCharacter = true;
		}
	};	
	static Button acceptCharacterButton = new Button(Display.getWidth()/2+705*Mideas.getDisplayXFactor(), Display.getHeight()/2+393*Mideas.getDisplayYFactor(), 195, 34, "Accept", 16) {
		@Override
		public void eventButtonClick() throws SQLException {
			SelectScreen.createCharacter();
			returnCharacterButton.reset();
		}
	};	
	static Button returnCharacterButton = new Button(Display.getWidth()/2+730*Mideas.getDisplayXFactor(), Display.getHeight()/2+442*Mideas.getDisplayYFactor(), 150, 34, "Return", 16) {
		@Override
		public void eventButtonClick() throws SQLException {
			creatingCharacter = false;
			acceptCharacterButton.reset();
		}
	};
	private static Button returnButton = new Button(Display.getWidth()/2+785*Mideas.getDisplayXFactor(), Display.getHeight()/2+438*Mideas.getDisplayYFactor(), 122, 27, "Return", 16) {
		@Override
		public void eventButtonClick() throws NoSuchAlgorithmException, SQLException {
			Interface.setHasLoggedIn(false);
			Mideas.setJoueur1Null();
			Mideas.setAccountId(0);
			LoginScreen.mouseEvent();
			CommandLogout.write();
			SelectScreen.characterLoaded = false;
		}
	};
	private static Button enterGameButton = new Button(Display.getWidth()/2-125*Mideas.getDisplayXFactor(), Display.getHeight()/2+403*Mideas.getDisplayYFactor(), 250, 50, "Enter game", 19) {
		@Override
		public void eventButtonClick() throws NoSuchAlgorithmException, SQLException {
			loadCharacterInfo();
		}
	};
	private static Button deleteCharacterButton = new Button(Display.getWidth()/2+558*Mideas.getDisplayXFactor(), Display.getHeight()/2+438*Mideas.getDisplayYFactor(), 202, 28, "Delete character", 16) {
		@Override
		public void eventButtonClick() throws NoSuchAlgorithmException, SQLException {
			deletingCharacter = true;
			this.reset();
		}
	};
	private static Button confirmDeleteCharacterButton = new Button(Display.getWidth()/2-275*Mideas.getDisplayXFactor(), Display.getHeight()/2+58*Mideas.getDisplayYFactor(), 240, 32, "OK", 20) {
		@Override
		public void eventButtonClick() throws NoSuchAlgorithmException, SQLException {
			deleteCharacter();
		}
		
		@Override
		public boolean activateCondition() {
			if(deleteCharacter.getText().trim().toLowerCase().equals("effacer")) {
				return true;
			}
			return false;
		}
	};
	private static Button cancelDeleteCharacterButton = new Button(Display.getWidth()/2-250*Mideas.getDisplayXFactor(), Display.getHeight()/2+45*Mideas.getDisplayYFactor(), 240, 32, "Annuler", 20) {
		@Override
		public void eventButtonClick() throws NoSuchAlgorithmException, SQLException {
			deletingCharacter = false;
		}
	};

	public static void draw() {
		if(!characterLoaded) {
			loadCharacter();
			selectedCharacter[0] = true;
			characterLoaded = true;
		}
		if(!init) {
			updateSize();
			init = true;
		}
		if(!creatingCharacter) {
			Draw.drawQuadBG(Sprites.select_screen_background);
			if(deletingCharacter) {
				Draw.drawQuad(Sprites.big_alert, Display.getWidth()/2-350*Mideas.getDisplayXFactor(), Display.getHeight()/2-120*Mideas.getDisplayYFactor(), Sprites.big_alert.getImageWidth()*Mideas.getDisplayXFactor(), Sprites.big_alert.getImageHeight()*Mideas.getDisplayYFactor());
				TTF2.selectScreenDeleteCharacterConfirm.drawStringShadow(Display.getWidth()/2-TTF2.selectScreenDeleteCharacterConfirm.getWidth("Voulez-vous effacer")/2*Mideas.getDisplayXFactor(), Display.getHeight()/2-105*Mideas.getDisplayYFactor(), "Voulez-vous effacer", YELLOW, Color.black, 3, 2, 2);
				TTF2.selectScreenDeleteCharacterConfirm.drawStringShadow(Display.getWidth()/2-TTF2.selectScreenDeleteCharacterConfirm.getWidth(characterList[selectedCharacterIndex].getName()+" "+characterList[selectedCharacterIndex].getClasse()+" level "+characterList[selectedCharacterIndex].getLevel())/2*Mideas.getDisplayXFactor(), Display.getHeight()/2-80*Mideas.getDisplayYFactor(), characterList[selectedCharacterIndex].getName()+" "+characterList[selectedCharacterIndex].getClasse()+" level "+characterList[selectedCharacterIndex].getLevel(), Color.white, Color.black, 2, 4, 2);
				TTF2.raceName.drawStringShadow(Display.getWidth()/2-TTF2.raceName.getWidth("Tapez \"EFFACER\" dans le champ pour confirmer.")*Mideas.getDisplayXFactor()/2, Display.getHeight()/2-30*Mideas.getDisplayYFactor(), "Tapez \"EFFACER\" dans le champ pour confirmer." , YELLOW, Color.black, 2, 1, 1);
				Draw.drawQuad(Sprites.input_box, Display.getWidth()/2-Sprites.input_box.getImageWidth()/2*Mideas.getDisplayXFactor(), Display.getHeight()/2-2*Mideas.getDisplayYFactor(), Sprites.input_box.getImageWidth()*Mideas.getDisplayXFactor(), Sprites.input_box.getImageHeight()*Mideas.getDisplayYFactor());
				TTF2.loginScreenAccount.drawStringShadow(Display.getWidth()/2-92*Mideas.getDisplayXFactor(), Display.getHeight()/2+5*Mideas.getDisplayYFactor(), deleteCharacter.getText(), Color.white, Color.black, 1, 1, 1);
				if(System.currentTimeMillis()%1000 < 500) {
					TTF2.selectScreenName.drawString(Display.getWidth()/2-96*Mideas.getDisplayXFactor()+deleteCharacter.getCursorShift(), Display.getHeight()/2+0*Mideas.getDisplayYFactor(), "|", Color.white);
				}
				confirmDeleteCharacterButton.draw();
				cancelDeleteCharacterButton.draw();
			}
			int i = 0;
			float y = 110*Mideas.getDisplayYFactor();
			while(i < characterList.length) {
				drawCharacter(i, y);
				i++;
				y+= 75*Mideas.getDisplayYFactor();
			}
			newCharacterButton.draw();
			returnButton.draw();
			enterGameButton.draw();
			deleteCharacterButton.draw();
		}
		else {
			Draw.drawQuadBG(Sprites.create_character_background);
			alert.draw();
			TTF2.loginScreenAccount.drawStringShadow(Display.getWidth()/2-77*Mideas.getDisplayXFactor(), Display.getHeight()/2+405*Mideas.getDisplayYFactor(), character.getText(), Color.white, Color.black, 1, 1, 2);
			acceptCharacterButton.draw();
			returnCharacterButton.draw();
			int i = 0;
			float x = Display.getWidth()/2-868*Mideas.getDisplayXFactor();
			float y = Display.getHeight()/2+99*Mideas.getDisplayYFactor();
			while(i < selectedRace.getClasse().length) {
				Draw.drawQuad(selectedRace.getClasse()[i].getTexture(),x , y, selectedRace.getClasse()[i].getTexture().getImageWidth()*Mideas.getDisplayXFactor(), selectedRace.getClasse()[i].getTexture().getImageHeight()*Mideas.getDisplayYFactor());
				i++;
				x+= 61;
				if(i == 3) {
					x = Display.getWidth()/2-868*Mideas.getDisplayXFactor();
					y+= 56;
				}
			}
			if(System.currentTimeMillis()%1000 < 500) {
				TTF2.loginScreenTick.drawString(Display.getWidth()/2-84*Mideas.getDisplayXFactor()+character.getCursorShift(), Display.getHeight()/2+393*Mideas.getDisplayYFactor(), "|", Color.white);
			}
			if(hoveredRace != null && hoveredRace != selectedRace) {
				Draw.drawQuad(Sprites.select_screen_hover, x_hover_race+3, y_hover_race+2, Sprites.select_screen_hover.getImageWidth(), Sprites.select_screen_hover.getImageHeight());
				Draw.drawColorQuad(x_hover_race+63*Mideas.getDisplayXFactor(), y_hover_race-35, TTF2.raceName.getWidth(hoveredRace.getName())+50*Mideas.getDisplayXFactor(), 40*Mideas.getDisplayYFactor(), bgColor);
				Draw.drawColorQuadBorder(x_hover_race+63*Mideas.getDisplayXFactor(), y_hover_race-35, TTF2.raceName.getWidth(hoveredRace.getName())+50*Mideas.getDisplayXFactor(), 40*Mideas.getDisplayYFactor(), Color.gray);
				TTF2.raceName.drawStringShadow(x_hover_race+73, y_hover_race-29, hoveredRace.getName(), Color.white, Color.black, 1, 1, 1);
			}
			if(selectedRace != null) {
				Draw.drawQuad(Sprites.select_screen_hover, x_selected_race+3, y_selected_race+2, Sprites.select_screen_hover.getImageWidth(), Sprites.select_screen_hover.getImageHeight());
				TTF2.raceName.drawStringShadow(x_selected_race+30*Mideas.getDisplayXFactor()-TTF2.raceName.getWidth(selectedRace.getName())/2, y_selected_race+40*Mideas.getDisplayYFactor(), selectedRace.getName(), YELLOW, Color.black, 1, 1, 1);
			}
			if(hoveredClasse != null && hoveredClasse != selectedClasse) {
				Draw.drawQuad(Sprites.select_screen_hover, x_hover_classe-1, y_hover_classe-1, Sprites.select_screen_hover.getImageWidth(), Sprites.select_screen_hover.getImageHeight());
				Draw.drawColorQuad(x_hover_classe+63*Mideas.getDisplayXFactor(), y_hover_classe-35, TTF2.raceName.getWidth(hoveredClasse.getName())+50*Mideas.getDisplayXFactor(), 40*Mideas.getDisplayYFactor(), bgColor);
				Draw.drawColorQuadBorder(x_hover_classe+63*Mideas.getDisplayXFactor(), y_hover_classe-35, TTF2.raceName.getWidth(hoveredClasse.getName())+50*Mideas.getDisplayXFactor(), 40*Mideas.getDisplayYFactor(), Color.gray);
				TTF2.raceName.drawStringShadow(x_hover_classe+73, y_hover_classe-29, hoveredClasse.getName(), Color.white, Color.black, 1, 1, 1);
			}
			if(selectedClasse != null) {
				Draw.drawQuad(Sprites.select_screen_hover, x_selected_classe-1, y_selected_classe-1, Sprites.select_screen_hover.getImageWidth(), Sprites.select_screen_hover.getImageHeight());
				TTF2.raceName.drawStringShadow(x_selected_classe+30*Mideas.getDisplayXFactor()-TTF2.raceName.getWidth(selectedClasse.getName())/2, y_selected_classe+40*Mideas.getDisplayYFactor(), selectedClasse.getName(), YELLOW, Color.black, 2, 1, 1);
			}
		}
	}
 
	public static boolean mouseEvent() throws SQLException, NoSuchAlgorithmException {
		if(!creatingCharacter) {
			if(deletingCharacter) {
				confirmDeleteCharacterButton.event();
				cancelDeleteCharacterButton.event();
			}
			else {
				newCharacterButton.event();
				returnButton.event();
				enterGameButton.event();
				deleteCharacterButton.event();
			}
		}
		else {
			alert.event();
			acceptCharacterButton.event();
			returnCharacterButton.event();
			hoveredRace = null;
			hoveredClasse = null;
			selectRace();
			selectClasse();
			if(!Mouse.getEventButtonState()) {
				if(Mouse.getEventButton() == 0) {
					if(hoveredRace != null) {
						selectedRace = hoveredRace;
						x_selected_race = x_hover_race;
						y_selected_race = y_hover_race;
						selectedClasse = selectedRace.getClasse()[0];
						x_selected_classe = Display.getWidth()/2-868*Mideas.getDisplayXFactor();
						y_selected_classe = Display.getHeight()/2+99*Mideas.getDisplayYFactor();
					}
					else if(hoveredClasse != null) {
						selectedClasse = hoveredClasse;
						x_selected_classe = x_hover_classe;
						y_selected_classe = y_hover_classe;
					}
				}
			}
		}
		return true;
	}
	
	public static void event() {
		if(creatingCharacter) {
			character.event();
			if(Keyboard.getEventKey() == Keyboard.KEY_RETURN || Keyboard.getEventKey() == 156) {
				createCharacter();
			}
		}
		else {
			if(deletingCharacter) {
				if(Keyboard.getEventKey() == Keyboard.KEY_RETURN || Keyboard.getEventKey() == 156) {
					deleteCharacter();
				}
				else {
					deleteCharacter.event();
				}
			}
			else {
				if(Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
					if(selectedCharacterIndex < totalCharacter-1) {
						selectedCharacter[selectedCharacterIndex] = false;
						selectedCharacterIndex++;
						selectedCharacter[selectedCharacterIndex] = true;
					}
				}
				else if(Keyboard.getEventKey() == Keyboard.KEY_UP) {
					if(selectedCharacterIndex > 0) {
						selectedCharacter[selectedCharacterIndex] = false;
						selectedCharacterIndex--;
						selectedCharacter[selectedCharacterIndex] = true;
					}
				}
				else if(Keyboard.getEventKey() == Keyboard.KEY_RETURN || Keyboard.getEventKey() == 156) {
					loadCharacterInfo();
				}
			}
		}
	}
	
	static void loadCharacterInfo() {
		Mideas.setCharacterId(characterList[selectedCharacterIndex].getId());
		setPlayer();
		CommandLoadCharacter.write(characterList[selectedCharacterIndex].getId());
	}
	
	static void deleteCharacter() {
		if(deleteCharacter.getText().trim().toLowerCase().equals("effacer")) {
			CommandDeleteCharacter.write(characterList[selectedCharacterIndex].getId());
		}
	}
	
	public static void characterDeleted() {
		deletingCharacter = false;
		Arrays.fill(characterList, null);
		selectedCharacterIndex = 0;
		selectedCharacter[0] = true;
		deleteCharacter.resetText();
	}
	
	private static void setPlayer() {
		if(characterList[selectedCharacterIndex].getClasse().equals("WARRIOR")) {
			Mideas.setJoueur1(ClassManager.getPlayerClone("Guerrier"));
		}
		if(characterList[selectedCharacterIndex].getClasse().equals("PALADIN")) {
			Mideas.setJoueur1(ClassManager.getPlayerClone("Paladin"));
		}
		if(characterList[selectedCharacterIndex].getClasse().equals("ROGUE")) {
			Mideas.setJoueur1(ClassManager.getPlayerClone("Rogue"));
		}
		if(characterList[selectedCharacterIndex].getClasse().equals("WARLOCK")) {
			Mideas.setJoueur1(ClassManager.getPlayerClone("Warlock"));
		}
		if(characterList[selectedCharacterIndex].getClasse().equals("MAGE")) {
			Mideas.setJoueur1(ClassManager.getPlayerClone("Mage"));
		}
		if(characterList[selectedCharacterIndex].getClasse().equals("HUNTER")) {
			Mideas.setJoueur1(ClassManager.getPlayerClone("Hunter"));
		}
		if(characterList[selectedCharacterIndex].getClasse().equals("PRIEST")) {
			Mideas.setJoueur1(ClassManager.getPlayerClone("Priest"));
		}
		if(characterList[selectedCharacterIndex].getClasse().equals("DRUID")) {
			Mideas.setJoueur1(ClassManager.getPlayerClone("Druid"));
		}
	}
	
	private static void selectRace() {
		int i = 0;
		float x = Display.getWidth()/2-875*Mideas.getDisplayXFactor();
		float y = Display.getHeight()/2-340*Mideas.getDisplayYFactor();
		while(i < 10) {
			if(Mideas.getHover() && Mideas.mouseX() >= x && Mideas.mouseX() <= x+63*Mideas.getDisplayXFactor() && Mideas.mouseY() >= y && Mideas.mouseY() <= y+60*Mideas.getDisplayYFactor()) {
				hoveredRace = race[i];
				x_hover_race = x;
				y_hover_race = y;
				Mideas.setHover(false);
				break;
			}
			i++;
			y+= 66*Mideas.getDisplayYFactor();
			if(i == 5) {
				y = Display.getHeight()/2-340*Mideas.getDisplayYFactor();
				x = Display.getWidth()/2-742*Mideas.getDisplayXFactor();
			}
		}
	}
	
	private static void selectClasse() {
		int i = 0;
		float x = Display.getWidth()/2-868*Mideas.getDisplayXFactor();
		float y = Display.getHeight()/2+99*Mideas.getDisplayYFactor();
		while(i < selectedRace.getClasse().length) {
			if(Mideas.getHover() && Mideas.mouseX() >= x && Mideas.mouseX() <= x+58*Mideas.getDisplayXFactor() && Mideas.mouseY() >= y && Mideas.mouseY() <= y+57*Mideas.getDisplayYFactor()) {
				hoveredClasse = selectedRace.getClasse()[i];
				x_hover_classe = x;
				y_hover_classe = y;
				Mideas.setHover(false);
				break;
			}
			i++;
			x+= 61;
			if(i == 3) {
				x = Display.getWidth()/2-868*Mideas.getDisplayXFactor();
				y+= 56;
			}
		}	
	}
	
	static void createCharacter() {
		CommandCreateCharacter.write(character.getText());
	}
	
	public static String getSelectedRace() {
		return selectedRace.toString();
	}
	
	public static String getSelectedClasse() {
		return selectedClasse.toString();
	}
	
	public static void setCreatingCharacter(boolean we) {
		creatingCharacter = we;
	}
	
	public static void setSelectedCharacter(int i, boolean we) {
		selectedCharacter[i] = we;
	}
	
	public static int getSelectedCharacterIndex() {
		return selectedCharacterIndex;
	}
	
	public static void setSelectedCharacterIndex(int we) {
		selectedCharacterIndex = we;
	}
	
	public static int getTotalCharacter() {
		return totalCharacter;
	}
	
	public static void loadCharacter() {
		CommandSelectScreenLoadCharacters.write();
	}
	
	public static void setCharacterList(SelectScreenPlayer player, int i) {
		if(i < characterList.length) {
			characterList[i] = player;
		}
	}
	
	private static void drawCharacter(int i, float y) {
		if(characterList[i] != null) {
			if(selectedCharacter[i]) {
				Draw.drawQuad(Sprites.selected_character, Display.getWidth()/2+600*Mideas.getDisplayXFactor(), y-15, Sprites.selected_character.getImageWidth()*Mideas.getDisplayXFactor(), Sprites.selected_character.getImageHeight()*Mideas.getDisplayYFactor());
			}
			TTF2.selectScreenName.drawStringShadow(Display.getWidth()/2+625*Mideas.getDisplayXFactor(), y, characterList[i].getName(), Color.decode("#FFC700"), Color.black, 2, 1, 2);
			TTF2.selectScreenLevel.drawStringShadow(Display.getWidth()/2+625*Mideas.getDisplayXFactor(), y+27, convClasseToString(characterList[i].getClasse())+" level "+characterList[i].getLevel(), Color.white, Color.black, 1, 1, 2);
		}
	}
	
	private static String convClasseToString(String race) {
		if(race.equals("DEATHKNIGHT")) {
			return "DeathKnight";
		}
		if(race.equals("WARRIOR")) {
			return "Warrior";
		}
		if(race.equals("MAGE")) {
			return "Mage";
		}
		if(race.equals("HUNTER")) {
			return "Hunter";
		}
		if(race.equals("PALADIN")) {
			return "Paladin";
		}
		if(race.equals("WARLOCK")) {
			return "Warlock";
		}
		if(race.equals("MONK")) {
			return "Monk";
		}
		if(race.equals("PRIEST")) {
			return "Priest";
		}
		if(race.equals("ROGUE")) {
			return "Rogue";
		}
		if(race.equals("SHAMAN")) {
			return "Shaman";
		}
		return null;
	}
	
	public static void setTotalCharacter(int i) {
		totalCharacter = i;
	}
	
	public static void updateSize() {
		newCharacterButton.update(Display.getWidth()/2+630*Mideas.getDisplayXFactor(), Display.getHeight()/2+293*Mideas.getDisplayYFactor(), 278*Mideas.getDisplayXFactor(), 36);
		acceptCharacterButton.update(Display.getWidth()/2+705*Mideas.getDisplayXFactor(), Display.getHeight()/2+393*Mideas.getDisplayYFactor(), 195*Mideas.getDisplayXFactor(), 34);
		returnCharacterButton.update(Display.getWidth()/2+730*Mideas.getDisplayXFactor(), Display.getHeight()/2+442*Mideas.getDisplayYFactor(), 150*Mideas.getDisplayXFactor(), 34);
		returnButton.update(Display.getWidth()/2+785*Mideas.getDisplayXFactor(), Display.getHeight()/2+438*Mideas.getDisplayYFactor(), 122*Mideas.getDisplayXFactor(), 28*Mideas.getDisplayXFactor());
		enterGameButton.update(Display.getWidth()/2-125*Mideas.getDisplayXFactor(), Display.getHeight()/2+403*Mideas.getDisplayYFactor(), 250*Mideas.getDisplayXFactor(), 50);
		deleteCharacterButton.update(Display.getWidth()/2+558*Mideas.getDisplayXFactor(), Display.getHeight()/2+438*Mideas.getDisplayYFactor(), 202*Mideas.getDisplayXFactor(), 28);
		alert.setX(-355*Mideas.getDisplayXFactor());
		alert.setY(-60);
		confirmDeleteCharacterButton.update(Display.getWidth()/2-275*Mideas.getDisplayXFactor(), Display.getHeight()/2+58*Mideas.getDisplayYFactor(), 240, 32);
		cancelDeleteCharacterButton.update(Display.getWidth()/2+23*Mideas.getDisplayXFactor(), Display.getHeight()/2+58*Mideas.getDisplayYFactor(), 240, 32);
	}
	
	public static Alert getAlert() {
		return alert;
	}
	
	public static Input getCharacterInput() {
		return character;
	}
}