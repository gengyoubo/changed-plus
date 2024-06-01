package github.com.gengyoubo.changedplus.LP;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class LatexGeneratorBlock {

    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (hand == InteractionHand.MAIN_HAND) { // 检查玩家是否使用了主手
            if (!world.isClientSide()) {
                BlockEntity tileEntity = world.getBlockEntity(pos);
                if (tileEntity instanceof latex_generator) {
                    System.out.println("Right-clicked on latex_generator"); // 注意这里是 System
                    player.openMenu((MenuProvider) tileEntity);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }
}
