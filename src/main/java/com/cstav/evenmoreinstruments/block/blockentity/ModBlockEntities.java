package com.cstav.evenmoreinstruments.block.blockentity;

import com.cstav.evenmoreinstruments.EMIMain;
import com.cstav.evenmoreinstruments.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.Builder;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
        DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, EMIMain.MODID);

    public static void register(final IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LooperBlockEntity>> LOOPER =
        BLOCK_ENTITIES.register("looper", () ->
            Builder.of(
                LooperBlockEntity::new, ModBlocks.LOOPER.get()
            ).build(null)
        );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModInstrumentBlockEntity>> INSTRUMENT =
        BLOCK_ENTITIES.register(EMIMain.MODID+"_instrument", () ->
            BlockEntityType.Builder.of(
                ModInstrumentBlockEntity::new,
                ModBlocks.KEYBOARD.get(),
                ModBlocks.KEYBOARD_STAND.get(),
                ModBlocks.KOTO.get()
            ).build(null)
        );
    
}