package github.com.gengyoubo.changedplus.LP;

import github.com.gengyoubo.changedplus.other.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class latex_generator extends BlockEntity {

    public latex_generator(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.LATEX_GENERATOR, pos, state);
    }

    public static BlockEntity create(BlockPos pos, BlockState state) {
        return new latex_generator(pos, state);
    }
    private int energyStored;
}
