package com.cstav.evenmoreinstruments.networking;

import com.cstav.evenmoreinstruments.attachments.ModAttachments;
import com.cstav.evenmoreinstruments.attachments.ModTagAttachment;
import com.cstav.evenmoreinstruments.client.gui.instrument.LooperOverlayInjector;
import com.cstav.evenmoreinstruments.client.gui.instrument.noteblockinstrument.NoteBlockInstrumentScreen;
import com.cstav.evenmoreinstruments.networking.packet.s2c.LooperPlayStatePacket;
import com.cstav.evenmoreinstruments.networking.packet.s2c.LooperUnplayablePacket;
import com.cstav.evenmoreinstruments.networking.packet.s2c.OpenNoteBlockInstrumentPacket;
import com.cstav.evenmoreinstruments.networking.packet.s2c.SyncModTagPacket;
import com.cstav.genshinstrument.networking.IModPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static com.cstav.genshinstrument.util.ServerUtil.switchEntry;

@OnlyIn(Dist.CLIENT)
public class ClientDistExecutor {

    public static final Map<String, BiConsumer<? extends IModPacket, IPayloadContext>> PACKET_SWITCH = Map.ofEntries(
        switchEntry(ClientDistExecutor::handle, LooperPlayStatePacket.class),
        switchEntry(ClientDistExecutor::handle, LooperUnplayablePacket.class),
        switchEntry(ClientDistExecutor::handle, OpenNoteBlockInstrumentPacket.class),
        switchEntry(ClientDistExecutor::handle, SyncModTagPacket.class)
    );


    private static void handle(final LooperPlayStatePacket packet, final IPayloadContext context) {
        final Level level = Minecraft.getInstance().player.level();

        final List<LivingEntity> closeEntities = level.getEntitiesOfClass(
            LivingEntity.class,
            (new AABB(packet.blockPos)).inflate(3)
        );

        // Parrots go brrrr
        for (final LivingEntity livingentity : closeEntities) {
            livingentity.setRecordPlayingNearby(packet.blockPos, packet.isPlaying);
        }
    }

    private static void handle(final LooperUnplayablePacket packet, final IPayloadContext context) {
        LooperOverlayInjector.handleLooperRemoved();
    }

    private static void handle(final OpenNoteBlockInstrumentPacket packet, final IPayloadContext context) {
        Minecraft.getInstance().setScreen(new NoteBlockInstrumentScreen(packet.instrument));
    }

    private static void handle(final SyncModTagPacket packet, final IPayloadContext context) {
        final Level level = Minecraft.getInstance().player.level();
        final BlockEntity be = level.getBlockEntity(packet.pos);

        if (be == null)
            return;

        final ModTagAttachment tag = new ModTagAttachment();
        tag.setTag(packet.modTag);

        be.setData(ModAttachments.MOD_TAG, tag);
    }

}
