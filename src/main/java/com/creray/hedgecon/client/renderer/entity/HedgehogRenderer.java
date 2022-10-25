package com.creray.hedgecon.client.renderer.entity;

import com.creray.hedgecon.client.renderer.entity.layers.HedgehogHeldItemLayer;
import com.creray.hedgecon.client.model.geom.ModelLayers;
import com.creray.hedgecon.client.model.HedgehogModel;
import com.creray.hedgecon.world.entity.animal.hedgehog.Hedgehog;
import com.creray.hedgecon.resources.ModResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HedgehogRenderer extends MobRenderer<Hedgehog, HedgehogModel> {
    public HedgehogRenderer(EntityRendererProvider.Context context) {
        super(context, new HedgehogModel(context.bakeLayer(ModelLayers.HEDGEHOG)), 0.25f);
        addLayer(new HedgehogHeldItemLayer(this, context.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(Hedgehog entity) {
        return ModResourceLocation.of("textures/entity/hedgehog/hedgehog.png");
    }
}
