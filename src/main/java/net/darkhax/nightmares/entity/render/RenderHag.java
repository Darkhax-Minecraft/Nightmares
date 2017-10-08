package net.darkhax.nightmares.entity.render;

import net.darkhax.bookshelf.client.model.ModelPlayerMob;
import net.darkhax.bookshelf.client.rendering.RenderPlayerMob;
import net.darkhax.nightmares.entity.EntityHag;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHag extends RenderPlayerMob<EntityHag> {
    
    public static final ResourceLocation TEXTURE = new ResourceLocation("nightmares", "textures/entity/hag.png");
    
    public RenderHag (RenderManager renderManager) {
        
        super(renderManager, new ModelPlayerMob(0.5f, true));
    }
    
    @Override
    protected ResourceLocation getEntityTexture (EntityHag entity) {
        
        return TEXTURE;
    }
    
    public static class Factory implements IRenderFactory<EntityHag> {
        
        @Override
        public Render<? super EntityHag> createRenderFor (RenderManager manager) {
            
            return new RenderHag(manager);
        }
    }
    
    @Override
    public void setOverlayVisibility (EntityHag entity, ModelPlayerMob model) {
        
        model.bipedHeadwear.showModel = true;
        model.bodyOverlay.showModel = false;
        model.leftLegOverlay.showModel = false;
        model.rightLegLverlay.showModel = false;
        model.leftArmOverlay.showModel = false;
        model.rightArmOverlay.showModel = false;
    }
}