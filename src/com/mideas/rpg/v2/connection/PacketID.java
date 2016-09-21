package com.mideas.rpg.v2.connection;

public class PacketID {
	
	public static final byte LOGIN = 1;
	public static final byte LOGOUT = 2;
	public static final byte ALREADY_LOGGED = 3;
	public static final byte LOGIN_ACCEPT = 4;
	public static final byte ACCOUNT_BANNED_TEMP = 5;
	public static final byte ACCOUNT_BANNED_PERM = 6;
	public static final byte LOGIN_WRONG = 7;
	public static final byte SELECT_SCREEN_LOAD_CHARACTERS = 8;
	public static final byte CREATE_CHARACTER = 9;
	public static final byte ERROR_NAME_ALPHABET = 10;
	public static final byte ERROR_NAME_ALREADY_TAKEN = 11;
	public static final byte CHARACTER_CREATED = 12;
	public static final byte DELETE_CHARACTER = 13;
	public static final byte LOAD_CHARACTER = 14;
	public static final byte LOAD_EQUIPPED_ITEMS = 15;
	public static final byte STUFF = 18;
}