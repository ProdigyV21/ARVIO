package androidx.media3.ui;

import android.graphics.Color;
import androidx.fragment.app.a2;
import androidx.media3.common.util.Util;

/* JADX INFO: loaded from: classes3.dex */
final class HtmlUtils {
    private HtmlUtils() {
    }

    public static String cssAllClassDescendantsSelector(String str) {
        return a2.m(".", str, ",.", str, " *");
    }

    public static String toCssRgba(int i10) {
        return Util.formatInvariant("rgba(%d,%d,%d,%.3f)", Integer.valueOf(Color.red(i10)), Integer.valueOf(Color.green(i10)), Integer.valueOf(Color.blue(i10)), Double.valueOf(((double) Color.alpha(i10)) / 255.0d));
    }
}
