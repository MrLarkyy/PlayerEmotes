package cz.larkyy.playeremotes.spigotplugin;

import com.ticxo.playeranimator.PlayerAnimatorImpl;
import com.ticxo.playeranimator.api.PlayerAnimator;
import com.ticxo.playeranimator.api.PlayerAnimatorPlugin;
import cz.larkyy.playeremotes.spigotplugin.commands.Commands;
import cz.larkyy.playeremotes.spigotplugin.listeners.PlayerLeaveListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import xyz.larkyy.aquaticutils.action.Actions;
import xyz.larkyy.aquaticutils.condition.Conditions;

public final class PlayerEmotes extends PlayerAnimatorPlugin {

    public static PlayerAnimator playerAnimator;
    public static Manager manager;
    public static Conditions conditions;
    public static Actions actions;
    @Override
    public void onEnable() {
        plugin = this;
        manager = new Manager();
        conditions = new Conditions();
        actions = new Actions();
        playerAnimator = PlayerAnimatorImpl.initialize(this);
        getCommand("playeremotes").setExecutor(new Commands());

        load();
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(),this);
    }

    public void load() {
        Bukkit.getScheduler().runTaskAsynchronously(this, () -> playerAnimator.getAnimationManager().importPacks());
        manager.load();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static PlayerAnimator getPlayerAnimator() {
        return playerAnimator;
    }
}
