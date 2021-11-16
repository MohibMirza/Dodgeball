package com.kingfrozo.game.events;

import com.kingfrozo.game.GamePlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

public class GamePlayerJoinLeaveHandler implements Listener {

    public static Map<String, GamePlayer> gamePlayers = new HashMap<String, GamePlayer>();

    @EventHandler
    public void createPlayer(PlayerLoginEvent event){
        GamePlayer gp = new GamePlayer(event.getPlayer());
        gamePlayers.put(event.getPlayer().getName(), gp);

    }

    @EventHandler
    public void removePlayer(PlayerQuitEvent event){
        gamePlayers.remove(event.getPlayer().getName());

    }





    // GamePlayer is an object
    // What does constructor do??


}
