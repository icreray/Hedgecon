package com.creray.hedgecon;

import com.creray.hedgecon.world.entity.ModEntityTypes;
import com.creray.hedgecon.world.entity.ai.sensing.ModSensorTypes;
import com.creray.hedgecon.world.biome.BiomesSpawnModifications;
import com.creray.hedgecon.world.item.ModItems;
import net.fabricmc.api.ModInitializer;

public class Hedgecon implements ModInitializer {

    public static String MOD_ID = "hedgecon";

    @Override
    public void onInitialize() {
        ModSensorTypes.init();
        ModEntityTypes.register();
        ModItems.register();
        BiomesSpawnModifications.apply();
    }
}
