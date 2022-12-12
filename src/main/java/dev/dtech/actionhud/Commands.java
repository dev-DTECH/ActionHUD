package dev.dtech.actionhud;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player && !sender.hasPermission("actionhud.reload"))
        {
            sender.sendMessage("§f[§6Action§3HUD§f] You don't have permission!");
            return true;
        }
        ActionHUD.plugin.reloadConfig();
        sender.sendMessage("§f[§6Action§3HUD§f] §cActionHUD reloaded successfully!");
        return true;
    }
}
