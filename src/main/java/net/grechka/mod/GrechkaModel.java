package net.grechka.mod;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

public class GrechkaModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart bb_main;

    public GrechkaModel(ModelPart root) {
        this.bb_main = root.getChild("bb_main");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        
        ModelPartData bb_main = modelPartData.addChild("bb_main", 
            ModelPartBuilder.create()
                .uv(0, 0).mirrored().cuboid(-1.0F, -10.0F, -3.0F, 6.0F, 4.0F, 6.0F, new Dilation(0.0F)).mirrored(false)
                .uv(44, 1).cuboid(3.0F, -14.0F, -3.0F, 4.0F, 4.0F, 6.0F, new Dilation(0.0F))
                .uv(49, 18).cuboid(7.0F, -12.0F, -1.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 10).cuboid(-7.0F, -9.0F, -2.0F, 6.0F, 3.0F, 4.0F, new Dilation(0.0F))
                .uv(27, 21).cuboid(5.0F, -16.0F, -3.0F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 26).cuboid(3.0F, -6.0F, -2.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 26).cuboid(3.0F, -6.0F, 1.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 26).cuboid(-6.0F, -6.0F, 1.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 26).cuboid(-6.0F, -6.0F, -2.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)),
            ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData _r1 = bb_main.addChild("_r1", 
            ModelPartBuilder.create()
                .uv(21, 38).cuboid(-12.1962F, -7.9282F, -0.6F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(28, 29).cuboid(-11.1962F, -4.9282F, -0.6F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)),
            ModelTransform.of(-1.0F, -0.3F, 0.0F, 0.0F, 0.0F, 0.5236F));

        ModelPartData _1_r1 = bb_main.addChild("1_r1", 
            ModelPartBuilder.create()
                .uv(16, 19).cuboid(6.0F, -15.8318F, -1.7962F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F)),
            ModelTransform.of(-1.0F, -0.3F, 0.0F, -0.2182F, 0.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Анимация движения ног
        this.bb_main.yaw = netHeadYaw * ((float) Math.PI / 180F);
        this.bb_main.pitch = headPitch * ((float) Math.PI / 180F);
    }

    @Override
    public void render(net.minecraft.client.util.math.MatrixStack matrices, net.minecraft.client.render.VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}
