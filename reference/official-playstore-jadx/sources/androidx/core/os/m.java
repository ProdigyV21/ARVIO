package androidx.core.os;

import android.os.Build;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public final class m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final m f2126b = a(new Locale[0]);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o f2127a;

    public m(o oVar) {
        this.f2127a = oVar;
    }

    public static m a(Locale... localeArr) {
        return Build.VERSION.SDK_INT >= 24 ? new m(new p(l.a(localeArr))) : new m(new n(localeArr));
    }

    public static m b(String str) {
        if (str == null || str.isEmpty()) {
            return f2126b;
        }
        String[] strArrSplit = str.split(",", -1);
        int length = strArrSplit.length;
        Locale[] localeArr = new Locale[length];
        for (int i10 = 0; i10 < length; i10++) {
            localeArr[i10] = k.a(strArrSplit[i10]);
        }
        return a(localeArr);
    }

    public final Locale c(int i10) {
        return this.f2127a.get(i10);
    }

    public final boolean d() {
        return this.f2127a.isEmpty();
    }

    public final int e() {
        return this.f2127a.size();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof m) {
            return this.f2127a.equals(((m) obj).f2127a);
        }
        return false;
    }

    public final String f() {
        return this.f2127a.a();
    }

    public final int hashCode() {
        return this.f2127a.hashCode();
    }

    public final String toString() {
        return this.f2127a.toString();
    }
}
