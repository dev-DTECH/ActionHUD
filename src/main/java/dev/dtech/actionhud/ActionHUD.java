package dev.dtech.actionhud;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ActionHUD extends JavaPlugin implements Listener {
    static Plugin plugin;
    @Override
    public void onEnable() {
        this.plugin=this;
        saveDefaultConfig();
        this.getCommand("ahudreload").setExecutor(new Commands());
        // Plugin startup logic
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            /*
             * We register the EventListener here, when PlaceholderAPI is installed.
             * Since all events are in the main class (this class), we simply use "this"
             */
//            Bukkit.getPluginManager().registerEvents(this, this);
            new PAPI(this).register();
        } else {
            /*
             * We inform about the fact that PlaceholderAPI isn't installed and then
             * disable this plugin to prevent issues.
             */
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        ActionBar.start();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
