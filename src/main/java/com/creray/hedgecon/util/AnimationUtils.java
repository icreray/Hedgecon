package com.creray.hedgecon.util;

import net.minecraft.world.entity.Entity;

public class AnimationUtils {

    public static float calculateWalkAnimationSpeedModifier(Entity entity) {
        return Math.min((float)entity.getDeltaMovement().lengthSqr() * 75.0F, 2.0F);
    }
}
