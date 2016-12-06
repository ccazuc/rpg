package com.mideas.rpg.v2.utils;

import org.lwjgl.opengl.Display;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.FontManager;

public class Alert {

	private Button button;
	private String text;
	private String[] formatedText;
	private boolean init = true;
	private int baseX;
	private int baseY;
	private int x;
	private int y;
	private float x_size_button;
	private float y_size_button;
	private AlertBackground background;
	private boolean isActive;
	private int diff;
	
	public Alert(String text, float x, float y, float x_size_alert, float y_size_alert, float x_size_button, float y_size_button, float y_button, int font_size, String button_text) {
		this.text = text;
		this.x = Display.getWidth()/2+(int)x;
		this.y = Display.getHeight()/2+(int)y;
		this.baseX = (int)x;
		this.baseY = (int)y;
		this.x_size_button = x_size_button;
		this.y_size_button = y_size_button;
		this.background = new AlertBackground(this.x, this.y, x_size_alert, y_size_alert, .7f);
		this.diff = (int)(y_size_alert-10-FontManager.get("FRIZQT", 22).getLineHeight());
		this.button = new Button(this.x+x_size_alert/2-x_size_button/2, this.y+FontManager.get("FRIZQT", 22).getLineHeight()+this.diff/2-y_size_button/2, x_size_button, y_size_button, button_text, font_size, 2);
	}
	
	public void draw() {
		if(Display.wasResized() || this.init) {
			this.button.setY(this.y+5/Mideas.getDisplayXFactor()+FontManager.get("FRIZQT", 22).getLineHeight()+this.diff/2-this.y_size_button/2);
			this.init = false;
			int i = 0;
			int j = 0;
			int k = 0;
			float lineNumber = (FontManager.get("FRIZQT", 22).getWidth(this.text)/(this.background.getWidth()*Mideas.getDisplayXFactor()-30));
			this.formatedText = new String[(int)Math.ceil(lineNumber)];
			while(i < this.text.length()) {
				if(FontManager.get("FRIZQT", 22).getWidth(this.text.substring(j, i)) < this.background.getWidth()*Mideas.getDisplayXFactor()-30) {
					
				}
				else {
					if(this.text.charAt(i) != ' ') {
						i = checkSpace(this.text, i);
						this.formatedText[k] = this.text.substring(j, i);
						j = i;
						i++;
						k++;
						continue;
					}
					this.formatedText[k] = this.text.substring(j, i);
					j = i;
					i--;
					k++;
				}
				i++;
			}
			if(k < lineNumber) {
				this.formatedText[k] = this.text.substring(j);
			}
		}
		if(this.isActive) {
			/*float xFac = Mideas.getDisplayXFactor();
			float yFac = Mideas.getDisplayYFactor();
			int xTemp = this.x;
			int yTemp = this.y;
			int xSizeAlertTemp = (int) (this.x_size_alert*xFac);
			int ySizeAlertTemp = (int) (this.y_size_alert*yFac);
			int cornerWidth = (int) (Sprites.top_left_corner_alert.getImageWidth()*xFac);
			int cornerHeight = (int) (Sprites.top_left_corner_alert.getImageHeight()*yFac);
			int heightBorderWidth = (int) (Sprites.height_left_border_alert.getImageWidth()*xFac);
			int botBorderHeight = (int) (Sprites.width_bot_border_alert.getImageHeight()*yFac);
			
			Draw.drawQuad(Sprites.top_left_corner_alert, xTemp, yTemp);
			Draw.drawQuad(Sprites.top_right_corner_alert, xTemp+xSizeAlertTemp-cornerWidth, yTemp);
			Draw.drawQuad(Sprites.bot_left_corner_alert, xTemp, yTemp+ySizeAlertTemp-cornerHeight);
			Draw.drawQuad(Sprites.bot_right_corner_alert, xTemp+xSizeAlertTemp-cornerWidth, yTemp+ySizeAlertTemp-cornerHeight);
			
			Draw.drawQuad(Sprites.height_left_border_alert, xTemp, yTemp+cornerHeight, heightBorderWidth, ySizeAlertTemp-cornerHeight*2);
			Draw.drawQuad(Sprites.height_right_border_alert, xTemp+xSizeAlertTemp-cornerWidth+1, yTemp+cornerHeight, heightBorderWidth, ySizeAlertTemp-cornerHeight*2);
			Draw.drawQuad(Sprites.width_bot_border_alert, xTemp+cornerWidth, yTemp+ySizeAlertTemp-botBorderHeight, xSizeAlertTemp-2*cornerWidth, botBorderHeight);
			Draw.drawQuad(Sprites.width_top_border_alert, xTemp+cornerWidth, yTemp, xSizeAlertTemp-2*cornerWidth, botBorderHeight);
			
			Draw.drawColorQuad(xTemp+cornerWidth, yTemp+cornerHeight, xSizeAlertTemp-2*cornerWidth+1, ySizeAlertTemp-2*cornerHeight, this.bgColor);*/
			/*float xFac = Mideas.getDisplayXFactor();
			float yFac = Mideas.getDisplayYFactor();
			int imageWidth = (int) (Sprites.top_left_corner_alert.getImageWidth()*xFac);
			int imageHeight = (int) (Sprites.top_left_corner_alert.getImageHeight()*yFac);
			Draw.drawQuad(Sprites.top_left_corner_alert, this.x, this.y, imageWidth, imageHeight);
			Draw.drawQuad(Sprites.width_top_border_alert, this.x+imageWidth, this.y, this.x_size_alert-2*imageWidth, imageHeight);
			Draw.drawQuad(Sprites.top_right_corner_alert, this.x+this.x_size_alert-imageWidth, this.y, imageWidth, imageHeight);
			
			Draw.drawQuad(Sprites.bot_left_corner_alert, this.x, this.y+this.y_size_alert-imageHeight, imageWidth, imageHeight);
			Draw.drawQuad(Sprites.width_bot_border_alert, this.x+imageWidth, this.y+this.y_size_alert-imageHeight, this.x_size_alert-2*imageWidth, imageHeight);
			Draw.drawQuad(Sprites.bot_right_corner_alert, this.x+this.x_size_alert-imageWidth, this.y+this.y_size_alert-imageHeight, imageWidth, imageHeight);
			Draw.drawQuad(Sprites.height_left_border_alert, this.x, this.y+imageHeight, imageWidth, this.y_size_alert-2*imageHeight);
			Draw.drawQuad(Sprites.height_right_border_alert, this.x+this.x_size_alert-imageWidth, this.y+imageHeight, imageWidth, this.y_size_alert-2*imageHeight);
			Draw.drawColorQuad(this.x+imageWidth, this.y+imageHeight, this.x_size_alert-2*imageWidth, this.y_size_alert-2*imageHeight, this.bgColor);*/
			this.background.draw();
			int i = 0;
			int y_shift = 0;
			FontManager.get("FRIZQT", 22).drawBegin();
			if(this.formatedText.length == 1) {
				FontManager.get("FRIZQT", 22).drawStringShadowPart(this.x+this.background.getWidth()/2-FontManager.get("FRIZQT", 22).getWidth(this.formatedText[0])/2, this.y+14, this.formatedText[0], Color.YELLOW, Color.BLACK, 3, 2, 2);
			}
			else {
				while(i < this.formatedText.length) {
					FontManager.get("FRIZQT", 22).drawStringShadowPart(this.x+10, this.y+14+y_shift, this.formatedText[i], Color.YELLOW, Color.BLACK, 2, 2, 1);
					y_shift+= FontManager.get("FRIZQT", 22).getLineHeight()+3;
					i++;
				}
			}
			FontManager.get("FRIZQT", 22).drawEnd();
			this.button.draw();
		}
	}
	
	public boolean event() {
		if(this.isActive) {
			if(this.button.event()) {
				this.button.reset();
				this.isActive = false;
				return true;
			}
		}
		return false;
	}
	
	public void setWidth(float width) {
		this.background.setWidth(width);
		this.button.setX(this.x+this.background.getWidth()/2-this.x_size_button/2);
	}
	
	public void setX(float x) {
		this.x = Display.getWidth()/2+(int)x;
		this.background.setX(this.x);
		this.button.setX(this.x+this.background.getWidth()/2-this.x_size_button/2);
	}
	
	public void setY(float y) {
		this.y = Display.getHeight()/2+(int)y;
		this.background.setY(this.y);
		this.button.setY(this.y+FontManager.get("FRIZQT", 22).getLineHeight()+this.diff/2-this.y_size_button/2);
	}
	
	public void setText(String text) {
		this.text = text;
		int i = 0;
		int j = 0;
		int k = 0;
		float lineNumber = (FontManager.get("FRIZQT", 22).getWidth(this.text)/(this.background.getWidth()*Mideas.getDisplayXFactor()-30));
		this.formatedText = new String[(int)Math.ceil(lineNumber)];
		while(i < this.text.length()) {
			if(FontManager.get("FRIZQT", 22).getWidth(this.text.substring(j, i)) < this.background.getWidth()*Mideas.getDisplayXFactor()-30) {
			}
			else {
				if(this.text.substring(i, i+1).charAt(0) != ' ') {
					i = checkSpace(this.text, i);
					this.formatedText[k] = this.text.substring(j, i);
					j = i;
					i++;
					k++;
					continue;
				}
				this.formatedText[k] = this.text.substring(j, i);
				j = i;
				i--;
				k++;
			}
			i++;
		}
		if(k < lineNumber) {
			this.formatedText[k] = this.text.substring(j);
		}
	}
	
	public int getBaseX() {
		return this.baseX;
	}
	
	public int getBaseY() {
		return this.baseY;
	}
	
	public void setActive() {
		this.isActive = true;
		this.event();
	}
	
	public void setInactive() {
		this.isActive = false;
		this.button.reset();
	}
	
	public boolean isActive() {
		return this.isActive;
	}
	
	private static int checkSpace(String text, int i) {
		while(text.substring(i, i+1).charAt(0) != ' ' || text.substring(i, i+1).charAt(0) != ',') {
			i--;
		}
		return i;
	}
}
