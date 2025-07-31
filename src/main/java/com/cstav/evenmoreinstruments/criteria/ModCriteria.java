package com.cstav.evenmoreinstruments.criteria;

import com.cstav.evenmoreinstruments.EMIMain;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCriteria {
    private static final DeferredRegister<CriterionTrigger<?>> CRITERION = DeferredRegister.create(BuiltInRegistries.TRIGGER_TYPES.key(), EMIMain.MODID);
    public static void register(final IEventBus bus) {
        CRITERION.register(bus);
    }


    public static final DeferredHolder<CriterionTrigger<?>, RecordInjectedTrigger> RECORD_INJECTED_TRIGGER =
        CRITERION.register("record_injected", RecordInjectedTrigger::new);
}
