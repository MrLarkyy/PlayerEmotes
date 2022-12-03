package cz.larkyy.playeremotes.spigotplugin.commands.impl;

import cz.larkyy.playeremotes.spigotplugin.PlayerEmotes;
import cz.larkyy.playeremotes.spigotplugin.commands.ICommand;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements ICommand {
    @Override
    public void run(CommandSender sender, String[] args) {
        if (!sender.hasPermission("playeremotes.reload")) {
            return;
        }

        PlayerEmotes.getPlugin(PlayerEmotes.class).load();
        sender.sendMessage("Plugin has been reloaded!");
    }
}
