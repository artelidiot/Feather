package me.artel.feather.metrics;

import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class bStats {
    @Getter
    private final JavaPlugin plugin;
    @Getter
    private final int pluginID;
    @Getter
    private final Metrics metrics;

    public bStats(JavaPlugin plugin, int pluginID) {
        this.plugin = plugin;
        this.pluginID = pluginID;

        metrics = new Metrics(plugin, pluginID);
    }
}