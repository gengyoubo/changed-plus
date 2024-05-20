package github.com.gengyoubo.changedplus.item;

import net.ltxprogrammer.changed.entity.variant.LatexVariant;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import java.util.List;

@Mod.EventBusSubscriber
public class Latex_sword extends SwordItem {
    private static final int BASE_ATTACK_DAMAGE = 10; // 基础攻击力
    private static final int ADDITIONAL_ATTACK_DAMAGE = 25; // 额外攻击力

    public Latex_sword() {
        super(Tiers.NETHERITE, BASE_ATTACK_DAMAGE, 0,
                new Item.Properties().stacksTo(7).tab(ChangedPlusTab.getInstance()).durability(1000));
    }

    @SubscribeEvent
    public static void onEntityHurt(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof LivingEntity) {
            LivingEntity attacker = (LivingEntity) event.getSource().getDirectEntity();
            Item item = attacker.getMainHandItem().getItem();

            if (item instanceof Latex_sword) {
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
            if (stack.getItem() instanceof Latex_sword) {
                updateAttackDamage(stack, event.player);
            }
        }
    }

    private static void updateAttackDamage(ItemStack stack, LivingEntity entity) {
        int currentAttackDamage = calculateCurrentAttackDamage(entity);

        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt("CurrentAttackDamage", currentAttackDamage);
        stack.setTag(tag);
    }

    private static int calculateCurrentAttackDamage(LivingEntity entity) {
        int currentAttackDamage = BASE_ATTACK_DAMAGE;
        if (LatexVariant.getEntityVariant(entity) != null) {
            currentAttackDamage += ADDITIONAL_ATTACK_DAMAGE;
        }
        return currentAttackDamage;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        CompoundTag tag = stack.getTag();
        int currentAttackDamage = BASE_ATTACK_DAMAGE;
        if (tag != null && tag.contains("CurrentAttackDamage")) {
            currentAttackDamage = tag.getInt("CurrentAttackDamage");
        }
        tooltip.add(new TranslatableComponent("item.changedplus.latex_sword.tooltip",5 + currentAttackDamage));
    }
}
