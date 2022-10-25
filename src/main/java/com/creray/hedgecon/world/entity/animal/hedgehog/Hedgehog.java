package com.creray.hedgecon.world.entity.animal.hedgehog;

import com.creray.hedgecon.world.level.storage.loot.ModLootTables;
import com.creray.hedgecon.tags.ItemTags;
import com.mojang.serialization.Dynamic;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Hedgehog extends PathfinderMob {
    private static final float MINIMAL_ITEM_FALL_DISTANCE_TO_LOOT = 0.9f;

    private boolean canPickUpFallingLoot;

    public static AttributeSupplier.Builder createHedgehogAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3f);
    }

    public Hedgehog(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);
        setCanPickUpFallingLoot(true);
    }

    /*
     * Compound Tags
     */
    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        setCanPickUpFallingLoot(compoundTag.getBoolean("CanPickUpFallingLoot"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putBoolean("CanPickUpFallingLoot", canPickUpFallingLoot);
    }

    /*
     * Spawns with equipment
     */
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {
        populateDefaultEquipmentSlots(random, difficultyInstance);
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance difficultyInstance) {
        LootTable lootTable = level.getServer().getLootTables().get(ModLootTables.HEDGEHOG_SPAWNS_WITH_ENTITY);
        LootContext context = new LootContext.Builder((ServerLevel) level)
                .withRandom(randomSource)
                .create(LootContextParamSets.EMPTY);
        lootTable.getRandomItems(context,  itemStack -> setItemSlot(EquipmentSlot.MAINHAND, itemStack));
    }

    /*
     * Player mob interaction
     */
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack playerItemStack = player.getItemInHand(hand);
        if (!playerItemStack.isEmpty()) {
            return InteractionResult.PASS;
        }

        ItemStack itemStack = getItemBySlot(EquipmentSlot.MAINHAND);
        if (itemStack.isEmpty()) {
            return InteractionResult.PASS;
        }

        setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        player.addItem(itemStack);

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    /*
     * Looting falling items
     */
    @Override
    public void aiStep() {
        super.aiStep();
        level.getProfiler().push("lootingFallingItems");
        processLootingFallingItems();
        level.getProfiler().pop();
    }

    protected void processLootingFallingItems() {
        if (level.isClientSide || !canPickUpFallingLoot() || !isAlive() || dead || !level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
            return;
        }
        AABB itemPickUpBox = getBoundingBox().move(0, 0.4, 0).inflate(0.4,  0, 0.4);
        List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, itemPickUpBox);

        for (ItemEntity item : items) {
            if (!item.isRemoved() && !item.getItem().isEmpty() && canPickUpFallingLoot() && canGetCaughtOnNeedles(item)) {
                pickUpItem(item);
            }
        }
    }

    @Override
    public boolean equipItemIfPossible(ItemStack item) {
        ItemStack itemStack = getItemBySlot(EquipmentSlot.MAINHAND);
        if (itemStack.isEmpty()) {
            setItemSlotAndDropWhenKilled(EquipmentSlot.MAINHAND, item);
            return true;
        }
        return false;
    }

    protected void setCanPickUpFallingLoot(boolean canPickUpFallingLoot) {
        this.canPickUpFallingLoot = canPickUpFallingLoot;
    }

    protected boolean canPickUpFallingLoot() {
        return canPickUpFallingLoot && getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
    }
    protected boolean canGetCaughtOnNeedles(ItemEntity item) {
        return item.fallDistance > MINIMAL_ITEM_FALL_DISTANCE_TO_LOOT && item.getItem().is(ItemTags.CAN_GET_CAUGHT_ON_HEDGEHOG);
    }

    /*
     * Mob AI
     */
    @Override
    protected Brain.Provider<Hedgehog> brainProvider() {
        return HedgehogAi.provider();
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return HedgehogAi.makeBrain(brainProvider().makeBrain(dynamic));
    }

    @Override
    protected void customServerAiStep() {
        level.getProfiler().push("hedgehogBrain");
        @SuppressWarnings("unchecked")
        Brain<Hedgehog> brain = (Brain<Hedgehog>) getBrain();
        brain.tick((ServerLevel) level, this);
        level.getProfiler().pop();
        level.getProfiler().push("hedgehogActivityUpdate");
        HedgehogAi.updateActivities(this);
        level.getProfiler().pop();
    }

    /*
     * Animations
     */
    @Override
    public void tick() {
        super.tick();
        if (level.isClientSide) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        //TODO: animations
    }

    public boolean isRolledUp() {
        // I used sleeping pose, because Pose is enum and cannot be modified
        return getPose() == Pose.SLEEPING;
    }

    public void rollUp() {
        setPose(Pose.SLEEPING);
    }

    public void unroll() {
        setPose(Pose.STANDING);
    }
}
