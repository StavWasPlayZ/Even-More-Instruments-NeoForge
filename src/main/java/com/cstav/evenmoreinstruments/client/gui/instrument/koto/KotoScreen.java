package com.cstav.evenmoreinstruments.client.gui.instrument.koto;

import com.cstav.evenmoreinstruments.EMIMain;
import com.cstav.evenmoreinstruments.client.gui.instrument.pipa.PipaScreen;
import com.cstav.evenmoreinstruments.sound.ModSounds;
import com.cstav.genshinstrument.client.gui.screen.instrument.partial.InstrumentThemeLoader;
import com.cstav.genshinstrument.client.gui.screen.instrument.partial.grid.GridInstrumentScreen;
import com.cstav.genshinstrument.sound.NoteSound;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KotoScreen extends GridInstrumentScreen {
    public static final ResourceLocation INSTRUMENT_ID = EMIMain.loc("koto");

    @Override
    public NoteSound[] getInitSounds() {
        return ModSounds.KOTO;
    }

    @Override
    public ResourceLocation getInstrumentId() {
        return INSTRUMENT_ID;
    }
    @Override
    public boolean isGenshinInstrument() {
        return false;
    }

    public static final InstrumentThemeLoader THEME_LOADER = InstrumentThemeLoader.fromOther(
        PipaScreen.THEME_LOADER,
        INSTRUMENT_ID
    );
    @Override
    public InstrumentThemeLoader getThemeLoader() {
        return THEME_LOADER;
    }
    
}