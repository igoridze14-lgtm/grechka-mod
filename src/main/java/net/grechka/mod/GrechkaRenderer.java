package net.grechka.mod;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class GrechkaRenderer extends MobEntityRenderer<GrechkaEntity, GrechkaModel<GrechkaEntity>> {
    
    private static final Identifier TEXTURE = new Identifier("grechka-mod", "textures/entity/grechka/dog.png");

    public GrechkaRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new GrechkaModel<>(ctx.getPart(GrechkaEntityInit.GRECHKA_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(GrechkaEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(GrechkaEntity entity, MatrixStack matrices, float tickDelta) {
        matrices.scale(0.8f, 0.8f, 0.8f);
    }
}
