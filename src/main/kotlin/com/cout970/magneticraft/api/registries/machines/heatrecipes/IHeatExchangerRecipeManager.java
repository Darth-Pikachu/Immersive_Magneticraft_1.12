package com.cout970.magneticraft.api.registries.machines.heatrecipes;

import net.minecraftforge.fluids.FluidStack;

import java.util.List;

/**
 * Created by cout970 on 24/08/2016.
 */
public interface IHeatExchangerRecipeManager {

    /**
     * Retrieves the first recipe that matches the given input
     *
     * @param input the input to check the recipes
     *
     * @return the recipes that matches the input or null if none matches the input
     */
    IHeatExchangerRecipe findRecipe(FluidStack input);

    /**
     * The list with all registered recipes
     */
    List<IHeatExchangerRecipe> getRecipes();

    /**
     * Register a recipe if is not already registered
     *
     * @param recipe The recipe to register
     *
     * @return if the registration has ended successfully
     */
    boolean registerRecipe(IHeatExchangerRecipe recipe);

    /**
     * Creates a default recipe
     *
     * @param input the input stack
     * @param output the output stack
     * @param heat the heat consumed or generated by the recipe
     * @param minTemp the minimum temperature required for the recipe to craft
     * @param maxTemp the maximum temperature at which the recipe can craft
     * @param reverseLow if true, the recipe will run in reverse if below its minimum temperature
     * @param reverseHigh if true, the recipe will run in reverse if above its maximum temperature
     */
    IHeatExchangerRecipe createRecipe(FluidStack input, FluidStack output, long heat, double minTemp, double maxTemp,
                                      boolean reverseLow, boolean reverseHigh);
}
