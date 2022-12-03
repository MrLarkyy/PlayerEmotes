package cz.larkyy.playeremotes.spigotplugin.commands.impl;

import cz.larkyy.playeremotes.spigotplugin.Utils;
import cz.larkyy.playeremotes.spigotplugin.commands.ICommand;
import org.bukkit.command.CommandSender;

public class EmoteListCommand implements ICommand {
    @Override
    public void run(CommandSender sender, String[] args) {

        if (!sender.hasPermission("playeremotes.list")) {
            sender.sendMessage("§cYou do not have permissions to do that!");
            return;
        }
        for (String str : Utils.getAvailableEmotes()) {
            sender.sendMessage(str);
        }

    }
}
