package net.Vyf.powerednetherrack.item;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.Vyf.powerednetherrack.PoweredNetherrack;

public class ModItem {
    
    public static final DeferredRegister<Item> ITEMS
    = DeferredRegister.create(ForgeRegistries.ITEMS, PoweredNetherrack.MOD_ID);



    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst",
    () -> new Item(new Item.Properties().group(ModItemGroup.TUTORIAL_MOD)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
