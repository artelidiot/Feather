package me.artel.feather.integration;

import lombok.Getter;
import lombok.Setter;
import me.artel.feather.server.VersionUtils.CommonVersions;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is a wrapper for Bukkit initialization methods
 */
public class Wrapper {
    private static Wrapper instance;
    @Getter @Setter
    private static JavaPlugin plugin;

    public static Wrapper init(JavaPlugin plugin) {
        if (instance == null) {
            instance = new Wrapper();
        }

        if (getPlugin() == null) {
            setPlugin(plugin);
        }

        return instance;
    }

    /**
     * Set the minimum server version supported by your integrated plugin.
     * @apiNote Automatically disables the plugin if the server version is too old.
     *
     * @return - The {@link Wrapper} class for future registration methods to be called.
     */
    public Wrapper setMinimumSupportedVersion(CommonVersions version) {

        // TODO

        return instance;
    }

    /**
     * Method to register a command on the server.
     *
     * @param command - The command name found in plugin.yml.
     * @param commandInterface - The class containing the command executor and tab completion implementations.
     *
     * @return - The {@link Wrapper} class for future registration methods to be called.
     */
    public <T extends CommandExecutor & TabCompleter> Wrapper registerCommand(String command, T commandInterface) {
        PluginCommand pluginCommand = plugin.getCommand(command);

        if (pluginCommand == null) {
            Bukkit.getPluginManager().disablePlugin(plugin);
        } else {
            pluginCommand.setExecutor(commandInterface);
            pluginCommand.setTabCompleter(commandInterface);
        }

        return instance;
    }

    /**
     * Method to register a listener on the server.
     *
     * @param listener - The class containing the listener implementation.
     *
     * @return - The {@link Wrapper} class for future registration methods to be called.
     */
    public <T extends Listener> Wrapper registerListener(T listener) {
        Bukkit.getPluginManager().registerEvents(listener, plugin);

        return instance;
    }
}