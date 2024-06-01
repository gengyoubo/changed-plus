package github.com.gengyoubo.changedplus.item.item;

import github.com.gengyoubo.changedplus.other.ChangedPlusTab;
import net.ltxprogrammer.changed.entity.variant.LatexVariant;
import net.ltxprogrammer.changed.process.ProcessTransfur;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

@Mod.EventBusSubscriber
public class Latex_sword extends SwordItem {
    private static final int BASE_ATTACK_DAMAGE = 10;
    private static final int ADDITIONAL_ATTACK_DAMAGE = 35;


    public Latex_sword() {
        super(Tiers.NETHERITE, BASE_ATTACK_DAMAGE, 0,
                new Item.Properties().stacksTo(7).tab(ChangedPlusTab.getInstance()).durability(1000));
    }

    @SubscribeEvent
    public static void onEntityHurt(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof LivingEntity attacker) {
            Item item = attacker.getMainHandItem().getItem();

            if (item instanceof Latex_sword) {
                LatexVariant<?> variant = LatexVariant.getEntityVariant(attacker);
                if (variant != null) {

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
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        CompoundTag tag = stack.getTag();
        int currentAttackDamage = BASE_ATTACK_DAMAGE;
        if (tag != null && tag.contains("CurrentAttackDamage")) {
            currentAttackDamage = tag.getInt("CurrentAttackDamage");
        }
        tooltip.add(new TranslatableComponent("item.changedplus.latex_sword.tooltip", 5 + currentAttackDamage));
    }

    @SubscribeEvent
    public static void transfur1(LivingHurtEvent event) {

        LivingEntity entity = event.getEntityLiving();


        LivingEntity attacker = (LivingEntity) event.getSource().getDirectEntity();


        if (event.getAmount() >= entity.getMaxHealth()) {

            if (attacker != null) {
                Item item = attacker.getMainHandItem().getItem();
                if (item instanceof Latex_sword) {

                    LatexVariant<?> variant = LatexVariant.getEntityVariant(attacker);
                    if (variant != null) {

                        Level level = entity.getLevel();


                        event.setCanceled(true);
                        ProcessTransfur.transfur(entity, level, variant, false);
                    }
                }
            }
        }
    }
}

