package cz.larkyy.playeremotes.spigotplugin;

import com.ticxo.playeranimator.api.model.player.PlayerModel;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.larkyy.menulib.Menu;
import xyz.larkyy.menulib.MenuItem;

import java.util.Arrays;
import java.util.List;

public class EmotesMenu {

    private final List<Integer> emoteSlots;
    private final Menu.Builder menuBuilder;

    public EmotesMenu(Menu.Builder menuBuilder, List<Integer> emoteSlots) {
        this.emoteSlots = emoteSlots;
        this.menuBuilder = menuBuilder;
    }

    public void open(Player p, int page) {
        Menu.Builder builder = menuBuilder.clone();
        loadEmoteItems(builder,page,p);
        Menu m = builder.build();

        MenuItem mi;
        mi = m.getItem("next-page");
        if (mi != null) {
            mi.addAction(e -> openNextPage(p,page));
        }
        mi = m.getItem("prev-page");
        if (mi != null) {
            mi.addAction(e -> openPrevPage(p,page));
        }
        p.openInventory(m.getInventory());
    }

    private void loadEmoteItems(Menu.Builder builder, int page, Player p) {

        int i = page * emoteSlots.size();
        for (int slot : emoteSlots) {
            if (i >= getEmotes().size()) {
                return;
            }
            String emote = getEmotes().get(i);

            ItemStack is = new ItemStack(Material.TOTEM_OF_UNDYING);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName("§e"+emote);
            is.setItemMeta(im);

            builder.addItem(MenuItem.builder("emote-"+emote,is)
                    .slots(Arrays.asList(slot))
                    .action(e -> {
                        if (!p.hasPermission("playeremotes.emote."+emote)) {
                            p.sendMessage("§cYou do not have permissions to use this emote!");
                            return;
                        }
                        p.closeInventory();
                        PlayerModel model = new AquaticPlayerModel(p);
                        model.playAnimation(emote);
                    })
                    .build()
            );
            i++;
        }
    }

    private void openPrevPage(Player p, int page) {
        if (hasPreviousPage(page)) {
            open(p,page-1);
        }
    }

    private void openNextPage(Player p, int page) {
        if (hasNextPage(page)) {
            open(p,page+1);
        }
    }

    private boolean hasNextPage(int page) {
        return (page < getEmotes().size()/emoteSlots.size());
    }

    private boolean hasPreviousPage(int page) {
        return page > 0;
    }

    public List<String> getEmotes() {
        return Utils.getAvailableEmotes();
    }

}
