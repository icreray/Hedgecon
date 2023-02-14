package io.creray.hedgecon.client.renderer.entity;

import io.creray.hedgecon.client.model.geom.ModelLayers;
import io.creray.hedgecon.client.model.HedgehogModelLayer;
import io.creray.hedgecon.world.entity.ModEntityTypes;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ModEntityRendererRegistry {
    public static void register() {
        EntityRendererRegistry.register(ModEntityTypes.HEDGEHOG, HedgehogRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModelLayers.HEDGEHOG, HedgehogModelLayer::create);
    }
}
