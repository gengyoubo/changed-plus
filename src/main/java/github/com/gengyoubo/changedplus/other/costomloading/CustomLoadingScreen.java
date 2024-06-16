package github.com.gengyoubo.changedplus.other.costomloading;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class CustomLoadingScreen extends Screen {

    // 随机加载消息
    private static final String[] RANDOM_MESSAGES = {
            "message.one",
            "message.two",
            "message.three",
            "message.four",
            "message.five",
            "message.six",
            "message.seven",
            "message.eight",
            // "message.nine",
            // "message.ten"
    };

    // 英文最大宽度
    private static final int MAX_WIDTH_ENGLISH = 300;
    // 中文最大宽度
    private static final int MAX_WIDTH_CHINESE = 30;

    // 上一次展示的消息
    private static String lastDisplayedMessage = "";

    // 加载消息行列表
    private final List<String> loadingMessageLines;

    // 构造函数
    public CustomLoadingScreen() {
        super(new TranslatableComponent(getDisplayMessageKey()));
        String rawMessage = new TranslatableComponent(getDisplayMessageKey()).getString();
        int maxWidth = getMaxWidth();
        this.loadingMessageLines = wrapText(rawMessage, maxWidth);
    }

    // 获取显示消息的键
    private static String getDisplayMessageKey() {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.getMonthValue() == 6 && currentDate.getDayOfMonth() == 16) {
            return "Message.HappyBirthday!";
        } else {
            return "Message.DoYouKnow." + getRandomMessage();
        }
    }

    // 获取随机消息
    private static String getRandomMessage() {
        Random random = new Random();
        String message;
        do {
            message = RANDOM_MESSAGES[random.nextInt(RANDOM_MESSAGES.length)];
        } while (message.equals(lastDisplayedMessage)); // 确保随机消息不等于上一次展示的消息
        lastDisplayedMessage = message; // 更新上一次展示的消息
        return message;
    }

    // 获取最大宽度
    private int getMaxWidth() {
        // 获取当前语言
        String language = Minecraft.getInstance().getLanguageManager().getSelected().getCode();
        if (language.equals("zh_cn")) {
            return MAX_WIDTH_CHINESE;
        } else {
            return MAX_WIDTH_ENGLISH;
        }
    }

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
                lines.add(currentLine.toString().trim()); // trim() 方法用于移除字符串两端的空格
                currentLine = new StringBuilder(word + " ");
                lineWidth = wordWidth;
            }
        }

        if (!currentLine.toString().isEmpty()) {
            lines.add(currentLine.toString().trim());
        }

        return lines;
    }

    // 渲染方法
    @Override
    public void render(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        int X_coordinate_offset = 0;
        int Y_coordinate_offset = -100;
        int screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int screenHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        int textX = screenWidth / 2 + X_coordinate_offset;
        int textY = screenHeight / 2 + Y_coordinate_offset;

        drawCenteredString(matrixStack, Minecraft.getInstance().font, loadingMessageLines, textX, textY);
    }

    // 绘制居中文本
    private void drawCenteredString(@NotNull PoseStack matrixStack, @NotNull Font font, @NotNull List<String> lines, int x, int y) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            font.drawShadow(matrixStack, line, x - (float) font.width(line) / 2, y + (font.lineHeight + 2) * i, 16777215);
        }
    }
}
