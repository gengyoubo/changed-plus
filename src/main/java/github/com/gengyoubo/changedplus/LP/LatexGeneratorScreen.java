package github.com.gengyoubo.changedplus.LP;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class LatexGeneratorScreen extends AbstractContainerScreen<LatexGeneratorContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("changedplus", "textures/gui/latex_generator.png");

    public LatexGeneratorScreen(LatexGeneratorContainer container, Inventory playerInventory, Component title) {
        super(container, playerInventory, title);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTickTime, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        this.blit(poseStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
        // 渲染其他UI元素
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTickTime) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTickTime);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }


}
