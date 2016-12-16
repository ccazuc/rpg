package com.mideas.rpg.v2.hud.social.friends;

import org.lwjgl.input.Mouse;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.Sprites;
import com.mideas.rpg.v2.TTF;
import com.mideas.rpg.v2.FontManager;
import com.mideas.rpg.v2.chat.ChatFrame;
import com.mideas.rpg.v2.command.CommandFriend;
import com.mideas.rpg.v2.command.CommandIgnore;
import com.mideas.rpg.v2.command.CommandParty;
import com.mideas.rpg.v2.game.social.Friend;
import com.mideas.rpg.v2.game.social.Ignore;
import com.mideas.rpg.v2.hud.PopupFrame;
import com.mideas.rpg.v2.utils.Button;
import com.mideas.rpg.v2.utils.Color;
import com.mideas.rpg.v2.utils.Draw;
import com.mideas.rpg.v2.utils.FrameTab;
import com.mideas.rpg.v2.utils.ScrollBar;

import static com.mideas.rpg.v2.hud.social.SocialFrame.Y_SOCIAL_FRAME;

import static com.mideas.rpg.v2.hud.social.SocialFrame.X_SOCIAL_FRAME;

public class FriendsFrame {

	static int selectedFriend = -1;
	static int selectedIgnore = -1;
	private static int hoveredFriend = -1;
	private static int leftButtonDownFriend = -1;
	private static int hoveredIgnore = -1;
	private static int leftButtonDownIgnore = -1;
	private static boolean friendInit;
	private final static int FRIEND_BUTTON_LENGTH = 145;
	private final static int FRIEND_BUTTON_HEIGHT = 25;
	private final static int IGNORE_BUTTON_LENGTH = 145;
	private final static int IGNORE_BUTTON_HEIGHT = 25;
	private final static int FL_MAXIMUM_DISPLAY = 10;
	private final static int IL_MAXIMUM_DISPLAY = 20;
	static boolean friend_tab_active = true;
	private final static Button deleteFriendButton = new Button(X_SOCIAL_FRAME+17*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+437*Mideas.getDisplayYFactor(), FRIEND_BUTTON_LENGTH*Mideas.getDisplayXFactor(), FRIEND_BUTTON_HEIGHT*Mideas.getDisplayXFactor(), "Delete", 12, 1) {
		@Override
		public void eventButtonClick() {
			CommandFriend.removeFriend(Mideas.joueur1().getFriendList().get(selectedFriend).getCharacterId());
		}
		
		@Override
		public boolean activateCondition() {
			return selectedFriend != -1;
		}
	};
	private final static Button addFriendButton = new Button(X_SOCIAL_FRAME+17*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+410*Mideas.getDisplayYFactor(), FRIEND_BUTTON_LENGTH*Mideas.getDisplayXFactor(), FRIEND_BUTTON_HEIGHT*Mideas.getDisplayXFactor(), "Add friend", 12, 1) {
		@Override
		public void eventButtonClick() {
			PopupFrame.activateAddFriendPopupInput();
		}
	};
	private final static Button sendMessageFriendButton = new Button(X_SOCIAL_FRAME+241*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+410*Mideas.getDisplayYFactor(), FRIEND_BUTTON_LENGTH*Mideas.getDisplayXFactor(), FRIEND_BUTTON_HEIGHT*Mideas.getDisplayXFactor(), "Send message", 12, 1) {
		@Override
		public void eventButtonClick() {
			ChatFrame.setWhisper(Mideas.joueur1().getFriendList().get(selectedFriend).getName());
			ChatFrame.setChatActive(true);
		}
		
		@Override
		public boolean activateCondition() {
			return selectedFriend != -1 && Mideas.joueur1().getFriendList().get(selectedFriend).isOnline();
		}
	};
	private final static Button invInParty = new Button(X_SOCIAL_FRAME+241*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+437*Mideas.getDisplayYFactor(), FRIEND_BUTTON_LENGTH*Mideas.getDisplayXFactor(), FRIEND_BUTTON_HEIGHT*Mideas.getDisplayXFactor(), "Inv. in party", 12 , 1) {
		@Override
		public void eventButtonClick() {
			CommandParty.invitePlayer(Mideas.joueur1().getFriendList().get(selectedFriend).getName());
		}
		
		@Override
		public boolean activateCondition() {
			return selectedFriend != -1 && Mideas.joueur1().getFriendList().get(selectedFriend).isOnline();
		}
	};
	private final static Button ignorePlayer = new Button(X_SOCIAL_FRAME+17*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+439*Mideas.getDisplayYFactor(), IGNORE_BUTTON_LENGTH*Mideas.getDisplayXFactor(), IGNORE_BUTTON_HEIGHT*Mideas.getDisplayXFactor(), "Ignore Player", 12 , 1) {
		@Override
		public void eventButtonClick() {
			//enable ignore popup
		}
	};
	private final static Button removeIgnorePlayer = new Button(X_SOCIAL_FRAME+241*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+439*Mideas.getDisplayYFactor(), IGNORE_BUTTON_LENGTH*Mideas.getDisplayXFactor(), IGNORE_BUTTON_HEIGHT*Mideas.getDisplayXFactor(), "Remove Player", 12 , 1) {
		@Override
		public void eventButtonClick() {
			CommandIgnore.removeIgnore(Mideas.joueur1().getIgnoreList().get(selectedIgnore).getId());
		}
		
		@Override
		public boolean activateCondition() {
			return selectedIgnore != -1;
		}
	};
	final static FrameTab friendFrameTab = new FrameTab(X_SOCIAL_FRAME+73*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+45*Mideas.getDisplayYFactor(), 57*Mideas.getDisplayXFactor(), 32*Mideas.getDisplayYFactor(), "Friend", 12, 1, true) {
		
		@Override
		public void eventButtonClick() {
			friend_tab_active = true;
			this.setIsSelected(true);
			ignoreFrameTab.setIsSelected(false);
		}
	};
	final static FrameTab ignoreFrameTab = new FrameTab(X_SOCIAL_FRAME+135*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+45*Mideas.getDisplayYFactor(), 77*Mideas.getDisplayXFactor(), 32*Mideas.getDisplayYFactor(), "Ignore", 12, 1, false) {
		
		@Override
		public void eventButtonClick() {
			friend_tab_active = false;
			this.setIsSelected(true);
			friendFrameTab.setIsSelected(false);
		}
	};
	private final static ScrollBar friendScrollbar = new ScrollBar(X_SOCIAL_FRAME+358*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+77*Mideas.getDisplayYFactor(), 311*Mideas.getDisplayYFactor(), 400*Mideas.getDisplayXFactor(), 307*Mideas.getDisplayYFactor(), false, 32*Mideas.getDisplayYFactor());
	private final static ScrollBar ignoreScrollbar = new ScrollBar(X_SOCIAL_FRAME+358*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+77*Mideas.getDisplayYFactor(), 341*Mideas.getDisplayYFactor(), 400*Mideas.getDisplayXFactor(), 350*Mideas.getDisplayYFactor(), false, 17.6f*Mideas.getDisplayYFactor());

	public static void draw() {
		if(friend_tab_active) {
			if(!friendInit && Mideas.joueur1().getFriendList().size() > 0) {
				selectedFriend = 0;
				friendInit = true;
			}
			final int FL_SIZE = Mideas.joueur1().getFriendList().size();
			Draw.drawQuad(Sprites.friend_frame, X_SOCIAL_FRAME, Y_SOCIAL_FRAME);
			int i = 0;
			int iOffset = 0;
			if(FL_SIZE > 10) {
				friendScrollbar.draw();
				iOffset = i = (int)((FL_SIZE-FL_MAXIMUM_DISPLAY)*friendScrollbar.getScrollPercentage());
			}
			Friend friend;
			float y = Y_SOCIAL_FRAME+82*Mideas.getDisplayYFactor();
			float yShift = 32*Mideas.getDisplayYFactor();
			long timer = System.nanoTime();
			/*while(i < Mideas.joueur1().getFriendList().size()) {
				friend = Mideas.joueur1().getFriendList().get(i);
				if(friend.isOnline()) {
					Draw.drawQuad(Sprites.friend_note_online, X_SOCIAL_FRAME+30*Mideas.getDisplayXFactor(), y+5);
					FontManager.get("FRIZQT", 15).drawStringShadow(X_SOCIAL_FRAME+40*Mideas.getDisplayXFactor(), y, friend.getName(), Color.YELLOW, Color.BLACK, 1, 0, 0);
					FontManager.get("FRIZQT", 15).drawStringShadow(X_SOCIAL_FRAME+40*Mideas.getDisplayXFactor()+FontManager.get("FRIZQT", 15).getWidth(friend.getName()), y, friend.getAreaText(), Color.WHITE, Color.BLACK, 1, 0, 0);
					FontManager.get("FRIZQT", 12).drawStringShadow(X_SOCIAL_FRAME+40*Mideas.getDisplayXFactor(), y+18*Mideas.getDisplayYFactor(), friend.getInfosText(), Color.WHITE, Color.BLACK, 1, 0, 0);
				}
				else {
					Draw.drawQuad(Sprites.friend_note_offline, X_SOCIAL_FRAME+30*Mideas.getDisplayXFactor(), y+5);
					FontManager.get("FRIZQT", 15).drawStringShadow(X_SOCIAL_FRAME+40*Mideas.getDisplayXFactor(), y, friend.getName(), Color.GREY, Color.BLACK, 1, 0, 0);
					FontManager.get("FRIZQT", 15).drawStringShadow(X_SOCIAL_FRAME+40*Mideas.getDisplayXFactor()+FontManager.get("FRIZQT", 15).getWidth(friend.getName()), y, " - offline", Color.GREY, Color.BLACK, 1, 0, 0);
					FontManager.get("FRIZQT", 12).drawStringShadow(X_SOCIAL_FRAME+40*Mideas.getDisplayXFactor(), y+18*Mideas.getDisplayYFactor(), "Unknwon", Color.WHITE, Color.BLACK, 1, 0, 0);
				}
				if(selectedFriend == friend || hoveredFriend == i) {
					if(FL_SIZE > FL_MAXIMUM_DISPLAY) {
						Draw.drawQuadBlend(Sprites.friend_border, X_SOCIAL_FRAME+20*Mideas.getDisplayXFactor(), y, 335*Mideas.getDisplayXFactor(), 34*Mideas.getDisplayYFactor());
					}
					else {
						Draw.drawQuadBlend(Sprites.friend_border, X_SOCIAL_FRAME+20*Mideas.getDisplayXFactor(), y, 355*Mideas.getDisplayXFactor(), 34*Mideas.getDisplayYFactor());
					}
				}
				i++;
				y+= yShift;
				if(y >= Y_SOCIAL_FRAME+400*Mideas.getDisplayYFactor()) {
					break;
				}
			}*/
			TTF font = FontManager.get("FRIZQT", 15);
			font.drawBegin();
			while(i < iOffset+FL_MAXIMUM_DISPLAY) {
				friend = Mideas.joueur1().getFriendList().get(i);
				if(friend.isOnline()) {
					font.drawStringShadowPart(X_SOCIAL_FRAME+40*Mideas.getDisplayXFactor(), y, friend.getName(), Color.YELLOW, Color.BLACK, 1, 0, 0);
					font.drawStringShadowPart(X_SOCIAL_FRAME+40*Mideas.getDisplayXFactor()+FontManager.get("FRIZQT", 15).getWidth(friend.getName()), y, friend.getAreaText(), Color.WHITE, Color.BLACK, 1, 0, 0);
				}
				else {
					font.drawStringShadowPart(X_SOCIAL_FRAME+40*Mideas.getDisplayXFactor(), y, friend.getName(), Color.GREY, Color.BLACK, 1, 0, 0);
					font.drawStringShadowPart(X_SOCIAL_FRAME+40*Mideas.getDisplayXFactor()+FontManager.get("FRIZQT", 15).getWidth(friend.getName()), y, " - offline", Color.GREY, Color.BLACK, 1, 0, 0);
				}
				i++;
				y+= yShift;
			}
			font.drawEnd();
			i = iOffset;
			y = Y_SOCIAL_FRAME+82*Mideas.getDisplayYFactor();
			while(i < iOffset+FL_MAXIMUM_DISPLAY) {
				friend = Mideas.joueur1().getFriendList().get(i);
				if(friend.isOnline()) {
					Draw.drawQuad(Sprites.friend_note_online, X_SOCIAL_FRAME+30*Mideas.getDisplayXFactor(), y+5);
				}
				else {
					Draw.drawQuad(Sprites.friend_note_offline, X_SOCIAL_FRAME+30*Mideas.getDisplayXFactor(), y+5);
				}
				i++;
				y+= yShift;
			}
			i = iOffset;
			y = Y_SOCIAL_FRAME+82*Mideas.getDisplayYFactor();
			font = FontManager.get("FRIZQT", 12);
			font.drawBegin();
			while(i < iOffset+FL_MAXIMUM_DISPLAY) {
				friend = Mideas.joueur1().getFriendList().get(i);
				if(friend.isOnline()) {
					font.drawStringShadowPart(X_SOCIAL_FRAME+40*Mideas.getDisplayXFactor(), y+18*Mideas.getDisplayYFactor(), friend.getInfosText(), Color.WHITE, Color.BLACK, 1, 0, 0);
				}
				else {
					font.drawStringShadowPart(X_SOCIAL_FRAME+40*Mideas.getDisplayXFactor(), y+18*Mideas.getDisplayYFactor(), "Unknwon", Color.WHITE, Color.BLACK, 1, 0, 0);
				}
				i++;
				y+= yShift;
			}
			font.drawEnd();
			y = Y_SOCIAL_FRAME+82*Mideas.getDisplayYFactor();
			if(hoveredFriend != -1 && hoveredFriend >= iOffset && hoveredFriend <= iOffset+IL_MAXIMUM_DISPLAY) {
				Draw.drawQuadBlend(Sprites.friend_border, X_SOCIAL_FRAME+20*Mideas.getDisplayXFactor(), y+(hoveredFriend-iOffset)*yShift, 335*Mideas.getDisplayXFactor(), 34*Mideas.getDisplayYFactor());
			}
			if(selectedFriend != -1 && hoveredFriend != selectedFriend && selectedFriend >= iOffset && selectedFriend < iOffset+IL_MAXIMUM_DISPLAY) {
				Draw.drawQuadBlend(Sprites.friend_border, X_SOCIAL_FRAME+20*Mideas.getDisplayXFactor(), y+(selectedFriend-iOffset)*yShift, 335*Mideas.getDisplayXFactor(), 34*Mideas.getDisplayYFactor());
			}
			Mideas.nTime(timer, "Draw friendlist");
			deleteFriendButton.draw();
			addFriendButton.draw();
			sendMessageFriendButton.draw();
			invInParty.draw();
		}
		else {
			Draw.drawQuad(Sprites.ignore_frame, X_SOCIAL_FRAME, Y_SOCIAL_FRAME+1);
			ignorePlayer.draw();
			removeIgnorePlayer.draw();
			int i = 0;
			float x = X_SOCIAL_FRAME+32*Mideas.getDisplayXFactor();
			float y = Y_SOCIAL_FRAME+80*Mideas.getDisplayYFactor();
			float yShift = 17.6f*Mideas.getDisplayYFactor();
			int iOffset = 0;
			int IGNORE_LIST_SIZE = Mideas.joueur1().getIgnoreList().size();
			if(IGNORE_LIST_SIZE > IL_MAXIMUM_DISPLAY) {
				ignoreScrollbar.draw();
				iOffset = (int)((IGNORE_LIST_SIZE-IL_MAXIMUM_DISPLAY)*ignoreScrollbar.getScrollPercentage());
				i = iOffset;
			}
			long timer = System.nanoTime();
			TTF font = FontManager.get("FRIZQT", 13);
			font.drawBegin();
			while(i < IGNORE_LIST_SIZE) {
				Ignore ignore = Mideas.joueur1().getIgnoreList().get(i);
				font.drawStringShadowPart(x, y, ignore.getName(), Color.YELLOW, Color.BLACK, 1, 0, 0);
				i++;
				y+= yShift;
				if(y >= Y_SOCIAL_FRAME+430*Mideas.getDisplayYFactor()) {
					break;
				}
			}
			i = iOffset;
			font.drawEnd();
			Mideas.nTime(timer, "Ignore text draw time");
			y = Y_SOCIAL_FRAME+80*Mideas.getDisplayYFactor();
			if(hoveredIgnore != -1 && hoveredIgnore >= iOffset && hoveredIgnore <= iOffset+IL_MAXIMUM_DISPLAY) {
				Draw.drawQuadBlend(Sprites.friend_border, x-7*Mideas.getDisplayXFactor(), y+(hoveredIgnore-iOffset)*yShift, 328*Mideas.getDisplayXFactor(), 18*Mideas.getDisplayYFactor());
			}
			if(selectedIgnore != -1 && hoveredIgnore != selectedIgnore && selectedIgnore >= iOffset && selectedIgnore < iOffset+IL_MAXIMUM_DISPLAY) {
				Draw.drawQuadBlend(Sprites.friend_border, x-7*Mideas.getDisplayXFactor(), y+(selectedIgnore-iOffset)*yShift, 328*Mideas.getDisplayXFactor(), 18*Mideas.getDisplayYFactor());
			}
			Mideas.nTime(timer, "Ignore text total draw time");
		}
		friendFrameTab.draw();
		ignoreFrameTab.draw();
	}
	
	public static boolean mouseEvent() {
		if(friendFrameTab.event()) return true;
		if(ignoreFrameTab.event()) return true;
		if(friend_tab_active) {
			hoveredFriend = -1;
			int i = 0;
			final int FL_SIZE = Mideas.joueur1().getFriendList().size();
			if(FL_SIZE > 10) {
				friendScrollbar.draw();
				i = (int)((FL_SIZE-FL_MAXIMUM_DISPLAY)*friendScrollbar.getScrollPercentage());
			}
			float y = Y_SOCIAL_FRAME+80*Mideas.getDisplayYFactor();
			float yShift = 32*Mideas.getDisplayYFactor();
			while(i < Mideas.joueur1().getFriendList().size()) {
				if(isHover(y+i*yShift)) {
					hoveredFriend = i;
					break;
				}
				i++;
			}
			if(hoveredFriend != -1) {
				if(Mouse.getEventButtonState()) {
					if(Mouse.getEventButton() == 0) {
						leftButtonDownFriend = hoveredFriend;
					}
				}
			}
			if(!Mouse.getEventButtonState()) {
				if(Mouse.getEventButton() == 0) {
					if(leftButtonDownFriend != -1 && leftButtonDownFriend == hoveredFriend) {
						selectedFriend = leftButtonDownFriend;
					}
					else {
						leftButtonDownFriend = -1;
					}
				}
			}
			deleteFriendButton.event();
			addFriendButton.event();
			sendMessageFriendButton.event();
			invInParty.event();
			if(Mideas.joueur1().getFriendList().size() > FL_MAXIMUM_DISPLAY) {
				friendScrollbar.event();
			}
		}
		else {
			int i = 0;
			float x = X_SOCIAL_FRAME+25*Mideas.getDisplayXFactor();
			float y = Y_SOCIAL_FRAME+80*Mideas.getDisplayYFactor();
			float yShift = 17.6f*Mideas.getDisplayYFactor();
			int IGNORE_LIST_SIZE = Mideas.joueur1().getIgnoreList().size();
			if(IGNORE_LIST_SIZE > IL_MAXIMUM_DISPLAY) {
				ignoreScrollbar.event();
				i = (int)((IGNORE_LIST_SIZE-IL_MAXIMUM_DISPLAY)*ignoreScrollbar.getScrollPercentage());
			}
			hoveredIgnore = -1;
			while(i < IGNORE_LIST_SIZE) {
				if(Mideas.mouseX() >= x && Mideas.mouseX() <= x+350*Mideas.getDisplayXFactor() && Mideas.mouseY() >= y && Mideas.mouseY() <= y+yShift) {
					hoveredIgnore = i;
				}
				i++;
				y+= yShift;
				if(y >= Y_SOCIAL_FRAME+430*Mideas.getDisplayYFactor()) {
					break;
				}
			}
			if(hoveredIgnore != -1) {
				if(Mouse.getEventButtonState()) {
					if(Mouse.getEventButton() == 0) {
						leftButtonDownIgnore = hoveredIgnore;
					}
				}
			}
			if(!Mouse.getEventButtonState()) {
				if(Mouse.getEventButton() == 0) {
					if(hoveredIgnore != -1 && leftButtonDownIgnore == hoveredIgnore) {
						selectedIgnore = hoveredIgnore;
					}
					else {
						leftButtonDownIgnore = -1;
					}
				}
			}
			if(removeIgnorePlayer.event()) return true;
			if(ignorePlayer.event()) return true;
		}
		return false;
	}
	
	public static void resetSelectedFriend() {
		selectedFriend = -1;
	}
	
	private static boolean isHover(float y) {
		if(Mideas.getHover() && Mideas.mouseX() >= X_SOCIAL_FRAME+20*Mideas.getDisplayXFactor() && Mideas.mouseX() <= X_SOCIAL_FRAME+(20+343)*Mideas.getDisplayXFactor() && Mideas.mouseY() >= y && Mideas.mouseY() <= y+Sprites.friend_border.getImageHeight()*Mideas.getDisplayYFactor()) {
			Mideas.setHover(false);
			return true;
		}
		return false;
	}
	
	public static void updateSize() {
		deleteFriendButton.update(X_SOCIAL_FRAME+17*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+437*Mideas.getDisplayYFactor(), FRIEND_BUTTON_LENGTH*Mideas.getDisplayXFactor(), FRIEND_BUTTON_HEIGHT*Mideas.getDisplayXFactor());
		addFriendButton.update(X_SOCIAL_FRAME+17*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+410*Mideas.getDisplayYFactor(), FRIEND_BUTTON_LENGTH*Mideas.getDisplayXFactor(), FRIEND_BUTTON_HEIGHT*Mideas.getDisplayXFactor());
		sendMessageFriendButton.update(X_SOCIAL_FRAME+241*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+410*Mideas.getDisplayYFactor(), FRIEND_BUTTON_LENGTH*Mideas.getDisplayXFactor(), FRIEND_BUTTON_HEIGHT*Mideas.getDisplayXFactor());
		invInParty.update(X_SOCIAL_FRAME+241*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+437*Mideas.getDisplayYFactor(), FRIEND_BUTTON_LENGTH*Mideas.getDisplayXFactor(), FRIEND_BUTTON_HEIGHT*Mideas.getDisplayXFactor());
		friendFrameTab.update(X_SOCIAL_FRAME+73*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+45*Mideas.getDisplayYFactor(), 57*Mideas.getDisplayXFactor(), 32*Mideas.getDisplayYFactor());
		ignoreFrameTab.update(X_SOCIAL_FRAME+135*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+45*Mideas.getDisplayYFactor(), 77*Mideas.getDisplayXFactor(), 32*Mideas.getDisplayYFactor());
		ignorePlayer.update(X_SOCIAL_FRAME+18*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+439*Mideas.getDisplayYFactor(), IGNORE_BUTTON_LENGTH*Mideas.getDisplayXFactor(), IGNORE_BUTTON_HEIGHT*Mideas.getDisplayXFactor());
		removeIgnorePlayer.update(X_SOCIAL_FRAME+241*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+439*Mideas.getDisplayYFactor(), IGNORE_BUTTON_LENGTH*Mideas.getDisplayXFactor(), IGNORE_BUTTON_HEIGHT*Mideas.getDisplayXFactor());
		friendScrollbar.update(X_SOCIAL_FRAME+358*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+77*Mideas.getDisplayYFactor(), 311*Mideas.getDisplayYFactor(), 400*Mideas.getDisplayXFactor(), 307*Mideas.getDisplayYFactor(), 32*Mideas.getDisplayYFactor());
		ignoreScrollbar.update(X_SOCIAL_FRAME+358*Mideas.getDisplayXFactor(), Y_SOCIAL_FRAME+77*Mideas.getDisplayYFactor(), 341*Mideas.getDisplayYFactor(), 400*Mideas.getDisplayXFactor(), 350*Mideas.getDisplayYFactor(), 17.6f*Mideas.getDisplayYFactor());
	}
}
