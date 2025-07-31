package com.cstav.evenmoreinstruments.attachments.recording;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

public class RecordingData implements INBTSerializable<CompoundTag> {
    public static final String
        RECORDING_TAG = "Recording",
        REC_POS_TAG = "LooperPos"
    ;

    private boolean isRecording = false;
    private BlockPos looperPos = null;

    public void setRecording(final BlockPos looperPos) {
        isRecording = true;
        this.looperPos = looperPos;
    }
    public void setNotRecording() {
        isRecording = false;
        looperPos = null;
    }

    public boolean isRecording() {
        return isRecording;
    }
    public BlockPos getLooperPos() {
        return looperPos;
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(@NotNull Provider provider) {
        final CompoundTag nbt = new CompoundTag();

        nbt.putBoolean(RECORDING_TAG, isRecording);
        if (looperPos != null)
            nbt.put(REC_POS_TAG, NbtUtils.writeBlockPos(looperPos));

        return nbt;
    }

    @Override
    public void deserializeNBT(@NotNull Provider provider, @NotNull CompoundTag nbt) {
        isRecording = nbt.getBoolean(RECORDING_TAG);
        looperPos = NbtUtils.readBlockPos(nbt, REC_POS_TAG).orElse(null);
    }
}
