package androidx.appcompat.app;

import android.content.res.Configuration;
import android.os.LocaleList;

/* JADX INFO: loaded from: classes.dex */
public abstract class i0 {
    public static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
        LocaleList locales = configuration.getLocales();
        LocaleList locales2 = configuration2.getLocales();
        if (locales.equals(locales2)) {
            return;
        }
        configuration3.setLocales(locales2);
        configuration3.locale = configuration2.locale;
    }

    public static androidx.core.os.m b(Configuration configuration) {
        return androidx.core.os.m.b(configuration.getLocales().toLanguageTags());
    }

    public static void c(androidx.core.os.m mVar) {
        LocaleList.setDefault(LocaleList.forLanguageTags(mVar.f()));
    }

    public static void d(Configuration configuration, androidx.core.os.m mVar) {
        configuration.setLocales(LocaleList.forLanguageTags(mVar.f()));
    }
}
