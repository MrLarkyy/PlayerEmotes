package cz.larkyy.playeremotes.spigotplugin.commands;

import org.bukkit.command.CommandSender;

public interface ICommand {
    void run(CommandSender sender, String[] args);
}
