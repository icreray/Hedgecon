package com.creray.hedgecon.client.model.geom;

import com.creray.hedgecon.resources.ModResourceLocation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;

@Environment(EnvType.CLIENT)
public class ModelLayers {
    private static final String DEFAULT_LAYER = "main";
    public static final ModelLayerLocation HEDGEHOG = register("hedgehog");

    private static ModelLayerLocation register(String id) {
        return createLocation(id, DEFAULT_LAYER);
    }

    private static ModelLayerLocation createLocation(String id, String layer) {
        return new ModelLayerLocation(ModResourceLocation.of(id), layer);
    }
}
