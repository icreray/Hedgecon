package io.creray.hedgecon.resources;

import io.creray.hedgecon.Hedgecon;
import net.minecraft.resources.ResourceLocation;

public class ModResourceLocation {

    public static ResourceLocation of(String id) {
        return new ResourceLocation(Hedgecon.MOD_ID, id);
    }
}
