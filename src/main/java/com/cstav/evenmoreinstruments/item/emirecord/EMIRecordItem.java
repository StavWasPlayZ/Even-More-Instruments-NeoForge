package com.cstav.evenmoreinstruments.item.emirecord;

import com.cstav.evenmoreinstruments.block.blockentity.LooperBlockEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class EMIRecordItem extends Item {
    public static final String
        CHANNEL_TAG = "Channel",

        INSTRUMENT_ID_TAG = "InstrumentId",
        NOTES_TAG = "Notes",
        NOTE_TYPE = "NoteType",

        SOUND_INDEX_TAG = "SoundIndex",
        SOUND_TYPE_TAG = "SoundType",
        PITCH_TAG = "Pitch",
        VOLUME_TAG = "Volume",
        TIMESTAMP_TAG = "Timestamp",
        REPEAT_TICK_TAG = "RepeatTick",

        // Held-exclusive
        HELD_PHASE = "HeldPhase",

        WRITABLE_TAG = "Writable"
    ;


    public EMIRecordItem(final Properties properties) {
        super(properties);
    }

    public abstract void onInsert(final ItemStack stack, final LooperBlockEntity lbe);
}
