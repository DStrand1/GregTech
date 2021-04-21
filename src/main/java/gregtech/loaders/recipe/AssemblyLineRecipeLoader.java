package gregtech.loaders.recipe;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.item.ItemStack;

public class AssemblyLineRecipeLoader {

    public static void registerAssemblyLineRecipes() {

        ItemStack last_bat = (GAConfig.GT5U.replaceUVwithMAXBat ? GAMetaItems.MAX_BATTERY : MetaItems.ZPM2).getStackForm();
        if (GAConfig.GT5U.enableZPMandUVBats) {
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .inputs(OreDictUnifier.get(OrePrefix.plate, Materials.Europium, 16),
                            MetaItems.WETWARE_SUPER_COMPUTER_UV.getStackForm(4),
                            MetaItems.ENERGY_LAPOTRONIC_ORB2.getStackForm(8),
                            MetaItems.FIELD_GENERATOR_LUV.getStackForm(2),
                            MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(64),
                            MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(64),
                            MetaItems.SMD_DIODE.getStackForm(8),
                            OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Naquadah, 32))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(2880),
                            Materials.Water.getFluid(8000))
                    .outputs(GAMetaItems.ENERGY_MODULE.getStackForm())
                    .duration(2000).EUt(100000).buildAndRegister();

            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .inputs(OreDictUnifier.get(OrePrefix.plate, Materials.Americium, 16),
                            MetaItems.WETWARE_SUPER_COMPUTER_UV.getStackForm(4),
                            GAMetaItems.ENERGY_MODULE.getStackForm(8),
                            MetaItems.FIELD_GENERATOR_ZPM.getStackForm(2),
                            MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                            MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                            MetaItems.SMD_DIODE.getStackForm(16),
                            OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.NaquadahAlloy, 32))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(2880),
                            Materials.Water.getFluid(16000))
                    .outputs(GAMetaItems.ENERGY_CLUSTER.getStackForm())
                    .duration(2000).EUt(200000).buildAndRegister();

            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .inputs(OreDictUnifier.get(OrePrefix.plate, Materials.Neutronium, 16),
                            MetaItems.WETWARE_MAINFRAME_MAX.getStackForm(4),
                            GAMetaItems.ENERGY_CLUSTER.getStackForm(8),
                            MetaItems.FIELD_GENERATOR_UV.getStackForm(2),
                            MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                            MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                            MetaItems.SMD_DIODE.getStackForm(16),
                            OreDictUnifier.get(OrePrefix.wireGtSingle, MarkerMaterials.Tier.Superconductor, 32))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(2880),
                            Materials.Water.getFluid(16000),
                            Materials.Naquadria.getFluid(1152))
                    .outputs(last_bat)
                    .duration(2000).EUt(300000).buildAndRegister();
        }
        else {
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .inputs(OreDictUnifier.get(OrePrefix.plate, Materials.Neutronium, 16),
                            MetaItems.WETWARE_MAINFRAME_MAX.getStackForm(4),
                            MetaItems.ENERGY_LAPOTRONIC_ORB2.getStackForm(8),
                            MetaItems.FIELD_GENERATOR_UV.getStackForm(2),
                            MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                            MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                            MetaItems.SMD_DIODE.getStackForm(16),
                            OreDictUnifier.get(OrePrefix.wireGtSingle, MarkerMaterials.Tier.Superconductor, 32))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(2880),
                            Materials.Water.getFluid(16000))
                    .outputs(last_bat)
                    .duration(2000).EUt(300000).buildAndRegister();
        }

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL),
                        OreDictUnifier.get(OrePrefix.plate, Materials.Plutonium241),
                        OreDictUnifier.get(OrePrefix.plate, Materials.NetherStar),
                        MetaItems.FIELD_GENERATOR_IV.getStackForm(2),
                        MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(32),
                        OreDictUnifier.get(OrePrefix.wireGtSingle, MarkerMaterials.Tier.Superconductor, 32))
                .input(OrePrefix.circuit, MarkerMaterials.Tier.Ultimate, 4)
                .fluidInputs(Materials.SolderingAlloy.getFluid(2880))
                .outputs(MetaTileEntities.FUSION_REACTOR[0].getStackForm())
                .duration(1000).EUt(30000).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL),
                        OreDictUnifier.get(OrePrefix.plate, Materials.Europium, 4),
                        MetaItems.FIELD_GENERATOR_LUV.getStackForm(2),
                        MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(48),
                        OreDictUnifier.get(OrePrefix.wireGtDouble, MarkerMaterials.Tier.Superconductor, 32))
                .input(OrePrefix.circuit, MarkerMaterials.Tier.Superconductor, 4)
                .fluidInputs(Materials.SolderingAlloy.getFluid(2880))
                .outputs(MetaTileEntities.FUSION_REACTOR[1].getStackForm())
                .duration(1000).EUt(60000).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL),
                        MetaItems.WETWARE_MAINFRAME_MAX.getStackForm(4),
                        OreDictUnifier.get(OrePrefix.plate, Materials.Americium, 4),
                        MetaItems.FIELD_GENERATOR_ZPM.getStackForm(2),
                        MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                        OreDictUnifier.get(OrePrefix.wireGtQuadruple, MarkerMaterials.Tier.Superconductor, 32))
                .fluidInputs(Materials.SolderingAlloy.getFluid(2880))
                .outputs(MetaTileEntities.FUSION_REACTOR[2].getStackForm())
                .duration(1000).EUt(90000).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.stickLong, Materials.NeodymiumMagnetic),
                        OreDictUnifier.get(OrePrefix.stickLong, Materials.HSSG, 2),
                        OreDictUnifier.get(OrePrefix.ring, Materials.HSSG, 4),
                        OreDictUnifier.get(OrePrefix.valueOf("round"), Materials.HSSG, 16), // TODO valueOf
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper, 64),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper, 64),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper, 64),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper, 64),
                        OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.YttriumBariumCuprate, 2))
                .fluidInputs(Materials.SolderingAlloy.getFluid(144),
                        Materials.Lubricant.getFluid(250))
                .outputs(MetaItems.ELECTRIC_MOTOR_LUV.getStackForm())
                .duration(600).EUt(10240).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.stickLong, Materials.NeodymiumMagnetic),
                        OreDictUnifier.get(OrePrefix.stickLong, Materials.HSSE, 2),
                        OreDictUnifier.get(OrePrefix.ring, Materials.HSSE, 4),
                        OreDictUnifier.get(OrePrefix.valueOf("round"), Materials.HSSE, 16), // TODO valueOf
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 64),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 64),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 64),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 64),
                        OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.VanadiumGallium, 2))
                .fluidInputs(Materials.SolderingAlloy.getFluid(288),
                        Materials.Lubricant.getFluid(750))
                .outputs(MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm())
                .duration(600).EUt(40960).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.block, Materials.NeodymiumMagnetic),
                        OreDictUnifier.get(OrePrefix.stickLong, Materials.Neutronium, 2),
                        OreDictUnifier.get(OrePrefix.ring, Materials.Neutronium, 4),
                        OreDictUnifier.get(OrePrefix.valueOf("round"), Materials.Neutronium, 16), // TODO valueOf
                        OreDictUnifier.get(OrePrefix.wireGtSingle, MarkerMaterials.Tier.Superconductor, 64),
                        OreDictUnifier.get(OrePrefix.wireGtSingle, MarkerMaterials.Tier.Superconductor, 64),
                        OreDictUnifier.get(OrePrefix.wireGtSingle, MarkerMaterials.Tier.Superconductor, 64),
                        OreDictUnifier.get(OrePrefix.wireGtSingle, MarkerMaterials.Tier.Superconductor, 64),
                        OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.NiobiumTitanium, 2))
                .fluidInputs(Materials.SolderingAlloy.getFluid(1296),
                        Materials.Lubricant.getFluid(2000))
                .outputs(MetaItems.ELECTRIC_MOTOR_UV.getStackForm())
                .duration(600).EUt(163840).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(MetaItems.ELECTRIC_MOTOR_LUV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.pipeSmall, Materials.Ultimet, 2),
                        OreDictUnifier.get(OrePrefix.screw, Materials.HSSG, 8),
                        OreDictUnifier.get(OrePrefix.ring, Materials.SiliconeRubber, 16),
                        OreDictUnifier.get(OrePrefix.rotor, Materials.HSSG, 2),
                        OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.YttriumBariumCuprate, 2))
                .fluidInputs(Materials.SolderingAlloy.getFluid(144),
                        Materials.Lubricant.getFluid(250))
                .outputs(MetaItems.ELECTRIC_PUMP_LUV.getStackForm())
                .duration(600).EUt(15360).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm(),
                        OreDictUnifier.get(OrePrefix.pipeMedium, Materials.Ultimet, 2),
                        OreDictUnifier.get(OrePrefix.screw, Materials.HSSE, 8),
                        OreDictUnifier.get(OrePrefix.ring, Materials.SiliconeRubber, 16),
                        OreDictUnifier.get(OrePrefix.rotor, Materials.HSSE, 2),
                        OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.VanadiumGallium, 2))
                .fluidInputs(Materials.SolderingAlloy.getFluid(288),
                        Materials.Lubricant.getFluid(750))
                .outputs(MetaItems.ELECTRIC_PUMP_ZPM.getStackForm())
                .duration(600).EUt(61440).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(MetaItems.ELECTRIC_MOTOR_UV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.pipeLarge, Materials.Ultimet, 2),
                        OreDictUnifier.get(OrePrefix.screw, Materials.Neutronium, 8),
                        OreDictUnifier.get(OrePrefix.ring, Materials.SiliconeRubber, 16),
                        OreDictUnifier.get(OrePrefix.rotor, Materials.Neutronium, 2),
                        OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.NiobiumTitanium, 2))
                .fluidInputs(Materials.SolderingAlloy.getFluid(1296),
                        Materials.Lubricant.getFluid(2000))
                .outputs(MetaItems.ELECTRIC_PUMP_UV.getStackForm())
                .duration(600).EUt(245760).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(MetaItems.ELECTRIC_MOTOR_LUV.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.plate, Materials.HSSG, 8),
                        OreDictUnifier.get(OrePrefix.gear, Materials.HSSG, 4),
                        OreDictUnifier.get(OrePrefix.stick, Materials.HSSG, 4),
                        OreDictUnifier.get(OrePrefix.ingot, Materials.HSSG, 2),
                        OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.YttriumBariumCuprate, 2))
                .fluidInputs(Materials.StyreneButadieneRubber.getFluid(1440),
                        Materials.Lubricant.getFluid(250))
                .outputs(MetaItems.CONVEYOR_MODULE_LUV.getStackForm())
                .duration(600).EUt(15360).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.plate, Materials.HSSE, 8),
                        OreDictUnifier.get(OrePrefix.gear, Materials.HSSE, 4),
                        OreDictUnifier.get(OrePrefix.stick, Materials.HSSE, 4),
                        OreDictUnifier.get(OrePrefix.ingot, Materials.HSSE, 2),
                        OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.VanadiumGallium, 2))
                .fluidInputs(Materials.StyreneButadieneRubber.getFluid(2880),
                        Materials.Lubricant.getFluid(750))
                .outputs(MetaItems.CONVEYOR_MODULE_ZPM.getStackForm())
                .duration(600).EUt(61440).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(MetaItems.ELECTRIC_MOTOR_UV.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.plate, Materials.Neutronium, 8),
                        OreDictUnifier.get(OrePrefix.gear, Materials.Neutronium, 4),
                        OreDictUnifier.get(OrePrefix.stick, Materials.Neutronium, 4),
                        OreDictUnifier.get(OrePrefix.ingot, Materials.Neutronium, 2),
                        OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.NiobiumTitanium, 2))
                .fluidInputs(Materials.StyreneButadieneRubber.getFluid(2880),
                        Materials.Lubricant.getFluid(2000))
                .outputs(MetaItems.CONVEYOR_MODULE_UV.getStackForm())
                .duration(600).EUt(245760).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(MetaItems.ELECTRIC_MOTOR_LUV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.plate, Materials.HSSG, 8),
                        OreDictUnifier.get(OrePrefix.gearSmall, Materials.HSSG, 8),
                        OreDictUnifier.get(OrePrefix.stick, Materials.HSSG, 4),
                        OreDictUnifier.get(OrePrefix.ingot, Materials.HSSG, 2),
                        OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.YttriumBariumCuprate, 2))
                .fluidInputs(Materials.SolderingAlloy.getFluid(144),
                        Materials.Lubricant.getFluid(250))
                .outputs(MetaItems.ELECTRIC_PISTON_LUV.getStackForm())
                .duration(600).EUt(15360).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm(),
                        OreDictUnifier.get(OrePrefix.plate, Materials.HSSE, 8),
                        OreDictUnifier.get(OrePrefix.gearSmall, Materials.HSSE, 8),
                        OreDictUnifier.get(OrePrefix.stick, Materials.HSSE, 4),
                        OreDictUnifier.get(OrePrefix.ingot, Materials.HSSE, 2),
                        OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.VanadiumGallium, 2))
                .fluidInputs(Materials.SolderingAlloy.getFluid(288),
                        Materials.Lubricant.getFluid(750))
                .outputs(MetaItems.ELECTRIC_PISTON_ZPM.getStackForm())
                .duration(600).EUt(61440).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(MetaItems.ELECTRIC_MOTOR_UV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.plate, Materials.Neutronium, 8),
                        OreDictUnifier.get(OrePrefix.gearSmall, Materials.Neutronium, 8),
                        OreDictUnifier.get(OrePrefix.stick, Materials.Neutronium, 4),
                        OreDictUnifier.get(OrePrefix.ingot, Materials.Neutronium, 2),
                        OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.NiobiumTitanium, 2))
                .fluidInputs(Materials.SolderingAlloy.getFluid(1296),
                        Materials.Lubricant.getFluid(2000))
                .outputs(MetaItems.ELECTRIC_PISTON_UV.getStackForm())
                .duration(600).EUt(245760).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.cableGtDouble, Materials.YttriumBariumCuprate, 16),
                        OreDictUnifier.get(OrePrefix.screw, Materials.HSSG, 16),
                        OreDictUnifier.get(OrePrefix.stick, Materials.HSSG, 16),
                        OreDictUnifier.get(OrePrefix.ingot, Materials.HSSG),
                        MetaItems.ELECTRIC_MOTOR_LUV.getStackForm(2),
                        MetaItems.ELECTRIC_PISTON_LUV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.Extreme, 8))
                .fluidInputs(Materials.SolderingAlloy.getFluid(576),
                        Materials.Lubricant.getFluid(250))
                .outputs(MetaItems.ROBOT_ARM_LUV.getStackForm())
                .duration(600).EUt(20480).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.cableGtDouble, Materials.VanadiumGallium, 16),
                        OreDictUnifier.get(OrePrefix.screw, Materials.HSSE, 16),
                        OreDictUnifier.get(OrePrefix.stick, Materials.HSSE, 16),
                        OreDictUnifier.get(OrePrefix.ingot, Materials.HSSE),
                        MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm(2),
                        MetaItems.ELECTRIC_PISTON_ZPM.getStackForm(),
                        OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.Elite, 8))
                .fluidInputs(Materials.SolderingAlloy.getFluid(1152),
                        Materials.Lubricant.getFluid(750))
                .outputs(MetaItems.ROBOT_ARM_ZPM.getStackForm())
                .duration(600).EUt(81920).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.cableGtDouble, Materials.NiobiumTitanium, 16),
                        OreDictUnifier.get(OrePrefix.screw, Materials.Neutronium, 16),
                        OreDictUnifier.get(OrePrefix.stick, Materials.Neutronium, 16),
                        OreDictUnifier.get(OrePrefix.ingot, Materials.Neutronium),
                        MetaItems.ELECTRIC_MOTOR_UV.getStackForm(2),
                        MetaItems.ELECTRIC_PISTON_UV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.Master, 8))
                .fluidInputs(Materials.SolderingAlloy.getFluid(2304),
                        Materials.Lubricant.getFluid(2000))
                .outputs(MetaItems.ROBOT_ARM_UV.getStackForm())
                .duration(600).EUt(327680).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.HSSG),
                        MetaItems.EMITTER_IV.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Electrum, 64),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Electrum, 64),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Electrum, 64),
                        OreDictUnifier.get(OrePrefix.wireGtDouble, Materials.YttriumBariumCuprate, 8),
                        OreDictUnifier.get(OrePrefix.gemExquisite, Materials.Ruby, 2),
                        OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.Extreme, 8))
                .fluidInputs(Materials.SolderingAlloy.getFluid(576))
                .outputs(MetaItems.EMITTER_LUV.getStackForm())
                .duration(600).EUt(15360).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.HSSE),
                        MetaItems.EMITTER_LUV.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Platinum, 64),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Platinum, 64),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Platinum, 64),
                        OreDictUnifier.get(OrePrefix.wireGtDouble, Materials.VanadiumGallium, 8),
                        OreDictUnifier.get(OrePrefix.gemExquisite, Materials.Emerald, 2),
                        OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.Elite, 8))
                .fluidInputs(Materials.SolderingAlloy.getFluid(576))
                .outputs(MetaItems.EMITTER_ZPM.getStackForm())
                .duration(600).EUt(61440).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.Neutronium),
                        MetaItems.EMITTER_ZPM.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Osmiridium, 64),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Osmiridium, 64),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Osmiridium, 64),
                        OreDictUnifier.get(OrePrefix.wireGtDouble, Materials.NiobiumTitanium, 8),
                        OreDictUnifier.get(OrePrefix.gemExquisite, Materials.Diamond, 2),
                        OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.Master, 8))
                .fluidInputs(Materials.SolderingAlloy.getFluid(576))
                .outputs(MetaItems.EMITTER_UV.getStackForm())
                .duration(600).EUt(245760).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.HSSG),
                        MetaItems.SENSOR_IV.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Electrum, 64),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Electrum, 64),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Electrum, 64),
                        OreDictUnifier.get(OrePrefix.wireGtDouble, Materials.YttriumBariumCuprate, 8),
                        OreDictUnifier.get(OrePrefix.gemExquisite, Materials.Ruby, 2),
                        OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.Extreme, 8))
                .fluidInputs(Materials.SolderingAlloy.getFluid(576))
                .outputs(MetaItems.SENSOR_LUV.getStackForm())
                .duration(600).EUt(15360).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.HSSE),
                        MetaItems.SENSOR_LUV.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Platinum, 64),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Platinum, 64),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Platinum, 64),
                        OreDictUnifier.get(OrePrefix.wireGtDouble, Materials.VanadiumGallium, 8),
                        OreDictUnifier.get(OrePrefix.gemExquisite, Materials.Emerald, 2),
                        OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.Elite, 8))
                .fluidInputs(Materials.SolderingAlloy.getFluid(576))
                .outputs(MetaItems.SENSOR_ZPM.getStackForm())
                .duration(600).EUt(61440).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.Neutronium),
                        MetaItems.SENSOR_ZPM.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Osmiridium, 64),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Osmiridium, 64),
                        OreDictUnifier.get(OrePrefix.foil, Materials.Osmiridium, 64),
                        OreDictUnifier.get(OrePrefix.wireGtDouble, Materials.NiobiumTitanium, 8),
                        OreDictUnifier.get(OrePrefix.gemExquisite, Materials.Diamond, 2),
                        OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.Master, 8))
                .fluidInputs(Materials.SolderingAlloy.getFluid(576))
                .outputs(MetaItems.SENSOR_UV.getStackForm())
                .duration(600).EUt(245760).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.HSSG),
                        MetaItems.QUANTUM_STAR.getStackForm(),
                        MetaItems.EMITTER_LUV.getStackForm(4),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                        OreDictUnifier.get(OrePrefix.cableGtOctal, Materials.YttriumBariumCuprate, 4),
                        OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.Master, 16))
                .fluidInputs(Materials.SolderingAlloy.getFluid(576))
                .outputs(MetaItems.FIELD_GENERATOR_LUV.getStackForm())
                .duration(600).EUt(30720).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.HSSE),
                        MetaItems.QUANTUM_STAR.getStackForm(),
                        MetaItems.EMITTER_ZPM.getStackForm(4),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                        OreDictUnifier.get(OrePrefix.cableGtOctal, Materials.YttriumBariumCuprate, 4),
                        OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.Ultimate, 16))
                .fluidInputs(Materials.SolderingAlloy.getFluid(1152))
                .outputs(MetaItems.FIELD_GENERATOR_ZPM.getStackForm())
                .duration(600).EUt(245760).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.Neutronium),
                        MetaItems.GRAVI_STAR.getStackForm(),
                        MetaItems.EMITTER_UV.getStackForm(4),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                        OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                        OreDictUnifier.get(OrePrefix.cableGtOctal, Materials.YttriumBariumCuprate, 4),
                        OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.Superconductor, 16))
                .fluidInputs(Materials.SolderingAlloy.getFluid(2304))
                .outputs(MetaItems.FIELD_GENERATOR_UV.getStackForm())
                .duration(600).EUt(491520).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.Tritanium, 4),
                        MetaItems.WETWARE_SUPER_COMPUTER_UV.getStackForm(8),
                        MetaItems.SMALL_COIL.getStackForm(4),
                        MetaItems.SMD_CAPACITOR.getStackForm(32),
                        MetaItems.SMD_RESISTOR.getStackForm(32),
                        MetaItems.SMD_TRANSISTOR.getStackForm(32),
                        MetaItems.SMD_DIODE.getStackForm(32),
                        MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(16),
                        OreDictUnifier.get(OrePrefix.wireGtDouble, MarkerMaterials.Tier.Superconductor, 16),
                        OreDictUnifier.get(OrePrefix.foil, Materials.SiliconeRubber, 64))
                .fluidInputs(Materials.SolderingAlloy.getFluid(2880),
                        Materials.Water.getFluid(10000))
                .outputs(MetaItems.WETWARE_MAINFRAME_MAX.getStackForm())
                .duration(2000).EUt(300000).buildAndRegister();

        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(MetaItems.WETWARE_BOARD.getStackForm(),
                        GAMetaItems.STEM_CELLS.getStackForm(8),
                        MetaItems.GLASS_TUBE.getStackForm(8),
                        OreDictUnifier.get(OrePrefix.foil, Materials.SiliconeRubber, 64),
                        OreDictUnifier.get(OrePrefix.plate, Materials.Gold, 8),
                        OreDictUnifier.get(OrePrefix.plate, Materials.StainlessSteel, 4))
                .fluidInputs(GAMaterials.STERILE_GROWTH_MEDIUM.getFluid(100),
                        Materials.UUMatter.getFluid(20),
                        Materials.DistilledWater.getFluid(4000))
                .outputs(GAMetaItems.NEURO_PROCESSOR.getStackForm(8))
                .duration(200).EUt(20000).buildAndRegister();
    }
}
