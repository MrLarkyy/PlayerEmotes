package cz.larkyy.playeremotes.spigotplugin;

import com.ticxo.playeranimator.api.model.player.PlayerModel;
import com.ticxo.playeranimator.api.texture.TextureWrapper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class AquaticPlayerModel extends PlayerModel {

    private Player viewer = null;
    private Player player = null;

    public AquaticPlayerModel(Player player) {
        super(player);
        this.player = player;
    }

    public AquaticPlayerModel(Entity base, String url, boolean isSlim) {
        super(base, url, isSlim);
    }

    public AquaticPlayerModel(Entity base, TextureWrapper tex) {
        super(base, tex);
    }

    public AquaticPlayerModel(Entity base, Player player) {
        super(base, player);
    }

    public void setViewer(Player p) {
        viewer = p;
    }

    @Override
    public void spawn() {
        if (player != null) {
            player.setInvisible(true);
            PlayerEmotes.manager.addPlayerEmote(player,this);
        }

        if (viewer == null) {
            super.spawn();
        } else {
            super.spawn(viewer);
        }
    }

    @Override
    public void despawn() {
        if (player != null) {
            player.setInvisible(false);
        }
        super.despawn();
    }
    @Override
    public void despawn(Player player) {
        if (player != null) {
            player.setInvisible(false);
            PlayerEmotes.manager.removePlayerEmote(player);
        }
        super.despawn(player);
    }
}
