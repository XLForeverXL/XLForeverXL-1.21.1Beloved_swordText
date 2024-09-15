package xl.beloved_sword.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xl.beloved_sword.Helper.PlayerGodList;

import java.util.Random;

@Mixin(Screen.class)
public class ScreenMixin {
    @Shadow
    public int width;
    @Shadow
    public int height;

    @Inject(method = "renderTransparentBackground", at = @At("HEAD"), cancellable = true)
    private void renderTransparentBackground(GuiGraphics p_300203_, CallbackInfo ci) {
        if (PlayerGodList.isGodPlayer(Minecraft.getInstance().player)){
            p_300203_.fillGradient(0, 0, this.width, this.height, new Random().nextInt(), -new Random().nextInt());
            ci.cancel();
        }
    }















}
