package cz.larkyy.playeremotes.spigotplugin.commands.impl;

import cz.larkyy.playeremotes.spigotplugin.EmotesMenu;
import cz.larkyy.playeremotes.spigotplugin.PlayerEmotes;
import cz.larkyy.playeremotes.spigotplugin.commands.ICommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenuCommand implements ICommand {
    @Override
    public void run(CommandSender sender, String[] args) {
        if (!(sender instanceof Player p)) {
            return;
        }

        if (!p.hasPermission("playeremotes.menu")) {
            sender.sendMessage("§cYou do not have permissions to do that!");
            return;
        }

        EmotesMenu menu = PlayerEmotes.manager.getEmotesMenu();
        if (menu == null) {
            return;
        } else {
            menu.open(p,0);
        }
    }
}
