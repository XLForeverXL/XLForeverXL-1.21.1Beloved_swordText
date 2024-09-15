package xl.beloved_sword.Helper;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gameevent.GameEvent;

public class EntityHelper {




    public static void KillEntity(Entity entity){
        entity.kill();
        entity.onRemovedFromWorld();
        entity.remove(Entity.RemovalReason.KILLED);
        entity.gameEvent(GameEvent.ENTITY_DIE);
        entity.remove(Entity.RemovalReason.KILLED);
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.setHealth(0.0f);
            livingEntity.deathTime = 19;
            livingEntity.hurtTime = 20;
            livingEntity.valid = false;
            livingEntity.isLazy = true;
            livingEntity.setRemoved(Entity.RemovalReason.KILLED);

        }



    }

























}
