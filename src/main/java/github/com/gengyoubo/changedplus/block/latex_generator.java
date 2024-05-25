package github.com.gengyoubo.changedplus.block;

import github.com.gengyoubo.changedplus.item.ChangedPlusTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod("changedplus")
public class latex_generator {

    // 使用 DeferredRegister 来注册方块和方块项
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "changedplus");
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "changedplus");

    public static final RegistryObject<Block> LATEX_GENERATOR = BLOCKS.register("latex_generator",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3.0f).requiresCorrectToolForDrops()));

    public latex_generator() {
        // 注册方块和物品到事件总线
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        // 注册 BlockItem
        ITEMS.register("latex_generator",
                () -> new BlockItem(LATEX_GENERATOR.get(), new Item.Properties().tab(ChangedPlusTab.getInstance())));
    }
}