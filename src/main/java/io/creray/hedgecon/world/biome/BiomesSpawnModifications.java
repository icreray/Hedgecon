package io.creray.hedgecon.world.biome;

import io.creray.hedgecon.world.entity.ModEntityTypes;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;

public class BiomesSpawnModifications {

    public static void apply() {
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(
                        Biomes.TAIGA,
                        Biomes.OLD_GROWTH_PINE_TAIGA,
                        Biomes.OLD_GROWTH_SPRUCE_TAIGA,
                        Biomes.FOREST,
                        Biomes.FLOWER_FOREST,
                        Biomes.BIRCH_FOREST,
                        Biomes.OLD_GROWTH_BIRCH_FOREST,
                        Biomes.DARK_FOREST,
                        Biomes.GROVE),
                MobCategory.CREATURE,
                ModEntityTypes.HEDGEHOG,
                20,  1, 3
        );
    }
}
