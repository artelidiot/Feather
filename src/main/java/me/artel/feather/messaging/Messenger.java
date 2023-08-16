package me.artel.feather.messaging;

import com.google.common.collect.ImmutableList;
import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.bukkit.ChatColor.COLOR_CHAR;

public class Messenger {
    private static final ImmutableList<Pattern> hexPatterns = new ImmutableList.Builder<Pattern>()
            .add(Pattern.compile("&#" + "([A-Fa-f0-9]{6})"))
            .add(Pattern.compile("<#" + "([A-Fa-f0-9]{6})" + ">"))
            .add(Pattern.compile("\\{#" + "([A-Fa-f0-9]{6})" + "}"))
            .build();

    public static void send(Player player, String message, Object... replacements) {
        player.sendMessage(parseAllColors(replacements == null ? message : MessageFormat.format(message, replacements)));
    }

    public static void send(CommandSender sender, String message, Object... replacements) {
        sender.sendMessage(parseAllColors(replacements == null ? message : MessageFormat.format(message, replacements)));
    }

    /**
     * Send a MineDown parsed String to a {@link Player}.
     *
     * @param player - The recipient of the message.
     * @param message - The string to parse and send to the recipient.
     * @param replacements - Optional replacements with {@link MessageFormat#format(String, Object...)}.
     */
    public static void sendMD(Player player, String message, Object... replacements) {
        player.spigot().sendMessage(parseMD(replacements == null ? message : MessageFormat.format(message, replacements)));
    }

    /**
     * Send a MineDown parsed String to a {@link CommandSender}.
     *
     * @param sender - The recipient of the message.
     * @param message - The string to parse and send to the recipient.
     * @param replacements - Optional replacements with {@link MessageFormat#format(String, Object...)}.
     */
    public static void sendMD(CommandSender sender, String message, Object... replacements) {
        sender.spigot().sendMessage(parseMD(replacements == null ? message : MessageFormat.format(message, replacements)));
    }

    /**
     * Parse a String for MineDown syntax.
     *
     * @param input - The string to parse for any MineDown syntax.
     * @return - A sequence of {@link BaseComponent} which can be parsed by Spigot #spigot().sendMessage() methods.
     */
    public static BaseComponent[] parseMD(String input) {
        return MineDown.parse(input);
    }

    public static String parseAllColors(String input) {
        String processed = input;

        processed = parseHexColors(processed);
        processed = parseLegacyColors(processed);

        return processed;
    }

    public static String parseLegacyColors(String input) {
        String processed = input;

        processed = ChatColor.translateAlternateColorCodes('&', processed);

        return processed;
    }

    public static String parseHexColors(String input) {
        String processed = input;

        for (Pattern pattern : hexPatterns) {
            processed = parseHexColors(processed, pattern);
        }

        return processed;
    }

    public static String parseHexColors(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        StringBuilder builder = new StringBuilder(input.length() + 4 * 8);

        while (matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(builder, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0)
                    + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2)
                    + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4)
                    + COLOR_CHAR + group.charAt(5));
        }

        return matcher.appendTail(builder).toString();
    }
}