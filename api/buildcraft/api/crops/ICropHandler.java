package buildcraft.api.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public interface ICropHandler {

    /** Check if an item is a seed.
     *
     * @param stack
     * @return true if the item can be planted. */
    boolean isSeed(ItemStack stack);

    /** Check if the item can be planted. You can assume canSustainPlant() will only be called if isSeed() returned
     * true.
     *
     * @param world
     * @param seed
     * @param pos
     * @return true if the item can be planted at pos. */
    boolean canSustainPlant(Level world, ItemStack seed, BlockPos pos);

    /** Plant the item in the block. You can assume plantCrop() will only be called if canSustainPlant() returned true.
     *
     * @param world
     * @param player
     * @param seed
     * @param pos
     * @return true if the item was planted at pos. */
    boolean plantCrop(Level world, Player player, ItemStack seed, BlockPos pos);

    /** Check if a crop is mature and can be harvested.
     *
     * @param blockAccess
     * @param state
     * @param pos
     * @return true if the block at pos is mature and can be harvested. */
    boolean isMature(LevelAccessor blockAccess, BlockState state, BlockPos pos);

    /** Harvest the crop. You can assume harvestCrop() will only be called if isMature() returned true.
     *
     * @param world
     * @param pos
     * @param drops a list to return the harvest's drops.
     * @return true if the block was successfully harvested. */
    boolean harvestCrop(Level world, BlockPos pos, NonNullList<ItemStack> drops);

}
