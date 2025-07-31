package com.cstav.evenmoreinstruments.server;

import com.cstav.evenmoreinstruments.EMIMain;
import com.cstav.evenmoreinstruments.util.LooperRecordStateUtil;
import com.cstav.genshinstrument.event.InstrumentOpenStateChangedEvent;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = EMIMain.MODID)
public class ServerEvents {

    @SubscribeEvent
    public static void onInstrumentClosedStateClosed(final InstrumentOpenStateChangedEvent event) {
        if (event.player.level().isClientSide)
            return;

        if (!event.isOpen) {
            LooperRecordStateUtil.handle((ServerPlayer) event.player, event.hand, false);
        }
    }

}
