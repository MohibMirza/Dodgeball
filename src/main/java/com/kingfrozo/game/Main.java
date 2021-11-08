package com.kingfrozo.game;

import com.kingfrozo.game.events.SnowballHitHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class Main extends JavaPlugin {

    private static Main plugin;
    private static Random random;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        random = new Random();
        // System.out.println("\n\n\n\n\n\ntest\n\n\n\n\n\n");
        getServer().getPluginManager().registerEvents(new SnowballHitHandler(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static Random getRandom() {
        return random;
    }
}
