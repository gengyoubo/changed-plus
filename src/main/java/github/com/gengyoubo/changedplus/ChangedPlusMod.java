package github.com.gengyoubo.changedplus;

//import github.com.gengyoubo.changedplus.block.latex_generator;
import github.com.gengyoubo.changedplus.block.latex_generator;
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

        // 实例化并使用 latex_generator 类以确保其构造函数被调用
        new latex_generator();
    }

    private void setup(final FMLCommonSetupEvent event) {
        // 一些初始化逻辑
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // 客户端初始化逻辑
    }
}
