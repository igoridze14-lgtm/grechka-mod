package net.grechka.mod.entity;

import net.minecraft.entity.passive.DogEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class CompanionDogEntity extends DogEntity {

    private static final int REGENERATION_RATE = 100; // in ticks
    private int regenerationTimer;

    public CompanionDogEntity(World world) {
        super(world);
        this.regenerationTimer = 0;
    }

    @Override
    public void tick() {
        super.tick();
        handleRegeneration();
    }

    private void handleRegeneration() {
        regenerationTimer++;
        if (regenerationTimer >= REGENERATION_RATE) {
            if (this.getHealth() < this.getMaxHealth()) {
                this.heal(1.0F); // Regenerate health
            }
            regenerationTimer = 0;
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        // Add additional NBT data support here if needed
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        // Read additional NBT data support here if needed
    }

    @Override
    public boolean isPersistent() {
        return true; // Ensure this entity never despawns
    }
}