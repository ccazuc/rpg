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
	public static final byte ERROR_NAME_LENGTH = 12;
	public static final byte CHARACTER_CREATED = 13;
	public static final byte DELETE_CHARACTER = 14;
	public static final byte LOAD_CHARACTER = 15;
	public static final byte LOAD_EQUIPPED_ITEMS = 16;
	public static final byte LOAD_BAG_ITEMS = 17;
	public static final byte LOAD_SPELLBAR = 18;
	public static final byte STUFF = 19;
	public static final byte WEAPON = 20;
	public static final byte GEM = 21;
	public static final byte POTION = 22;
	public static final byte SEND_EQUIPPED_ITEMS = 23;
	public static final byte SEND_BAG_ITEMS = 24;
	public static final byte PING = 25;
	public static final byte PING_CONFIRMED = 26;
	public static final byte SEND_SINGLE_BAG_ITEM = 27;
	public static final byte SEND_SINGLE_EQUIPPED_ITEM = 28;
	public static final byte SEND_SINGLE_SPELLBAR_ITEM = 29;
	public static final byte LOAD_STATS = 30;
	public static final byte ADD_ITEM = 31;
	public static final byte ADD_ITEM_CONFIRMED = 32;
	public static final byte REQUEST_ITEM = 33;
	public static final byte KNOWN_ITEM = 34;
	public static final byte UNKNOWN_ITEM = 35;
	public static final byte CHAT_LIST_PLAYER = 36;
	public static final byte CHAT_SET_STAMINA = 37;
	public static final byte CHAT_SET_MANA = 38;
	public static final byte CHAT_PLAYER_INFO = 39;
	public static final byte CHAT_NOT_ALLOWED = 40;
	public static final byte CHAT_SET = 41;
	public static final byte CHAT_SET_GOLD = 42;
	public static final byte CHAT_SET_EXPERIENCE = 43;
	public static final byte CHAT_GET = 44;
	public static final byte CHAT_GET_STAMINA = 45;
	public static final byte CHAT_GET_MANA = 46;
	public static final byte CHAT_GET_GOLD = 47;
	public static final byte CHAT_GET_EXPERIENCE = 48;
	public static final byte CHAT_GET_ID = 49;
	public static final byte CHAT_GET_IP = 50;
	public static final byte STRING = 51;
	public static final byte INT = 52;
	public static final byte SPELL_CAST = 53;
	public static final byte UPDATE_STATS = 54;
	public static final byte UPDATE_STATS_STAMINA = 55;
	public static final byte UPDATE_STATS_MANA = 56;
	public static final byte CHARACTER_LOGOUT = 57;
	public static final byte UPDATE_STATS_EXPERIENCE = 58;
	public static final byte UPDATE_STATS_GOLD = 59;
	public static final byte TRADE = 60;
	public static final byte TRADE_NEW = 61;
	public static final byte TRADE_ADD_ITEM = 62;
	public static final byte TRADE_ACCEPT = 63;
	public static final byte TRADE_NEW_CONFIRM = 64;
	public static final byte TRADE_REQUEST = 65;
	public static final byte TRADE_CLOSE = 66;
	public static final byte TRADE_UNACCEPT = 67;
	public static final byte TRADE_REMOVE_ITEM = 68;
	public static final byte TRADE_ADD_ITEM_ERROR = 69;
	public static final byte TRADE_SEND_ALL_ITEMS = 70;
	public static final byte FRIEND = 71;
	public static final byte FRIEND_SEND_INFO = 72;
	public static final byte FRIEND_ADD = 73;
	public static final byte LOGIN_NEW_KEY = 74;
	public static final byte REGISTER_WORLD_SERVER = 75;
	public static final byte SEND_REALM_LIST = 76;
	public static final byte LOGIN_REALM = 77;
	public static final byte LOGIN_REALM_ACCEPTED = 78;
	public static final byte LOGIN_REALM_REQUEST = 79;
	public static final byte LOGIN_REALM_SUCCESS = 80;
	public static final byte FRIEND_OFFLINE = 81;
	public static final byte FRIEND_ONLINE = 82;
	public static final byte FRIEND_REMOVE = 83;
	public static final byte PLAYER_NOT_FOUND = 84;
	public static final byte SEND_MESSAGE = 85;
	public static final byte PARTY = 86;
	public static final byte PARTY_ADD_MEMBER = 87;
	public static final byte PARTY_DECLINE_REQUEST = 88;
	public static final byte PARTY_ACCEPT_REQUEST = 89;
	public static final byte PARTY_MEMBER_JOINED = 90;
	public static final byte PARTY_NEW = 91;
	public static final byte PARTY_DISBAND = 92;
	public static final byte PARTY_LEFT = 93;
	public static final byte PARTY_MEMBER_LEFT = 94;
	public static final byte PARTY_SET_LEADER = 95;
}
