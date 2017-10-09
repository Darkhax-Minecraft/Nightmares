package net.darkhax.nightmares.entity;

import javax.annotation.Nullable;

import net.darkhax.nightmares.Nightmares;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityPhantasmicSpider extends EntitySpider {

    public EntityPhantasmicSpider (World world) {

        super(world);
        this.setSize(0.7F, 0.5F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender () {

        return 15728880;
    }

    @Override
    public float getBrightness () {

        return 1.0F;
    }

    @Override
    protected void applyEntityAttributes () {

        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
    }

    @Override
    public IEntityLivingData onInitialSpawn (DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {

        // Just overrides default spider
        return livingdata;
    }

    @Override
    public float getEyeHeight () {

        return 0.45F;
    }
    
    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        
        return Nightmares.NIGHTMARE;
    }
}