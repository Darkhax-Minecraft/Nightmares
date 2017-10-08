package net.darkhax.nightmares.nightmare;

import java.util.ArrayList;
import java.util.List;

import net.darkhax.nightmares.util.SpawnEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class NightmareBase implements INightmare {

    private ITextComponent alert = new TextComponentTranslation("nightmare.default.alert").setStyle(new Style().setItalic(true).setColor(TextFormatting.RED));
    private final List<SpawnEntry> spawns = new ArrayList<>();

    public ResourceLocation id;

    public NightmareBase (ResourceLocation id) {

        this.id = id;
    }

    public NightmareBase addSpawn (SpawnEntry spawn) {

        this.spawns.add(spawn);
        return this;
    }

    public NightmareBase setAlert (ITextComponent alert) {

        this.alert = alert;
        return this;
    }

    @Override
    public int getTickDelay (EntityPlayer player) {

        return 20;
    }

    @Override
    public void spawnMobs (EntityPlayer player, BlockPos pos) {

        for (final SpawnEntry entry : this.spawns) {

            entry.spawn(player.world, pos);
        }
    }

    @Override
    public ITextComponent alertPlayer (EntityPlayer player) {

        return this.alert;
    }
}