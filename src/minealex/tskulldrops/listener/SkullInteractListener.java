package minealex.tskulldrops.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import minealex.tskulldrops.TSkullDrops;

@SuppressWarnings("unused")
public class SkullInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Player) {
            Player targetPlayer = (Player) event.getRightClicked();
            if (event.getPlayer().getItemInHand().getType() == Material.SKULL_ITEM) {
                executeConsoleCommands(event.getPlayer(), targetPlayer);
            }
        }
    }

    private void executeConsoleCommands(Player player, Player targetPlayer) {
        for (String command : TSkullDrops.getInstance().getConfig().getStringList("skullInteractCommands")) {
            command = command.replace("{player}", player.getName())
                             .replace("{target}", targetPlayer.getName());
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
        }
    }
}
