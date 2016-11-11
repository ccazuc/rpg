package com.mideas.rpg.v2.command;

import java.util.ArrayList;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.chat.ChatFrame;
import com.mideas.rpg.v2.chat.Message;
import com.mideas.rpg.v2.chat.MessageType;
import com.mideas.rpg.v2.connection.ConnectionManager;
import com.mideas.rpg.v2.connection.PacketID;
import com.mideas.rpg.v2.game.ClassType;
import com.mideas.rpg.v2.game.guild.Guild;
import com.mideas.rpg.v2.game.guild.GuildMember;
import com.mideas.rpg.v2.game.guild.GuildRank;
import com.mideas.rpg.v2.hud.social.GuildInviteNotification;

public class CommandGuild extends Command {
	
	@Override
	public void read() {
		byte packetId = ConnectionManager.getConnection().readByte();
		if(packetId == PacketID.GUILD_UPDATE_PERMISSION) {
			int rank_order = ConnectionManager.getConnection().readInt();
			int permission = ConnectionManager.getConnection().readInt();
			Mideas.joueur1().getGuild().getRank(rank_order).setPermission(permission);
		}
		else if(packetId == PacketID.GUILD_INVITE_PLAYER) {
			String player_name = ConnectionManager.getConnection().readString();
			String guild_name = ConnectionManager.getConnection().readString();
			GuildInviteNotification.setRequest(player_name, guild_name);
			//enable popup
		}
		else if(packetId == PacketID.GUILD_INIT) {
			int i = 0;
			int guildId = ConnectionManager.getConnection().readInt();
			int leaderId = ConnectionManager.getConnection().readInt();
			String guildName = ConnectionManager.getConnection().readString();
			String information = ConnectionManager.getConnection().readString();
			String motd = ConnectionManager.getConnection().readString();
			int rankListSize = ConnectionManager.getConnection().readInt();
			ArrayList<GuildRank> rankList = new ArrayList<GuildRank>(rankListSize);
			while(i < rankListSize) {
				int rank_order = ConnectionManager.getConnection().readInt();
				String name = ConnectionManager.getConnection().readString();
				int permission = ConnectionManager.getConnection().readInt();
				rankList.add(new GuildRank(rank_order, permission, name));
				i++;
			}
			i = 0;
			int memberListSize = ConnectionManager.getConnection().readInt();
			boolean canSeeOfficerNote = ConnectionManager.getConnection().readBoolean();
			ArrayList<GuildMember> memberList = new ArrayList<GuildMember>(memberListSize);
			if(canSeeOfficerNote) {
				while(i < memberListSize) {
					int id = ConnectionManager.getConnection().readInt();
					int level = ConnectionManager.getConnection().readInt();
					String name = ConnectionManager.getConnection().readString();
					ClassType type = ClassType.values()[ConnectionManager.getConnection().readChar()];
					String note = ConnectionManager.getConnection().readString();
					String officerNote = ConnectionManager.getConnection().readString();
					int rank = ConnectionManager.getConnection().readInt();
					boolean isOnline = ConnectionManager.getConnection().readBoolean();
					GuildRank guildRank = null;
					int j = 0;
					while(j < rankList.size()) {
						if(rankList.get(j).getOrder() == rank) {
							guildRank = rankList.get(j);
							break;
						}
						j++;
					}
					memberList.add(new GuildMember(id, name, level, guildRank, isOnline, note, officerNote, type));
					i++;
				}
			}
			else {
				while(i < memberListSize) {
					int id = ConnectionManager.getConnection().readInt();
					int level = ConnectionManager.getConnection().readInt();
					String name = ConnectionManager.getConnection().readString();
					ClassType type = ClassType.values()[ConnectionManager.getConnection().readChar()];
					String note = ConnectionManager.getConnection().readString();
					int rank = ConnectionManager.getConnection().readInt();
					boolean isOnline = ConnectionManager.getConnection().readBoolean();
					GuildRank guildRank = null;
					int j = 0;
					while(j < rankList.size()) {
						if(rankList.get(j).getOrder() == rank) {
							guildRank = rankList.get(j);
							break;
						}
						j++;
					}
					memberList.add(new GuildMember(id, name, level, guildRank, isOnline, note, "", type));
					i++;
				}
			}
			Mideas.joueur1().setGuild(new Guild(guildId, leaderId, guildName, information, motd, memberList, rankList));
			Mideas.joueur1().setGuildRank(Mideas.joueur1().getGuild().getMember(Mideas.joueur1().getId()).getRank());
			Mideas.joueur1().getGuild().getMember(Mideas.joueur1().getId()).setOnlineStatus(true);
			Mideas.joueur1().getGuild().initOnlineMembers();
		}
		else if(packetId == PacketID.GUILD_NEW_MEMBER) {
			int id = ConnectionManager.getConnection().readInt();
			String name = ConnectionManager.getConnection().readString();
			String note = ConnectionManager.getConnection().readString();
			String officerNote = ConnectionManager.getConnection().readString();
			int rankOrder = ConnectionManager.getConnection().readInt();
			GuildRank rank = Mideas.joueur1().getGuild().getRank(rankOrder);
			int level = ConnectionManager.getConnection().readInt();
			boolean isOnline = ConnectionManager.getConnection().readBoolean();
			ClassType type = ClassType.values()[ConnectionManager.getConnection().readChar()];
			Mideas.joueur1().getGuild().addMember(new GuildMember(id, name, level, rank, isOnline, note, officerNote, type));
			ChatFrame.addMessage(new Message(name+" joined the guild.", false, MessageType.SELF));
		}
		else if(packetId == PacketID.GUILD_KICK_MEMBER) {
			int id = ConnectionManager.getConnection().readInt();
			String memberName = ConnectionManager.getConnection().readString();
			String officerName = ConnectionManager.getConnection().readString();
			if(id != Mideas.joueur1().getId()) {
				ChatFrame.addMessage(new Message(memberName+" has been kicked out of the guild by "+officerName, false, MessageType.SELF));
				Mideas.joueur1().getGuild().removeMember(id);
			}
			else {
				ChatFrame.addMessage(new Message("you have been kicked out of the guild by "+officerName, false, MessageType.SELF));
				Mideas.joueur1().setGuild(null);
			}
		}
	}
	
	public static void addMember(String name) {
		ConnectionManager.getConnection().writeByte(PacketID.GUILD);
		ConnectionManager.getConnection().writeByte(PacketID.GUILD_INVITE_PLAYER);
		ConnectionManager.getConnection().writeString(name);
		ConnectionManager.getConnection().send();
	}
	
	public static void acceptRequest() {
		ConnectionManager.getConnection().writeByte(PacketID.GUILD);
		ConnectionManager.getConnection().writeByte(PacketID.GUILD_ACCEPT_REQUEST);
		ConnectionManager.getConnection().send();
	}
	
	public static void declineRequest() {
		ConnectionManager.getConnection().writeByte(PacketID.GUILD);
		ConnectionManager.getConnection().writeByte(PacketID.GUILD_DECLINE_REQUEST);
		ConnectionManager.getConnection().send();
	}
}