package io.creray.hedgecon.client.model;

import io.creray.hedgecon.client.animation.definitions.HedgeconAnimation;
import io.creray.hedgecon.util.AnimationUtils;
import io.creray.hedgecon.world.entity.animal.hedgehog.Hedgehog;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;

@Environment(EnvType.CLIENT)
public class HedgehogModel extends HierarchicalModel<Hedgehog> {
    public final ModelPart root;
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
    public void setupAnim(Hedgehog hedgehog, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        root().getAllParts().forEach(ModelPart::resetPose);
        float walkAnimationSpeedModifier = AnimationUtils.calculateWalkAnimationSpeedModifier(hedgehog);
        animate(hedgehog.idleAnimationState, HedgeconAnimation.HEDGEHOG_IDLE, animationProgress);
        animate(hedgehog.walkAnimationState, HedgeconAnimation.HEDGEHOG_WALK, animationProgress, walkAnimationSpeedModifier);
        // animate(hedgehog.swimAnimationState, HedgeconAnimation.HEDGEHOG_SWIM, animationProgress);
    }
}
