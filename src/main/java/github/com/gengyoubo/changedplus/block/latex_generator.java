package github.com.gengyoubo.changedplus.block;

import com.mojang.datafixers.DSL;
import net.minecraft.core.BlockPos;
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

    public static final RegistryObject<Block> MY_MACHINE;
    public static final RegistryObject<BlockEntityType<MyMachineEntity>> MY_MACHINE_BLOCK_ENTITY;

    static {
        MY_MACHINE = BLOCKS.register("latex_generator",
                () -> new MyMachine(BlockBehaviour.Properties.of(Material.METAL)));
        MY_MACHINE_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("latex_generator",
                () -> BlockEntityType.Builder.of(MyMachineEntity::new, MY_MACHINE.get()).build(DSL.remainderType()));
    }
    public latex_generator() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    public static final class MyMachine extends BaseEntityBlock {
        public MyMachine(Properties props) {
            super(props);
        }

        @Nonnull
        @Override
        public RenderShape getRenderShape(@Nonnull BlockState state) {
            return RenderShape.MODEL;
        }

        @Override
        public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
            return new MyMachineEntity(pos, state);
        }
    }
    public static final class MyMachineEntity extends BlockEntity {
        public MyMachineEntity(BlockEntityType<MyMachineEntity> type, BlockPos worldPosition, BlockState blockState) {
            super(type, worldPosition, blockState);
        }
        public MyMachineEntity(BlockPos worldPosition, BlockState blockState) {
            this(MY_MACHINE_BLOCK_ENTITY.get(), worldPosition, blockState);
        }
    }
}