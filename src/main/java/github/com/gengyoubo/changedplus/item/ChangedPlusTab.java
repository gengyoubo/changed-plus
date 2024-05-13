package github.com.gengyoubo.changedplus.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

// 创造模式物品选项卡
public class ChangedPlusTab extends CreativeModeTab {

    private ChangedPlusTab() {
        super("changedplus");
    }

    // 创建自定义选项卡的实例
    public static final ChangedPlusTab INSTANCE = new ChangedPlusTab();

    // 获取选项卡的实例
    public static ChangedPlusTab getInstance() {
        return INSTANCE;
    }

    // 重写makeIcon方法以设置选项卡的图标
    @Override
    public @NotNull ItemStack makeIcon() {
        // 返回latex_sword的图标物品
        return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("changedplus", "latex_sword")));
    }
}




