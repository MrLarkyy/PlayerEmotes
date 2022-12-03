package cz.larkyy.playeremotes.spigotplugin.listeners;

import cz.larkyy.playeremotes.spigotplugin.PlayerEmotes;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        if (PlayerEmotes.manager.isPlayerInEmote(e.getPlayer())) {
            e.getPlayer().setInvisible(false);
        }
    }
}
