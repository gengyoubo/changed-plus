package github.com.gengyoubo.changedplus.other.ModEventSubscriber;

import github.com.gengyoubo.changedplus.LP.LatexGeneratorContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "changedplus", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModContainerTypes {
    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, "changedplus");

    public static final RegistryObject<MenuType<LatexGeneratorContainer>> LATEX_GENERATOR = CONTAINER_TYPES.register("latex_generator",
            () -> IForgeMenuType.create((windowId, inv, data) -> new LatexGeneratorContainer(windowId, inv)));
}
