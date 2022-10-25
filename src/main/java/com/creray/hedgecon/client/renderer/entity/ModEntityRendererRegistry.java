package com.creray.hedgecon.client.renderer.entity;

import com.creray.hedgecon.client.model.geom.ModelLayers;
import com.creray.hedgecon.client.model.HedgehogModelLayer;
import com.creray.hedgecon.world.entity.EntityTypeRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ModEntityRendererRegistry {
    public static void register() {
        EntityRendererRegistry.register(EntityTypeRegistry.HEDGEHOG, HedgehogRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModelLayers.HEDGEHOG, HedgehogModelLayer::create);
    }
}
