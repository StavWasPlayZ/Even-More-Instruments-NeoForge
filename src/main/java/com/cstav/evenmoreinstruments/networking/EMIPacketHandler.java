package com.cstav.evenmoreinstruments.networking;

import com.cstav.evenmoreinstruments.EMIMain;
import com.cstav.evenmoreinstruments.networking.packet.c2s.DoesLooperExistPacket;
import com.cstav.evenmoreinstruments.networking.packet.c2s.LooperRecordStatePacket;
import com.cstav.evenmoreinstruments.networking.packet.s2c.LooperPlayStatePacket;
import com.cstav.evenmoreinstruments.networking.packet.s2c.LooperUnplayablePacket;
import com.cstav.evenmoreinstruments.networking.packet.s2c.OpenNoteBlockInstrumentPacket;
import com.cstav.evenmoreinstruments.networking.packet.s2c.SyncModTagPacket;
import com.cstav.genshinstrument.networking.IModPacket;
import com.cstav.genshinstrument.util.ServerUtil;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.List;

// Copy pasta
@EventBusSubscriber(modid = EMIMain.MODID)
public class EMIPacketHandler {
    @SuppressWarnings("unchecked")
    private static final List<Class<IModPacket>> ACCEPTABLE_PACKETS_C2S = List.of(new Class[] {
        DoesLooperExistPacket.class,
        LooperRecordStatePacket.class
    });
    @SuppressWarnings("unchecked")
    private static final List<Class<IModPacket>> ACCEPTABLE_PACKETS_S2C = List.of(new Class[] {
        OpenNoteBlockInstrumentPacket.class,
        LooperUnplayablePacket.class,
        LooperPlayStatePacket.class,
        SyncModTagPacket.class
    });


    private static final String PROTOCOL_VERSION = "1.1";
    private static final PayloadRegistrar payloadRegistrar = new PayloadRegistrar(PROTOCOL_VERSION);

    @SubscribeEvent
    public static void onPayloadRegistration(final RegisterPayloadHandlersEvent event) {
        ServerUtil.registerC2SPackets(ACCEPTABLE_PACKETS_C2S, payloadRegistrar);
        ServerUtil.registerS2CPackets(ACCEPTABLE_PACKETS_S2C, () -> ClientDistExecutor.PACKET_SWITCH, payloadRegistrar);
    }


    public static void sendToServer(final IModPacket packet) {
        PacketDistributor.sendToServer(packet);
    }
    public static void sendToClient(final IModPacket packet, final ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, packet);
    }

}
