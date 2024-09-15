package xl.beloved_sword.Helper;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.*;

public class PlayerGodList {
    private static final List<Entity> entityMap = new ArrayList<Entity>(){
        @Override
        public void clear() {

        }

        @Override
        public boolean remove(Object o) {
            return false;
        }
    };

    public static boolean isGodPlayer(Object o) {
        if (!(o instanceof Player))
            return false;
        return (entityMap.contains(o));
    }
    public static void SetGodPlayer(Player entity) {
        for (int i = 0; i < 5; i++) {
            entityMap.add(entity);
        }
    }



}
