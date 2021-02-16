/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package me.friendly.api.minecraft.render;

import java.awt.Color;
import java.awt.Rectangle;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
//import net.minecraft.client.renderer.WorldRenderer;
//import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public final class RenderMethods {
    public static FloatBuffer matModelView = GLAllocation.createDirectFloatBuffer(16);
    public static FloatBuffer matProjection = GLAllocation.createDirectFloatBuffer(16);

    public static Color rainbow(long offset, float fade) {
        float hue = (float)(System.nanoTime() + offset) / 1.0E10f % 1.0f;
        long color = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(hue, 1.0f, 1.0f)), 16);
        Color c = new Color((int)color);
        return new Color((float)c.getRed() / 255.0f * fade, (float)c.getGreen() / 255.0f * fade, (float)c.getBlue() / 255.0f * fade, (float)c.getAlpha() / 255.0f);
    }

    public static Color blend(Color color1, Color color2, float ratio) {
        float rat = 1.0f - ratio;
        float[] rgb1 = new float[3];
        float[] rgb2 = new float[3];
        color1.getColorComponents(rgb1);
        color2.getColorComponents(rgb2);
        Color color = new Color(rgb1[0] * ratio + rgb2[0] * rat, rgb1[1] * ratio + rgb2[1] * rat, rgb1[2] * ratio + rgb2[2] * rat);
        return color;
    }

    public static double getDiff(double lastI, double i, float ticks, double ownI) {
        return lastI + (i - lastI) * (double)ticks - ownI;
    }

    public static void enableGL2D() {
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
    }

    public static void enableGL3D() {
        GL11.glDisable((int)3008);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)2884);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4353);
        GL11.glDisable((int)2896);
    }

    public static void disableGL3D() {
        GL11.glEnable((int)2896);
        GL11.glDisable((int)2848);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glDepthMask((boolean)true);
        GL11.glCullFace((int)1029);
    }

    public static void disableGL2D() {
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)2848);
        GL11.glHint((int)3154, (int)4352);
        GL11.glHint((int)3155, (int)4352);
    }

    public static void drawTriangle(int x, int y, int type, int size, int color) {
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        float alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        GL11.glColor4f((float)r, (float)g, (float)b, (float)alpha);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)1.0f);
        GL11.glShadeModel((int)7425);
        switch (type) {
            case 0: {
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)x, (double)(y + size));
                GL11.glVertex2d((double)(x + size), (double)(y - size));
                GL11.glVertex2d((double)(x - size), (double)(y - size));
                GL11.glEnd();
                GL11.glBegin((int)4);
                GL11.glVertex2d((double)x, (double)(y + size));
                GL11.glVertex2d((double)(x + size), (double)(y - size));
                GL11.glVertex2d((double)(x - size), (double)(y - size));
                GL11.glEnd();
                break;
            }
            case 1: {
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)x, (double)y);
                GL11.glVertex2d((double)x, (double)(y + size / 2));
                GL11.glVertex2d((double)(x + size + size / 2), (double)y);
                GL11.glEnd();
                GL11.glBegin((int)4);
                GL11.glVertex2d((double)x, (double)y);
                GL11.glVertex2d((double)x, (double)(y + size / 2));
                GL11.glVertex2d((double)(x + size + size / 2), (double)y);
                GL11.glEnd();
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)x, (double)y);
                GL11.glVertex2d((double)((double)x + (double)size * 1.25), (double)(y - size / 2));
                GL11.glVertex2d((double)((double)x + (double)size * 1.25), (double)(y + size / 2));
                GL11.glEnd();
                GL11.glBegin((int)4);
                GL11.glVertex2d((double)((double)x + (double)size * 1.25), (double)(y - size / 2));
                GL11.glVertex2d((double)x, (double)y);
                GL11.glVertex2d((double)((double)x + (double)size * 1.25), (double)(y + size / 2));
                GL11.glEnd();
            }
        }
        GL11.glDisable((int)2848);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
    }

    public static void enableGL3D(float lineWidth) {
        GL11.glDisable((int)3008);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)2884);
        Minecraft.getMinecraft().entityRenderer.enableLightmap();
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
        GL11.glLineWidth((float)lineWidth);
    }

    public static int applyTexture(int texId, int width, int height, ByteBuffer pixels, boolean linear, boolean repeat) {
        GL11.glBindTexture((int)3553, (int)texId);
        GL11.glTexParameteri((int)3553, (int)10241, (int)(linear ? 9729 : 9728));
        GL11.glTexParameteri((int)3553, (int)10240, (int)(linear ? 9729 : 9728));
        GL11.glTexParameteri((int)3553, (int)10242, (int)(repeat ? 10497 : 10496));
        GL11.glTexParameteri((int)3553, (int)10243, (int)(repeat ? 10497 : 10496));
        GL11.glPixelStorei((int)3317, (int)1);
        GL11.glTexImage2D((int)3553, (int)0, (int)32856, (int)width, (int)height, (int)0, (int)6408, (int)5121, (ByteBuffer)pixels);
        return texId;
    }

    public static void drawLine(float x, float y, float x1, float y1, float width) {
        GL11.glDisable((int)3553);
        GL11.glLineWidth((float)width);
        GL11.glBegin((int)1);
        GL11.glVertex2f((float)x, (float)y);
        GL11.glVertex2f((float)x1, (float)y1);
        GL11.glEnd();
        GL11.glEnable((int)3553);
    }

    /*public static void drawRect(Rectangle rectangle, int color) {
        RenderMethods.drawRect(rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height, color);
    }*/

    public static void drawRect(float x, float y, float x1, float y1, int color) {
        RenderMethods.enableGL2D();
        RenderMethods.glColor(color);
        RenderMethods.drawRect(x, y, x1, y1);
        RenderMethods.disableGL2D();
    }

    public static void drawBorderedRect(float x, float y, float x1, float y1, float width, int internalColor, int borderColor) {
        RenderMethods.enableGL2D();
        RenderMethods.glColor(internalColor);
        RenderMethods.drawRect(x + width, y + width, x1 - width, y1 - width);
        RenderMethods.glColor(borderColor);
        RenderMethods.drawRect(x + width, y, x1 - width, y + width);
        RenderMethods.drawRect(x, y, x + width, y1);
        RenderMethods.drawRect(x1 - width, y, x1, y1);
        RenderMethods.drawRect(x + width, y1 - width, x1 - width, y1);
        RenderMethods.disableGL2D();
    }

    public static void drawBorderedRect(float x, float y, float x1, float y1, int insideC, int borderC) {
        RenderMethods.enableGL2D();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        RenderMethods.drawVLine(x *= 2.0f, y *= 2.0f, (y1 *= 2.0f) - 1.0f, borderC);
        RenderMethods.drawVLine((x1 *= 2.0f) - 1.0f, y, y1, borderC);
        RenderMethods.drawHLine(x, x1 - 1.0f, y, borderC);
        RenderMethods.drawHLine(x, x1 - 2.0f, y1 - 1.0f, borderC);
        RenderMethods.drawRect(x + 1.0f, y + 1.0f, x1 - 1.0f, y1 - 1.0f, insideC);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        RenderMethods.disableGL2D();
    }

    public static void drawBorderedRectReliant(float x, float y, float x1, float y1, float lineWidth, int inside, int border) {
        RenderMethods.enableGL2D();
        RenderMethods.drawRect(x, y, x1, y1, inside);
        RenderMethods.glColor(border);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glLineWidth((float)lineWidth);
        GL11.glBegin((int)3);
        GL11.glVertex2f((float)x, (float)y);
        GL11.glVertex2f((float)x, (float)y1);
        GL11.glVertex2f((float)x1, (float)y1);
        GL11.glVertex2f((float)x1, (float)y);
        GL11.glVertex2f((float)x, (float)y);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        RenderMethods.disableGL2D();
    }

    public static void drawGradientBorderedRectReliant(float x, float y, float x1, float y1, float lineWidth, int border, int bottom, int top) {
        RenderMethods.enableGL2D();
        RenderMethods.drawGradientRect(x, y, x1, y1, top, bottom);
        RenderMethods.glColor(border);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glLineWidth((float)lineWidth);
        GL11.glBegin((int)3);
        GL11.glVertex2f((float)x, (float)y);
        GL11.glVertex2f((float)x, (float)y1);
        GL11.glVertex2f((float)x1, (float)y1);
        GL11.glVertex2f((float)x1, (float)y);
        GL11.glVertex2f((float)x, (float)y);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        RenderMethods.disableGL2D();
    }

    public static void drawRoundedRect(float x, float y, float x1, float y1, int borderC, int insideC) {
        RenderMethods.enableGL2D();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        RenderMethods.drawVLine(x *= 2.0f, (y *= 2.0f) + 1.0f, (y1 *= 2.0f) - 2.0f, borderC);
        RenderMethods.drawVLine((x1 *= 2.0f) - 1.0f, y + 1.0f, y1 - 2.0f, borderC);
        RenderMethods.drawHLine(x + 2.0f, x1 - 3.0f, y, borderC);
        RenderMethods.drawHLine(x + 2.0f, x1 - 3.0f, y1 - 1.0f, borderC);
        RenderMethods.drawHLine(x + 1.0f, x + 1.0f, y + 1.0f, borderC);
        RenderMethods.drawHLine(x1 - 2.0f, x1 - 2.0f, y + 1.0f, borderC);
        RenderMethods.drawHLine(x1 - 2.0f, x1 - 2.0f, y1 - 2.0f, borderC);
        RenderMethods.drawHLine(x + 1.0f, x + 1.0f, y1 - 2.0f, borderC);
        RenderMethods.drawRect(x + 1.0f, y + 1.0f, x1 - 1.0f, y1 - 1.0f, insideC);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        RenderMethods.disableGL2D();
    }

    /*public static void drawBorderedRect(Rectangle rectangle, float width, int internalColor, int borderColor) {
        float x = rectangle.x;
        float y = rectangle.y;
        float x1 = rectangle.x + rectangle.width;
        float y1 = rectangle.y + rectangle.height;
        RenderMethods.enableGL2D();
        RenderMethods.glColor(internalColor);
        RenderMethods.drawRect(x + width, y + width, x1 - width, y1 - width);
        RenderMethods.glColor(borderColor);
        RenderMethods.drawRect(x + 1.0f, y, x1 - 1.0f, y + width);
        RenderMethods.drawRect(x, y, x + width, y1);
        RenderMethods.drawRect(x1 - width, y, x1, y1);
        RenderMethods.drawRect(x + 1.0f, y1 - width, x1 - 1.0f, y1);
        RenderMethods.disableGL2D();
    }*/

    public static void drawGradientRect(float x, float y, float x1, float y1, int topColor, int bottomColor) {
        RenderMethods.enableGL2D();
        GL11.glShadeModel((int)7425);
        GL11.glBegin((int)7);
        RenderMethods.glColor(topColor);
        GL11.glVertex2f((float)x, (float)y1);
        GL11.glVertex2f((float)x1, (float)y1);
        RenderMethods.glColor(bottomColor);
        GL11.glVertex2f((float)x1, (float)y);
        GL11.glVertex2f((float)x, (float)y);
        GL11.glEnd();
        GL11.glShadeModel((int)7424);
        RenderMethods.disableGL2D();
    }

    public static void drawGradientHRect(float x, float y, float x1, float y1, int topColor, int bottomColor) {
        RenderMethods.enableGL2D();
        GL11.glShadeModel((int)7425);
        GL11.glBegin((int)7);
        RenderMethods.glColor(topColor);
        GL11.glVertex2f((float)x, (float)y);
        GL11.glVertex2f((float)x, (float)y1);
        RenderMethods.glColor(bottomColor);
        GL11.glVertex2f((float)x1, (float)y1);
        GL11.glVertex2f((float)x1, (float)y);
        GL11.glEnd();
        GL11.glShadeModel((int)7424);
        RenderMethods.disableGL2D();
    }

    public static void drawGradientRect(double x, double y, double x2, double y2, int col1, int col2) {
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glShadeModel((int)7425);
        GL11.glPushMatrix();
        GL11.glBegin((int)7);
        RenderMethods.glColor(col1);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        RenderMethods.glColor(col2);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glShadeModel((int)7424);
    }

    public static void drawGradientBorderedRect(double x, double y, double x2, double y2, float l1, int col1, int col2, int col3) {
        RenderMethods.enableGL2D();
        GL11.glPushMatrix();
        RenderMethods.glColor(col1);
        GL11.glLineWidth((float)1.0f);
        GL11.glBegin((int)1);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        RenderMethods.drawGradientRect(x, y, x2, y2, col2, col3);
        RenderMethods.disableGL2D();
    }

    public static void drawStrip(int x, int y, float width, double angle, float points, float radius, int color) {
        float yc;
        float xc;
        float a;
        int i;
        float f1 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(color & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)0.0);
        GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)f1);
        GL11.glLineWidth((float)width);
        if (angle > 0.0) {
            GL11.glBegin((int)3);
            i = 0;
            while ((double)i < angle) {
                a = (float)((double)i * (angle * Math.PI / (double)points));
                xc = (float)(Math.cos(a) * (double)radius);
                yc = (float)(Math.sin(a) * (double)radius);
                GL11.glVertex2f((float)xc, (float)yc);
                ++i;
            }
            GL11.glEnd();
        }
        if (angle < 0.0) {
            GL11.glBegin((int)3);
            i = 0;
            while ((double)i > angle) {
                a = (float)((double)i * (angle * Math.PI / (double)points));
                xc = (float)(Math.cos(a) * (double)(-radius));
                yc = (float)(Math.sin(a) * (double)(-radius));
                GL11.glVertex2f((float)xc, (float)yc);
                --i;
            }
            GL11.glEnd();
        }
        RenderMethods.disableGL2D();
        GL11.glDisable((int)3479);
        GL11.glPopMatrix();
    }

    public static void drawHLine(float x, float y, float x1, int y1) {
        if (y < x) {
            float var5 = x;
            x = y;
            y = var5;
        }
        RenderMethods.drawRect(x, x1, y + 1.0f, x1 + 1.0f, y1);
    }

    public static void drawVLine(float x, float y, float x1, int y1) {
        if (x1 < y) {
            float var5 = y;
            y = x1;
            x1 = var5;
        }
        RenderMethods.drawRect(x, y + 1.0f, x + 1.0f, x1, y1);
    }

    public static void drawHLine(float x, float y, float x1, int y1, int y2) {
        if (y < x) {
            float var5 = x;
            x = y;
            y = var5;
        }
        RenderMethods.drawGradientRect(x, x1, y + 1.0f, x1 + 1.0f, y1, y2);
    }

    public static void drawRect(float x, float y, float x1, float y1, float r, float g, float b, float a) {
        RenderMethods.enableGL2D();
        GL11.glColor4f((float)r, (float)g, (float)b, (float)a);
        RenderMethods.drawRect(x, y, x1, y1);
        RenderMethods.disableGL2D();
    }

    public static void drawRect(float x, float y, float x1, float y1) {
        GL11.glBegin((int)7);
        GL11.glVertex2f((float)x, (float)y1);
        GL11.glVertex2f((float)x1, (float)y1);
        GL11.glVertex2f((float)x1, (float)y);
        GL11.glVertex2f((float)x, (float)y);
        GL11.glEnd();
    }

    /*public static void rectangle(double left, double top, double right, double bottom, int color) {
        double var5;
        if (left < right) {
            var5 = left;
            left = right;
            right = var5;
        }
        if (top < bottom) {
            var5 = top;
            top = bottom;
            bottom = var5;
        }
        float alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        Tessellator var9 = Tessellator.getInstance();
        WorldRenderer var10 = var9.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableLighting();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(red, green, blue, alpha);
        var10.startDrawingQuads();
        var10.addVertex(left, bottom, 0.0);
        var10.addVertex(right, bottom, 0.0);
        var10.addVertex(right, top, 0.0);
        var10.addVertex(left, top, 0.0);
        var9.draw();
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
    }*/

    public static void drawCircle(float cx, float cy, float r, int num_segments, int c) {
        cx *= 2.0f;
        cy *= 2.0f;
        float f = (float)(c >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(c >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(c >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(c & 0xFF) / 255.0f;
        float theta = (float)(6.2831852 / (double)num_segments);
        float p = (float)Math.cos(theta);
        float s = (float)Math.sin(theta);
        float x = r *= 2.0f;
        float y = 0.0f;
        RenderMethods.enableGL2D();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glBegin((int)2);
        for (int ii = 0; ii < num_segments; ++ii) {
            GL11.glVertex2f((float)(x + cx), (float)(y + cy));
            float t = x;
            x = p * x - s * y;
            y = s * t + p * y;
        }
        GL11.glEnd();
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        RenderMethods.disableGL2D();
    }

    public static void drawFullCircle(int cx, int cy, double r, int c) {
        r *= 2.0;
        cx *= 2;
        cy *= 2;
        float f = (float)(c >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(c >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(c >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(c & 0xFF) / 255.0f;
        RenderMethods.enableGL2D();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glBegin((int)6);
        for (int i = 0; i <= 360; ++i) {
            double x = Math.sin((double)i * Math.PI / 180.0) * r;
            double y = Math.cos((double)i * Math.PI / 180.0) * r;
            GL11.glVertex2d((double)((double)cx + x), (double)((double)cy + y));
        }
        GL11.glEnd();
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        RenderMethods.disableGL2D();
    }

    public static void glColor(Color color) {
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
    }

    public static void glColor(int hex) {
        float alpha = (float)(hex >> 24 & 0xFF) / 255.0f;
        float red = (float)(hex >> 16 & 0xFF) / 255.0f;
        float green = (float)(hex >> 8 & 0xFF) / 255.0f;
        float blue = (float)(hex & 0xFF) / 255.0f;
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)alpha);
    }

    public static void glColor(float alpha, int redRGB, int greenRGB, int blueRGB) {
        float red = 0.003921569f * (float)redRGB;
        float green = 0.003921569f * (float)greenRGB;
        float blue = 0.003921569f * (float)blueRGB;
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)alpha);
    }

    public static void drawOutlinedBox(AxisAlignedBB box) {
        if (box == null) {
            return;
        }
        GL11.glBegin((int)3);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.maxZ);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.maxZ);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.minZ);
        GL11.glEnd();
        GL11.glBegin((int)3);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.maxZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.maxZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.minZ);
        GL11.glEnd();
        GL11.glBegin((int)1);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.maxZ);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.maxZ);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.maxZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.maxZ);
        GL11.glEnd();
    }

    public static void renderCrosses(AxisAlignedBB box) {
        GL11.glBegin((int)1);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.maxZ);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.minZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.maxZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.maxZ);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.maxZ);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.maxZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.minZ);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.maxZ);
        GL11.glEnd();
    }

    public static void drawBox(AxisAlignedBB box) {
        if (box == null) {
            return;
        }
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.maxZ);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.maxZ);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.maxZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.maxZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.maxZ);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.maxZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.maxZ);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.maxZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.maxZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.maxZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.minZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.maxZ);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.minZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.maxZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.maxZ);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.maxZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.maxZ);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.maxZ);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.minZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.minZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.minZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.minZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.maxZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.maxZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.minZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.minZ);
        GL11.glVertex3d((double)box.minX, (double)box.maxY, (double)box.maxZ);
        GL11.glVertex3d((double)box.maxX, (double)box.maxY, (double)box.maxZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.maxZ);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.maxZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.minZ);
        GL11.glVertex3d((double)box.minX, (double)box.minY, (double)box.maxZ);
        GL11.glVertex3d((double)box.maxX, (double)box.minY, (double)box.maxZ);
        GL11.glEnd();
    }
}

