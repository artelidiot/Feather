package me.artel.feather.messaging;

import com.google.common.collect.ImmutableList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    private static final char colorChar = '\u00A7';
    private static final Pattern colorPattern = Pattern.compile("&" + "([A-Fa-f0-9])");
    private static final Pattern formatPattern = Pattern.compile("&" + "([KLMNORklmnor])");
    private static final ImmutableList<Pattern> hexPatterns = new ImmutableList.Builder<Pattern>()
            .add(Pattern.compile("&#" + "([A-Fa-f0-9]{6})" + "&"))
            .add(Pattern.compile("<#" + "([A-Fa-f0-9]{6})" + ">"))
            .add(Pattern.compile("\\{#" + "([A-Fa-f0-9]{6})" + "}"))
            .build();

    /**
     * Parse all standard colors (&1, &2, etc.), formatting (&k, &l, etc.) and hex colors (&#RRGGBB&) in a String
     *
     * @param input - The String to be processed
     * @return - The processed String containing standard colors, formatting and hex colors
     */
    public static String parseAll(String input) {
        String processed = input;

        processed = parseHexColors(processed);
        processed = parseColors(processed);
        processed = parseFormatting(processed);

        return processed;
    }

    /**
     * Parse all standard colors (&1, &2, etc.) in a String
     *
     * @param input - The String to be processed
     * @return - The processed String containing standard colors
     */
    public static String parseColors(String input) {
        Matcher matcher = colorPattern.matcher(input);
        StringBuilder builder = new StringBuilder(input.length());

        while (matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(builder, colorChar + group.charAt(0) + "");
        }

        return matcher.appendTail(builder).toString();
    }

    /**
     * Parse all formatting (&k, &l, etc.) in a String
     *
     * @param input - The String to be processed
     * @return - The processed String containing formatting
     */
    public static String parseFormatting(String input) {
        Matcher matcher = formatPattern.matcher(input);
        StringBuilder builder = new StringBuilder(input.length());

        while (matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(builder, colorChar + group.charAt(0) + "");
        }

        return matcher.appendTail(builder).toString();
    }

    /**
     * Parse all hex colors (&#RRGGBB&) in a String
     *
     * @param input - The String to be processed
     * @return - The processed String containing hex colors
     */
    public static String parseHexColors(String input) {
        String processed = input;

        for (Pattern pattern : hexPatterns) {
            processed = parseHexColors(processed, pattern);
        }

        return processed;
    }

    /**
     * Parse hex colors with a desired Pattern (&#RRGGBB&, <#RRGGBB>, etc.)
     *
     * @param input - The String to be processed
     * @param pattern - The Pattern to match against
     * @return - The processed String containing hex colors
     */
    public static String parseHexColors(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        StringBuilder builder = new StringBuilder(input.length() + 4 * 8);

        while (matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(builder, colorChar + "x"
                    + colorChar + group.charAt(0) + colorChar + group.charAt(1)
                    + colorChar + group.charAt(2) + colorChar + group.charAt(3)
                    + colorChar + group.charAt(4) + colorChar + group.charAt(5));
        }

        return matcher.appendTail(builder).toString();
    }
}