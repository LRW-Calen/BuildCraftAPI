/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * The BuildCraft API is distributed under the terms of the MIT License. Please check the contents of the license, which
 * should be located as "LICENSE.API" in the BuildCraft source code distribution. */
package buildcraft.api.robots;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;

import java.util.Collection;

public interface IRobotRegistry {

    long getNextRobotId();

    void registerRobot(EntityRobotBase robot);

    void killRobot(EntityRobotBase robot);

    void unloadRobot(EntityRobotBase robot);

    EntityRobotBase getLoadedRobot(long id);

    boolean isTaken(ResourceId resourceId);

    long robotIdTaking(ResourceId resourceId);

    EntityRobotBase robotTaking(ResourceId resourceId);

    boolean take(ResourceId resourceId, EntityRobotBase robot);

    boolean take(ResourceId resourceId, long robotId);

    void release(ResourceId resourceId);

    void releaseResources(EntityRobotBase robot);

    DockingStation getStation(BlockPos pos, Direction side);

    Collection<DockingStation> getStations();

    void registerStation(DockingStation station);

    void removeStation(DockingStation station);

    void take(DockingStation station, long robotId);

    void release(DockingStation station, long robotId);

    void writeToNBT(CompoundTag nbt);

    void readFromNBT(CompoundTag nbt);

    void registryMarkDirty();
}
