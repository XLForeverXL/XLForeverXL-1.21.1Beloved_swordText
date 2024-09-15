package xl.beloved_sword.Helper;

import net.minecraft.world.entity.Entity;

import java.util.*;


public class BadEntityLists {



    private static final List<String> Death = new ArrayList<>(){
        public void clear() {}

        public boolean remove(Object o) {
            return false;
        }
    };


    private static final List<Entity> list = new ArrayList();



    public static boolean isEntity(Object o) {
        if (!(o instanceof Entity)){
            return false;
        }
        return (list.contains(o) || Death.contains(o.getClass().getName()));
    }

    public static void addEntityToDeadList(Entity entity) {
        list.add(entity);
        Death.add(entity.getClass().getName());
    }



    public static void SetDeaded(Entity player) {
        Death.add(player.getClass().getName());
    }

}