package net.darkhax.nightmares.entity.render.layer;

import net.darkhax.nightmares.entity.EntityShadow;
import net.darkhax.nightmares.entity.render.RenderShadow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerShadowEyes implements LayerRenderer<EntityShadow> {
    
    private static final ResourceLocation SPIDER_EYES = new ResourceLocation("nightmares", "textures/entity/shadow_eyes.png");
    private final RenderShadow spiderRenderer;

    public LayerShadowEyes (RenderShadow spiderRendererIn) {

        this.spiderRenderer = spiderRendererIn;
    }

    @Override
    public void doRenderLayer (EntityShadow entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

        this.spiderRenderer.bindTexture(SPIDER_EYES);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);

        if (entitylivingbaseIn.isInvisible()) {
            GlStateManager.depthMask(false);
        }
        else {
            GlStateManager.depthMask(true);
        }

        int i = 61680;
        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        this.spiderRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
        i = entitylivingbaseIn.getBrightnessForRender();
        j = i % 65536;
        k = i / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);
        this.spiderRenderer.setLightmap(entitylivingbaseIn);
        GlStateManager.disableBlend();
    }

    @Override
    public boolean shouldCombineTextures () {

        return false;
    }
}