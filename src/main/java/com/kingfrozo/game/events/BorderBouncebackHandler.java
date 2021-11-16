package com.kingfrozo.game.events;

import com.kingfrozo.game.util.Config;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class BorderBouncebackHandler implements Listener {

    private static Map<String, Long> bbCooldown; // TODO: CLEAR THIS MAP END OF GAME
    private static int cooldown = 500;

    // TODO: IF PLAYER GETS THRU TELEPORT THEM BACK
    @EventHandler
    public void touchBorder(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location playerLoc = event.getPlayer().getLocation();

        if(playerLoc.getBlockX() < Config.x_bottom || playerLoc.getBlockX() > Config.x_top) { return; }

        if(playerLoc.getBlockZ() == Config.arenaZ && player.getGameMode() == GameMode.SURVIVAL && player.isFlying() == false){
            if(bbCooldown == null) {
                bbCooldown = new HashMap<String, Long>();
            }

            Long lastBB = bbCooldown.get(player.getName());
            if(lastBB == null) {

                bounceback(player);
                bbCooldown.put(player.getName(), System.currentTimeMillis());
            }else if(bbCooldown.get(player.getName()) + cooldown < System.currentTimeMillis()) {

                bounceback(player);
                bbCooldown.put(player.getName(), System.currentTimeMillis());
            }else {

                return;
            }
        }

    }

    public void bounceback(Player player) {

        player.setVelocity(player.getVelocity().add( new Vector(0, 0, 1.3) ) );

        player.setVelocity(player.getVelocity().setY(-.05));
        player.setVelocity(player.getVelocity().multiply(-1));
    }
}