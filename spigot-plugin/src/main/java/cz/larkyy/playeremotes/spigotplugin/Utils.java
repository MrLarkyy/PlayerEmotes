package cz.larkyy.playeremotes.spigotplugin;

import com.ticxo.playeranimator.api.PlayerAnimator;
import com.ticxo.playeranimator.api.animation.pack.AnimationPack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {

    public static List<String> getAvailableEmotes() {
        List<String> list = new ArrayList<>();
        Map<String, AnimationPack> map = PlayerAnimator.api.getAnimationManager().getRegistry();
        for (String packName : map.keySet()) {
            AnimationPack pack = map.get(packName);
            for (String animationName : pack.getAnimations().keySet()) {
                list.add(packName.replace(":",".")+"."+animationName);
            }
        }
        return list;
    }
}
