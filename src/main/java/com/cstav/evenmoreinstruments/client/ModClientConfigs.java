package com.cstav.evenmoreinstruments.client;

import com.cstav.evenmoreinstruments.client.gui.instrument.pipa.PipaSoundType;
import com.cstav.evenmoreinstruments.client.gui.instrument.violin.ViolinSoundType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.EnumValue;

@OnlyIn(Dist.CLIENT)
public class ModClientConfigs {
    public static final ModConfigSpec CONFIGS;

    public static final EnumValue<ViolinSoundType> VIOLIN_SOUND_TYPE;
    public static final EnumValue<PipaSoundType> PIPA_SOUND_TYPE;

    static {
        final ModConfigSpec.Builder configBuilder = new ModConfigSpec.Builder();

        VIOLIN_SOUND_TYPE = configBuilder.defineEnum("violin_sound_type", ViolinSoundType.HALF_NOTE);
        PIPA_SOUND_TYPE = configBuilder.defineEnum("pipa_sound_type", PipaSoundType.REGULAR);

        CONFIGS = configBuilder.build();
    }
}
