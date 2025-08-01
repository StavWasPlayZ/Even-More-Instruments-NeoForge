package com.cstav.evenmoreinstruments.networking.packet.c2s;

import com.cstav.evenmoreinstruments.EMIMain;
import com.cstav.evenmoreinstruments.util.LooperRecordStateUtil;
import com.cstav.genshinstrument.networking.IModPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.Optional;

public class LooperRecordStatePacket extends IModPacket {
    public static final String MOD_ID = EMIMain.MODID;
    public static final StreamCodec<RegistryFriendlyByteBuf, LooperRecordStatePacket> CODEC = CustomPacketPayload.codec(
        LooperRecordStatePacket::write,
        LooperRecordStatePacket::new
    );

    private final Optional<InteractionHand> usedHand;
    private final boolean recording;

    public LooperRecordStatePacket(boolean recording, InteractionHand usedHand) {
        this.recording = recording;
        this.usedHand = Optional.ofNullable(usedHand);
    }

    public LooperRecordStatePacket(final FriendlyByteBuf buf) {
        recording = buf.readBoolean();
        usedHand = buf.readOptional((fbb) -> fbb.readEnum(InteractionHand.class));
    }

    @Override
    public void write(final RegistryFriendlyByteBuf buf) {
        buf.writeBoolean(recording);
        buf.writeOptional(usedHand, FriendlyByteBuf::writeEnum);
    }

    @Override
    public void handleServer(final IPayloadContext context) {
        LooperRecordStateUtil.handle((ServerPlayer) context.player(), usedHand, recording);
    }

}
