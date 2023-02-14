package io.creray.hedgecon.client;

import io.creray.hedgecon.client.renderer.entity.ModEntityRendererRegistry;
import net.fabricmc.api.ClientModInitializer;

public class HedgeconClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModEntityRendererRegistry.register();
    }
}
