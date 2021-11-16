package com.kingfrozo.game.cmds;

import com.kingfrozo.game.Game;
import com.kingfrozo.game.GamePlayer;
import com.kingfrozo.game.events.RespawnHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Test implements CommandExecutor {
    @Override // /test
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            RespawnHandler.respawn(player);
        }

        return false;
    }
}
