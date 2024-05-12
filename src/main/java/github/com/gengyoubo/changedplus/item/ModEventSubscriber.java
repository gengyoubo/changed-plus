package github.com.gengyoubo.changedplus.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// 物品注册表
@Mod.EventBusSubscriber(modid = "changedplus", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        registerItem(event, new Latex_sword(), "latex_sword");
        // 注册更多物品，如果有的话
    }
    // 注册单个物品
    private static void registerItem(RegistryEvent.Register<Item> event, Item item, String name) {
        item.setRegistryName("changedplus", name);
        event.getRegistry().register(item);
    }
}
