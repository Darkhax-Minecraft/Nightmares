package net.darkhax.nightmares.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class DamageSourceNightmare extends EntityDamageSource {
    
    public DamageSourceNightmare (Entity damageSourceEntityIn) {
        
        super("nightmare", damageSourceEntityIn);
        this.setDamageBypassesArmor();
        this.setDamageAllowedInCreativeMode();
    }
    
    @Override
    public ITextComponent getDeathMessage (EntityLivingBase target) {
        
        return new TextComponentTranslation("death.nightmare.attack", target.getDisplayName().getFormattedText());
    }
}