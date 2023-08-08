package minealex.tskulldrops.utils;

import org.bukkit.ChatColor;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;

import minealex.tskulldrops.TSkullDrops;

public class Skulls {
    public static ItemStack getSkull(final Player player) {
        final boolean isNewVersion = Arrays.stream(Material.values()).map((Function<? super Material, ?>)Enum::name).collect(collector).contains("PLAYER_HEAD");
        final Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
        final ItemStack stack = new ItemStack(type, 1);
        if (!isNewVersion) {
            stack.setDurability((short) 3);
        }
        final SkullMeta meta = (SkullMeta) stack.getItemMeta();
        meta.setOwner(player.getName());

        // Cargar los mensajes desde el archivo de configuración
        String skullDisplayName = ChatColor.translateAlternateColorCodes('&',
                TSkullDrops.getInstance().getConfig().getString("messages.skullDisplayName", "&eHead of {player}"));
        String skullLore = ChatColor.translateAlternateColorCodes('&',
                TSkullDrops.getInstance().getConfig().getString("messages.skullLore", "&7Killed by {killer}"));

        // Reemplazar las variables en los mensajes
        skullDisplayName = skullDisplayName.replace("{player}", player.getName());
        skullLore = skullLore.replace("{killer}", player.getKiller().getName());

        // Usar los mensajes cargados en el código
        meta.setDisplayName(skullDisplayName);

        final List<String> lore = new ArrayList<String>();
        lore.add(skullLore);
        meta.setLore(lore);

        stack.setItemMeta((ItemMeta) meta);
        return stack;
    }
    
    // Esta es la definición del collector
    private static final Collector<? super Object, ?, List<Object>> collector = Collectors.toList();
}
