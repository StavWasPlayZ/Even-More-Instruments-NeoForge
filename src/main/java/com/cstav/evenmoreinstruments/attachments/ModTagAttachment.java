package com.cstav.evenmoreinstruments.attachments;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

/**
 * Wrapper for the mod tag compound.
 */
public class ModTagAttachment implements INBTSerializable<CompoundTag> {
    private CompoundTag tag = new CompoundTag();

    public CompoundTag getTag() {
        return tag;
    }
    public void setTag(CompoundTag tag) {
        this.tag = tag;
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(@NotNull Provider provider) {
        return getTag();
    }

    @Override
    public void deserializeNBT(@NotNull Provider provider, @NotNull CompoundTag nbt) {
        setTag(nbt);
    }
}
