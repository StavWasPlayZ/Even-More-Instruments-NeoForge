package com.cstav.evenmoreinstruments.networking.packet.c2s;

import com.cstav.evenmoreinstruments.EMIMain;
import com.cstav.evenmoreinstruments.block.blockentity.LooperBlockEntity;
import com.cstav.evenmoreinstruments.networking.EMIPacketHandler;
import com.cstav.evenmoreinstruments.networking.packet.s2c.LooperUnplayablePacket;
import com.cstav.evenmoreinstruments.networking.packet.s2c.SyncModTagPacket;
import com.cstav.evenmoreinstruments.util.LooperUtil;
import com.cstav.genshinstrument.attachment.instrumentopen.InstrumentOpenProvider;
import com.cstav.genshinstrument.networking.IModPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.Optional;

public class DoesLooperExistPacket extends IModPacket {
    public static final String MOD_ID = EMIMain.MODID;
    public static final StreamCodec<RegistryFriendlyByteBuf, DoesLooperExistPacket> CODEC = CustomPacketPayload.codec(
        DoesLooperExistPacket::write,
        DoesLooperExistPacket::new
    );

    public static final int MAX_RECORD_DIST = 8;

    final Optional<InteractionHand> hand;

    public DoesLooperExistPacket(final InteractionHand hand) {
        this.hand = Optional.of(hand);
    }
    /**
     * Counts this update request as a request for a block instrument
     */
    public DoesLooperExistPacket() {
        this.hand = Optional.empty();
    }
    public DoesLooperExistPacket(FriendlyByteBuf buf) {
        hand = buf.readOptional((fbb) -> fbb.readEnum(InteractionHand.class));
    }

    @Override
    public void write(final RegistryFriendlyByteBuf buf) {
        buf.writeOptional(hand, FriendlyByteBuf::writeEnum);
    }

    @Override
    public void handleServer(final IPayloadContext context) {
        final ServerPlayer player = (ServerPlayer) context.player();
        final Level level = player.level();

        LooperBlockEntity looperBE;

        if (hand.isPresent()) {
            final ItemStack instrumentItem = player.getItemInHand(hand.get());
            looperBE = LooperUtil.getFromItemInstrument(level, instrumentItem);

            if (looperBE != null)
                // For items, also check if we are too far away
                if (!looperBE.getBlockPos().closerToCenterThan(player.position(), MAX_RECORD_DIST)) {
                    looperBE = null;
                    LooperUtil.remLooperTag(instrumentItem);
                }
        } else {
            final BlockPos instrumentBlockPos = InstrumentOpenProvider.getBlockPos(player);
            final BlockEntity instrumentBlockEntity = level.getBlockEntity(instrumentBlockPos);

            looperBE = LooperUtil.getFromBlockInstrument(level, instrumentBlockEntity);

            // Manually update the tag removal for the client
            if (looperBE == null) {
                EMIPacketHandler.sendToClient(
                    new SyncModTagPacket(EMIMain.modTag(instrumentBlockEntity), instrumentBlockPos), player
                );
            }
        }

        if (looperBE == null)
            EMIPacketHandler.sendToClient(LooperUnplayablePacket.INSTANCE, player);
    }
    
}
