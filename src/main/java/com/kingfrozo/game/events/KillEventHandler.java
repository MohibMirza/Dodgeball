package com.kingfrozo.game.events;

import com.kingfrozo.game.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class KillEventHandler implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {



        if( !(event.getDamager() instanceof Snowball && event.getEntity() instanceof Player) ) { return; } // make sure entities are players

        Player damagee = (Player) event.getEntity();

        damagee.sendMessage("YOU GOT HIT BY A SNOWBALL FOO...");

        GamePlayer gDamagee = GamePlayerJoinLeaveHandler.gamePlayers.get(damagee.getName());

        if(!gDamagee.isPlayer()) { return; }

        // WHAT WE KNOW: gDamagee is Player, got hit by snowball, subtract from lives.



    }





}
