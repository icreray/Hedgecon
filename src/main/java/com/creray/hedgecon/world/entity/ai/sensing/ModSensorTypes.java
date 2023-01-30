package com.creray.hedgecon.world.entity.ai.sensing;

import com.creray.hedgecon.resources.ModResourceLocation;
import com.creray.hedgecon.world.entity.animal.hedgehog.HedgehogAi;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.sensing.TemptingSensor;

import java.util.function.Supplier;

public class ModSensorTypes {
    public static final SensorType<TemptingSensor> HEDGEHOG_TEMPTATIONS;

    static {
        HEDGEHOG_TEMPTATIONS = register("hedgehog_temptations", () -> new TemptingSensor(HedgehogAi.getTemptations()));
    }

    public static void init() {}

    private static <U extends Sensor<?>> SensorType<U> register(String id, Supplier<U> supplier) {
        return Registry.register(BuiltInRegistries.SENSOR_TYPE, ModResourceLocation.of(id), new SensorType<>(supplier));
    }
}
