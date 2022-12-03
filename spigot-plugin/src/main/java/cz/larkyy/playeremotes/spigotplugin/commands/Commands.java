package cz.larkyy.playeremotes.spigotplugin.commands;

import cz.larkyy.playeremotes.spigotplugin.commands.impl.EmoteListCommand;
import cz.larkyy.playeremotes.spigotplugin.commands.impl.MenuCommand;
import cz.larkyy.playeremotes.spigotplugin.commands.impl.PlayEmoteCommand;
import cz.larkyy.playeremotes.spigotplugin.commands.impl.ReloadCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class Commands implements CommandExecutor {

    private final Map<String, ICommand> availableCommands = new HashMap<>(){
        {
            put("playemote",new PlayEmoteCommand());
            put("list",new EmoteListCommand());
            put("menu",new MenuCommand());
            put("reload",new ReloadCommand());
        }
    };


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            availableCommands.get("menu").run(sender,args);
            return true;
        }

        ICommand cmd = availableCommands.get(args[0]);
        if (cmd == null) {
            return false;
        }
        cmd.run(sender,args);
        return true;
    }
}
