package com.creray.hedgecon.mixin.entity.behavior;

import com.creray.hedgecon.world.entity.ModEntityTypes;
import com.creray.hedgecon.world.entity.animal.hedgehog.Hedgehog;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(Fox.class)
public abstract class FoxMixin extends Animal {

    protected FoxMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "registerGoals", at = @At("HEAD"))
    private void addAttackHedgehogGoal(CallbackInfo callbackInfo) {
        Predicate<LivingEntity> isHedgehogPredicate = livingEntity -> livingEntity.getType() == ModEntityTypes.HEDGEHOG;
        goalSelector.addGoal(7, new NearestAttackableTargetGoal<>((Fox) (Object) this, Hedgehog.class, 30, false, false, isHedgehogPredicate));
    }
}
