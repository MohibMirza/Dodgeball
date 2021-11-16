package com.kingfrozo.game.events;

import com.kingfrozo.game.GamePlayer;
import com.kingfrozo.game.Main;
import com.kingfrozo.game.util.Config;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Random;

public class RespawnHandler {

    private static Random random = Main.getRandom();
    private static Map<String, GamePlayer> gamePlayers = GamePlayerJoinLeaveHandler.gamePlayers;

    public static void respawn(Player player) {

        int x_offset = random.nextInt(Config.arenaWidth);
        int respawnX = Config.x_bottom + x_offset;
        double z_offset = (gamePlayers.get(player.getName()).getTeam() == GamePlayer.Team.BLUE) ? 1.5 : -1.5;
        double respawnZ = Config.arenaZ + z_offset;

        float pitch = 0F;
        float yaw = 0F;

        Location loc = new Location(player.getWorld(), respawnX, Config.arenaY, respawnZ, pitch, yaw);
        player.teleport(loc);
    }

}
