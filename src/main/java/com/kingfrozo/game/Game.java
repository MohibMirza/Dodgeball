package com.kingfrozo.game;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

public class Game implements Listener {

    public static Game game;
    public static GameLoop gameLoop;

    private Map<String, GamePlayer> players;
    public boolean started;

    public Game() {
        if(game == null) {
            game = this;
        }
        if(gameLoop == null) {
            // create GameLoop
        }

        players = new HashMap<String, GamePlayer>();
        started = false;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        players.put(event.getPlayer().getName(), new GamePlayer(event.getPlayer()));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        players.remove(event.getPlayer().getName());
    }

    public Map<String, GamePlayer> getPlayers() {
        return players;
    }
}
