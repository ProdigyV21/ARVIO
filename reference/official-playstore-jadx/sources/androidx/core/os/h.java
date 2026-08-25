package androidx.core.os;

import android.content.res.Configuration;
import android.os.LocaleList;

/* JADX INFO: loaded from: classes3.dex */
public abstract class h {
    public static LocaleList a(Configuration configuration) {
        return configuration.getLocales();
    }

    public static void b(Configuration configuration, m mVar) {
        configuration.setLocales((LocaleList) mVar.f2127a.b());
    }
}
