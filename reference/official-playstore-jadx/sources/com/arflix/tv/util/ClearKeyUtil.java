package com.arflix.tv.util;

import android.util.Base64;
import androidx.compose.material3.d;
import androidx.fragment.app.a2;
import com.google.common.util.concurrent.r0;
import java.util.List;
import kotlin.Metadata;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007J\u0010\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\u0007J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0002¨\u0006\u000e"}, d2 = {"Lcom/arflix/tv/util/ClearKeyUtil;", "", "<init>", "()V", "drmSchemeToUuid", "Ljava/util/UUID;", "scheme", "", "normalizeScheme", "raw", "buildClearKeyLicenseUri", "kidKeyHex", "hexToBase64Url", "hex", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ClearKeyUtil {
    public static final int $stable = 0;
    public static final ClearKeyUtil INSTANCE = new ClearKeyUtil();

    private ClearKeyUtil() {
    }

    private final String hexToBase64Url(String hex) {
        if (hex.length() % 2 != 0) {
            return null;
        }
        try {
            int length = hex.length() / 2;
            byte[] bArr = new byte[length];
            for (int i10 = 0; i10 < length; i10++) {
                int i11 = i10 * 2;
                String strSubstring = hex.substring(i11, i11 + 2);
                r0.c(16);
                bArr[i10] = (byte) Integer.parseInt(strSubstring, 16);
            }
            return Base64.encodeToString(bArr, 11);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public final String buildClearKeyLicenseUri(String kidKeyHex) {
        String strHexToBase64Url;
        String strHexToBase64Url2;
        List listX0 = o.x0(kidKeyHex, new char[]{':'}, 6);
        if (listX0.size() != 2) {
            return null;
        }
        String string = o.L0((String) listX0.get(0)).toString();
        String string2 = o.L0((String) listX0.get(1)).toString();
        if (string.length() != 32 || string2.length() != 32 || (strHexToBase64Url = hexToBase64Url(string)) == null || (strHexToBase64Url2 = hexToBase64Url(string2)) == null) {
            return null;
        }
        return d.C("data:application/json;base64,", Base64.encodeToString(a2.m("{\"keys\":[{\"kty\":\"oct\",\"kid\":\"", strHexToBase64Url, "\",\"k\":\"", strHexToBase64Url2, "\"}],\"type\":\"temporary\"}").getBytes(kotlin.text.a.f19924a), 3));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
    
        if (r0.equals("com.microsoft.playready") == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
    
        if (r0.equals("widevine") == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003d, code lost:
    
        return androidx.media3.common.C.WIDEVINE_UUID;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0044, code lost:
    
        if (r0.equals("org.w3.clearkey") != false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0048, code lost:
    
        return androidx.media3.common.C.CLEARKEY_UUID;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004f, code lost:
    
        if (r0.equals("playready") == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005b, code lost:
    
        return androidx.media3.common.C.PLAYREADY_UUID;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001c, code lost:
    
        if (r0.equals("clearkey") == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0025, code lost:
    
        if (r0.equals("com.widevine.alpha") == false) goto L31;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.UUID drmSchemeToUuid(java.lang.String r3) {
        /*
            r2 = this;
            java.util.Locale r0 = java.util.Locale.ROOT
            java.lang.String r0 = r3.toLowerCase(r0)
            java.lang.CharSequence r0 = kotlin.text.o.L0(r0)
            java.lang.String r0 = r0.toString()
            int r1 = r0.hashCode()
            switch(r1) {
                case -1860423953: goto L49;
                case -1746801670: goto L3e;
                case -1400551171: goto L31;
                case -1380835838: goto L28;
                case -6637254: goto L1f;
                case 790309106: goto L16;
                default: goto L15;
            }
        L15:
            goto L51
        L16:
            java.lang.String r1 = "clearkey"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L46
            goto L51
        L1f:
            java.lang.String r1 = "com.widevine.alpha"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L3b
            goto L51
        L28:
            java.lang.String r1 = "com.microsoft.playready"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L59
            goto L51
        L31:
            java.lang.String r1 = "widevine"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L3b
            goto L51
        L3b:
            java.util.UUID r3 = androidx.media3.common.C.WIDEVINE_UUID
            return r3
        L3e:
            java.lang.String r1 = "org.w3.clearkey"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L51
        L46:
            java.util.UUID r3 = androidx.media3.common.C.CLEARKEY_UUID
            return r3
        L49:
            java.lang.String r1 = "playready"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L59
        L51:
            java.util.UUID r3 = java.util.UUID.fromString(r3)     // Catch: java.lang.IllegalArgumentException -> L56
            goto L58
        L56:
            java.util.UUID r3 = androidx.media3.common.C.WIDEVINE_UUID
        L58:
            return r3
        L59:
            java.util.UUID r3 = androidx.media3.common.C.PLAYREADY_UUID
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.ClearKeyUtil.drmSchemeToUuid(java.lang.String):java.util.UUID");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0033, code lost:
    
        if (r1.equals("com.microsoft.playready") == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003a, code lost:
    
        if (r1.equals("widevine") == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003d, code lost:
    
        return "widevine";
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0044, code lost:
    
        if (r1.equals("org.w3.clearkey") != false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0046, code lost:
    
        return "clearkey";
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004b, code lost:
    
        if (r1.equals("playready") == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005a, code lost:
    
        return "playready";
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0021, code lost:
    
        if (r1.equals("clearkey") == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002a, code lost:
    
        if (r1.equals("com.widevine.alpha") == false) goto L23;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String normalizeScheme(java.lang.String r7) {
        /*
            r6 = this;
            java.util.Locale r0 = java.util.Locale.ROOT
            java.lang.String r1 = r7.toLowerCase(r0)
            java.lang.CharSequence r1 = kotlin.text.o.L0(r1)
            java.lang.String r1 = r1.toString()
            int r2 = r1.hashCode()
            java.lang.String r3 = "playready"
            java.lang.String r4 = "widevine"
            java.lang.String r5 = "clearkey"
            switch(r2) {
                case -1860423953: goto L47;
                case -1746801670: goto L3e;
                case -1400551171: goto L36;
                case -1380835838: goto L2d;
                case -6637254: goto L24;
                case 790309106: goto L1d;
                default: goto L1c;
            }
        L1c:
            goto L4d
        L1d:
            boolean r1 = r1.equals(r5)
            if (r1 != 0) goto L46
            goto L4d
        L24:
            java.lang.String r2 = "com.widevine.alpha"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L3d
            goto L4d
        L2d:
            java.lang.String r2 = "com.microsoft.playready"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L5a
            goto L4d
        L36:
            boolean r1 = r1.equals(r4)
            if (r1 != 0) goto L3d
            goto L4d
        L3d:
            return r4
        L3e:
            java.lang.String r2 = "org.w3.clearkey"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L4d
        L46:
            return r5
        L47:
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L5a
        L4d:
            java.lang.String r7 = r7.toLowerCase(r0)
            java.lang.CharSequence r7 = kotlin.text.o.L0(r7)
            java.lang.String r7 = r7.toString()
            return r7
        L5a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.ClearKeyUtil.normalizeScheme(java.lang.String):java.lang.String");
    }
}
