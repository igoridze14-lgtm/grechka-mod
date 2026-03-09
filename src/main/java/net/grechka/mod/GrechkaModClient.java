package net.grechka.mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class GrechkaModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Регистрируем слои модели
        GrechkaEntityInit.registerModelLayers();
        
        // Регистрируем рендеринг собаки
        EntityRendererRegistry.register(GreczkaMod.GRECHKA_ENTITY_TYPE, 
            (ctx) -> new GrechkaRenderer(ctx));
    }
}
