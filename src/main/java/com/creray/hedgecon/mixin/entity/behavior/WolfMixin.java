package com.creray.hedgecon.mixin.entity.behavior;

import com.creray.hedgecon.world.entity.ModEntityTypes;
import com.creray.hedgecon.world.entity.animal.hedgehog.Hedgehog;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(Wolf.class)
public abstract class WolfMixin extends TamableAnimal {

    protected WolfMixin(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "registerGoals", at = @At("HEAD"))
    private void addAttackHedgehogGoal(CallbackInfo callbackInfo) {
        Predicate<LivingEntity> isHedgehogPredicate = livingEntity -> livingEntity.getType() == ModEntityTypes.HEDGEHOG;
        goalSelector.addGoal(7, new NonTameRandomTargetGoal<>((Wolf) (Object) this, Hedgehog.class, false, isHedgehogPredicate));
    }
}
