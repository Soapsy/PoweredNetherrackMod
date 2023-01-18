package net.Vyf.powerednetherrack.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.Vyf.powerednetherrack.PoweredNetherrack;
import net.Vyf.powerednetherrack.item.ModItemGroup;
import net.Vyf.powerednetherrack.item.ModItem;
import java.util.function.Supplier;

public class ModBlock {

    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, PoweredNetherrack.MOD_ID);

    public static final RegistryObject<Block> AMETHYST_ORE = registerBlock("amethyst_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(2).setRequiresTool().harvestTool(ToolType.PICKAXE).hardnessAndResistance(5f)));


    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItem.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().group(ModItemGroup.TUTORIAL_MOD)));
    }

    public static final RegistryObject<Block> FIRESTONE_BLOCK = registerBlock("firestone_block",
    () -> new FirestoneBlock(AbstractBlock.Properties.create(Material.IRON)
         .harvestLevel(2).hardnessAndResistance(6f).harvestTool(ToolType.PICKAXE).setRequiresTool()));
         

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }



}
