package com.kingfrozo.game.events;

import com.kingfrozo.game.Game;
import com.kingfrozo.game.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class SpectatorHandler implements Listener {
    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            GamePlayer gPlayer = Game.game.getPlayers().get(player.getName());
            if(gPlayer.getType() == GamePlayer.Type.SPECTATOR) {
                event.setCancelled(true);
            }

        }

    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            GamePlayer gPlayer = Game.game.getPlayers().get(player.getName());
            if(gPlayer.getType() == GamePlayer.Type.SPECTATOR) {
                event.setCancelled(true);
            }
        }
    }
}
