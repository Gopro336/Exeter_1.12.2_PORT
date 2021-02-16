/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.api.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public final class LinkHelper {
    public static String getOutput(String link) throws IOException {
        URL url = new URL(link);
        BufferedReader result = new BufferedReader(new InputStreamReader(url.openStream()));
        return result.readLine();
    }
}

