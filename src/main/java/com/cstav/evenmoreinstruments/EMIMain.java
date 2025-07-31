package com.cstav.evenmoreinstruments;

import com.cstav.evenmoreinstruments.attachments.ModAttachments;
import com.cstav.evenmoreinstruments.block.ModBlocks;
import com.cstav.evenmoreinstruments.block.blockentity.ModBlockEntities;
import com.cstav.evenmoreinstruments.criteria.ModCriteria;
import com.cstav.evenmoreinstruments.gamerule.ModGameRules;
import com.cstav.evenmoreinstruments.item.ModItems;
import com.cstav.evenmoreinstruments.item.component.ModDataComponents;
import com.cstav.evenmoreinstruments.item.crafting.ModRecipeSerializers;
import com.cstav.evenmoreinstruments.sound.ModSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main mod class of the "Even More Instruments!" mod
 */
@Mod(EMIMain.MODID)
public class EMIMain
{
    public static final String MODID = "evenmoreinstruments";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static ResourceLocation loc(final String name) {
        return ResourceLocation.fromNamespaceAndPath(MODID, name);
    }


//    public static CompoundTag modTag(final ItemStack item) {
//        return item.getOrCreateTagElement(MODID);
//    }
    public static CompoundTag modTag(final BlockEntity be) {
        return be.getData(ModAttachments.MOD_TAG).getTag();
    }
    
    public EMIMain(IEventBus bus, ModContainer modContainer)
    {
        ModSounds.register(bus);

        ModGameRules.load();
        ModCriteria.register(bus);

        EMIModCreativeModeTabs.register(bus);

        ModDataComponents.register(bus);
        ModBlocks.register(bus);
        ModBlockEntities.register(bus);
        ModItems.register(bus);
        ModRecipeSerializers.register(bus);

        ModAttachments.register(bus);
    }
}
