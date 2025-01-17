package com.mideas.rpg.v2.chat.channel;

import java.util.ArrayList;
import java.util.HashMap;

import com.mideas.rpg.v2.hud.social.discussion.DiscussionFrame;
import com.mideas.rpg.v2.utils.StringUtils;

public class ChannelMgr {

	private final static HashMap<String, ChatChannel> channelMap = new HashMap<String, ChatChannel>();
	
	public static ArrayList<ChannelMember> getMemberList(String channelName) {
		if(channelMap.containsKey(channelName)) {
			return channelMap.get(channelName).getPlayerList();
		}
		return null;
	}
	
	public static HashMap<String, ChatChannel> getChannelMap() {
		return channelMap;
	}
	
	public static void addChannel(String channelName, String channelID, int value, String password) {
		if(channelMap.containsKey(channelName)) {
			System.out.println("ChannelMgr.addChannel error, channel already exists");
			return;
		}
		ChatChannel channel = new ChatChannel(channelName, channelID, password, value);
		channelMap.put(channelID, channel);
		DiscussionFrame.addChannel(channel);
	}
	
	public static void leaveChannel(String channelName) {
		DiscussionFrame.removeChannel(channelMap.get(channelName));
		channelMap.remove(channelName);
	}
	
	public static void addMember(String channelName, String name, int id) {
		if(!channelMap.containsKey(channelName)) {
			System.out.println("ChannelMgr.addMember error, channel doesn't exist, channelName : "+channelName+", playerName : "+name);
			return;
		}
		channelMap.get(channelName).addMember(new ChannelMember(name, id));
	}
	
	public static int getChannelIndex(String channelName) {
		return channelMap.get(channelName).getValue();
	}
	
	public static String getMemberName(String channelName, int id) {
		return channelMap.get(channelName).getMemberName(id);
	}
	
	public static boolean removeMember(String channelName, int id) {
		return channelMap.get(channelName).removeMember(id);
	}
	
	public static void setLeader(String channelName, int id) {
		channelMap.get(channelName).setLeader(id);
	}
	
	public static String getLeaderName(String channelName) {
		return channelMap.get(channelName).getLeaderName();
	}
	
	public static String getChannelHeader(String channelName) {
		return channelMap.get(channelName).getMessageHeader();
	}
	
	public static int getChannelHeaderWidth(String channelName) {
		return channelMap.get(channelName).getMessageHeaderWidth();
	}
	
	public static void setModerator(String channelName, int id, boolean isModerator) {
		channelMap.get(channelName).setModerator(id, isModerator);
	}
	
	public static int getMemberID(String channelName, String playerName) {
		return channelMap.get(channelName).getMemberID(playerName);
	}
	
	public static ChatChannel getChannelByValue(int value) {
		for(ChatChannel channel : channelMap.values()) {
			if(channel.getValue() == value) {
				return channel;
			}
		}
		return null;
	}
	
	public static String findChannelName(String str) {
		if(StringUtils.isInteger(str)) {
			ChatChannel channel = null;
			channel = getChannelByValue(Integer.parseInt(str));
			if(channel == null) {
				return str;
			}
			return channel.getID();
		}
		return str;
	}
	
	public static int generateChannelID() {
		int i = 1;
		while(i < 10) {
			if(!isIDUsed(i)) {
				break;
			}
			i++;
		}
		return i;
	}
	
	private static boolean isIDUsed(int id) {
		for(ChatChannel channel : channelMap.values()) {
			if(channel.getValue() == id) {
				return true;
			}
		}
		return false;
	}
}
