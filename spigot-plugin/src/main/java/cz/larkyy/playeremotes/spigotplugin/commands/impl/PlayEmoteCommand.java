package cz.larkyy.playeremotes.spigotplugin.commands.impl;

import com.ticxo.playeranimator.api.animation.pack.AnimationPack;
import com.ticxo.playeranimator.api.model.player.PlayerModel;
import cz.larkyy.playeremotes.spigotplugin.AquaticPlayerModel;
import cz.larkyy.playeremotes.spigotplugin.PlayerEmotes;
import cz.larkyy.playeremotes.spigotplugin.commands.ICommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayEmoteCommand implements ICommand {
    @Override
    public void run(CommandSender sender, String[] args) {
        if (args.length <= 1) {
            return;
        }

        String[] info = args[1].split("\\.");
        String packName = info[0];
        String fileName = info[1];
        String animationName = info[2];

        AnimationPack pack = PlayerEmotes.getPlayerAnimator().getAnimationManager().getRegistry().get(packName+":"+fileName);
        if (pack == null) {
            sender.sendMessage("§cThis Emote does not exist!");
            return;
        }

        if (!pack.getAnimations().containsKey(animationName)) {
            sender.sendMessage("§cThis Emote does not exist!");
            return;
        }

        if (args.length == 2) {
            if (!sender.hasPermission("playeremotes.play")) {
                return;
            }
            if (!(sender instanceof Player p)) {
                return;
            }
            if (!sender.hasPermission("playeremotes.emote."+args[1])) {
                sender.sendMessage("§cYou do not have permissions to do that!");
                return;
            }

            PlayerModel model = new AquaticPlayerModel(p);
            model.playAnimation(args[1]);
        } else {
            if (!sender.hasPermission("playeremotes.play.others")) {
                return;
            }

            Player t = Bukkit.getPlayer(args[2]);
            if (t == null) {
                sender.sendMessage("§cUnknown player!");
                return;
            }
            PlayerModel model = new AquaticPlayerModel(t);
            model.playAnimation(args[1]);
        }
    }
}
