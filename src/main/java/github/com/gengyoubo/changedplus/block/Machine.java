package github.com.gengyoubo.changedplus.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.ObjectHolder;

public class Machine extends Block {
    @ObjectHolder("changedplus:machine")
    public static final Machine BLOCK = null;

    public Machine() {
        super(BlockBehaviour.Properties.of(Material.METAL)
                .strength(5.0f, 6.0f)
                .requiresCorrectToolForDrops());
    }
}
