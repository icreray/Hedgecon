package io.creray.hedgecon.client.animation.definitions;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.world.level.storage.loot.LootTable;

import static net.minecraft.client.animation.AnimationChannel.Targets;
import static net.minecraft.client.animation.AnimationChannel.Interpolations;

public class HedgeconAnimation {
    public static final AnimationDefinition HEDGEHOG_WALK = initWalkAnimation();
    public static final AnimationDefinition HEDGEHOG_IDLE = initIdleAnimation();
    // public static final AnimationDefinition HEDGEHOG_SWIM = initSwimAnimation();

    private static AnimationDefinition initWalkAnimation() {
        final float length = 0.25f;
        final float quarterOfLength = length / 4.0f;
        final float halfOfLength = length / 2.0f;
        final float halfAndQuarterOfLength = halfOfLength + quarterOfLength;

        final float rootZRotation = 1.5f;
        final float bodyXRotation = 1.5f;
        final float bodyYRotation = 4.5f;
        final float limbXRotation = 22.5f;

        AnimationChannel limbAnimationChannel = new AnimationChannel(Targets.ROTATION,
                new Keyframe(0.0f, KeyframeAnimations.degreeVec(limbXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(halfOfLength, KeyframeAnimations.degreeVec(-limbXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(length, KeyframeAnimations.degreeVec(limbXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM));

        AnimationChannel limbAnimationChannel2 = new AnimationChannel(Targets.ROTATION,
                new Keyframe(0.0f, KeyframeAnimations.degreeVec(-limbXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(halfOfLength, KeyframeAnimations.degreeVec(limbXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(length, KeyframeAnimations.degreeVec(-limbXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM));

        return AnimationDefinition.Builder.withLength(length).looping()
                .addAnimation("root", new AnimationChannel(Targets.ROTATION,
                        new Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, -rootZRotation), Interpolations.CATMULLROM),
                        new Keyframe(halfOfLength, KeyframeAnimations.degreeVec(0.0f, 0.0f, rootZRotation), Interpolations.CATMULLROM),
                        new Keyframe(length, KeyframeAnimations.degreeVec(0.0f, 0.0f, -rootZRotation), Interpolations.CATMULLROM)))
                .addAnimation("body", new AnimationChannel(Targets.ROTATION,
                        new Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, -bodyYRotation, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(halfOfLength, KeyframeAnimations.degreeVec(0.0f, bodyYRotation, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(length, KeyframeAnimations.degreeVec(0.0f, -bodyYRotation, 0.0f), Interpolations.CATMULLROM)))
                .addAnimation("body", new AnimationChannel(Targets.ROTATION,
                        new Keyframe(0.0f, KeyframeAnimations.degreeVec(bodyXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(quarterOfLength, KeyframeAnimations.degreeVec(-bodyXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(halfOfLength, KeyframeAnimations.degreeVec(bodyXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(halfAndQuarterOfLength, KeyframeAnimations.degreeVec(-bodyXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(length, KeyframeAnimations.degreeVec(bodyXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM)))
                .addAnimation("right_front_leg", limbAnimationChannel)
                .addAnimation("left_front_leg", limbAnimationChannel2)
                .addAnimation("right_hind_leg", limbAnimationChannel2)
                .addAnimation("left_hind_leg", limbAnimationChannel)
                .build();
    }

    private static AnimationDefinition initIdleAnimation() {
        final float length = 0.9f;
        final float twelfthOfLength = length / 12f;

        final float noseXRotation = 8f;

        return AnimationDefinition.Builder.withLength(length)
                .addAnimation("nose", new AnimationChannel(Targets.ROTATION,
                        new Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(twelfthOfLength, KeyframeAnimations.degreeVec(noseXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(3 * twelfthOfLength, KeyframeAnimations.degreeVec(-noseXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(5 * twelfthOfLength, KeyframeAnimations.degreeVec(noseXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(7 * twelfthOfLength, KeyframeAnimations.degreeVec(-noseXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(9 * twelfthOfLength, KeyframeAnimations.degreeVec(noseXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(11 * twelfthOfLength, KeyframeAnimations.degreeVec(-noseXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(length, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), Interpolations.CATMULLROM)))
                .build();

    }

    private static AnimationDefinition initSwimAnimation() {
        final float length = 1.6f;
        final float halfOfLength = length / 2f;

        final float earsYRotation = 40f;
        final float rootXRotation = 7f;
        final float rootZRotation = 3f;
        final float bodyXRotation = 7f;
        final float noseYRotation = 2f;
        final float limbXRotation = 30f;

        final float limbMaxPosition = 0.3f;
        final float limbMinPosition = -0.1f;

        AnimationChannel limbRotationAnimation = new AnimationChannel(Targets.ROTATION,
                new Keyframe(0.0f, KeyframeAnimations.degreeVec(limbXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(halfOfLength * 0.6f, KeyframeAnimations.degreeVec(-limbXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(halfOfLength, KeyframeAnimations.degreeVec(limbXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(halfOfLength * 1.6f, KeyframeAnimations.degreeVec(-limbXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(length, KeyframeAnimations.degreeVec(limbXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM));

        AnimationChannel limbPositionAnimation = new AnimationChannel(Targets.POSITION,
                new Keyframe(0.0f, KeyframeAnimations.posVec(limbMaxPosition, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(halfOfLength * 0.6f, KeyframeAnimations.degreeVec(limbMinPosition, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(halfOfLength, KeyframeAnimations.degreeVec(limbMaxPosition, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(halfOfLength * 1.6f, KeyframeAnimations.degreeVec(limbMinPosition, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(length, KeyframeAnimations.degreeVec(limbMaxPosition, 0.0f, 0.0f), Interpolations.CATMULLROM));

        AnimationChannel limbRotationAnimation2 = new AnimationChannel(Targets.ROTATION,
                new Keyframe(0.0f, KeyframeAnimations.degreeVec(-limbXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(halfOfLength * 0.4f, KeyframeAnimations.degreeVec(limbXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(halfOfLength, KeyframeAnimations.degreeVec(-limbXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(halfOfLength * 1.4f, KeyframeAnimations.degreeVec(limbXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(length, KeyframeAnimations.degreeVec(-limbXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM));

        AnimationChannel limbPositionAnimation2 = new AnimationChannel(Targets.POSITION,
                new Keyframe(0.0f, KeyframeAnimations.posVec(limbMinPosition, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(halfOfLength * 0.4f, KeyframeAnimations.posVec(limbMaxPosition, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(halfOfLength, KeyframeAnimations.posVec(limbMinPosition, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(halfOfLength * 1.4f, KeyframeAnimations.posVec(limbMaxPosition, 0.0f, 0.0f), Interpolations.CATMULLROM),
                new Keyframe(length, KeyframeAnimations.posVec(limbMinPosition, 0.0f, 0.0f), Interpolations.CATMULLROM));

        return AnimationDefinition.Builder.withLength(length).looping()
                .addAnimation("left_ear", new AnimationChannel(Targets.ROTATION,
                        new Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, -earsYRotation, 0.0f), Interpolations.CATMULLROM)))
                .addAnimation("right_ear", new AnimationChannel(Targets.ROTATION,
                        new Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, earsYRotation, 0.0f), Interpolations.CATMULLROM)))
                .addAnimation("root", new AnimationChannel(Targets.ROTATION,
                        new Keyframe(0.0f, KeyframeAnimations.degreeVec(rootXRotation, 0.0f, rootZRotation), Interpolations.CATMULLROM),
                        new Keyframe(halfOfLength * 0.5f, KeyframeAnimations.degreeVec(rootXRotation, 0.0f, -rootZRotation), Interpolations.CATMULLROM),
                        new Keyframe(halfOfLength, KeyframeAnimations.degreeVec(rootXRotation, 0.0f, rootZRotation), Interpolations.CATMULLROM),
                        new Keyframe(halfOfLength * 1.5f, KeyframeAnimations.degreeVec(rootXRotation, 0.0f, -rootZRotation), Interpolations.CATMULLROM),
                        new Keyframe(length, KeyframeAnimations.degreeVec(rootXRotation, 0.0f, rootZRotation), Interpolations.CATMULLROM)))
                .addAnimation("body", new AnimationChannel(Targets.ROTATION,
                        new Keyframe(0.0f, KeyframeAnimations.degreeVec(bodyXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(halfOfLength, KeyframeAnimations.degreeVec(-bodyXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(length, KeyframeAnimations.degreeVec(bodyXRotation, 0.0f, 0.0f), Interpolations.CATMULLROM)))
                .addAnimation("nose", new AnimationChannel(Targets.ROTATION,
                        new Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, noseYRotation, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(halfOfLength * 0.5f, KeyframeAnimations.degreeVec(0.0f, -noseYRotation, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(halfOfLength, KeyframeAnimations.degreeVec(0.0f, noseYRotation, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(halfOfLength * 1.5f, KeyframeAnimations.degreeVec(0.0f, -noseYRotation, 0.0f), Interpolations.CATMULLROM),
                        new Keyframe(length, KeyframeAnimations.degreeVec(0.0f, noseYRotation, 0.0f), Interpolations.CATMULLROM)))
                .addAnimation("right_front_leg", limbRotationAnimation)
                .addAnimation("right_front_leg", limbPositionAnimation)
                .addAnimation("left_front_leg", limbRotationAnimation2)
                .addAnimation("left_front_leg", limbPositionAnimation2)
                .addAnimation("left_hind_leg", limbRotationAnimation)
                .addAnimation("left_hind_leg", limbPositionAnimation)
                .addAnimation("right_hind_leg", limbRotationAnimation2)
                .addAnimation("right_hind_leg", limbPositionAnimation2)
                .build();
    }
}
