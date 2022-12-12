package dev.dtech.actionhud;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class PAPI extends PlaceholderExpansion {
    private final ActionHUD plugin;

    public PAPI(ActionHUD plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "actionhud";
    }

    @Override
    public @NotNull String getAuthor() {
        return "dtech";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer p, String params) {
        if (p == null)
            return null;
        String[] par = params.split("_");
        if(par.length>1 && par[0].equals("text")){
            String key = par[1];
            int offset=ActionHUD.plugin.getConfig().getInt("text." + key + ".offset");
            char[] text = PlaceholderAPI.setPlaceholders(p, ActionHUD.plugin.getConfig().getString("text." + key + ".text")).toCharArray();
//            System.out.println(text);
            for (int i = 0; i < text.length; i++) {
                text[i]+=offset;
            }
//            System.out.println(text);
            return String.valueOf(text);
        }
        String key = params;
        double percent = Double.valueOf(me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(p, ActionHUD.plugin.getConfig().getString("hud." + key + ".current"))) / Double.valueOf(me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(p, ActionHUD.plugin.getConfig().getString("hud." + key + ".max")));
        int frame_no = (int) ((ActionHUD.plugin.getConfig().getConfigurationSection("hud." + key + ".frames").getKeys(false).size() - 1) * percent);
        String sprite = ActionHUD.plugin.getConfig().getString("hud." + key + ".frames." + frame_no);
        return sprite;
    }
}
