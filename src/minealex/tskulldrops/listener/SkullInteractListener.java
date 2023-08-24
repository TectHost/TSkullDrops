package minealex.tskulldrops.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import minealex.tskulldrops.TSkullDrops;

public class SkullInteractListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = event.getEntity().getKiller();

        if (killer != null) {
            for (String command : TSkullDrops.getInstance().getConfig().getStringList("skullInteractCommands")) {
                command = command.replace("{player}", killer.getName())
                        .replace("{target}", victim.getName());
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
            }
        }
    }
}