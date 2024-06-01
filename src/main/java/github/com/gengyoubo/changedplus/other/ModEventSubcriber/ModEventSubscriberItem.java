package github.com.gengyoubo.changedplus.other.ModEventSubcriber;

import github.com.gengyoubo.changedplus.item.item.Latex;
import github.com.gengyoubo.changedplus.item.item.Latex_sword;
import github.com.gengyoubo.changedplus.item.item.latex_stick;
import github.com.gengyoubo.changedplus.item.item.long_latex_stick;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// 物品注册表
@Mod.EventBusSubscriber(modid = "changedplus", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriberItem {
    // 注册物品
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        registerItem(event, new Latex_sword(), "latex_sword");
        registerItem(event, new Latex(), "latex");
        registerItem(event, new latex_stick(), "latex_stick");
        registerItem(event, new long_latex_stick(), "long_latex_stick");
        // 以registerItem的格式注册物品↑
    }
    //一键注册物品的属性
    private static void registerItem(RegistryEvent.Register<Item> event, Item item, String name) {
        item.setRegistryName("changedplus", name);
        event.getRegistry().register(item);
    }
}

