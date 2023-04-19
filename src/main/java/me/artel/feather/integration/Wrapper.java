package me.artel.feather.integration;

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

    public static Wrapper init() {
        if (instance == null) {
            instance = new Wrapper();
        }

        return instance;
    }

    /**
     * Method to register a command on the server.
     *
     * @param plugin - The plugin the command will be attached to.
     * @param command - The command name found in plugin.yml.
     * @param commandInterface - The class containing the command executor and tab completion implementations.
     *
     * @return - The {@link Wrapper} class for future registration methods to be called.
     */
    public <T extends CommandExecutor & TabCompleter> Wrapper registerCommand(JavaPlugin plugin, String command, T commandInterface) {
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
     * @param plugin - The plugin the listener will be attached to.
     * @param listener - The class containing the listener implementation.
     *
     * @return - The {@link Wrapper} class for future registration methods to be called.
     */
    public <T extends Listener> Wrapper registerListener(JavaPlugin plugin, T listener) {
        Bukkit.getPluginManager().registerEvents(listener, plugin);

        return instance;
    }
}