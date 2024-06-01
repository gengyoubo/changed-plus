package github.com.gengyoubo.changedplus.other;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;


public class ChangedPlusTab extends CreativeModeTab {

    private ChangedPlusTab() {
        super("changedplus");
    }


    public static final ChangedPlusTab INSTANCE = new ChangedPlusTab();


    public static ChangedPlusTab getInstance() {
        return INSTANCE;
    }


    @Override
    public @NotNull ItemStack makeIcon() {

        return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("changedplus", "latex_sword")));
    }
}




