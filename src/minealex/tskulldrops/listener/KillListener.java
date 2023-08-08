package minealex.tskulldrops.listener;

import org.bukkit.event.EventHandler;
import minealex.tskulldrops.utils.Skulls;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.Listener;

public class KillListener implements Listener
{
    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player && event.getEntity().getPlayer() instanceof Player) {
            final Player player = event.getEntity().getPlayer();
            event.getDrops().add(Skulls.getSkull(player));
        }
    }
}
