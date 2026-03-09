package net.grechka.mod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.DogEntity;
import net.minecraft.world.World;
import net.fabricmc.fabric.api.entity.event.v1.EntityEnergizer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.entity.event.v1.EntityEnergizer;

public class GrechkaMod implements ModInitializer {
    @Override
    public void onInitialize() {
        // Register the immortal companion dog
        EntityType<DogEntity> immortalDogType = registerImmortalDog();
        // Additional initialization code
    }

    private EntityType<DogEntity> registerImmortalDog() {
        return EntityType.Builder.create(DogEntity::new, EntityType.CREATURE)
                .size(0.6f, 0.85f)
                .build(new Identifier("grechka-mod", "immortal_dog").toString());
    }
}