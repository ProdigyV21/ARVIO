package j$.time.chrono;

import j$.time.DateTimeException;
import j$.time.LocalDate;
import j$.time.temporal.Temporal;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class v implements j, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final v f18184d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final v[] f18185e;
    private static final long serialVersionUID = 1466499369062886794L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final transient int f18186a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final transient LocalDate f18187b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final transient String f18188c;

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ Object G(j$.time.c cVar) {
        return j$.com.android.tools.r8.a.v(this, cVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ long H(j$.time.temporal.o oVar) {
        return j$.com.android.tools.r8.a.o(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ boolean e(j$.time.temporal.o oVar) {
        return j$.com.android.tools.r8.a.r(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ int l(j$.time.temporal.o oVar) {
        return j$.com.android.tools.r8.a.m(this, oVar);
    }

    static {
        v vVar = new v(-1, LocalDate.of(1868, 1, 1), "Meiji");
        f18184d = vVar;
        f18185e = new v[]{vVar, new v(0, LocalDate.of(1912, 7, 30), "Taisho"), new v(1, LocalDate.of(1926, 12, 25), "Showa"), new v(2, LocalDate.of(1989, 1, 8), "Heisei"), new v(3, LocalDate.of(2019, 5, 1), "Reiwa")};
    }

    public final v k() {
        if (this == f18185e[r0.length - 1]) {
            return null;
        }
        return o(this.f18186a + 1);
    }

    public v(int i10, LocalDate localDate, String str) {
        this.f18186a = i10;
        this.f18187b = localDate;
        this.f18188c = str;
    }

    public static v o(int i10) {
        int i11 = i10 + 1;
        if (i11 >= 0) {
            v[] vVarArr = f18185e;
            if (i11 < vVarArr.length) {
                return vVarArr[i11];
            }
        }
        throw new DateTimeException("Invalid era: " + i10);
    }

    @Override // j$.time.temporal.l
    public final Temporal r(Temporal temporal) {
        return temporal.c(getValue(), j$.time.temporal.a.ERA);
    }

    public static v j(LocalDate localDate) {
        if (localDate.a0(u.f18180d)) {
            throw new DateTimeException("JapaneseDate before Meiji 6 are not supported");
        }
        for (int length = f18185e.length - 1; length >= 0; length--) {
            v vVar = f18185e[length];
            if (localDate.compareTo(vVar.f18187b) >= 0) {
                return vVar;
            }
        }
        return null;
    }

    @Override // j$.time.chrono.j
    public final int getValue() {
        return this.f18186a;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final j$.time.temporal.s n(j$.time.temporal.o oVar) {
        j$.time.temporal.a aVar = j$.time.temporal.a.ERA;
        if (oVar != aVar) {
            return j$.time.temporal.p.d(this, oVar);
        }
        return s.f18178d.u(aVar);
    }

    public final String toString() {
        return this.f18188c;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new b0((byte) 5, this);
    }
}
