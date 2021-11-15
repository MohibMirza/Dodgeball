package com.kingfrozo.game;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;

public class GamePlayer implements Listener {
    private Player player;
    private int kills;
    private int lives;

    private Type type;
    private boolean participated; // if played in the game true, if just spectated false

    public enum Type {
        PLAYER,
        SPECTATOR // fly, no item pickup, cancel damage,
    }

    public GamePlayer(Player player) {
        this.player = player;
        kills = 0;
        type = (Game.game.started) ? Type.SPECTATOR : Type.PLAYER;
        lives = (Game.game.started) ? 3 : 0;
        participated = (type == Type.PLAYER) ? true : false;
    }

    public int getKills() {
        return kills;
    }

    public int getLives() {
        return lives;
    }

    public Type getType() {
        return type;
    }

    public boolean getParticipated() {
        return participated;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
