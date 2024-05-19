package github.com.gengyoubo.changedplus.item;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.nbt.CompoundTag;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber
public class Latex_sword extends SwordItem {
    private static final int BASE_ATTACK_DAMAGE = 7; // 基础攻击力
    private static final int ATTACK_DAMAGE_BOOST = 1; // 每次击杀提升的攻击力

    public Latex_sword() {
        super(Tiers.IRON, BASE_ATTACK_DAMAGE, 0, new Item.Properties().stacksTo(1).tab(ChangedPlusTab.getInstance()).durability(1000));
        MinecraftForge.EVENT_BUS.register(this); // 注册事件监听器
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) { // 如果击杀者是玩家
            ItemStack mainHandItem = player.getMainHandItem();
            if (mainHandItem.getItem() instanceof Latex_sword) { // 如果使用的是你的这把剑
                CompoundTag tag = mainHandItem.getOrCreateTag();
                int killCount = tag.getInt("KillCount"); // 获取当前击杀数
                killCount += 1; // 增加击杀数
                tag.putInt("KillCount", killCount); // 存储新的击杀数
                int newAttackDamage = BASE_ATTACK_DAMAGE + killCount * ATTACK_DAMAGE_BOOST; // 计算新的攻击力
                System.out.println("当前攻击力：" + newAttackDamage);
            }
        }
    }

    public static float getCustomAttackDamage(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains("KillCount")) {
            int killCount = tag.getInt("KillCount");
            return BASE_ATTACK_DAMAGE + killCount * ATTACK_DAMAGE_BOOST;
        }
        return BASE_ATTACK_DAMAGE;
    }

    @Override
    public float getDamage() {
        return BASE_ATTACK_DAMAGE;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, net.minecraft.world.entity.LivingEntity target, net.minecraft.world.entity.LivingEntity attacker) {
        float attackDamage = getCustomAttackDamage(stack);
        target.hurt(net.minecraft.world.damagesource.DamageSource.playerAttack((Player) attacker), attackDamage);
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(new TranslatableComponent("item.changedplus.latex_sword.tooltip", getCustomAttackDamage(stack)));
    }
}
