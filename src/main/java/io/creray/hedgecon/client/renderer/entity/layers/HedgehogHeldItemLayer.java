package io.creray.hedgecon.client.renderer.entity.layers;

import io.creray.hedgecon.client.model.HedgehogModel;
import io.creray.hedgecon.world.entity.animal.hedgehog.Hedgehog;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class HedgehogHeldItemLayer extends RenderLayer<Hedgehog, HedgehogModel> {
    private final ItemInHandRenderer itemInHandRenderer;

    public HedgehogHeldItemLayer(RenderLayerParent<Hedgehog, HedgehogModel> renderLayerParent, ItemInHandRenderer itemInHandRenderer) {
        super(renderLayerParent);
        this.itemInHandRenderer = itemInHandRenderer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, Hedgehog hedgehog, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        HedgehogModel model = getParentModel();

        poseStack.pushPose();

        // Move item (root.pivotX, (root.pivotY - 2.75) / 16, 0)
        poseStack.translate(0, 1.046875, 0);

        // Rotate item by 90 degrees
        poseStack.mulPose(Axis.XP.rotationDegrees(90));

        // Sync item rotation with root
        ModelPart root = model.root;
        poseStack.mulPose(Axis.YP.rotation(root.zRot));

        // Sync item rotation with body
        ModelPart body = model.body;
        poseStack.mulPose(Axis.ZN.rotation(body.yRot));
        poseStack.mulPose(Axis.XP.rotation(body.xRot));

        // Move item 2 pixels closer to face
        poseStack.translate(0, -0.125, 0);

        ItemStack itemStack = hedgehog.getItemBySlot(EquipmentSlot.MAINHAND);
        itemInHandRenderer.renderItem(hedgehog, itemStack, ItemTransforms.TransformType.GROUND, false, poseStack, multiBufferSource, light);

        poseStack.popPose();
    }
}
