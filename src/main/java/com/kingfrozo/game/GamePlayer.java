package com.kingfrozo.game;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;
//test
public class GamePlayer {
    public static enum Type {
        PLAYER,
        SPECTATOR // fly, no item pickup, cancel damage,
    }
    public static enum Team {
        BLUE,
        RED
    }

    private Player player;
    private int kills;
    private int lives;

    private Type type;
    private Team team;
    private boolean participated; // if played in the game true, if just spectated false

    public GamePlayer(Player player) { // constructor
        this.player = player;
        team = Team.RED;
        kills = 0;
        type = (Game.game.started) ? Type.SPECTATOR : Type.PLAYER;
        lives = (Game.game.started) ? 0 : 3;
        participated = (type == Type.PLAYER);
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

    public boolean isPlayer() {
        return (type == Type.PLAYER);
    }

    public Team getTeam() { return team; }

    public boolean getParticipated() {
        return participated;
    }

    public Player getPlayer() { return player; }

    public void addKill() {
        this.kills += 1;
    }

    public void removeLife(){
        this.lives -= 1;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setTeam(Team team) { this.team = team; }

}