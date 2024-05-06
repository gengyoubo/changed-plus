package com.gengyoubo.changedplus;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// 此处的值应该与 META-INF/mods.toml 文件中的条目匹配
@Mod("changedplus")
public class ChangedPlus
{
    // 直接引用 slf4j 日志记录器
    private static final Logger LOGGER = LogUtils.getLogger();

    public ChangedPlus()
    {
        // 注册用于模组加载的设置方法
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        // 注册我们自己以便监听我们感兴趣的服务器和其他游戏事件
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void setup(final FMLCommonSetupEvent event) {}
    private void doClientStuff(final InterModEnqueueEvent event) {}
}
