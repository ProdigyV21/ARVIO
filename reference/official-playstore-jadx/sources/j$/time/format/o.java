package j$.time.format;

import j$.time.LocalDate;
import j$.time.chrono.ChronoLocalDate;
import j$.time.chrono.Chronology;
import j$.util.Objects;
import j$.util.function.Consumer$CC;
import java.util.ArrayList;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class o extends i {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final LocalDate f18272h = LocalDate.of(2000, 1, 1);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ChronoLocalDate f18273g;

    @Override // j$.time.format.i
    public final boolean b(u uVar) {
        if (uVar.f18292c) {
            return super.b(uVar);
        }
        return false;
    }

    public o(j$.time.temporal.o oVar, int i10, int i11, ChronoLocalDate chronoLocalDate, int i12) {
        super(oVar, i10, i11, c0.NOT_NEGATIVE, i12);
        this.f18273g = chronoLocalDate;
    }

    @Override // j$.time.format.i
    public final long a(w wVar, long j10) {
        long jAbs = Math.abs(j10);
        ChronoLocalDate chronoLocalDate = this.f18273g;
        long jL = chronoLocalDate != null ? Chronology.CC.a(wVar.f18299a).E(chronoLocalDate).l(this.f18248a) : 0;
        long[] jArr = i.f18247f;
        if (j10 >= jL) {
            long j11 = jArr[this.f18249b];
            if (j10 < jL + j11) {
                return jAbs % j11;
            }
        }
        return jAbs % jArr[this.f18250c];
    }

    @Override // j$.time.format.i
    public final int c(u uVar, long j10, int i10, int i11) {
        final o oVar;
        final u uVar2;
        final long j11;
        final int i12;
        final int i13;
        int iL;
        long j12;
        ChronoLocalDate chronoLocalDate = this.f18273g;
        if (chronoLocalDate != null) {
            Chronology chronology = uVar.c().f18225c;
            if (chronology == null && (chronology = uVar.f18290a.f18203e) == null) {
                chronology = j$.time.chrono.p.f18175d;
            }
            iL = chronology.E(chronoLocalDate).l(this.f18248a);
            oVar = this;
            uVar2 = uVar;
            j11 = j10;
            i12 = i10;
            i13 = i11;
            Consumer consumer = new Consumer() { // from class: j$.time.format.n
                public final /* synthetic */ Consumer andThen(Consumer consumer2) {
                    return Consumer$CC.$default$andThen(this, consumer2);
                }

                @Override // java.util.function.Consumer
                /* JADX INFO: renamed from: accept */
                public final void n(Object obj) {
                    this.f18267a.c(uVar2, j11, i12, i13);
                }
            };
            if (uVar2.f18294e == null) {
                uVar2.f18294e = new ArrayList();
            }
            uVar2.f18294e.add(consumer);
        } else {
            oVar = this;
            uVar2 = uVar;
            j11 = j10;
            i12 = i10;
            i13 = i11;
            iL = 0;
        }
        int i14 = i13 - i12;
        int i15 = oVar.f18249b;
        if (i14 != i15 || j11 < 0) {
            j12 = j11;
        } else {
            long j13 = i.f18247f[i15];
            long j14 = iL;
            long j15 = j14 - (j14 % j13);
            long j16 = iL > 0 ? j15 + j11 : j15 - j11;
            j12 = j16 < j14 ? j13 + j16 : j16;
        }
        return uVar2.f(oVar.f18248a, j12, i12, i13);
    }

    @Override // j$.time.format.i
    public final i d() {
        if (this.f18252e == -1) {
            return this;
        }
        return new o(this.f18248a, this.f18249b, this.f18250c, this.f18273g, -1);
    }

    @Override // j$.time.format.i
    public final i e(int i10) {
        return new o(this.f18248a, this.f18249b, this.f18250c, this.f18273g, this.f18252e + i10);
    }

    @Override // j$.time.format.i
    public final String toString() {
        Object objRequireNonNull = this.f18273g;
        if (objRequireNonNull == null) {
            objRequireNonNull = Objects.requireNonNull(0, "defaultObj");
        }
        return "ReducedValue(" + this.f18248a + "," + this.f18249b + "," + this.f18250c + "," + objRequireNonNull + ")";
    }
}
