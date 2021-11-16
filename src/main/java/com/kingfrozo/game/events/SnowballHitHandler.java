package com.kingfrozo.game.events;

import com.kingfrozo.game.GamePlayer;
import com.kingfrozo.game.util.Config;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class SnowballHitHandler implements Listener {

    public static int count = 0;
    private Map<String, GamePlayer> gamePlayers = GamePlayerJoinLeaveHandler.gamePlayers;

    // TODO: add some code to respawn dodgeballs when players are hit
    @EventHandler(priority = EventPriority.LOW)
    public void onMiss(ProjectileHitEvent event) {
        if(event.getHitBlock() == null) {
            return;
        }

        if(event.getEntity() instanceof Snowball
            && event.getEntity().getShooter() instanceof Player) {

            Player player = (Player) event.getEntity().getShooter();

            dropSnowball(event.getHitBlock().getLocation());

        }
    }

    // TODO: Spawn snowball in place of player
    @EventHandler(priority = EventPriority.HIGH)
    public void onHit(ProjectileHitEvent event) {
        if(event.getHitEntity() instanceof Player && event.getEntity() instanceof Snowball
                && event.getEntity().getShooter() instanceof Player)  {

            event.setCancelled(true);

            Player damager = (Player) event.getEntity().getShooter();
            Player damagee = (Player) event.getHitEntity();
            GamePlayer gpDamager = gamePlayers.get(damager.getName());
            GamePlayer gpDamagee = gamePlayers.get(damagee.getName());

            gpDamager.addKill();
            gpDamagee.removeLife();

            System.out.println(damager.getName() + " kills: " + gpDamager.getKills() );
            System.out.println(damagee.getName() + " dealths: " + gpDamagee.getLives());

            dropSnowball(event.getHitEntity().getLocation());

        }
    }

    public static void dropSnowball(Location loc) {
        if(loc == null) { return; }
        ItemStack drop = new ItemStack(Material.SNOWBALL);
        drop.setAmount(1);

        ItemMeta meta = drop.getItemMeta();
        meta.setCustomModelData(count);
        count++;

        boundCheck(loc);

        loc.getWorld().dropItemNaturally(loc, drop);
    }

    public static void boundCheck(Location loc) {
        if(loc.getBlockY() != Config.arenaY){ // height manipulation
            loc.setY(Config.arenaY);
        }

        if(loc.getBlockZ() < Config.z_bottom) {
            loc.setZ(Config.z_bottom);
        }else if(loc.getBlockZ() > Config.z_top) {
            loc.setZ(Config.z_top);
        }

        if(loc.getBlockX() < Config.x_bottom) {
            loc.setX(Config.x_bottom);
        }else if(loc.getBlockX() > Config.x_top) {
            loc.setX(Config.x_top);
        }
        return;
    }

    // TODO: Create a Thread that counts down the players respawn
    // TODO: If died three times, move them into spectate mode instead of teleport
    public static void deathTeleport(Player player) {
        Location loc = player.getLocation();
        loc.setX(Config.respawnX);
        loc.setY(Config.respawnY);
        loc.setZ(Config.respawnZ);

        player.teleport(loc);
    }


}
