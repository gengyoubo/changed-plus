package github.com.gengyoubo.changedplus.item;

import github.com.gengyoubo.changedplus.other.ChangedPlusTab;
import net.minecraft.world.item.Item;

// 单个物品函数
public class latex_stick extends Item {
    public latex_stick()
    {
        super(new Item.Properties().tab(ChangedPlusTab.getInstance()));
    }

}