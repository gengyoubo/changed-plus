package github.com.gengyoubo.changedplus.other.costomloading;

import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.client.event.ScreenOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod("changedplus")
public class CustomLoadingScreenHandler {

    public CustomLoadingScreenHandler() {
        // 注册事件监听器
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onGuiOpen(ScreenOpenEvent event) {
        // 检查当前屏幕是否是加载屏幕
        Screen currentScreen = event.getScreen();
        if (currentScreen instanceof LevelLoadingScreen) {
            // 设置自定义加载屏幕
            event.setScreen(new CustomLoadingScreen());
        }
    }

    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent event) {
        // 这里可以执行客户端设置时需要的代码
    }

}
