package github.com.gengyoubo.changedplus;

import net.minecraft.Util;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("changedplus")
@Mod.EventBusSubscriber
//public 公用的函数
//class 属性
//Main 函数的名字，这里是文件的名字，对应了Main.java
public class Main  {

    @SubscribeEvent
    //static 静态函数
    //void 从这里开始调用
    public static void playerJoinWorld(PlayerEvent.PlayerLoggedInEvent event){
        Player player = event.getPlayer();
        Level level =player.level;
        //从这里写进入世界的时候的信息
        //player.sendMessage(new TextComponent("信息"), Util.NIL_UUID);
        player.sendMessage(new TextComponent("欢迎来到胶兽的世界!"+player.getDisplayName().getString()+",你准备好被兽化了吗？"), Util.NIL_UUID);
        //如果想添加玩家的名称时，需要在"的外面写+player.getDisplayName().getString()+
    }
}
