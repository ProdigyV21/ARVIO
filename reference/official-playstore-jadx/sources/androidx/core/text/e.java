package androidx.core.text;

import android.os.Build;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Method f2181a;

    static {
        if (Build.VERSION.SDK_INT < 24) {
            try {
                f2181a = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", Locale.class);
            } catch (Exception e5) {
                throw new IllegalStateException(e5);
            }
        }
    }

    public static String a(Locale locale) {
        if (Build.VERSION.SDK_INT >= 24) {
            return d.c(d.a(d.b(locale)));
        }
        try {
            return c.a((Locale) f2181a.invoke(null, locale));
        } catch (IllegalAccessException e5) {
            Log.w("ICUCompat", e5);
            return c.a(locale);
        } catch (InvocationTargetException e6) {
            Log.w("ICUCompat", e6);
            return c.a(locale);
        }
    }
}
