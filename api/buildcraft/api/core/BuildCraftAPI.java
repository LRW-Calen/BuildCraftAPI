/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public License 1.0, or MMPL. Please check the contents
 * of the license located in http://www.mod-buildcraft.com/MMPL-1.0.txt */
package buildcraft.api.core;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;

import java.util.HashMap;
import java.util.Set;

public final class BuildCraftAPI {
    public static IFakePlayerProvider fakePlayerProvider;

    public static final Set<Block> softBlocks = Sets.newHashSet();
    public static final HashMap<String, IWorldProperty> worldProperties = Maps.newHashMap();

    /** Deactivate constructor */
    private BuildCraftAPI() {
    }

    public static String getVersion() {
        ModContainer container = ModList.get().getModContainerById("buildcraftlib").get();
        if (container != null) {
            return container.getModInfo().getVersion().getQualifier();
        }
        return "UNKNOWN VERSION";
    }

    public static IWorldProperty getWorldProperty(String name) {
        return worldProperties.get(name);
    }

    public static void registerWorldProperty(String name, IWorldProperty property) {
        if (worldProperties.containsKey(name)) {
            BCLog.logger.warn("The WorldProperty key '" + name + "' is being overridden with " + property.getClass().getSimpleName() + "!");
        }
        worldProperties.put(name, property);
    }

    public static boolean isSoftBlock(Level world, BlockPos pos) {
        return worldProperties.get("soft").get(world, pos);
    }

    public static ResourceLocation nameToResourceLocation(String name) {
        if (name.indexOf(':') > 0) return new ResourceLocation(name);
//        ModContainer modContainer = Loader.instance().activeModContainer();
        ModContainer modContainer = ModLoadingContext.get().getActiveContainer();
        if (modContainer == null) {
            throw new IllegalStateException("Illegal recipe name " + name + ". Provide domain id to register it correctly.");
        }
        return new ResourceLocation(modContainer.getModId(), name);
    }
}
