package dev.dtech.actionhud;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLib;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.injector.PacketConstructor;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;

public class ActionBar {
    static void start(){
        new BukkitRunnable(){
            @Override
            public void run() {

//                String msg = String.format("%s",ActionHUD.plugin.getConfig().getString(""))


                for(Player p: Bukkit.getOnlinePlayers()){
//                    p.getAttribute(Attribute.GENERIC_ARMOR).getValue();
//                    p.send
                    String hud=PlaceholderAPI.setPlaceholders(p,ActionHUD.plugin.getConfig().getString("format"));

//
//                    for(String key: ActionHUD.plugin.getConfig().getConfigurationSection("hud").getKeys(false)) {
//                        double percent = Double.valueOf(PlaceholderAPI.setPlaceholders(p,ActionHUD.plugin.getConfig().getString("hud."+ key+ ".current")))/Double.valueOf(PlaceholderAPI.setPlaceholders(p,ActionHUD.plugin.getConfig().getString("hud."+ key+ ".max")));
//                        int frame_no = (int) ((ActionHUD.plugin.getConfig().getConfigurationSection("hud."+ key+ ".frames").getKeys(false).size()-1)*percent);
//                        String sprite = ActionHUD.plugin.getConfig().getString("hud."+ key+ ".frames."+frame_no);
//                        System.out.println("hud."+ key+ ".frames."+frame_no);
//                        System.out.println(sprite);
//                        hud = hud.replace("%actionhud_"+key+"%",sprite);
//                        System.out.println(hud);
//                    }

                    PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.SYSTEM_CHAT);
                    packet.getStrings().write(0,WrappedChatComponent.fromText(hud).getJson());
                    packet.getBooleans().write(0,true);
                    try {
                        ProtocolLibrary.getProtocolManager().sendServerPacket(p,packet);
                    } catch (Exception e) {
                        e.printStackTrace();
//                        throw new RuntimeException(e);
                    }
                }
            }
        }.runTaskTimerAsynchronously(ActionHUD.plugin,0,ActionHUD.plugin.getConfig().getInt("refresh-rate"));
    }
}
