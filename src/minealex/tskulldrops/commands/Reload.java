package minealex.tskulldrops.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import minealex.tskulldrops.TSkullDrops;

public class Reload implements CommandExecutor {
    @SuppressWarnings("unused")
	private final Plugin plugin;

    public Reload(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && !sender.hasPermission("tsd.reload")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', TSkullDrops.getInstance().getConfig().getString("messages.noPermission", "&cYou don't have permission to use this command.")));
            return true;
        }

        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            // Recargar la configuración
            TSkullDrops.getInstance().reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', TSkullDrops.getInstance().getConfig().getString("messages.reloadSuccess", "&aTSkullDrops configuration reloaded.")));
            return true;
        }

        return false;
    }
}
