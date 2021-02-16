/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonObject
 *  org.apache.commons.codec.binary.Base64
 */
package me.friendly.exeter.command.impl.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.imageio.ImageIO;
import me.friendly.api.stopwatch.Stopwatch;
import me.friendly.exeter.command.Argument;
import me.friendly.exeter.command.Command;
import me.friendly.exeter.logging.Logger;
import net.minecraft.util.ScreenShotHelper;
import org.apache.commons.codec.binary.Base64;

public final class ScreenShot
extends Command {
    private final Stopwatch stopwatch = new Stopwatch();

    public ScreenShot() {
        super(new String[]{"screenshot"}, new Argument[0]);
    }

    @Override
    public String dispatch() {
        ScreenShotHelper.saveScreenshot(this.minecraft.gameDir, this.minecraft.displayWidth, this.minecraft.displayHeight, this.minecraft.getFramebuffer());
        File screenshots = new File("screenshots");
        File[] files = screenshots.listFiles(File::isFile);
        long timeModified = Long.MIN_VALUE;
        File lastModified = null;
        for (File file : files) {
            if (file.lastModified() <= timeModified) continue;
            lastModified = file;
            timeModified = file.lastModified();
        }
        if (lastModified != null) {
            this.uploadImage(lastModified);
        } else {
            Logger.getLogger().printToChat("Unable to locare screenshot.");
        }
        return "Uploading screenshot!";
    }

    public void uploadImage(final File file) {
        Thread thread = new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    String line;
                    BufferedImage image = ImageIO.read(new File(file.getAbsolutePath()));
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    ImageIO.write((RenderedImage)image, "png", byteArray);
                    byte[] fileByes = byteArray.toByteArray();
                    String base64File = Base64.encodeBase64String((byte[])fileByes);
                    String postData = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(base64File, "UTF-8");
                    URL imgurApi = new URL("https://api.imgur.com/3/image");
                    HttpURLConnection connection = (HttpURLConnection)imgurApi.openConnection();
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Authorization", "Client-ID 57e0280fe5e3a5e");
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.connect();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
                    outputStreamWriter.write(postData);
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    StringBuilder stringBuilder = new StringBuilder();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((line = rd.readLine()) != null) {
                        stringBuilder.append(line).append(System.lineSeparator());
                    }
                    rd.close();
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    JsonObject json = (JsonObject)gson.fromJson(stringBuilder.toString(), JsonObject.class);
                    String url = "http://i.imgur.com/" + json.get("data").getAsJsonObject().get("id").getAsString() + ".png";
                    StringSelection contents = new StringSelection(url);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(contents, null);
                    Logger.getLogger().printToChat("Screenshot URL copied to clipboard.");
                }
                catch (IOException e) {
                    Logger.getLogger().printToChat("Unable to upload screenshot.");
                    e.printStackTrace();
                }
                if (!file.delete()) {
                    Logger.getLogger().printToChat("Unable to delete screenshot.");
                }
            }
        });
        thread.setName("Screenshot Upload Thread");
        thread.start();
    }
}

