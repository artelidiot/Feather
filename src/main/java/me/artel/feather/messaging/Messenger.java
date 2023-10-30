package me.artel.feather.messaging;

import de.themoep.minedown.adventure.MineDown;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Messenger {

    /**
     * Parse a String through MineDown into an Adventure {@link Component}
     *
     * @param input - The String to be parsed
     * @return - The MineDown parsed Adventure Component
     */
    public static Component parseMineDown(String input) {
        return MineDown.parse(input);
    }

    /**
     * Send a MineDown parsed String to a Player
     *
     * @param player - The recipient (Player) of the message
     * @param message - The message to be sent
     */
    public static void send(Player player, String message) {
        player.sendMessage(parseMineDown(message));
    }

    /**
     * Send a MineDown parsed String to a CommandSender
     *
     * @param commandSender - The recipient (CommandSender) of the message
     * @param message - The message to be sent
     */
    public static void send(CommandSender commandSender, String message) {
        commandSender.sendMessage(parseMineDown(message));
    }

    /**
     * Send a MineDown parsed String to Console
     *
     * @param message - The message to be sent
     */
    public static void sendConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(parseMineDown(message));
    }
}