/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * The BuildCraft API is distributed under the terms of the MIT License. Please check the contents of the license, which
 * should be located as "LICENSE.API" in the BuildCraft source code distribution. */
package buildcraft.api.core;

import net.minecraft.world.level.Level;

/** Provides a way of tracking time in the world, without requiring manual ticking. */
public class SafeTimeTracker {

    private long lastMark = Long.MIN_VALUE;
    private long duration = -1;
    private long randomRange = 0;
    private long lastRandomDelay = 0;
    private long internalDelay = 1;

    /** Only use this if the delay time changes (say, if you use this to determine when a refining should be complete */
    public SafeTimeTracker() {

    }

    public SafeTimeTracker(long delay) {
        internalDelay = delay;
    }

    /** In many situations, it is a bad idea to have all objects of the same kind to be waiting for the exact same
     * amount of time, as that can lead to some situation where they're all synchronized and got to work all at the same
     * time. When created with a random range, the mark that is set when reaching the expect delay will be added with a
     * random number between [0, range[, meaning that the event will take between 0 and range more tick to run. */
    public SafeTimeTracker(long delay, long random) {
        internalDelay = delay;
        randomRange = random;
    }

    /** Return true if the internal delay has passed since last time marked was called successfully. */
    public boolean markTimeIfDelay(Level world) {
        return markTimeIfDelay(world, internalDelay);
    }

    /** Return true if a given delay has passed since last time marked was called successfully. */
    public boolean markTimeIfDelay(Level world, long delay) {
        if (world == null) {
            return false;
        }

        long currentTime = world.getGameTime();

        if (currentTime < lastMark) {
            lastMark = currentTime;
            return false;
        } else if (lastMark + delay + lastRandomDelay <= currentTime) {
            duration = currentTime - lastMark;
            lastMark = currentTime;
            lastRandomDelay = (int) (Math.random() * randomRange);

            return true;
        } else {
            return false;
        }
    }

    public long durationOfLastDelay() {
        return duration > 0 ? duration : 0;
    }

    public void markTime(Level world) {
        lastMark = world.getGameTime();
    }
}
