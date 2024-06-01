package github.com.gengyoubo.changedplus.other;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("changedplus")
@Mod.EventBusSubscriber
public class welcome {

    @SubscribeEvent
    public static void playerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getPlayer();
        Level level = player.level;

        Component welcomeMessage = new TranslatableComponent("message.welcome", player.getDisplayName().getString());
        player.sendMessage(welcomeMessage, Util.NIL_UUID);
    }
}
