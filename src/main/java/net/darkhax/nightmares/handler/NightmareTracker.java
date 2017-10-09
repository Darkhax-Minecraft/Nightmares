package net.darkhax.nightmares.handler;

import net.darkhax.nightmares.Nightmares;
import net.darkhax.nightmares.nightmare.INightmare;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Instances of this class are used to track players who are having afflicted with a nightmare.
 * When the {@link #ticksTillAttack} timer reaches 0, the nightmare will start and the tracker
 * will be removed from the event bus.
 */
public class NightmareTracker {

    /**
     * The player being tracked.
     */
    private final EntityPlayer player;

    /**
     * The amount of ticks before the nightmare starts.
     */
    private int ticksTillAttack;

    /**
     * Constructs a new tracker for an entity. This will automatically start the tracker.
     *
     * @param player The player to track.
     */
    public NightmareTracker (EntityPlayer player) {

        this.player = player;
        this.ticksTillAttack = 15;
        this.start();
    }

    @SubscribeEvent
    public void onPlayerTick (TickEvent.PlayerTickEvent event) {

        // Break the nightmare
        if (this.player == null || this.player.isDead || !this.player.isPlayerSleeping()) {
            this.stop();
        }

        // Tick the nightmare
        if (event.player == this.player) {

            this.ticksTillAttack--;

            if (this.ticksTillAttack < 1) {

                this.player.wakeUpPlayer(true, true, false);
                this.stop();

                final INightmare nightmare = Nightmares.NIGHTMARE_REGISTRY.getRandomEntry().getEntry();

                // Chat message, because action bar messages don't support colours and
                // formatting.
                this.player.sendMessage(nightmare.alertPlayer(this.player));

                // Spawn mobs
                nightmare.spawnMobs(this.player, this.player.getPosition());
            }
        }
    }

    /**
     * Starts the nightmare tracker.
     */
    private void start () {

        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Stops the nightmare tracker.
     */
    private void stop () {

        MinecraftForge.EVENT_BUS.unregister(this);
    }
}
