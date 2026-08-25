package j$.time.format;

import io.ktor.util.date.GMTDateParser;
import j$.time.DateTimeException;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import java.text.ParsePosition;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class s implements e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile Map.Entry f18281c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile Map.Entry f18282d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j$.time.c f18283a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f18284b;

    public m a(u uVar) {
        Set<String> set = j$.time.zone.i.f18419d;
        int size = set.size();
        Map.Entry simpleImmutableEntry = uVar.f18291b ? f18281c : f18282d;
        if (simpleImmutableEntry == null || ((Integer) simpleImmutableEntry.getKey()).intValue() != size) {
            synchronized (this) {
                try {
                    simpleImmutableEntry = uVar.f18291b ? f18281c : f18282d;
                    if (simpleImmutableEntry == null || ((Integer) simpleImmutableEntry.getKey()).intValue() != size) {
                        Integer numValueOf = Integer.valueOf(size);
                        m mVar = uVar.f18291b ? new m("", null, null) : new l("", null, null);
                        for (String str : set) {
                            mVar.a(str, str);
                        }
                        simpleImmutableEntry = new AbstractMap.SimpleImmutableEntry(numValueOf, mVar);
                        if (uVar.f18291b) {
                            f18281c = simpleImmutableEntry;
                        } else {
                            f18282d = simpleImmutableEntry;
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return (m) simpleImmutableEntry.getValue();
    }

    public s(j$.time.c cVar, String str) {
        this.f18283a = cVar;
        this.f18284b = str;
    }

    @Override // j$.time.format.e
    public boolean l(w wVar, StringBuilder sb2) {
        ZoneId zoneId = (ZoneId) wVar.b(this.f18283a);
        if (zoneId == null) {
            return false;
        }
        sb2.append(zoneId.k());
        return true;
    }

    @Override // j$.time.format.e
    public final int m(u uVar, CharSequence charSequence, int i10) {
        int i11;
        int length = charSequence.length();
        if (i10 > length) {
            throw new IndexOutOfBoundsException();
        }
        if (i10 == length) {
            return ~i10;
        }
        char cCharAt = charSequence.charAt(i10);
        if (cCharAt == '+' || cCharAt == '-') {
            return b(uVar, charSequence, i10, i10, j.f18254e);
        }
        int i12 = i10 + 2;
        if (length >= i12) {
            char cCharAt2 = charSequence.charAt(i10 + 1);
            if (uVar.a(cCharAt, 'U') && uVar.a(cCharAt2, 'T')) {
                int i13 = i10 + 3;
                if (length >= i13 && uVar.a(charSequence.charAt(i12), 'C')) {
                    return b(uVar, charSequence, i10, i13, j.f18255f);
                }
                return b(uVar, charSequence, i10, i12, j.f18255f);
            }
            if (uVar.a(cCharAt, 'G') && length >= (i11 = i10 + 3) && uVar.a(cCharAt2, GMTDateParser.MONTH) && uVar.a(charSequence.charAt(i12), 'T')) {
                int i14 = i10 + 4;
                if (length >= i14 && uVar.a(charSequence.charAt(i11), '0')) {
                    uVar.e(ZoneId.of("GMT0"));
                    return i14;
                }
                return b(uVar, charSequence, i10, i11, j.f18255f);
            }
        }
        m mVarA = a(uVar);
        ParsePosition parsePosition = new ParsePosition(i10);
        String strC = mVarA.c(charSequence, parsePosition);
        if (strC == null) {
            if (!uVar.a(cCharAt, 'Z')) {
                return ~i10;
            }
            uVar.e(ZoneOffset.UTC);
            return i10 + 1;
        }
        uVar.e(ZoneId.of(strC));
        return parsePosition.getIndex();
    }

    public static int b(u uVar, CharSequence charSequence, int i10, int i11, j jVar) {
        String upperCase = charSequence.subSequence(i10, i11).toString().toUpperCase();
        if (i11 >= charSequence.length()) {
            uVar.e(ZoneId.of(upperCase));
            return i11;
        }
        if (charSequence.charAt(i11) != '0' && !uVar.a(charSequence.charAt(i11), 'Z')) {
            u uVar2 = new u(uVar.f18290a);
            uVar2.f18291b = uVar.f18291b;
            uVar2.f18292c = uVar.f18292c;
            int iM = jVar.m(uVar2, charSequence, i11);
            try {
                if (iM < 0) {
                    if (jVar == j.f18254e) {
                        return ~i10;
                    }
                    uVar.e(ZoneId.of(upperCase));
                    return i11;
                }
                uVar.e(ZoneId.X(upperCase, ZoneOffset.d0((int) uVar2.d(j$.time.temporal.a.OFFSET_SECONDS).longValue())));
                return iM;
            } catch (DateTimeException unused) {
                return ~i10;
            }
        }
        uVar.e(ZoneId.of(upperCase));
        return i11;
    }

    public final String toString() {
        return this.f18284b;
    }
}
