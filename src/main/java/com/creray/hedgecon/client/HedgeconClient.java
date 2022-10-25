package com.creray.hedgecon.client;

import com.creray.hedgecon.client.renderer.entity.ModEntityRendererRegistry;
import net.fabricmc.api.ClientModInitializer;

public class HedgeconClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModEntityRendererRegistry.register();
    }
}
