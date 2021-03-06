package com.jaquadro.minecraft.chameleon.resources;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

@SideOnly(Side.CLIENT)
public class IconRegistry
{
    private final Set<ResourceLocation> resources = new HashSet<ResourceLocation>();
    private final Map<ResourceLocation, TextureAtlasSprite> icons = new HashMap<ResourceLocation, TextureAtlasSprite>();

    public void registerIcon (ResourceLocation location) {
        if (location != null && !resources.contains(location))
            resources.add(location);
    }

    public TextureAtlasSprite getIcon (ResourceLocation location) {
        if (location == null)
            return null;

        TextureAtlasSprite icon = icons.get(location);
        if (icon != null)
            return icon;

        return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
    }

    public void loadIcons (TextureMap iconRegistry) {
        Iterator iterator = resources.iterator();
        while (iterator.hasNext()) {
            ResourceLocation resourcelocation = (ResourceLocation) iterator.next();
            TextureAtlasSprite textureatlassprite = iconRegistry.registerSprite(resourcelocation);
            icons.put(resourcelocation, textureatlassprite);
        }
    }
}
