package com.cstav.evenmoreinstruments.attachments;

import com.cstav.evenmoreinstruments.EMIMain;
import com.cstav.evenmoreinstruments.attachments.recording.RecordingData;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModAttachments {

    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
        DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, EMIMain.MODID);

    public static void register(final IEventBus bus) {
        ATTACHMENT_TYPES.register(bus);
    }

    // For blocks
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<ModTagAttachment>> MOD_TAG = ATTACHMENT_TYPES.register(
        "mod_tag", () -> AttachmentType.serializable(ModTagAttachment::new).build()
    );

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<RecordingData>> RECORDING_TAG = ATTACHMENT_TYPES.register(
        "recording", () -> AttachmentType.serializable(RecordingData::new).build()
    );

}
