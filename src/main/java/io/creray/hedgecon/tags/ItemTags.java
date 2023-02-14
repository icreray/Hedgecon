package io.creray.hedgecon.tags;

import io.creray.hedgecon.resources.ModResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ItemTags {
    public static final TagKey<Item> HEDGEHOG_TEMPT_ITEMS = bind("hedgehog_tempt_items");
    public static final TagKey<Item> CAN_GET_CAUGHT_ON_HEDGEHOG = bind("can_get_caught_on_hedgehog");
    private static TagKey<Item> bind(String id) {
        return TagKey.create(Registries.ITEM, ModResourceLocation.of(id));
    }
}
