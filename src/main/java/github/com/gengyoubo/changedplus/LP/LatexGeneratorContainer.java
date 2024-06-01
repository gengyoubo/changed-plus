package github.com.gengyoubo.changedplus.LP;

import github.com.gengyoubo.changedplus.other.ModEventSubscriber.ModContainerTypes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class LatexGeneratorContainer extends AbstractContainerMenu {
        public LatexGeneratorContainer(int windowId, Inventory playerInventory) {
                super(ModContainerTypes.LATEX_GENERATOR.get(), windowId);


                this.addSlot(new Slot(playerInventory, 0, 80, 45) {
                        @Override
                        public boolean mayPlace(ItemStack stack) {

                                return true;
                        }
                });


                for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 9; j++) {
                                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
                        }
                }
                for (int i = 0; i < 9; i++) {
                        this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
                }
        }

        @Override
        public boolean stillValid(Player player) {

                return true;
        }


}
