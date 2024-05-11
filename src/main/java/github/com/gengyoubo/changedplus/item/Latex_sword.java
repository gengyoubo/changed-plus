package github.com.gengyoubo.changedplus.item;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.Item;
//单个物品函数
public class Latex_sword extends SwordItem {

    //武器的属性
    public Latex_sword() {
        // 调用父类的构造函数，传递铁材料等级、攻击力、攻击速度和其他属性
        super(Tiers.IRON, 12, 6, new Item.Properties().tab(ChangedPlusTab.createChangedPlusTab())); // 设置攻击力为18，攻击速度为3
    }//武器等级，伤害，速度
}



