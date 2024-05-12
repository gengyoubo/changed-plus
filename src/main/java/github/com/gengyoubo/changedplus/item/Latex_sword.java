package github.com.gengyoubo.changedplus.item;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.Item;

// 单个物品函数
public class Latex_sword extends SwordItem {

    // 武器的属性
    public Latex_sword() {
        super(Tiers.IRON, 12, 6, new Item.Properties().stacksTo(1).tab(ChangedPlusTab.createChangedPlusTab()).durability(1000)); // 设置材质等级为铁，攻击力为12，攻击速度为-2.4，耐久度为1000
    }
}



