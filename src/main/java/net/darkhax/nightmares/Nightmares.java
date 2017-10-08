package net.darkhax.nightmares;

import net.darkhax.bookshelf.lib.LoggingHelper;
import net.darkhax.bookshelf.lib.MCColor;
import net.darkhax.bookshelf.lib.WeightedSelector;
import net.darkhax.bookshelf.registry.RegistryHelper;
import net.darkhax.nightmares.entity.EntityHag;
import net.darkhax.nightmares.entity.EntityPhantasmicSpider;
import net.darkhax.nightmares.entity.EntityShadow;
import net.darkhax.nightmares.entity.render.RenderHag;
import net.darkhax.nightmares.entity.render.RenderPhantasmicSpider;
import net.darkhax.nightmares.entity.render.RenderShadow;
import net.darkhax.nightmares.handler.NightmareTracker;
import net.darkhax.nightmares.nightmare.INightmare;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
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

    /**
     * A weighted registry of all the nightmare situations.
     */
    public static final WeightedSelector<INightmare> NIGHTMARE_REGISTRY = new WeightedSelector<>();

    /**
     * Creature type used by all nightmare mobs.
     */
    public static final EnumCreatureAttribute NIGHTMARE = EnumHelper.addCreatureAttribute("NIGHTMARE");

    @EventHandler
    public void onPreInit (FMLPreInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(this);
        helper.registerMob(EntityHag.class, "hag", 0, MCColor.DYE_LIME.getRGB(), MCColor.DYE_YELLOW.getRGB());
        helper.registerMob(EntityShadow.class, "shadow", 1, MCColor.DYE_BLACK.getRGB(), MCColor.DYE_WHITE.getRGB());
        helper.registerMob(EntityPhantasmicSpider.class, "spider", 2, MCColor.DYE_MAGENTA.getRGB(), MCColor.DYE_PURPLE.getRGB());

        // TODO probably get around to making things server safe.
        RenderingRegistry.registerEntityRenderingHandler(EntityHag.class, new RenderHag.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityShadow.class, new RenderShadow.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityPhantasmicSpider.class, new RenderPhantasmicSpider.Factory());
    }

    @SubscribeEvent
    public void playerSleep (PlayerSleepInBedEvent event) {

        if (!event.getEntityPlayer().getEntityWorld().isRemote) {
            // TODO add a probability
            new NightmareTracker(event.getEntityPlayer());
        }
    }
}
