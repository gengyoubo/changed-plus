package github.com.gengyoubo.changedplus.item.item;

import github.com.gengyoubo.changedplus.other.ChangedPlusTab;
import net.ltxprogrammer.changed.entity.variant.LatexVariant;
import net.ltxprogrammer.changed.process.ProcessTransfur;
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
import org.jetbrains.annotations.NotNull;
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
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
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
        if (event.getSource().getDirectEntity() instanceof LivingEntity attacker) {
            Item item = attacker.getMainHandItem().getItem();

            if (item instanceof long_latex_stick) {
                LatexVariant<?> variant = LatexVariant.getEntityVariant(attacker);
                if (variant != null) {
                    // 如果攻击者是latex player或者是NPC latex，增加攻击力
                    event.setAmount(event.getAmount() + ADDITIONAL_ATTACK_DAMAGE);
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
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        CompoundTag tag = stack.getTag();
        int currentAttackDamage = BASE_ATTACK_DAMAGE;
        if (tag != null && tag.contains("CurrentAttackDamage")) {
            currentAttackDamage = tag.getInt("CurrentAttackDamage");
        }
        tooltip.add(new TranslatableComponent("item.changedplus.latex_sword.tooltip1", currentAttackDamage));
    }

    @SubscribeEvent
    public static void transfur2(LivingHurtEvent event) {
        // 获取受到伤害的实体
        LivingEntity entity = event.getEntityLiving();

        // 获取攻击者
        LivingEntity attacker = (LivingEntity) event.getSource().getDirectEntity();

        // 检查实体是否因为这次伤害而死亡
        if (event.getAmount() >= entity.getMaxHealth()) {
            // 检查攻击者是否持有long latex stick
            if (attacker != null) {
                Item item = attacker.getMainHandItem().getItem();
                if (item instanceof long_latex_stick) {
                    // 获取攻击者的LatexVariant实体变体
                    LatexVariant<?> variant = LatexVariant.getEntityVariant(attacker);
                    if (variant != null) {
                        // 获取实体所在的世界
                        Level level = entity.getLevel();

                        // 阻止实体死亡，改为触发转换进程
                        event.setCanceled(true);
                        ProcessTransfur.transfur(entity, level, variant, false);
                    }
                }
            }
        }
    }
}

