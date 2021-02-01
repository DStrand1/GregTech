package gregtech.api.recipes.builders;

import com.google.common.collect.ImmutableMap;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.ValidationResult;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class GasCollectorRecipeBuilder extends RecipeBuilder<GasCollectorRecipeBuilder> {

    private int dimID;

    public GasCollectorRecipeBuilder() {
    }

    public GasCollectorRecipeBuilder(Recipe recipe, RecipeMap<GasCollectorRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
        this.dimID = recipe.getIntegerProperty("dimension");
    }

    public GasCollectorRecipeBuilder(RecipeBuilder<GasCollectorRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public GasCollectorRecipeBuilder copy() {
        return new GasCollectorRecipeBuilder(this);
    }

    @Override
    public boolean applyProperty(String key, Object value) {
        if (key.equals("dimension")) {
            this.dimID(((Number) value).intValue());
            return true;
        }
        return true;
    }

    public GasCollectorRecipeBuilder dimID(int dimID) {
        // TODO Check for valid dimension ID, if possible
        this.dimID = dimID;
        return this;
    }

    public ValidationResult<Recipe> build() {
        return ValidationResult.newResult(finalizeAndValidate(),
            new Recipe(inputs, outputs, chancedOutputs, fluidInputs, fluidOutputs,
                ImmutableMap.of("dimension", dimID),
                duration, EUt, hidden));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("dimension", dimID)
            .toString();
    }
}
