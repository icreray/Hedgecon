package com.creray.hedgecon.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

@Environment(EnvType.CLIENT)
public class HedgehogModelLayer {

    public static LayerDefinition create() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition root = modelData.getRoot()
                .addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0f, 19.5f, 0f));

        // Body
        CubeListBuilder bodyPart = CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-2.5f, -2.5f, -4f, 5f, 5f, 8f);
        PartDefinition body = root.addOrReplaceChild("body", bodyPart, PartPose.ZERO);

        // Nose
        CubeListBuilder nosePart = CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-1.5f, -0.5f, -1f, 3f, 1f, 1f)
                .texOffs(0,2)
                .addBox(-0.5f, 0.5f, -1f, 1f, 1f, 0f);
        body.addOrReplaceChild("nose", nosePart, PartPose.offset(0, 2f, -4f));

        // Ears
        CubeListBuilder rightEarPart = CubeListBuilder.create()
                .texOffs(6, 6)
                .addBox(-1f, 0f, 0f, 1f, 2f, 0f);
        body.addOrReplaceChild("right_ear", rightEarPart, PartPose.offset(-2.5f, -2.5f, -3f));

        CubeListBuilder leftEarPart = CubeListBuilder.create()
                .texOffs(6, 6)
                .addBox(1f, 0f, 0f, 1f, 2f, 0f);
        body.addOrReplaceChild("left_ear", leftEarPart, PartPose.offset(1.5f, -2.5f, -3f));

        // Legs
        CubeListBuilder legPart = CubeListBuilder.create()
                .texOffs(0, 3)
                .addBox(0f, 1f, 0f, 1f, 2f, 0f);

        root.addOrReplaceChild("right_hind_leg", legPart, PartPose.offset(-2.5f, 1.5f, 3f));
        root.addOrReplaceChild("left_hind_leg", legPart, PartPose.offset(1.5f, 1.5f, 3f));
        root.addOrReplaceChild("right_front_leg", legPart, PartPose.offset(-2.5f, 1.5f, -3f));
        root.addOrReplaceChild("left_front_leg", legPart, PartPose.offset(1.5f, 1.5f, -3f));

        return LayerDefinition.create(modelData, 32, 32);
    }
}
