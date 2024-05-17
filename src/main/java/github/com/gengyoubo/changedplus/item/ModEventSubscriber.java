package github.com.gengyoubo.changedplus.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// 物品注册表
@Mod.EventBusSubscriber(modid = "changedplus", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
    // 注册物品
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        registerItem(event, new Latex_sword(), "latex_sword");
        registerItem(event, new Latex(), "latex");
        registerItem(event, new latex_steak(), "latex_steak");
        registerItem(event, new long_latex_steak(), "long_latex_steak");
        // 以registerItem的格式注册物品↑
    }
    //一键注册物品的属性
    private static void registerItem(RegistryEvent.Register<Item> event, Item item, String name) {
        item.setRegistryName("changedplus", name);
        event.getRegistry().register(item);
    }
}

