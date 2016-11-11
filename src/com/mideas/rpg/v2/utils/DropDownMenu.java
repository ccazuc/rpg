package com.mideas.rpg.v2.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.Color;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.Sprites;
import com.mideas.rpg.v2.TTF;

public class DropDownMenu {

	private int x_bar;
	private int y_bar;
	private int x_size_bar;
	private int x_alert;
	private int y_alert;
	private int x_size_alert;
	private int y_size_alert;
	private TTF font;
	private Arrow arrow;
	private ArrayList<TextMenu> menuList;
	private AlertBackground background;
	protected boolean backgroundActive;
	private int backgroundHeight;
	private String selectMenuText;
	private int selectMenuValue;
	
	public DropDownMenu(float x_bar, float y_bar, float x_size_bar, float x_alert, float y_alert, float x_size_alert, float font_size, float opacity) {
		this.x_bar = (int)x_bar;
		this.y_bar = (int)y_bar;
		this.x_size_bar = (int)x_size_bar;
		this.x_alert = (int)x_alert;
		this.y_alert = (int)y_alert;
		this.x_size_alert = (int)x_size_alert;
		this.background = new AlertBackground(x_alert, y_alert, x_size_alert, 0, opacity);
		this.arrow = new Arrow(this.x_bar+this.x_size_alert-Sprites.arrow_bot.getImageWidth()*Mideas.getDisplayXFactor(), this.y_bar, ArrowDirection.BOT) {
			
			@Override
			public void eventButtonClick() {
				DropDownMenu.this.backgroundActive = !DropDownMenu.this.backgroundActive;
			}
		};
		InputStream inputStream = ResourceLoader.getResourceAsStream("sprite/police/FRIZQT__.TTF");
		Font awtFont = null;
		try {
			awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(font_size);
		} 
		catch (FontFormatException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		this.font = new TTF(awtFont, true);
	}
	
	public void draw() {
		int imageWidth = Sprites.drop_down_menu_left_border.getImageWidth();
		int imageHeight = (int)(Sprites.drop_down_menu_left_border.getImageHeight()*Mideas.getDisplayYFactor());
		Draw.drawQuad(Sprites.drop_down_menu_left_border, this.x_bar, this.y_bar, imageWidth, imageHeight);
		Draw.drawQuad(Sprites.drop_down_menu_middle_border, this.x_bar+imageWidth, this.y_bar, this.x_size_bar-2*imageWidth, imageHeight);
		Draw.drawQuad(Sprites.drop_down_menu_right_border, this.x_bar+this.x_size_bar-imageWidth, this.y_bar, imageWidth, imageHeight);
		this.font.drawStringShadow(this.x_bar+10, this.y_bar, this.selectMenuText, Color.white, Color.black, 1, 0, 0);
		this.arrow.draw();
		if(this.backgroundActive) {
			int i = 0;
			this.background.draw();
			while(i < this.menuList.size()) {
				this.menuList.get(i).draw();
				i++;
			}
		}
	}
	
	public boolean event() {
		this.arrow.event();
		if(this.backgroundActive) {
			int i = 0;
			while(i < this.menuList.size()) {
				if(this.menuList.get(i).event()) {
					this.selectMenuValue = this.menuList.get(i).getValue();
					this.selectMenuText = this.menuList.get(i).getText();
					eventButtonClick();
					return true;
				}
				i++;
			}
		}
		return false;
	}
	
	public void resetMenuList() {
		this.menuList.clear();
		this.background.setHeight(0);
	}
	
	public void addMenu(TextMenu menu) {
		menu.setValue(this.menuList.size());
		this.menuList.add(menu);
		this.backgroundHeight+= this.font.getLineHeight()+2;
		this.background.setHeight(this.backgroundHeight);
	}
	
	protected void eventButtonClick() {}
}