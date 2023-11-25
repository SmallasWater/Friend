package com.smallaswater.friends.utils.items;

import cn.nukkit.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public enum ItemIDSunName {
    STONE("\u77f3\u5934", 1, 0, "textures/blocks/stone.png"),
    STONE_GRANITE("\u82b1\u5c97\u5ca9", 1, 1, "textures/blocks/stone_granite.png"),
    STONE_GRANITE_SMOOTH("\u78e8\u5236\u82b1\u5c97\u5ca9", 1, 2, "textures/blocks/stone_granite_smooth.png"),
    STONE_DIORITE("\u95ea\u957f\u5ca9", 1, 3, "textures/blocks/stone_diorite.png"),
    STONE_DIORITE_SMOOTH("\u78e8\u5236\u95ea\u957f\u5ca9", 1, 4, "textures/blocks/stone_diorite_smooth.png"),
    STONE_ANDESITE("\u5b89\u5c71\u5ca9", 1, 5, "textures/blocks/stone_andesite.png"),
    STONE_ANDESITE_SMOOTH("\u78e8\u5236\u5b89\u5c71\u5ca9", 1, 6, "textures/blocks/stone_andesite_smooth.png"),
    GRASS("\u8349\u65b9\u5757", 2, 0, "textures/blocks/grass_side_carried.png"),
    DIRT("\u6ce5\u571f", 3, 0, "textures/blocks/dirt.png"),
    COBBLESTONE("\u5706\u77f3", 4, 0, "textures/blocks/cobblestone.png"),
    PLANKS("\u6a61\u6811\u6728\u677f", 5, 0, "textures/blocks/planks_oak.png"),
    PLANKS_SPRUCE("\u4e91\u6749\u6728\u677f", 5, 1, "textures/blocks/planks_spruce.png"),
    PLANKS_BIRCH("\u6866\u6728\u677f", 5, 2, "textures/blocks/planks_birch.png"),
    PLANKS_JUNGLE("\u4e1b\u6797\u6811\u6728\u677f", 5, 3, "textures/blocks/planks_jungle.png"),
    PLANKS_ACACIA("\u91d1\u5408\u6b22\u6728\u677f", 5, 4, "textures/blocks/planks_acacia.png"),
    PLANKS_BIG_OAK("\u6df1\u8272\u6a61\u6728\u6728\u677f", 5, 5, "textures/blocks/planks_big_oak.png"),
    SAPLING("\u6a61\u6811\u82d7", 6, 0, "textures/blocks/sapling_oak.png"),
    SAPLING_SPRUCE("\u4e91\u6749\u6811\u82d7", 6, 1, "textures/blocks/sapling_spruce.png"),
    SAPLING_BIRCH("\u6866\u6811\u82d7", 6, 2, "textures/blocks/sapling_birch.png"),
    SAPLING_JUNGLE("\u4e1b\u6797\u6811\u82d7", 6, 3, "textures/blocks/sapling_jungle.png"),
    SAPLING_ACACIA("\u91d1\u5408\u6b22\u6811\u82d7", 6, 4, "textures/blocks/sapling_acacia.png"),
    SAPLING_ROOFED_OAK("\u6df1\u8272\u6a61\u6811\u82d7", 6, 5, "textures/blocks/sapling_roofed_oak.png"),
    BEDROCK("\u57fa\u5ca9", 7, 0, "textures/blocks/bedrock.png"),
    FLOWING_WATER("\u6d41\u52a8\u7684\u6c34", 8, 0, "textures/blocks/water_placeholder.png"),
    WATER("\u6c34", 9, 0, "textures/blocks/water_placeholder.png"),
    FLOWING_LAVA("\u6d41\u52a8\u7684\u5ca9\u6d46", 10, 0, "textures/blocks/lava_placeholder.png"),
    LAVA("\u5ca9\u6d46", 11, 0, "textures/blocks/lava_placeholder.png"),
    SAND("\u6c99\u5b50", 12, 0, "textures/blocks/sand.png"),
    RED_SAND("\u7ea2\u6c99", 12, 1, "textures/blocks/red_sand.png"),
    GRAVEL("\u783e\u77f3", 13, 0, "textures/blocks/gravel.png"),
    GOLD_ORE("\u91d1\u77ff\u77f3", 14, 0, "textures/blocks/gold_ore.png"),
    IRON_ORE("\u94c1\u77ff\u77f3", 15, 0, "textures/blocks/iron_ore.png"),
    COAL_ORE("\u7164\u77ff\u77f3", 16, 0, "textures/blocks/coal_ore.png"),
    LOG("\u6a61\u6728", 17, 0, "textures/blocks/log_oak.png"),
    LOG_SPRUCE("\u4e91\u6749\u6728", 17, 1, "textures/blocks/log_spruce.png"),
    LOG_BIRCH("\u6866\u6728", 17, 2, "textures/blocks/log_birch.png"),
    LOG_JUNGLE("\u4e1b\u6797\u6728", 17, 3, "textures/blocks/log_jungle.png"),
    LEAVES("\u6a61\u6811\u53f6", 18, 0, "textures/blocks/leaves_oak_carried.tga"),
    LEAVES_SPRUCE_CARRIED("\u4e91\u6749\u53f6", 18, 1, "textures/blocks/leaves_spruce_carried.tga"),
    LEAVES_BIRCH_CARRIED("\u6866\u6811\u53f6", 18, 2, "textures/blocks/leaves_birch_carried.tga"),
    LEAVES_JUNGLE_CARRIED("\u4e1b\u6797\u6811\u53f6", 18, 3, "textures/blocks/leaves_jungle_carried.tga"),
    SPONGE("\u5e72\u6d77\u7ef5", 19, 0, "textures/blocks/sponge.png"),
    SPONGE_WET("\u6e7f\u6d77\u7ef5", 19, 1, "textures/blocks/sponge_wet.png"),
    GLASS("\u73bb\u7483", 20, 0, "textures/blocks/glass.png"),
    LAPIS_ORE("\u9752\u91d1\u77f3\u77ff", 21, 0, "textures/blocks/lapis_ore.png"),
    LAPIS_BLOCK("\u9752\u91d1\u77f3\u5757", 22, 0, "textures/blocks/lapis_block.png"),
    DISPENSER("\u53d1\u5c04\u5668", 23, 0, "textures/blocks/dispenser_front_horizontal.png"),
    SANDSTONE("\u6c99\u77f3", 24, 0, "textures/blocks/sandstone_normal.png"),
    SANDSTONE_CARVED("\u933e\u5236\u6c99\u77f3", 24, 1, "textures/blocks/sandstone_carved.png"),
    SANDSTONE_SMOOTH("\u5149\u6ed1\u6c99\u77f3", 24, 2, "textures/blocks/sandstone_smooth.png"),
    NOTEBLOCK("\u97f3\u7b26\u76d2", 25, 0, "textures/blocks/noteblock.png"),
    BED_BLOCK("\u65b9\u5757\u5e8a", 26, 0, "textures/blocks/bed_head_top.png"),
    GOLDEN_RAIL("\u52a8\u529b\u94c1\u8f68", 27, 0, "textures/blocks/rail_golden.png"),
    DETECTOR_RAIL("\u63a2\u6d4b\u94c1\u8f68", 28, 0, "textures/blocks/rail_detector.png"),
    STICKY_PISTON("\u7c98\u6027\u6d3b\u585e", 29, 0, "textures/blocks/piston_top_sticky.png"),
    WEB("\u8718\u86db\u7f51", 30, 0, "textures/blocks/web.png"),
    TALLGRASS("\u9ad8\u8349", 31, 0, "textures/blocks/deadbush.png"),
    TALLGRASS_CARRIED("\u8349", 31, 1, "textures/blocks/tallgrass_carried.tga"),
    FERN_CARRIED("\u8568", 31, 2, "textures/blocks/fern_carried.tga"),
    DEADBUSH("\u67af\u6b7b\u7684\u704c\u6728", 32, 0, "textures/blocks/deadbush.png"),
    PISTON("\u6d3b\u585e", 33, 0, "textures/blocks/piston_top_normal.png"),
    PISTON_HEAD("\u6d3b\u585e\u81c2", 34, 0, "textures/blocks/piston_top_normal.png"),
    WOOL("\u767d\u8272\u7f8a\u6bdb", 35, 0, "textures/blocks/wool_colored_white.png"),
    WOOL_COLORED_ORANGE("\u6a59\u8272\u7f8a\u6bdb", 35, 1, "textures/blocks/wool_colored_orange.png"),
    WOOL_COLORED_MAGENTA("\u54c1\u7ea2\u8272\u7f8a\u6bdb", 35, 2, "textures/blocks/wool_colored_magenta.png"),
    WOOL_COLORED_LIGHT_BLUE("\u6de1\u84dd\u8272\u7f8a\u6bdb", 35, 3, "textures/blocks/wool_colored_light_blue.png"),
    WOOL_COLORED_YELLOW("\u9ec4\u8272\u7f8a\u6bdb", 35, 4, "textures/blocks/wool_colored_yellow.png"),
    WOOL_COLORED_LIME("\u9ec4\u7eff\u8272\u7f8a\u6bdb", 35, 5, "textures/blocks/wool_colored_lime.png"),
    WOOL_COLORED_PINK("\u7c89\u7ea2\u8272\u7f8a\u6bdb", 35, 6, "textures/blocks/wool_colored_pink.png"),
    WOOL_COLORED_GRAY("\u7070\u8272\u7f8a\u6bdb", 35, 7, "textures/blocks/wool_colored_gray.png"),
    WOOL_COLORED_SILVER("\u6de1\u7070\u8272\u7f8a\u6bdb", 35, 8, "textures/blocks/wool_colored_silver.png"),
    WOOL_COLORED_CYAN("\u9752\u8272\u7f8a\u6bdb", 35, 9, "textures/blocks/wool_colored_cyan.png"),
    WOOL_COLORED_PURPLE("\u7d2b\u8272\u7f8a\u6bdb", 35, 10, "textures/blocks/wool_colored_purple.png"),
    WOOL_COLORED_BLUE("\u84dd\u8272\u7f8a\u6bdb", 35, 11, "textures/blocks/wool_colored_blue.png"),
    WOOL_COLORED_BROWN("\u68d5\u8272\u7f8a\u6bdb", 35, 12, "textures/blocks/wool_colored_brown.png"),
    WOOL_COLORED_GREEN("\u7eff\u8272\u7f8a\u6bdb", 35, 13, "textures/blocks/wool_colored_green.png"),
    WOOL_COLORED_RED("\u7ea2\u8272\u7f8a\u6bdb", 35, 14, "textures/blocks/wool_colored_red.png"),
    WOOL_COLORED_BLACK("\u9ed1\u8272\u7f8a\u6bdb", 35, 15, "textures/blocks/wool_colored_black.png"),
    YELLOW_FLOWER("\u9ec4\u82b1", 37, 0, "textures/blocks/glazed_terracotta_yellow.png"),
    RED_FLOWER("\u7f42\u7c9f", 38, 0, "textures/blocks/flower_rose.png"),
    FLOWER_BLUE_ORCHID("\u84dd\u8272\u7684\u5170\u82b1", 38, 1, "textures/blocks/flower_blue_orchid.png"),
    FLOWER_ALLIUM("\u7ed2\u7403\u8471", 38, 2, "textures/blocks/flower_allium.png"),
    FLOWER_HOUSTONIA("\u831c\u8349\u82b1", 38, 3, "textures/blocks/flower_houstonia.png"),
    FLOWER_TULIP_RED("\u7ea2\u8272\u90c1\u91d1\u9999", 38, 4, "textures/blocks/flower_tulip_red.png"),
    FLOWER_TULIP_ORANGE("\u6a59\u8272\u90c1\u91d1\u9999", 38, 5, "textures/blocks/flower_tulip_orange.png"),
    FLOWER_TULIP_WHITE("\u767d\u8272\u90c1\u91d1\u9999", 38, 6, "textures/blocks/flower_tulip_white.png"),
    FLOWER_TULIP_PINK("\u7c89\u8272\u90c1\u91d1\u9999", 38, 7, "textures/blocks/flower_tulip_pink.png"),
    FLOWER_OXEYE_DAISY("\u6ee8\u83ca", 38, 8, "textures/blocks/flower_oxeye_daisy.png"),
    BROWN_MUSHROOM("\u68d5\u8272\u8611\u83c7", 39, 0, "textures/blocks/mushroom_brown.png"),
    RED_MUSHROOM("\u7ea2\u8272\u8611\u83c7", 40, 0, "textures/blocks/mushroom_red.png"),
    GOLD_BLOCK("\u9ec4\u91d1\u5757", 41, 0, "textures/blocks/gold_block.png"),
    IRON_BLOCK("\u94c1\u5757", 42, 0, "textures/blocks/iron_block.png"),
    DOUBLE_STONE_SLAB("\u53cc\u77f3\u53f0\u9636", 43, 0, "textures/blocks/stone_slab_side.png"),
    SANDSTONE_BOTTOM("\u53cc\u6c99\u77f3\u53f0\u9636", 43, 1, "textures/blocks/sandstone_bottom.png"),
    PLANKS_OAK("\u53cc\u6a61\u6728\u53f0\u9636", 43, 2, "textures/blocks/planks_oak.png"),
    DOUBLE_PEBBLE_STEPS("\u53cc\u5706\u77f3\u53f0\u9636", 43, 3, "textures/blocks/cobblestone.png"),
    DOUBLE_BRICK_STEPS("\u53cc\u7816\u53f0\u9636", 43, 4, "textures/blocks/brick.png"),
    DOUBLE_STONE_BRICK_STEPS("\u53cc\u77f3\u7816\u53f0\u9636", 43, 5, "textures/blocks/stonebrick.png"),
    STONE_BRICK("\u77f3\u7816", 98, 0, "textures/blocks/stonebrick.png"),
    DOUBLE_QUARTZ_STEPS("\u53cc\u77f3\u82f1\u53f0\u9636", 43, 6, "textures/blocks/nether_brick.png"),
    DOUBLE_HELL_BRICK_STEPS("\u53cc\u5730\u72f1\u7816\u53f0\u9636", 43, 7, "textures/blocks/quartz_block_top.png"),
    STONE_SLAB("\u77f3\u53f0\u9636", 44, 0, "textures/blocks/stone_slab_top.png"),
    SANDSTONE_TOP("\u6c99\u77f3\u53f0\u9636", 44, 1, "textures/blocks/sandstone_top.png"),
    COBBLESTONE_STEPS("\u5706\u77f3\u53f0\u9636", 44, 3, "textures/blocks/cobblestone.png"),
    BRICK_STEPS("\u7816\u53f0\u9636", 44, 4, "textures/blocks/brick.png"),
    STONEBRICK_STEPS("\u77f3\u7816\u53f0\u9636", 44, 5, "textures/blocks/stonebrick.png"),
    NETHER_BRICK_STEPS("\u77f3\u82f1\u53f0\u9636", 44, 6, "textures/blocks/nether_brick.png"),
    QUARTZ_BLOCK_TOP_STEPS("\u5730\u72f1\u7816\u53f0\u9636", 44, 7, "textures/blocks/quartz_block_top.png"),
    BRICK_("\u7816", 45, 0, "textures/blocks/brick.png"),
    TNT("TNT", 46, 0, "textures/blocks/tnt_side.png"),
    BOOKSHELF("\u4e66\u67b6", 47, 0, "textures/blocks/bookshelf.png"),
    MOSSY_COBBLESTONE("\u82d4\u77f3", 48, 0, "textures/blocks/cobblestone_mossy.png"),
    OBSIDIAN("\u9ed1\u66dc\u77f3", 49, 0, "textures/blocks/obsidian.png"),
    TORCH("\u706b\u628a", 50, 0, "textures/blocks/torch_on.png"),
    FIRE("\u706b", 51, 0, "textures/blocks/fire_0_placeholder.png"),
    MOB_SPAWNER("\u5237\u602a\u7b3c", 52, 0, "textures/blocks/mob_spawner.png"),
    OAK_STAIRS("\u6a61\u6728\u9636\u68af", 53, 0, "textures/blocks/planks_oak.png"),
    CHEST("\u7bb1\u5b50", 54, 0, "textures/blocks/chest_front.png"),
    REDSTONE_WIRE("\u7ea2\u77f3\u7c89", 55, 0, "textures/blocks/redstone_dust_line.png"),
    DIAMOND_ORE("\u94bb\u77f3\u77ff", 56, 0, "textures/blocks/diamond_ore.png"),
    DIAMOND_BLOCK("\u94bb\u77f3\u5757", 57, 0, "textures/blocks/diamond_block.png"),
    CRAFTING_TABLE("\u5de5\u4f5c\u53f0", 58, 0, "textures/blocks/crafting_table_top.png"),
    WHEAT_STAGE("\u5c0f\u9ea6", 59, 0, "textures/items/wheat.png"),
    FARMLAND("\u519c\u7530", 60, 0, "textures/blocks/farmland_dry.png"),
    FURNACE("\u7194\u7089", 61, 0, "textures/blocks/furnace_front_off.png"),
    SIGN2("\u544a\u793a\u724c", 63, 0, "textures/items/sign.png"),
    LADDER("\u68af\u5b50", 65, 0, "textures/blocks/ladder.png"),
    RAIL("\u94c1\u8f68", 66, 0, "textures/blocks/rail_normal.png"),
    STONE_STAIRS("\u5706\u77f3\u9636\u68af", 67, 0, "textures/blocks/cobblestone.png"),
    LEVER("\u62c9\u6746", 69, 0, "textures/blocks/lever.png"),
    STONE_PRESSURE_PLATE("\u77f3\u8d28\u538b\u529b\u677f", 70, 0, "textures/blocks/stone.png"),
    WOODEN_PRESSURE_PLATE("\u6728\u8d28\u538b\u529b\u677f", 72, 0, "textures/blocks/planks_oak.png"),
    REDSTONE_ORE("\u7ea2\u77f3\u77ff", 73, 0, "textures/blocks/redstone_ore.png"),
    LIT_REDSTONE_ORE("\u53d1\u5149\u7684\u7ea2\u77f3\u77ff", 74, 0, "textures/blocks/redstone_ore.png"),
    UNLIT_REDSTONE_TORCH("\u7ea2\u77f3\u706b\u628a", 75, 0, "textures/blocks/redstone_torch_off.png"),
    STONE_BUTTON("\u77f3\u8d28\u6309\u94ae", 77, 0, "textures/blocks/stone.png"),
    SNOW_LAYER("\u9876\u5c42\u96ea", 78, 0, "textures/blocks/snow.png"),
    ICE("\u51b0", 79, 0, "textures/blocks/ice.png"),
    SNOW("\u96ea", 80, 0, "textures/blocks/snow.png"),
    CACTUS("\u4ed9\u4eba\u638c", 81, 0, "textures/blocks/cactus_side.tga"),
    CLAY("\u7c98\u571f", 82, 0, "textures/blocks/clay.png"),
    REDDS("\u7518\u8517", 83, 0, "textures/items/reeds.png"),
    JUKEBOX("\u97f3\u4e50\u76d2", 84, 0, "textures/blocks/jukebox_top.png"),
    FENCE("\u6a61\u6728\u56f4\u5899", 85, 0, "textures/blocks/planks_oak.png"),
    PUMPKIN("\u5357\u74dc", 86, 0, "textures/blocks/pumpkin_face_off.png"),
    NETHERRACK("\u5730\u72f1\u5ca9", 87, 0, "textures/blocks/netherrack.png"),
    SOUL_SAND("\u7075\u9b42\u6c99", 88, 0, "textures/blocks/soul_sand.png"),
    GLOWSTONE("\u8424\u77f3", 89, 0, "textures/blocks/glowstone.png"),
    PORTAL("\u4f20\u9001\u95e8", 90, 0, "textures/blocks/portal_placeholder.png"),
    LIT_PUMPKIN("\u5357\u74dc\u706f", 91, 0, "textures/blocks/pumpkin_face_on.png"),
    STAINED_GLASS("\u9690\u5f62\u57fa\u5ca9", 95, 0, "textures/blocks/glass_white.png"),
    TRAPDOOR("\u6728\u8d28\u9677\u9631\u95e8", 96, 0, "textures/blocks/trapdoor.png"),
    MONSTER_EGG("\u77f3\u5934\u5237\u602a\u86cb", 97, 0, "textures/blocks/stone.png"),
    COBBLESTONE_EGG("\u5706\u77f3\u5237\u602a\u86cb", 97, 1, "textures/blocks/Cobblestone.png"),
    STONEBRICK("\u77f3\u7816\u5237\u602a\u86cb", 97, 2, "textures/blocks/stonebrick.png"),
    STONEBRICK_MOSSY("\u82d4\u77f3\u7816", 98, 1, "textures/blocks/stonebrick_mossy.png"),
    STONEBRICK_CRACKED("\u88c2\u77f3\u7816", 98, 2, "textures/blocks/stonebrick_cracked.png"),
    STONEBRICK_CARVED("\u933e\u5236\u77f3\u7816", 98, 3, "textures/blocks/stonebrick_carved.png"),
    BROWN_MUSHROOM_BLOCK("\u68d5\u8272\u8611\u83c7\u5757", 99, 0, "textures/blocks/mushroom_block_skin_brown.png"),
    RED_MUSHROOM_BLOCK("\u7ea2\u8272\u8611\u83c7\u5757", 100, 0, "textures/blocks/mushroom_block_skin_red.png"),
    IRON_BARS("\u94c1\u680f\u6746", 101, 0, "textures/blocks/iron_bars.png"),
    GLASS_PANE("\u73bb\u7483\u677f", 102, 0, "textures/blocks/glass_pane_top.png"),
    MELON_SIDE("\u897f\u74dc\u5757", 103, 0, "textures/blocks/melon_side.png"),
    PUMPKIN_STEM("\u5357\u74dc\u6897", 104, 0, "textures/blocks/pumpkin_stem_disconnected.png"),
    VINE("\u85e4\u8513", 106, 0, "textures/blocks/vine_carried.png"),
    FENCE_GATE("\u6a61\u6728\u56f4\u5899\u5927\u95e8", 107, 0, "textures/blocks/planks_oak.png"),
    BRICK_STAIRS("\u7816\u5757\u9636\u68af", 108, 0, "textures/blocks/brick.png"),
    STONE_BRICK_STAIRS("\u77f3\u7816\u9636\u68af", 109, 0, "textures/blocks/stonebrick.png"),
    MYCELIUM("\u83cc\u4e1d", 110, 0, "textures/blocks/mycelium_side.png"),
    WATERLILY("\u7761\u83b2", 111, 0, "textures/blocks/carried_waterlily.png"),
    NETHERBRICK("\u5730\u72f1\u7816\u5757", 112, 0, "textures/blocks/nether_brick.png"),
    NETHER_BRICK_FENCE("\u5730\u72f1\u7816\u56f4\u5899", 113, 0, "textures/blocks/nether_brick.png"),
    NETHER_BRICK_STAIRS("\u5730\u72f1\u7816\u9636\u68af", 114, 0, "textures/blocks/nether_brick.png"),
    ENCHANTING_TABLE("\u9644\u9b54\u53f0", 116, 0, "textures/blocks/enchanting_table_side.png"),
    BREWING_STAND("\u917f\u9020\u53f0", 117, 0, "textures/blocks/brewing_stand.png"),
    CAULDRON("\u70bc\u836f\u9505", 118, 0, "textures/blocks/cauldron_side.png"),
    END_PORTAL("\u672b\u5730\u95e8", 119, 0, "textures/blocks/end_portal.png"),
    END_PORTAL_FRAME("\u672b\u5730\u4f20\u9001\u95e8", 120, 0, "textures/blocks/end_portal.png"),
    END_STONE("\u672b\u5730\u77f3", 121, 0, "textures/blocks/end_stone.png"),
    DRAGON_EGG("\u9f99\u86cb", 122, 0, "textures/blocks/dragon_egg.png"),
    REDSTONE_LAMP("\u7ea2\u77f3\u706f", 123, 0, "textures/blocks/redstone_lamp_off.png"),
    DROPPER_FRONT("\u6295\u63b7\u5668", 123, 0, "textures/blocks/dropper_front_horizontal.png"),
    SANDSTONE_STAIRS("\u6c99\u77f3\u9636\u68af", 128, 0, "textures/blocks/sandstone_bottom.png"),
    EMERALD_ORE("\u7eff\u5b9d\u77f3\u77ff\u77f3", 129, 0, "textures/blocks/emerald_ore.png"),
    ENDER_CHEST("\u672b\u5f71\u7bb1", 130, 0, "textures/blocks/ender_chest_front.png"),
    TRIPWIRE_HOOK("\u62cc\u7ebf\u94a9", 131, 0, "textures/blocks/trip_wire_source.png"),
    TRIPWIRE("\u62cc\u7ebf", 132, 0, "textures/blocks/trip_wire.png"),
    EMERALD_BLOCK("\u7eff\u5b9d\u77f3\u5757", 133, 0, "textures/blocks/emerald_block.png"),
    SPRUCE_STAIRS("\u4e91\u6749\u6728\u9636\u68af", 134, 0, "textures/blocks/planks_spruce.png"),
    BIRCH_STAIRS("\u6866\u6728\u9636\u68af", 135, 0, "textures/blocks/planks_birch.png"),
    JUNGLE_STAIRS("\u4e1b\u6797\u6728\u9636\u68af", 136, 0, "textures/blocks/planks_jungle.png"),
    COMMAND_BLOCK("\u547d\u4ee4\u65b9\u5757", 137, 0, "textures/blocks/command_block.png"),
    BEACON("\u4fe1\u6807", 138, 0, "textures/blocks/beacon.png"),
    COBBLESTONE_WALL("\u5706\u77f3\u5899", 139, 0, "textures/blocks/cobblestone.png"),
    MOSS_COBBLESTONE_WALL("\u82d4\u77f3\u5899", 139, 1, "textures/blocks/cobblestone_mossy.png"),
    POTATOES("\u571f\u8c46", 142, 0, "textures/blocks/potatoes_stage_3.png"),
    WOODEN_BUTTON("\u6728\u8d28\u6309\u94ae", 143, 0, "textures/blocks/planks_oak.png"),
    ANVIL("\u94c1\u7827", 145, 0, "textures/blocks/anvil_top_damaged_0.png"),
    TRAPPED_CHEST("\u9677\u9631\u7bb1", 146, 0, "textures/blocks/trapped_chest_front.png"),
    LIGHT_WEIGHTED_PRESSURE_PLATE("\u91cd\u529b\u538b\u529b\u677f(\u8f7b\u578b)", 147, 0, "textures/blocks/gold_block.png"),
    HEAVY_WEIGHTED_PRESSURE_PLATE("\u91cd\u529b\u538b\u529b\u677f(\u91cd\u578b)", 148, 0, "textures/blocks/iron_block.png"),
    DAYLIGHT_DETECTOR_INVERTED("\u9633\u5149\u4f20\u611f\u5668", 178, 0, "textures/blocks/daylight_detector_inverted_top.png"),
    REDSTONE_BLOCK("\u7ea2\u77f3\u5757", 152, 0, "textures/blocks/redstone_block.png"),
    QUARTZ_ORE("\u5730\u72f1\u77f3\u82f1\u77ff\u77f3", 153, 0, "textures/blocks/quartz_ore.png"),
    HOPPER("\u6f0f\u6597", 410, 0, "textures/blocks/hopper_top.png"),
    QUARTZ_BLOCK("\u77f3\u82f1\u5757", 155, 0, "textures/blocks/quartz_block_top.png"),
    VERTICAL_GRAIN_QUARTZ_BLOCK("\u7ad6\u7eb9\u77f3\u82f1\u5757", 155, 1, "textures/blocks/quartz_block_lines.png"),
    QUARTZ_BLOCK_CHISELED("\u933e\u5236\u77f3\u82f1\u5757", 155, 2, "textures/blocks/quartz_block_chiseled_top.png"),
    QUARTZ_STAIRS("\u77f3\u82f1\u9636\u68af", 156, 0, "textures/blocks/quartz_block_top.png"),
    OAK_WOOD_STAIRS("\u6a61\u6728\u53f0\u9636", 158, 0, "textures/blocks/planks_oak.png"),
    WHITE_STAINED_HARDENED_CLAY("\u767d\u8272\u9676\u74e6", 159, 0, "textures/blocks/hardened_clay_stained_white.png"),
    ORANGE_STAINED_HARDENED_CLAY("\u6a59\u8272\u9676\u74e6", 159, 1, "textures/blocks/hardened_clay_stained_orange.png"),
    SOLFERINO_STAINED_HARDENED_CLAY("\u54c1\u7ea2\u8272\u9676\u74e6", 159, 2, "textures/blocks/hardened_clay_stained_magenta.png"),
    LIGHT_BLUE_STAINED_HARDENED_CLAY("\u6de1\u84dd\u8272\u9676\u74e6", 159, 3, "textures/blocks/hardened_clay_stained_light_blue.png"),
    YELLOW_STAINED_HARDENED_CLAY("\u9ec4\u8272\u9676\u74e6", 159, 4, "textures/blocks/hardened_clay_stained_yellow.png"),
    OLIVINE_STAINED_HARDENED_CLAY("\u9ec4\u7eff\u8272\u9676\u74e6", 159, 5, "textures/blocks/hardened_clay_stained_lime.png"),
    PINK_STAINED_HARDENED_CLAY("\u7c89\u7ea2\u8272\u9676\u74e6", 159, 6, "textures/blocks/hardened_clay_stained_pink.png"),
    GRAY_STAINED_HARDENED_CLAY("\u7070\u8272\u9676\u74e6", 159, 7, "textures/blocks/hardened_clay_stained_gray.png"),
    LIGHT_GRAY_STAINED_HARDENED_CLAY("\u6de1\u7070\u8272\u9676\u74e6", 159, 8, "textures/blocks/concrete_gray.png"),
    CYAN_STAINED_HARDENED_CLAY("\u9752\u8272\u9676\u74e6", 159, 9, "textures/blocks/hardened_clay_stained_lime.png"),
    VIOLET_STAINED_HARDENED_CLAY("\u7d2b\u8272\u9676\u74e6", 159, 10, "textures/blocks/hardened_clay_stained_purple.png"),
    BLUE_STAINED_HARDENED_CLAY("\u84dd\u8272\u9676\u74e6", 159, 11, "textures/blocks/hardened_clay_stained_blue.png"),
    BROWN_STAINED_HARDENED_CLAY("\u68d5\u8272\u9676\u74e6", 159, 12, "textures/blocks/hardened_clay_stained_brown.png"),
    GREEN_STAINED_HARDENED_CLAY("\u7eff\u8272\u9676\u74e6", 159, 13, "textures/blocks/hardened_clay_stained_green.png"),
    RED_STAINED_HARDENED_CLAY("\u7ea2\u8272\u9676\u74e6", 159, 14, "textures/blocks/hardened_clay_stained_red.png"),
    BLACK_STAINED_HARDENED_CLAY("\u9ed1\u8272\u9676\u74e6", 159, 15, "textures/blocks/hardened_clay_stained_black.png"),
    WHITE_STAINED_GLASS_PANE("\u767d\u8272\u73bb\u7483\u677f", 160, 0, "textures/blocks/glass_pane_top_white.png"),
    ORANGE_STAINED_GLASS_PANE("\u6a59\u8272\u73bb\u7483\u677f", 160, 1, "textures/blocks/glass_pane_top_orange.png"),
    SOLFERINO_STAINED_GLASS_PANE("\u54c1\u7ea2\u8272\u73bb\u7483\u677f", 160, 2, "textures/blocks/glass_pane_top_magenta.png"),
    LIGHT_BLUE_STAINED_GLASS_PANE("\u6de1\u84dd\u8272\u73bb\u7483\u677f", 160, 3, "textures/blocks/glass_light_blue.png"),
    YELLOW_STAINED_GLASS_PANE("\u9ec4\u8272\u73bb\u7483\u677f", 160, 4, "textures/blocks/glass_yellow.png"),
    OLIVINE_STAINED_GLASS_PANE("\u9ec4\u7eff\u8272\u73bb\u7483\u677f", 160, 5, "textures/blocks/glass_pane_top_lime.png"),
    PINK_STAINED_GLASS_PANE("\u7c89\u7ea2\u8272\u73bb\u7483\u677f", 160, 6, "textures/blocks/glass_pane_top_pink.png"),
    GRAY_STAINED_GLASS_PANE("\u7070\u8272\u73bb\u7483\u677f", 160, 7, "textures/blocks/glass_gray.png"),
    LIGHT_GRAY_STAINED_GLASS_PANE("\u6de1\u7070\u8272\u73bb\u7483\u677f", 160, 8, "textures/blocks/glass_brown.png"),
    CYAN_STAINED_GLASS_PANE("\u9752\u8272\u73bb\u7483\u677f", 160, 9, "textures/blocks/glass_pane_top_cyan.png"),
    VIOLET_STAINED_GLASS_PANE("\u7d2b\u8272\u73bb\u7483\u677f", 160, 10, "textures/blocks/glass_pane_top_purple.png"),
    BLUE_STAINED_GLASS_PANE("\u84dd\u8272\u73bb\u7483\u677f", 160, 11, "textures/blocks/glass_blue.png"),
    BROWN_STAINED_GLASS_PANE("\u68d5\u8272\u73bb\u7483\u677f", 160, 12, "textures/blocks/glass_brown.png"),
    GREEN_STAINED_GLASS_PANE("\u7eff\u8272\u73bb\u7483\u677f", 160, 13, "textures/blocks/glass_pane_top_green.png"),
    RED_STAINED_GLASS_PANE("\u7ea2\u8272\u73bb\u7483\u677f", 160, 14, "textures/blocks/glass_red.png"),
    BLACK_STAINED_GLASS_PANE("\u9ed1\u8272\u73bb\u7483\u677f", 160, 15, "textures/blocks/glass_black.png"),
    ACACIA_LEAVES("\u91d1\u5408\u6b22\u53f6", 161, 0, "textures/blocks/leaves_acacia_opaque.png"),
    DARK_OAK_LEAF("\u6df1\u8272\u6a61\u6811\u53f6", 161, 1, "textures/blocks/leaves_big_oak_opaque.png"),
    ACACIA_WOOD("\u91d1\u5408\u6b22\u6728", 162, 0, "textures/blocks/log_acacia.png"),
    DARK_OAK("\u6df1\u8272\u6a61\u6728", 162, 1, "textures/blocks/log_acacia.png"),
    ACACIA_STAIRS("\u91d1\u5408\u6b22\u6728\u9636\u68af", 163, 0, "textures/blocks/planks_acacia.png"),
    DARK_OAK_STAIRS("\u6df1\u8272\u6a61\u6728\u9636\u68af", 164, 0, "textures/blocks/planks_big_oak.png"),
    SLIME("\u7c98\u6db2\u5757", 165, 0, "textures/blocks/slime.png"),
    IRON_DOOR("\u94c1\u95e8", 330, 0, "textures/blocks/door_iron_upper.png"),
    PRISMARINE("\u6d77\u6676\u77f3", 168, 0, "textures/blocks/prismarine_dark.png"),
    DARK_PRISMARINE("\u6697\u6d77\u6676\u77f3", 168, 1, "textures/blocks/prismarine_dark.png"),
    PRISMARINE_STONE_BRICK("\u6d77\u6676\u77f3\u7816", 168, 2, "textures/blocks/prismarine_bricks.png"),
    SEA_LANTERN("\u6d77\u6676\u706f", 169, 0, "textures/blocks/sea_lantern.png"),
    HAY_BLOCK("\u5e72\u8349\u6346", 170, 0, "textures/blocks/hay_block_side.png"),
    WHITE_CARPET("\u767d\u8272\u5730\u6bef", 171, 0, "textures/blocks/wool_colored_white.png"),
    ORANGE_CARPET("\u6a59\u8272\u5730\u6bef", 171, 1, "textures/blocks/wool_colored_orange.png"),
    SOLFERINO_CARPET("\u54c1\u7ea2\u8272\u5730\u6bef", 171, 2, "textures/blocks/wool_colored_magenta.png"),
    LIGHT_BLUE_CARPET("\u6de1\u84dd\u8272\u5730\u6bef", 171, 3, "textures/blocks/wool_colored_light_blue.png"),
    YELLOW_CARPET("\u9ec4\u8272\u5730\u6bef", 171, 4, "textures/blocks/wool_colored_yellow.png"),
    OLIVINE_CARPET("\u9ec4\u7eff\u8272\u5730\u6bef", 171, 5, "textures/blocks/wool_colored_lime.png"),
    PINK_CARPET("\u7c89\u7ea2\u8272\u5730\u6bef", 171, 6, "textures/blocks/wool_colored_pink.png"),
    GRAY_CARPET("\u7070\u8272\u5730\u6bef", 171, 7, "textures/blocks/wool_colored_gray.png"),
    LIGHT_GRAY_CARPET("\u6de1\u7070\u8272\u5730\u6bef", 171, 8, "textures/blocks/wool_colored_silver.png"),
    CYAN_CARPET("\u9752\u8272\u5730\u6bef", 171, 9, "textures/blocks/wool_colored_cyan.png"),
    VIOLET_CARPET("\u7d2b\u8272\u5730\u6bef", 171, 10, "textures/blocks/wool_colored_purple.png"),
    BLUE_CARPET("\u84dd\u8272\u5730\u6bef", 171, 11, "textures/blocks/wool_colored_blue.png"),
    BROWN_CARPET("\u68d5\u8272\u5730\u6bef", 171, 12, "textures/blocks/wool_colored_brown.png"),
    GREEN_CARPET("\u7eff\u8272\u5730\u6bef", 171, 13, "textures/blocks/wool_colored_green.png"),
    RED_CARPET("\u7ea2\u8272\u5730\u6bef", 171, 14, "textures/blocks/wool_colored_red.png"),
    BLACK_CARPET("\u9ed1\u8272\u5730\u6bef", 171, 15, "textures/blocks/wool_colored_black.png"),
    HARDENED_CLAY("\u9676\u74e6", 172, 0, "textures/blocks/hardened_clay.png"),
    COAL_BLOCK("\u7164\u70ad\u5757", 173, 0, "textures/blocks/coal_block.png"),
    PACKED_ICE("\u6d6e\u51b0", 174, 0, "textures/blocks/ice_packed.png"),
    SUNFLOWER("\u5411\u65e5\u8475", 175, 0, "textures/blocks/double_plant_sunflower_front.png"),
    LILAC("\u4e01\u9999", 175, 1, "textures/blocks/flower_cornflower.png"),
    TALL_GRASS("\u9ad8\u8349\u4e1b", 175, 2, "textures/blocks/double_plant_grass_carried.png"),
    LARGE_FERN("\u5927\u578b\u8568", 175, 3, "textures/blocks/tallgrass.png"),
    ROSE_BUSH("\u73ab\u7470\u4e1b", 175, 4, "textures/blocks/sweet_berry_bush_stage3.png"),
    PEONY("\u7261\u4e39", 175, 5, "textures/blocks/flower_allium.png"),
    STANDING_BANNER("\u65d7\u5e1c", 176, 0, "textures/blocks/bone_block_side.png"),
    WALL_BANNER("\u60ac\u6302\u7684\u65d7\u5e1c", 177, 0, "textures/blocks/bone_block_side.png"),
    RED_SANDSTONE("\u7ea2\u6c99\u77f3", 179, 0, "textures/blocks/red_sandstone_bottom.png"),
    CHISELED_RED_SANDSTONE("\u933e\u5236\u7ea2\u6c99\u77f3", 179, 1, "textures/blocks/red_sandstone_carved.png"),
    SMOOTH_RED_SANDSTONE("\u5e73\u6ed1\u7ea2\u6c99\u77f3", 179, 2, "textures/blocks/red_sandstone_smooth.png"),
    RED_SANDSTONE_STAIRS("\u7ea2\u6c99\u77f3\u9636\u68af", 180, 0, "textures/blocks/red_sandstone_carved.png"),
    STONE_SLAB2("\u7ea2\u6c99\u77f3\u53f0\u9636", 182, 0, "textures/blocks/red_sandstone_smooth.png"),
    SPRUCE_FENCE_GATE("\u4e91\u6749\u56f4\u5899\u5927\u95e8", 183, 0, "textures/blocks/door_spruce_lower.png"),
    BIRCH_FENCE_GATE("\u6866\u6728\u56f4\u5899\u5927\u95e8", 184, 0, "textures/blocks/door_birch_upper.png"),
    JUNGLE_FENCE_GATE("\u4e1b\u6797\u6728\u56f4\u5899\u5927\u95e8", 185, 0, "textures/blocks/door_acacia_upper.png"),
    DARK_OAK_FENCE_GATE("\u6df1\u8272\u6a61\u6728\u56f4\u5899\u5927\u95e8", 186, 0, "textures/blocks/door_dark_oak_lower.png"),
    ACACIA_FENCE_GATE("\u91d1\u5408\u6b22\u6728\u56f4\u5899\u5927\u95e8", 187, 0, "textures/blocks/door_spruce_lower.png"),
    SPRUCE_FENCE("\u91cd\u590d\u547d\u4ee4\u5757", 188, 0, "textures/blocks/chain_command_block_conditional_mipmap.png"),
    BIRCH_FENCE("\u94fe\u547d\u4ee4\u5757", 189, 0, "textures/blocks/repeating_command_block_back_mipmap.png"),
    BIRCH_DOOR("\u6866\u6728\u95e8", 194, 0, "textures/blocks/door_birch_upper.png"),
    END_ROD("\u7eff\u8335\u5c0f\u9053", 198, 0, "textures/blocks/end_rod.png"),
    CHORUS_FLOWER("\u5171\u9e23\u82b1", 200, 0, "textures/blocks/chorus_flower.png"),
    PURPUR_BLOCK("\u7d2b\u73c0\u65b9\u5757", 201, 0, "textures/blocks/purpur_block.png"),
    PURPUR_STAIRS("\u7d2b\u73c0\u9636\u68af", 203, 0, "textures/blocks/purpur_block.png"),
    PURPUR_SLAB("\u6f5c\u533f\u4e4b\u8d1d\u7bb1\u5b50", 205, 0, "textures/blocks/shulker_top_undyed.png"),
    END_BRICKS("\u672b\u5730\u77f3\u7816", 206, 0, "textures/blocks/end_bricks.png"),
    GRASS_PATH("\u672b\u5730\u68d2", 208, 0, "textures/blocks/end_rod.png"),
    END_GATEWAY("\u672b\u5730\u95e82", 209, 0, "textures/blocks/end_gateway.png"),
    WHITE_GLAZED("\u767d\u8272\u5e26\u91c9\u9676\u74e6", 220, 0, "textures/blocks/glazed_terracotta_white.png"),
    GRAY_GLAZED("\u7070\u8272\u5e26\u91c9\u9676\u74e6", 227, 0, "textures/blocks/glazed_terracotta_gray.png"),
    SILVER_GLAZED("\u6de1\u7070\u8272\u5e26\u91c9\u9676\u74e6", 228, 0, "textures/blocks/glazed_terracotta_silver.png"),
    OBSIDIAN_GLAZED("\u9ed1\u8272\u5e26\u91c9\u9676\u74e6", 235, 0, "textures/blocks/glowing_obsidian.png"),
    BROWN_GLAZED("\u68d5\u8272\u5e26\u91c9\u9676\u74e6", 232, 0, "textures/blocks/glazed_terracotta_brown.png"),
    RED_GLAZED("\u7ea2\u8272\u5e26\u91c9\u9676\u74e6", 234, 0, "textures/blocks/glazed_terracotta_red.png"),
    ORANGE_GLAZED("\u6a59\u8272\u5e26\u91c9\u9676\u74e6", 221, 0, "textures/blocks/glazed_terracotta_orange.png"),
    YELLOW_GLAZED("\u9ec4\u8272\u5e26\u91c9\u9676\u74e6", 224, 0, "textures/blocks/glazed_terracotta_yellow.png"),
    LIME_GLAZED("\u9ec4\u7eff\u8272\u5e26\u91c9\u9676\u74e6", 225, 0, "textures/blocks/glazed_terracotta_lime.png"),
    GREEN_GLAZED("\u7eff\u8272\u5e26\u91c9\u9676\u74e6", 233, 0, "textures/blocks/glazed_terracotta_green.png"),
    CYAN_GLAZED("\u9752\u8272\u5e26\u91c9\u9676\u74e6", 229, 0, "textures/blocks/glazed_terracotta_cyan.png"),
    LBLUE_GLAZED("\u6de1\u84dd\u8272\u5e26\u91c9\u9676\u74e6", 223, 0, "textures/blocks/glazed_terracotta_light_blue.png"),
    BLUE_GLAZED("\u84dd\u8272\u5e26\u91c9\u9676\u74e6", 231, 0, "textures/blocks/glazed_terracotta_blue.png"),
    PURPLE_GLAZED("\u7d2b\u8272\u5e26\u91c9\u9676\u74e6", 219, 0, "textures/blocks/glazed_terracotta_purple.png"),
    MAGENTA_GLAZED("\u54c1\u7ea2\u8272\u5e26\u91c9\u9676\u74e6", 222, 0, "textures/blocks/glazed_terracotta_magenta.png"),
    PINK_GLAZED("\u7c89\u7ea2\u8272\u5e26\u91c9\u9676\u74e6", 226, 0, "textures/blocks/glazed_terracotta_pink.png"),
    CHORUS_PLANT("\u5171\u9e23\u690d\u7269", 240, 0, "textures/blocks/chorus_plant.png"),
    Glass_White("\u767d\u8272\u73bb\u7483", 241, 0, "textures/blocks/glass_white.png"),
    Glass_Orange("\u6a59\u8272\u73bb\u7483", 241, 1, "textures/blocks/glass_orange.png"),
    Glass_Magenta("\u54c1\u7ea2\u8272\u73bb\u7483", 241, 2, "textures/blocks/glass_magenta.png"),
    Glass_LBlue("\u6de1\u84dd\u8272\u73bb\u7483", 241, 3, "textures/blocks/glass_light_blue.png"),
    Glass_Yellow("\u9ec4\u8272\u73bb\u7483", 241, 4, "textures/blocks/glass_yellow.png"),
    Glass_Lime("\u9ec4\u7eff\u8272\u73bb\u7483", 241, 5, "textures/blocks/glass_lime.png"),
    Glass_Pink("\u7c89\u7ea2\u8272\u73bb\u7483", 241, 6, "textures/blocks/glass_pink.png"),
    Glass_Gray("\u7070\u8272\u73bb\u7483", 241, 7, "textures/blocks/glass_gray.png"),
    Glass_Silver("\u6de1\u7070\u8272\u73bb\u7483", 241, 8, "textures/blocks/glass_silver.png"),
    Glass_Cyan("\u9752\u8272\u73bb\u7483", 241, 9, "textures/blocks/glass_cyan.png"),
    Glass_Purple("\u7d2b\u8272\u73bb\u7483", 241, 10, "textures/blocks/glass_purple.png"),
    Glass_Blue("\u84dd\u8272\u73bb\u7483", 241, 11, "textures/blocks/glass_blue.png"),
    Glass_Brown("\u68d5\u8272\u73bb\u7483", 241, 12, "textures/blocks/glass_brown.png"),
    Glass_Green("\u7eff\u8272\u73bb\u7483", 241, 13, "textures/blocks/glass_green.png"),
    Glass_RED("\u7ea2\u8272\u73bb\u7483", 241, 14, "textures/blocks/glass_red.png"),
    Glass_Black("\u9ed1\u8272\u73bb\u7483", 241, 15, "textures/blocks/glass_black.png"),
    IRON_SHOVEL("\u94c1\u9539", 256, 0, "textures/items/iron_shovel.png"),
    IRON_PICKAXE("\u94c1\u9550", 257, 0, "textures/items/iron_pickaxe.png"),
    IRON_AXE("\u94c1\u65a7", 258, 0, "textures/items/iron_axe.png"),
    FLINT_AND_STEEL("\u6253\u706b\u77f3", 259, 0, "textures/items/flint_and_steel.png"),
    APPLE("\u82f9\u679c", 260, 0, "textures/items/apple.png"),
    BOW("\u5f13", 261, 0, "textures/items/bow_standby.png"),
    ARROW("\u7bad", 262, 0, "textures/items/arrow.png"),
    COAL("\u7164\u70ad", 263, 0, "textures/items/coal.png"),
    CHARCOAL("\u6728\u70ad", 263, 1, "textures/items/charcoal.png"),
    DIAMOND("\u94bb\u77f3", 264, 0, "textures/items/diamond.png"),
    IRON_INGOT("\u94c1\u952d", 265, 0, "textures/items/iron_ingot.png"),
    GOLD_INGOT("\u91d1\u952d", 266, 0, "textures/items/gold_ingot.png"),
    IRON_SWORD("\u94c1\u5251", 267, 0, "textures/items/iron_sword.png"),
    WOODEN_SWORD("\u6728\u5251", 268, 0, "textures/items/wood_sword.png"),
    WOODEN_SHOVEL("\u6728\u9539", 269, 0, "textures/items/wood_shovel.png"),
    WOODEN_PICKAXE("\u6728\u9550", 270, 0, "textures/items/wood_pickaxe.png"),
    WOODEN_AXE("\u6728\u65a7", 271, 0, "textures/items/wood_axe.png"),
    STONE_SWORD("\u77f3\u5251", 272, 0, "textures/items/stone_sword.png"),
    STONE_SHOVEL("\u77f3\u9539", 273, 0, "textures/items/stone_shovel.png"),
    STONE_PICKAXE("\u77f3\u9550", 274, 0, "textures/items/stone_pickaxe.png"),
    STONE_AXE("\u77f3\u65a7", 275, 0, "textures/items/stone_axe.png"),
    DIAMOND_SWORD("\u94bb\u77f3\u5251", 276, 0, "textures/items/diamond_sword.png"),
    DIAMOND_SHOVEL("\u94bb\u77f3\u9539", 277, 0, "textures/items/diamond_shovel.png"),
    DIAMOND_PICKAXE("\u94bb\u77f3\u9550", 278, 0, "textures/items/diamond_pickaxe.png"),
    DIAMOND_AXE("\u94bb\u77f3\u65a7", 279, 0, "textures/items/diamond_axe.png"),
    STICK("\u6728\u68cd", 280, 0, "textures/items/stick.png"),
    BOWL("\u7897", 281, 0, "textures/items/bowl.png"),
    MUSHROOM_STEW("\u8611\u83c7\u7172", 282, 0, "textures/items/mushroom_stew.png"),
    GOLDEN_SWORD("\u91d1\u5251", 283, 0, "textures/items/gold_sword.png"),
    GOLDEN_SHOVEL("\u91d1\u9539", 284, 0, "textures/items/gold_shovel.png"),
    GOLDEN_PICKAXE("\u91d1\u9550", 285, 0, "textures/items/gold_pickaxe.png"),
    GOLDEN_AXE("\u91d1\u65a7", 286, 0, "textures/items/gold_axe.png"),
    STRING("\u86db\u4e1d", 287, 0, "textures/items/string.png"),
    FEATHER("\u7fbd\u6bdb", 288, 0, "textures/items/feather.png"),
    GUNPOWDER("\u706b\u836f", 289, 0, "textures/items/gunpowder.png"),
    WOODEN_HOE("\u6728\u9504", 290, 0, "textures/items/wood_hoe.png"),
    STONE_HOE("\u77f3\u9504", 291, 0, "textures/items/stone_hoe.png"),
    IRON_HOE("\u94c1\u9504", 292, 0, "textures/items/iron_hoe.png"),
    DIAMOND_HOE("\u94bb\u77f3\u9504", 293, 0, "textures/items/diamond_hoe.png"),
    GOLDEN_HOE("\u91d1\u9504", 294, 0, "textures/items/gold_hoe.png"),
    WHEAT_SEEDS("\u79cd\u5b50", 295, 0, "textures/items/seeds_wheat.png"),
    WHEAT("\u5c0f\u9ea6", 296, 0, "textures/items/wheat.png"),
    BREAD("\u9762\u5305", 297, 0, "textures/items/bread.png"),
    LEATHER_HELMET("\u76ae\u9769\u5934\u76d4", 298, 0, "textures/items/leather_helmet.tga"),
    LEATHER_CHESTPLATE("\u76ae\u9769\u80f8\u7532", 299, 0, "textures/items/leather_chestplate.png"),
    LEATHER_LEGGINGS("\u76ae\u9769\u62a4\u817f", 300, 0, "textures/items/leather_leggings.tga"),
    LEATHER_BOOTS("\u76ae\u9769\u9774\u5b50", 301, 0, "textures/items/leather_boots.tga"),
    CHAINMAIL_HELMET("\u9501\u94fe\u5934\u76d4", 302, 0, "textures/items/chainmail_helmet.png"),
    CHAINMAIL_CHESTPLATE("\u9501\u94fe\u80f8\u7532", 303, 0, "textures/items/chainmail_chestplate.png"),
    CHAINMAIL_LEGGINGS("\u9501\u94fe\u62a4\u817f", 304, 0, "textures/items/chainmail_leggings.png"),
    CHAINMAIL_BOOTS("\u9501\u94fe\u9774\u5b50", 305, 0, "textures/items/chainmail_boots.png"),
    IRON_HELMET("\u94c1\u5934\u76d4", 306, 0, "textures/items/iron_helmet.png"),
    IRON_CHESTPLATE("\u94c1\u80f8\u7532", 307, 0, "textures/items/iron_chestplate.png"),
    IRON_LEGGINGS("\u94c1\u62a4\u817f", 308, 0, "textures/items/iron_leggings.png"),
    IRON_BOOTS("\u94c1\u9774\u5b50", 309, 0, "textures/items/iron_boots.png"),
    DIAMOND_HELMET("\u94bb\u77f3\u5934\u76d4", 310, 0, "textures/items/diamond_helmet.png"),
    DIAMOND_CHESTPLATE("\u94bb\u77f3\u80f8\u7532", 311, 0, "textures/items/diamond_chestplate.png"),
    DIAMOND_LEGGINGS("\u94bb\u77f3\u62a4\u817f", 312, 0, "textures/items/diamond_leggings.png"),
    DIAMOND_BOOTS("\u94bb\u77f3\u9774\u5b50", 313, 0, "textures/items/diamond_boots.png"),
    GOLDEN_HELMET("\u91d1\u5934\u76d4", 314, 0, "textures/items/gold_helmet.png"),
    GOLDEN_CHESTPLATE("\u91d1\u80f8\u7532", 315, 0, "textures/items/gold_chestplate.png"),
    GOLDEN_LEGGINGS("\u91d1\u62a4\u817f", 316, 0, "textures/items/gold_leggings.png"),
    GOLDEN_BOOTS("\u91d1\u9774\u5b50", 317, 0, "textures/items/gold_boots.png"),
    FLINT("\u71e7\u77f3", 318, 0, "textures/items/flint.png"),
    PORKCHOP("\u751f\u732a\u6392", 319, 0, "textures/items/porkchop_raw.png"),
    COOKED_PORKCHOP("\u719f\u732a\u6392", 320, 0, "textures/items/porkchop_cooked.png"),
    PAINTING("\u753b", 321, 0, "textures/items/painting.png"),
    GOLDEN_APPLE("\u91d1\u82f9\u679c", 322, 0, "textures/items/apple_golden.png"),
    SIGN("\u544a\u793a\u724c", 323, 0, "textures/items/sign.png"),
    WOODEN_DOOR("\u6a61\u6728\u95e8", 324, 0, "textures/items/door_wood.png"),
    BUCKET("\u6876", 325, 0, "textures/items/bucket_empty.png"),
    MINECART("\u77ff\u8f66", 328, 0, "textures/items/minecart_normal.png"),
    SADDLE("\u978d", 329, 0, "textures/items/saddle.png"),
    REDSTONE("\u7ea2\u77f3", 331, 0, "textures/items/redstone_dust.png"),
    SNOWBALL("\u96ea\u7403", 332, 0, "textures/items/snowball.png"),
    BOAT("\u6a61\u6728\u8239", 333, 0, "textures/items/boat.png"),
    LEATHER("\u76ae\u9769", 334, 0, "textures/items/leather.png"),
    CLAY_BALL("\u7c98\u571f\u7403", 337, 0, "textures/items/clay_ball.png"),
    BRICK("\u7ea2\u7816", 336, 0, "textures/items/brick.png"),
    REEDS("\u7518\u8517", 338, 0, "textures/items/reeds.png"),
    PAPER("\u7eb8", 339, 0, "textures/items/paper.png"),
    BOOK("\u4e66", 340, 0, "textures/items/book_normal.png"),
    SLIME_BALL("\u7c98\u6db2\u7403", 341, 0, "textures/items/slimeball.png"),
    CHEST_MINECART("\u7bb1\u5b50\u77ff\u8f66", 342, 0, "textures/items/minecart_chest.png"),
    EGG("\u9e21\u86cb", 344, 0, "textures/items/egg.png"),
    COMPASS("\u6307\u5357\u9488", 345, 0, "textures/items/compass_item.png"),
    FISHING_ROD("\u9c7c\u7aff", 346, 0, "textures/items/fishing_rod_cast.png"),
    CLOCK("\u65f6\u949f", 347, 0, "textures/items/clock_item.png"),
    GLOWSTONE_DUST("\u8367\u77f3\u7c89", 348, 0, "textures/items/glowstone_dust.png"),
    FISH("\u9c7c", 349, 0, "textures/items/fish_raw.png"),
    COOKED_FISH("\u719f\u9c7c", 350, 0, "textures/items/fish_cooked.png"),
    DYE("\u58a8\u56ca", 351, 0, "textures/items/dye_powder_black.png"),
    SOLFERINO_DYE("\u54c1\u7ea2\u8272\u67d3\u6599", 351, 1, "textures/items/dye_powder_purple.png"),
    GREEN_DYE("\u7eff\u8272\u67d3\u6599", 351, 2, "textures/items/dye_powder_green.png"),
    BROWN_DYE("\u53ef\u53ef\u8c46", 351, 3, "textures/items/dye_powder_brown.png"),
    BLUE_DYE("\u84dd\u8272\u67d3\u6599", 351, 4, "textures/items/dye_powder_blue.png"),
    VIOLET_DYE("\u7d2b\u8272\u67d3\u6599", 351, 5, "textures/items/dye_powder_purple.png"),
    CYAN_DYE("\u9752\u8272\u67d3\u6599", 351, 6, "textures/items/dye_powder_cyan.png"),
    LIGHT_GRAY_DYE("\u6de1\u7070\u8272\u67d3\u6599", 351, 7, "textures/items/dye_powder_silver.png"),
    GRAY_DYE("\u7070\u8272\u67d3\u6599", 351, 8, "textures/items/dye_powder_pink.png"),
    PINK_DYE("\u7c89\u7ea2\u8272\u67d3\u6599", 351, 9, "textures/items/dye_powder_pink.png"),
    OLIVINE_DYE("\u9ec4\u7eff\u8272\u67d3\u6599", 351, 10, "textures/items/dye_powder_lime.png"),
    YELLOW_DYE("\u9ec4\u8272\u67d3\u6599", 351, 11, "textures/items/dye_powder_yellow.png"),
    LIGHT_BLUE_DYE("\u6de1\u84dd\u8272\u67d3\u6599", 351, 12, "textures/items/dye_powder_light_blue.png"),
    RED_DYE("\u7ea2\u8272\u67d3\u6599", 351, 13, "textures/items/dye_powder_red.png"),
    ORANGE_DYE("\u6a59\u8272\u67d3\u6599", 351, 14, "textures/items/dye_powder_orange.png"),
    WHITE_DYE("\u9aa8\u7c89", 351, 15, "textures/items/dye_powder_white.png"),
    BONE("\u9aa8\u5934", 352, 0, "textures/items/bone.png"),
    SUGAR("\u7cd6", 353, 0, "textures/items/sugar.png"),
    CAKE("\u86cb\u7cd5", 354, 0, "textures/items/cake.png"),
    REPEATER("\u4e2d\u7ee7\u5668", 356, 0, "textures/items/repeater.png"),
    COOKIE("\u66f2\u5947", 357, 0, "textures/items/cookie.png"),
    FILLED_MAP("\u5730\u56fe(\u6ee1)", 358, 0, "textures/items/map_nautilus.png"),
    SHEARS("\u526a\u5200", 359, 0, "textures/items/shears.png"),
    MELON("\u897f\u74dc", 360, 0, "textures/items/melon.png"),
    MELON_SEEDS("\u5357\u74dc\u79cd\u5b50", 362, 0, "textures/items/seeds_melon.png"),
    BEEF("\u751f\u725b\u8089", 363, 0, "textures/items/beef_raw.png"),
    COOKED_BEEF("\u719f\u725b\u8089", 364, 0, "textures/items/beef_cooked.png"),
    CHICKEN("\u751f\u9e21\u8089", 365, 0, "textures/items/chicken_raw.png"),
    COOKED_CHICKEN("\u719f\u9e21\u8089", 366, 0, "textures/items/chicken_cooked.png"),
    ROTTEN_FLESH("\u8150\u8089", 367, 0, "textures/items/rotten_flesh.png"),
    ENDER_PEARL("\u672b\u5f71\u73cd\u73e0", 368, 0, "textures/items/ender_pearl.png"),
    BLAZE_ROD("\u70c8\u7130\u68d2", 369, 0, "textures/items/blaze_rod.png"),
    GHAST_TEAR("\u6076\u9b42\u6cea", 370, 0, "textures/items/ghast_tear.png"),
    GOLD_NUGGET("\u91d1\u7c92", 371, 0, "textures/items/gold_nugget.png"),
    NETHER_WART("\u5730\u72f1\u75a3", 372, 0, "textures/items/nether_wart.png"),
    POTION("\u6c34\u74f6", 373, 0, "textures/items/potion_bottle_drinkable.png"),
    GLASS_BOTTLE("\u73bb\u7483\u74f6", 374, 0, "textures/items/potion_bottle_empty.png"),
    SPIDER_EYE("\u8718\u86db\u773c", 375, 0, "textures/items/spider_eye.png"),
    SPIDER_EYE_FERMENTED("\u53d1\u9175\u8718\u86db\u773c", 376, 0, "textures/items/spider_eye_fermented.png"),
    BLAZE_POWDER("\u70c8\u7130\u7c89", 377, 0, "textures/items/blaze_powder.png"),
    MAGMA_CREAM("\u5ca9\u6d46\u818f", 378, 0, "textures/items/magma_cream.png"),
    ENDER_EYE("\u672b\u5f71\u4e4b\u773c", 381, 0, "textures/items/ender_eye.png"),
    SPECKLED_MELON("\u91d1\u897f\u74dc", 382, 0, "textures/items/melon_speckled.png"),
    EXPERIENCE_BOTTLE("\u7ecf\u9a8c\u74f6", 384, 0, "textures/items/experience_bottle.png"),
    FIRE_CHARGE("\u706b\u7403", 385, 0, "textures/items/fireball.png"),
    WRITE_BOOK("\u4e66", 387, 0, "textures/items/book_written.png"),
    EMERALD("\u7eff\u5b9d\u77f3", 388, 0, "textures/items/emerald.png"),
    ITEM_FRAME("\u5c55\u793a\u6846", 389, 0, "textures/items/item_frame.png"),
    FLOWER_POT("\u82b1\u76c6", 390, 0, "textures/items/flower_pot.png"),
    CARROT("\u80e1\u841d\u535c", 391, 0, "textures/items/carrot.png"),
    POTATO("\u9a6c\u94c3\u85af", 392, 0, "textures/items/potato.png"),
    BAKED_POTATO("\u70e4\u9a6c\u94c3\u85af", 393, 0, "textures/items/potato_baked.png"),
    POISONOUS_POTATO("\u6bd2\u9a6c\u94c3\u85af", 394, 0, "textures/items/potato_poisonous.png"),
    MAP("\u7a7a\u5730\u56fe", 395, 0, "textures/items/map_empty.png"),
    GOLDEN_CARROT("\u91d1\u80e1\u841d\u535c", 396, 0, "textures/items/carrot_golden.png"),
    SKELETON_SKULL("\u9ab7\u9ac5\u5934", 397, 0, "textures/items/bone.png"),
    LEIERDA_SKULL("\u51cb\u96f6\u9ab7\u9ac5\u5934", 397, 1, "textures/blocks/observer_front.png"),
    ZOMBIE_SKULL("\u50f5\u5c38\u5934", 397, 2, "textures/blocks/observer_front.png"),
    STEVE_SKULL("\u53f2\u8482\u592b\u5934", 397, 3, "textures/ui/Friend2.png"),
    CREEPER_SKULL("\u82e6\u529b\u6015\u5934", 397, 4, "textures/blocks/observer_front.png"),
    DRAGON_SKULL("\u9f99\u5934", 397, 5, "textures/blocks/observer_front.png"),
    CARROT_ON_A_STICK("\u80e1\u841d\u535c\u6746", 398, 0, "textures/items/carrot_on_a_stick.png"),
    NETHER_STAR("\u4e0b\u5c4a\u4e4b\u661f", 399, 0, "textures/items/nether_star.png"),
    PUMPKIN_PIE("\u5357\u74dc\u6d3e", 400, 0, "textures/items/pumpkin_pie.png"),
    ENCHANTED_BOOK("\u9644\u9b54\u4e66", 403, 0, "textures/items/book_enchanted.png"),
    COMPARATOR("\u6bd4\u8f83\u5668", 404, 0, "textures/items/comparator.png"),
    NETHER_BRICK("\u5730\u72f1\u7816", 405, 0, "textures/items/netherbrick.png"),
    QUARTZ("\u5730\u72f1\u77f3\u82f1", 406, 0, "textures/items/quartz.png"),
    TNT_MINECART("tnt\u77ff\u8f66", 407, 0, "textures/items/minecart_tnt.png"),
    HOPPER_MINECART("\u6f0f\u6597\u77ff\u8f66", 408, 0, "textures/items/minecart_hopper.png"),
    PRISMARINE_SHARD("\u6d77\u6676\u788e\u7247", 409, 0, "textures/items/prismarine_shard.png"),
    DRIED_KELP("\u5e72\u6d77\u5e26", 464, 0, "textures/items/dried_kelp.png"),
    KELP("\u6d77\u5e26", 335, 0, "textures/items/kelp.png"),
    RABBIT("\u751f\u5154\u5b50\u8089", 411, 0, "textures/items/rabbit_raw.png"),
    COOKED_RABBIT("\u719f\u5154\u5b50\u8089", 412, 0, "textures/items/rabbit_cooked.png"),
    RABBIT_STEW("\u5154\u5b50\u7172", 413, 0, "textures/items/rabbit_stew.png"),
    RABBIT_FOOT("\u5154\u5b50\u811a", 414, 0, "textures/items/rabbit_foot.png"),
    RABBIT_HIDE("\u5154\u5b50\u76ae", 415, 0, "textures/items/rabbit_hide.png"),
    ARMOR_STAND("\u76ae\u9769\u9a6c\u978d", 416, 0, "textures/items/saddle.png"),
    IRON_HORSE_ARMOR("\u94c1\u9a6c\u978d", 417, 0, "textures/items/iron_horse_armor.png"),
    GOLD_HORSE_ARMOR("\u91d1\u9a6c\u978d", 418, 0, "textures/items/gold_horse_armor.png"),
    DIAMOND_HORSE_ARMOR("\u94bb\u77f3\u9a6c\u978d", 419, 0, "textures/items/diamond_horse_armor.png"),
    LEAD("\u6813\u7ef3", 420, 0, "textures/items/lead.png"),
    NAME_TAG("\u547d\u540d\u724c", 421, 0, "textures/items/name_tag.png"),
    Prismarine("\u6d77\u6676\u7802\u783e", 422, 0, "textures/items/prismarine_crystals.png"),
    MUTTON("\u751f\u7f8a\u8089", 423, 0, "textures/items/mutton_raw.png"),
    COOKED_MUTTON("\u719f\u7f8a\u8089", 424, 0, "textures/items/mutton_cooked.png"),
    mysj("\u672b\u5f71\u6c34\u6676", 426, 0, "textures/items/end_crystal.png"),
    SPRUCE_DOOR("\u4e91\u6749\u6728\u95e8", 427, 0, "textures/items/door_jungle.png"),
    BIRCH_WOOD_DOOR("\u6866\u6811\u6728\u95e8", 428, 0, "textures/items/door_birch.png"),
    JUNGLE_DOOR("\u4e1b\u6797\u6728\u95e8", 429, 0, "textures/items/door_spruce.png"),
    ACACIA_DOOR("\u91d1\u5408\u6b22\u6728\u95e8", 430, 0, "textures/items/door_acacia.png"),
    DARK_OAK_DOOR("\u6df1\u8272\u6a61\u6728\u95e8", 431, 0, "textures/items/door_dark_oak.png"),
    CHORUS_FRUIT("\u5171\u9e23\u679c", 432, 0, "textures/items/chorus_fruit.png"),
    POPPED_CHORUS_FRUIT("\u7206\u88c2\u5171\u9e23\u679c", 433, 0, "textures/items/chorus_fruit_popped.png"),
    DRAGON_BREATH("\u9f99\u606f", 437, 0, "textures/items/dragons_breath.png"),
    SPLASH_POTION("\u55b7\u6e85\u7684\u6c34\u74f6", 438, 0, "textures/items/potion_bottle_splash.png"),
    LINGERING_POTION("\u9057\u7559\u7684\u6c34\u74f6", 441, 0, "textures/items/potion_bottle_lingering_waterBreathing.png"),
    ELYTRA("\u7fc5\u9798", 444, 0, "textures/items/elytra.png"),
    BIRCH_BOAT("\u6f5c\u533f\u4e4b\u58f3", 445, 0, "textures/items/shulker_shell.png"),
    no_death("\u4e0d\u6b7b\u56fe\u817e", 450, 0, "textures/items/totem.png"),
    THREE_WEAPON("\u4e09\u53c9\u621f", 455, 0, "textures/items/trident.png"),
    BEET("\u751c\u83dc\u6839", 457, 0, "textures/items/beetroot.png"),
    YINGWU("\u9e66\u9e49\u87ba\u58f3", 465, 0, "textures/items/nautilus.png"),
    ENCHANT_GOLD_APPLE("\u9644\u9b54\u91d1\u82f9\u679c", 466, 0, "textures/items/apple_golden.png"),
    SEA_HEART("\u6d77\u6d0b\u4e4b\u5fc3", 467, 0, "textures/items/heartofthesea_closed.png"),
    TURTLE_HELMET("\u6d77\u9f9f\u58f3", 469, 0, "textures/items/turtle_helmet.png"),
    PHANTOM("\u5e7b\u5f71\u8584\u819c", 470, 0, "textures/items/phantom_membrane.png"),
    SWEET_BERRIES("\u751c\u6885", 477, 0, "textures/items/sweet_berries.png"),
    MAGMA("\u5ca9\u6d46\u5757", 213, 0, "textures/blocks/magma.png"),
    CONCRETE_POWDER_SILVER("\u6de1\u7070\u8272\u6df7\u51dd\u571f\u7c89\u672b", 237, 8, "textures/blocks/concrete_powder_silver.png"),
    CONCRETE_POWDER_GRAY("\u7070\u8272\u6df7\u51dd\u571f\u7c89\u672b", 237, 7, "textures/blocks/concrete_powder_gray.png"),
    CONCRETE_POWDER_BLACK("\u9ed1\u8272\u6df7\u51dd\u571f\u7c89\u672b", 237, 15, "textures/blocks/concrete_powder_black.png"),
    CONCRETE_POWDER_BROWN("\u68d5\u8272\u6df7\u51dd\u571f\u7c89\u672b", 237, 12, "textures/blocks/concrete_powder_brown.png"),
    CONCRETE_POWDER_RED("\u7ea2\u8272\u6df7\u51dd\u571f\u7c89\u672b", 237, 14, "textures/blocks/concrete_powder_red.png"),
    CONCRETE_POWDER_ORANGE("\u6a59\u8272\u6df7\u51dd\u571f\u7c89\u672b", 237, 1, "textures/blocks/concrete_powder_orange.png"),
    CONCRETE_POWDER_YELLOW("\u9ec4\u8272\u6df7\u51dd\u571f\u7c89\u672b", 237, 4, "textures/blocks/concrete_powder_yellow.png"),
    CONCRETE_POWDER_LIME("\u9ec4\u7eff\u8272\u6df7\u51dd\u571f\u7c89\u672b", 237, 5, "textures/blocks/concrete_powder_lime.png"),
    CONCRETE_POWDER_GREEN("\u7eff\u8272\u6df7\u51dd\u571f\u7c89\u672b", 237, 13, "textures/blocks/concrete_powder_green.png"),
    CONCRETE_POWDER_CYAN("\u9752\u8272\u6df7\u51dd\u571f\u7c89\u672b", 237, 9, "textures/blocks/concrete_powder_cyan.png"),
    CONCRETE_POWDER_LIGHT_BLUE("\u6de1\u84dd\u8272\u6df7\u51dd\u571f\u7c89\u672b", 237, 3, "textures/blocks/concrete_powder_light_blue.png"),
    CONCRETE_POWDER_BLUE("\u84dd\u8272\u6df7\u51dd\u571f\u7c89\u672b", 237, 11, "textures/blocks/concrete_powder_blue.png"),
    CONCRETE_POWDER_PURPLE("\u7d2b\u8272\u6df7\u51dd\u571f\u7c89\u672b", 237, 10, "textures/blocks/concrete_powder_purple.png"),
    CONCRETE_POWDER_MAGENTA("\u54c1\u7ea2\u8272\u6df7\u51dd\u571f\u7c89\u672b", 237, 2, "textures/blocks/concrete_powder_magenta.png"),
    CONCRETE_POWDER_PINK("\u7c89\u8272\u6df7\u51dd\u571f\u7c89\u672b", 237, 6, "textures/blocks/concrete_powder_pink.png"),
    CONCRETE_POWDER_WHITE("\u767d\u8272\u6df7\u51dd\u571f\u7c89\u672b", 237, 0, "textures/blocks/concrete_powder_white.png"),
    CONCRETE_SILVER("\u6de1\u7070\u8272\u6df7\u51dd\u571f", 236, 8, "textures/blocks/concrete_silver.png"),
    CONCRETE_GRAY("\u7070\u8272\u6df7\u51dd\u571f", 236, 7, "textures/blocks/concrete_gray.png"),
    CONCRETE_BLACK("\u9ed1\u8272\u6df7\u51dd\u571f", 236, 15, "textures/blocks/concrete_black.png"),
    CONCRETE_BROWN("\u68d5\u8272\u6df7\u51dd\u571f", 236, 12, "textures/blocks/concrete_brown.png"),
    CONCRETE_RED("\u7ea2\u8272\u6df7\u51dd\u571f", 236, 14, "textures/blocks/concrete_red.png"),
    CONCRETE_ORANGE("\u6a59\u8272\u6df7\u51dd\u571f", 236, 1, "textures/blocks/concrete_orange.png"),
    CONCRETE_YELLOW("\u9ec4\u8272\u6df7\u51dd\u571f", 236, 4, "textures/blocks/concrete_yellow.png"),
    CONCRETE_LIME("\u9ec4\u7eff\u8272\u6df7\u51dd\u571f", 236, 5, "textures/blocks/concrete_lime.png"),
    CONCRETE_GREEN("\u7eff\u8272\u6df7\u51dd\u571f", 236, 13, "textures/blocks/concrete_green.png"),
    CONCRETE_CYAN("\u9752\u8272\u6df7\u51dd\u571f", 236, 9, "textures/blocks/concrete_cyan.png"),
    CONCRETE_LIGHT_BLUE("\u6de1\u84dd\u8272\u6df7\u51dd\u571f", 236, 3, "textures/blocks/concrete_light_blue.png"),
    CONCRETE_BLUE("\u84dd\u8272\u6df7\u51dd\u571f", 236, 11, "textures/blocks/concrete_blue.png"),
    CONCRETE_PURPLE("\u7d2b\u8272\u6df7\u51dd\u571f", 236, 10, "textures/blocks/concrete_purple.png"),
    CONCRETE_MAGENTA("\u54c1\u7ea2\u8272\u6df7\u51dd\u571f", 236, 2, "textures/blocks/concrete_magenta.png"),
    CONCRETE_PINK("\u7c89\u8272\u6df7\u51dd\u571f", 236, 6, "textures/blocks/concrete_pink.png"),
    CONCRETE_WHITE("\u767d\u8272\u6df7\u51dd\u571f", 236, 0, "textures/blocks/concrete_white.png"),
    ARMOR_STAND_ITEM("\u76d4\u7532\u67b6", 425, 0, "textures/items/armor_stand.png"),
    BOOK_WRITABLE("\u4e66\u4e0e\u7fbd\u6bdb", 386, 0, "textures/items/book_writable.png"),
    SHULKER_TOP_WHITE("\u767d\u8272\u6f5c\u533f\u4e4b\u8d1d\u7bb1\u5b50", 218, 0, "textures/blocks/shulker_top_white.png"),
    SHULKER_TOP_SILVER("\u6de1\u7070\u8272\u6f5c\u533f\u4e4b\u8d1d\u7bb1\u5b50", 218, 8, "textures/blocks/shulker_top_silver.png"),
    SHULKER_TOP_GRAY("\u7070\u8272\u6f5c\u533f\u4e4b\u8d1d\u7bb1\u5b50", 218, 7, "textures/blocks/shulker_top_gray.png"),
    SHULKER_TOP_BLACK("\u9ed1\u8272\u6f5c\u533f\u4e4b\u8d1d\u7bb1\u5b50", 218, 15, "textures/blocks/shulker_top_black.png"),
    SHULKER_TOP_BROWN("\u68d5\u8272\u6f5c\u533f\u4e4b\u8d1d\u7bb1\u5b50", 218, 12, "textures/blocks/shulker_top_brown.png"),
    SHULKER_TOP_RED("\u7ea2\u8272\u6f5c\u533f\u4e4b\u8d1d\u7bb1\u5b50", 218, 14, "textures/blocks/shulker_top_red.png"),
    SHULKER_TOP_ORANGE("\u6a59\u8272\u6f5c\u533f\u4e4b\u8d1d\u7bb1\u5b50", 218, 1, "textures/blocks/shulker_top_orange.png"),
    SHULKER_TOP_YELLOW("\u9ec4\u8272\u6f5c\u533f\u4e4b\u8d1d\u7bb1\u5b50", 218, 4, "textures/blocks/shulker_top_yellow.png"),
    SHULKER_TOP_LIME("\u9ec4\u7eff\u8272\u6f5c\u533f\u4e4b\u8d1d\u7bb1\u5b50", 218, 5, "textures/blocks/shulker_top_lime.png"),
    SHULKER_TOP_GREEN("\u7eff\u8272\u6f5c\u533f\u4e4b\u8d1d\u7bb1\u5b50", 218, 13, "textures/blocks/shulker_top_green.png"),
    SHULKER_TOP_CYAN("\u9752\u8272\u6f5c\u533f\u4e4b\u8d1d\u7bb1\u5b50", 218, 9, "textures/blocks/shulker_top_cyan.png"),
    SHULKER_TOP_LIGHT_BLUE("\u6de1\u84dd\u8272\u6f5c\u533f\u4e4b\u8d1d\u7bb1\u5b50", 218, 3, "textures/blocks/shulker_top_light_blue.png"),
    SHULKER_TOP_BLUE("\u84dd\u8272\u6f5c\u533f\u4e4b\u8d1d\u7bb1\u5b50", 218, 11, "textures/blocks/shulker_top_blue.png"),
    SHULKER_TOP_PURPLE("\u7d2b\u8272\u6f5c\u533f\u4e4b\u8d1d\u7bb1\u5b50", 218, 10, "textures/blocks/shulker_top_purple.png"),
    SHULKER_TOP_MAGENTA("\u54c1\u7ea2\u8272\u6f5c\u533f\u4e4b\u8d1d\u7bb1\u5b50", 218, 2, "textures/blocks/shulker_top_magenta.png"),
    SHULKER_TOP_PINK("\u7c89\u8272\u6f5c\u533f\u4e4b\u8d1d\u7bb1\u5b50", 218, 6, "textures/blocks/shulker_top_pink.png"),
    BED_WHITE("\u767d\u8272\u5e8a", 355, 0, "textures/items/bed_white.png"),
    BED_SILVER("\u6de1\u7070\u8272\u5e8a", 355, 8, "textures/items/bed_silver.png"),
    BED_GRAY("\u7070\u8272\u5e8a", 355, 7, "textures/items/bed_gray.png"),
    BED_BLACK("\u9ed1\u8272\u5e8a", 355, 15, "textures/items/bed_black.png"),
    BED_BROWN("\u68d5\u8272\u5e8a", 355, 12, "textures/items/bed_brown.png"),
    BED_RED("\u7ea2\u8272\u5e8a", 355, 14, "textures/items/bed_red.png"),
    BED_ORANGE("\u6a59\u8272\u5e8a", 355, 1, "textures/items/bed_orange.png"),
    BED_YELLOW("\u9ec4\u8272\u5e8a", 355, 4, "textures/items/bed_yellow.png"),
    BED_LIME("\u9ec4\u7eff\u8272\u5e8a", 355, 5, "textures/items/bed_lime.png"),
    BED_GREEN("\u7eff\u8272\u5e8a", 355, 13, "textures/items/bed_green.png"),
    BED_CYAN("\u9752\u8272\u5e8a", 355, 9, "textures/items/bed_cyan.png"),
    BED_LIGHT_BLUE("\u6de1\u84dd\u8272\u5e8a", 355, 3, "textures/items/bed_light_blue.png"),
    BED_BLUE("\u84dd\u8272\u5e8a", 355, 11, "textures/items/bed_blue.png"),
    BED_PURPLE("\u7d2b\u8272\u5e8a", 355, 10, "textures/items/bed_purple.png"),
    BED_MAGENTA("\u54c1\u7ea2\u8272\u5e8a", 355, 2, "textures/items/bed_magenta.png"),
    BED_PINK("\u7c89\u8272\u5e8a", 355, 6, "textures/items/bed_pink.png"),
    CROSSBOW("\u5f29", 471, 0, "textures/items/crossbow_standby.png");

    private int ID;
    private int Damage;
    private String Name;
    private String Path;
    private static final HashMap<String, Map<String, Object>> NAME_MAP;
    private static final HashMap<String, Map<String, Object>> ID_MAP;
    private static final HashMap<String, ItemIDSunName> ItemIDSunName_MAP;
    private static final ArrayList<HashMap<String, Object>> All;

    private ItemIDSunName(String Name, int id, int Damage, String Path2) {
        this.ID = id;
        this.Name = Name;
        this.Damage = Damage;
        this.Path = Path2;
    }

    public String getPath() {
        return this.Path;
    }

    public String getName() {
        return this.Name;
    }

    public int getDamage() {
        return this.Damage;
    }

    public int getID() {
        return this.ID;
    }

    public static String getIDByPath(Item item) {
        String name = item.getId() + ":" + item.getDamage();
        String path = ItemIDSunName.getIDByPath(name);
        if (path == null && (path = ItemIDSunName.getIDByPath(item.getId())) == null) {
            return "textures/ui/how_to_play_button_default";
        }
        return path;
    }

    public static String getIDByPath(int ID) {
        return ItemIDSunName.getIDByPath(ID + ":0");
    }

    public static String getIDByPath(int ID, int Damage) {
        return ItemIDSunName.getIDByPath(ID + ":" + Damage);
    }

    public static String getIDByPath(String ID) {
        Map map = ID_MAP.getOrDefault(ID, null);
        if (map == null || map.getOrDefault("Path", null) == null) {
            return null;
        }
        return String.valueOf(map.get("Path"));
    }

    public static String getIDByName(int ID) {
        return ItemIDSunName.getIDByName(ID + ":0");
    }

    public static String getIDByName(int ID, int Damage) {
        return ItemIDSunName.getIDByName(ID + ":" + Damage);
    }

    public static String getIDByName(Item item) {
        String name = ItemIDSunName.getIDByName(item.getId() + ":" + item.getDamage());
        if (item.hasCustomName()) {
            return item.getCustomName();
        }
        if (name == null) {
            name = ItemIDSunName.getIDByName(item.getId() + ":0");
            if (name != null) {
                return name;
            }
            return item.getName();
        }
        return name;
    }

    public static String getIDByName(String ID) {
        Map map = ID_MAP.getOrDefault(ID, null);
        if (map == null || map.getOrDefault("Name", null) == null) {
            return null;
        }
        return String.valueOf(map.get("Name"));
    }

    public static int getNameByID(String Name) {
        Map map = NAME_MAP.getOrDefault(Name, null);
        if (map == null || map.getOrDefault("ID", null) == null) {
            return 0;
        }
        return Integer.valueOf(String.valueOf(map.get("ID")));
    }

    public static int getNameByDamage(String Name) {
        Map map = NAME_MAP.getOrDefault(Name, null);
        if (map == null || map.getOrDefault("Damage", null) == null) {
            return 0;
        }
        return Integer.valueOf(String.valueOf(map.get("Damage")));
    }

    public static String getNameByPath(String Name) {
        Map map = NAME_MAP.getOrDefault(Name, null);
        if (map == null || map.getOrDefault("Path", null) == null) {
            return null;
        }
        return String.valueOf(map.get("Path"));
    }

    public static ItemIDSunName getItem(int ID) {
        return ItemIDSunName.getItem(ID, 0);
    }

    public static ItemIDSunName getItem(int ID, int Damage) {
        return ItemIDSunName_MAP.getOrDefault(ID + ":" + Damage, null);
    }

    public static ItemIDSunName getItem(String Name) {
        return ItemIDSunName.getItem(ItemIDSunName.getNameByID(Name), ItemIDSunName.getNameByDamage(Name));
    }

    public static String UnknownToID(String ID) {
        if (!ID.contains(":")) {
            if (ItemIDSunName.getNameByPath(ID) != null) {
                return ItemIDSunName.getNameByID(ID) + ":" + ItemIDSunName.getNameByDamage(ID);
            }
            if (ItemIDSunName.getIDByPath(ID + ":0") != null) {
                return ID + ":0";
            }
            return ID;
        }
        if (ItemIDSunName.getIDByPath(ID) != null) {
            return ID;
        }
        if (ItemIDSunName.getNameByPath(ID) != null) {
            return ItemIDSunName.getNameByID(ID) + ":" + ItemIDSunName.getNameByDamage(ID);
        }
        return ID;
    }

    public static String UnknownToPath(String string) {
        if (ItemIDSunName.UnknownToID(string) != null) {
            return ItemIDSunName.getIDByPath(ItemIDSunName.UnknownToID(string));
        }
        return string;
    }

    public static ArrayList<HashMap<String, Object>> getAll() {
        return All;
    }

    static {
        NAME_MAP = new HashMap();
        ID_MAP = new HashMap();
        ItemIDSunName_MAP = new HashMap();
        All = new ArrayList();
        for (ItemIDSunName item : ItemIDSunName.values()) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ID", item.ID);
            map.put("Damage", item.Damage);
            map.put("Path", item.Path);
            map.put("Name", item.Name);
            map.put("item", (Object) item);
            All.add(map);
            NAME_MAP.put(item.Name, map);
            ID_MAP.put(item.ID + ":" + item.Damage, map);
            ItemIDSunName_MAP.put(item.ID + ":" + item.Damage, item);
        }
    }
}

