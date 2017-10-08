
package net.darkhax.nightmares.entity.render;

import net.darkhax.bookshelf.lib.MCColor;
import net.darkhax.nightmares.entity.EntityPhantasmicSpider;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPhantasmicSpider extends RenderLiving<EntityPhantasmicSpider> {

    private static final ResourceLocation CAVE_SPIDER_TEXTURES = new ResourceLocation("minecraft", "textures/entity/spider/spider.png");

    public RenderPhantasmicSpider (RenderManager renderManagerIn) {

        super(renderManagerIn, new ModelSpider(), 1.0F);
        this.shadowSize *= 0.35F;
    }

    @Override
    public void doRender (EntityPhantasmicSpider entity, double x, double y, double z, float entityYaw, float partialTicks) {

        final MCColor color = new MCColor(entity.getUniqueID().toString());

        GlStateManager.disableLighting();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(color.getRedF(), color.getGreenF(), color.getBlueF(), 0.15F);

        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);

        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableLighting();
    }

    @Override
    protected float getDeathMaxRotation (EntityPhantasmicSpider entityLivingBaseIn) {

        return 180.0F;
    }

    @Override
    protected void preRenderCallback (EntityPhantasmicSpider entity, float partialTickTime) {

        GlStateManager.scale(0.35F, 0.35F, 0.35F);
    }

    @Override
    protected ResourceLocation getEntityTexture (EntityPhantasmicSpider entity) {

        return CAVE_SPIDER_TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityPhantasmicSpider> {

        @Override
        public Render<? super EntityPhantasmicSpider> createRenderFor (RenderManager manager) {

            return new RenderPhantasmicSpider(manager);
        }
    }
}