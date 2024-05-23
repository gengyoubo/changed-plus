package github.com.gengyoubo.changedplus.block;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModEventSubscriberBlock {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "changedplus");

    // 在模组加载上下文中注册方块
    public ModEventSubscriberBlock() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // 事件订阅器，用于注册方块
    @Mod.EventBusSubscriber(modid = "changedplus", bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registration {
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            // 这里不需要调用BLOCKS.register(event)，因为在构造函数中已经注册过了
        }
    }
}