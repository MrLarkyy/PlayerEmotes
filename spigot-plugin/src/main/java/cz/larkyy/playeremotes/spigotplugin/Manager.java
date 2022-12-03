package cz.larkyy.playeremotes.spigotplugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import xyz.larkyy.colorutils.Colors;
import xyz.larkyy.itemlibrary.CustomItem;
import xyz.larkyy.menulib.Menu;
import xyz.larkyy.menulib.MenuItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {

    private final Config config = new Config(PlayerEmotes.plugin,"config.yml");
    private final Map<Player,AquaticPlayerModel> playersInEmote = new HashMap<>();
    private EmotesMenu emotesMenu;

    public void load() {
        config.load();
        loadEmotesMenu();
    }

    public boolean isPlayerInEmote(Player p) {
        return playersInEmote.containsKey(p);
    }

    public AquaticPlayerModel getPlayerModel(Player p) {
        return playersInEmote.get(p);
    }

    public void addPlayerEmote(Player p, AquaticPlayerModel model) {
        playersInEmote.put(p,model);
    }

    public void removePlayerEmote(Player p) {
        playersInEmote.remove(p);
    }

    public EmotesMenu getEmotesMenu() {
        return emotesMenu;
    }

    private void loadEmotesMenu() {
        if (!getConfiguration().contains("emotes-menu")) {
            return;
        }

        String title;
        if (getConfiguration().contains("emotes-menu.title")) {
            title = Colors.format(getConfiguration().getString("emotes-menu.title"));
        } else {
            title = "Emotes Menu";
        }
        Menu.Builder builder =Menu.builder(PlayerEmotes.plugin)
                .size(54)
                .title(title);

        if (getConfiguration().contains("emotes-menu.items")) {
            for (String str : getConfiguration().getConfigurationSection("emotes-menu.items").getKeys(false)) {
                builder.addItem(loadMenuItem(str,"emotes-menu.items."+str));
            }
        }

        emotesMenu = new EmotesMenu(
                builder,
                getConfiguration().getIntegerList("emotes-menu.emotes-slots")
                );
    }

    private MenuItem loadMenuItem(String identifier, String path) {

        CustomItem item = loadItem(path);
        List<Integer> slots;
        if (getConfiguration().contains(path+".slot")) {
            slots = Arrays.asList(getConfiguration().getInt(path+".slot"));
        } else {
            slots = getConfiguration().getIntegerList(path+".slots");
        }

        return MenuItem.builder(identifier,item.getItem())
                .slots(slots)
                .build();
    }

    private CustomItem loadItem(String path) {
        return CustomItem.loadFromYaml(getConfiguration(),path);
    }

    public FileConfiguration getConfiguration() {
        return config.getConfiguration();
    }

}
