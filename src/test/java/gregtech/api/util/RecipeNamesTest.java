package gregtech.api.util;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import net.minecraft.init.Bootstrap;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static gregtech.api.recipes.RecipeMaps.*;
import static org.junit.Assert.*;

public class RecipeNamesTest {

    private static final List<RecipeMap<?>> RECIPE_MAPS = new ArrayList<>(Arrays.asList(
            COMPRESSOR_RECIPES,
            EXTRACTOR_RECIPES,
            MACERATOR_RECIPES,
            ORE_WASHER_RECIPES,
            THERMAL_CENTRIFUGE_RECIPES,
            FURNACE_RECIPES,
            MICROWAVE_RECIPES,
            ASSEMBLER_RECIPES,
            FORMING_PRESS_RECIPES,
            FLUID_CANNER_RECIPES,
            PLASMA_ARC_FURNACE_RECIPES,
            ARC_FURNACE_RECIPES,
            SIFTER_RECIPES,
            LASER_ENGRAVER_RECIPES,
            MIXER_RECIPES,
            AUTOCLAVE_RECIPES,
            ELECTROMAGNETIC_SEPARATOR_RECIPES,
            POLARIZER_RECIPES,
            CHEMICAL_BATH_RECIPES,
            BREWING_RECIPES,
            FLUID_HEATER_RECIPES,
            DISTILLERY_RECIPES,
            FERMENTING_RECIPES,
            FLUID_SOLIDFICATION_RECIPES,
            FLUID_EXTRACTION_RECIPES,
            FUSION_RECIPES,
            CENTRIFUGE_RECIPES,
            ELECTROLYZER_RECIPES,
            BLAST_RECIPES,
            IMPLOSION_RECIPES,
            VACUUM_RECIPES,
            CHEMICAL_RECIPES,
            DISTILLATION_RECIPES,
            CRACKING_RECIPES,
            PYROLYSE_RECIPES,
            WIREMILL_RECIPES,
            BENDER_RECIPES,
            ALLOY_SMELTER_RECIPES,
            CANNER_RECIPES,
            LATHE_RECIPES,
            CUTTER_RECIPES,
            EXTRUDER_RECIPES,
            FORGE_HAMMER_RECIPES,
            PACKER_RECIPES,
            UNPACKER_RECIPES,
            AMPLIFIERS
    ));

    @BeforeClass
    public static void bootStrap() {
        Bootstrap.register(); // TODO May need to do more here
    }

    @Test
    public void testRecipeMapUniqueness() {
        Map<String, List<Recipe>> duplicateNames = new HashMap<>();
        for (RecipeMap<?> map : RECIPE_MAPS) {
            Map<String, Recipe> exploredNames = new HashMap<>();

            for (Recipe recipe : map.getRecipeList()) {
                for (String name : exploredNames.keySet()) {

                    if (recipe.getName().equals(name)) {
                        if (duplicateNames.containsKey(name))
                            duplicateNames.get(name).add(recipe);
                        else
                            duplicateNames.put(name, new ArrayList<>(Collections.singletonList(recipe)));
                        break;
                    }
                }
                exploredNames.put(recipe.getName(), recipe);
            }
        }
        assertSame(
                "Duplicate recipes found",
                duplicateNames,
                new HashMap<String, List<Recipe>>()
        );
    }
}
