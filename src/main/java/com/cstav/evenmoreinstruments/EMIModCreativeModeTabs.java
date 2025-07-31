package com.cstav.evenmoreinstruments;

import com.cstav.evenmoreinstruments.item.ModItems;
import com.cstav.genshinstrument.GICreativeModeTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EMIModCreativeModeTabs {
    
    public static final DeferredRegister<CreativeModeTab> TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EMIMain.MODID);

    public static void register(final IEventBus bus) {
        TABS.register(bus);
    }

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab>
        MUSIC_PRODUCTION_TAB = TABS.register("music_production_tab",
            () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.evenmoreinstruments.music_production_tab"))
                .icon(() -> new ItemStack(ModItems.LOOPER.get()))
                .withTabsBefore(GICreativeModeTabs.INSTRUMENTS_TAB.getKey())
            .build()
        )
    ;

}
