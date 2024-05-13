package github.com.gengyoubo.changedplus.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "changedplus", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class long_latex_steak extends SwordItem {

    public long_latex_steak() {
        super(Tiers.IRON, 27, -3.5F, new Item.Properties().stacksTo(1).tab(ChangedPlusTab.getInstance()).durability(1000));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean flag = super.hurtEnemy(stack, target, attacker); // 调用父类的方法

        // 添加击退效果
        target.knockback(5, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());

        // 添加缓慢效果
        if (!target.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1200, 5));
        }

        return flag;
    }

    @SubscribeEvent
    public static void onTooltipEvent(ItemTooltipEvent event) {
        if (event.getItemStack().getItem() instanceof long_latex_steak) {
            // 添加详细信息到物品的第二个行（下方）
            event.getToolTip().add(new TranslatableComponent("item.changedplus.long_latex_steak.detailed_info"));
        }
    }
}

