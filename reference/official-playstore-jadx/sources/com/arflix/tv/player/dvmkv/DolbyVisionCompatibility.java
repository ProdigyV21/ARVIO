package com.arflix.tv.player.dvmkv;

import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Util;
import j$.util.Objects;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public final class DolbyVisionCompatibility {
    private static volatile Object fragmentedMp4DolbyVisionSampleTransformer;
    private static volatile boolean isHdr10BaseLayerModeActive;
    private static volatile boolean mapDv7ToHevcEnabled;
    private static volatile Object matroskaDolbyVisionSampleTransformer;
    private static volatile Object mp4DolbyVisionSampleTransformer;
    private static volatile Object tsDolbyVisionNalTransformer;

    private DolbyVisionCompatibility() {
    }

    public static String chooseHevcCodecsString(String str, String str2) {
        if (isHevcCodecsString(str)) {
            return str;
        }
        if (isHevcCodecsString(str2)) {
            return str2;
        }
        return null;
    }

    private static String extractDolbyVisionCodec(String str) {
        if (str == null) {
            return null;
        }
        for (String str2 : Util.splitCodecs(str)) {
            String lowerCase = str2.trim().toLowerCase(Locale.US);
            if (lowerCase.startsWith("dvhe") || lowerCase.startsWith("dvh1")) {
                return lowerCase;
            }
        }
        return null;
    }

    public static Object getFragmentedMp4DolbyVisionSampleTransformer() {
        return fragmentedMp4DolbyVisionSampleTransformer;
    }

    public static Object getMatroskaDolbyVisionSampleTransformer() {
        return matroskaDolbyVisionSampleTransformer;
    }

    public static Object getMp4DolbyVisionSampleTransformer() {
        return mp4DolbyVisionSampleTransformer;
    }

    public static Object getTsDolbyVisionNalTransformer() {
        return tsDolbyVisionNalTransformer;
    }

    public static boolean isDolbyVisionProfile7(String str) {
        String strExtractDolbyVisionCodec = extractDolbyVisionCodec(str);
        if (strExtractDolbyVisionCodec == null) {
            return false;
        }
        String[] strArrSplit = strExtractDolbyVisionCodec.split("\\.");
        return strArrSplit.length >= 2 && parseIntOrUnset(strArrSplit[1]) == 7;
    }

    public static boolean isHdr10BaseLayerModeActive() {
        return isHdr10BaseLayerModeActive;
    }

    private static boolean isHevcCodecsString(String str) {
        if (str == null) {
            return false;
        }
        String strTrim = str.trim();
        return strTrim.startsWith("hvc1") || strTrim.startsWith("hev1");
    }

    public static boolean isMapDv7ToHevcEnabled() {
        return mapDv7ToHevcEnabled;
    }

    private static int parseIntOrUnset(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public static void setFragmentedMp4DolbyVisionSampleTransformer(Object obj) {
        fragmentedMp4DolbyVisionSampleTransformer = obj;
    }

    public static void setHdr10BaseLayerModeActive(boolean z) {
        isHdr10BaseLayerModeActive = z;
    }

    public static void setMapDv7ToHevcEnabled(boolean z) {
        mapDv7ToHevcEnabled = z;
    }

    public static void setMatroskaDolbyVisionSampleTransformer(Object obj) {
        matroskaDolbyVisionSampleTransformer = obj;
    }

    public static void setMp4DolbyVisionSampleTransformer(Object obj) {
        mp4DolbyVisionSampleTransformer = obj;
    }

    public static void setTsDolbyVisionNalTransformer(Object obj) {
        tsDolbyVisionNalTransformer = obj;
    }

    public static boolean shouldMapDolbyVisionProfile7(String str, String str2) {
        if (!mapDv7ToHevcEnabled) {
            return false;
        }
        if (str == null || str.equals(MimeTypes.VIDEO_DOLBY_VISION)) {
            return (str2 == null || str2.trim().isEmpty()) ? Objects.equals(str, MimeTypes.VIDEO_DOLBY_VISION) : extractDolbyVisionCodec(str2) != null;
        }
        return false;
    }
}
