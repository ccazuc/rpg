package com.mideas.rpg.v2.command;

import com.mideas.rpg.v2.callback.CallbackMgr;
import com.mideas.rpg.v2.connection.ConnectionManager;
import com.mideas.rpg.v2.connection.PacketID;
import com.mideas.rpg.v2.game.item.Item;
import com.mideas.rpg.v2.game.mail.Mail;
import com.mideas.rpg.v2.game.mail.MailMgr;

public class CommandMail extends Command {

	@Override
	public void read()
	{
		short packetId = ConnectionManager.getWorldServerConnection().readShort();
		System.out.println("CommandMail");
		if (packetId == PacketID.MAIL_INIT)
		{
			int numberMail = ConnectionManager.getWorldServerConnection().readInt();
			System.out.println("Mal init size: " + numberMail);
			int i = -1;
			while (++i < numberMail)
			{
				MailMgr.addMail(readMail());
			}
			CallbackMgr.onMailReceived();
		}
		else if (packetId == PacketID.MAIL_SEND)
		{
			CallbackMgr.onMailSent();
		}
		else if (packetId == PacketID.MAIL_DELETE)
		{
			long GUID = ConnectionManager.getWorldServerConnection().readLong();
			Mail mail = MailMgr.getMail(GUID);
			MailMgr.removeMail(GUID);
			CallbackMgr.onMailDeleted(mail);
		}
		else if (packetId == PacketID.MAIL_OPENED)
		{
			long GUID = ConnectionManager.getWorldServerConnection().readLong();
			MailMgr.mailOpened(GUID);
		}
		else if (packetId == PacketID.MAIL_TAKE_ITEM)
		{
			
		}
		else if (packetId == PacketID.MAIL_RECEIVED)
		{
			MailMgr.addMail(readMail());
			CallbackMgr.onMailReceived();
		}
		else if (packetId == PacketID.MAIL_LOAD_MAIL)
		{
			long GUID = ConnectionManager.getWorldServerConnection().readLong();
			String content = ConnectionManager.getWorldServerConnection().readString();
			Mail mail = MailMgr.getMail(GUID);
			mail.setContent(content);
			mail.setIsLoaded();
			CallbackMgr.onMailLoaded(mail);
		}
	}
	
	public static Mail readMail()
	{
		long GUID = ConnectionManager.getWorldServerConnection().readLong();
		long deleteDate = ConnectionManager.getWorldServerConnection().readLong();
		String authorName = ConnectionManager.getWorldServerConnection().readString();
		String title = ConnectionManager.getWorldServerConnection().readString();
		int gold = ConnectionManager.getWorldServerConnection().readInt();
		boolean isCR = ConnectionManager.getWorldServerConnection().readBoolean();
		byte template = ConnectionManager.getWorldServerConnection().readByte();
		boolean read = ConnectionManager.getWorldServerConnection().readBoolean();
		boolean canReply = ConnectionManager.getWorldServerConnection().readBoolean();
		return (new Mail(GUID, deleteDate, authorName, title, "", gold, isCR, template, read, canReply));
	}
	
	public static void loadMail(Mail mail)
	{
		ConnectionManager.getWorldServerConnection().startPacket();
		ConnectionManager.getWorldServerConnection().writeShort(PacketID.MAIL);
		ConnectionManager.getWorldServerConnection().writeShort(PacketID.MAIL_LOAD_MAIL);
		ConnectionManager.getWorldServerConnection().writeLong(mail.getGUID());
		ConnectionManager.getWorldServerConnection().endPacket();
		ConnectionManager.getWorldServerConnection().send();
	}
	
	public static void sendMail(String destination, String title, String content, boolean isCr, int gold, Item[] itemList)
	{
		ConnectionManager.getWorldServerConnection().startPacket();
		ConnectionManager.getWorldServerConnection().writeShort(PacketID.MAIL);
		ConnectionManager.getWorldServerConnection().writeShort(PacketID.MAIL_SEND);
		ConnectionManager.getWorldServerConnection().writeString(destination);
		ConnectionManager.getWorldServerConnection().writeString(title);
		ConnectionManager.getWorldServerConnection().writeString(content);
		ConnectionManager.getWorldServerConnection().writeInt(gold);
		ConnectionManager.getWorldServerConnection().writeBoolean(isCr);
		ConnectionManager.getWorldServerConnection().endPacket();
		ConnectionManager.getWorldServerConnection().send();
	}
	
	public static void mailOpened(Mail mail)
	{
		ConnectionManager.getWorldServerConnection().startPacket();
		ConnectionManager.getWorldServerConnection().writeShort(PacketID.MAIL);
		ConnectionManager.getWorldServerConnection().writeShort(PacketID.MAIL_OPENED);
		ConnectionManager.getWorldServerConnection().writeLong(mail.getGUID());
		ConnectionManager.getWorldServerConnection().endPacket();
		ConnectionManager.getWorldServerConnection().send();
	}
	
	public static void deleteMail(Mail mail)
	{
		ConnectionManager.getWorldServerConnection().startPacket();
		ConnectionManager.getWorldServerConnection().writeShort(PacketID.MAIL);
		ConnectionManager.getWorldServerConnection().writeShort(PacketID.MAIL_DELETE);
		ConnectionManager.getWorldServerConnection().writeLong(mail.getGUID());
		ConnectionManager.getWorldServerConnection().endPacket();
		ConnectionManager.getWorldServerConnection().send();
	}
	
	public static void returnMail(Mail mail)
	{
		ConnectionManager.getWorldServerConnection().startPacket();
		ConnectionManager.getWorldServerConnection().writeShort(PacketID.MAIL);
		ConnectionManager.getWorldServerConnection().writeShort(PacketID.MAIL_RETURN);
		ConnectionManager.getWorldServerConnection().writeLong(mail.getGUID());
		ConnectionManager.getWorldServerConnection().endPacket();
		ConnectionManager.getWorldServerConnection().send();
	}
}
