package net.darkhax.nightmares;

import net.darkhax.bookshelf.lib.LoggingHelper;
import net.darkhax.bookshelf.lib.MCColor;
import net.darkhax.bookshelf.registry.RegistryHelper;
import net.darkhax.nightmares.entity.EntityHag;
import net.darkhax.nightmares.entity.EntityShadow;
import net.darkhax.nightmares.entity.render.RenderHag;
import net.darkhax.nightmares.entity.render.RenderShadow;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer.SleepResult;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "nightmares", name = "Nightmares", version = "@VERSION@", dependencies = "required-after:bookshelf@[2.2.457,);", certificateFingerprint = "@FINGERPRINT@")
public class Nightmares {

    public static final RegistryHelper helper = new RegistryHelper("nightmares").enableAutoRegistration();
    public static final LoggingHelper log = new LoggingHelper("nightmares");

    @EventHandler
    public void onPreInit (FMLPreInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(this);
        helper.registerMob(EntityHag.class, "hag", 0, MCColor.DYE_YELLOW.getRGB(), MCColor.DYE_LIME.getRGB());
        helper.registerMob(EntityShadow.class, "shadow", 1, MCColor.DYE_BLACK.getRGB(), MCColor.DYE_WHITE.getRGB());

        RenderingRegistry.registerEntityRenderingHandler(EntityHag.class, new RenderHag.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityShadow.class, new RenderShadow.Factory());
    }

    @SubscribeEvent
    public void playerSleep (PlayerSleepInBedEvent event) {

        event.setResult(SleepResult.NOT_SAFE);

        final EntityZombie zombie = new EntityZombie(event.getEntityPlayer().getEntityWorld());
        final BlockPos ps = event.getPos();
        zombie.setPosition(ps.getX() + 0.5f, ps.getY() + 0.6f, ps.getZ() + 0.5f);
        zombie.setRevengeTarget(event.getEntityPlayer());
        event.getEntityPlayer().getEntityWorld().spawnEntity(zombie);
    }
}
