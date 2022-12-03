package cz.larkyy.playeremotes.spigotplugin.emote;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.larkyy.aquaticutils.condition.ConditionList;

public class EmoteItem {

    private final ItemStack lockedItem;
    private final ItemStack unlockedItem;
    private final ConditionList unlockItemConditions;
    private final ConditionList lockedItemConditions;
    private final ConditionList viewConditions;

    public EmoteItem(ItemStack lockedItem, ItemStack unlockedItem,
                     ConditionList unlockItemConditions, ConditionList lockedItemConditions,
                     ConditionList viewConditions) {
        this.lockedItem = lockedItem;
        this.unlockedItem = unlockedItem;
        this.unlockItemConditions = unlockItemConditions;
        this.lockedItemConditions = lockedItemConditions;
        this.viewConditions = viewConditions;
    }

    public ItemStack getUnlockedItem() {
        return unlockedItem;
    }

    public ConditionList getViewConditions() {
        return viewConditions;
    }

    public boolean canBeViewed(Player player) {
        return viewConditions.areMet(player);
    }

    public ItemStack getItem(Player player) {
        
    }

    public ConditionList getUnlockItemConditions() {
        return unlockItemConditions;
    }

    public ItemStack getLockedItem() {
        return lockedItem;
    }

    public ConditionList getLockedItemConditions() {
        return lockedItemConditions;
    }
}
