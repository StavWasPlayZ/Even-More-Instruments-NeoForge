package com.cstav.evenmoreinstruments.item.crafting;

import com.cstav.evenmoreinstruments.EMIMain;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPES =
        DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, EMIMain.MODID);
    public static void register(final IEventBus bus) {
        RECIPES.register(bus);
    }

    public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<RecordCloningRecipe>>
        RECORD_CLONING = RECIPES.register(
            "crafting_special_recordcloning",
            () -> new SimpleCraftingRecipeSerializer<>(RecordCloningRecipe::new)
        )
    ;

}
