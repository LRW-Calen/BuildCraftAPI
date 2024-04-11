/**
 * Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 * <p>
 * The BuildCraft API is distributed under the terms of the MIT License. Please check the contents of the license, which
 * should be located as "LICENSE.API" in the BuildCraft source code distribution.
 */
package buildcraft.api.fuels;

import net.minecraftforge.fluids.FluidStack;

// Calen: ICoolant 1.12.2 -> IFluidCoolant 1.18.2
// 1.18.2 new class ICoolant super ICoolant, IFluidCoolant
//public interface ICoolant
public interface IFluidCoolant extends ICoolant
{
    boolean matchesFluid(FluidStack fluid);

    /** @param fluid
     * @param heat
     * @return 0 if the input fluid provides no cooling, or a value greater than 0 if it does. */
    float getDegreesCoolingPerMB(FluidStack fluid, float heat);
    float getDegreesCoolingPerMB();
}
