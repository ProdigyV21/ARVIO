package androidx.core.os;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public final class n implements o {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Locale[] f2128c = new Locale[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Locale[] f2129a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f2130b;

    static {
        new Locale("en", "XA");
        new Locale("ar", "XB");
        String[] strArrSplit = "en-Latn".split("-", -1);
        if (strArrSplit.length > 2) {
            new Locale(strArrSplit[0], strArrSplit[1], strArrSplit[2]);
        } else if (strArrSplit.length > 1) {
            new Locale(strArrSplit[0], strArrSplit[1]);
        } else {
            if (strArrSplit.length != 1) {
                throw new IllegalArgumentException("Can not parse language tag: [en-Latn]");
            }
            new Locale(strArrSplit[0]);
        }
    }

    public n(Locale... localeArr) {
        if (localeArr.length == 0) {
            this.f2129a = f2128c;
            this.f2130b = "";
            return;
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        StringBuilder sb2 = new StringBuilder();
        for (int i10 = 0; i10 < localeArr.length; i10++) {
            Locale locale = localeArr[i10];
            if (locale == null) {
                throw new NullPointerException(androidx.compose.foundation.c.o(i10, "list[", "] is null"));
            }
            if (!hashSet.contains(locale)) {
                Locale locale2 = (Locale) locale.clone();
                arrayList.add(locale2);
                sb2.append(locale2.getLanguage());
                String country = locale2.getCountry();
                if (country != null && !country.isEmpty()) {
                    sb2.append('-');
                    sb2.append(locale2.getCountry());
                }
                if (i10 < localeArr.length - 1) {
                    sb2.append(',');
                }
                hashSet.add(locale2);
            }
        }
        this.f2129a = (Locale[]) arrayList.toArray(new Locale[0]);
        this.f2130b = sb2.toString();
    }

    @Override // androidx.core.os.o
    public final String a() {
        return this.f2130b;
    }

    @Override // androidx.core.os.o
    public final Object b() {
        return null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        Locale[] localeArr = ((n) obj).f2129a;
        Locale[] localeArr2 = this.f2129a;
        if (localeArr2.length != localeArr.length) {
            return false;
        }
        for (int i10 = 0; i10 < localeArr2.length; i10++) {
            if (!localeArr2[i10].equals(localeArr[i10])) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.core.os.o
    public final Locale get(int i10) {
        if (i10 < 0) {
            return null;
        }
        Locale[] localeArr = this.f2129a;
        if (i10 < localeArr.length) {
            return localeArr[i10];
        }
        return null;
    }

    public final int hashCode() {
        int iHashCode = 1;
        for (Locale locale : this.f2129a) {
            iHashCode = (iHashCode * 31) + locale.hashCode();
        }
        return iHashCode;
    }

    @Override // androidx.core.os.o
    public final boolean isEmpty() {
        return this.f2129a.length == 0;
    }

    @Override // androidx.core.os.o
    public final int size() {
        return this.f2129a.length;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("[");
        int i10 = 0;
        while (true) {
            Locale[] localeArr = this.f2129a;
            if (i10 >= localeArr.length) {
                sb2.append("]");
                return sb2.toString();
            }
            sb2.append(localeArr[i10]);
            if (i10 < localeArr.length - 1) {
                sb2.append(',');
            }
            i10++;
        }
    }
}
