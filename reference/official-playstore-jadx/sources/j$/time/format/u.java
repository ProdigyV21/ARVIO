package j$.time.format;

import j$.time.ZoneId;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DateTimeFormatter f18290a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f18291b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f18292c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f18293d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList f18294e;

    public u(DateTimeFormatter dateTimeFormatter) {
        ArrayList arrayList = new ArrayList();
        this.f18293d = arrayList;
        this.f18294e = null;
        this.f18290a = dateTimeFormatter;
        arrayList.add(new a0());
    }

    public final boolean a(char c10, char c11) {
        if (this.f18291b) {
            return c10 == c11;
        }
        return b(c10, c11);
    }

    public final boolean g(CharSequence charSequence, int i10, CharSequence charSequence2, int i11, int i12) {
        if (i10 + i12 <= charSequence.length() && i11 + i12 <= charSequence2.length()) {
            if (this.f18291b) {
                for (int i13 = 0; i13 < i12; i13++) {
                    if (charSequence.charAt(i10 + i13) == charSequence2.charAt(i11 + i13)) {
                    }
                }
                return true;
            }
            for (int i14 = 0; i14 < i12; i14++) {
                char cCharAt = charSequence.charAt(i10 + i14);
                char cCharAt2 = charSequence2.charAt(i11 + i14);
                if (cCharAt == cCharAt2 || Character.toUpperCase(cCharAt) == Character.toUpperCase(cCharAt2) || Character.toLowerCase(cCharAt) == Character.toLowerCase(cCharAt2)) {
                }
            }
            return true;
        }
        return false;
    }

    public static boolean b(char c10, char c11) {
        return c10 == c11 || Character.toUpperCase(c10) == Character.toUpperCase(c11) || Character.toLowerCase(c10) == Character.toLowerCase(c11);
    }

    public final a0 c() {
        return (a0) this.f18293d.get(r0.size() - 1);
    }

    public final Long d(j$.time.temporal.a aVar) {
        return (Long) ((HashMap) c().f18223a).get(aVar);
    }

    public final int f(j$.time.temporal.o oVar, long j10, int i10, int i11) {
        Objects.requireNonNull(oVar, "field");
        Long l10 = (Long) ((HashMap) c().f18223a).put(oVar, Long.valueOf(j10));
        return (l10 == null || l10.longValue() == j10) ? i11 : ~i10;
    }

    public final void e(ZoneId zoneId) {
        Objects.requireNonNull(zoneId, "zone");
        c().f18224b = zoneId;
    }

    public final String toString() {
        return c().toString();
    }
}
