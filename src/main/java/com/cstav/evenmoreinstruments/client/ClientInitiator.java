package com.cstav.evenmoreinstruments.client;

import com.cstav.evenmoreinstruments.EMIMain;
import com.cstav.evenmoreinstruments.client.gui.instrument.guitar.GuitarScreen;
import com.cstav.evenmoreinstruments.client.gui.instrument.keyboard.KeyboardScreen;
import com.cstav.evenmoreinstruments.client.gui.instrument.koto.KotoScreen;
import com.cstav.evenmoreinstruments.client.gui.instrument.noteblockinstrument.NoteBlockInstrumentScreen;
import com.cstav.evenmoreinstruments.client.gui.instrument.pipa.PipaScreen;
import com.cstav.evenmoreinstruments.client.gui.instrument.saxophone.SaxophoneScreen;
import com.cstav.evenmoreinstruments.client.gui.instrument.shamisen.ShamisenScreen;
import com.cstav.evenmoreinstruments.client.gui.instrument.trombone.TromboneScreen;
import com.cstav.evenmoreinstruments.client.gui.instrument.violin.ViolinScreen;
import com.cstav.genshinstrument.client.gui.screen.instrument.InstrumentScreenRegistry;
import com.cstav.genshinstrument.client.gui.screen.instrument.partial.InstrumentScreen;
import com.cstav.genshinstrument.util.CommonUtil;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig.Type;

import java.util.Map;
import java.util.function.Supplier;

@Mod(value = EMIMain.MODID, dist = Dist.CLIENT)
public class ClientInitiator {

    private static final Class<?>[] LOAD_ME = new Class[] {
        // Load this ourselves because it's not included
        // in out instruments map - hence the theme loader of the
        // note block is not loaded.
        NoteBlockInstrumentScreen.class
    };

    private static final Map<ResourceLocation, Supplier<? extends InstrumentScreen>> INSTRUMENTS = Map.of(
        KeyboardScreen.INSTRUMENT_ID, KeyboardScreen::new,
        ViolinScreen.INSTRUMENT_ID, ViolinScreen::new,
        TromboneScreen.INSTRUMENT_ID, TromboneScreen::new,
        GuitarScreen.INSTRUMENT_ID, GuitarScreen::new,
        PipaScreen.INSTRUMENT_ID, PipaScreen::new,
        ShamisenScreen.INSTRUMENT_ID, ShamisenScreen::new,
        KotoScreen.INSTRUMENT_ID, KotoScreen::new,
        SaxophoneScreen.INSTRUMENT_ID, SaxophoneScreen::new
    );

    public ClientInitiator(final ModContainer container) {
        CommonUtil.loadClasses(LOAD_ME);
        InstrumentScreenRegistry.register(INSTRUMENTS);

        container.registerConfig(Type.CLIENT, ModClientConfigs.CONFIGS);
    }

}
