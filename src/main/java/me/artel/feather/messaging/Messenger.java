package me.artel.feather.messaging;

import de.themoep.minedown.adventure.MineDown;
import lombok.Getter;
import me.artel.feather.integration.Wrapper;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @apiNote You must initialize your plugin with {@link Wrapper} to use this class
 */
public class Messenger {
    @Getter
    private static final BukkitAudiences audiences = BukkitAudiences.create(Wrapper.getPlugin());

    /**
     * Send a MineDown parsed string to any desired Bukkit Audience
     *
     * @param audience - Any audience present in Bukkit
     * @param message - The message to be sent
     */
    public static void send(Audience audience, String message) {
        Component processed = MineDown.parse(message);

        audience.sendMessage(processed);
    }

    /**
     * Send a MineDown parsed string to a Player
     *
     * @param player - The recipient (Player) of the message
     * @param message - The message to be sent
     */
    public static void send(Player player, String message) {
        send(audiences.player(player), message);
    }

    /**
     * Send a MineDown parsed string to a CommandSender
     *
     * @param commandSender - The recipient (CommandSender) of the message
     * @param message - The message to be sent
     */
    public static void send(CommandSender commandSender, String message) {
        send(audiences.sender(commandSender), message);
    }

    /**
     * Send a MineDown parsed string to Console
     *
     * @param message - The message to be sent
     */
    public static void sendConsole(String message) {
        send(audiences.console(), message);
    }
}