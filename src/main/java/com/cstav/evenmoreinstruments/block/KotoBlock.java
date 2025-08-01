package com.cstav.evenmoreinstruments.block;

import com.cstav.evenmoreinstruments.EMIMain;
import com.cstav.evenmoreinstruments.block.partial.DoubleInstrumentBlock;
import com.cstav.genshinstrument.networking.packet.instrument.util.InstrumentPacketUtil;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class KotoBlock extends DoubleInstrumentBlock {
    public static final MapCodec<KeyboardBlock> CODEC = simpleCodec(KeyboardBlock::new);

    public static final VoxelShape
        SHAPE_LEFT_SOUTH = Block.box(0.0D, 0.0D, 4.0D, 15.65D, 4.4D, 12.8D),
        SHAPE_RIGHT_SOUTH = Block.box(0.3D, 0.0D, 4D, 16.0D, 4.4D, 12.8D),

        SHAPE_LEFT_EAST = Block.box(3.5D, 0.0D, 0.0D, 12D, 4.4D, 15.65D),
        SHAPE_RIGHT_EAST = Block.box(3.5D, 0.0D, 0.3D, 12D, 4.4D, 16D)
    ;

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }


    public KotoBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected void onInstrumentOpen(ServerPlayer player) {
        InstrumentPacketUtil.sendOpenPacket(player, EMIMain.loc("koto"));
    }


    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        final Direction facing = pState.getValue(FACING);
        final boolean southOrNorth = (facing == Direction.SOUTH) || (facing == Direction.NORTH);

        return (pState.getValue(PART) == BlockPart.LEFT)
            ? (southOrNorth ? SHAPE_LEFT_SOUTH : SHAPE_LEFT_EAST)
            : (southOrNorth ? SHAPE_RIGHT_SOUTH : SHAPE_RIGHT_EAST);
    }

}