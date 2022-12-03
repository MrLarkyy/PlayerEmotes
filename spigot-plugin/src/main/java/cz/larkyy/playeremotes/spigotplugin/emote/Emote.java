package cz.larkyy.playeremotes.spigotplugin.emote;

public class Emote {

    private final boolean canMove;
    private final boolean canSkip;
    private final String identifier;
    private final String emoteNamespace;
    private final EmoteItem emoteItem;
    private final String permission;

    public Emote(String identifier, String emoteNamespace, EmoteItem emoteItem,
                 String permission, boolean canMove, boolean canSkip) {
        this.canMove = canMove;
        this.emoteNamespace = emoteNamespace;
        this.canSkip = canSkip;
        this.identifier = identifier;
        this.permission = permission;
        this.emoteItem = emoteItem;
    }

    public String getIdentifier() {
        return identifier;
    }

    public boolean canMove() {
        return canMove;
    }

    public boolean canSkip() {
        return canSkip;
    }

    public String getEmoteNamespace() {
        return emoteNamespace;
    }

    public String getPermission() {
        return permission;
    }

    public EmoteItem getEmoteItem() {
        return emoteItem;
    }
}
