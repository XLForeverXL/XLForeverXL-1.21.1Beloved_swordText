package xl.beloved_sword.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Iterator;
import java.util.Random;

@Mixin(TitleScreen.class)

public class TitleScreenMixin extends Screen{


    protected TitleScreenMixin(Component p_96550_) {
        super(p_96550_);
    }

    @Override
    public void render(GuiGraphics p_281549_, int p_281550_, int p_282878_, float p_282465_) {
        p_281549_.blit(new ResourceLocation("beloved_sword","textures/item/ccc.png"), 0, 0, p_281550_, p_282878_, width , height, width * 2, height * 2);
        this.renderBackground(p_281549_, p_281550_, p_282878_, p_282465_);

        for (Renderable renderable : this.renderables) {
            renderable.render(p_281549_, p_281550_, p_282878_, p_282465_);
        }
    }
}
