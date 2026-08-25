package com.google.android.gms.cast.framework;

import androidx.compose.foundation.c;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public final class CastState {
    public static final int CONNECTED = 4;
    public static final int CONNECTING = 3;
    public static final int NOT_CONNECTED = 2;
    public static final int NO_DEVICES_AVAILABLE = 1;

    private CastState() {
    }

    public static String toString(int i10) {
        if (i10 == 1) {
            return "NO_DEVICES_AVAILABLE";
        }
        if (i10 == 2) {
            return "NOT_CONNECTED";
        }
        if (i10 == 3) {
            return "CONNECTING";
        }
        if (i10 == 4) {
            return "CONNECTED";
        }
        Locale locale = Locale.ROOT;
        return c.o(i10, "UNKNOWN_STATE(", ")");
    }
}
