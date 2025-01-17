package com.mideas.rpg.v2;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.util.ResourceLoader;

public class TTF2 {

	public static TTF buttonFont;
	public static TTF spellName;
	public static TTF hpAndMana;
	public static TTF playerName;
	public static TTF characterFrameStats;
	public static TTF font;
	public static TTF font2;
	public static TTF font3;
	public static TTF font4;
	public static TTF font5;
	public static TTF coin;
	public static TTF coinContainer;
	public static TTF statsName;
	public static TTF itemName;
	public static TTF itemNumber;
	public static TTF talent;
	
	public static void init() throws FontFormatException, IOException {
		InputStream inputStream = ResourceLoader.getResourceAsStream("sprite/police/MORPHEUS.TTF");
		Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		awtFont = awtFont.deriveFont(18f);
	    font = new TTF(awtFont, true);
	    
	    awtFont = new Font("MORPHEUS", Font.BOLD, 11);
	    font2 = new TTF(awtFont, true);
	    
	    awtFont = new Font("MORPHEUS", Font.BOLD, 20);
	    font3 = new TTF(awtFont, true);
	    
		inputStream = ResourceLoader.getResourceAsStream("sprite/police/FRIZQT__.TTF");
		awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		awtFont = awtFont.deriveFont(16f);
	    font4 = new TTF(awtFont, true);
	    
		inputStream = ResourceLoader.getResourceAsStream("sprite/police/FRIZQT__.TTF");
		awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		awtFont = awtFont.deriveFont(13f);
	    playerName = new TTF(awtFont, true);
	    
		inputStream = ResourceLoader.getResourceAsStream("sprite/police/FRIZQT__.TTF");
		awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		awtFont = awtFont.deriveFont(11f);
	    hpAndMana = new TTF(awtFont, true);
	    
	    awtFont = new Font("MORPHEUS", Font.BOLD, 20);
	    font5 = new TTF(awtFont, true);

		inputStream = ResourceLoader.getResourceAsStream("sprite/police/FRIZQT__.TTF");
		awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		awtFont = awtFont.deriveFont(13f);
		buttonFont = new TTF(awtFont, true);

		inputStream = ResourceLoader.getResourceAsStream("sprite/police/FRIZQT__.TTF");
		awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		awtFont = awtFont.deriveFont(21f);
		awtFont = awtFont.deriveFont(Font.BOLD);
		spellName = new TTF(awtFont, true);

		inputStream = ResourceLoader.getResourceAsStream("sprite/police/FRIZQT__.TTF");
		awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream); 
		awtFont = awtFont.deriveFont(15f);
		awtFont = awtFont.deriveFont(Font.BOLD);
		statsName= new TTF(awtFont, true);
		
		inputStream = ResourceLoader.getResourceAsStream("sprite/police/FRIZQT__.TTF");
		awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream); 
		awtFont = awtFont.deriveFont(20f);
		awtFont = awtFont.deriveFont(Font.BOLD);
		itemName = new TTF(awtFont, true);

		inputStream = ResourceLoader.getResourceAsStream("sprite/police/FRIZQT__.TTF");
		awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		awtFont = awtFont.deriveFont(10f);
	    coin = new TTF(awtFont, true);
	    
		inputStream = ResourceLoader.getResourceAsStream("sprite/police/FRIZQT__.TTF");
		awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		awtFont = awtFont.deriveFont(18f);
		awtFont = awtFont.deriveFont(Font.BOLD);
	    coinContainer = new TTF(awtFont, true);
	    
		inputStream = ResourceLoader.getResourceAsStream("sprite/police/FRIZQT__.TTF");
		awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		awtFont = awtFont.deriveFont(14f);
		awtFont = awtFont.deriveFont(Font.BOLD);
		itemNumber = new TTF(awtFont, true);
	    
		inputStream = ResourceLoader.getResourceAsStream("sprite/police/FRIZQT__.TTF");
		awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		awtFont = awtFont.deriveFont(14f);
		awtFont = awtFont.deriveFont(Font.BOLD);
		talent = new TTF(awtFont, true);

		inputStream = ResourceLoader.getResourceAsStream("sprite/police/FRIZQT__.TTF");
		awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		awtFont = awtFont.deriveFont(12f);
		awtFont = awtFont.deriveFont(Font.BOLD);
		characterFrameStats = new TTF(awtFont, true);
	}
}
