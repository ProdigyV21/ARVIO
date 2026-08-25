package org.jsoup.internal;

import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public final class Normalizer {
    public static String lowerCase(String str) {
        return str != null ? str.toLowerCase(Locale.ENGLISH) : "";
    }

    public static String normalize(String str) {
        return lowerCase(str).trim();
    }

    public static String normalize(String str, boolean z) {
        return z ? lowerCase(str) : normalize(str);
    }
}
