package github.com.gengyoubo.changedplus.block;

import com.mojang.datafixers.DSL;
import github.com.gengyoubo.changedplus.item.ChangedPlusTab;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;

@Mod("changedplus")
public class latex_generator {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "changedplus");
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, "changedplus");
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "changedplus");

    public static final RegistryObject<Block> LATEX_GENERATOR = BLOCKS.register("latex_generator",
            () -> new LatexGeneratorBlock(BlockBehaviour.Properties.of(Material.METAL)));
    public static final RegistryObject<BlockEntityType<LatexGeneratorBlockEntity>> LATEX_GENERATOR_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("latex_generator",
            () -> BlockEntityType.Builder.of(LatexGeneratorBlockEntity::new, LATEX_GENERATOR.get()).build(DSL.remainderType()));
    public static final RegistryObject<Item> LATEX_GENERATOR_ITEM = ITEMS.register("latex_generator",
            () -> new BlockItem(LATEX_GENERATOR.get(), new Item.Properties().tab(ChangedPlusTab.getInstance())));

    public latex_generator() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final class LatexGeneratorBlock extends BaseEntityBlock {
        public LatexGeneratorBlock(Properties props) {
            super(props);
        }

        @Nonnull
        @Override
        public RenderShape getRenderShape(@Nonnull BlockState state) {
            return RenderShape.MODEL;
        }

        @Override
        public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
            return new LatexGeneratorBlockEntity(pos, state);
        }
    }

    public static final class LatexGeneratorBlockEntity extends BlockEntity {
        private int latexAmount; // 存储的乳胶数量
        // 存储的能量数量

        public LatexGeneratorBlockEntity(BlockPos worldPosition, BlockState blockState) {
            super(LATEX_GENERATOR_BLOCK_ENTITY.get(), worldPosition, blockState);
        }

        public void tick() {
            if (level != null && !level.isClientSide) {
                if (canGeneratePower()) {
                    if (hasEnoughLatex()) {
                        consumeLatex();
                        generatePower();
                    }
                }
            }
        }

        private boolean canGeneratePower() {
            // 检查方块是否可以生成能量
            return true; // 示例实现
        }

        private boolean hasEnoughLatex() {
            return latexAmount > 0; // 检查是否有足够的乳胶
        }

        private void consumeLatex() {
            if (latexAmount > 0) {
                latexAmount--; // 消耗乳胶
            }
        }

        private void generatePower() {
            // 生成能量逻辑
        }

        private int calculateEnergyFromLatex() {
            // 计算消耗乳胶生成的能量
            return 10; // 示例值，每个乳胶单位生成10单位能量
        }

        // 其他必要的方法和逻辑
    }
}
