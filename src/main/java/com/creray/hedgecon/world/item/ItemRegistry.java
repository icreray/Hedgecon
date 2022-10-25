package com.creray.hedgecon.world.item;

import com.creray.hedgecon.world.entity.EntityTypeRegistry;
import com.creray.hedgecon.resources.ModResourceLocation;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class ItemRegistry {

    public static final Item HEDGEHOG_SPAWN_EGG;

    static {
        HEDGEHOG_SPAWN_EGG = new SpawnEggItem(EntityTypeRegistry.HEDGEHOG, 0xD1C0B8, 0x574543, new FabricItemSettings().group(CreativeModeTab.TAB_MISC));
    }

    public static void register() {
        register("hedgehog_spawn_egg", HEDGEHOG_SPAWN_EGG);
    }

    private static void register(String id, Item item) {
        Registry.register(Registry.ITEM, ModResourceLocation.of(id), item);
    }
}
