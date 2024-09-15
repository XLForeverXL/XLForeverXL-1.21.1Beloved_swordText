package xl.beloved_sword;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import xl.beloved_sword.Entity.ColorLightningBolt;
import xl.beloved_sword.Entity.ColorLightningBoltRenderer;
import xl.beloved_sword.Helper.PlayerGodList;
import xl.beloved_sword.item.Beloved_swordItem;

import java.awt.*;
import java.util.Random;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Beloved_sword.MODID)
public class Beloved_sword {

    public static final String MODID = "beloved_sword";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
    public static final RegistryObject<EntityType<ColorLightningBolt>> CUSTOM_THUNDER_ENTITY = ENTITIES.register("custom_thunder_entity",
            () -> EntityType.Builder.of(ColorLightningBolt::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .build("custom_thunder_entity"));
    public Beloved_sword() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        ENTITIES.register(modEventBus);
        ITEMS.register("beloved_swordiem", Beloved_swordItem::new);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event){
        if (PlayerGodList.isGodPlayer(event.player)){
            event.player.setHealth(20.0f);
            event.player.deathTime = 0;
            event.player.hurtTime = 0;
            if (Minecraft.getInstance().screen instanceof DeathScreen){
                Minecraft.getInstance().screen.height = 0;
                Minecraft.getInstance().screen.width = 0;
                Minecraft.getInstance().screen = null;
            }
        }
    }
    @SubscribeEvent
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(CUSTOM_THUNDER_ENTITY.get(), new ColorLightningBoltRenderer()
        );
    }


    @SubscribeEvent
    public void RenderTooltipEvent(RenderTooltipEvent.Color event){
        if (event.getItemStack().getItem() instanceof Beloved_swordItem){
            event.setBackground(Color.BLACK.getRGB());
            event.setBorderStart(new Random().nextInt());
            event.setBorderEnd(new Random().nextInt());
        }
    }
    @SubscribeEvent
    public void RenderTooltipEvent1(RenderTooltipEvent.Pre event){
        if (event.getItemStack().getItem() instanceof Beloved_swordItem){
            int width = event.getScreenWidth();
            int height = event.getScreenHeight();
            event.getGraphics().blit(new ResourceLocation("beloved_sword","textures/item/ccc.png"), 0, 0, 0.0F, 0.0F, width, height, width, height);
        }
    }


    @SubscribeEvent
    public void onDeath(LivingDeathEvent event){
        if (PlayerGodList.isGodPlayer(event.getEntity())){
            event.setCanceled(true);
        }
    }
    @SubscribeEvent
    public void onHurt(LivingHurtEvent event){
        if (PlayerGodList.isGodPlayer(event.getEntity())){
            event.setCanceled(true);
        }
    }


    static {


    }

}
