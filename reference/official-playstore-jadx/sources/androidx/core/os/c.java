package androidx.core.os;

import android.os.Build;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public abstract class c {
    static {
        int i10 = Build.VERSION.SDK_INT;
        a aVar = a.f2120a;
        if (i10 >= 30) {
            aVar.a(30);
        }
        if (i10 >= 30) {
            aVar.a(31);
        }
        if (i10 >= 30) {
            aVar.a(33);
        }
        if (i10 >= 30) {
            aVar.a(1000000);
        }
    }

    public static final boolean a() {
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 33) {
            return true;
        }
        if (i10 < 32) {
            return false;
        }
        String str = Build.VERSION.CODENAME;
        if ("REL".equals(str)) {
            return false;
        }
        Locale locale = Locale.ROOT;
        return str.toUpperCase(locale).compareTo("Tiramisu".toUpperCase(locale)) >= 0;
    }
}
