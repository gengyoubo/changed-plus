package github.com.gengyoubo.changedplus.other;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("changedplus")
public class ChangedPlusMod {

    public ChangedPlusMod() {
        // 注册事件总线
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // 初始化逻辑，例如注册方块
        // 示例：注册 latex_generator 方块
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // 客户端初始化逻辑
    }
}
