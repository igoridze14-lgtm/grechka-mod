package net.grechka.mod;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class GrechkaEntityInit {
    public static final EntityModelLayer GRECHKA_LAYER = 
        new EntityModelLayer(new Identifier("grechka-mod", "grechka"), "main");

    public static void registerModelLayers() {
        EntityModelLayerRegistry.registerModelLayer(
            GRECHKA_LAYER,
            GrechkaModel::getTexturedModelData
        );
    }
}
