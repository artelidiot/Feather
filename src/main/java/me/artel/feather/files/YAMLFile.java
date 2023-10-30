package me.artel.feather.files;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

// TODO: Create "FileBuilder" supporting various formats such as YAML, JSON, TOML, etc.
public class YAMLFile {
    @Getter
    private static YAMLFile instance;
    @Getter
    private final JavaPlugin plugin;
    @Getter
    private final String fileName;
    @Getter
    private final File file;
    @Getter
    private final YamlConfiguration yaml;

    public YAMLFile(JavaPlugin plugin, String fileName) {
        this(plugin, new File(plugin.getDataFolder(), fileName));
    }

    public YAMLFile(JavaPlugin plugin, File file) {
        instance = this;

        this.plugin = plugin;
        this.fileName = file.getName();
        this.file = file;
        this.yaml = YamlConfiguration.loadConfiguration(file);

        register();
    }

    public void register() {
        save();
        reload();
    }

    public void save() {
        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }
    }

    @SneakyThrows
    public void reload() {
        yaml.load(file);
    }
}