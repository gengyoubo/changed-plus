package github.com.gengyoubo.changedplus.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
//创造模式物品选项卡
class ChangedPlusTab extends CreativeModeTab {
    private ChangedPlusTab() {
        super("changedplus");
    }

    public static ChangedPlusTab createChangedPlusTab() {
        return new ChangedPlusTab();
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        // 返回latex_sword的图标物品
        return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("changedplus", "latex_sword")));
    }
}





