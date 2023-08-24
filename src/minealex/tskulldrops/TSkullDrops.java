package minealex.tskulldrops;

import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;
import java.io.IOException;

import minealex.tskulldrops.commands.Reload;
import minealex.tskulldrops.listener.KillListener;
import minealex.tskulldrops.listener.SkullInteractListener;

@SuppressWarnings("unused")
public final class TSkullDrops extends JavaPlugin {
    private static TSkullDrops instance;
    
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig(); // Esto asegura que se cree el archivo config.yml si no existe
        createConfig(); // Llama al método para crear la configuración
        
        // Resto de tu código de inicialización
        this.getServer().getPluginManager().registerEvents(new KillListener(), this);
        this.getServer().getPluginManager().registerEvents(new SkullInteractListener(), this);
        getCommand("tsd").setExecutor(new Reload(this));
    }

    @Override
    public void onDisable() {
    }

    public static TSkullDrops getInstance() {
        return instance;
    }
    
    // Método para crear el archivo config.yml
    private void createConfig() {
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }
    
    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
