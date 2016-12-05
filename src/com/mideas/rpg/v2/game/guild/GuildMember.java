package com.mideas.rpg.v2.game.guild;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.FontManager;
import com.mideas.rpg.v2.game.ClassType;
import com.mideas.rpg.v2.game.Joueur;
import com.mideas.rpg.v2.utils.Colors;

public class GuildMember {

	private int id;
	private String name;
	private int level;
	private GuildRank rank;
	private boolean isOnline;
	private String note_save = "";
	private String officer_note_save = "";
	private String note_displayed = "";
	private String officer_note_displayed = "";
	private ClassType classType;
	private String classTypeString;
	private String levelString;
	private Colors color;
	private String informationString;
	private long lastLoginTimer;
	private String lastLoginTimerString;
	
	private final static long MS_IN_A_YEAR = 31536000000l;
	private final static long MS_IN_A_MONTH = 1036800000l;
	private final static long MS_IN_A_WEEK = 604800000l;
	private final static long MS_IN_A_DAY = 86400000l;
	private final static long MS_IN_AN_HOUR = 3600000l;
	private final static long MS_IN_A_MINUTE = 60000l;
	private final static String noNote = "Click here to write a public note";
	private final static String noOfficerNote = "Click here to write an officer note";
	
	public GuildMember(int id, String name, int level, GuildRank rank, boolean isOnline, String note, String officer_note, ClassType classType, long lastLoginTimer) {
		this.id = id;
		this.name = name;
		this.level = level;
		this.levelString = String.valueOf(this.level);
		this.rank = rank;
		this.isOnline = isOnline;
		this.note_displayed = updateNote(note, false);
		this.officer_note_displayed = updateNote(officer_note, true);
		this.note_save = note;
		this.officer_note_save = officer_note;
		this.classType = classType;
		this.color = Joueur.convClassTypeToColor(this.classType);
		this.classTypeString = Joueur.convClassTypeToString(this.classType);
		this.informationString = this.classTypeString+" level "+this.levelString;
		this.lastLoginTimer = lastLoginTimer;
		updateLastLoginTimerString();
	}
	
	public void updateLastLoginTimerString() {
		if(!this.isOnline) {
			long delta = System.currentTimeMillis()-this.lastLoginTimer;
			if(delta >= MS_IN_A_YEAR) {
				this.lastLoginTimerString = (delta/MS_IN_A_YEAR)+" year";
				if(delta/MS_IN_A_YEAR > 1) {
					this.lastLoginTimerString+= "s";
				}
			}
			else if(delta >= MS_IN_A_MONTH) {
				this.lastLoginTimerString = (delta/MS_IN_A_MONTH)+" month";
				if(delta/MS_IN_A_MONTH > 1) {
					this.lastLoginTimerString+= "s";
				}
			}
			else if(delta >= MS_IN_A_WEEK) {
				this.lastLoginTimerString = (delta/MS_IN_A_WEEK)+" week";
				if(delta/MS_IN_A_WEEK > 1) {
					this.lastLoginTimerString+= "s";
				}
			}
			else if(delta >= MS_IN_A_DAY) {
				this.lastLoginTimerString = (delta/MS_IN_A_DAY)+" day";
				if(delta/MS_IN_A_DAY > 1) {
					this.lastLoginTimerString+= "s";
				}
			}
			else if(delta >= MS_IN_AN_HOUR) {
				this.lastLoginTimerString = (delta/MS_IN_AN_HOUR)+" hour";
				if(delta/MS_IN_AN_HOUR > 1) {
					this.lastLoginTimerString+= "s";
				}
			}
			else if(delta >= MS_IN_A_MINUTE) {
				this.lastLoginTimerString = (delta/MS_IN_A_MINUTE)+" minute";
				if(delta/MS_IN_A_MINUTE > 1) {
					this.lastLoginTimerString+= "s";
				}
			}
		}
		else {
			this.lastLoginTimerString = "Online";
		}
 	}
	
	public static String updateNote(String note, boolean isOfficer) {
		StringBuilder builder = new StringBuilder();
		if(note.equals("")) {
			if(isOfficer) {
				note = noOfficerNote;
			}
			else {
				note = noNote;
			}
		}
		int i = 0;
		int x_shift = 0;
		boolean lineChange = false;
		while(i < note.length()) {
			builder.append(note.charAt(i));
			x_shift+= FontManager.get("FRIZQT", 13).getWidth(note.charAt(i));
			i++;
			if(!lineChange && (note.charAt(i-1) == ' ' || note.charAt(i-1) == ',') && x_shift+FontManager.get("FRIZQT", 13).getWidth(nextWord(note, i)) >= 180*Mideas.getDisplayXFactor()) {
				x_shift = 0;
				builder.append("\n");
				lineChange = true;
			}
			else if(lineChange && i < note.length()-3 && x_shift+FontManager.get("FRIZQT", 13).getWidth(note.charAt(i+1))+FontManager.get("FRIZQT", 13).getWidth(note.charAt(i+2))+FontManager.get("FRIZQT", 13).getWidth(note.charAt(i+3)) >= 180*Mideas.getDisplayXFactor()) {
				int j = 0;
				while(j < 3) {
					builder.append('.');
					x_shift+= FontManager.get("FRIZQT", 13).getWidth('.');
					j++;
				}
				return builder.toString();
			}
		}
		return builder.toString();
	}
	
	/*private static String removeLine(String text) {
		StringBuilder builder = new StringBuilder();
		int i = 0;
		while(i < text.length()) {
			if(text.charAt(i) != '\n') {
				builder.append(text.charAt(i));
			}
			i++;
		}
		return builder.toString();
	}*/
	
	private static String nextWord(String text, int i) {
		int start = i;
		while(i < text.length() && text.charAt(i) != ' ' && text.charAt(i) != ',') {
			i++;
		}
		return text.substring(start, i);
	}
	
	public int getId() {
		return this.id;
	}
	
	public ClassType getClassType() {
		return this.classType;
	}
	
	public long getLastLoginTimer() {
		return this.lastLoginTimer;
	}
	
	public void setLastLoginTimer(long lastLoginTimer) {
		this.lastLoginTimer = lastLoginTimer;
	}
	
	public String getLastLoginTimerString() {
		return this.lastLoginTimerString;
	}
	
	public void setLastLoginTimerString(String lastLoginTimerString) {
		this.lastLoginTimerString = lastLoginTimerString;
	}
	
	public String getClassTypeString() {
		return this.classTypeString;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Colors getColor() {
		return this.color;
	}
	
	public String getInformationString() {
		return this.informationString;
	}
	
	public void setLevel(int level) {
		this.level = level;
		this.levelString = String.valueOf(this.level);
		this.informationString = this.classTypeString+" niveau "+this.levelString;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public String getLevelString() {
		return this.levelString;
	}
	
	public void setRank(GuildRank rank) {
		this.rank = rank;
	}
	
	public GuildRank getRank() {
		return this.rank;
	}
	
	public void setOnlineStatus(boolean we) {
		this.isOnline = we;
	}
	
	public boolean isOnline() {
		return this.isOnline;
	}
	
	public void setNoteSave(String note) {
		this.note_save = note;
		this.note_displayed = updateNote(this.note_save, false);
	}
	
	public String getNoteSave() {
		return this.note_save;
	}
	
	public void setNoteDisplayed(String note) {
		this.note_displayed = note;
	}
	
	public String getNoteDisplayed() {
		return this.note_displayed;
	}
	
	public void setOfficerNoteSave(String officer_note) {
		this.officer_note_save = officer_note;
		this.officer_note_displayed = updateNote(this.officer_note_save, true);
	}
	
	public String getOfficerNoteSave() {
		return this.officer_note_save;
	}
	
	public void setOfficerNoteDisplayed(String officer_note) {
		this.officer_note_displayed = officer_note;
	}
	
	public String getOfficerNoteDisplayed() {
		return this.officer_note_displayed;
	}
}
