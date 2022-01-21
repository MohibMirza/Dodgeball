package com.kingfrozo.game.events;

import com.kingfrozo.game.GamePlayer;
import com.kingfrozo.game.Main;
import com.kingfrozo.game.util.Config;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.*;

public class SnowballHitHandler implements Listener {

    public static Main plugin = Main.getPlugin();
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
    // TODO: SPECTATORS DONT APPLY TO THIS
    @EventHandler(priority = EventPriority.HIGH)
    public void onHit(ProjectileHitEvent event) {

        if(event.getHitEntity() instanceof Player && event.getEntity() instanceof Snowball
                && event.getEntity().getShooter() instanceof Player)  {

            event.setCancelled(true);

            Player damager = (Player) event.getEntity().getShooter();
            Player damagee = (Player) event.getHitEntity();
            Snowball snowball = (Snowball) event.getEntity();

            GamePlayer gpDamager = gamePlayers.get(damager.getName());
            GamePlayer gpDamagee = gamePlayers.get(damagee.getName());
            //snowball stops flying once it hits player
            snowball.setVelocity(new Vector(0, 0, 0));

            //friendly fire
            if(gpDamager.getTeam() == gpDamagee.getTeam()) { return; }
            if(damagee.getGameMode() != GameMode.SURVIVAL || damager.getGameMode() != GameMode.SURVIVAL) { return; }


            gpDamager.addKill();
            gpDamagee.removeLife();

            System.out.println(damager.getName() + " kills: " + gpDamager.getKills() );
            System.out.println(damagee.getName() + " lives: " + gpDamagee.getLives());

            if(gpDamagee.getLives() == 0) {
                lose(damagee);

            }else {
                Location respawnRoom = new Location(damagee.getWorld(), Config.respawnX, Config.respawnY, Config.respawnZ);

                die(damagee, respawnRoom);

                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
                    respawn(damagee);
                }, 60);
                dropSnowball(event.getHitEntity().getLocation());
            }

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

    public static void die(Player player, Location respawnRoom) {



        respawnRoom.setYaw(-180);
        respawnRoom.setPitch(90);
        player.teleport(respawnRoom);
        player.setGameMode(GameMode.SPECTATOR);
        player.setFlySpeed(0.0f);
        player.setWalkSpeed(0.0f);
        player.getInventory().clear(); // TODO: REGEN CLEARED SNOWBALLS
    }


    public static void respawn(Player player) {

        GamePlayer gPlayer = GamePlayerJoinLeaveHandler.gamePlayers.get(player.getName());
        boolean isRed = gPlayer.getTeam() == GamePlayer.Team.RED;

        Random random = new Random(System.currentTimeMillis());

        double xoffset = random.nextInt(29)-14;

        double zoffset = random.nextInt(5)+6; // gen a random no. from 4-10

        double respawnX = Config.arenaX - xoffset;
        double respawnY = Config.arenaY;
        double respawnZ;
        if(gPlayer.getTeam() == GamePlayer.Team.RED) {
            respawnZ = Config.arenaZ + zoffset;
        }else {
            respawnZ = Config.arenaZ - zoffset;
        }

        Location loc = new Location(player.getWorld(), respawnX, respawnY, respawnZ);
        player.setGameMode(GameMode.SURVIVAL);
        player.setFlySpeed(0.2f);
        player.setWalkSpeed(0.2f);

        if(gPlayer.getTeam() == GamePlayer.Team.RED) {
            loc.setYaw(180);
            loc.setPitch(0);
        }
        player.setGameMode(GameMode.SURVIVAL);
        player.teleport(loc);

    }

    public void lose(Player player) {
        player.setGameMode(GameMode.CREATIVE);

        for(Player otherPlayer : Bukkit.getOnlinePlayers()) {
            otherPlayer.hidePlayer(plugin, player);
        }

        gamePlayers.get(player.getName()).setType(GamePlayer.Type.SPECTATOR);

        player.getInventory().clear();

        return;
    }

}
