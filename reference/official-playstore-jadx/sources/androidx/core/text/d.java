package androidx.core.text;

import android.icu.util.ULocale;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public abstract class d {
    public static ULocale a(Object obj) {
        return ULocale.addLikelySubtags((ULocale) obj);
    }

    public static ULocale b(Locale locale) {
        return ULocale.forLocale(locale);
    }

    public static String c(Object obj) {
        return ((ULocale) obj).getScript();
    }
}
