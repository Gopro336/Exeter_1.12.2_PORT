/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package me.friendly.api.minecraft.render;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
//import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CustomFont {
    private static final FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
    private Font theFont;
    private Graphics2D theGraphics;
    private FontMetrics theMetrics;
    private float fontSize;
    private int startChar;
    private int endChar;
    private float[] xPos;
    private float[] yPos;
    private BufferedImage bufferedImage;
    private float extraSpacing = 0.0f;
    private DynamicTexture dynamicTexture;
    private ResourceLocation resourceLocation;
    private final Pattern patternControlCode = Pattern.compile("(?i)\\u00A7[0-9A-FK-OG]");
    private final Pattern patternUnsupported = Pattern.compile("(?i)\\u00A7[K-O]");

    public CustomFont(Object font, float size) {
        this(font, size, 0.0f);
    }

    public CustomFont(Object font) {
        this(font, 18.0f, 0.0f);
    }

    public CustomFont(Object font, float size, float spacing) {
        this.fontSize = size;
        this.startChar = 32;
        this.endChar = 255;
        this.extraSpacing = spacing;
        this.xPos = new float[this.endChar - this.startChar];
        this.yPos = new float[this.endChar - this.startChar];
        this.setupGraphics2D();
        //this.createFont(font, size);
    }

    private final void setupGraphics2D() {
        this.bufferedImage = new BufferedImage(256, 256, 2);
        this.theGraphics = (Graphics2D)this.bufferedImage.getGraphics();
        this.theGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    /*private final void createFont(Object font, float size) {
        try {
            this.theFont = font instanceof Font ? (Font)font : (font instanceof File ? Font.createFont(0, (File)font).deriveFont(size) : (font instanceof InputStream ? Font.createFont(0, (InputStream)font).deriveFont(size) : (font instanceof String ? new Font((String)font, 0, Math.round(size)) : new Font("Verdana", 0, Math.round(size)))));
            this.theGraphics.setFont(this.theFont);
        }
        catch (Exception e) {
            e.printStackTrace();
            this.theFont = new Font("Verdana", 0, Math.round(size));
            this.theGraphics.setFont(this.theFont);
        }
        this.theGraphics.setColor(new Color(255, 255, 255, 0));
        this.theGraphics.fillRect(0, 0, 256, 256);
        this.theGraphics.setColor(Color.white);
        this.theMetrics = this.theGraphics.getFontMetrics();
        float x = 5.0f;
        float y = 5.0f;
        for (int i = this.startChar; i < this.endChar; ++i) {
            this.theGraphics.drawString(Character.toString((char)i), x, y + (float)this.theMetrics.getAscent());
            this.xPos[i - this.startChar] = x;
            this.yPos[i - this.startChar] = y - (float)this.theMetrics.getMaxDescent();
            x += (float)this.theMetrics.stringWidth(Character.toString((char)i)) + 2.0f;
            if (!(x >= (float)(250 - this.theMetrics.getMaxAdvance()))) continue;
            x = 5.0f;
            y += (float)(this.theMetrics.getMaxAscent() + this.theMetrics.getMaxDescent()) + this.fontSize / 2.0f;
        }
        this.dynamicTexture = new DynamicTexture(this.bufferedImage);
        this.resourceLocation = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("font" + font.toString() + size, this.dynamicTexture);
    }*/

    /*public final void drawString(String text, float x, float y, FontType fontType, int color, int color2) {
        GL11.glPushMatrix();
        text = this.stripUnsupported(text);
        GL11.glEnable((int)3042);
        GL11.glEnable((int)3042);
        GL11.glEnable((int)2848);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3008);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glShadeModel((int)7425);
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        String text2 = this.stripControlCodes(text);
        switch (fontType.ordinal()) {
            case 1: {
                this.drawer(text2, x + 0.5f, y, color2);
                this.drawer(text2, x - 0.5f, y, color2);
                this.drawer(text2, x, y + 0.5f, color2);
                this.drawer(text2, x, y - 0.5f, color2);
                break;
            }
            case 2: {
                this.drawer(text2, x + 0.5f, y + 0.5f, color2);
                break;
            }
            case 3: {
                this.drawer(text2, x + 0.5f, y + 1.0f, color2);
                break;
            }
            case 4: {
                this.drawer(text2, x, y + 0.5f, color2);
                break;
            }
            case 5: {
                this.drawer(text2, x, y - 0.5f, color2);
                break;
            }
        }
        this.drawer(text, x, y, color);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        GL11.glShadeModel((int)7424);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }*/

    public void drawCenteredString(String text, float x, float y, int color) {
        //this.drawString(text, x - this.getStringWidth(text) / 2.0f, y, FontType.SHADOW_THIN, color);
        fontRenderer.drawString(text, (int)(x - this.getStringWidth(text) / 2.0f), (int)y, color);
    }

    public final void drawString(String text, float x, float y, FontType fontType, int color) {
        //this.drawString(text, x, y, fontType, color, -1157627904);
        fontRenderer.drawString(text, (int)x, (int)y, color);
    }

    /*private final void drawer(String text, float x, float y, int color) {
        y *= 2.0f;
        GL11.glEnable((int)3553);
        Minecraft.getMinecraft().getTextureManager().bindTexture(this.resourceLocation);
        float alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)alpha);
        float startX = x *= 2.0f;
        for (int i = 0; i < text.length(); ++i) {
            if (text.charAt(i) == '\u00a7' && i + 1 < text.length()) {
                int colorCode;
                char oneMore = Character.toLowerCase(text.charAt(i + 1));
                if (oneMore == 'n') {
                    y += (float)(this.theMetrics.getAscent() + 2);
                    x = startX;
                }
                if ((colorCode = "0123456789abcdefklmnorg".indexOf(oneMore)) < 16) {
                    try {
                        int newColor = Minecraft.getMinecraft().fontRenderer.colorCode[colorCode];
                        GL11.glColor4f((float)((float)(newColor >> 16) / 255.0f), (float)((float)(newColor >> 8 & 0xFF) / 255.0f), (float)((float)(newColor & 0xFF) / 255.0f), (float)alpha);
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else if (oneMore == 'f') {
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
                } else if (oneMore == 'r') {
                    GL11.glColor4f((float)red, (float)green, (float)blue, (float)alpha);
                } else if (oneMore == 'g') {
                    GL11.glColor4f((float)0.47f, (float)0.67f, (float)0.27f, (float)alpha);
                }
                ++i;
                continue;
            }
            try {
                char c = text.charAt(i);
                this.drawChar(c, x, y);
                x += this.getStringWidth(Character.toString(c)) * 2.0f;
                continue;
            }
            catch (ArrayIndexOutOfBoundsException indexException) {
                char c = text.charAt(i);
                System.out.println("Can't draw character: " + c + " (" + Character.getNumericValue(c) + ")");
            }
        }
    }*/

    public final float getStringWidth(String text) {
        return (float)(this.getBounds(text).getWidth() + (double)this.extraSpacing) / 2.0f;
    }

    public final float getStringHeight(String text) {
        return (float)this.getBounds(text).getHeight() / 2.0f;
    }

    private final Rectangle2D getBounds(String text) {
        return this.theMetrics.getStringBounds(text, this.theGraphics);
    }

    private final void drawChar(char character, float x, float y) throws ArrayIndexOutOfBoundsException {
        Rectangle2D bounds = this.theMetrics.getStringBounds(Character.toString(character), this.theGraphics);
        this.drawTexturedModalRect(x, y, this.xPos[character - this.startChar], this.yPos[character - this.startChar], (float)bounds.getWidth(), (float)bounds.getHeight() + (float)this.theMetrics.getMaxDescent() + 1.0f);
    }

    private final List listFormattedStringToWidth(String s, int width) {
        return Arrays.asList(this.wrapFormattedStringToWidth(s, width).split("\n"));
    }

    private final String wrapFormattedStringToWidth(String s, float width) {
        int wrapWidth = this.sizeStringToWidth(s, width);
        if (s.length() <= wrapWidth) {
            return s;
        }
        String split = s.substring(0, wrapWidth);
        String split2 = this.getFormatFromString(split) + s.substring(wrapWidth + (s.charAt(wrapWidth) == ' ' || s.charAt(wrapWidth) == '\n' ? 1 : 0));
        try {
            return split + "\n" + this.wrapFormattedStringToWidth(split2, width);
        }
        catch (Exception e) {
            System.out.println("Cannot wrap string to width.");
            return "";
        }
    }

    private final int sizeStringToWidth(String par1Str, float par2) {
        int var5;
        int var3 = par1Str.length();
        float var4 = 0.0f;
        int var6 = -1;
        boolean var7 = false;
        for (var5 = 0; var5 < var3; ++var5) {
            char var8 = par1Str.charAt(var5);
            switch (var8) {
                case '\n': {
                    --var5;
                    break;
                }
                case '\u00a7': {
                    char var9;
                    if (var5 >= var3 - 1) break;
                    if ((var9 = par1Str.charAt(++var5)) != 'l' && var9 != 'L') {
                        if (var9 != 'r' && var9 != 'R' && !this.isFormatColor(var9)) break;
                        var7 = false;
                        break;
                    }
                    var7 = true;
                    break;
                }
                case ' ': {
                    var6 = var5;
                }
                case '-': {
                    var6 = var5;
                }
                case '_': {
                    var6 = var5;
                }
                case ':': {
                    var6 = var5;
                }
                default: {
                    String text = String.valueOf(var8);
                    var4 += this.getStringWidth(text);
                    if (!var7) break;
                    var4 += 1.0f;
                }
            }
            if (var8 == '\n') {
                var6 = ++var5;
                continue;
            }
            if (var4 > par2) break;
        }
        return var5 != var3 && var6 != -1 && var6 < var5 ? var6 : var5;
    }

    private final String getFormatFromString(String par0Str) {
        String var1 = "";
        int var2 = -1;
        int var3 = par0Str.length();
        while ((var2 = par0Str.indexOf(167, var2 + 1)) != -1) {
            if (var2 >= var3 - 1) continue;
            char var4 = par0Str.charAt(var2 + 1);
            if (this.isFormatColor(var4)) {
                var1 = "\u00a7" + var4;
                continue;
            }
            if (!this.isFormatSpecial(var4)) continue;
            var1 = var1 + "\u00a7" + var4;
        }
        return var1;
    }

    private final boolean isFormatColor(char par0) {
        return par0 >= '0' && par0 <= '9' || par0 >= 'a' && par0 <= 'f' || par0 >= 'A' && par0 <= 'F';
    }

    private final boolean isFormatSpecial(char par0) {
        return par0 >= 'k' && par0 <= 'o' || par0 >= 'K' && par0 <= 'O' || par0 == 'r' || par0 == 'R';
    }

    private final void drawTexturedModalRect(float x, float y, float u, float v, float width, float height) {
        float scale = 0.0039063f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        //WorldRenderer renderer = tessellator.getWorldRenderer();
        /*renderer.startDrawingQuads();
        renderer.addVertexWithUV(x + 0.0f, y + height, 0.0, (u + 0.0f) * scale, (v + height) * scale);
        renderer.addVertexWithUV(x + width, y + height, 0.0, (u + width) * scale, (v + height) * scale);
        renderer.addVertexWithUV(x + width, y + 0.0f, 0.0, (u + width) * scale, (v + 0.0f) * scale);
        renderer.addVertexWithUV(x + 0.0f, y + 0.0f, 0.0, (u + 0.0f) * scale, (v + 0.0f) * scale);*/
        tessellator.draw();
    }

    public final String stripControlCodes(String s) {
        return this.patternControlCode.matcher(s).replaceAll("");
    }

    public final String stripUnsupported(String s) {
        return this.patternUnsupported.matcher(s).replaceAll("");
    }

    public final Graphics2D getGraphics() {
        return this.theGraphics;
    }

    public final Font getFont() {
        return this.theFont;
    }

    public static enum FontType {
        NORMAL,
        SHADOW_THICK,
        SHADOW_THIN,
        OUTLINE_THIN,
        EMBOSS_TOP,
        EMBOSS_BOTTOM;

    }
}

