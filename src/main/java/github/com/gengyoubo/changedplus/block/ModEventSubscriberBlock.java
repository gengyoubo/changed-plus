package github.com.gengyoubo.changedplus.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import github.com.gengyoubo.changedplus.item.ChangedPlusTab;

// 方块注册表
@Mod.EventBusSubscriber(modid = "changedplus", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriberBlock {

    @ObjectHolder("changedplus:latex_generator")
    public static final Block LATEX_GENERATOR = null;

    // 注册方块
    @SubscribeEvent
    public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) {
        registerBlock(event, new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3.0f).requiresCorrectToolForDrops()), "latex_generator");
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        // 使用 ObjectHolder 注解来引用方块
        event.getRegistry().register(new BlockItem(LATEX_GENERATOR, new Item.Properties().tab(ChangedPlusTab.getInstance())).setRegistryName(LATEX_GENERATOR.getRegistryName()));
    }

    // 一键注册方块的属性
    private static void registerBlock(RegistryEvent.Register<Block> event, Block block, String name) {
        block.setRegistryName("changedplus", name);
        event.getRegistry().register(block);
    }
}
