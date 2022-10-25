package com.creray.hedgecon.tags;

import com.creray.hedgecon.resources.ModResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class BiomeTags {
    public static final TagKey<Biome> SPAWNS_HEDGEHOGS = of("spawns_mob/hedgehog");

    private static TagKey<Biome> of(String id) {
        return TagKey.create(Registry.BIOME_REGISTRY, ModResourceLocation.of(id));
    }
}
