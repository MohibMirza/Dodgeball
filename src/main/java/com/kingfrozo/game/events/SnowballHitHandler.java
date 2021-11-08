package com.kingfrozo.game.events;

import com.kingfrozo.game.Config;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SnowballHitHandler implements Listener {

    public static int count = 0;

    @EventHandler(priority = EventPriority.LOW)
    public void onMiss(ProjectileHitEvent event) {
        if(event.getEntity() instanceof Snowball
            && event.getEntity().getShooter() instanceof Player) {

            Player player = (Player) event.getEntity().getShooter();

            dropSnowball(event.getHitBlock().getLocation());

        }
    }

    // TODO: Give damager a point
    // TODO: Kill damagee upon hit
    @EventHandler(priority = EventPriority.HIGH)
    public void onHit(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player
                && event.getEntity() instanceof Player)  {

            Player damager = (Player) event.getDamager();
            Player damagee = (Player) event.getEntity();

            deathTeleport(damagee);

        }
    }

    public void dropSnowball(Location loc) {
        ItemStack drop = new ItemStack(Material.SNOWBALL);
        drop.setAmount(1);

        ItemMeta meta = drop.getItemMeta();
        meta.setCustomModelData(count);
        count++;

        loc = boundCheck(loc);

        loc.getWorld().dropItemNaturally(loc, drop);
    }

    public Location boundCheck(Location loc) {
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

        return loc;
    }

    public void deathTeleport(Player player) {
        Location loc = player.getLocation();
        loc.setX(Config.respawnX);
        loc.setY(Config.respawnY);
        loc.setZ(Config.respawnZ);

        player.teleport(loc);
    }

}
