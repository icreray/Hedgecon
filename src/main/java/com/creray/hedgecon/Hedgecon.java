package com.creray.hedgecon;

import com.creray.hedgecon.world.entity.EntityTypeRegistry;
import com.creray.hedgecon.world.item.ItemRegistry;
import com.creray.hedgecon.world.biome.BiomesSpawnModifications;
import net.fabricmc.api.ModInitializer;

public class Hedgecon implements ModInitializer {

    public static String MOD_ID = "hedgecon";

    @Override
    public void onInitialize() {
        EntityTypeRegistry.register();
        ItemRegistry.register();
        BiomesSpawnModifications.apply();
    }
}
