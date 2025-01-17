package com.mideas.rpg.v2.files.dbc;

import java.io.File;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.lwjgl.Sys;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.connection.Buffer;
import com.mideas.rpg.v2.files.logs.LogsMgr;
import com.mideas.rpg.v2.game.aura.AuraMgr;

public class AuraDBC {


	private final static String FOLDER_PATH = "Data/";
	private final static String FILE_PATH = "Data/Aura.dbc";
	private static Buffer readBuffer;
	private static Buffer readBufferHeader = new Buffer(12);
	private final static byte[] HEADER_SIGNATURE = new byte[] {(byte)87, (byte)68, (byte)66, (byte)67};
	
	public static void readFile() {
		try {
			if(!checkFileStatus()) {
				System.out.println("**ERROR** "+FILE_PATH+" not found, the game will now exit.");
				Sys.alert("Error", FILE_PATH + "not found, the game will now exit.");
				Mideas.closeGame();
				return;
			}
			FileChannel fc = (FileChannel) Files.newByteChannel(Paths.get(FILE_PATH), StandardOpenOption.READ);
			readBufferHeader.setOrder(ByteOrder.BIG_ENDIAN);
			fc.read(readBufferHeader.getBuffer());
			readBufferHeader.flip();
			int i = 0;
			while(i < HEADER_SIGNATURE.length) {
				if(readBufferHeader.readByte() != HEADER_SIGNATURE[i]) {
					System.out.println("**ERROR** "+FILE_PATH+" invalid signature, the game will now exit.");
					Sys.alert("Error", FILE_PATH + "'s header is corrupted, the game will now exit.");
					Mideas.closeGame();
					return;
				}
				i++;
			}
			int fileSize = readBufferHeader.readInt();
			int numberAura = readBufferHeader.readInt();
			readBuffer = new Buffer(fileSize);
			readBuffer.setOrder(ByteOrder.BIG_ENDIAN);
			fc.read(readBuffer.getBuffer());
			readBuffer.flip();
			i = 0;
			LogsMgr.writeMiscLog("Started reading Aura.dbc");
			while(readBuffer.hasRemaining()) {
				AuraMgr.storeAura(readBuffer.readAura());
				i++;
 			}
			LogsMgr.writeMiscLog("Finished reading Aura.dbc");
			if(i != numberAura) {
				System.out.println("**ERROR** "+FILE_PATH+" number of aura is not correct, the game will now exit.");
				LogsMgr.writeMiscLog("Error while reading Aura.dbc, incorrect number of row.");
				Mideas.closeGame();
				return;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean checkFileStatus() {
		File folder = new File(FOLDER_PATH);
		if(!folder.exists()) {
			return false;
		}
		File file = new File(FILE_PATH);
		if(!file.exists()) {
			return false;
		}
		return true;
	}
}
