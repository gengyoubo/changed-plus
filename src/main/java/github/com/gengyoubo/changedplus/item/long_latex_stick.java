package github.com.gengyoubo.changedplus.item;

import net.ltxprogrammer.changed.entity.variant.LatexVariant;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Mod.EventBusSubscriber(modid = "changedplus", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class long_latex_stick extends SwordItem {
    private static final int BASE_ATTACK_DAMAGE = 25; // 基础攻击力
    private static final int ADDITIONAL_ATTACK_DAMAGE = 70; // 额外攻击力

    public long_latex_stick() {
        super(Tiers.NETHERITE, BASE_ATTACK_DAMAGE, -3.5F, new Item.Properties().stacksTo(1).tab(ChangedPlusTab.getInstance()).durability(1000));
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
        if (event.getItemStack().getItem() instanceof long_latex_stick) {
            // 添加详细信息到物品的第二个行（下方）
            event.getToolTip().add(new TranslatableComponent("item.changedplus.long_latex_stick.detailed_info"));
        }
    }

    @SubscribeEvent
    public static void onEntityHurt(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof LivingEntity) {
            LivingEntity attacker = (LivingEntity) event.getSource().getDirectEntity();
            Item item = attacker.getMainHandItem().getItem();

            if (item instanceof long_latex_stick) {
                LatexVariant<?> variant = LatexVariant.getEntityVariant(attacker);
                if (variant != null) {
                    // 如果攻击者是latex player或者是NPC latex，增加攻击力
                    event.setAmount(event.getAmount() + ADDITIONAL_ATTACK_DAMAGE);
                } else {
                    // 如果不是，保持原攻击力
                    // 这里不需要做任何操作，因为攻击力已经是默认值
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            ItemStack stack = event.player.getMainHandItem();
            if (stack.getItem() instanceof long_latex_stick) {
                updateAttackDamage(stack, event.player);
            }
        }
    }

    private static void updateAttackDamage(ItemStack stack, LivingEntity entity) {
        int currentAttackDamage = BASE_ATTACK_DAMAGE;

        if (LatexVariant.getEntityVariant(entity) != null) {
            currentAttackDamage += ADDITIONAL_ATTACK_DAMAGE;
        }

        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt("CurrentAttackDamage", 5 + currentAttackDamage);
        stack.setTag(tag);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        CompoundTag tag = stack.getTag();
        int currentAttackDamage = BASE_ATTACK_DAMAGE;
        if (tag != null && tag.contains("CurrentAttackDamage")) {
            currentAttackDamage = tag.getInt("CurrentAttackDamage");
        }
        tooltip.add(new TranslatableComponent("item.changedplus.latex_sword.tooltip1", currentAttackDamage));
    }
}
