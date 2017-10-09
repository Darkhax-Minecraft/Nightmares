package net.darkhax.nightmares.util;

import java.util.ArrayList;
import java.util.List;

import net.darkhax.bookshelf.util.MathsUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpawnEntry {

    private int min;
    private int max;
    private ResourceLocation entityId;

    public SpawnEntry (int min, int max, ResourceLocation entityId) {

        if (min > max) {

            throw new IllegalArgumentException("The minimum must be larger than the maximum!");
        }

        this.min = min;
        this.max = max;
        this.entityId = entityId;
    }

    public SpawnEntry (int min, int max, String entityId) {

        this(min, max, new ResourceLocation(entityId));
    }

    public List<Entity> spawn (World world, BlockPos pos) {

        final List<Entity> entities = new ArrayList<>();

        for (int i = 0; i < MathsUtils.nextIntInclusive(this.min, this.max); i++) {

            final Entity entity = EntityList.createEntityByIDFromName(this.entityId, world);
            entity.setPositionAndUpdate(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f);
            world.spawnEntity(entity);

            entities.add(entity);
        }

        return entities;
    }

    public int getMin () {

        return this.min;
    }

    public void setMin (int min) {

        this.min = min;
    }

    public int getMax () {

        return this.max;
    }

    public void setMax (int max) {

        this.max = max;
    }

    public ResourceLocation getEntityId () {

        return this.entityId;
    }

    public void setEntityId (ResourceLocation entityId) {

        this.entityId = entityId;
    }
}