package com.creray.hedgecon.tags;

import com.creray.hedgecon.resources.ModResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ItemTags {
    public static final TagKey<Item> CAN_GET_CAUGHT_ON_HEDGEHOG = of("can_get_caught_on_hedgehog");
    private static TagKey<Item> of(String id) {
        return TagKey.create(Registry.ITEM_REGISTRY, ModResourceLocation.of(id));
    }
}
