package io.creray.hedgecon;

import io.creray.hedgecon.world.entity.ModEntityTypes;
import io.creray.hedgecon.world.entity.ai.sensing.ModSensorTypes;
import io.creray.hedgecon.world.biome.BiomesSpawnModifications;
import io.creray.hedgecon.world.item.ModItems;
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
