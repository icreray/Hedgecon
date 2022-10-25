package com.creray.hedgecon.world.biome;

import com.creray.hedgecon.world.entity.EntityTypeRegistry;
import com.creray.hedgecon.tags.BiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.MobCategory;

public class BiomesSpawnModifications {
    public static void apply() {
        BiomeModifications.addSpawn(
                BiomeSelectors.tag(BiomeTags.SPAWNS_HEDGEHOGS),
                MobCategory.CREATURE,
                EntityTypeRegistry.HEDGEHOG,
                20,  1, 3
        );
    }
}
