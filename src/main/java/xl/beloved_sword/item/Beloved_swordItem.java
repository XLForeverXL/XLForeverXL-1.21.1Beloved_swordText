package xl.beloved_sword.item;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;
import xl.beloved_sword.Beloved_sword;
import xl.beloved_sword.Entity.ColorLightningBolt;
import xl.beloved_sword.FontRender.LoveFontRender;
import xl.beloved_sword.Helper.BadEntityLists;
import xl.beloved_sword.Helper.EntityHelper;
import xl.beloved_sword.Helper.PlayerGodList;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class Beloved_swordItem extends SwordItem {
    public Beloved_swordItem() {
        super(new Tier() {
            @Override
            public int getUses() {
                return Integer.MAX_VALUE;
            }

            @Override
            public float getSpeed() {
                return Float.MAX_VALUE;
            }

            @Override
            public float getAttackDamageBonus() {
                return Float.MAX_VALUE;
            }

            @Override
            public TagKey<Block> getIncorrectBlocksForDrops() {
                return null;
            }

            @Override
            public int getEnchantmentValue() {
                return Integer.MAX_VALUE;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return null;
            }
        }, new Properties());
    }


    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public @Nullable Font getFont(ItemStack stack, FontContext context) {
                return LoveFontRender.getFont();
            }
        });
    }

    @Override
    public Component getName(ItemStack p_41458_) {
        return Component.literal("至爱の刃");
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, TooltipContext p_333372_, List<Component> list, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_333372_, list, p_41424_);
        list.add(Component.literal("独知入渊深为止，身似浮萍命难迟"));
        list.add(Component.literal("千舟皆潮归海处，一苇轻波戏浪巅！"));
        list.add(Component.literal("一苇轻波戏浪巅，惊鸿四散鱼逃尽！"));
        list.add(Component.literal("唯有残帆傲此间，待到天开云雾散！"));
    }
    public int getUseDuration(ItemStack p_40680_, LivingEntity p_344246_) {
        return 72000;
    }

    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand hand) {
        p_41433_.startUsingItem(hand);
        return super.use(p_41432_, p_41433_, hand);
    }

    @Override
    public void releaseUsing(ItemStack p_41412_, Level level, LivingEntity entity, int p_41415_) {
        final Vec3 _center = new Vec3(entity.getX(), entity.getY(), entity.getZ());
        List<Entity> entityList = level.getEntitiesOfClass(Entity.class, new AABB(_center, _center).
                        inflate(520 / 2d), e -> true).
                stream().sorted(Comparator.comparingDouble(_entcnd ->
                        _entcnd.distanceToSqr(_center))).toList();
        for (Entity all : entityList){
            if (!(all instanceof Player || all instanceof ItemEntity)){
                BadEntityLists.addEntityToDeadList(entity);
                EntityHelper.KillEntity(all);
            }

        }
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        PlayerGodList.SetGodPlayer((Player) p_41406_);
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
    }
}
