package github.com.gengyoubo.changedplus.other;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TranslatableComponent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomLoadingScreen extends Screen {

    // 存储随机消息的数组
    private static final String[] RANDOM_MESSAGES = {
            "message.one",
            "message.two",
            "message.three",
            "message.four",
            "message.five",
            "message.six",
            "message.seven",
           // "message.eight",
           // "message.nine",
           // "message.ten"
    };

    // 获取随机消息的静态方法
    private static String getRandomMessage() {
        Random random = new Random();
        return RANDOM_MESSAGES[random.nextInt(RANDOM_MESSAGES.length)];
    }

    // 存储加载时显示的消息
    private final List<String> loadingMessageLines;

    public CustomLoadingScreen() {
        super(new TranslatableComponent("Message.DoYouKnow." + getRandomMessage()));
        String rawMessage = new TranslatableComponent("Message.DoYouKnow." + getRandomMessage()).getString();
        this.loadingMessageLines = wrapText(rawMessage, 200); // 指定的最大宽度为200
    }

    // 将文本按指定宽度换行
    private List<String> wrapText(String text, int maxWidth) {
        List<String> lines = new ArrayList<>();
        Font font = Minecraft.getInstance().font;
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();
        int lineWidth = 0;

        for (String word : words) {
            int wordWidth = font.width(word);
            if (lineWidth + wordWidth <= maxWidth) {
                currentLine.append(word).append(" ");
                lineWidth += wordWidth;
            } else {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word + " ");
                lineWidth = wordWidth;
            }
        }

        if (!currentLine.toString().isEmpty()) {
            lines.add(currentLine.toString());
        }

        return lines;
    }

    // 重写渲染方法，绘制加载界面的文本
    @Override
    public void render(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        // 偏移坐标轴
        int X_coordinate_offset = 0;
        int Y_coordinate_offset = -100;
        // 获取屏幕宽度和高度
        int screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int screenHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        // 这里你可以指定文本的坐标
        int textX = screenWidth / 2 + X_coordinate_offset;
        int textY = screenHeight / 2 + Y_coordinate_offset;

        // 绘制文本，自动换行
        drawCenteredString(matrixStack, Minecraft.getInstance().font, loadingMessageLines, textX, textY, 0xFFFFFF); // 最后一个参数是颜色
    }

    // 绘制多行居中文本的辅助方法
    private void drawCenteredString(@NotNull PoseStack matrixStack, @NotNull Font font, @NotNull List<String> lines, int x, int y, int color) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            font.drawShadow(matrixStack, line, x - (float) font.width(line) / 2, y + (font.lineHeight + 2) * i, color); // 使用换行距离font.lineHeight + 2
        }
    }
}
