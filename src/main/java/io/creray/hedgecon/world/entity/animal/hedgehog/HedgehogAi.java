package io.creray.hedgecon.world.entity.animal.hedgehog;

import io.creray.hedgecon.world.entity.ai.sensing.ModSensorTypes;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.crafting.Ingredient;

public class HedgehogAi {
    protected static final ImmutableList<SensorType<? extends Sensor<? super Hedgehog>>> SENSOR_TYPES;
    protected static final ImmutableList<MemoryModuleType<?>> MODULE_TYPES;

    public static Ingredient getTemptations() {
        return Hedgehog.TEMPTATION_ITEM;
    }

    protected static Brain<Hedgehog> makeBrain(Brain<Hedgehog> brain) {
        initCoreActivity(brain);
        initIdleActivity(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }

    protected static Brain.Provider<Hedgehog> provider() {
        return Brain.provider(MODULE_TYPES, SENSOR_TYPES);
    }

    private static void initCoreActivity(Brain<Hedgehog> brain) {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(
                new Swim(0.6f),
                new AnimalPanic(1.5f),
                new LookAtTargetSink(45, 90),
                new MoveToTargetSink(),
                new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)));
    }

    private static void initIdleActivity(Brain<Hedgehog> brain) {
        brain.addActivity(Activity.IDLE, ImmutableList.of(
                Pair.of(0, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0f, UniformInt.of(30, 60))),
                Pair.of(1, new FollowTemptation(hedgehog -> 1.25f)),
                Pair.of(2, new RunOne<>(
                        ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT),
                        ImmutableList.of(
                                Pair.of(RandomStroll.stroll(1.0f), 2),
                                Pair.of(SetWalkTargetFromLookTarget.create(1.0F, 3), 2),
                                Pair.of(new DoNothing(30, 60), 1))))));
    }

    protected static void updateActivities(Hedgehog hedgehog) {
        hedgehog.getBrain().setActiveActivityToFirstValid(ImmutableList.of(Activity.SWIM, Activity.IDLE));
    }

    static {
        SENSOR_TYPES = ImmutableList.of(
                SensorType.NEAREST_LIVING_ENTITIES,
                SensorType.NEAREST_PLAYERS,
                SensorType.HURT_BY,

                ModSensorTypes.HEDGEHOG_TEMPTATIONS
        );
        MODULE_TYPES = ImmutableList.of(
                MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
                MemoryModuleType.NEAREST_VISIBLE_PLAYER,

                MemoryModuleType.HURT_BY,
                MemoryModuleType.HURT_BY_ENTITY,

                MemoryModuleType.LOOK_TARGET,
                MemoryModuleType.WALK_TARGET,
                MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
                MemoryModuleType.PATH,

                MemoryModuleType.IS_TEMPTED,
                MemoryModuleType.TEMPTATION_COOLDOWN_TICKS,
                MemoryModuleType.TEMPTING_PLAYER,

                MemoryModuleType.IS_PANICKING
        );
    }
}
