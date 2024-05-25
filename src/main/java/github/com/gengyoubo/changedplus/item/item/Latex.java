package github.com.gengyoubo.changedplus.item.item;

import github.com.gengyoubo.changedplus.item.ChangedPlusTab;
import net.minecraft.world.item.Item;

// 单个物品函数
public class Latex extends Item {
    public Latex()
    {
        super(new Item.Properties().tab(ChangedPlusTab.getInstance()));
    }

}
