package github.com.gengyoubo.changedplus.other.ModEventSubscriber;

import github.com.gengyoubo.changedplus.LP.LatexGeneratorBlock;
import github.com.gengyoubo.changedplus.other.ChangedPlusTab;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import github.com.gengyoubo.changedplus.LP.latex_generator;

import java.util.Objects;



@Mod.EventBusSubscriber(modid = "changedplus", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriberEntityBlock {

    @ObjectHolder("changedplus:latex_generator")
    public static final Block latex_generator_block = null;

    @SubscribeEvent
    public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) {
        registerBlock(event, new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3.0f)
                .requiresCorrectToolForDrops()), "latex_generator");
    }

    @SubscribeEvent
    public static void onRegisterBlockEntityTypes(final RegistryEvent.Register<BlockEntityType<?>> event) {

        event.getRegistry().register(BlockEntityType.Builder.of(latex_generator::new, ModEventSubscriberEntityBlock.latex_generator_block).build(null).setRegistryName("changedplus", "latex_generator_entity"));
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new latex_generator(pos, state);
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {

        event.getRegistry().register(new BlockItem(latex_generator_block, new Item.Properties().tab(ChangedPlusTab.getInstance())).setRegistryName(Objects.requireNonNull(latex_generator_block.getRegistryName())));


    }

    private static void registerBlock(RegistryEvent.Register<Block> event, Block block, String name) {
        block.setRegistryName("changedplus", name);
        event.getRegistry().register(block);
    }

    public static void registerBlockUsage() {
        MinecraftForge.EVENT_BUS.register(LatexGeneratorBlock.class);
    }


    public static void registerBlockInteractions() {
        MinecraftForge.EVENT_BUS.register(LatexGeneratorBlock.class);
    }
}

