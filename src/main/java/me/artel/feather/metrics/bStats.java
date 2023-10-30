package me.artel.feather.metrics;

import lombok.Getter;
import me.artel.feather.integration.Wrapper;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * TODO: Replace this with a unified Builder class which supports more metrics systems.
 * @apiNote You must initialize your plugin with {@link Wrapper} to use this class
 */
public class bStats {
    @Getter
    private final int pluginID;
    @Getter
    private final Metrics metrics;

    public bStats(int pluginID) {
        this.pluginID = pluginID;

        metrics = new Metrics(Wrapper.getPlugin(), pluginID);
    }
}