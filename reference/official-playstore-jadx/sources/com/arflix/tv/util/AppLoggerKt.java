package com.arflix.tv.util;

import com.arflix.tv.ui.screens.player.w0;
import java.security.MessageDigest;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.collections.x;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0001¨\u0006\u0004"}, d2 = {"hash", "", "sanitizeEmail", "maskToken", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class AppLoggerKt {
    public static final String hash(String str) {
        try {
            return x.u0(r.l0(6, MessageDigest.getInstance("SHA-256").digest(str.getBytes(kotlin.text.a.f19924a))), "", null, null, new w0(24), 30);
        } catch (Exception unused) {
            return "hash_error";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence hash$lambda$0(byte b10) {
        return String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b10)}, 1));
    }

    public static final String maskToken(String str) {
        return str.length() > 4 ? o.I0(4, str).concat("***") : "***";
    }

    public static final String sanitizeEmail(String str) {
        int iE0 = o.e0(str, '@', 0, false, 6);
        int iK0 = o.k0(str, '.', 0, 6);
        if (iE0 <= 0 || iK0 <= iE0) {
            return "[EMAIL]";
        }
        return str.charAt(0) + "***@***." + str.substring(iK0 + 1);
    }
}
