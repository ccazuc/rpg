package com.mideas.rpg.v2;

import java.io.IOException;
import java.util.Random;

import com.mideas.rpg.v2.utils.Texture;

public class Sprites {

	private static boolean spriteLoaded;
	public static Texture loading_screen;
	public static Texture stuff_hover2;
	public static Texture playerUI;
	public static Texture admin_panel;
	public static Texture hover;
	public static Texture bag_hover2;
	public static Texture close_bag_button;
	public static Texture close_bag_button_hover;
	public static Texture un_green_talent;
	public static Texture deux_green_talent;
	public static Texture trois_green_talent;
	public static Texture quatre_green_talent;
	public static Texture trois_yellow_square_talent;
	public static Texture cinq_yellow_square_talent;
	public static Texture deux_yellow_square_talent;
	public static Texture left_second_line_talent;
	public static Texture cinq_green_talent;
	public static Texture spell_hover;
	public static Texture spell_hover2;
	public static Texture current_bg;
	public static Texture bg;
	public static Texture bc_bg;
	public static Texture doge_bg;
	public static Texture sunwell1_bg;
	public static Texture sunwell2_bg;
	public static Texture illidan_bg;
	public static Texture resized_base_bg;
	public static Texture resized_doge_bg;
	public static Texture resized_bc_bg;
	public static Texture resized_sunwell1_bg;
	public static Texture resized_sunwell2_bg;
	public static Texture resized_illidan_bg;
	public static Texture bag_open_border;
	public static Texture spell_border;
	public static Texture illidan;
	public static Texture priest;
	public static Texture priest2;
	public static Texture mage;
	public static Texture mage2;
	public static Texture war;
	public static Texture war2;
	public static Texture deathknight;
	public static Texture deathknight2;
	public static Texture hunter;
	public static Texture hunter2;
	public static Texture monk;
	public static Texture monk2;
	public static Texture paladin;
	public static Texture paladin2;
	public static Texture rogue;
	public static Texture rogue2;
	public static Texture shaman;
	public static Texture shaman2;
	public static Texture warlock;
	public static Texture warlock2;
	public static Texture playerUI2;
	public static Texture deathknight_create_classe;
	public static Texture hunter_create_classe;
	public static Texture mage_create_classe;
	public static Texture monk_create_classe;
	public static Texture paladin_create_classe;
	public static Texture priest_create_classe;
	public static Texture rogue_create_classe;
	public static Texture shaman_create_classe;
	public static Texture warlock_create_classe;
	public static Texture warrior_create_classe;
	public static Texture deathknight_create_classe_level;
	public static Texture hunter_create_classe_level;
	public static Texture mage_create_classe_level;
	public static Texture monk_create_classe_level;
	public static Texture paladin_create_classe_level;
	public static Texture priest_create_classe_level;
	public static Texture rogue_create_classe_level;
	public static Texture shaman_create_classe_level;
	public static Texture warlock_create_classe_level;
	public static Texture warrior_create_classe_level;
	public static Texture button;
	public static Texture button2;
	public static Texture button_down;
	public static Texture button_hover;
	public static Texture button_hover2;
	public static Texture button_disabled;
	public static Texture tooltip;
	public static Texture cursor;
	public static Texture cursor_buy;
	public static Texture alert;
	public static Texture alert_dungeon;
	public static Texture character_frame;
	public static Texture character_frame2;
	public static Texture character_frame_stats;
	public static Texture shop_frame;
	public static Texture class_choose;
	public static Texture bag_hover;
	public static Texture bag_border1;
	public static Texture bag_click_hover;
	public static Texture inventory_click_hover;
	public static Texture gem_red;
	public static Texture gem_blue;
	public static Texture gem_yellow;
	public static Texture gem_meta;
	public static Texture bag4;
	public static Texture bag6;
	public static Texture bag8;
	public static Texture bag10;
	public static Texture bag12;
	public static Texture bag14;
	public static Texture bag16;
	public static Texture bag18;
	public static Texture bag20;
	public static Texture back_bag;
	public static Texture back_bag2;
	public static Texture back_bag3;
	public static Texture back_bag4;
	public static Texture icon_bag;
	public static Texture large_spellbar;
	public static Texture spellbar;
	public static Texture spell_attack;
	public static Texture spell_book_heroic_strike;
	public static Texture spell_book_charge;
	public static Texture spell_book_mortal_strike;
	public static Texture spell_book_thunder_clap;
	public static Texture spell_book_rend;
	public static Texture spell_book_grey_charge;
	public static Texture spell_book_grey_mortal_strike;
	public static Texture spell_book_grey_thunder_clap;
	public static Texture spell_book_grey_rend;
	public static Texture border;
	public static Texture border2;
	public static Texture border3;
	public static Texture border4;
	public static Texture border5;
	public static Texture border6;
	public static Texture border7;
	public static Texture border8;
	public static Texture border9;
	public static Texture border10;
	public static Texture close_button_inventory_hover;
	public static Texture close_button_inventory_down;
	public static Texture close_button_inventory_down_hover;
	public static Texture close_shop_hover;
	public static Texture close_bag_button_down;
	public static Texture close_bag_button_down_hover;
	public static Texture shop_hover;
	public static Texture stuff_border;
	public static Texture stuff_hover;
	public static Texture draggedspell_border;
	public static Texture super_healing_potion;
	public static Texture bag_super_healing_potion;
	public static Texture shop_super_healing_potion;
	public static Texture left_colored_arrow;
	public static Texture right_colored_arrow;
	public static Texture right_uncolored_arrow;
	public static Texture right_arrow_hover;
	public static Texture left_arrow_hover;
	public static Texture gold_coin;
	public static Texture gold_coin_container;
	public static Texture silver_coin;
	public static Texture silver_coin_container;
	public static Texture copper_coin;
	public static Texture copper_coin_container;
	public static Texture warrior_talent_tree;
	public static Texture escape_frame;
	public static Texture level;
	public static Texture spellbook_page1;
	public static Texture spellbook_spell_bg;
	public static Texture spellbook_spell_bg_grey;
	public static Texture close_spell_book_hover;
	public static Texture linen_cloth;
	public static Texture bag_linen_cloth;
	public static Texture craft_frame;
	public static Texture elevator_button;
	public static Texture shop_border;
	public static Texture chat_button;
	public static Texture up_chat_button;
	public static Texture final_spellbar;
	public static Texture spellbar_case;
	public static Texture spellbar_case2;
	//public static Texture talent_frame_open;
	//public static Texture spellbook_frame_open;
	//public static Texture character_frame_open;
	//public static Texture escape_frame_open;
	public static Texture equipped_item_frame;
	public static Texture shortcut_hover;
	public static Texture bag_hover_tooltip;
	public static Texture loading_screen_bar1;
	public static Texture itemnumber_frame;
	public static Texture itemnumber_hover_ok;
	public static Texture itemnumber_hover_cancel;
	public static Texture itemnumber_leftyellow_arrow;
	public static Texture itemnumber_rightgray_arrow;
	public static Texture loading_screen_bar2;
	public static Texture loading_screen_bar3;
	public static Texture loading_screen_bar4;
	public static Texture loading_screen_bar5;
	public static Texture loading_screen_bar6;
	public static Texture crimson_spinel;
	public static Texture empyrean_sapphire;
	public static Texture pyrestone;
	public static Texture gem_frame;
	public static Texture select_screen_background;
	public static Texture enter_game_colored;
	public static Texture create_new_character_hover;
	public static Texture create_character_background;
	public static Texture login_screen;
	public static Texture selected_character;
	public static Texture button_down_hover;
	public static Texture select_screen_warrior;
	public static Texture select_screen_mage;
	public static Texture select_screen_warlock;
	public static Texture select_screen_shaman;
	public static Texture select_screen_hunter;
	public static Texture select_screen_druid;
	public static Texture select_screen_rogue;
	public static Texture select_screen_priest;
	public static Texture select_screen_paladin;
	public static Texture select_screen_hover;
	public static Texture exp_bar;
	public static Texture life_bar;
	public static Texture mana_bar;
	public static Texture big_alert;
	public static Texture input_box;
	public static Texture top_left_corner_alert;
	public static Texture top_right_corner_alert;
	public static Texture bot_left_corner_alert;
	public static Texture bot_right_corner_alert;
	public static Texture width_top_border_alert;
	public static Texture width_bot_border_alert;
	public static Texture height_left_border_alert;
	public static Texture height_right_border_alert;
	public static Texture reduce_category_craft;
	public static Texture expand_category_craft;
	public static Texture craft_orange_selection;
	public static Texture craft_yellow_selection;
	public static Texture craft_green_selection;
	public static Texture craft_grey_selection;
	public static Texture reduce_category_craft_hover;
	public static Texture expand_category_craft_hover;
	public static Texture scrollbar_up_arrow;
	public static Texture scrollbar_grey_up_arrow;
	public static Texture scrollbar_down_arrow;
	public static Texture scrollbar_grey_down_arrow;
	public static Texture ascensor;
	public static Texture profession_border;
	public static Texture scrollbar;
	public static Texture top_button;
	public static Texture bot_button;
	public static Texture cast_bar;
	public static Texture cast_bar_progression;
	public static Texture cast_bar_glow;
	public static Texture loading_screen_bar;
	public static Texture loading_screen_bar_progress;
	public static Texture check_box;
	public static Texture check_box_hover;
	public static Texture check_box_down;
	public static Texture check_box_down_hover;
	public static Texture check_box_enable;
	public static Texture interface_option_frame;
	public static Texture[] shortcut = new Texture[8];
	private static String easternKingdom = "sprite/interface/loading_screen/LoadScreenEasternKingdom2.png";
	private static String outland2 = "sprite/interface/loading_screen/LOADSCREENOUTLAND2WIDE.png";
	private static String outland = "sprite/interface/loading_screen/LoadScreenOutlandWide.png";
	
	private static String[] bgList = new String[3];
	
	public static void initBG() throws IOException {
		bgList[0] = easternKingdom;
		bgList[1] = outland2;
		bgList[2] = outland;
		loading_screen = new Texture(bgList[new Random().nextInt((bgList.length-1)+1)]);
		loading_screen_bar = new Texture("sprite/interface/loading_screen/Loading-BarBorder.png");
		loading_screen_bar_progress = new Texture("sprite/interface/loading_screen/Loading-BarFill.png");
	}
	
	public static boolean sprite() throws IOException {
		gem_frame = new Texture("sprite/interface/gem/gem_frame.png");
		pyrestone = new Texture("sprite/gem/inv_jewelcrafting_pyrestone_02.jpg");
		empyrean_sapphire = new Texture("sprite/gem/inv_jewelcrafting_empyreansapphire_02.jpg");
		crimson_spinel = new Texture("sprite/gem/inv_jewelcrafting_crimsonspinel_02.jpg");
		gem_red = new Texture("sprite/interface/gem/gem_red.png");
		gem_blue = new Texture("sprite/interface/gem/gem_blue.png");
		gem_yellow = new Texture("sprite/interface/gem/gem_yellow.png");
		gem_meta = new Texture("sprite/interface/gem/gem_meta.png");
		close_bag_button_down = new Texture("sprite/interface/buttons/close_button_bag_down.png");
		close_bag_button_down_hover = new Texture("sprite/interface/buttons/close_button_bag_down_hover.png");
		itemnumber_leftyellow_arrow = new Texture("sprite/interface/itemnumber_leftyellow_arrow.png");
		itemnumber_rightgray_arrow = new Texture("sprite/interface/itemnumber_rightgray_arrow.png");
		equipped_item_frame = new Texture("sprite/interface/equipped_item_frame.png");
		itemnumber_hover_ok = new Texture("sprite/interface/itemnumber_hover_ok.png");
		itemnumber_hover_cancel = new Texture("sprite/interface/itemnumber_hover_cancel.png");
		itemnumber_frame = new Texture("sprite/interface/itemnumber_frame.png");
		shortcut_hover = new Texture("sprite/interface/shotcut_hover.png");
		close_bag_button = new Texture("sprite/interface/buttons/close_button_bag.png");
		close_bag_button_hover = new Texture("sprite/interface/buttons/close_button_bag_hover.png");
		bag_hover_tooltip = new Texture("sprite/interface/bag_hover_tooltip.png");
		elevator_button = new Texture("sprite/interface/elevator_button.png");
		bag_click_hover = new Texture("sprite/interface/bag_click_hover.png");
		inventory_click_hover = new Texture("sprite/interface/inventory_click_hover.png");
		//spellbook_frame_open = new Texture("sprite/interface/buttons/buttons/UI-MicroButton-Spellbook-Down.png");
		bag_open_border = new Texture("sprite/interface/bag_open_border.png");
		shortcut[0] = new Texture("sprite/interface/shortcut/character_frame_open.png");
		shortcut[1] = new Texture("sprite/interface/buttons/buttons/UI-MicroButton-Spellbook-Down.png");
		shortcut[2] = new Texture("sprite/interface/buttons/buttons/UI-MicroButton-Talents-Down.png");
		//shortcut[3] = quest_open
		//shortcut[4] = social_open
		//shortcut[5] = lfg_open
		shortcut[6] = new Texture("sprite/interface/buttons/buttons/UI-MicroButton-MainMenu-Down.png");
		//shortcut[7] = request_open
		//talent_frame_open = new Texture("sprite/interface/buttons/buttons/UI-MicroButton-Talents-Down.png");
		//character_frame_open = new Texture("sprite/interface/shortcut/character_frame_open.png");
		//escape_frame_open = new Texture("sprite/interface/buttons/buttons/UI-MicroButton-MainMenu-Down.png");
		spellbar_case = new Texture("sprite/interface/UI-Quickslot.png");
		spellbar_case2 = new Texture("sprite/interface/UI-Quickslot.png");
		final_spellbar = new Texture("sprite/interface/very_final_spellbar.png");
		up_chat_button = new Texture("sprite/interface/up_chat_button.png");
		chat_button = new Texture("sprite/interface/chat/chat_button.png");
		craft_frame = new Texture("sprite/interface/profession/craft_frame.png");
		linen_cloth = new Texture("sprite/items/craft/linen_cloth.jpg");
		bag_linen_cloth = new Texture("sprite/items/bagcraft/bag_linen_cloth.jpg");
		admin_panel = new Texture("sprite/interface/admin_panel.png");
		current_bg = new Texture("sprite/interface/bg.png");
		doge_bg = new Texture("sprite/interface/bgDoge.jpg");
		doge_bg = new Texture("sprite/interface/bgDoge.jpg");
		bg = new Texture("sprite/interface/bg.png");
		bc_bg = new Texture("sprite/interface/bc_bg.jpg");
		illidan_bg = new Texture("sprite/interface/illidan_bg.jpg");
		sunwell1_bg = new Texture("sprite/interface/sunwell1_bg.jpg");
		sunwell2_bg = new Texture("sprite/interface/sunwell2_bg.jpg");
		resized_base_bg = new Texture("sprite/interface/resizedBG/resized_base_bg.png");
		resized_doge_bg = new Texture("sprite/interface/resizedBG/resized_doge_bg.png");
		resized_bc_bg = new Texture("sprite/interface/resizedBG/resized_bc_bg.png");
		resized_sunwell1_bg = new Texture("sprite/interface/resizedBG/resized_sunwell1_bg.png");
		resized_sunwell2_bg = new Texture("sprite/interface/resizedBG/resized_sunwell2_bg.png");
		resized_illidan_bg = new Texture("sprite/interface/resizedBG/resized_illidan_bg.png");
		close_spell_book_hover = new Texture("sprite/interface/buttons/close_spell_book_hover.png");
		spellbook_page1 = new Texture("sprite/interface/spell/spellbook.png");
		spellbook_spell_bg = new Texture("sprite/interface/spell/spellbook_spell_bg.png");
		spellbook_spell_bg_grey = new Texture("sprite/interface/spell/spellbook_spell_bg_grey.png");
		escape_frame = new Texture("sprite/interface/optionFrame.png");
		level = new Texture("sprite/interface/level.png");
		un_green_talent = new Texture("sprite/interface/talent/1_green_talent.png");
		deux_green_talent = new Texture("sprite/interface/talent/2_green_talent.png");
		trois_green_talent = new Texture("sprite/interface/talent/3_green_talent.png");
		gold_coin_container = new Texture("sprite/interface/gold_coin_container.png");
		return true;
	}
	
	public static boolean sprite2() throws IOException {
		select_screen_background = new Texture("sprite/selectScreen/select_screen_background.png");
		enter_game_colored = new Texture("sprite/selectScreen/enter_game_colored.png");
		create_new_character_hover = new Texture("sprite/selectScreen/create_new_character_hover.png");
		create_character_background = new Texture("sprite/selectScreen/create_character_background.png");
		draggedspell_border = new Texture("sprite/interface/draggedspell_border.png");
		mage = new Texture("sprite/classes/mage.png");
		mage2 = new Texture("sprite/classes/mage.png");
		war = new Texture("sprite/classes/warrior.png");
		war2 = new Texture("sprite/classes/warrior.png");
		deathknight = new Texture("sprite/classes/death_knight.png");
		deathknight2 = new Texture("sprite/classes/death_knight.png");
		hunter = new Texture("sprite/classes/hunter.png");
		hunter2 = new Texture("sprite/classes/hunter.png");
		monk = new Texture("sprite/classes/monk.png");
		monk2 = new Texture("sprite/classes/monk.png");
		paladin = new Texture("sprite/classes/paladin.png");
		paladin2 = new Texture("sprite/classes/paladin.png");
		rogue = new Texture("sprite/classes/rogue.png");
		rogue2 = new Texture("sprite/classes/rogue.png");
		shaman = new Texture("sprite/classes/shaman.png");
		shaman2= new Texture("sprite/classes/shaman.png");
		warlock = new Texture("sprite/classes/warlock.png");
		warlock2 = new Texture("sprite/classes/warlock.png");

		deathknight_create_classe = new Texture("sprite/createClasses/death_knight_createClasse.jpg");
		hunter_create_classe = new Texture("sprite/createClasses/hunter_createClasse.jpg");
		mage_create_classe = new Texture("sprite/createClasses/mage_createClasse.jpg");
		monk_create_classe = new Texture("sprite/createClasses/monk_createClasse.jpg");
		paladin_create_classe = new Texture("sprite/createClasses/paladin_createClasse.jpg");
		priest_create_classe = new Texture("sprite/createClasses/priest_createClasse.jpg");
		rogue_create_classe = new Texture("sprite/createClasses/rogue_createClasse.jpg");
		shaman_create_classe = new Texture("sprite/createClasses/shaman_createClasse.jpg");
		warlock_create_classe = new Texture("sprite/createClasses/warlock_createClasse.jpg");
		warrior_create_classe = new Texture("sprite/createClasses/warrior_createClasse.jpg");
		
		deathknight_create_classe_level = new Texture("sprite/createClasses/death_knight_level.jpg");
		hunter_create_classe_level = new Texture("sprite/createClasses/hunter_level.jpg");
		mage_create_classe_level = new Texture("sprite/createClasses/mage_level.jpg");
		monk_create_classe_level = new Texture("sprite/createClasses/monk_level.jpg");
		paladin_create_classe_level = new Texture("sprite/createClasses/paladin_level.jpg");
		priest_create_classe_level = new Texture("sprite/createClasses/priest_level.jpg");
		rogue_create_classe_level = new Texture("sprite/createClasses/rogue_level.jpg");
		shaman_create_classe_level = new Texture("sprite/createClasses/shaman_level.jpg");
		warlock_create_classe_level = new Texture("sprite/createClasses/warlock_level.jpg");
		warrior_create_classe_level = new Texture("sprite/createClasses/warrior_level.jpg");

		select_screen_warrior = new Texture("sprite/selectScreen/warrior.png");
		select_screen_paladin = new Texture("sprite/selectScreen/paladin.png");
		select_screen_mage = new Texture("sprite/selectScreen/mage.png");
		select_screen_warlock = new Texture("sprite/selectScreen/warlock.png");
		select_screen_shaman = new Texture("sprite/selectScreen/shaman.png");
		select_screen_priest = new Texture("sprite/selectScreen/priest.png");
		select_screen_rogue = new Texture("sprite/selectScreen/rogue.png");
		select_screen_druid = new Texture("sprite/selectScreen/druid.png");
		select_screen_hunter = new Texture("sprite/selectScreen/hunter.png");
		select_screen_hover = new Texture("sprite/selectScreen/hover.png");
		large_spellbar = new Texture("sprite/interface/large_spellbar.png");
		silver_coin = new Texture("sprite/interface/silver_coin.png");
		silver_coin_container = new Texture("sprite/interface/silver_coin_container.png");
		copper_coin = new Texture("sprite/interface/copper_coin.png");
		copper_coin_container = new Texture("sprite/interface/copper_coin_container.png");
		right_arrow_hover = new Texture("sprite/interface/right_arrow_hover.jpg");
		left_arrow_hover = new Texture("sprite/interface/left_arrow_hover.jpg");
		warrior_talent_tree = new Texture("sprite/interface/talent/warrior_talent_tree.png");
		playerUI = new Texture("sprite/interface/UI-Player-Portrait.png");
		playerUI2 = new Texture("sprite/interface/UI-Player-Portrait.png");
		illidan = new Texture("sprite/classes/illidan.png");
		priest = new Texture("sprite/classes/priest.png");
		priest2 = new Texture("sprite/classes/priest.png");
		
		stuff_border = new Texture("sprite/interface/stuff_border.png");
		return true;
	}
		
	public static boolean sprite8() throws IOException {
		bag_super_healing_potion = new Texture("sprite/items/bagpotion/bag_super_healing_potion.jpg");
		shop_super_healing_potion = new Texture("sprite/items/shoppotion/shop_super_healing_potion.jpg");
		super_healing_potion = new Texture("sprite/items/potion/super_healing_potion.jpg");

		bag_border1 = new Texture("sprite/interface/bag_border.png");
		shop_border = new Texture("sprite/interface/shop_border.png");
		
		bot_left_corner_alert = new Texture("sprite/interface/bot_left_corner_alert.png");
		bot_right_corner_alert = new Texture("sprite/interface/bot_right_corner_alert.png");
		top_left_corner_alert = new Texture("sprite/interface/top_left_corner_alert.png");
		top_right_corner_alert = new Texture("sprite/interface/top_right_corner_alert.png");
		width_top_border_alert = new Texture("sprite/interface/width_top_border_alert.png");
		width_bot_border_alert = new Texture("sprite/interface/width_bot_border_alert.png");
		height_left_border_alert = new Texture("sprite/interface/height_left_border_alert.png");
		height_right_border_alert = new Texture("sprite/interface/height_right_border_alert.png");
		reduce_category_craft = new Texture("sprite/interface/buttons/reduce_craft_category.png");
		expand_category_craft = new Texture("sprite/interface/buttons/expand_craft_category.png");
		reduce_category_craft_hover = new Texture("sprite/interface/buttons/reduce_craft_category_hover.png");
		expand_category_craft_hover = new Texture("sprite/interface/buttons/expand_craft_category_hover.png");
		craft_orange_selection = new Texture("sprite/interface/profession/craft_orange_selection.png");
		craft_yellow_selection = new Texture("sprite/interface/profession/craft_yellow_selection.png");
		craft_green_selection = new Texture("sprite/interface/profession/craft_green_selection.png");
		craft_grey_selection = new Texture("sprite/interface/profession/craft_grey_selection.png");
		scrollbar_up_arrow = new Texture("sprite/interface/scrollBar/up_arrow.png");
		scrollbar_grey_up_arrow = new Texture("sprite/interface/scrollBar/grey_up_arrow.png");
		scrollbar_down_arrow = new Texture("sprite/interface/scrollBar/down_arrow.png");
		scrollbar_grey_down_arrow = new Texture("sprite/interface/scrollBar/grey_down_arrow.png");
		ascensor = new Texture("sprite/interface/scrollBar/ascensor.png");
		scrollbar = new Texture("sprite/interface/scrollBar/scrollbar.png");
		top_button = new Texture("sprite/interface/scrollBar/top_button.png");
		bot_button = new Texture("sprite/interface/scrollBar/bot_button.png");
		profession_border = new Texture("sprite/interface/profession/border.png");
		button_disabled = new Texture("sprite/interface/button_disabled.png");
		cast_bar = new Texture("sprite/interface/cast/cast_bar.png");
		cast_bar_progression = new Texture("sprite/interface/cast/cast_bar_progression.png");
		cast_bar_glow = new Texture("sprite/interface/cast/cast_bar_glow.png");
		quatre_green_talent = new Texture("sprite/interface/talent/4_green_talent.png");
		trois_yellow_square_talent = new Texture("sprite/interface/talent/yellow_3-3_talent.png");
		deux_yellow_square_talent = new Texture("sprite/interface/talent/yellow_2-2_talent.png");
		cinq_yellow_square_talent = new Texture("sprite/interface/talent/yellow_5-5_talent.png");
		left_second_line_talent = new Texture("sprite/interface/talent/left_second_line_talent.png");
		left_colored_arrow = new Texture("sprite/interface/left_colored_arrow.png");
		right_colored_arrow = new Texture("sprite/interface/right_colored_arrow.png");
		right_uncolored_arrow = new Texture("sprite/interface/right_uncolored_arrow.png");
		gold_coin = new Texture("sprite/interface/gold_coin.png");
		check_box = new Texture("sprite/interface/checkBox/check_box.png");
		check_box_hover = new Texture("sprite/interface/checkBox/check_box_hover.png");
		check_box_down = new Texture("sprite/interface/checkBox/check_box_down2.png");
		check_box_down_hover = new Texture("sprite/interface/checkBox/check_box_down_hover.png");
		check_box_enable = new Texture("sprite/interface/checkBox/check_box_enable.png");
		interface_option_frame = new Texture("sprite/interface/interface_option_frame.png");
		return true;
	}
		
	public static boolean sprite9() throws IOException {
		selected_character = new Texture("sprite/interface/character_selected.png");
		login_screen = new Texture("sprite/interface/loginscreen.png");
		bag4 = new Texture("sprite/interface/bag/4.png");
		bag6 = new Texture("sprite/interface/bag/6.png");
		bag8 = new Texture("sprite/interface/bag/8.png");
		bag10 = new Texture("sprite/interface/bag/10.png");
		bag12 = new Texture("sprite/interface/bag/12.png");
		bag14 = new Texture("sprite/interface/bag/14.png");
		bag16 = new Texture("sprite/interface/bag/16.png");
		bag18 = new Texture("sprite/interface/bag/18.png");
		bag20 = new Texture("sprite/interface/bag/20.png");
		back_bag = new Texture("sprite/interface/bag/backbag.png");
		icon_bag = new Texture("sprite/interface/icon_bag.png");
		bag_hover = new Texture("sprite/interface/bag_hover.png");
		bag_hover2 = new Texture("sprite/interface/bag_hover.png");
		shop_hover = new Texture("sprite/interface/shop_hover.png");
		stuff_hover = new Texture("sprite/interface/stuff_hover.png");
		stuff_hover2 = new Texture("sprite/interface/stuff_hover.png");
		character_frame = new Texture("sprite/interface/characterFrame_final.png");
		character_frame2 = new Texture("sprite/interface/characterFrame.png");
		character_frame_stats = new Texture("sprite/interface/character_frame_stats.png");
		shop_frame = new Texture("sprite/interface/shop_frame.png");
		class_choose = new Texture("sprite/interface/class_choose.png");
		border = new Texture("sprite/interface/border.png");
		spell_hover = new Texture("sprite/interface/spell_hover.png");
		spell_hover2 = new Texture("sprite/interface/spell_hover.png");
		spell_border = new Texture("sprite/interface/final_border.png");
		border2 = new Texture("sprite/interface/border.png");
		border3 = new Texture("sprite/interface/border.png");
		border4 = new Texture("sprite/interface/border.png");
		border5 = new Texture("sprite/interface/border.png");
		border6 = new Texture("sprite/interface/border.png");
		border7 = new Texture("sprite/interface/border.png");
		border8 = new Texture("sprite/interface/border.png");
		border9 = new Texture("sprite/interface/border.png");
		border10 = new Texture("sprite/interface/border.png");
		hover = new Texture("sprite/interface/hover.png");
		button = new Texture("sprite/interface/button.png");
		button2 = new Texture("sprite/interface/button.png");
		button_down = new Texture("sprite/interface/button_down.png");
		button_hover = new Texture("sprite/interface/button_hover.png");
		button_hover2 = new Texture("sprite/interface/button_hover.png");
		button_down_hover = new Texture("sprite/interface/button_down_hover.png");
		tooltip = new Texture("sprite/interface/tooltip.png");
		cursor = new Texture("sprite/interface/cursor.png");
		cursor_buy = new Texture("sprite/interface/cursor_buy.png");
		alert = new Texture("sprite/interface/alert.png");
		alert_dungeon = new Texture("sprite/interface/alert_dungeon.png");
		close_button_inventory_hover = new Texture("sprite/interface/buttons/close_button_inventory_hover.png");
		close_button_inventory_down = new Texture("sprite/interface/buttons/close_button_inventory_down.png");
		close_button_inventory_down_hover = new Texture("sprite/interface/buttons/close_button_inventory_down_hover.png");
		close_shop_hover = new Texture("sprite/interface/buttons/close_shop_hover.png");
		exp_bar = new Texture("sprite/interface/exp_bar.png");
		life_bar = new Texture("sprite/interface/life_bar.png");
		mana_bar = new Texture("sprite/interface/mana_bar.png");
		big_alert = new Texture("sprite/interface/big_alert.png");
		input_box = new Texture("sprite/interface/input_box.png");
		return true;
	}

	public static boolean sprite10() throws IOException {
		spellbar = new Texture("sprite/interface/spellbar.png");
		spriteLoaded = true;
		return true;
	}
	
	public static boolean getSpriteLoaded() {
		return spriteLoaded;
	}
}
