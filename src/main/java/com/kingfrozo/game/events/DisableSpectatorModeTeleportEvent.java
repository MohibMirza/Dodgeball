package com.kingfrozo.game.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class DisableSpectatorModeTeleportEvent implements Listener {
    @EventHandler
    public void onTeleport(PlayerTeleportEvent event)
    {
        if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.SPECTATE)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "Sorry, but you can't do that!");
        }
    }
}
