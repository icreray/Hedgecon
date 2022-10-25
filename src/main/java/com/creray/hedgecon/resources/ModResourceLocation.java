package com.creray.hedgecon.resources;

import com.creray.hedgecon.Hedgecon;
import net.minecraft.resources.ResourceLocation;

public class ModResourceLocation {

    public static ResourceLocation of(String id) {
        return new ResourceLocation(Hedgecon.MOD_ID, id);
    }
}
