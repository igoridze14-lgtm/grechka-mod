package net.grechka.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class GreczkaMod implements ModInitializer {
    
    public static final String MOD_ID = "grechka-mod";
    
    public static final EntityType<GrechkaEntity> GRECHKA_ENTITY_TYPE = Registry.register(
        Registries.ENTITY_TYPE,
        new Identifier(MOD_ID, "grechka"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GrechkaEntity::new)
            .dimensions(EntityDimensions.fixed(0.6f, 0.8f))
            .build()
    );

    @Override
    public void onInitialize() {
        // Мод инициализирован!
    }
}
