package com.creray.hedgecon.world.level.storage.loot;

import com.creray.hedgecon.resources.ModResourceLocation;
import net.minecraft.resources.ResourceLocation;

public class ModLootTables {
    public static final ResourceLocation HEDGEHOG_SPAWNS_WITH_ENTITY;

    static {
        HEDGEHOG_SPAWNS_WITH_ENTITY = ModResourceLocation.of("entities/hedgehog/spawns_with");
    }
}
