package androidx.appcompat.app;

import android.content.res.Configuration;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class g0 {
    public static void a(Configuration configuration, Locale locale) {
        configuration.setLayoutDirection(locale);
    }

    public static void b(Configuration configuration, Locale locale) {
        configuration.setLocale(locale);
    }
}
