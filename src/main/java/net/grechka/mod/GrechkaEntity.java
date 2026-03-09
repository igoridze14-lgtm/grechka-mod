package net.grechka.mod;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class GrechkaEntity extends AnimalEntity {
    
    private static final String OWNER_UUID_KEY = "OwnerUUID";
    private java.util.UUID ownerUuid = null;

    public GrechkaEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0f);
    }

    @Override
    protected void initGoals() {
        // Приоритет целей (выше = важнее)
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new SitGoal(this));
        this.goalSelector.add(3, new AttackGoal(this, 1.0D, true));
        this.goalSelector.add(4, new FollowOwnerGoal(this, 1.0D, 10.0f, 2.0f, false));
        this.goalSelector.add(5, new WanderAroundGoal(this, 0.8D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(7, new LookAroundGoal(this));

        // Целевые цели
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, net.minecraft.entity.hostile.HostileEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createGrechkaAttributes() {
        return AnimalEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1000.0D) // Бессмертная!
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0D)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0D);
    }

    @Override
    public void tick() {
        super.tick();
        // Убедимся, что здоровье всегда максимальное (бессмертная собака)
        if (!this.getWorld().isClient) {
            this.setHealth(this.getMaxHealth());
        }
    }

    @Override
    public boolean interactMob(PlayerEntity player, net.minecraft.util.Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        
        // Приручение костью
        if (itemStack.isOf(Items.BONE)) {
            if (!this.getWorld().isClient) {
                itemStack.decrement(1);
                this.setOwner(player);
                this.getWorld().sendEntityStatus(this, (byte) 7); // Частицы приручения
            }
            return true;
        }
        
        return super.interactMob(player, hand);
    }

    public void setOwner(PlayerEntity player) {
        this.ownerUuid = player.getUuid();
        this.setPersistent();
    }

    public boolean hasOwner() {
        return this.ownerUuid != null;
    }

    public java.util.UUID getOwnerUuid() {
        return this.ownerUuid;
    }

    public PlayerEntity getOwner() {
        if (this.ownerUuid == null) return null;
        return this.getWorld().getPlayerByUuid(this.ownerUuid);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.containsUuid(OWNER_UUID_KEY)) {
            this.ownerUuid = nbt.getUuid(OWNER_UUID_KEY);
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (this.ownerUuid != null) {
            nbt.putUuid(OWNER_UUID_KEY, this.ownerUuid);
        }
    }

    @Override
    public GrechkaEntity createChild(net.minecraft.entity.passive.AnimalEntity other) {
        return new GrechkaEntity(GreczkaMod.GRECHKA_ENTITY_TYPE, this.getWorld());
    }
}
