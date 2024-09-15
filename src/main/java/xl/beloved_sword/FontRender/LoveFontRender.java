package xl.beloved_sword.FontRender;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.font.FontSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.awt.*;
import java.util.function.Function;

public class LoveFontRender extends Font {
    public LoveFontRender(Function<ResourceLocation, FontSet> p_243253_, boolean p_243245_) {
        super(p_243253_, p_243245_);
    }

    public static Font getFont(){
        return new LoveFontRender(Minecraft.getInstance().font.fonts,false);
    }

    long getSystemTime() {
        return System.currentTimeMillis() * 1000L / System.nanoTime();
    }

    public int drawInBatch(@NotNull FormattedCharSequence formattedCharSequence, float x, float y, int rgb, boolean b1, @NotNull Matrix4f matrix4f, @NotNull MultiBufferSource multiBufferSource, @NotNull DisplayMode mode, int i, int i1) {
        StringBuilder stringBuilder = new StringBuilder();
        formattedCharSequence.accept((index, style, codePoint) -> {
            stringBuilder.appendCodePoint(codePoint);
            return true;
        });
        String text = ChatFormatting.stripFormatting(stringBuilder.toString());
        if (text != null) {
            float hueOffset = (float) Util.getMillis() / 800.0F;
            for (int index = 0; index < text.length(); ++index) {
                String s = String.valueOf(text.charAt(index));
                float  yOffset = (float) Math.sin((float) index   + (float) Util.getMillis() /400) * 4;
                float   xOffset = (float) Math.cos((float) index  + (float) Util.getMillis() /200);
                float yOffset2 = (float)Math.sin((double)((float)index + (float)milliTime() / 300.0F));
                float xOffset2 = (float)Math.cos((double)((float)index + (float)milliTime() / 300.0F));
                float hue = (hueOffset + (float) index / (float) text.length()) % 1.0F;
                int c = rgb & -16777216 | Mth.hsvToRgb(hue, 0.9f, 1.0f);
                super.drawInBatch(s, x, y + yOffset   , c, b1, matrix4f, multiBufferSource, mode, i, i1);
                x += (float) this.width(s);
            }
        }
        return (int)x;
    }

    public static long milliTime() {
        return System.nanoTime() / 1000000L;
    }
    public static int getColor() {
        return (Color.HSBtoRGB((float) ((float)  milliTime() / 7000.0F % 20.0),0.8f,0.8f));
    }
}
