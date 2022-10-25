package com.creray.hedgecon.client.model;

import com.creray.hedgecon.world.entity.animal.hedgehog.Hedgehog;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

@Environment(EnvType.CLIENT)
public class HedgehogModel extends HierarchicalModel<Hedgehog> {
    private final ModelPart root;
    public final ModelPart body;
    private final ModelPart nose;
    private final ModelPart rightEar;
    private final ModelPart leftEar;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;

    public HedgehogModel(ModelPart root) {
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.nose = body.getChild("nose");
        this.rightEar = body.getChild("right_ear");
        this.leftEar = body.getChild("left_ear");
        this.rightHindLeg = this.root.getChild("right_hind_leg");
        this.leftHindLeg = this.root.getChild("left_hind_leg");
        this.rightFrontLeg = this.root.getChild("right_front_leg");
        this.leftFrontLeg = this.root.getChild("left_front_leg");
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Hedgehog entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        final float walkAngleModifier = Mth.cos(limbAngle * 0.8f);
        final float limbXRotation = walkAngleModifier * limbDistance;
        final float reversedLimbPitch = -limbXRotation;

        rightHindLeg.xRot = limbXRotation;
        leftHindLeg.xRot = reversedLimbPitch;
        rightFrontLeg.xRot = reversedLimbPitch;
        leftFrontLeg.xRot = limbXRotation;

        body.yRot = limbXRotation * 0.3f;

        nose.xRot = Mth.cos(animationProgress * 0.8f) * 0.15f;
    }
}
