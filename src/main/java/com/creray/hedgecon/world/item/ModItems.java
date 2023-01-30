package com.creray.hedgecon.world.item;

import com.creray.hedgecon.world.entity.ModEntityTypes;
import com.creray.hedgecon.resources.ModResourceLocation;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class ModItems {

    public static final Item HEDGEHOG_SPAWN_EGG;

    static {
        HEDGEHOG_SPAWN_EGG = new SpawnEggItem(ModEntityTypes.HEDGEHOG, 0xD1C0B8, 0x574543, new FabricItemSettings());
    }

    public static void register() {
        register("hedgehog_spawn_egg", ModItems.HEDGEHOG_SPAWN_EGG);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(entries -> entries.accept(HEDGEHOG_SPAWN_EGG));
    }

    private static void register(String id, Item item) {
        Registry.register(BuiltInRegistries.ITEM, ModResourceLocation.of(id), item);
    }
}
