package com.cstav.evenmoreinstruments.server.command;

import com.cstav.evenmoreinstruments.EMIMain;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = EMIMain.MODID)
public class ModCommands {

    @SubscribeEvent
    public static void onCommandsRegister(final RegisterCommandsEvent event) {
        EMIRecordCommand.register(event.getDispatcher());
    }

}
