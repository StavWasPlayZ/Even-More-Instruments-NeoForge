package com.cstav.evenmoreinstruments.attachments.recording;

import com.cstav.evenmoreinstruments.attachments.ModAttachments;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;

import java.util.function.Consumer;

public class RecordingCapabilityProvider {

    public static boolean isRecording(final Player player) {
        return player.getData(ModAttachments.RECORDING_TAG).isRecording();
    }
    public static BlockPos getLooperPos(final Player player) {
        return player.getData(ModAttachments.RECORDING_TAG).getLooperPos();
    }

    public static void setRecording(final Player player, final BlockPos looperPos) {
        setData(player, (data) -> data.setRecording(looperPos));
    }
    public static void setNotRecording(final Player player) {
        setData(player, RecordingData::setNotRecording);
    }


    private static void setData(final Player player, final Consumer<RecordingData> dataConsumer) {
        final RecordingData data = player.getData(ModAttachments.RECORDING_TAG);
        dataConsumer.accept(data);
        player.setData(ModAttachments.RECORDING_TAG, data);
    }
}
