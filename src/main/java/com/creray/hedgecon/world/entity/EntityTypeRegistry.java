package com.creray.hedgecon.world.entity;

import com.creray.hedgecon.world.entity.animal.hedgehog.Hedgehog;
import com.creray.hedgecon.resources.ModResourceLocation;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class EntityTypeRegistry {

    public static final EntityType<Hedgehog> HEDGEHOG;

    static {
        HEDGEHOG = FabricEntityTypeBuilder.create(MobCategory.CREATURE, Hedgehog::new)
                .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                .build();
    }

    public static void register() {
        register("hedgehog", HEDGEHOG);
        registerAttributes();
    }

    @SuppressWarnings("ConstantConditions")
    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(HEDGEHOG, Hedgehog.createHedgehogAttributes());
    }

    private static void register(String id, EntityType<? extends Entity> type) {
        ResourceLocation identifier = ModResourceLocation.of(id);
        Registry.register(Registry.ENTITY_TYPE, identifier, type);
    }
}
