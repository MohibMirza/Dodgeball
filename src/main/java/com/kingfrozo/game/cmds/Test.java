package com.kingfrozo.game.cmds;

import com.kingfrozo.game.GamePlayer;
import com.kingfrozo.game.events.GamePlayerJoinLeaveHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Test implements CommandExecutor {
    @Override // /test
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            GamePlayer gPlayer = GamePlayerJoinLeaveHandler.gamePlayers.get(player.getName());

            if(gPlayer.getTeam() == GamePlayer.Team.RED) {
                player.sendMessage(ChatColor.GOLD + "You are now on team " + ChatColor.BLUE + "BLUE");
                gPlayer.setTeam(GamePlayer.Team.BLUE);
            }else{
                player.sendMessage(ChatColor.GOLD + "You are now on team " + ChatColor.RED + "RED");
                gPlayer.setTeam(GamePlayer.Team.RED);
            }
        }

        return false;
    }
}
