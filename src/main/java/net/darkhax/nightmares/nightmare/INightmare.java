package net.darkhax.nightmares.nightmare;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

public interface INightmare {

    /**
     * Gets the amount of ticks to delay from when the player goes to sleep.
     *
     * @param player The player being affected.
     * @return The amount of ticks to delay.
     */
    int getTickDelay (EntityPlayer player);

    /**
     * Spawns all of the mobs for the nightmare.
     *
     * @param player The player to spawn them at.
     * @param pos The position of the bed.
     */
    void spawnMobs (EntityPlayer player, BlockPos pos);

    /**
     * Gets a chat message to alert the player with.
     *
     * @param player The player that will get the message.
     * @return The message to send the player.
     */
    ITextComponent alertPlayer (EntityPlayer player);
}