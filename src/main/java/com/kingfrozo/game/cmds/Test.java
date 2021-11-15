package com.kingfrozo.game.cmds;

import com.kingfrozo.game.Game;
import com.kingfrozo.game.GamePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Test implements CommandExecutor {
    @Override // /test
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Map Valid: " + (Game.game.getPlayers().get(sender.getName()) != null));

        return false;
    }
}
