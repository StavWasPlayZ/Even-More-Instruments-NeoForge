package com.cstav.evenmoreinstruments.criteria;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class RecordInjectedTrigger extends SimpleCriterionTrigger<RecordInjectedTrigger.TriggerInstance> {
    public void trigger(final ServerPlayer player, final ItemStack instrument) {
        super.trigger(player, (triggerInstance) ->
            triggerInstance.matches(instrument)
        );
    }

    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }


    public record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> item) implements SimpleInstance {
        public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create((triggerInstance) ->
            triggerInstance.group(
                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player")
                    .forGetter(TriggerInstance::player),
                ItemPredicate.CODEC.optionalFieldOf("record")
                    .forGetter(TriggerInstance::item)
            ).apply(triggerInstance, TriggerInstance::new)
        );

        public boolean matches(final ItemStack record) {
            return item.isEmpty() || item.get().test(record);
        }
    }

}