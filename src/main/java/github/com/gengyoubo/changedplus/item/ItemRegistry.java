package github.com.gengyoubo.changedplus.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

// 物品注册表
@Mod.EventBusSubscriber(modid = "changedplus", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistry {

    @ObjectHolder("changedplus:latex_sword")
    public static final Item LATEX_SWORD = null;

    // 在注册事件上使用@SubscribeEvent注释来指示注册物品的方法
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        // 在这里注册你的物品
        event.getRegistry().registerAll(
                // 示例：new YourItem().setRegistryName("changedplus", "your_item_name")
                // 如果有多个物品，可以用逗号分隔，并用event.getRegistry().registerAll()包装
                // 例如：event.getRegistry().registerAll(item1, item2, item3);
        );
    }
}

