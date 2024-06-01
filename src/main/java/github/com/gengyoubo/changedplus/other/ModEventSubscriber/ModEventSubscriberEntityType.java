package github.com.gengyoubo.changedplus.other.ModEventSubscriber;

import github.com.gengyoubo.changedplus.LP.latex_generator;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import static github.com.gengyoubo.changedplus.other.ModEventSubscriber.ModEventSubscriberEntityBlock.latex_generator_block;


@Mod.EventBusSubscriber(modid = "changedplus", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriberEntityType {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, "changedplus");

    public static final RegistryObject<BlockEntityType<latex_generator>> LATEX_GENERATOR = BLOCK_ENTITY_TYPES.register("latex_generator",
            () -> BlockEntityType.Builder.of(latex_generator::new, latex_generator_block).build(null));
}
