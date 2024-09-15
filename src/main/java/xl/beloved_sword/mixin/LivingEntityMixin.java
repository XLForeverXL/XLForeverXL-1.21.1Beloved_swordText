package xl.beloved_sword.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xl.beloved_sword.Helper.PlayerGodList;

import java.util.Random;

@Mixin(LivingEntity.class)

public class LivingEntityMixin {
    private LivingEntity living = (LivingEntity) (Object) this;

    @Inject(method = "getHealth", at = @At("RETURN"), cancellable = true)
    private void getHealth(CallbackInfoReturnable<Float> cir) {
        if (PlayerGodList.isGodPlayer(living)){
            cir.setReturnValue(20.0f);
        }
    }
    @Inject(method = "setHealth", at = @At("HEAD"), cancellable = true)
    private void setHealth(float p_21154_, CallbackInfo ci) {
        if (PlayerGodList.isGodPlayer(living)){
            ci.cancel();
        }
    }
    @Inject(method = "remove", at = @At("HEAD"), cancellable = true)
    private void remove(Entity.RemovalReason p_276115_, CallbackInfo ci) {
        if (PlayerGodList.isGodPlayer(living)){
            ci.cancel();
        }
    }
    @Inject(method = "getMaxHealth", at = @At("RETURN"), cancellable = true)
    private void getMaxHealth(CallbackInfoReturnable<Float> cir) {
        if (PlayerGodList.isGodPlayer(living)){
            cir.setReturnValue(20.0f);
        }
    }
    @Inject(method = "die", at = @At("HEAD"), cancellable = true)
    private void die(DamageSource p_21014_, CallbackInfo ci) {
        if (PlayerGodList.isGodPlayer(living)){
            ci.cancel();
        }
    }
    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void hurt(DamageSource p_21016_, float p_21017_, CallbackInfoReturnable<Boolean> cir) {
        if (PlayerGodList.isGodPlayer(living)){
            cir.setReturnValue(false);
            cir.cancel();
        }
    }















}
